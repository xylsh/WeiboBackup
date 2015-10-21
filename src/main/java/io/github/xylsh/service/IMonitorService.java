package io.github.xylsh.service;

import io.github.xylsh.model.MonitorDomain;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by apple on 15-4-6.
 */
public interface IMonitorService {

    void startMonitorService();

    void stopMonitorService();

    boolean isServiceStart();

    //void backup();

    boolean addMonitor(MonitorDomain monitorDomain);

    List<MonitorDomain> getAllMonitor();

    int stopMonitor(long monitorId);

    int startMonitor(long monitorId);

    MonitorDomain getMonitor(long monitorId);

    List<Long> getWeiboIds(long monitorId);
}
