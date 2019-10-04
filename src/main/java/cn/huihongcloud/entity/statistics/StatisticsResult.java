package cn.huihongcloud.entity.statistics;

import lombok.Data;

import java.util.List;

/**
 * Created by 钟晖宏 on 2019/1/11
 */
@Data
public class StatisticsResult {

    private List<SummaryEntity> summaryList;
    private List<MultipleComparisonsEntity> multipleComparisonsList;
    private AnalysisEntity analysisEntity;

    public List<SummaryEntity> getSummaryList() {
        return summaryList;
    }

    public void setSummaryList(List<SummaryEntity> summaryList) {
        this.summaryList = summaryList;
    }

    public List<MultipleComparisonsEntity> getMultipleComparisonsList() {
        return multipleComparisonsList;
    }

    public void setMultipleComparisonsList(List<MultipleComparisonsEntity> multipleComparisonsList) {
        this.multipleComparisonsList = multipleComparisonsList;
    }

    public AnalysisEntity getAnalysisEntity() {
        return analysisEntity;
    }

    public void setAnalysisEntity(AnalysisEntity analysisEntity) {
        this.analysisEntity = analysisEntity;
    }
}
