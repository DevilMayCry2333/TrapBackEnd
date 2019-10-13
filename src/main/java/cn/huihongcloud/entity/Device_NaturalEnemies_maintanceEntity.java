package cn.huihongcloud.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;
@Data

public class Device_NaturalEnemies_maintanceEntity {
    @Excel(name = "记录ID")
    private Long id;
    @Excel(name = "设备ID")
    private Long deviceId;
    @Excel(name = "编号")
    private String serial;

    private String region;
    @Excel(name = "提交日期")
    private String submitDate;
    @Excel(name = "批次")
    private Integer batch;
    @Excel(name = "经度")
    private String longitude;
    @Excel(name = "纬度")
    private String latitude;
    @Excel(name = "天敌类型")
    private String predatorstype;
    @Excel(name = "释放数量")
    private Integer releaseNum;
    @Excel(name = "照片")
    private String pic;
    @Excel(name = "工人")
    private String worker;
    @Excel(name = "备注")
    private String remarks;

//    @Excel(name = "二维码ID")
    private Long scanid;
    @Excel(name = "管理员")
    private String username;
    @Excel(name = "是否报告")
    private Integer reported;
    @Excel(name = "海拔")
    private String altitude;


    private String province;

    private String city;

    private String area;

    private String town;

    private String precision;

    @Excel(name = "区域")
    private String customtown;



    public Device_NaturalEnemies_maintanceEntity(Long id, Long deviceId, String serial, String region, String submitDate, Integer batch, String longitude, String latitude, String predatorstype, Integer releaseNum, String pic, String worker, String remarks, Long scanid, String username,Integer reported,String altitude,String province,String city,String area,String town,String precision,String customtown) {
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
        this.altitude = altitude;
        this.province = province;
        this.city = city;
        this.area = area;
        this.town = town;
        this.precision = precision;
        this.customtown = customtown;
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

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
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

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
}