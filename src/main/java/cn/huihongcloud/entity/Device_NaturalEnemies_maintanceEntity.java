package cn.huihongcloud.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Device_NaturalEnemies_maintanceEntity {

    private Long id;
    @Excel(name = "设备ID")
    private Long deviceId;
    @Excel(name = "编号")
    private String serial;
    @Excel(name = "区域")
    private String customTown;

    private String region;

    @Excel(name = "日期",exportFormat = "yyyy年MM月dd日",importFormat = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    private Date submitDate;


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

    private String luanKaNumSum;

    private String releaseNumSum;


    private String natualMannerTotal;
    private String natualMannerOne;
    private String natualMannerTwo;
    private String natualMannerThree;

    private Integer deviceNum;

    @Excel(name = "用户名")
    private String username;
    private Integer reported;
    private String altitude;


    private String startDate;
    private String endDate;

    private String province;

    private String city;

    private String area;
    @Excel(name = "乡镇")
    private String town;

    private String precision;

    private String adcode;

    private String customSerial;

    private String customProject;


    public String getNatualMannerTotal() {
        return natualMannerTotal;
    }

    public void setNatualMannerTotal(String natualMannerTotal) {
        this.natualMannerTotal = natualMannerTotal;
    }

    public String getNatualMannerOne() {
        return natualMannerOne;
    }

    public void setNatualMannerOne(String natualMannerOne) {
        this.natualMannerOne = natualMannerOne;
    }

    public String getNatualMannerTwo() {
        return natualMannerTwo;
    }

    public void setNatualMannerTwo(String natualMannerTwo) {
        this.natualMannerTwo = natualMannerTwo;
    }

    public String getNatualMannerThree() {
        return natualMannerThree;
    }

    public void setNatualMannerThree(String natualMannerThree) {
        this.natualMannerThree = natualMannerThree;
    }

    private Integer releasePlace;

    public Integer getTotalReleasePlace() {
        return totalReleasePlace;
    }

    public void setTotalReleasePlace(Integer totalReleasePlace) {
        this.totalReleasePlace = totalReleasePlace;
    }

    private Integer totalReleasePlace;
    public String getCustomTown() {
        return customTown;
    }

    public void setCustomTown(String customTown) {
        this.customTown = customTown;
    }

    public Integer getReleasePlace() {
        return releasePlace;
    }

    public void setReleasePlace(Integer releasePlace) {
        this.releasePlace = releasePlace;
    }

    //    @Excel(name = "二维码ID")
    private Long scanId;

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









    public Device_NaturalEnemies_maintanceEntity(Long id, Long deviceId, String serial, String region, Date submitDate, Integer batch, String longitude, String latitude, String predatorstype, Integer releaseNum, String pic, String worker, String remarks, Long scanId, String username,Integer reported,String altitude,String province,String city,String area,String town,String precision) {
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
        this.scanId = scanId;
        this.username = username;
        this.reported = reported;
        this.altitude = altitude;
        this.province = province;
        this.city = city;
        this.area = area;
        this.town = town;
        this.precision = precision;
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

    public Long getScanId() {
        return scanId;
    }

    public void setScanId(Long scanId) {
        this.scanId = scanId;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getLuanKaNumSum() {
        return luanKaNumSum;
    }

    public void setLuanKaNumSum(String luanKaNumSum) {
        this.luanKaNumSum = luanKaNumSum;
    }

    public String getReleaseNumSum() {
        return releaseNumSum;
    }

    public void setReleaseNumSum(String releaseNumSum) {
        this.releaseNumSum = releaseNumSum;
    }

    public Integer getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(Integer deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getCustomSerial() {
        return customSerial;
    }

    public void setCustomSerial(String customSerial) {
        this.customSerial = customSerial;
    }

    public String getCustomProject() {
        return customProject;
    }

    public void setCustomProject(String customProject) {
        this.customProject = customProject;
    }
}