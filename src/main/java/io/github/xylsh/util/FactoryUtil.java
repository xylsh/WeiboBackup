package io.github.xylsh.util;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import io.github.xylsh.bean.*;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 15-4-6.
 */
public class FactoryUtil {

    private static final Logger logger = LoggerFactory.getLogger(FactoryUtil.class);
    private static final User NO_USER = new User();

    static {
        NO_USER.setId("123456789");
        NO_USER.setScreenName("无用户名");
    }

    //todo:如果以后格式变了怎么办？
    //出错返回null,而不是抛出异常，这样如果某个card非法，不会影响后面card的解析
    public static Card createCard(JSONObject cardJObject) {
        Card card = new Card();

        try {
            card.setCardType(cardJObject.getInt(Card.CARD_TYPE_FIELD));

            //只要cardtype==9的card,todo:改成用filter
            if (card.getCardType() == null || card.getCardType() != 9) {
                return null;
            }

            card.setmBlog(createMBlog(cardJObject.getJSONObject(Card.MBlog_FIELD)));
        } catch (Exception e) {
            logger.error("用json构造对象出错。", e);
            return null;
        }

        return card;
    }

    private static MBlog createMBlog(JSONObject jsonObject) {
        MBlog mBlog = new MBlog();

        mBlog.setId(jsonObject.getString(MBlog.ID_FIELD));
        mBlog.setText(jsonObject.getString(MBlog.TEXT_FIELD));
        mBlog.setCreatedAt(StringUtil.toDate(jsonObject.getString(MBlog.CREATEDAT_FIELD)));

        try {
            //被删除微博无此字段,所以放到了try里
            mBlog.setUser(createUser(jsonObject.getJSONObject(MBlog.USER_FIELD)));
        } catch (JSONException jsonException) {
            mBlog.setUser(NO_USER);   //给一个默认'无user'
        }

        try {
            mBlog.setRetweetedStatus(createMBlog(jsonObject.getJSONObject(MBlog.RETWEETED_STATUS_FIELD)));
        } catch (JSONException jsonException) {
            mBlog.setRetweetedStatus(null);  //该条微博无转推
        }

        //微博图片
        try {
            JSONObject picinfosJO = jsonObject.getJSONObject(MBlog.PIC_INFOS_FIELD);
            JSONArray picidsJA = jsonObject.getJSONArray(MBlog.PIC_IDS_FIELD);
            if (picinfosJO != null && picidsJA != null) {   //如果这条微博有图片
                //todo:处理不相等时的情况
                checkState(picinfosJO.length() == picidsJA.length());

                PicInfo[] picInfos = new PicInfo[picidsJA.length()];

                for (int i = 0; i < picidsJA.length(); i++) {
                    String currPicId = picidsJA.getString(i);

                    picInfos[i] = createPicInfo(picinfosJO, currPicId);
                }

                mBlog.setPicInfos(picInfos);
            }
        } catch (JSONException jsonException) {
            //该条微博无图片
            mBlog.setPicInfos(null);
        }

        return mBlog;
    }


    private static User createUser(JSONObject jsonObject) {
        User user = new User();

        user.setId(jsonObject.getString(User.ID_FIELD));
        user.setScreenName(jsonObject.getString(User.SCREEN_NAME_FIELD));

        return user;
    }

    private static PicInfo createPicInfo(JSONObject picinfosJO, String currPicId) {
        PicInfo picInfo = new PicInfo();

        JSONObject picJO = picinfosJO.getJSONObject(currPicId);
        picInfo.setPicId(picJO.getString(PicInfo.PIC_ID_FIELD));
        picInfo.setLargest(createPicType(picJO.getJSONObject(PicInfo.LARGEST_FIELD)));

        return picInfo;
    }

    private static PicType createPicType(JSONObject jsonObject) {
        PicType picType = new PicType();

        picType.setUrl(jsonObject.getString(PicType.URL_FIELD));
        picType.setType(jsonObject.getString(PicType.TYPE_FIELD));

        return picType;
    }

}
