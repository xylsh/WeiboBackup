package io.github.xylsh.common;

import com.google.common.collect.Lists;
import io.github.xylsh.bean.Card;
import io.github.xylsh.util.FactoryUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by apple on 15-4-10.
 */
public class JSONToCardProcessor implements IProcessor<String, List<Card>> {

    private static final Logger logger = LoggerFactory.getLogger(JSONToCardProcessor.class);

    @Override
    public List<Card> process(String jsonStr) {
        List<Card> cardList = Lists.newArrayListWithCapacity(22);

        try {
            JSONArray cardJsonArray = new JSONObject(jsonStr).getJSONArray("cards");
            for (int i = 0; i < cardJsonArray.length(); i++) {
                JSONObject currCardJObject = cardJsonArray.getJSONObject(i);

                Card card = FactoryUtil.createCard(currCardJObject);
                if (card != null) {
                    cardList.add(card);
                }
            }
        } catch (JSONException e) {
            logger.error("解析json出错，json:{}", jsonStr, e);
        }

        return cardList;  //即使出错，也会返回一个没有元素的list
    }
}