package io.github.xylsh.model;

/**
 * Created by apple on 15-4-8.
 */
public class MonitorWeiboDomain {

    private Long id;
    private Long monitorId;
    private Long weiboId;

    public MonitorWeiboDomain() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }

    public Long getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Long weiboId) {
        this.weiboId = weiboId;
    }

    @Override
    public String toString() {
        return "MonitorWeiboDomain{" +
                "id=" + id +
                ", monitorId=" + monitorId +
                ", weiboId=" + weiboId +
                '}';
    }
}
