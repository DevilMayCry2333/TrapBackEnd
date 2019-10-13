package cn.huihongcloud.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.huihongcloud.entity.statistics.AnalysisEntity;
import cn.huihongcloud.entity.statistics.InputEntity;
import cn.huihongcloud.entity.statistics.StatisticsResult;
import cn.huihongcloud.util.StatisticsUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2019/1/19
 */
@Service
public class StatisticsService {


    @Autowired
    private DeviceMaintenanceService deviceMaintenanceService;

    public Workbook exportStatisticsData(String adcode, String manager, String startDate, String endDate, Integer type) {

        List<InputEntity> inputs = null;
        Map<String, Object> map = new HashMap<String, Object>();

        switch (type) {
            case 0: inputs = deviceMaintenanceService.getInputEntityForCity(adcode, startDate, endDate); map.put("type", "市");break;
            case 1: inputs = deviceMaintenanceService.getInputEntityForArea(adcode, startDate, endDate); map.put("type", "县");break;
            case 2: inputs = deviceMaintenanceService.getInputEntityForTown(adcode, startDate, endDate); map.put("type", "乡镇");break;
            case 3: inputs = deviceMaintenanceService.getInputEntityForWorker(manager, startDate, endDate); map.put("type", "工人");break;
        }
        if (inputs == null || inputs.size()<2 || inputs.isEmpty())
            return null;
        StatisticsResult result = StatisticsUtil.getResult(inputs);
        System.out.println(result);
        TemplateExportParams templateExportParams = new TemplateExportParams("statisticsExcel.xlsx", 0, 1, 2, 3);
        AnalysisEntity analysisEntity = result.getAnalysisEntity();
        map.put("dfa", analysisEntity.getDFA());
        map.put("dfe", analysisEntity.getDFE());
        map.put("f", analysisEntity.getF());
        map.put("fcrit", analysisEntity.getFCrit());
        map.put("msa", analysisEntity.getMSA());
        map.put("mse", analysisEntity.getMSE());
        map.put("r", analysisEntity.getR());
        map.put("ssa", analysisEntity.getSSA());
        map.put("sse", analysisEntity.getSSE());
        map.put("sst", analysisEntity.getSST());
        map.put("dft", analysisEntity.getDFT());
        if(result.getMultipleComparisonsList().size()>2) {
            map.put("multipleComparisonsList", result.getMultipleComparisonsList());
        }
        map.put("summaryList", result.getSummaryList());
        return ExcelExportUtil.exportExcel(templateExportParams, map);
    }
}
