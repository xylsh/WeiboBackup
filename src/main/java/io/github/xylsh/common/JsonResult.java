package io.github.xylsh.common;

/**
 * Created by apple on 15-4-13.
 */
public class JsonResult<T> {
    private boolean ret;
    private String errmsg;
    private T data;

    public JsonResult() {
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
