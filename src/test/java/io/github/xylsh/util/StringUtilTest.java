package io.github.xylsh.util;

import org.junit.Test;

import java.io.File;
import java.util.Date;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void testResourceNameFromUrl() throws Exception {
        String url = "http://ww3.sinaimg.cn/crop.0.0.0.0/0063PFBRjw1eq9m7nk5i8j30k00k0td9.jpg";
        assertEquals(StringUtil.resourceNameFromUrl(url), "0063PFBRjw1eq9m7nk5i8j30k00k0td9.jpg");
    }

    @Test
    public void testRenameFile() throws Exception {
        File file = new File("/home/apple/ideaprojects/test.txt");
        File newFile = StringUtil.renameFile(file);
        System.out.println(newFile);
    }


}