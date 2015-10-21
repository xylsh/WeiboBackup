package io.github.xylsh.util;

import com.google.common.collect.ImmutableMap;

/**
 * Created by apple on 15-4-24.
 */
public class HTTPHeaders {

    public static final ImmutableMap<String, String> CARD_REQUEST_HEADER = ImmutableMap.<String, String>builder()
            .put("Accept-Encoding", "gzip,deflate")
            .put("Connection", "Keep-Alive")
            .put("User-Agent", "OPPO N9_4.1.1_weibo_5.1.1_android")
                    //.put("X-Log-Uid", "5080815198")  //请求者的uid
            .build();

    public static final ImmutableMap<String, String> PIC_REQUEST_HEADER = ImmutableMap.<String, String>builder()
            .put("Connection", "keep-alive")
            .put("Pragma", "no-cache")
            .put("Cache-Control", "no-cache")
            .put("Accept", "image/webp,*/*;q=0.8")
            .put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36")
            .put("Referer", "http://weibo.com/hejiong")
            .put("Accept-Encoding", "gzip, deflate, sdch")
            .put("Accept-Language", "zh-CN,zh;q=0.8")
            .build();

}
