package io.github.xylsh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by apple on 15-4-7.
 */
public class ThreadUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUtil.class);

    public static void sleep(long millis) {
        LOGGER.info("sleep(millis={}), {}, 休眠{}s...", millis, Thread.currentThread(), millis / 1000);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LOGGER.error("休眠被打断", e);
        }
    }
}
