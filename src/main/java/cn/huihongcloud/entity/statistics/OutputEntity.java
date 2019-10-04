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


    public List<Double> getLsdConfidenceLo(){
        return lsdConfidenceLo;
    }

    public void setLsdConfidenceLo(List<Double> lsdConfidenceLo){
        this.lsdConfidenceLo = lsdConfidenceLo;
    }

    public List<Double> getLsdConfidenceHi(){
        return lsdConfidenceHi;
    }

    public void setLsdConfidenceHi(List<Double> lsdConfidenceHi){
        this.lsdConfidenceHi = lsdConfidenceHi;
    }

    public List<Double> getScheffeConfidenceLo(){
        return scheffeConfidenceLo;
    }
    public void setScheffeConfidenceLo(List<Double> scheffeConfidenceLo){
        this.scheffeConfidenceLo = scheffeConfidenceLo;
    }

    public List<Double> getScheffeConfidenceHi(){
        return scheffeConfidenceHi;

    }
    public void setScheffeConfidenceHi(List<Double> scheffeConfidenceHi){
        this.scheffeConfidenceHi = scheffeConfidenceHi;
    }

    public List<Double> getStandardError(){
        return standardError;
    }

    public void setStandardError(List<Double> standardError){
        this.standardError = standardError;
    }

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

    public List<String> getLabelA() {
        return labelA;
    }

    public void setLabelA(List<String> labelA) {
        this.labelA = labelA;
    }

    public List<String> getLabelB() {
        return labelB;
    }

    public void setLabelB(List<String> labelB) {
        this.labelB = labelB;
    }

    public List<Double> getLsd() {
        return lsd;
    }

    public void setLsd(List<Double> lsd) {
        this.lsd = lsd;
    }

    public List<Double> getScheffe() {
        return scheffe;
    }

    public void setScheffe(List<Double> scheffe) {
        this.scheffe = scheffe;
    }

    public List<Double> getDv() {
        return dv;
    }

    public void setDv(List<Double> dv) {
        this.dv = dv;
    }

    public List<Boolean> getLsdSignificance() {
        return lsdSignificance;
    }

    public void setLsdSignificance(List<Boolean> lsdSignificance) {
        this.lsdSignificance = lsdSignificance;
    }

    public List<Boolean> getScheffeSignificance() {
        return scheffeSignificance;
    }

    public void setScheffeSignificance(List<Boolean> scheffeSignificance) {
        this.scheffeSignificance = scheffeSignificance;
    }
}
