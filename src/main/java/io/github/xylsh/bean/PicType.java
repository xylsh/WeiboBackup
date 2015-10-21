package io.github.xylsh.bean;

/**
 * Created by apple on 15-3-29.
 */
public class PicType {

    public static final String URL_FIELD = "url";
    public static final String TYPE_FIELD = "type";

    private String url;
    private String type;//"JPEG"

    public PicType() {
    }

    public PicType(String url, String type) {
        this.url = url;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PicType{" +
                "url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
