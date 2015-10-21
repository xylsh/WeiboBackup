package io.github.xylsh.service.impl;

import static com.google.common.base.Preconditions.*;

import io.github.xylsh.common.IProcessor;
import io.github.xylsh.service.INetworkService;
import io.github.xylsh.util.ThreadUtil;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by apple on 15-4-6.
 */
@Service
public class NetworkService implements INetworkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkService.class);

    public String request(String url, Map<String, String> headers) throws IOException {
        Request request = Request.Get(url);
        addHeader(request, headers);

        return request.execute().returnContent().asString();  //出错抛出异常
    }

    public <T> T request(String url, Map<String, String> headers, IProcessor<String, T> processor) {
        checkNotNull(processor);
        LOGGER.info("request(url={},headers={},processor)", url, headers);

        T result = null;
        try {
            result = processor.process(request(url, headers));   //处理逻辑与网络请求解耦
        } catch (IOException e) {
            LOGGER.error("请求url出错,url:{}", url, e);
        }
        return result;  //出错返回空
    }

    public boolean downloadFile(String url, File toSaveFile) {
        return downloadFile(url, toSaveFile, null);
    }

    public boolean downloadFile(String url, File toSaveFile, Map<String, String> headers) {
        LOGGER.info("{}, downloadFile(url={},toSaveFile={})", Thread.currentThread(), url, toSaveFile);

        try {
            Request request = Request.Get(url);
            addHeader(request, headers);
            request.execute().saveContent(toSaveFile);  //todo:应对下载失败的图片

            return true;
        } catch (IOException e) {
            LOGGER.error("{}, 下载文件失败.url={},toSaveFile={}", Thread.currentThread(), url, toSaveFile, e);
            return false;
        }
    }

    public static final int EXECUTOR_SIZE = 3;
    private static final Executor executor = Executors.newFixedThreadPool(EXECUTOR_SIZE);

    public boolean downloadFileWithExcutor(String url, File toSaveFile, Map<String, String> headers, CyclicBarrier barrier) {
        checkNotNull(barrier);

        DownloadTask downloadTask = null;
        if (url == null || toSaveFile == null) {
            //LOGGER.info("{},即将提交空任务...", Thread.currentThread());
            downloadTask = new DownloadTask(barrier);
        } else {
            //LOGGER.info("{},即将提交下载任务,...", Thread.currentThread());
            downloadTask = new DownloadTask(url, toSaveFile, headers, barrier);
        }
        executor.execute(downloadTask);

        return true;
    }

    private class DownloadTask implements Runnable {
        private String url;
        private File toSaveFile;
        private Map<String, String> headers;
        private CyclicBarrier barrier;

        private DownloadTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        private DownloadTask(String url, File toSaveFile, Map<String, String> headers, CyclicBarrier barrier) {
            this.url = url;
            this.toSaveFile = toSaveFile;
            this.headers = headers;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            if (url != null && toSaveFile != null && barrier != null) {
                downloadFile(url, toSaveFile, headers);
                LOGGER.info("{},下载结束.url={},toSaveFile={}", Thread.currentThread(), url, toSaveFile);
            } else {
                LOGGER.info("{},下载结束,这是一个空任务，无需下载.", Thread.currentThread());
            }

            try {
                LOGGER.info("{}, barrier.await()", Thread.currentThread());
                barrier.await();
                ThreadUtil.sleep(BackupService.PIC_WITH_EXECUTOR_SLEEP_TIME);
            } catch (InterruptedException e) {
                LOGGER.error("DownloadTask.run(),InterruptedException,url={},toSaveFile={},barrier={}",
                        url, toSaveFile, barrier, e);
            } catch (BrokenBarrierException e) {
                LOGGER.error("DownloadTask.run(),BrokenBarrierException,url={},toSaveFile={},barrier={}",
                        url, toSaveFile, barrier, e);
            }
        }
    }

    private void addHeader(Request request, Map<String, String> headers) {
        if (headers == null || headers.size() == 0) {
            return;
        }
        for (String header : headers.keySet()) {
            request.addHeader(header, headers.get(header));
        }
    }
}
