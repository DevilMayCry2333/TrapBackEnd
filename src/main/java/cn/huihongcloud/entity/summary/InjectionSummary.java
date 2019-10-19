package cn.huihongcloud.entity.summary;

import lombok.Data;

@Data
public class InjectionSummary {
    private Long deviceCount;
    private Long injectNum;
    private String name;
    private String code;

    public Long getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Long getInjectNum() {
        return injectNum;
    }

    public void setInjectNum(Long injectNum) {
        this.injectNum = injectNum;
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
