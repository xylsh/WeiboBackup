package io.github.xylsh.model.ext;

import io.github.xylsh.model.WeiboDomain;

/**
 * Created by apple on 15-4-15.
 */
public class WeiboVo {

    private String mainWeiboUid;
    private WeiboDomain mainWeibo;
    private WeiboDomain retweetWeibo;

    public WeiboVo() {
    }

    public WeiboVo(String mainWeiboUid) {
        this.mainWeiboUid = mainWeiboUid;
    }

    public String getMainWeiboUid() {
        return mainWeiboUid;
    }

    public void setMainWeiboUid(String mainWeiboUid) {
        this.mainWeiboUid = mainWeiboUid;
    }

    public WeiboDomain getMainWeibo() {
        return mainWeibo;
    }

    public void setMainWeibo(WeiboDomain mainWeibo) {
        this.mainWeibo = mainWeibo;
    }

    public WeiboDomain getRetweetWeibo() {
        return retweetWeibo;
    }

    public void setRetweetWeibo(WeiboDomain retweetWeibo) {
        this.retweetWeibo = retweetWeibo;
    }

    @Override
    public String toString() {
        return "WeiboVo{" +
                "mainWeibo=" + mainWeibo +
                ", retweetWeibo=" + retweetWeibo +
                '}';
    }
}
