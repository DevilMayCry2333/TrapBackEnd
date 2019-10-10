package cn.huihongcloud.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.mapper.DeviceSummaryMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/newTrap")
public class NewTrapDataSummary {
    @Autowired
    private DeviceSummaryMapper deviceSummaryMapper;

    @RequestMapping("/managerSummary")
    public Object ManagerSummary(@RequestParam(required = false) String startDate,@RequestParam(required = false) String endDate,
                                 @RequestParam(required = false) String colName,@RequestParam String manager,
                                 @RequestParam(required = false) String searchText,
                                 @RequestParam int page,
                                 @RequestParam int limit){
        List<cn.huihongcloud.entity.NewTrapDataSummary> newTrapDataSummaries = deviceSummaryMapper.selectByColName(colName,startDate,endDate,searchText,manager);
        for (int i = 0 ;i  < newTrapDataSummaries.size(); i++){
            newTrapDataSummaries.get(i).setRecordByCol(searchText);
            newTrapDataSummaries.get(i).setColName(colName);
            newTrapDataSummaries.get(i).setStartDate(startDate);
            newTrapDataSummaries.get(i).setEndDate(endDate);
            newTrapDataSummaries.get(i).setTotalNum(newTrapDataSummaries.size());
        }
        Page<Object> pageObject = PageHelper.startPage(page,limit);

        PageWrapper pageWrapper = new PageWrapper();
        System.out.println(newTrapDataSummaries.get(0).getTotalNum());
        System.out.println(limit);

        pageWrapper.setTotalPage((int) Math.ceil(newTrapDataSummaries.get(0).getTotalNum()/limit + 0.5));
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(newTrapDataSummaries.get(0).getTotalNum());
        pageWrapper.setData(newTrapDataSummaries);

        return Result.ok(pageWrapper);

    }

    @RequestMapping("/export")
    public void exportExcel(HttpServletResponse response,
                            String token,
                            @RequestParam(required = false) String startDate,@RequestParam(required = false) String endDate,
                            @RequestParam(required = false) String colName,@RequestParam String manager,
                            @RequestParam(required = false) String searchText
                                ) throws IOException {
        List<cn.huihongcloud.entity.NewTrapDataSummary> newTrapDataSummaries = deviceSummaryMapper.selectByColName(colName,startDate,endDate,searchText,manager);
        for (int i = 0; i < newTrapDataSummaries.size(); i++) {
            switch (colName){
                case "device_id":
                    newTrapDataSummaries.get(i).setColName("编号");
                    break;
                case "CustomTown":
                    newTrapDataSummaries.get(i).setColName("区域");
                    break;
                case "batch":
                    newTrapDataSummaries.get(i).setColName("批次");
                    break;
                case "username":
                    newTrapDataSummaries.get(i).setColName("施工人员");
                    break;
            }
            newTrapDataSummaries.get(i).setRecordByCol(searchText);
            newTrapDataSummaries.get(i).setStartDate(startDate);
            newTrapDataSummaries.get(i).setEndDate(endDate);

        }
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");

        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(colName);
        System.out.println(searchText);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("数据汇总", "数据汇总"), cn.huihongcloud.entity.NewTrapDataSummary.class, newTrapDataSummaries);
        workbook.write(response.getOutputStream());


    }
}
