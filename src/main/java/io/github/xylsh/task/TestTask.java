package io.github.xylsh.task;

import com.google.common.base.Splitter;
import io.github.xylsh.util.StringUtil;
import io.github.xylsh.util.ThreadUtil;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by apple on 15-4-4.
 */
@Component
public class TestTask {

    //private static volatile long generation = 0;
    private static Executor executor = Executors.newFixedThreadPool(4);

    public void test() {
        System.out.println("test task running..." + new Date());
    }

    public static void main(String[] args0) throws Exception {
        for (int i = 1; i <= 7; i++) {
            executor.execute(new Task(executor));
        }
        int notifyCount = 0;
        while (notifyCount < 7) {
            executor.wait();
            notifyCount++;
        }
        System.out.println("主线程退出..");
    }

    private static class Task implements Runnable {
        Executor executor;

        private Task(Executor executor) {
            this.executor = executor;
        }

        @Override
        public void run() {
            //...do some work
            DownloadService.download(Thread.currentThread() + ":下载图片...");
            executor.notifyAll();
        }
    }
}

class DownloadService {
    static CyclicBarrier c = new CyclicBarrier(4, new Runnable() {
        @Override
        public void run() {
            ThreadUtil.sleep(15 * 1000);
        }
    });

    public static void download(String tip) {
        //download work...
        System.out.println(tip);

        try {
            c.await();
            //ThreadUtil.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

}