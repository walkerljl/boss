package org.walkerljl.boss.model.enums.monitor;

/**
 * 监控任务类型
 *
 * @author: lijunlin
 */
public enum MonitorObjectType {

    /**
     * DNS switchwable http
     */
    DNS_SWITCHABLE_HTTP(1, "可切换DNS的HTTP"),

    /**
     * HTTP
     */
    HTTP(2, "HTTP");

    private int value;
    private String name;

    MonitorObjectType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static MonitorObjectType getType(int value) {
        if (value <= 0) {
            return null;
        }
        for (MonitorObjectType ele : MonitorObjectType.values()) {
            if (ele.getValue() == value) {
                return ele;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
