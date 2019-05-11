package cn.huihongcloud.entity.device;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2018/9/24
 */
public class DeviceMaintenanceForChart {

    @Excel(name = "ID")
    private int id;
    @Excel(name = "设备ID")
    private String deviceId;
    @Excel(name = "批次")
    private int batch;
    @Excel(name = "虫子数")
    private int num;
    @Excel(name = "雄虫数")
    private int maleNum;
    @Excel(name = "雌虫数")
    private int femaleNum;
    @Excel(name = "用户名")
    private String username;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    @DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    @Excel(name = "日期")
    private Date date;
    @Excel(name = "经度")
    private Double longitude;
    @Excel(name = "纬度")
    private Double latitude;
    @Excel(name = "海拔")
    private Double altitude;
    private String imageId;
    @Excel(name = "药剂类型")
    private String drug;
    @Excel(name = "备注")
    private String remark;

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

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMaleNum() {
        return maleNum;
    }

    public void setMaleNum(int maleNum) {
        this.maleNum = maleNum;
    }

    public int getFemaleNum() {
        return femaleNum;
    }

    public void setFemaleNum(int femaleNum) {
        this.femaleNum = femaleNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
