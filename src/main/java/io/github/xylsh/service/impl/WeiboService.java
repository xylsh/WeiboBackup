package io.github.xylsh.service.impl;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.Lists;
import io.github.xylsh.dao.MonitorWeiboDao;
import io.github.xylsh.dao.WeiboDao;
import io.github.xylsh.model.MonitorDomain;
import io.github.xylsh.model.WeiboDomain;
import io.github.xylsh.model.ext.WeiboVo;
import io.github.xylsh.service.IMonitorService;
import io.github.xylsh.service.IWeiboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by apple on 15-4-15.
 */
@Service
public class WeiboService implements IWeiboService {

    @Resource
    private IMonitorService monitorService;
    @Resource
    private MonitorWeiboDao monitorWeiboDao;
    @Resource
    private WeiboDao weiboDao;

    public List<WeiboVo> getWeiboByMonitorId(long monitorId) {
        checkArgument(monitorId >= 0);

        MonitorDomain pointMonitor = monitorService.getMonitor(monitorId);
        if (pointMonitor == null) {  //如果指定的monitor不存在，则返回空列表
            return Lists.newArrayList();
        }

        List<Long> weiboIdList = monitorWeiboDao.getWeiboIds(monitorId);
        if (weiboIdList == null || weiboIdList.size() == 0) {
            return Lists.newArrayList();   //指定的monitor没有存储微博，则返回空列表
        }

        List<Map<String, Object>> originalWeiboVoMapList = weiboDao.getWeiboByWeiboIds(weiboIdList);
        List<WeiboVo> weiboVoList = parseWeiboVo(originalWeiboVoMapList, pointMonitor.getWeiboUid());

        return weiboVoList;
    }

    public List<WeiboVo> getWeiboByMonitorId(long monitorId, int page, int size) {
        checkArgument(monitorId >= 0);
        checkArgument(page >= 0);
        checkArgument(size >= 0);

        MonitorDomain pointMonitor = monitorService.getMonitor(monitorId);
        if (pointMonitor == null) {  //如果指定的monitor不存在，则返回空列表
            return Lists.newArrayList();
        }

        List<Long> allWeiboIdList = monitorWeiboDao.getWeiboIds(monitorId);
        if (allWeiboIdList == null || allWeiboIdList.size() == 0) {
            return Lists.newArrayList();   //指定的monitor没有存储微博，则返回空列表
        }

        int fromIndex = (page - 1) * size;
        int toIndex = fromIndex + size;
        toIndex = toIndex > allWeiboIdList.size() ? allWeiboIdList.size() : toIndex;

        List<Map<String, Object>> originalWeiboVoMapList = weiboDao.getWeiboByWeiboIds(allWeiboIdList).subList(fromIndex, toIndex);
        List<WeiboVo> weiboVoList = parseWeiboVo(originalWeiboVoMapList, pointMonitor.getWeiboUid());

        return weiboVoList;
    }

    private List<WeiboVo> parseWeiboVo(List<Map<String, Object>> originalWeiboVoMapList, String monitorWeiboUid) {
        List<WeiboVo> weiboVoList = Lists.newArrayList();

        for (Map<String, Object> originalWeiboVoMap : originalWeiboVoMapList) {
            WeiboVo weiboVo = new WeiboVo(monitorWeiboUid);

            WeiboDomain mainWeiboDomain = new WeiboDomain();
            mainWeiboDomain.setId((Long) originalWeiboVoMap.get("main_id"));
            mainWeiboDomain.setWeiboNickname((String) originalWeiboVoMap.get("main_nickname"));
            mainWeiboDomain.setWeiboText((String) originalWeiboVoMap.get("main_text"));
            mainWeiboDomain.setWeiboTime((Timestamp) originalWeiboVoMap.get("main_time"));
            mainWeiboDomain.setWeiboPics((String) originalWeiboVoMap.get("mian_pics"));
            mainWeiboDomain.setRetweetWeiboId((Long) originalWeiboVoMap.get("main_retweet_weibo_id"));
            weiboVo.setMainWeibo(mainWeiboDomain);

            if (mainWeiboDomain.getRetweetWeiboId() != null && mainWeiboDomain.getRetweetWeiboId() > 0L) {
                WeiboDomain retweetWeiboDomain = new WeiboDomain();
                retweetWeiboDomain.setId((Long) originalWeiboVoMap.get("retweet_id"));
                retweetWeiboDomain.setWeiboNickname((String) originalWeiboVoMap.get("retweet_nickname"));
                retweetWeiboDomain.setWeiboText((String) originalWeiboVoMap.get("retweet_text"));
                retweetWeiboDomain.setWeiboTime((Timestamp) originalWeiboVoMap.get("retweet_time"));
                retweetWeiboDomain.setWeiboPics((String) originalWeiboVoMap.get("retweet_pics"));
                retweetWeiboDomain.setRetweetWeiboId((Long) originalWeiboVoMap.get("retweet_retweet_weibo_id"));
                weiboVo.setRetweetWeibo(retweetWeiboDomain);
            }

            weiboVoList.add(weiboVo);
        }

        return weiboVoList;
    }

}
