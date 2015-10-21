package io.github.xylsh.dao;

import io.github.xylsh.model.WeiboDomain;
import io.github.xylsh.model.ext.WeiboVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 15-4-8.
 */
@Repository
public interface WeiboDao {

    int save(WeiboDomain weiboDomain);

    /**
     * 查询指定id集合对应的微博
     * @param weiboIdList
     * @return 返回的list是按时间逆序排列的
     */
    List<Map<String,Object>> getWeiboByWeiboIds(@Param("weiboIdList") List<Long> weiboIdList);

}
