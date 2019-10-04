package cn.huihongcloud.entity.statistics;

import lombok.Data;

/**
 * Created by 钟晖宏 on 2019/1/11
 */
@Data
public class AnalysisEntity {
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

    public double getSSA() {
        return SSA;
    }

    public void setSSA(double SSA) {
        this.SSA = SSA;
    }

    public int getDFA() {
        return DFA;
    }

    public void setDFA(int DFA) {
        this.DFA = DFA;
    }

    public double getMSA() {
        return MSA;
    }

    public void setMSA(double MSA) {
        this.MSA = MSA;
    }

    public double getF() {
        return F;
    }

    public void setF(double f) {
        F = f;
    }

    public double getFCrit() {
        return FCrit;
    }

    public void setFCrit(double FCrit) {
        this.FCrit = FCrit;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public double getSSE() {
        return SSE;
    }

    public void setSSE(double SSE) {
        this.SSE = SSE;
    }

    public int getDFE() {
        return DFE;
    }

    public void setDFE(int DFE) {
        this.DFE = DFE;
    }

    public double getMSE() {
        return MSE;
    }

    public void setMSE(double MSE) {
        this.MSE = MSE;
    }

    public double getSST() {
        return SST;
    }

    public void setSST(double SST) {
        this.SST = SST;
    }

    public int getDFT() {
        return DFT;
    }

    public void setDFT(int DFT) {
        this.DFT = DFT;
    }
}
