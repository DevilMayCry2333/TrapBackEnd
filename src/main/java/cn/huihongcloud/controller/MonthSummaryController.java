package cn.huihongcloud.controller;
import cn.huihongcloud.component.JWTComponent;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.service.DeviceMaintenanceService;
import cn.huihongcloud.util.StatisticsUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import cn.huihongcloud.entity.page.PageWrapper;
import javax.servlet.http.HttpServletResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class MonthSummaryController {
    @Autowired
    private DeviceMaintenanceService deviceMaintenanceService;

    @Autowired
    // private MonthSummaryService monthSummaryService;

    //@Autowired
    private JWTComponent jwtComponent;


    @GetMapping("auth_api/monthSummary/city")
    public PageWrapper getMonthSummaryDataByCity(String adcode,String startYear,String endYear) {

        // if( myYear.length()==0){

        //   return Result.failed();
        // }

        Page<Object> pageObject = PageHelper.startPage(1, 200);
        Object maintenanceData = deviceMaintenanceService.getMonthSummaryByCity(adcode, startYear,endYear);

        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setCurrentPage(1);

        return pageWrapper;

    }
/*

SELECT Customer,SUM(OrderPrice) FROM Orders
GROUP BY Customer
select sum(num) from device_maintenance
group by month where year=?
 */
}