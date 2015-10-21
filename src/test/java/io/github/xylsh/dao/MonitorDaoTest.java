package io.github.xylsh.dao;

import com.google.common.base.Splitter;
import io.github.xylsh.model.MonitorDomain;
import io.github.xylsh.junit.AbstractSpringJUnite4Test;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

public class MonitorDaoTest extends AbstractSpringJUnite4Test {

    @Resource
    private MonitorDao monitorDao;

    @Test
    public void testGetAllMonitor() throws Exception {
        System.out.println(monitorDao.getAllMonitor());
    }

    @Test
    public void testUpdateNewestWeiboDate() throws Exception {
        List<MonitorDomain> monitorDomains = monitorDao.getAllMonitor();
        int r = monitorDao.updateNewestWeiboDate(monitorDomains.get(0).getId(), new Date());
        System.out.println(r);
    }

    @Test
    public void testInsert() {
        MonitorDomain monitorDomain = new MonitorDomain();
        monitorDomain.setWeiboNickname("test");
        monitorDomain.setWeiboUid("test");
        monitorDomain.setWeiboUrl("test");
        monitorDomain.setWeiboInterfaceUrl("test");
        monitorDomain.setIsStop(true);

        System.out.println(monitorDao.insert(monitorDomain));
        System.out.println(monitorDomain.getId());
    }

    @Test
    public void testStopMonitor() {
        int rowAffected = monitorDao.stopMonitor(46L);
        System.out.println(rowAffected);
    }

    @Test
    public void testStartMonitor() {
        int rowAffected = monitorDao.startMonitor(46L);
        System.out.println(rowAffected);
    }
}