package cn.huihongcloud.entity.summary;

import lombok.Data;

@Data
public class DeadTreesSummary {
    private String woodVolume;
    private String woodNum;
    private String name;
    private String code;

    public String getWoodVolume() {
        return woodVolume;
    }

    public void setWoodVolume(String woodVolume) {
        this.woodVolume = woodVolume;
    }

    public String getWoodNum() {
        return woodNum;
    }

    public void setWoodNum(String woodNum) {
        this.woodNum = woodNum;
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
