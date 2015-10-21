package io.github.xylsh.task;

import io.github.xylsh.service.IMonitorService;
import io.github.xylsh.service.ISystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by apple on 15-4-6.
 */
@Component
public class BuckupTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuckupTask.class);

    @Resource
    private ISystemService systemService;

    public void backup(){
        LOGGER.info("BuckupTask.backup() start...");

        systemService.startBackup();

        LOGGER.info("BuckupTask.backup() end...");
    }
}
