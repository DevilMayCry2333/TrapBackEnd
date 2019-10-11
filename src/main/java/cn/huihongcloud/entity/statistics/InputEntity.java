package cn.huihongcloud.entity.statistics;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 钟晖宏 on 2019/1/10
 */
@Data
public class InputEntity {

    private String name;
    private Long count;
    private String customtown;

    private BigDecimal sum;
    private BigDecimal mean;
    private Double std;
    private int max;
    private int min;


    public InputEntity(String name, BigDecimal sum,String customtown,BigDecimal mean, Double std, BigDecimal max, BigDecimal min, Long count) {


        this.name = name;
        this.count = count;
        this.customtown = customtown;
        this.sum = sum;
        /*
        BigDecimal count1=new BigDecimal(count);
        int a = count1.intValue();
        int b=sum.intValue();
        this.mean = BigDecimal.valueOf(b/a);
        */
        this.mean=mean;
        if(std==null){
            this.std=0d;
        }else {
            this.std = std;
        }
        this.max = max.intValue();;
        this.min = min.intValue();;



    }

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

    public String getCustomtown() {
        return customtown;
    }

    public void setCustomtown(String customtown) {
        this.customtown = customtown;
    }
}