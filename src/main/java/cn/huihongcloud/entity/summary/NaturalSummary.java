package cn.huihongcloud.entity.summary;

import lombok.Data;

@Data
public class NaturalSummary {
    private Long deviceCount;
    private Long releaseNum;
    private String name;
    private String code;

    public Long getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Long getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(Long releaseNum) {
        this.releaseNum = releaseNum;
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
