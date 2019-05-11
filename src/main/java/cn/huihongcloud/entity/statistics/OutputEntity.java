package cn.huihongcloud.entity.statistics;

import lombok.Data;

import java.util.List;

/**
 * Created by 钟晖宏 on 2019/1/10
 */
@Data
public class OutputEntity {
    private double SSA;
    private int DFA;
    private double MSA;
    private double F;
    private double FCrit;
    private double R;
    private double SSE;
    private int DFE;
    private double MSE;
    private double SST;
    private int DFT;
    private List<String> labelA;
    private List<String> labelB;
    private List<Double> lsd;
    private List<Double> scheffe;
    private List<Double> dv;
    private List<Boolean> lsdSignificance;
    private List<Boolean> scheffeSignificance;

    List<Double> lsdConfidenceLo;
    List<Double> lsdConfidenceHi;
    List<Double> scheffeConfidenceLo;
    List<Double> scheffeConfidenceHi;
    List<Double> standardError;
}
