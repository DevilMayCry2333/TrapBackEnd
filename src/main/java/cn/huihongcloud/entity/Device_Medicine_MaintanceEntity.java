package cn.huihongcloud.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Device_Medicine_MaintanceEntity {

    @Excel(name = "记录ID")
    private Long id;
    @Excel(name = "设备ID")
    private Long deviceId;
    @Excel(name = "编号")
    private String serial;
    @Excel(name = "所属区域")
    private String region;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    // @DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @Excel(name = "日期")
    private Date submitDate;
    @Excel(name = "批次")
    private Integer batch;
    @Excel(name = "经度")
    private String longitude;
    @Excel(name = "纬度")
    private String latitude;
    @Excel(name = "药剂质量")
    private String medicineQua;
    @Excel(name = "药剂名称")
    private String medicineName;
    @Excel(name = "防治面积")
    private Double areaFz;
    @Excel(name = "工作内容")
    private String workContent;
    @Excel(name = "工人")
    private String worker;
    @Excel(name = "备注")
    private String remarks;

    private String pic;

    private String customTown;

    private Byte reported;

    private String username;

    private Double medicineQuaSum;
    private Long scanId;
    private String town;
    private String dataPrecision;
    private Double areaFzSum;
    private String startDate;

    private String endDate;
    private Double totalMedicineQuaSum;
    private Double totalAreaFzNum;
    private String altitude;

    public Device_Medicine_MaintanceEntity(Long id, Long deviceId, String serial, String region, Date submitDate, Integer batch, String longitude, String latitude, String medicineQua, String medicineName, Double areaFz, String workContent, String worker, String remarks, String pic, String customTown, Byte reported, String username,String town,String altitude,Long scanId) {
        this.id = id;
        this.deviceId = deviceId;
        this.serial = serial;
        this.region = region;
        this.submitDate = submitDate;
        this.batch = batch;
        this.longitude = longitude;
        this.latitude = latitude;
        this.medicineQua = medicineQua;
        this.medicineName = medicineName;
        this.areaFz = areaFz;
        this.workContent = workContent;
        this.worker = worker;
        this.remarks = remarks;
        this.pic = pic;
        this.customTown = customTown;
        this.reported = reported;
        this.username = username;
        this.town = town;
        this.altitude = altitude;
        this.scanId = scanId;
    }

    public Device_Medicine_MaintanceEntity() {
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

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
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
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getMedicineQua() {
        return medicineQua;
    }

    public void setMedicineQua(String medicineQua) {
        this.medicineQua = medicineQua == null ? null : medicineQua.trim();
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName == null ? null : medicineName.trim();
    }

    public Double getAreaFz() {
        return areaFz;
    }

    public void setAreaFz(Double areaFz) {
        this.areaFz = areaFz;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent == null ? null : workContent.trim();
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getCustomTown() {
        return customTown;
    }

    public void setCustomTown(String customTown) {
        this.customTown = customTown == null ? null : customTown.trim();
    }

    public Byte getReported() {
        return reported;
    }

    public void setReported(Byte reported) {
        this.reported = reported;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}