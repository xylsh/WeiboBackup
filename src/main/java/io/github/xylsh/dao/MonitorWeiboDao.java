package io.github.xylsh.dao;

import io.github.xylsh.model.MonitorWeiboDomain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by apple on 15-4-8.
 */
@Repository
public interface MonitorWeiboDao {

    int save(MonitorWeiboDomain monitorWeiboDomain);

    List<Long> getWeiboIds(@Param("monitorId") long monitorId);
}
