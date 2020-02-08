package cn.huihongcloud.controller;

import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.service.DeviceService;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 钟晖宏 on 2018/6/15
 */

@RestController
@Api("DeviceController API")
public class DeviceController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    DeviceMapper deviceMapper;

    @Value("${device.img.path}")
    String pathPrefix;

    @RequestMapping(value = "auth_api/device_list", method = RequestMethod.GET)
    @ApiOperation("获取设备列表")
    public Object getDevices(@RequestAttribute("username") String username,int page,
                                int limit,
                                  @RequestParam(value = "searchText", required = false) String searchText,
                                  @RequestParam(value = "workerName", required = false) String workerName,
                                  @RequestParam("isMap") boolean isMap,
                             @RequestParam(value = "project",required = false) String project) {
        User user = userService.getUserByUserName(username);

        Page<Object> pageObject = PageHelper.startPage(page, limit);

        List<Device> deviceList = null;
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


        if(user.getRole()<1){
            deviceList =  deviceMapper.adminDevice();
        }
        if (user.getRole() == 1) {
            deviceList = deviceService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 2) {
            deviceList = deviceService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 3) {
            deviceList = deviceService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 4) {


//            list = deviceService.getDeviceByManager(username);
            if(isMap==true){
                deviceList = deviceMapper.getDeviceByCustomProjectAndTrap(user.getParent());

            }else {
                deviceList = deviceMapper.getDeviceByCustomProject(user.getParent(),project);



            }


        }

        if (user.getRole() == 5) {
//            list = deviceService.getDeviceByWorker(username);
            //改成工程账号查询


            User user1 = userService.getUserByUserName(user.getParent());
            User user2 = userService.getUserByUserName(user1.getParent());


            deviceList = deviceMapper.getDeviceByCustomProject(user2.getUsername(),project);
        }



        PageWrapper pageWrapper = new PageWrapper();

        pageWrapper.setData(deviceList);





        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());


        return pageWrapper;
    }

    @PostMapping(value = "auth_api/device_relation")
    @ApiOperation("增加设备关联")
    public Object addDeviceRelation(@RequestAttribute("username") String username, @RequestBody List<Map<String, Object>> data) {
        User user = userService.getUserByUserName(username);
        String adcode = user.getAdcode();
        List<String> deviceIds = deviceService.getDeviceIdsCanAssociateWithWorker(adcode, username);
        int sum = 0;
        for (Map<String, Object> item : data) {
            String worker = (String) item.get("worker");
            Integer num = (Integer) item.get("num");
            sum += num;
        }
        if (sum > deviceIds.size()) {
            return Result.failed("超出可分配的设备数量");
        }
        int index = 0;
        for (Map<String, Object> item : data) {
            String worker = (String) item.get("worker");
            Integer num = (Integer) item.get("num");
            for (int i = 0; i < num; ++i, ++index) {
                deviceService.addDeviceRelation(deviceIds.get(index), worker);
            }
        }
        return Result.ok();

    }

    @RequestMapping(value = "auth_api/device_relation", method = RequestMethod.PUT)
    @ApiOperation("编辑设备关联")
    public Object editDeviceRelation(@RequestAttribute("username") String username, @RequestBody Map<String, Object> data) {
        // todo 增加限制
        String worker = (String) data.get("userId");
        deviceService.clearWorkerDeviceRelation(worker);
        List<String> deviceIds = (List<String>) data.get("deviceIds");
        for (String item: deviceIds) {
            deviceService.addDeviceRelation(item, worker);
        }
        return Result.ok();
    }

    @RequestMapping(value = "auth_api/device_relation", method = RequestMethod.DELETE)
    @ApiOperation("删除设备关联")
    public void deleteDeviceRelation(@RequestAttribute("username") String username, @RequestParam("userId") String userId,
                                     @RequestParam("deviceId") String deviceId, HttpServletResponse response) {
        // todo 增加限制
        Boolean result = deviceService.deleteDeviceRelation(deviceId, userId);
        if (!result) {
            response.setStatus(400);
        }
    }

//    @RequestMapping(value = "auth_api/device_relation", method = RequestMethod.GET)
//    @ApiOperation("获取用户可关联的设备")
    // 废弃这个
    public PageWrapper getDeviceNotAssociatedWithWorker(@RequestAttribute("username") String username,
                                                         @RequestParam("workerName") String workerName,
                                                         @RequestParam("page") int page,
                                                         @RequestParam("limit") int limit,
                                                         @RequestParam("searchText") String searchText) {
        // 获取到用户
//        User user = userMapper.getUserByUserName(userId);
        // 获取当前用户已经关联过的设备

//        List<Device> associatedList = deviceMapper.getDeviceByMap(userId);
//        List<Device> areaDeviceList = deviceMapper.getDeviceByLocation(user.getAdcode(), user.getTown(), "");
//        for (int i = 0; i < areaDeviceList.size(); ++i) {
//            for (int j = 0; j < associatedList.size(); ++i) {
//                if (associatedList.get(j).getId().equals(areaDeviceList.get(i).getId())) {
//                    associatedList.remove(j);
//                    break;
//                }
//            }
//        }
        //尝试粗暴的多表连接
        PageWrapper pageWrapper = new PageWrapper();
        Page<Object> pages = PageHelper.startPage(page, limit);
        List<Device> list = deviceService.getDeviceNotAssociatedWithWorker(workerName, searchText.trim());
        pageWrapper.setData(list);
        pageWrapper.setTotalPage(pages.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pages.getTotal());
        return pageWrapper;
    }

    @GetMapping("auth_api/device_relation")
    public Object getDeviceIdsAssociatedWithWorker(String worker) {
        return Result.ok(deviceService.getDeviceIdsAssociatedWithWorker(worker));
    }

    @RequestMapping(value = "auth_api/device", method = RequestMethod.POST)
    @ApiOperation("扫描到设备")
    public void DeviceScannedByWorker(@RequestAttribute("username") String username, @RequestBody Device device) {

        Device obj = deviceService.packDevice(device.getLatitude(), device.getLongitude(), device.getId());
        obj.setAltitude(device.getAltitude());
        obj.setReceiveDate(new Date());
        User user = userService.getUserByUserName(username);
        // todo 这里是否要判断下？？？？
        if (user.getRole() == 2 && deviceService.judgeDeviceRelation(username, device.getId())) {
//            deviceService.addDeviceRelation(device.getId(), user.getUsername());
            deviceService.updateDevice(obj);


        }
//        if (deviceService.isExist(obj.getId())) {
//            deviceService.updateDevice(obj);
//        } else {
//            deviceService.addDevice(obj);
//        }
    }

    /*
    public void DeviceScannedByWorker(@RequestAttribute("username") String username, @RequestBody Device device) {

        Device obj = deviceService.packDevice(device.getLatitude(), device.getLongitude(), device.getId());
        obj.setAltitude(device.getAltitude());
        obj.setReceiveDate(new Date());
        User user = userService.getUserByUserName(username);
        if (user.getRole() == 2) {
            deviceService.addDeviceRelation(device.getId(), user.getUsername());
        }
        if (deviceService.isExist(obj.getId())) {
            deviceService.updateDevice(obj);
        } else {
            deviceService.addDevice(obj);
        }
    }
     */

    @PutMapping("auth_api/device")
    public Object deviceModifyByAdministrator() {
        return Result.ok();
    }

    @RequestMapping(value = "auth_api/device_code", method = RequestMethod.GET)
    @ApiOperation("生成设备识别码")
    public String generateDeviceCode(@RequestAttribute("username") String username) {
        return deviceService.generateDeviceCode(username);
    }

    @RequestMapping(value = "auth_api/device_img", method = RequestMethod.POST)
    @ApiOperation("上传设备图片")
    public Result uploadImg(@RequestAttribute("username") String username,
                            @RequestParam("image") MultipartFile multipartFile,
                            @RequestParam("deviceId") String deviceId) {
        if (deviceService.saveImg(multipartFile, deviceId, username) != null) {
            return new Result.Ok();
        } else {
            return new Result.Failed();
        }
    }

    @RequestMapping(value = "auth_api/device_img", method = RequestMethod.GET)
    @ApiOperation("获取设备图片信息")
    public PageWrapper getDeviceImgInfo(String deviceId) {
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(deviceService.getDeviceImgListByDeviceId(deviceId));
        return pageWrapper;
    }

    @RequestMapping(value = "device_img", method = RequestMethod.GET)
    @ApiOperation("获取图片")
    public ResponseEntity<byte[]> getDeviceImg(@RequestParam("imgName") String imgName) throws IOException {
        String realPath = deviceService.getImgPath(imgName);
        if (realPath != null && !realPath.isEmpty()) {
            File file = new File(realPath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", imgName);
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<byte[]>(Files.readAllBytes(file.toPath()), headers, HttpStatus.CREATED);
        }
        return null;
    }

    @RequestMapping(value = "device_img2", method = RequestMethod.GET)
    @ApiOperation("获取图片")
    public ResponseEntity<byte[]> getDeviceImg2(@RequestParam("imgName") String imgName) throws IOException {
//        String realPath = deviceService.getImgPath(imgName);
        String realPath = pathPrefix + imgName;
        if (realPath != null && !realPath.isEmpty()) {
            File file = new File(realPath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", imgName);
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<byte[]>(Files.readAllBytes(file.toPath()), headers, HttpStatus.CREATED);
        }
        return null;
    }



    @GetMapping("auth_api/device_count")
    public Object getDeviceCountByUser(@RequestAttribute String username) {
        return Result.ok(deviceService.getDeviceCountByUser(username));
    }

    @GetMapping("auth_api/device_in_region")
    public Object getDeviceInRegion(String adcode, String town, int limit, int page) {
        PageWrapper pageWrapper = new PageWrapper();
        Page<Object> pages = PageHelper.startPage(page, limit);
        List<Device> list = deviceService.getDeviceInRegion(adcode, town);
        pageWrapper.setData(list);
        pageWrapper.setTotalPage(pages.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pages.getTotal());
        return pageWrapper;
    }

    /*
        因为现在改成一对多
     */

    @GetMapping("auth_api/device_can_associate")
    public Object getDeviceIdsCanAssociateWithWorker(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);
        return Result.ok(deviceService.getDeviceIdsCanAssociateWithWorker(user.getAdcode(), username));
    }

    @GetMapping("auth_api/test_belongings")
    @ApiOperation("测试该设备是否属于某用户")
    public Object testDeviceBelongings(@RequestAttribute String username, String deviceId) {
        return Result.ok(deviceService.judgeDeviceRelation(username, deviceId));
    }


}
