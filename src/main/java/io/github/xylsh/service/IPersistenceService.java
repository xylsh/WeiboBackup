package io.github.xylsh.service;

import io.github.xylsh.bean.Card;
import io.github.xylsh.bean.MBlog;
import io.github.xylsh.bean.PicInfo;
import io.github.xylsh.model.MonitorDomain;
import io.github.xylsh.model.WeiboDomain;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by apple on 15-4-6.
 */
public interface IPersistenceService {

    public void save(Card card, MonitorDomain monitorDomain);

    public long save(MBlog mBlog, MonitorDomain monitorDomain);

    /**
     * 保存主微博
     * @param mainBlog
     * @param retweetWeiboId
     * @param monitor
     * @return
     */
    long saveMainMBlog(MBlog mainBlog, long retweetWeiboId, MonitorDomain monitor);


    /**
     * 保存被转发的微博
     *
     * @param mainBlog 主微博
     * @return
     */
    long saveRetweetMBlog(MBlog mainBlog);

    boolean saveWeiboRetry2(WeiboDomain weiboDomain);

    void savePic(PicInfo[] picInfos, File saveDir);

    void savePicWithExcutor(PicInfo[] picInfos, File saveDir);
}
