package cn.huihongcloud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Device_Natural_Detail {
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

    private String altitude;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date receiveDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date checkDate;
    private String adcode;
    private String towncode;
    private String province;
    private String city;
    private String area;
    private String town;
    private String qrcode;
    private String manager;

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
        this.serial = serial;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
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
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPredatorstype() {
        return predatorstype;
    }

    public void setPredatorstype(String predatorstype) {
        this.predatorstype = predatorstype;
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
        this.pic = pic;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Integer getReported() {
        return reported;
    }

    public void setReported(Integer reported) {
        this.reported = reported;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getTowncode() {
        return towncode;
    }

    public void setTowncode(String towncode) {
        this.towncode = towncode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
