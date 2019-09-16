package cn.huihongcloud.entity;

public class Device_Injection_maintanceEntity {
    private Long id;

    private Long deviceId;

    private String serial;

    private String region;

    private String submitDate;

    private Integer batch;

    private String longitude;

    private String latitude;

    private String workContent;

    private Integer injectionNum;

    private String woodstatus;

    private String pic;

    private String worker;

    private String remarks;

    private Long scanid;

    private String username;

    private String altitude;

    private String dataPrecision;

    private String province;

    private String city;

    private String area;

    private String town;

    public Device_Injection_maintanceEntity(String altitude, String dataPrecision, Long id, Long deviceId, String serial, String region, String submitDate, Integer batch, String longitude, String latitude, String workContent, Integer injectionNum, String woodstatus, String pic, String worker, String remarks, Long scanid, String username,String province,String city,String area,String town) {
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
        this.woodstatus = woodstatus;
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

    public String getWoodstatus() {
        return woodstatus;
    }

    public void setWoodstatus(String woodstatus) {
        this.woodstatus = woodstatus == null ? null : woodstatus.trim();
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
}