package cn.huihongcloud.entity;

import io.swagger.models.auth.In;

public class AreaStatic {
    private Integer beetlesNum;
    private Integer otherNum;
    private Integer avager;
    private Integer avager1;
    private String CustomTown;
    private Integer avagerother;

    public Integer getAvager1() {
        return avager1;
    }

    public void setAvager1(Integer avager1) {
        this.avager1 = avager1;
    }

    public Integer getAvagerother() {
        return avagerother;
    }

    public void setAvagerother(Integer avagerother) {
        this.avagerother = avagerother;
    }

    public Integer getBeetlesNum() {
        return beetlesNum;
    }

    public void setBeetlesNum(Integer beetlesNum) {
        this.beetlesNum = beetlesNum;
    }

    public Integer getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(Integer otherNum) {
        this.otherNum = otherNum;
    }

    public Integer getAvager() {
        return avager;
    }

    public void setAvager(Integer avager) {
        this.avager = avager;
    }

    public String getCustomTown() {
        return CustomTown;
    }

    public void setCustomTown(String customTown) {
        CustomTown = customTown;
    }
}
