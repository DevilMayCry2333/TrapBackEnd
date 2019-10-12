package cn.huihongcloud.controller.newqrcode;

import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.NewQrCodeMapper;
import cn.huihongcloud.mapper.UserMapper;
import cn.huihongcloud.util.DistUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/newQrCode")
public class NewQrCode {
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
    public Object getgetMaxAvableCode(@RequestParam String adcode,@RequestParam String appVal){
        System.out.println(adcode);
        System.out.println(appVal);
        String app = "";
        switch (Integer.parseInt(appVal)){
            case 1:
                app = "诱捕器管理";
                break;
            case 2:
                app = "注干剂监测";
                break;
            case 3:
                app = "天敌防治";
                break;
            case 4:
                app = "枯死树采伐";
                break;
            case 5:
                app = "轨迹追踪";
                break;
        }
        List<Device> device = newQrCodeMapper.getMaxAvaDevice(adcode,app);

        return device.get(0).getId();
//        return "OK";
    }

    @RequestMapping("/assignQRCode")
    public JSONObject assignCode(@RequestParam String proxyCode,@RequestParam String cityCode,@RequestParam String areaCode,@RequestParam String projectCode,@RequestParam String startID,@RequestParam String endID){
//        List<User> cityUser = newQrCodeMapper.getCity(cityCode);
//        List<User> areaUser = newQrCodeMapper.getArea(areaCode);
//        List<User> proxyUser = newQrCodeMapper.getProxyByCode(proxyCode);
        String mydist[] = distUtil.getNames(areaCode,null);
        System.out.println(mydist[0]);
        System.out.println(mydist[1]);
        System.out.println(mydist[2]);

        String []project = {"诱捕器管理","注干剂监测","天敌防治","枯死树采伐","轨迹追踪"};
        int switchProject = Integer.parseInt(projectCode);


        long startIDInt = Long.parseLong(startID);
        long endIDInt = Long.parseLong(endID);
        for (long i = startIDInt; i <= endIDInt; i++) {
            newQrCodeMapper.insertDevice(String.valueOf(i),mydist[0],mydist[1],mydist[2],project[switchProject-1],areaCode);
        }
        res.put("Res",true);

        return res;

    }

    @RequestMapping("/assignCodeByManager")
    public JSONObject assignCodeByManager(@RequestParam String startID,@RequestParam String endID,
                                          @RequestParam int IDNum,@RequestParam int applicationValue,
                                          @RequestParam String customRegion,@RequestParam String prefix,
                                          @RequestParam String serialStart,@RequestParam String serialEnd,
                                          @RequestParam int serialNum,
                                          @RequestParam String username)
    {
        System.out.println(username);
        User user = userMapper.getUserByUserName(username);

        for (long i = Long.parseLong(startID),j=0; i <= Long.parseLong(endID); i++,j++) {
            newQrCodeMapper.assginDeviceByManager(i,customRegion,prefix,Long.parseLong(serialStart)+j,user.getParent(),username);
        }
        res.put("Res",true);
        return res;
    }

    @RequestMapping("/search")
    public Object serach(@RequestParam String colName,@RequestParam String searchText){
        List<Device> deviceList = newQrCodeMapper.selectByConditions(colName, searchText);
        Page<Object> pages = null;
        PageWrapper pageWrapper = new PageWrapper();
        pages = PageHelper.startPage(1, 1000000);

        pageWrapper.setData(deviceList);
        pageWrapper.setTotalPage(pages.getPages());
        pageWrapper.setCurrentPage(1);
        pageWrapper.setTotalNum(deviceList.size());
        return pageWrapper;
    }

}
