package cn.huihongcloud.controller.track;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.huihongcloud.entity.DeviceTrackMap;
import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.Device_Track_MaintanceEntity;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_Track_MaintanceEntityMapper;
import cn.huihongcloud.service.TrackService;
import cn.huihongcloud.service.UserService;
import cn.huihongcloud.util.ImageDownUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/track")
public class Track {
    @Autowired
    TrackService trackService;
    @Autowired
    UserService userService;
    @Autowired
    Device_Track_MaintanceEntityMapper deviceTrackMaintanceEntityMapper;


    JSONObject jsonObject = new JSONObject();

    @RequestMapping("/detail")
    public Object detail(@RequestParam String username,@RequestParam int page,@RequestParam int limit){
        List<Device_Track_MaintanceEntity> deviceTrackMaintanceEntities = trackService.selectAll(username, page*limit-limit, page*limit);

        for (int i = 0; i < deviceTrackMaintanceEntities.size(); i++) {
            deviceTrackMaintanceEntities.get(i).setChecked(false);

        }
        jsonObject.put("Data",deviceTrackMaintanceEntities);
        jsonObject.put("total",trackService.countAll(username,null,null,null,null));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;
    }

    @RequestMapping("/areaDetail")
    public Object areaDetail(@RequestParam String username,@RequestParam int page,@RequestParam int limit){
        User user = userService.getUserByUserName(username);

        jsonObject.put("Data",trackService.selectAllByArea(user.getAdcode(), page*limit-limit, page*limit));
        jsonObject.put("total",trackService.countAllArea(user.getAdcode()));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;
    }

    @RequestMapping("/searchDetail")
    public Object searchDetail(@RequestParam int page,@RequestParam int limit,@RequestParam String username,@RequestParam(required = false) String startDate,@RequestParam(required = false) String endDate,@RequestParam(required = false) String colName,@RequestParam(required = false) String searchText,@RequestParam String adcode){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);

        User user = userService.getUserByUserName(username);

        System.out.println("=====用户名====");
        System.out.println(username);
        String dateString = null;

        if(endDate!=null) {
            try {
                Date currentTime_2 = formatter.parse(endDate, pos);

                currentTime_2.setTime(currentTime_2.getTime() + 24 * 3600 * 1000);

                System.out.println(currentTime_2.getDate());

                dateString = formatter.format(currentTime_2);

                System.out.println(dateString);
            }catch (Exception e){
                dateString = null;
            }

        }


        jsonObject.put("Res",true);
        System.out.println(page);
        System.out.println(limit);
        System.out.println(colName);
        System.out.println(searchText);
        if(user.getRole()==4){
            jsonObject.put("Data",trackService.selectByDateAndColSearch(username,startDate,dateString,colName,searchText,page*limit-limit,page*limit,adcode));
            jsonObject.put("total",trackService.countAll(username,startDate,dateString,colName,searchText));
        }else {
            jsonObject.put("Data",deviceTrackMaintanceEntityMapper.selectByDateAndColSearchAdcode(startDate,dateString,colName,searchText,page*limit-limit,limit,user.getAdcode()));
            jsonObject.put("total",deviceTrackMaintanceEntityMapper.countAllByAdcode(user.getAdcode(),startDate,dateString,colName,searchText));
        }



        jsonObject.put("current",page);
        System.out.println(jsonObject);

        return jsonObject;

    }

    @RequestMapping("/selectAll")
    public Object selectAll(@RequestParam String username,@RequestParam String adcode,@RequestParam int page,@RequestParam int limit){
        User user = userService.getUserByUserName(username);

        jsonObject.put("Data",trackService.selectAllByAdcode(user.getAdcode(), page*limit-limit, page*limit));
        jsonObject.put("total",trackService.countAll(username,null,null,null,null));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;

    }

    @RequestMapping(value = "/device_list", method = RequestMethod.GET)
    @ApiOperation("获取设备列表")
    public PageWrapper getDevices(@RequestAttribute("username") String username, @RequestParam("page") int page,
                                  @RequestParam("limit") int limit,
                                  @RequestParam(value = "searchText", required = false) String searchText,
                                  @RequestParam(value = "workerName", required = false) String workerName,
                                  @RequestParam(value = "lineName",required = false) String lineName) {

        System.out.println(workerName);
        User user = userService.getUserByUserName(username);

        List<Device> list = null;
        Page<Object> pages = null;
        PageWrapper pageWrapper = new PageWrapper();

        List<DeviceTrackMap> deviceTrackMaps = null;
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
//            list = trackService.getDeviceByLocation(user.getAdcode(), null, null);
            deviceTrackMaps = trackService.getDeviceByManager(username,true,user.getAdcode(),"map",null);
        }
        if (user.getRole() == 2) {
//            list = trackService.getDeviceByLocation(user.getAdcode(), null, null);
            deviceTrackMaps = trackService.getDeviceByManager(username,true,user.getAdcode(),"map",null);
        }
        if (user.getRole() == 3) {
//            list = trackService.getDeviceByLocation(user.getAdcode(), null, null);
            deviceTrackMaps = trackService.getDeviceByManager(username,true,user.getAdcode(),"map",null);
        }

        if (user.getRole() == 4) {
            if(lineName!=null && lineName!=""){
                deviceTrackMaps = trackService.getDeviceByManager(username,false,null,"detail",lineName);
            }else {
                deviceTrackMaps = trackService.getDeviceByManager(username,false,null,"map",null);
            }

        }

        if (user.getRole() == 5) {
//            list = trackService.getDeviceByWorker(username);
            deviceTrackMaps = trackService.getDeviceByManager(username,false,null,"map",null);
        }


        JSONArray totalData = new JSONArray();

        deviceTrackMaps.stream().collect(Collectors.groupingBy(DeviceTrackMap::getLinename)).forEach((track,MyList) -> {
            JSONObject dataByGroup = new JSONObject();
            System.out.println(track);
            dataByGroup.put("trackGroup",MyList);
            dataByGroup.put("area",user.getArea());
            dataByGroup.put("city",user.getCity());
            dataByGroup.put("province",user.getProvince());
            totalData.add(dataByGroup);
        });
        pageWrapper.setData(totalData);
        pageWrapper.setTotalPage(pages.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pages.getTotal());
        return pageWrapper;
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response,
                            String token,
                            @RequestParam(required = false) String startDate,
                            @RequestParam(required = false) String endDate,
                            @RequestParam(required = false) String colName,
                            @RequestParam(required = false) String searchText,
                            @RequestParam String username,
                            @RequestParam String adcode
    ) throws IOException {
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");

        User user = userService.getUserByUserName(username);

        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(colName);
        System.out.println(searchText);
        List<Device_Track_MaintanceEntity> deviceTrackMaintanceEntities = null;

        if(user.getRole()==4){
            deviceTrackMaintanceEntities  = trackService.selectByDateAndColSearch(username,startDate,endDate,colName,searchText,1*10-10,1*10,adcode);
        }else {
            deviceTrackMaintanceEntities  = deviceTrackMaintanceEntityMapper.selectByDateAndColSearchAdcode(startDate,endDate,colName,searchText,1*10-10,1*10,user.getAdcode());
        }

//        for (Device_NaturalEnemies_maintanceEntity d:
//             deviceNaturalEnemiesMaintanceEntities) {
//            System.out.println(d.getArea());
//
//        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轨迹跟踪明细表", "轨迹跟踪明细表"), Device_Track_MaintanceEntity.class, deviceTrackMaintanceEntities);
        workbook.write(response.getOutputStream());

    }

    @RequestMapping("/exportImage")
    public void exportImage(HttpServletResponse response,
                            String token,
                            @RequestParam(required = false) String startDate,
                            @RequestParam(required = false) String endDate,
                            @RequestParam(required = false) String colName,
                            @RequestParam(required = false) String searchText,
                            @RequestParam String username,
                            @RequestParam String adcode
    ) throws IOException {
        User user = userService.getUserByUserName(username);
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(colName);
        System.out.println(searchText);
        if(colName.equals("1")){
            colName = "serial";
        }
        if(colName.equals("2")){
            colName = "CustomTown";
        }
        if(colName.equals("3")){
            colName = "batch";
        }
        if(colName.equals("4")){
            colName = "Worker";
        }

        List<Device_Track_MaintanceEntity> deviceTrackMaintanceEntities = null;
        if(user.getRole()==4){
            deviceTrackMaintanceEntities  = trackService.selectByDateAndColSearch(username,startDate,endDate,colName,searchText,1*10-10,1*10,adcode);
        }else {
            deviceTrackMaintanceEntities  = deviceTrackMaintanceEntityMapper.selectByDateAndColSearchAdcode(startDate,endDate,colName,searchText,1*10-10,1*10,user.getAdcode());
        }
        ImageDownUtil imageDownUtil = new ImageDownUtil();
        for (Device_Track_MaintanceEntity d:deviceTrackMaintanceEntities) {
            try {
                for(int i = 0;i<5;i++){
//                    String tmp = d.getPic();
                    imageDownUtil.moveFile("/root/img/" + d.getPic1(), "/var/www/html/img/" + "照片1," + "线路名称："+d.getLinename() + "," + "耗时：" + d.getTimeconsume() + "," +"工作内容："+ d.getWorkingContent());
                    imageDownUtil.moveFile("/root/img/" + d.getPic2(), "/var/www/html/img/" + "照片2," + "线路名称："+d.getLinename() + "," + "耗时：" + d.getTimeconsume() + "," +"工作内容："+ d.getWorkingContent());
                    imageDownUtil.moveFile("/root/img/" + d.getPic3(), "/var/www/html/img/" + "照片3," + "线路名称："+d.getLinename() + "," + "耗时：" + d.getTimeconsume() + "," +"工作内容："+ d.getWorkingContent());
                    imageDownUtil.moveFile("/root/img/" + d.getPic4(), "/var/www/html/img/" + "照片4," + "线路名称："+d.getLinename() + "," + "耗时：" + d.getTimeconsume() + "," +"工作内容："+ d.getWorkingContent());
                    imageDownUtil.moveFile("/root/img/" + d.getPic5(), "/var/www/html/img/" + "照片5," + "线路名称："+d.getLinename() + "," + "耗时：" + d.getTimeconsume() + "," +"工作内容："+ d.getWorkingContent());
                }
            }catch (Exception e){

            }

        }
        imageDownUtil.tarFile();
        response.sendRedirect("http://106.15.200.245/img.tar");

    }


    @RequestMapping("/importExcel")
    public Object importExcel(String token,@RequestParam("file") MultipartFile multipartFile) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        List<Device_Track_MaintanceEntity> deviceTrackMaintanceEntityList = ExcelImportUtil
                .importExcel(multipartFile.getInputStream(), Device_Track_MaintanceEntity.class, importParams);
        for (Device_Track_MaintanceEntity d:
                deviceTrackMaintanceEntityList) {
            System.out.println("natural");
            System.out.println(d.getId());

            Device_Track_MaintanceEntity tmp = deviceTrackMaintanceEntityMapper.selectById(String.valueOf(d.getId()));
            if(tmp!=null){
                deviceTrackMaintanceEntityMapper.updateRecordById(d);
            }else {
                deviceTrackMaintanceEntityMapper.insert(d);
            }
        }
        return "OK";
    }

    @RequestMapping("/updateRec")
    public Object updateRec(@RequestBody Device_Track_MaintanceEntity d){
        deviceTrackMaintanceEntityMapper.updateRecordByFront(d);

        return "OK";

    }



    

}
