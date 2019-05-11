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

}
