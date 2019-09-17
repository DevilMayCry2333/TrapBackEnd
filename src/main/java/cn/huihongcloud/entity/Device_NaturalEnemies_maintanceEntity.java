package cn.huihongcloud.entity;

import java.util.Date;

public class Device_NaturalEnemies_maintanceEntity {
    private Long id;

    private Long deviceId;

    private String serial;

    private String region;

    private String submitDate;

    private Integer batch;

    private String longitude;

    private String latitude;

    private String predatorstype;

    private Integer releaseNum;

    private String pic;

    private String worker;

    private String remarks;

    private Long scanid;

    private String username;

    private Integer reported;

    public Device_NaturalEnemies_maintanceEntity(Long id, Long deviceId, String serial, String region, String submitDate, Integer batch, String longitude, String latitude, String predatorstype, Integer releaseNum, String pic, String worker, String remarks, Long scanid, String username,Integer reported) {
        this.id = id;
        this.deviceId = deviceId;
        this.serial = serial;
        this.region = region;
        this.submitDate = submitDate;
        this.batch = batch;
        this.longitude = longitude;
        this.latitude = latitude;
        this.predatorstype = predatorstype;
        this.releaseNum = releaseNum;
        this.pic = pic;
        this.worker = worker;
        this.remarks = remarks;
        this.scanid = scanid;
        this.username = username;
        this.reported = reported;
    }

    public Device_NaturalEnemies_maintanceEntity() {
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
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

    public String getPredatorstype() {
        return predatorstype;
    }

    public void setPredatorstype(String predatorstype) {
        this.predatorstype = predatorstype == null ? null : predatorstype.trim();
    }

    public Integer getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(Integer releaseNum) {
        this.releaseNum = releaseNum;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public Integer getReported() {
        return reported;
    }

    public void setReported(Integer reported) {
        this.reported = reported;
    }
}