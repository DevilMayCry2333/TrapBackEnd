package cn.huihongcloud.entity.summary;

import lombok.Data;

@Data
public class MedicineSummary {
    private Long deviceCount;
    private String name;
    private String code;

    public Long getDeviceCount() {
        return deviceCount;
    }

    public Double getMedicineQuaSum() {
        return medicineQuaSum;
    }

    public void setMedicineQuaSum(Double medicineQuaSum) {
        this.medicineQuaSum = medicineQuaSum;
    }

    public Double getAreaFzSum() {
        return areaFzSum;
    }

    public void setAreaFzSum(Double areaFzSum) {
        this.areaFzSum = areaFzSum;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    private Double medicineQuaSum;
    private Double areaFzSum;
}
