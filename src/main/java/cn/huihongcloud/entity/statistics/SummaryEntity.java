package cn.huihongcloud.entity.statistics;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 钟晖宏 on 2019/1/11
 */
@Data
public class SummaryEntity {
    private String name;
    private Long count;
    private BigDecimal sum;
    private BigDecimal mean;
    private Double std;
    private int max;
    private int min;
    private Double confidenceLo;
    private Double confidenceHi;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getMean() {
        return mean;
    }

    public void setMean(BigDecimal mean) {
        this.mean = mean;
    }

    public Double getStd() {
        return std;
    }

    public void setStd(Double std) {
        this.std = std;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public Double getConfidenceLo() {
        return confidenceLo;
    }

    public void setConfidenceLo(Double confidenceLo) {
        this.confidenceLo = confidenceLo;
    }

    public Double getConfidenceHi() {
        return confidenceHi;
    }

    public void setConfidenceHi(Double confidenceHi) {
        this.confidenceHi = confidenceHi;
    }
}
