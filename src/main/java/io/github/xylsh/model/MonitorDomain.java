package io.github.xylsh.model;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 15-4-5.
 */
public class MonitorDomain {

    private static final Splitter andSpitter = Splitter.on("&");

    private Long id;

    @NotBlank(message = "weiboUid不能为空")
    //todo:@Length(max = 50, message = "培训名不能超过50")
    private String weiboUid;//weibo_uid;

    @NotBlank(message = "weiboNickname不能为空")
    private String weiboNickname;//weibo_nickname;

    //@NotBlank(message = "weiboUrl不能为空")
    private String weiboUrl;//weibo_url;可以为空

    private Date lastWeiboTime;//last_weibo_time;

    @NotNull(message = "isStop不能为空")
    private Boolean isStop;//is_stop,是否停止监控

    @NotBlank(message = "weiboInterfaceUrl不能为空")
    private String weiboInterfaceUrl;//weibo_interface_url;

    public MonitorDomain() {
    }

    public Long getId() {
        return id;
    }

    public MonitorDomain setId(Long id) {
        this.id = id;
        return this;
    }

    public String getWeiboNickname() {
        return weiboNickname;
    }

    public MonitorDomain setWeiboNickname(String weiboNickname) {
        this.weiboNickname = weiboNickname;
        return this;
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public MonitorDomain setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
        return this;
    }

    public String getWeiboInterfaceUrl() {
        return weiboInterfaceUrl;
    }

    public String getWeiboInterfaceUrl(int page) {
        checkArgument(StringUtils.isNotBlank(weiboInterfaceUrl));

        int questionIndex = weiboInterfaceUrl.indexOf("?");
        checkArgument(questionIndex >= 0);
        String args = weiboInterfaceUrl.substring(questionIndex + 1);

        List<String> argList = andSpitter.splitToList(args);
        String customPageUrl = "";
        for (String arg : argList) {
            if (arg.startsWith("page=")) {
                //todo:guava
                customPageUrl = weiboInterfaceUrl.replace(arg, "page=" + page);
            }
        }

        return customPageUrl;
    }

    public MonitorDomain setWeiboInterfaceUrl(String weiboInterfaceUrl) {
        this.weiboInterfaceUrl = weiboInterfaceUrl;
        return this;
    }

    public Date getLastWeiboTime() {
        return lastWeiboTime;
    }

    public MonitorDomain setLastWeiboTime(Date lastWeiboTime) {
        this.lastWeiboTime = lastWeiboTime;
        return this;
    }

    public String getWeiboUid() {
        return weiboUid;
    }

    public void setWeiboUid(String weiboUid) {
        this.weiboUid = weiboUid;
    }

    public Boolean getIsStop() {
        return isStop;
    }

    public void setIsStop(Boolean isStop) {
        this.isStop = isStop;
    }

    @Override
    public String toString() {
        return "MonitorDomain{" +
                "id=" + id +
                ", weiboUid='" + weiboUid + '\'' +
                ", weiboNickname='" + weiboNickname + '\'' +
                ", weiboUrl='" + weiboUrl + '\'' +
                ", lastWeiboTime=" + lastWeiboTime +
                ", isStop=" + isStop +
                ", weiboInterfaceUrl='" + weiboInterfaceUrl + '\'' +
                '}';
    }
}
