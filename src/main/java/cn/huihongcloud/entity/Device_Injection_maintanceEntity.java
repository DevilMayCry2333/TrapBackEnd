package cn.huihongcloud.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Device_Injection_maintanceEntity {
    @Excel(name = "记录ID")
    private Long id;
    @Excel(name = "设备ID")
    private Long deviceId;
    @Excel(name = "编号")
    private String serial;


    private String region;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @Excel(name = "提交日期")
    private String submitDate;
    @Excel(name = "批次")
    private Integer batch;
    @Excel(name = "经度")
    private String longitude;
    @Excel(name = "纬度")
    private String latitude;
    @Excel(name = "工作内容")
    private String workContent;
    @Excel(name = "注剂数量")
    private Integer injectionNum;



    private String pic;

    @Excel(name = "工作人员")
    private String worker;

    @Excel(name = "备注")
    private String remarks;

    private Long scanid;

    private String username;
    @Excel(name = "海拔")
    private String altitude;

    private String dataPrecision;

    private String province;

    private String city;

    private String area;

    private String town;

    @Excel(name = "是否报告")
    private Integer reported;

    @Excel(name = "树木状态")
    private Integer woodstatus;

//    @Excel(name = "树木数量")
    private String woodNum;

    @Excel(name = "区域")
    private String customtown;

    private Integer injectNumSum;

    private Integer WoodStatusSum;

    private String startDate;

    private String endDate;

    private Integer totalInjectSum;

    private Integer totalWoodSum;

    private Integer totalDeadSum;

    private Double deadRate;

    public void setReported(Integer reported) {
        this.reported = reported;
    }

    public Double getChestDiameter() {
        return chestDiameter;
    }

    public void setChestDiameter(Double chestDiameter) {
        this.chestDiameter = chestDiameter;
    }



    @Excel(name = "胸径")
    private Double chestDiameter;

    public String getInjectName() {
        return injectName;
    }

    public void setInjectName(String injectName) {
        this.injectName = injectName;
    }

    @Excel(name = "注剂类型")
    private String injectName;

    private String woodStatusName;

    public String getWoodStatusName() {
        return woodStatusName;
    }

    public void setWoodStatusName(String woodStatusName) {
        this.woodStatusName = woodStatusName;
    }

    public Double getDeadRate() {
        return deadRate;
    }

    public void setDeadRate(Double deadRate) {
        this.deadRate = deadRate;
    }

    public Device_Injection_maintanceEntity(String altitude, String dataPrecision, Long id, Long deviceId, String serial, String region, String submitDate, Integer batch, String longitude, String latitude,
                                            String workContent, Integer injectionNum, String pic, String worker, String remarks,
                                            Long scanid, String username, String province, String city, String area, String town, Integer reported, String woodNum, String customtown, Integer injectNumSum,
                                            Integer woodstatus, Integer WoodStatusSum, Integer totalInjectSum, Integer totalWoodSum, Integer totalDeadSum, Double chestDiameter, String injectName, Double deadRate,String woodStatusName) {

        this.woodstatus = woodstatus;
        this.chestDiameter = chestDiameter;
        this.injectName = injectName;
        this.id = id;
        this.submitDate = submitDate;
        this.deviceId = deviceId;
        this.serial = serial;
        this.region = region;
        this.batch = batch;
        this.longitude = longitude;
        this.latitude = latitude;
        this.workContent = workContent;
        this.injectionNum = injectionNum;
        this.pic = pic;
        this.worker = worker;
        this.remarks = remarks;
        this.scanid = scanid;
        this.username = username;
        this.altitude = altitude;
        this.dataPrecision = dataPrecision;
        this.province = province;
        this.city = city;
        this.area = area;
        this.town = town;
        this.reported = reported;
        this.woodNum = woodNum;
        this.customtown = customtown;
        this.injectNumSum = injectNumSum;
        this.WoodStatusSum = WoodStatusSum;
        this.totalInjectSum = totalInjectSum;
        this.totalWoodSum = totalWoodSum;
        this.totalDeadSum = totalDeadSum;
        this.deadRate = deadRate;
        this.woodStatusName = woodStatusName;
    }

    public Device_Injection_maintanceEntity() {
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

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent == null ? null : workContent.trim();
    }

    public Integer getInjectionNum() {
        return injectionNum;
    }

    public void setInjectionNum(Integer injectionNum) {
        this.injectionNum = injectionNum;
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

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getDataPrecision() {
        return dataPrecision;
    }

    public void setDataPrecision(String dataPrecision) {
        this.dataPrecision = dataPrecision;
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

    public int getReported() {
        return reported;
    }

    public void setReported(int reported) {
        this.reported = reported;
    }

    public String getWoodNum() {
        return woodNum;
    }

    public void setWoodNum(String woodNum) {
        this.woodNum = woodNum;
    }

    public Integer getInjectNumSum() {
        return injectNumSum;
    }

    public void setInjectNumSum(Integer injectNumSum) {
        this.injectNumSum = injectNumSum;
    }


    public Integer getWoodStatusSum() {
        return WoodStatusSum;
    }

    public void setWoodStatusSum(Integer woodStatusSum) {
        WoodStatusSum = woodStatusSum;
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

    public Integer getWoodstatus() {
        return woodstatus;
    }

    public void setWoodstatus(Integer woodstatus) {
        this.woodstatus = woodstatus;
    }

    public String getCustomtown() {
        return customtown;
    }

    public void setCustomtown(String customtown) {
        this.customtown = customtown;
    }

    public Integer getTotalInjectSum() {
        return totalInjectSum;
    }

    public void setTotalInjectSum(Integer totalInjectSum) {
        this.totalInjectSum = totalInjectSum;
    }

    public Integer getTotalWoodSum() {
        return totalWoodSum;
    }

    public void setTotalWoodSum(Integer totalWoodSum) {
        this.totalWoodSum = totalWoodSum;
    }

    public Integer getTotalDeadSum() {
        return totalDeadSum;
    }

    public void setTotalDeadSum(Integer totalDeadSum) {
        this.totalDeadSum = totalDeadSum;
    }
}