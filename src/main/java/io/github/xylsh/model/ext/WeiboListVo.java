package io.github.xylsh.model.ext;

import java.util.List;

/**
 * Created by apple on 15-4-21.
 */
public class WeiboListVo {

    /**
     * 总微博数
     */
    private int totalWeibo;
    private int totalPage;
    private int currPage;
    private int pageSize;
    private List<WeiboVo> weiboList;

    public WeiboListVo() {
    }

    public WeiboListVo(List<WeiboVo> weiboList) {
        this.weiboList = weiboList;
    }

    public int getTotalWeibo() {
        return totalWeibo;
    }

    public void setTotalWeibo(int totalWeibo) {
        this.totalWeibo = totalWeibo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<WeiboVo> getWeiboList() {
        return weiboList;
    }

    public void setWeiboList(List<WeiboVo> weiboList) {
        this.weiboList = weiboList;
    }
}
