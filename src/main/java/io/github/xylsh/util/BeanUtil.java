package io.github.xylsh.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import io.github.xylsh.bean.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by apple on 15-3-30.
 */
public class BeanUtil {

    public static List<InternatResource> transform(List<Card> cardList) {
        List<InternatResource> downloadBeanList = Lists.newArrayList();

        for (Card card : cardList) {
            downloadBeanList.addAll(transform(card.getmBlog()));
        }

        return downloadBeanList;
    }

    private static List<InternatResource> transform(MBlog mBlog) {
        List<InternatResource> downloadBeanList = Lists.newArrayList();

        PicInfo[] picInfos = mBlog.getPicInfos();
        if (picInfos != null && picInfos.length > 0) {
            for(PicInfo picInfo:picInfos ){
                PicType largets = picInfo.getLargest();
                downloadBeanList.add(new InternatResource(largets));
            }

        } else if ( mBlog.getRetweetedStatus() != null) {
            downloadBeanList.addAll(transform(mBlog.getRetweetedStatus()));
        }

        return downloadBeanList;
    }

}
