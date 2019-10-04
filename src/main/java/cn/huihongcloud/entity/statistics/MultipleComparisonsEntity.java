package cn.huihongcloud.entity.statistics;

import lombok.Data;

/**
 * Created by 钟晖宏 on 2019/1/11
 */
@Data
public class MultipleComparisonsEntity {
    private String labelA;
    private String labelB;
    private Double dv;
    private Double lsd;
    private Double scheffe;
    private Boolean lsdSignificance;
    private Boolean scheffeSignificance;
    private Double standardError;
    private Double lsdConfidenceLo;
    private Double lsdConfidenceHi;
    private Double scheffeConfidenceLo;
    private Double scheffeConfidenceHi;

    public String getLabelA() {
        return labelA;
    }

    public void setLabelA(String labelA) {
        this.labelA = labelA;
    }

    public String getLabelB() {
        return labelB;
    }

    public void setLabelB(String labelB) {
        this.labelB = labelB;
    }

    public Double getDv() {
        return dv;
    }

    public void setDv(Double dv) {
        this.dv = dv;
    }

    public Double getLsd() {
        return lsd;
    }

    public void setLsd(Double lsd) {
        this.lsd = lsd;
    }

    public Double getScheffe() {
        return scheffe;
    }

    public void setScheffe(Double scheffe) {
        this.scheffe = scheffe;
    }

    public Boolean getLsdSignificance() {
        return lsdSignificance;
    }

    public void setLsdSignificance(Boolean lsdSignificance) {
        this.lsdSignificance = lsdSignificance;
    }

    public Boolean getScheffeSignificance() {
        return scheffeSignificance;
    }

    public void setScheffeSignificance(Boolean scheffeSignificance) {
        this.scheffeSignificance = scheffeSignificance;
    }

    public Double getStandardError() {
        return standardError;
    }

    public void setStandardError(Double standardError) {
        this.standardError = standardError;
    }

    public Double getLsdConfidenceLo() {
        return lsdConfidenceLo;
    }

    public void setLsdConfidenceLo(Double lsdConfidenceLo) {
        this.lsdConfidenceLo = lsdConfidenceLo;
    }

    public Double getLsdConfidenceHi() {
        return lsdConfidenceHi;
    }

    public void setLsdConfidenceHi(Double lsdConfidenceHi) {
        this.lsdConfidenceHi = lsdConfidenceHi;
    }

    public Double getScheffeConfidenceLo() {
        return scheffeConfidenceLo;
    }

    public void setScheffeConfidenceLo(Double scheffeConfidenceLo) {
        this.scheffeConfidenceLo = scheffeConfidenceLo;
    }

    public Double getScheffeConfidenceHi() {
        return scheffeConfidenceHi;
    }

    public void setScheffeConfidenceHi(Double scheffeConfidenceHi) {
        this.scheffeConfidenceHi = scheffeConfidenceHi;
    }
}
