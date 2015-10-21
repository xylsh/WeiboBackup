package io.github.xylsh.service.impl;

import io.github.xylsh.service.IBackupService;
import io.github.xylsh.service.IMonitorService;
import io.github.xylsh.service.ISystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by apple on 15-4-22.
 */
@Service
public class SystemService implements ISystemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemService.class);

    @Resource
    private IMonitorService monitorService;
    @Resource
    private IBackupService backupService;

    private Thread backupThread;

    public void startBackup(){
        if( !monitorService.isServiceStart() ){  //如果服务没有开启
            LOGGER.info("monitorService没有开启，SystemService.startBackup() end.");
            return;
        }

        if( backupThread != null && backupThread.getState() != Thread.State.TERMINATED ){
            LOGGER.info("上一次备份没有结束，这一次将不会备份.SystemService.startBackup() end.");
            return;
        }

        backupThread = new Thread(new Runnable() {
            @Override
            public void run() {
                backupService.backup();
            }
        });
        backupThread.start();
    }

    public void stopBackup(){
        backupService.stopBackup();
    }
}
