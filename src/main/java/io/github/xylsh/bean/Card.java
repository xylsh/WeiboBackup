package io.github.xylsh.bean;

/**
 * Created by apple on 15-3-29.
 */
public class Card {

    public static final String CARD_TYPE_FIELD = "card_type";
    public static final String MBlog_FIELD = "mblog";

    private Integer cardType;//card_type，9
    private MBlog mBlog;//mblog

    public Card() {
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public MBlog getmBlog() {
        return mBlog;
    }

    public void setmBlog(MBlog mBlog) {
        this.mBlog = mBlog;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardType=" + cardType +
                ", mBlog=" + mBlog +
                '}';
    }
}
