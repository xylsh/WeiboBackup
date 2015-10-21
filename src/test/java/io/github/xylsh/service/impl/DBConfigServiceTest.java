package io.github.xylsh.service.impl;

import io.github.xylsh.model.MonitorDomain;
import io.github.xylsh.dao.MonitorDao;
import io.github.xylsh.service.IConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/dao.xml",
        "classpath:spring/service.xml",
        "classpath:spring/config.xml"
})
public class DBConfigServiceTest {

    @Resource(type = DBConfigService.class)
    private IConfigService configService;// = new DBConfigService();
    @Resource
    private MonitorDao monitorDao;

    @Test
    public void testGetAllMonitors() throws Exception {
        System.out.println(monitorDao.getAllMonitor());
    }

    @Test
    public void testUpdateNewestWeiboDate() throws Exception {
        List<MonitorDomain> monitorDomains = monitorDao.getAllMonitor();
        int r = configService.updateNewestWeiboDate(monitorDomains.get(0), new Date());
        System.out.println(r);
    }
}