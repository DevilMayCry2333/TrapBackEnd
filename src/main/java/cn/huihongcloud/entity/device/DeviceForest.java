package cn.huihongcloud.entity.device;

/**
 * Created by 钟晖宏 on 2018/7/18
 */
public class DeviceForest {

    private int id;
    private String deviceId;
    private String slopePosition;
    private String slopeDirection;
    private String forestStructure;
    private Double avgHeight;
    private Double avgDbh;
    private int forestStructureDensity;
    private Double crownDensity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSlopePosition() {
        return slopePosition;
    }

    public void setSlopePosition(String slopePosition) {
        this.slopePosition = slopePosition;
    }

    public String getSlopeDirection() {
        return slopeDirection;
    }

    public void setSlopeDirection(String slopeDirection) {
        this.slopeDirection = slopeDirection;
    }

    public String getForestStructure() {
        return forestStructure;
    }

    public void setForestStructure(String forestStructure) {
        this.forestStructure = forestStructure;
    }

    public Double getAvgHeight() {
        return avgHeight;
    }

    public void setAvgHeight(Double avgHeight) {
        this.avgHeight = avgHeight;
    }

    public Double getAvgDbh() {
        return avgDbh;
    }

    public void setAvgDbh(Double avgDbh) {
        this.avgDbh = avgDbh;
    }

    public int getForestStructureDensity() {
        return forestStructureDensity;
    }

    public void setForestStructureDensity(int forestStructureDensity) {
        this.forestStructureDensity = forestStructureDensity;
    }

    public Double getCrownDensity() {
        return crownDensity;
    }

    public void setCrownDensity(Double crownDensity) {
        this.crownDensity = crownDensity;
    }
}
