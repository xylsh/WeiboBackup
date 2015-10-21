package io.github.xylsh.service;

import io.github.xylsh.bean.Card;
import io.github.xylsh.common.IProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by apple on 15-4-6.
 */
public interface INetworkService {

    String request(String url, Map<String, String> headers) throws IOException;

    <T> T request(String url, Map<String, String> headers, IProcessor<String, T> iProcessor);

    boolean downloadFile(String url, File toSaveFile);

    boolean downloadFileWithExcutor(String url, File toSaveFile, Map<String, String> headers, CyclicBarrier barrier);

}
