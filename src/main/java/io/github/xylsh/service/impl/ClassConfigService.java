package io.github.xylsh.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.github.xylsh.model.MonitorDomain;
import io.github.xylsh.service.IConfigService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 从类自身的信息里读取配置
 * Created by apple on 15-4-6.
 */
@Service
@Deprecated
public class ClassConfigService implements IConfigService {

    private List<MonitorDomain> monitorDomains;

    public ClassConfigService() {
        Calendar calendar = Calendar.getInstance();
        //calendar.set(1970, 1, 1, 0, 0, 0);
        DateTime dateTime = new DateTime(2015, 4, 5, 11, 59, 12);

        MonitorDomain monitorDomain = new MonitorDomain();
        monitorDomain.setId(1L)
                //.setWeiboUid("2487489574")
                .setWeiboNickname("KIYA酱_niku")
                .setWeiboUrl("http://weibo.com/kiyaniku")
                        //.setWeiboInterfaceUrl("http://api.weibo.cn/2/cardlist?v_f=2&fid=1076035553440679_-_WEIBO_SECOND_PROFILE_WEIBO&uid=5580825148&count=20&c=android&wm=5311_4002&luicode=10000001&from=1051195010&lang=zh_CN&skin=default&i=0b172d2&s=720e3ab8&gsid=4uG2b83530GBqbHQS0NJ7npPj2o&page=1&containerid=1076035553440679_-_WEIBO_SECOND_PROFILE_WEIBO&ua=AMOI-AMOI%20N828__weibo__5.1.1__android__android4.2.1&oldwm=5311_0001&v_p=18&uicode=10000198&featurecode=10000001")
                .setWeiboInterfaceUrl("http://api.weibo.cn/2/guest/cardlist?v_f=2&fid=1076035553440679_-_WEIBO_SECOND_PROFILE_WEIBO&uid=1000819473427&lfid=100303type%3D1%26t%3D3&checktoken=3f213cad0b199df07516405b260c0615&count=20&c=android&wm=30001_0001&did=80dab7f622da61cc8a10ddb95c784e7a4ee26019&luicode=10000003&from=1052095010&lang=zh_CN&lcardid=weibo_31_1_6_0&skin=default&i=0b172d2&s=1859a19a&gsid=4wbgb8353ck0DoA56t0Sp15JkRz6T&page=1&containerid=1076035553440679_-_WEIBO_SECOND_PROFILE_WEIBO&ua=AMOI-AMOI%20N828__weibo__5.2.0__android__android4.2.1&oldwm=30001_0001&v_p=18&uicode=10000198&featurecode=10000085")
                .setLastWeiboTime(dateTime.toDate());
        monitorDomains = Lists.newArrayList(monitorDomain);
    }

    @Override
    public List<MonitorDomain> getAllMonitors() {
        return monitorDomains;
    }

    @Override
    public int updateNewestWeiboDate(MonitorDomain monitorDomain, Date newestWeiboDate) {
        if (newestWeiboDate != null && !newestWeiboDate.equals(monitorDomain.getLastWeiboTime())) {
            monitorDomain.setLastWeiboTime(newestWeiboDate);
        }
        return 1;
    }
}
