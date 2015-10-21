package io.github.xylsh.service.impl;

import static com.google.common.base.Preconditions.*;

import io.github.xylsh.model.MonitorDomain;
import io.github.xylsh.dao.MonitorDao;
import io.github.xylsh.service.IConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 15-4-7.
 */
@Service
public class DBConfigService implements IConfigService {

    private static final Logger logger = LoggerFactory.getLogger(DBConfigService.class);

    @Resource
    private MonitorDao monitorDao;

    @Override
    public List<MonitorDomain> getAllMonitors() {
        return monitorDao.getAllMonitor();
    }

    @Override
    public int updateNewestWeiboDate(MonitorDomain monitorDomain, Date newestWeiboDate) {
        checkNotNull(monitorDomain);

        if (newestWeiboDate == null || monitorDomain.getLastWeiboTime().after(newestWeiboDate)
                || monitorDomain.getLastWeiboTime().equals(newestWeiboDate)) {
            logger.info("无需更新指定monitor的'last_weibo_time', newestWeiboDate={}, lastWeiboDate={}",
                    newestWeiboDate, monitorDomain.getLastWeiboTime());
            return 0;
        }

        logger.info("更新指定monitor的'last_weibo_time',updateNewestWeiboDate(newestWeiboDate={}, monitorDomain={})", newestWeiboDate, monitorDomain);
        //返回修改行数
        return monitorDao.updateNewestWeiboDate(monitorDomain.getId(), newestWeiboDate);
    }
}
