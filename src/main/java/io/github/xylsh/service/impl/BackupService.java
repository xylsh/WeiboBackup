package io.github.xylsh.service.impl;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import io.github.xylsh.bean.Card;
import io.github.xylsh.model.MonitorDomain;
import io.github.xylsh.common.JSONToCardProcessor;
import io.github.xylsh.service.IBackupService;
import io.github.xylsh.service.IConfigService;
import io.github.xylsh.service.INetworkService;
import io.github.xylsh.service.IPersistenceService;
import io.github.xylsh.util.HTTPHeaders;
import io.github.xylsh.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by apple on 15-4-6.
 */
@Service
public class BackupService implements IBackupService {

    public static final long WEIBO_SLEEP_TIME = 30 * 1000;
    public static final long PIC_SLEEP_TIME = 24 * 1000;
    public static final long PIC_WITH_EXECUTOR_SLEEP_TIME = 20 * 1000;

    private static final Logger LOGGER = LoggerFactory.getLogger(BackupService.class);

    @Resource(type = DBConfigService.class)
    private IConfigService configService;
    @Resource
    private INetworkService networkService;
    @Resource(type = DBPersistenceService.class)
    private IPersistenceService persistenceService;

    private volatile boolean shouldStop = false;

    @Override
    public void backup() {
        LOGGER.info("备份开始，BackupService.backup() start...");

        shouldStop = false;

        List<MonitorDomain> monitorDomains = configService.getAllMonitors();

        for (MonitorDomain monitorDomain : monitorDomains) {
            if (shouldStop) {  //如果被要求停止
                LOGGER.info("shouldStop={}, 备份即将停止...", shouldStop);
                break;
            }

            if (monitorDomain.getIsStop()) {
                continue;
            }

            try {
                doBackup(monitorDomain);
            } catch (Exception e) {  //这样做是为了防止如果备份其中一个monitorDomain出错，导致后面的monitorDomain都得不到备份,todo:这样做好吗？
                LOGGER.error("备份异常，monitorDomain={}", monitorDomain, e);
            }

            ThreadUtil.sleep(WEIBO_SLEEP_TIME);  //无论是不是最后一个monitorDomain，休眠都是必要的
        }

        LOGGER.info("备份结束，BackupService.backup() end...");
    }

    public void stopBackup() {
        shouldStop = true;
    }

    //todo:委托给备份strategy,策略模式
    private void doBackup(MonitorDomain monitorDomain) {
        checkNotNull(monitorDomain);
        /*依次请求接口，直到找到上一条微博后的所有微博，然后持久化*/
        LOGGER.info("BackupService.doBackup(monitorDomain={})", monitorDomain);

        final Date lastWeiboDate = monitorDomain.getLastWeiboTime();
        JSONToCardProcessor jsonToCardProcessor = new JSONToCardProcessor();

        Date newestWeiboDate = null;
        boolean haveAllWeiboSinceLast = false;
        int currPage = 1;
        while (!haveAllWeiboSinceLast) {   //遍历每一页
            String currUrl = monitorDomain.getWeiboInterfaceUrl(currPage);
            List<Card> cardList = networkService.request(currUrl, HTTPHeaders.CARD_REQUEST_HEADER, jsonToCardProcessor);
            //jsonToCardProcessor的实现保证了即使出错，也会返回一个没有元素的list，所以无需检查cardList==null

            //筛选出正确时间段的微博
            Iterator<Card> cardIterator = Iterables.filter(cardList, new DatePredicate(lastWeiboDate)).iterator();

            int addCount = 0;
            while (cardIterator.hasNext()) {   //遍历card
                Card card = cardIterator.next();
                persistenceService.save(card, monitorDomain);  //持久化当前card
                addCount++;

                if (newestWeiboDate == null || card.getmBlog().getCreatedAt().after(newestWeiboDate)) {
                    newestWeiboDate = card.getmBlog().getCreatedAt();
                }
            }

            if (addCount == 0) {  //只要这一页添加了card，就到下一页看看
                haveAllWeiboSinceLast = true;
            }
            currPage++;

            if (!haveAllWeiboSinceLast) {  //最后一次请求只是用来确定要保存的微博保存完了，所以无需休眠
                ThreadUtil.sleep(WEIBO_SLEEP_TIME);
            }
        }

        //别忘了更新最新微博时间
        configService.updateNewestWeiboDate(monitorDomain, newestWeiboDate);
    }

    private class DatePredicate implements Predicate<Card> {
        private Date lastWeiboDate;

        private DatePredicate(Date lastWeiboDate) {
            this.lastWeiboDate = lastWeiboDate;
        }

        @Override
        public boolean apply(Card input) {
            if (input.getmBlog().getCreatedAt().after(lastWeiboDate)) {
                return true;
            }
            return false;
        }
    }

}
