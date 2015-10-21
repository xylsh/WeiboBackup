package io.github.xylsh.model;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import io.github.xylsh.bean.MBlog;
import io.github.xylsh.bean.PicInfo;
import io.github.xylsh.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by apple on 15-4-8.
 */
public class WeiboDomain {

    private static final Splitter COMMA_SPLITTER = Splitter.on(WeiboDomain.PICSTR_SEPERATOR).trimResults().omitEmptyStrings();
    private static final Splitter DOT_SPLITTER = Splitter.on(WeiboDomain.PIC_PATH_SEPERATOR).trimResults().omitEmptyStrings();

    public static final String PICSTR_SEPERATOR = ",";
    public static final String PIC_PATH_SEPERATOR = ".";

    private Long id;
    private String weiboNickname;
    private String weiboText;
    private Date weiboTime;
    private String weiboPics;
    private Long retweetWeiboId;

    public WeiboDomain() {
    }

    public WeiboDomain(MBlog mBlog) {
        checkNotNull(mBlog);

        weiboNickname = mBlog.getUser().getScreenName();
        weiboText = mBlog.getText();
        weiboTime = mBlog.getCreatedAt();

        PicInfo[] picInfos = mBlog.getPicInfos();
        if (picInfos != null && picInfos.length > 0) {
            String uid = mBlog.getUser().getId();

            StringBuilder sb = new StringBuilder();
            for (PicInfo picInfo : picInfos) {
                sb.append(uid + PIC_PATH_SEPERATOR + StringUtil.resourceNameFromUrl(picInfo.getLargest().getUrl()));
                sb.append(PICSTR_SEPERATOR);
            }

            weiboPics = sb.toString();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeiboNickname() {
        return weiboNickname;
    }

    public void setWeiboNickname(String weiboNickname) {
        this.weiboNickname = weiboNickname;
    }

    public String getWeiboText() {
        return weiboText;
    }

    public void setWeiboText(String weiboText) {
        this.weiboText = weiboText;
    }

    public Date getWeiboTime() {
        return weiboTime;
    }

    public void setWeiboTime(Date weiboTime) {
        this.weiboTime = weiboTime;
    }

    public String getWeiboPics() {
        return weiboPics;
    }

    public void setWeiboPics(String weiboPics) {
        this.weiboPics = weiboPics;
    }

    public String[] getParsedWeiboPics() {
        if (StringUtils.isBlank(weiboPics)) {
            return new String[0];
        }

        List<String> picList = COMMA_SPLITTER.splitToList(weiboPics);
        List<String> picUrls = Lists.newArrayListWithCapacity(picList.size());
        for (String pic : picList) {
            picUrls.add(pic.replaceFirst("[.]","/"));  //todo:guava?
        }

        return picUrls.toArray(new String[0]);
    }

    public Long getRetweetWeiboId() {
        return retweetWeiboId;
    }

    public void setRetweetWeiboId(Long retweetWeiboId) {
        this.retweetWeiboId = retweetWeiboId;
    }

    @Override
    public String toString() {
        return "WeiboDomain{" +
                "id=" + id +
                ", weiboNickname='" + weiboNickname + '\'' +
                ", weiboText='" + weiboText + '\'' +
                ", weiboTime=" + weiboTime +
                ", weiboPics='" + weiboPics + '\'' +
                ", retweetWeiboId=" + retweetWeiboId +
                '}';
    }
}
