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
}
