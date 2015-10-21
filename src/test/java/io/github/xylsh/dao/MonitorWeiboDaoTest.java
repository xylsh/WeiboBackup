package io.github.xylsh.dao;

import io.github.xylsh.model.MonitorWeiboDomain;
import io.github.xylsh.junit.AbstractSpringJUnite4Test;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class MonitorWeiboDaoTest extends AbstractSpringJUnite4Test {

    @Resource
    private MonitorWeiboDao monitorWeiboDao;

    @Test
    public void testSave() throws Exception {
        MonitorWeiboDomain monitorWeiboDomain = new MonitorWeiboDomain();
        monitorWeiboDomain.setWeiboId(2L);
        monitorWeiboDomain.setMonitorId(1L);
        int rowAffected = monitorWeiboDao.save(monitorWeiboDomain);
        System.out.println(rowAffected);
        System.out.println("id=" + monitorWeiboDomain.getId());
    }

    @Test
    public void testGetWeiboIds() {
        List<Long> weiboIds = monitorWeiboDao.getWeiboIds(1);
        System.out.println(weiboIds);
    }

}