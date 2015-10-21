package io.github.xylsh.service.impl;

import static com.google.common.base.Preconditions.*;

import io.github.xylsh.bean.Card;
import io.github.xylsh.bean.MBlog;
import io.github.xylsh.bean.PicInfo;
import io.github.xylsh.model.MonitorDomain;
import io.github.xylsh.model.MonitorWeiboDomain;
import io.github.xylsh.model.WeiboDomain;
import io.github.xylsh.dao.MonitorWeiboDao;
import io.github.xylsh.dao.WeiboDao;
import io.github.xylsh.service.INetworkService;
import io.github.xylsh.service.IPersistenceService;
import io.github.xylsh.util.HTTPHeaders;
import io.github.xylsh.util.StringUtil;
import io.github.xylsh.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by apple on 15-4-7.
 */
@Service
public class DBPersistenceService implements IPersistenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBConfigService.class);

    private static final Long NO_RETWEET_WEIBO_ID = 0L;
    private static final String ERROR_WEIBO_TEXT = "[微博异常，可能有不支持的字符]";

    private static final String CLASS_PATH = DBPersistenceService.class.getResource("/").getFile();
    private static final String RESOURCE_DIR_NAME = "resources";
    private static final String PIC_DIR_NAME = "pic";

    @Resource
    private WeiboDao weiboDao;
    @Resource
    private MonitorWeiboDao monitorWeiboDao;
    @Resource
    private INetworkService networkService;

    @Override
    public void save(Card card, MonitorDomain monitorDomain) {
        checkNotNull(card);
        checkNotNull(monitorDomain);

        save(card.getmBlog(), monitorDomain);
    }

    public long save(MBlog mBlog, MonitorDomain monitorDomain) {
        checkNotNull(mBlog);

        long retweetWeiboId = NO_RETWEET_WEIBO_ID;
        if (mBlog.getRetweetedStatus() != null) {
            retweetWeiboId = saveRetweetMBlog(mBlog);
        }

        return saveMainMBlog(mBlog, retweetWeiboId, monitorDomain);  //return 刚插入的记录的id
    }

    /**
     * 保存主微博
     *
     * @param mainBlog
     * @param retweetWeiboId
     * @param monitor
     * @return
     */
    public long saveMainMBlog(MBlog mainBlog, long retweetWeiboId, MonitorDomain monitor) {
        checkNotNull(mainBlog);

        WeiboDomain weiboDomain = new WeiboDomain(mainBlog);
        weiboDomain.setRetweetWeiboId(retweetWeiboId);

        //插入weibo表
        boolean isSucc = saveWeiboRetry2(weiboDomain);

        //插入monitor_weibo表
        if (isSucc) {
            //insert monitor_weibo
            MonitorWeiboDomain monitorWeiboDomain = new MonitorWeiboDomain();
            monitorWeiboDomain.setMonitorId(monitor.getId());
            monitorWeiboDomain.setWeiboId(weiboDomain.getId());
            monitorWeiboDao.save(monitorWeiboDomain);
        }

        //下载图片
        File userDir = getUserDir(mainBlog);
        savePicWithExcutor(mainBlog.getPicInfos(), userDir);

        return weiboDomain.getId();
    }


    /**
     * 保存被转发的微博
     *
     * @param mainBlog 主微博
     * @return
     */
    public long saveRetweetMBlog(MBlog mainBlog) {
        checkNotNull(mainBlog);

        MBlog retweetBlog = mainBlog.getRetweetedStatus();
        if (retweetBlog == null) {
            return NO_RETWEET_WEIBO_ID;
        }

        WeiboDomain weiboDomain = new WeiboDomain(retweetBlog);
        weiboDomain.setRetweetWeiboId(NO_RETWEET_WEIBO_ID);

        //插入weibo表；无需插入monitor_weibo表
        saveWeiboRetry2(weiboDomain);

        //下载图片
        File retweetUserDir = new File(getUserDir(mainBlog), retweetBlog.getUser().getId());
        savePicWithExcutor(retweetBlog.getPicInfos(), retweetUserDir);

        return weiboDomain.getId();
    }

    public boolean saveWeiboRetry2(WeiboDomain weiboDomain) {
        checkNotNull(weiboDomain);

        boolean saveWeiboSucc = false;
        if (!(saveWeiboSucc = saveLogException(weiboDomain))) {
            LOGGER.error("持久化微博出错,即将去掉微博内容后重试，weiboDomain={}", weiboDomain);
            weiboDomain.setWeiboText(ERROR_WEIBO_TEXT);
            saveWeiboSucc = saveLogException(weiboDomain);
        }

        return saveWeiboSucc;
    }

    private File getUserDir(MBlog mBlog) {
        checkNotNull(mBlog);

        File webinfDir = new File(CLASS_PATH).getParentFile();
        File resourcesDir = new File(webinfDir, RESOURCE_DIR_NAME);
        File picDir = new File(resourcesDir, PIC_DIR_NAME);
        File userDir = new File(picDir, mBlog.getUser().getId());

        return userDir;
    }

    public void savePic(PicInfo[] picInfos, File saveDir) {
        checkNotNull(saveDir);
        if (picInfos == null || picInfos.length <= 0) {
            return;
        }

        if (!saveDir.exists() || !saveDir.isDirectory()) {
            saveDir.mkdirs();
        }

        for (int i = 0; i < picInfos.length; i++) {
            PicInfo picInfo = picInfos[i];
            String url = picInfo.getLargest().getUrl();
            File saveFile = new File(saveDir, url.substring(url.lastIndexOf("/") + 1));
            if (saveFile.exists()) {   //如果重名
                saveFile = StringUtil.renameFile(saveFile);
            }
            networkService.downloadFile(url, saveFile);

            if (i != (picInfos.length - 1)) {  //下载到最后一张图片时，无需休眠
                //单数休眠PIC_SLEEP_TIME，偶数休眠PIC_SLEEP_TIME/2
                ThreadUtil.sleep(i % 2 == 1 ? BackupService.PIC_SLEEP_TIME : BackupService.PIC_SLEEP_TIME / 2);
            }
        }
    }

    private static volatile CyclicBarrier barrier = new CyclicBarrier(NetworkService.EXECUTOR_SIZE + 1);

    public void savePicWithExcutor(PicInfo[] picInfos, File saveDir) {
        checkNotNull(saveDir);
        if (picInfos == null || picInfos.length <= 0) {
            return;
        }

        LOGGER.info("savePicWithExcutor(picInfos={}, saveDir={})", picInfos, saveDir);

        if (!saveDir.exists() || !saveDir.isDirectory()) {
            saveDir.mkdirs();
        }

        initBarrier();
        for (int i = 0; i < picInfos.length; i += 3) {
            for (int y = i; y < i + 3; y++) {    //每次提交3个任务
                if (y >= picInfos.length || picInfos[y] == null) {
                    commitToNetworkService(null, null);    //提交空任务
                } else {
                    PicInfo picInfo = picInfos[y];
                    commitToNetworkService(picInfo, saveDir);
                }
            }

            try {
                LOGGER.info("{}, barrier.await()", Thread.currentThread());
                barrier.await();
                ThreadUtil.sleep(BackupService.PIC_WITH_EXECUTOR_SLEEP_TIME);
            } catch (InterruptedException e) {
                LOGGER.error("savePicWithExcutor(),InterruptedException,i={},picInfos={},saveDir={}",
                        i, picInfos, saveDir, e);
            } catch (BrokenBarrierException e) {
                LOGGER.error("savePicWithExcutor(),BrokenBarrierException,i={},picInfos={},saveDir={}",
                        i, picInfos, saveDir, e);
                initBarrier();
            }
        }
    }

    private void initBarrier(){
        if( barrier == null || barrier.isBroken() ){
            barrier = new CyclicBarrier(NetworkService.EXECUTOR_SIZE + 1);
        }
    }

    private void commitToNetworkService(PicInfo picInfo, File saveDir) {
        if (picInfo == null || saveDir == null) {
            networkService.downloadFileWithExcutor(null, null, null, barrier);//提交空任务
            return;
        }

        String url = picInfo.getLargest().getUrl();
        File saveFile = new File(saveDir, url.substring(url.lastIndexOf("/") + 1));
        if (saveFile.exists()) {   //如果重名
            saveFile = StringUtil.renameFile(saveFile);
        }
        networkService.downloadFileWithExcutor(url, saveFile, HTTPHeaders.PIC_REQUEST_HEADER, barrier);
    }

    private boolean saveLogException(WeiboDomain weiboDomain) {
        try {
            if (weiboDomain != null) {
                save(weiboDomain);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("持久化微博出错，weiboDomain={}", weiboDomain, e);
            return false;
        }
    }

    private long save(WeiboDomain weiboDomain) {
        checkNotNull(weiboDomain);
        LOGGER.info("即将持久化微博,save(weiboDomain={})", weiboDomain);

        int rowsAffected = weiboDao.save(weiboDomain);
        checkArgument(rowsAffected == 1, "持久化微博出错，rowsAffected=%s,weiboDomain=%s.", rowsAffected, weiboDomain);

        return weiboDomain.getId();
    }

}