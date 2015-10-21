package io.github.xylsh.service.impl;

import io.github.xylsh.junit.AbstractSpringJUnite4Test;
import io.github.xylsh.model.ext.WeiboVo;
import io.github.xylsh.service.IWeiboService;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class WeiboServiceTest extends AbstractSpringJUnite4Test {

    @Resource
    private IWeiboService weiboService;

    @Test
    public void testGetWeiboByMonitorId() throws Exception {
        List<WeiboVo> weiboVoList = weiboService.getWeiboByMonitorId(1);
        System.out.println(weiboVoList);
    }
}