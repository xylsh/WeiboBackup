package io.github.xylsh.bean;

/**
 * Created by apple on 15-3-29.
 */
public class PicInfo {

    public static final String PIC_ID_FIELD = "pic_id";
    public static final String LARGEST_FIELD = "largest";

    private String picId;//pic_id
    private PicType largest;//largest

    public PicInfo() {
    }

    public PicInfo(String picId, PicType largest) {
        this.picId = picId;
        this.largest = largest;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public PicType getLargest() {
        return largest;
    }

    public void setLargest(PicType largest) {
        this.largest = largest;
    }

    @Override
    public String toString() {
        return "PicInfo{" +
                "picId='" + picId + '\'' +
                ", largest=" + largest +
                '}';
    }
}
