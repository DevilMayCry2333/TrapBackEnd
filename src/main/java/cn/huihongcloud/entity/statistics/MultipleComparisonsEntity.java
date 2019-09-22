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
}
