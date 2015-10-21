package io.github.xylsh.bean;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by apple on 15-3-29.
 */
public class MBlog {

    public static final String ID_FIELD = "idstr";
    public static final String TEXT_FIELD = "text";
    public static final String CREATEDAT_FIELD = "created_at";
    public static final String PIC_INFOS_FIELD = "pic_infos";
    public static final String PIC_IDS_FIELD = "pic_ids";
    public static final String USER_FIELD = "user";
    public static final String RETWEETED_STATUS_FIELD = "retweeted_status";


    private String id;//idstr
    private String text; //微博内容
    private Date createdAt;//created_at;
    private PicInfo[] picInfos; //pic_infos,可能为null
    private User user;
    private MBlog retweetedStatus;//retweeted_status

    public MBlog() {
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PicInfo[] getPicInfos() {
        return picInfos;
    }

    public void setPicInfos(PicInfo[] picInfos) {
        this.picInfos = picInfos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MBlog getRetweetedStatus() {
        return retweetedStatus;
    }

    public void setRetweetedStatus(MBlog retweetedStatus) {
        this.retweetedStatus = retweetedStatus;
    }

    @Override
    public String toString() {
        return "MBlog{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", picInfos=" + Arrays.toString(picInfos) +
                ", user=" + user +
                ", retweetedStatus=" + retweetedStatus +
                '}';
    }
}
