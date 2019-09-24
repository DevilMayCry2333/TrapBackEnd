package cn.huihongcloud.controller.deadtrees;

import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.DeadTreeCutService;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deadTree")
public class DeadTreeCut {
    @Autowired
    DeadTreeCutService deadTreeCutService;
    @Autowired
    UserService userService;

    JSONObject jsonObject = new JSONObject();
    @RequestMapping("/detail")
    public Object detail(@RequestParam String username,@RequestParam int page,@RequestParam int limit){
        jsonObject.put("Res",true);
        System.out.println(page);
        System.out.println(limit);

        jsonObject.put("Data",deadTreeCutService.selectAll(username,page*limit-limit,limit));
        jsonObject.put("total",deadTreeCutService.countAll(username));
        jsonObject.put("current",page);

        return jsonObject;
    }

    @RequestMapping("/areaDetail")
    public Object areaDetail(@RequestParam String username,@RequestParam int page,@RequestParam int limit){
        jsonObject.put("Data",deadTreeCutService.selectAllByArea(username, page*limit-limit, limit));
        jsonObject.put("total",deadTreeCutService.countAllByArea(username));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;
    }

    @RequestMapping("/searchDetail")
    public Object searchDetail(@RequestParam int page,@RequestParam int limit,@RequestParam String username,@RequestParam String startDate,@RequestParam String endDate,@RequestParam String colName,@RequestParam String searchText,@RequestParam String adcode){
        jsonObject.put("Res",true);
        System.out.println(page);
        System.out.println(limit);
        jsonObject.put("Data",deadTreeCutService.selectByDateAndColSearch(username,startDate,endDate,colName,searchText,page*limit-limit,page*limit,adcode));
        jsonObject.put("total",deadTreeCutService.countAll(username));
        jsonObject.put("current",page);
        System.out.println(jsonObject);

        return jsonObject;

    }

    @RequestMapping("/selectAll")
    public Object selectAll(@RequestParam String username,@RequestParam String adcode,@RequestParam int page,@RequestParam int limit){
        jsonObject.put("Data",deadTreeCutService.selectAllByAdcode(adcode, page*limit-limit, page*limit));
        jsonObject.put("total",deadTreeCutService.countAll(username));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;

    }

    @RequestMapping(value = "/device_list", method = RequestMethod.GET)
    @ApiOperation("获取设备列表")
    public PageWrapper getDevices(@RequestAttribute("username") String username, @RequestParam("page") int page,
                                  @RequestParam("limit") int limit,
                                  @RequestParam(value = "searchText", required = false) String searchText,
                                  @RequestParam(value = "workerName", required = false) String workerName) {
        System.out.println(workerName);
        User user = userService.getUserByUserName(username);
        List<Device> list = null;
        Page<Object> pages = null;
        PageWrapper pageWrapper = new PageWrapper();
        /*
        if (user.getRole() > 1 && user.getRole() != 3) {
            // 工人查询所管理的设备
            pages = PageHelper.startPage(page, limit);
            list = deviceService.getDeviceByMap(username);
        } else if (workerName == null){
            // 管理员未传递指定工人 查询其下属地区所有诱捕器信息
            pages = PageHelper.startPage(page, limit);
            list = deviceService.getDeviceByLocation(user.getAdcode(), user.getTown(), searchText.trim());
        } else {
            // 管理员传递工人信息
            // todo 限制跨区域查询

            list = deviceService.getDeviceByMap(workerName);
        }
        */
        /*
        县级用户看到所有下属的诱捕器
        管理员看到关联的
        工人看到关联的
         */
        pages = PageHelper.startPage(page, limit);
        if (user.getRole() == 1) {
            list = deadTreeCutService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 2) {
            list = deadTreeCutService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 3) {
            list = deadTreeCutService.getDeviceByLocation(user.getAdcode(), null, null);
        }

        if (user.getRole() == 4) {
            list = deadTreeCutService.getDeviceByManager(username);
        }

        if (user.getRole() == 5) {
            list = deadTreeCutService.getDeviceByWorker(username);
        }
        pageWrapper.setData(list);
        pageWrapper.setTotalPage(pages.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pages.getTotal());
        return pageWrapper;
    }




}
