package io.github.xylsh.service;

import io.github.xylsh.model.ext.WeiboVo;

import java.util.List;

/**
 * Created by apple on 15-4-15.
 */
public interface IWeiboService {

    List<WeiboVo> getWeiboByMonitorId(long monitorId);

    List<WeiboVo> getWeiboByMonitorId(long monitorId, int page, int size);
}
