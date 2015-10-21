package io.github.xylsh.service.impl;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.Lists;
import io.github.xylsh.dao.MonitorWeiboDao;
import io.github.xylsh.model.MonitorDomain;
import io.github.xylsh.dao.MonitorDao;
import io.github.xylsh.service.IBackupService;
import io.github.xylsh.service.IMonitorService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * 总的应用控制服务
 * Created by apple on 15-4-4.
 */
@Service
public class MonitorService implements IMonitorService {

    private static final String USER_URL_PREFIX = "http://weibo.com/u/";
    private static final Logger logger = LoggerFactory.getLogger(MonitorService.class);

    @Resource
    private IBackupService backupService;
    @Resource
    private MonitorDao monitorDao;
    @Resource
    private MonitorWeiboDao monitorWeiboDao;

    private boolean isServiceStart = true;

    /**
     * 启动服务
     */
    public void startMonitorService() {
        //1.初始化工作：（1）检查数据库，恢复上次关闭监控时的状态
        /**
         *  1.初始化：从数据库中读取要监控的微博信息--暂时跳过
         *  2.启动服务
         */
        isServiceStart = true;
    }

    public void stopMonitorService() {
        isServiceStart = false;
    }

    public boolean isServiceStart() {
        return isServiceStart;
    }

//    public void backup() {
//        backupService.backup();
//    }

    public boolean addMonitor(MonitorDomain monitorDomain) {
        checkNotNull(monitorDomain);

        if (StringUtils.isBlank(monitorDomain.getWeiboUrl())) {
            monitorDomain.setWeiboUrl(USER_URL_PREFIX + monitorDomain.getWeiboUid());
        }

        int rowAffected = monitorDao.insert(monitorDomain);
        if (rowAffected <= 0) {
            logger.error("插入monitorDomain失败，rowAffected={},monitorDomain={}", rowAffected, monitorDomain);
            return false;
        }

        return true;
    }

    public List<MonitorDomain> getAllMonitor() {
        return monitorDao.getAllMonitor();
    }

    public int stopMonitor(long monitorId) {
        checkArgument(monitorId >= 0);

        return monitorDao.stopMonitor(monitorId);
    }

    public int startMonitor(long monitorId) {
        checkArgument(monitorId >= 0);

        return monitorDao.stopMonitor(monitorId);
    }

    public MonitorDomain getMonitor(long monitorId) {
        if (monitorId < 0) {
            return null;
        }

        MonitorDomain pointMonitor = null;
        List<MonitorDomain> monitorDomainList = getAllMonitor();
        for (MonitorDomain monitorDomain : monitorDomainList) {
            if (monitorId == monitorDomain.getId()) {
                pointMonitor = monitorDomain;
                break;
            }
        }

        return pointMonitor;
    }

    public List<Long> getWeiboIds(long monitorId) {
        if (monitorId < 0) {
            return Lists.newArrayList();
        }

        return monitorWeiboDao.getWeiboIds(monitorId);
    }
}
