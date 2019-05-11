package cn.huihongcloud.util;

import cn.huihongcloud.entity.statistics.*;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;


/**
 * Created by 钟晖宏 on 2019/1/10
 */
public class StatisticsUtil {

    public static double finv(double p, int df1, int df2) {
        FDistribution fDistribution = new FDistribution(df2, df1);
        return Math.pow(fDistribution.inverseCumulativeProbability(p), -1);
    }
    public static double tinv(double p, int df) {
        TDistribution tDistribution = new TDistribution(df);
        return -tDistribution.inverseCumulativeProbability(p);
    }

    /**
     * 组间差异
     * @param list 数据
     * @param pm 总均值
     * @return SSA
     */
    public static double ssa(List<InputEntity> list, double pm) {
        double ret = 0;
        for (InputEntity item: list) {
            ret += item.getCount() * Math.pow(item.getMean().doubleValue() - pm, 2);
        }
        return ret;
    }

    /**
     * 组内差异
     * @param list 数据
     * @return SSE
     */
    public static double sse(List<InputEntity> list) {
        double ret = 0;
        for (InputEntity item: list) {
            System.out.println(item);

                ret += Math.pow(item.getStd(), 2) * (item.getCount() - 1);
        }
        return ret;
    }

    /**
     * 最小显著差异
     * @param mse 组内均方
     * @param dfe 自由度
     * @param m
     * @param n
     * @return LSD
     */
    public static double lsd(double mse, int dfe, long m, long n) {
        return getNumberFormer(tinv(0.025, dfe) * Math.sqrt(mse * (1.0 / m + 1.0 /n)));
    }

    /**
     * SCHEFFE
     * @param mse
     * @param dfa
     * @param dfe
     * @param m
     * @param n
     * @return
     */
    public static double scheffe(double mse, int dfa, int dfe, long m, long n) {
        return getNumberFormer(Math.sqrt(dfa * finv(0.05, dfa, dfe) * mse*(1.0 / m + 1.0 /n)));
    }

    /**
     * 标准误差
     * @param mse
     * @param m
     * @param n
     * @return
     */
    public static double standardError(double mse, long m, long n) {
        return getNumberFormer(Math.sqrt(mse * (1.0 / m + 1.0 /n)));
    }

    public static OutputEntity calc(List<InputEntity> list) {
        /*
        private double SSA;
        private int DFA;
        private double MSA;
        private double F;
        private double FCrit;
        private double R;
        private double SSE;
        private double DFE;
        private double MSE;
        private double SST;
        private List<Double> lsd;
        private List<Double> dv;
        private List<Boolean> significance;
        */
        OutputEntity outputEntity = new OutputEntity();
        double psum = 0;
        int pcount = 0;
        for (InputEntity item: list) {
            psum += item.getSum().doubleValue();
            pcount += item.getCount();
        }
        double pm = psum / pcount; // 总均值
        double ssa = ssa(list, pm);
        double sse = sse(list);
        double sst = ssa + sse;
        int dfa = list.size() - 1;
        int dfe = pcount - list.size();
        double msa = ssa / dfa;
        double mse = sse / dfe;
        double f = msa / mse;
        double fcrit = finv(0.05, dfa, dfe);
        System.out.println("“”“”“”“”“”“”“dfa:" + dfa + " dfe:" + dfe);
        double r = Math.sqrt(ssa / sst);

        outputEntity.setSSA(ssa);
        outputEntity.setSSE(sse);
        outputEntity.setSST(sst);
        outputEntity.setDFA(dfa);
        outputEntity.setDFE(dfe);
        outputEntity.setDFT(dfa + dfe);
        outputEntity.setMSA(msa);
        outputEntity.setMSE(mse);
        outputEntity.setF(f);
        outputEntity.setFCrit(fcrit);
        outputEntity.setR(r);

        List<String> labelAs = new ArrayList<>();
        List<String> labelBs = new ArrayList<>();
        List<Double> dvs = new ArrayList<>();
        List<Double> lsds = new ArrayList<>();
        List<Double> scheffes = new ArrayList<>();
        List<Boolean> lsdSignificances = new ArrayList<>();
        List<Boolean> scheffeSignificances = new ArrayList<>();
        List<Double> lsdConfidenceLo = new ArrayList<>();
        List<Double> lsdConfidenceHi = new ArrayList<>();
        List<Double> scheffeConfidenceLo = new ArrayList<>();
        List<Double> scheffeConfidenceHi = new ArrayList<>();
        List<Double> standardError = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            for (int j = i + 1; j < list.size(); ++j) {
                labelAs.add(list.get(i).getName());
                labelBs.add(list.get(j).getName());
                double dv = getNumberFormer(Math.abs(list.get(i).getMean().doubleValue() - list.get(j).getMean().doubleValue()));
                dvs.add(dv);
                double lsd = lsd(mse, dfe, list.get(i).getCount(), list.get(j).getCount());
                double scheffe = scheffe(mse, dfa,dfe,list.get(i).getCount(), list.get(j).getCount());
                lsds.add(lsd);
                scheffes.add(scheffe);
                lsdSignificances.add(dv < lsd ? false : true);
                scheffeSignificances.add(dv < scheffe ? false : true);
                lsdConfidenceLo.add(getNumberFormer(dv - lsd));
                lsdConfidenceHi.add(getNumberFormer(dv + lsd));
                scheffeConfidenceLo.add(getNumberFormer(dv - scheffe));
                scheffeConfidenceHi.add(getNumberFormer(dv + scheffe));
                standardError.add(getNumberFormer(standardError(mse, list.get(i).getCount(), list.get(j).getCount())));
            }
        }
        outputEntity.setLabelA(labelAs);
        outputEntity.setLabelB(labelBs);
        outputEntity.setDv(dvs);
        outputEntity.setLsd(lsds);
        outputEntity.setScheffe(scheffes);
        outputEntity.setLsdSignificance(lsdSignificances);
        outputEntity.setScheffeSignificance(scheffeSignificances);
        outputEntity.setLsdConfidenceLo(lsdConfidenceLo);
        outputEntity.setLsdConfidenceHi(lsdConfidenceHi);
        outputEntity.setScheffeConfidenceLo(scheffeConfidenceLo);
        outputEntity.setScheffeConfidenceHi(scheffeConfidenceHi);
        outputEntity.setStandardError(standardError);

        return outputEntity;

    }
    public static double getNumberFormer(double d1){

            DecimalFormat    df   = new DecimalFormat("######0.0000");

            double num=Double.parseDouble(df.format(d1));

            return num;
    }
    public static StatisticsResult getResult(List<InputEntity> list) {
        StatisticsResult statisticsResult = new StatisticsResult();
        OutputEntity outputEntity = calc(list);

        // 摘要信息
        List<SummaryEntity> summaryList = new ArrayList<>();
        for (InputEntity item: list) {
            SummaryEntity summaryEntity = new SummaryEntity();
            BeanUtils.copyProperties(item, summaryEntity);

            double clo=getNumberFormer(item.getMean().doubleValue() - item.getStd() * 1.96 / Math.sqrt(item.getCount()));
            summaryEntity.setConfidenceLo(clo);
            double chi=getNumberFormer(item.getMean().doubleValue( ) + item.getStd() * 1.96 / Math.sqrt(item.getCount()));
            summaryEntity.setConfidenceHi(chi);
            double stdSl=getNumberFormer(item.getStd());
            summaryEntity.setStd(stdSl);
            summaryList.add(summaryEntity);
        }
        statisticsResult.setSummaryList(summaryList);


        // 分析信息
        AnalysisEntity analysisEntity = new AnalysisEntity();
        BeanUtils.copyProperties(outputEntity, analysisEntity);
        Double assa=outputEntity.getSSA();
        Double amsa=outputEntity.getMSA();
        Double aFCrit=outputEntity.getFCrit();
        Double af=outputEntity.getF();
        Double ar=outputEntity.getR();

            analysisEntity.setSSA(getNumberFormer(assa));
            analysisEntity.setMSA(getNumberFormer(amsa));
            analysisEntity.setFCrit(getNumberFormer(aFCrit));
            analysisEntity.setF(getNumberFormer(af));
            analysisEntity.setR(getNumberFormer(ar));
        analysisEntity.setSSE(getNumberFormer(outputEntity.getSSE()));
        analysisEntity.setMSE(getNumberFormer(outputEntity.getMSE()));
        analysisEntity.setSST(getNumberFormer(outputEntity.getSST()));

        statisticsResult.setAnalysisEntity(analysisEntity);


        //LSD 与 SCHEFFE
        List<String> labelAs = outputEntity.getLabelA();
        List<String> labelBs = outputEntity.getLabelB();
        List<Double> lsds = outputEntity.getLsd();
        List<Double> scheffes = outputEntity.getScheffe();
        List<Boolean> lsdSignificances = outputEntity.getLsdSignificance();
        List<Boolean> scheffeSignificances = outputEntity.getScheffeSignificance();

        List<Double> dvs = outputEntity.getDv();
        List<Double> lsdConfidenceLo = outputEntity.getLsdConfidenceLo();
        List<Double> lsdConfidenceHi = outputEntity.getLsdConfidenceHi();
        List<Double> scheffeConfidenceLo = outputEntity.getScheffeConfidenceLo();
        List<Double> scheffeConfidenceHi = outputEntity.getScheffeConfidenceHi();
        List<Double> standardError = outputEntity.getStandardError();
        List<MultipleComparisonsEntity> multipleComparisonsList = new ArrayList<>();
        for (int i = 0; i < labelAs.size(); ++i) {
            MultipleComparisonsEntity multipleComparisonsEntity  = new MultipleComparisonsEntity();
            multipleComparisonsEntity.setDv(getNumberFormer(dvs.get(i)));
            multipleComparisonsEntity.setLsd(getNumberFormer(lsds.get(i)));
            multipleComparisonsEntity.setLsdSignificance(lsdSignificances.get(i));
            multipleComparisonsEntity.setScheffe(getNumberFormer(scheffes.get(i)));
            multipleComparisonsEntity.setScheffeSignificance(scheffeSignificances.get(i));
            multipleComparisonsEntity.setLabelA(labelAs.get(i));
            multipleComparisonsEntity.setLabelB(labelBs.get(i));
            multipleComparisonsEntity.setStandardError(getNumberFormer(standardError.get(i)));
            multipleComparisonsEntity.setLsdConfidenceLo(getNumberFormer(lsdConfidenceLo.get(i)));
            multipleComparisonsEntity.setLsdConfidenceHi(getNumberFormer(lsdConfidenceHi.get(i)));
            multipleComparisonsEntity.setScheffeConfidenceLo(getNumberFormer(scheffeConfidenceLo.get(i)));
            multipleComparisonsEntity.setScheffeConfidenceHi(getNumberFormer(scheffeConfidenceHi.get(i)));
            multipleComparisonsList.add(multipleComparisonsEntity);
        }
        statisticsResult.setMultipleComparisonsList(multipleComparisonsList);
        return statisticsResult;
    }
}
