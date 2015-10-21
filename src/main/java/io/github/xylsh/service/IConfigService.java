package io.github.xylsh.service;

import io.github.xylsh.model.MonitorDomain;

import java.util.Date;
import java.util.List;

/**
 * Created by apple on 15-4-6.
 */
public interface IConfigService {

    List<MonitorDomain> getAllMonitors();

    int updateNewestWeiboDate(MonitorDomain monitorDomain, Date newestWeiboDate);

}
