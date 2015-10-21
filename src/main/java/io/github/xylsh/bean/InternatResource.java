package io.github.xylsh.bean;

/**
 * Created by apple on 15-3-30.
 */
public class InternatResource {

    private String fileName;//下载后要保存的文件名
    private String url;//下载链接

    public InternatResource() {
    }

    public InternatResource(PicType picType) {
        url = picType.getUrl();
        fileName = url.substring(url.lastIndexOf("/") + 1);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "InternatResource{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
