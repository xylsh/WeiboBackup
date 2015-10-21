package io.github.xylsh.service.impl;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import io.github.xylsh.bean.Card;
import io.github.xylsh.bean.MBlog;
import io.github.xylsh.bean.PicInfo;
import io.github.xylsh.model.MonitorDomain;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by apple on 15-4-6.
 */
@Service
@Deprecated
public class FilePersistenceService{//} implements IPersistenceService {

    private static final String DEFAUT_FILE_NAME = "weibo_backup.txt";

    private static final Charset DEFAULT_CHARSET = Charsets.UTF_8;
    private static final String CLASS_PATH = FilePersistenceService.class.getResource("/").getFile();

    //@Override
    public void save(Card card,MonitorDomain monitorDomain) {
        CharSink charSink = Files.asCharSink(new File(CLASS_PATH + card.getmBlog().getUser().getScreenName() + ".txt")
                , DEFAULT_CHARSET, FileWriteMode.APPEND);
        List<String> stringList = Lists.newArrayList();

        MBlog mBlog = card.getmBlog();
        addMBlog(stringList, mBlog);

        if (mBlog.getRetweetedStatus() != null) {
            stringList.add("转发自：");
            addMBlog(stringList, mBlog.getRetweetedStatus());
        }
        stringList.add("----------------");

        try {
            charSink.writeLines(stringList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addMBlog(List<String> stringList, MBlog mBlog) {
        stringList.add(mBlog.getUser().getScreenName());
        stringList.add(mBlog.getCreatedAt().toString());
        stringList.add(mBlog.getText());

        PicInfo[] picInfos = mBlog.getPicInfos();
        if (picInfos != null && picInfos.length > 0) {
            stringList.add("图片：");
            for (PicInfo picInfo : picInfos) {
                stringList.add(picInfo.getLargest().getUrl());
            }
        }
    }

}
