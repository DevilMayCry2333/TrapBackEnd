package cn.huihongcloud.entity;

import java.util.Date;

public class Device_DeadTrees_maintanceEntity {
    private Long id;

    private Long deviceId;

    private String serial;

    private Date submitDate;

    private String batch;

    private String longitude;

    private String latitude;

    private String wooddiameter;

    private String woodheight;

    private String woodvolume;

    private String killmethod;

    private String worker;

    private String remarks;

    private Long scanid;

    private String pic;

    public Device_DeadTrees_maintanceEntity(Long id, Long deviceId, String serial, Date submitDate, String batch, String longitude, String latitude, String wooddiameter, String woodheight, String woodvolume, String killmethod, String worker, String remarks, Long scanid, String pic) {
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

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
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
}