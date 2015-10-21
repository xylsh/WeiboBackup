package io.github.xylsh.util;

/**
 * Created by apple on 15-4-21.
 */
public class Util {

    public static int calTotalPage(int totalWeibo, int pageSize) {
        int totalPage = totalWeibo / pageSize;
        if ((totalPage * pageSize) != totalWeibo) {
            return totalPage + 1;
        }
        return totalPage;
    }
}
