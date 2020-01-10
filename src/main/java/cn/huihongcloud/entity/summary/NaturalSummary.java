package cn.huihongcloud.entity.summary;

import lombok.Data;

@Data
public class NaturalSummary {
    private Long deviceCount;
    private Long releaseNum;
    private String name;
    private String code;
    private String totalNaturalMannerByTown;
    private String NaturalMannerOneByTown;
    private String NaturalMannerTwoByTown;

    private String totalNaturalMannerByArea;
    private String NaturalMannerOneByArea;
    private String NaturalMannerTwoByArea;
    private String NaturalMannerThreeByArea;

    public String getTotalNaturalMannerByArea() {
        return totalNaturalMannerByArea;
    }

    public void setTotalNaturalMannerByArea(String totalNaturalMannerByArea) {
        this.totalNaturalMannerByArea = totalNaturalMannerByArea;
    }

    public String getNaturalMannerOneByArea() {
        return NaturalMannerOneByArea;
    }

    public void setNaturalMannerOneByArea(String naturalMannerOneByArea) {
        NaturalMannerOneByArea = naturalMannerOneByArea;
    }

    public String getNaturalMannerTwoByArea() {
        return NaturalMannerTwoByArea;
    }

    public void setNaturalMannerTwoByArea(String naturalMannerTwoByArea) {
        NaturalMannerTwoByArea = naturalMannerTwoByArea;
    }

    public String getNaturalMannerThreeByArea() {
        return NaturalMannerThreeByArea;
    }

    public void setNaturalMannerThreeByArea(String naturalMannerThreeByArea) {
        NaturalMannerThreeByArea = naturalMannerThreeByArea;
    }

    public String getTotalNaturalMannerByCustomProject() {
        return totalNaturalMannerByCustomProject;
    }

    public void setTotalNaturalMannerByCustomProject(String totalNaturalMannerByCustomProject) {
        this.totalNaturalMannerByCustomProject = totalNaturalMannerByCustomProject;
    }

    public String getNaturalMannerOneByCustomProject() {
        return naturalMannerOneByCustomProject;
    }

    public void setNaturalMannerOneByCustomProject(String naturalMannerOneByCustomProject) {
        this.naturalMannerOneByCustomProject = naturalMannerOneByCustomProject;
    }

    public String getNaturalMannerTwoByCustomProject() {
        return naturalMannerTwoByCustomProject;
    }

    public void setNaturalMannerTwoByCustomProject(String naturalMannerTwoByCustomProject) {
        this.naturalMannerTwoByCustomProject = naturalMannerTwoByCustomProject;
    }

    public String getNaturalMannerThreeByCustomProject() {
        return naturalMannerThreeByCustomProject;
    }

    public void setNaturalMannerThreeByCustomProject(String naturalMannerThreeByCustomProject) {
        this.naturalMannerThreeByCustomProject = naturalMannerThreeByCustomProject;
    }

    private String totalNaturalMannerByCustomProject;
    private String naturalMannerOneByCustomProject;
    private String naturalMannerTwoByCustomProject;
    private String naturalMannerThreeByCustomProject;
    public String getTotalNaturalMannerByTown() {
        return totalNaturalMannerByTown;
    }

    public void setTotalNaturalMannerByTown(String totalNaturalMannerByTown) {
        this.totalNaturalMannerByTown = totalNaturalMannerByTown;
    }

    public String getNaturalMannerOneByTown() {
        return NaturalMannerOneByTown;
    }

    public void setNaturalMannerOneByTown(String naturalMannerOneByTown) {
        NaturalMannerOneByTown = naturalMannerOneByTown;
    }

    public String getNaturalMannerTwoByTown() {
        return NaturalMannerTwoByTown;
    }

    public void setNaturalMannerTwoByTown(String naturalMannerTwoByTown) {
        NaturalMannerTwoByTown = naturalMannerTwoByTown;
    }

    public String getNaturalMannerThreeByTown() {
        return NaturalMannerThreeByTown;
    }

    public void setNaturalMannerThreeByTown(String naturalMannerThreeByTown) {
        NaturalMannerThreeByTown = naturalMannerThreeByTown;
    }

    private String NaturalMannerThreeByTown;





    public Long getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    public Long getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(Long releaseNum) {
        this.releaseNum = releaseNum;
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
}
