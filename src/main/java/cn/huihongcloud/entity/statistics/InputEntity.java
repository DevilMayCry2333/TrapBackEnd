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
    private BigDecimal sum;
    private BigDecimal mean;
    private Double std;
    private int max;
    private int min;


    public InputEntity(String name, BigDecimal sum, BigDecimal mean, Double std, BigDecimal max, BigDecimal min, Long count) {
        this.name = name;
        this.count = count;
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
}