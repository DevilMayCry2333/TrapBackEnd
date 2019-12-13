package cn.huihongcloud.controller.newApp;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceBeetleMapper;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.mapper.UserMapper;
import cn.huihongcloud.util.ImageDownUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app")
public class Trap {
    @Autowired
    DeviceBeetleMapper deviceBeetleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @RequestMapping("/getBeetle")
    public Object getBeetle(@RequestParam String username){
        User user = userMapper.getUserByUserName(username);
        String adcode = user.getAdcode();
        System.out.println(adcode);
        System.out.println(user.getUsername());
        return deviceBeetleMapper.getBeetleInfoByArea(adcode);
    }

    @RequestMapping("/getInject")
    public Object getInject(@RequestParam String username){
        User user = userMapper.getUserByUserName(username);
        return deviceBeetleMapper.getInjectTypeByArea(user.getAdcode());
    }

    @RequestMapping("/getWorkContent")
    public Object getWorkContent(@RequestParam String username){
        User user = userMapper.getUserByUserName(username);
        return deviceBeetleMapper.getWorkContentByArea(user.getAdcode());
    }

    @RequestMapping("/getMyDevice")
    public Object getMyDevice(@RequestParam String worker){
        User user = userMapper.getUserByUserName(worker);
        User user1 = userMapper.getUserByUserName(user.getParent());
        User user2 = userMapper.getUserByUserName(user1.getParent());
        String projectName = user2.getUsername();
        return deviceMapper.getDeviceByCustomProject(projectName);
    }

    @RequestMapping("/TrapWorker")
    public List<DeviceMaintenance> worker(@RequestParam String scanId){

        return deviceBeetleMapper.getTrapById(scanId);


    }

    @RequestMapping("/getTrapDetail")
    public Object Fuck(@RequestParam(required = false) String colName,
                                        @RequestParam int page,
                                        @RequestParam int limit,
                                        @RequestParam(required = false) String searchText,
                                        @RequestParam String username,
                        @RequestParam(required = false) String startDate,
                       @RequestParam(required = false) String endDate){


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);

        String dateString = null;



        if(endDate!=null && endDate!="") {
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




        User user = userMapper.getUserByUserName(username);
        List<DeviceMaintenance> deviceMaintenanceList = null;
        System.out.println(startDate);
        PageWrapper pageWrapper = new PageWrapper();
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        if(user.getRole()==4){
            deviceMaintenanceList = deviceBeetleMapper.getFuckFuckByCustomProject(colName,searchText,user.getParent(),startDate,dateString);
        }else {
            deviceMaintenanceList = deviceBeetleMapper.getFuckFuck(colName,searchText,user.getAdcode(),startDate,dateString);
        }

        pageWrapper.setData(deviceMaintenanceList);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;

    }

    @RequestMapping("/exportExcelTrap")
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
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(colName);
        System.out.println(searchText);
        List<DeviceMaintenance> deviceNaturalEnemiesMaintanceEntities  = deviceBeetleMapper.selectByDateAndColSearch(username,startDate,endDate,colName,searchText,adcode);
        for (DeviceMaintenance d:
             deviceNaturalEnemiesMaintanceEntities) {

            String pattern="yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf= new SimpleDateFormat(pattern);
            String datestr=sdf.format(d.getDate());// format  为格式化方法
            d.setRealdate(datestr);

        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("诱捕器管理情况明细表", "诱捕器管理情况明细表"), DeviceMaintenance.class, deviceNaturalEnemiesMaintanceEntities);
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

        List<DeviceMaintenance> deviceMaintenances = deviceBeetleMapper.selectByDateAndColSearch(username,startDate,endDate,colName,searchText,adcode);
        ImageDownUtil imageDownUtil = new ImageDownUtil();
        for (DeviceMaintenance d:deviceMaintenances) {
            try {
                String tmp = d.getImgId();
                imageDownUtil.moveFile("/root/img/" + tmp, "/var/www/html/img/" + "编号："+ d.getCustomSerial()+ "," + "区域：" + d.getCustomTown() + "," +"设备ID："+ d.getScanId()+ "," + "批次："+d.getBatch());
            }catch (Exception e){

            }

        }
        imageDownUtil.tarFile();
        response.sendRedirect("http://106.15.200.245/img.tar");
    }




}
