package cn.huihongcloud.entity.summary;

import lombok.Data;

/**
 * Created by 钟晖宏 on 2019/1/18
 */
@Data
public class SummaryEntity {
    private Long deviceCount;
    private Long beetleCount;
    private String name;
    private String code;

    public Long getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Long getBeetleCount() {
        return beetleCount;
    }

    public void setBeetleCount(Long beetleCount) {
        this.beetleCount = beetleCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
