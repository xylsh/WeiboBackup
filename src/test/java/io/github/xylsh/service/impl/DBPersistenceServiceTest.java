package io.github.xylsh.service.impl;

import io.github.xylsh.bean.PicInfo;
import io.github.xylsh.bean.PicType;
import io.github.xylsh.junit.AbstractSpringJUnite4Test;
import io.github.xylsh.service.IPersistenceService;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;

import static org.junit.Assert.*;

public class DBPersistenceServiceTest extends AbstractSpringJUnite4Test {

    @Resource
    private IPersistenceService persistenceService;

    @Test
    public void testSavePicWithExecutor() throws Exception {
        PicInfo[] picInfos = new PicInfo[9];

        PicType largest0 = new PicType("http://ww4.sinaimg.cn/large/a25b020bjw1erfwfww7boj21kw16odni.jpg", "JPEG");
        picInfos[0] = new PicInfo("a25b020bjw1erfwfww7boj21kw16odni", largest0);

        PicType largest1 = new PicType("http://ww2.sinaimg.cn/woriginal/a25b020bjw1erfwig1hz8j21kw16o45h.jpg", "JPEG");
        picInfos[1] = new PicInfo("a25b020bjw1erfwig1hz8j21kw16o45h", largest1);

        PicType largest2 = new PicType("http://ww2.sinaimg.cn/large/a25b020bjw1erfwfzp7m5j21kw16ojy6.jpg", "JPEG");
        picInfos[2] = new PicInfo("a25b020bjw1erfwfzp7m5j21kw16ojy6", largest2);

        PicType largest3 = new PicType("http://ww2.sinaimg.cn/large/a25b020bjw1erfwg14jb8j21kw16ogrx.jpg", "JPEG");
        picInfos[3] = new PicInfo("a25b020bjw1erfwg14jb8j21kw16ogrx", largest3);

        PicType largest4 = new PicType("http://ww3.sinaimg.cn/large/a25b020bjw1erfwg3wvkzj21kw16o0yz.jpg", "JPEG");
        picInfos[4] = new PicInfo("a25b020bjw1erfwg3wvkzj21kw16o0yz", largest4);

        PicType largest5 = new PicType("http://ww1.sinaimg.cn/large/a25b020bjw1erfwg51werj21kw16on44.jpg", "JPEG");
        picInfos[5] = new PicInfo("a25b020bjw1erfwg51werj21kw16on44", largest5);

        PicType largest6 = new PicType("http://ww4.sinaimg.cn/large/a25b020bjw1erfwg6c83wj21kw16o0xu.jpg", "JPEG");
        picInfos[6] = new PicInfo("a25b020bjw1erfwg6c83wj21kw16o0xu", largest6);

        PicType largest7 = new PicType("http://ww2.sinaimg.cn/large/a25b020bjw1erfwg7mbt4j21kw16o459.jpg", "JPEG");
        picInfos[7] = new PicInfo("a25b020bjw1erfwg7mbt4j21kw16o459", largest7);

        PicType largest8 = new PicType("http://ww1.sinaimg.cn/large/a25b020bjw1erfwg2lkvrj21kw16ojyw.jpg", "JPEG");
        picInfos[8] = new PicInfo("a25b020bjw1erfwg2lkvrj21kw16ojyw", largest8);

        File dir1 = new File("/home/apple/ideaprojects/WeiboBackup/out/test");
        File dir2 = new File("/home/apple/ideaprojects/WeiboBackup/out/test2");

        long start = System.currentTimeMillis();
        persistenceService.savePicWithExcutor(picInfos, dir1);
        long end = System.currentTimeMillis();
        System.out.println("线程池版耗时：" + (end - start) / 1000 + "s.");

//        start = System.currentTimeMillis();
//        persistenceService.savePicOld(picInfos, dir2);
//        end = System.currentTimeMillis();
//        System.out.println("非线程池版耗时：" + (end - start) / 1000 + "s.");
    }
}