package io.github.xylsh.bean;

/**
 * Created by apple on 15-3-30.
 */
public class User {

    public static final String ID_FIELD = "idstr";
    public static final String SCREEN_NAME_FIELD = "screen_name";

    private String id;//idstr
    private String screenName;//screen_name

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
