package cn.huihongcloud.controller.newqrcode;

import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.NewQrCodeMapper;
import cn.huihongcloud.mapper.UserMapper;
import cn.huihongcloud.util.DistUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xpath.internal.objects.XObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/newQrCode")
public class NewQrCode {
    //搜索19，魔术数字

    @Autowired
    NewQrCodeMapper newQrCodeMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private DistUtil distUtil;


    JSONObject res = new JSONObject();

    @RequestMapping("/getProxy")
    public JSONObject getProxy(){
        JSONArray data = new JSONArray();
        List<User> userList = newQrCodeMapper.getProxy();
        for (User u: userList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("label",u.getUsername());
            jsonObject.put("value",u.getAdcode());
            jsonObject.put("adcode",u.getAdcode());
            data.add(jsonObject);
        }
        res.put("Data",data);
        res.put("Res",true);
        return res;
    }

    @RequestMapping("/getCity")
    public JSONObject getCity(@RequestParam String adcode){
        JSONArray data = new JSONArray();
        List<User> userList = newQrCodeMapper.getCity(adcode);
        for (User u: userList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("label",u.getUsername());
            jsonObject.put("value",u.getAdcode());
            jsonObject.put("adcode",u.getAdcode());
            data.add(jsonObject);
        }
        res.put("Data",data);
        res.put("Res",true);
        return res;
    }
    @RequestMapping("/getArea")
    public JSONObject getArea(@RequestParam String adcode){
        JSONArray data = new JSONArray();
        List<User> userList = newQrCodeMapper.getArea(adcode);
        for (User u: userList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("label",u.getUsername());
            jsonObject.put("value",u.getAdcode());
            jsonObject.put("adcode",u.getAdcode());
            data.add(jsonObject);
        }
        res.put("Data",data);
        res.put("Res",true);
        return res;
    }

    @RequestMapping("/getMaxAvableCode")
    public Object getgetMaxAvableCode(@RequestParam String provinceCode){
        System.out.println(provinceCode);
        List<User> proxyUser = newQrCodeMapper.getProxyByCode(provinceCode);

        List<Device> device = newQrCodeMapper.getMaxAvaDevice(proxyUser.get(0).getProvince());

        return device.get(0).getScanId();
//        return "OK";
    }

    @RequestMapping("/getMaxCodeByProvince")
    public Object getMaxCodeByAreaAndProject(@RequestParam String username){
        User user = userMapper.getUserByUserName(username);
        List<Device> deviceList = newQrCodeMapper.getMaxScanId(user.getProvince());
        return deviceList.get(0).getScanId();

    }

    @RequestMapping("/reAssignQRCode")
    public Object reAssignQRCode(@RequestParam String customSerial,@RequestParam String scanId,@RequestParam String customProject){
        newQrCodeMapper.deleteScanId(scanId);
        newQrCodeMapper.updateScanId(customProject,customSerial, scanId);
        return "OK";
    }

    @RequestMapping("/assignQRCode")
    public JSONObject assignCode(@RequestParam String proxyCode,@RequestParam String startID,@RequestParam String endID){
//        List<User> cityUser = newQrCodeMapper.getCity(cityCode);
//        List<User> areaUser = newQrCodeMapper.getArea(areaCode);
        List<User> proxyUser = newQrCodeMapper.getProxyByCode(proxyCode);
//        String mydist[] = distUtil.getNames(areaCode,null);
//        System.out.println(mydist[0]);
//        System.out.println(mydist[1]);
//        System.out.println(mydist[2]);
//
//        String []project = {"诱捕器管理","注干剂监测","天敌防治","枯死树采伐","药剂防治管理"};
//        int switchProject = Integer.parseInt(projectCode);

        long startIDInt = Long.parseLong(startID);
        long endIDInt = Long.parseLong(endID);
        for (long i = startIDInt; i <= endIDInt; i++) {
            newQrCodeMapper.insertDevice(String.valueOf(i),proxyUser.get(0).getProvince());
        }
        res.put("Res",true);

        return res;

    }

    @RequestMapping("/assignCodeByManager")
    public JSONObject assignCodeByManager(@RequestParam String startScanID,@RequestParam String endScanID,
                                          @RequestParam int IDNum,@RequestParam int applicationValue,
                                          @RequestParam String customRegion,@RequestParam String prefix,
                                          @RequestParam String serialStart,@RequestParam String serialEnd,
                                          @RequestParam int serialNum,
                                          @RequestParam String username) throws InterruptedException {
        System.out.println(username);
        User user = userMapper.getUserByUserName(username);
        System.out.println(user.getAdcode());

        String mydist[] = distUtil.getNames(user.getAdcode(),null);
        System.out.println(mydist[0]);
        System.out.println(mydist[1]);
        System.out.println(mydist[2]);

        try {
            String devicePrefix = user.getAdcode() + "19" + applicationValue;

            for (long i = Long.parseLong(startScanID),j=0; i <= Long.parseLong(endScanID); i++,j++) {
                List<Device> device = newQrCodeMapper.getMaxDeviceId(devicePrefix, String.valueOf(applicationValue));
                System.out.println("=====1=");
                System.out.println(device.get(0).getId());
                System.out.println("=====1=");
                newQrCodeMapper.assginDeviceByManager(String.valueOf(applicationValue),Long.parseLong(device.get(0).getId()) + 1,i,customRegion,prefix,Long.parseLong(serialStart)+j,user.getParent(),username,mydist[1],mydist[2],user.getAdcode());
//                Thread.sleep(5 * 100);
            }
            res.put("Res",true);

        }catch (Exception e){
            String deviceId = user.getAdcode() +"19" + applicationValue +  "000001";

            String devicePrefix = user.getAdcode() + "19" + applicationValue;


            newQrCodeMapper.assginDeviceByManager(String.valueOf(applicationValue),Long.parseLong(deviceId),Long.parseLong(startScanID),customRegion,prefix,Long.parseLong(serialStart)+0,user.getParent(),username,mydist[1],mydist[2],user.getAdcode());
            for (long i = Long.parseLong(startScanID) + 1,j=1; i <= Long.parseLong(endScanID); i++,j++) {
                List<Device> device = newQrCodeMapper.getMaxDeviceId(devicePrefix, String.valueOf(applicationValue));
                System.out.println("======");
                System.out.println(device.get(0).getId());
                System.out.println("======");
                newQrCodeMapper.assginDeviceByManager(String.valueOf(applicationValue),Long.parseLong(device.get(0).getId()) + 1,i,customRegion,prefix,Long.parseLong(serialStart)+j,user.getParent(),username,mydist[1],mydist[2],user.getAdcode());
//                Thread.sleep(5 * 100);
            }
            res.put("Res",true);
        }

        return res;
    }



    @RequestMapping("/rootSearch")
    public Object serach(@RequestParam(required = false) String colName,@RequestParam(required = false) String searchText,Integer page,Integer limit){


        int allNum = newQrCodeMapper.countByCond1(colName, searchText);

        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<Device> deviceList = newQrCodeMapper.selectByConditions1(colName, searchText,page*limit-limit,limit);

        PageWrapper pageWrapper = new PageWrapper();

        pageWrapper.setData(deviceList);
        System.out.println(pageObject.getPages());
        System.out.println(pageObject.getTotal());

        pageWrapper.setCurrentPage(page);
//        pageWrapper.setTotalNum(allNum);
//        pageWrapper.setTotalPage(allNum/limit);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());


        return pageWrapper;
    }


    @RequestMapping("/usertosearch")
    public Object serach(@RequestAttribute("username") String username,@RequestParam(required = false) String colName, @RequestParam(required = false) String searchText, int page, int limit){

        //User user = userMapper.getUserByUserName(username);
        System.out.println(username);


//        int allNum = newQrCodeMapper.countByCond(username,colName, searchText);

        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<Device> deviceList = newQrCodeMapper.selectByConditions(username,colName, searchText,page*limit-limit,limit);

        PageWrapper pageWrapper = new PageWrapper();

        pageWrapper.setData(deviceList);
        System.out.println(pageObject.getPages());
        System.out.println(pageObject.getTotal());

        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }

    @RequestMapping("/getavailableNum")
    public Object serach(@RequestParam String province ){
        System.out.println(province);
        List<User> users = userMapper.getProvinceByCode(province);    //获取省级用户名字
        String ProvinceName = users.get(0).getProvince();
        int availabID = newQrCodeMapper.countByNOassign("0",ProvinceName);
        System.out.println(availabID);
        return availabID;
    }

}
