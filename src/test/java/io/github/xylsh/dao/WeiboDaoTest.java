package io.github.xylsh.dao;

import com.google.common.collect.Lists;
import io.github.xylsh.model.WeiboDomain;
import io.github.xylsh.junit.AbstractSpringJUnite4Test;
import io.github.xylsh.model.ext.WeiboVo;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WeiboDaoTest extends AbstractSpringJUnite4Test {

    @Resource
    private WeiboDao weiboDao;

    @Test
    public void testSave() throws Exception {
        WeiboDomain weiboDomain = new WeiboDomain();
        weiboDomain.setWeiboNickname("test");
        weiboDomain.setWeiboPics("test");
        weiboDomain.setWeiboText("test");
        weiboDomain.setWeiboTime(new Date());
        weiboDomain.setRetweetWeiboId(0L);

        System.out.println(weiboDao.save(weiboDomain));
        System.out.println("id=" + weiboDomain.getId());
    }

    @Test
    public void testGetWeiboByWeiboIds(){
        List<Long> weiboIdList = Lists.newArrayList(37L, 38L, 40L, 42L, 43L, 45L, 46L, 47L, 49L, 51L, 52L, 53L, 55L, 57L, 58L, 59L, 61L, 62L, 63L, 64L, 66L, 67L, 68L, 70L, 71L, 73L, 75L, 76L, 77L, 78L, 80L, 81L, 83L, 84L, 85L, 86L, 87L, 89L, 90L);
        List<Long> weiboIdList2 = Lists.newArrayList();
        List<Map<String,Object>> weiboVoList = weiboDao.getWeiboByWeiboIds(weiboIdList);
        System.out.println(weiboIdList);
        System.out.println(weiboVoList);
    }
}