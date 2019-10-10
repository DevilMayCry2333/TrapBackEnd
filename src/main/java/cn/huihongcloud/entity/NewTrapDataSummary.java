package cn.huihongcloud.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class NewTrapDataSummary {
    private String RecordByCol;
    @Excel(name = "开始日期")
    private String startDate;
    @Excel(name = "结束日期")
    private String endDate;
    @Excel(name = "列名")
    private String colName;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "设备数量")
    private int deviceNum;
    @Excel(name = "天牛数量")
    private int beetleNum;
    @Excel(name = "其他天牛数量")
    private int otherNum;

    private int totalNum;

    public String getRecordByCol() {
        return RecordByCol;
    }

    public void setRecordByCol(String recordByCol) {
        RecordByCol = recordByCol;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(int deviceNum) {
        this.deviceNum = deviceNum;
    }

    public int getBeetleNum() {
        return beetleNum;
    }

    public void setBeetleNum(int beetleNum) {
        this.beetleNum = beetleNum;
    }

    public int getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(int otherNum) {
        this.otherNum = otherNum;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
