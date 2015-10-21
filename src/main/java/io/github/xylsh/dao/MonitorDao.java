package io.github.xylsh.dao;

import io.github.xylsh.model.MonitorDomain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by apple on 15-4-7.
 */
@Repository
public interface MonitorDao {

    List<MonitorDomain> getAllMonitor();

    int updateNewestWeiboDate(@Param("id") Long id,@Param("newestWeiboDate") Date newestWeiboDate);

    int insert(MonitorDomain monitorDomain);

    int stopMonitor(@Param("id") Long id);

    int startMonitor(@Param("id") Long id);
}
