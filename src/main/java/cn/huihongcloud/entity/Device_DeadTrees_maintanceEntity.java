package cn.huihongcloud.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;
@Data
public class Device_DeadTrees_maintanceEntity {
    @Excel(name = "记录ID")
    private Long id;
    @Excel(name = "设备ID")
    private Long deviceId;
    @Excel(name = "序列号")
    private String serial;
    @Excel(name = "提交日期")
    private String submitDate;
    @Excel(name = "批次")
    private String batch;
    @Excel(name = "经度")
    private String longitude;
    @Excel(name = "纬度")
    private String latitude;
    @Excel(name = "树木直径")
    private String wooddiameter;
    @Excel(name = "树木高度")
    private String woodheight;
    @Excel(name = "树木材积")
    private String woodvolume;
    @Excel(name = "除害方式")
    private String killmethod;
    @Excel(name = "工人")
    private String worker;
    @Excel(name = "备注")
    private String remarks;
    @Excel(name = "二维码ID")
    private Long scanid;
    @Excel(name = "图片")
    private String pic;
    @Excel(name = "用户名")
    private String username;
    @Excel(name = "区域")
    private String region;
    @Excel(name = "海拔")
    private String altitude;
    @Excel(name = "精度")
    private String accuracy;

    public Device_DeadTrees_maintanceEntity(Long id, Long deviceId, String serial, String submitDate, String batch, String longitude, String latitude, String wooddiameter, String woodheight, String woodvolume, String killmethod, String worker, String remarks, Long scanid, String pic, String username,String region,String altitude,String accuracy) {
        this.id = id;
        this.deviceId = deviceId;
        this.serial = serial;
        this.submitDate = submitDate;
        this.batch = batch;
        this.longitude = longitude;
        this.latitude = latitude;
        this.wooddiameter = wooddiameter;
        this.woodheight = woodheight;
        this.woodvolume = woodvolume;
        this.killmethod = killmethod;
        this.worker = worker;
        this.remarks = remarks;
        this.scanid = scanid;
        this.pic = pic;
        this.username = username;
        this.region = region;
        this.altitude = altitude;
        this.accuracy = accuracy;
    }

    public Device_DeadTrees_maintanceEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial == null ? null : serial.trim();
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getWooddiameter() {
        return wooddiameter;
    }

    public void setWooddiameter(String wooddiameter) {
        this.wooddiameter = wooddiameter == null ? null : wooddiameter.trim();
    }

    public String getWoodheight() {
        return woodheight;
    }

    public void setWoodheight(String woodheight) {
        this.woodheight = woodheight == null ? null : woodheight.trim();
    }

    public String getWoodvolume() {
        return woodvolume;
    }

    public void setWoodvolume(String woodvolume) {
        this.woodvolume = woodvolume == null ? null : woodvolume.trim();
    }

    public String getKillmethod() {
        return killmethod;
    }

    public void setKillmethod(String killmethod) {
        this.killmethod = killmethod == null ? null : killmethod.trim();
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker == null ? null : worker.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Long getScanid() {
        return scanid;
    }

    public void setScanid(Long scanid) {
        this.scanid = scanid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }
}