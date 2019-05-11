package cn.huihongcloud.entity.device;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2018/7/18
 */
public class DeviceBeetle {
    private int id;
    private String deviceId;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    private Date changeDate;
    // 更换次数
    private int changeTimes;
    private int beetleNum;
    // 样株死亡情况
    private boolean sampleAlive;
    // 样株10米、10-150米半径范围死亡松树数量
    private int pineDeathNum;
    // 诱捕器是否疫点小班
    private boolean epidemicArea;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public int getChangeTimes() {
        return changeTimes;
    }

    public void setChangeTimes(int changeTimes) {
        this.changeTimes = changeTimes;
    }

    public int getBeetleNum() {
        return beetleNum;
    }

    public void setBeetleNum(int beetleNum) {
        this.beetleNum = beetleNum;
    }

    public boolean isSampleAlive() {
        return sampleAlive;
    }

    public void setSampleAlive(boolean sampleAlive) {
        this.sampleAlive = sampleAlive;
    }

    public int getPineDeathNum() {
        return pineDeathNum;
    }

    public void setPineDeathNum(int pineDeathNum) {
        this.pineDeathNum = pineDeathNum;
    }

    public boolean isEpidemicArea() {
        return epidemicArea;
    }

    public void setEpidemicArea(boolean epidemicArea) {
        this.epidemicArea = epidemicArea;
    }
}
