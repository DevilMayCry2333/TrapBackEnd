package cn.huihongcloud.controller.natural;

import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.NaturalEnemyService;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/natural")
public class NaturalEnemy {
    @Autowired
    NaturalEnemyService naturalEnemyService;
    @Autowired
    UserService userService;

    JSONObject jsonObject = new JSONObject();
    @RequestMapping("/detail")
    public Object NaturalDetail(@RequestParam int page,@RequestParam int limit,@RequestParam String username){
        jsonObject.put("Res",true);
        System.out.println(page);
        System.out.println(limit);
        jsonObject.put("Data",naturalEnemyService.selectAll(username,page*limit-limit,page*limit));
        jsonObject.put("total",naturalEnemyService.countAll(username));
        jsonObject.put("current",page);
        return jsonObject;
    }

    @RequestMapping("/areaDetail")
    public Object DetailByArea(@RequestParam int page,@RequestParam int limit,@RequestParam String username){
        jsonObject.put("Data",naturalEnemyService.selectAllByArea(username,page*limit-limit,page*limit));
        jsonObject.put("total",naturalEnemyService.countAllByArea(username));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;
    }

    @GetMapping("/maintenance2")
    public Object getMaintenanceData2(@RequestAttribute("username") String username, int page, int limit,
                                      @RequestParam(required = false) String condition,
                                      @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        System.out.println(condition);
//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<Device_NaturalEnemies_maintanceEntity> maintenanceData = naturalEnemyService.getMaintenanceData2(user, condition, startDate, endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }

}
