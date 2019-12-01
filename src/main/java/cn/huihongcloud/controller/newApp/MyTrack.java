package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import cn.huihongcloud.entity.Device_Track_MaintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.*;
import cn.huihongcloud.service.DeviceService;
import com.fasterxml.jackson.core.JsonParser;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app")
public class MyTrack {
    private static final Logger logger = LoggerFactory.getLogger(MyTrack.class);

    @Autowired
    DeviceBeetleMapper deviceBeetleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    DeviceService deviceService;
    @Autowired
    Device_Track_MaintanceEntityMapper deviceTrackMaintanceEntityMapper;


    @RequestMapping("/AddPhoto")
    public Object addPhoto(@RequestAttribute("username") String username,
                           @RequestParam(required = false) MultipartFile image,
                           String lineName,
                           int current
                           ){

        System.out.println(username);
        System.out.println(image);
        System.out.println(lineName);
        System.out.println(current);
        User user = userMapper.getUserByUserName(username);


        if (image!=null) {
            String imgId = deviceService.saveImg(image, lineName, username);
            if(current==1) {
                deviceTrackMaintanceEntityMapper.updatePic(lineName,"Pic1",imgId,user.getParent());
            }else if(current==2){
                deviceTrackMaintanceEntityMapper.updatePic(lineName,"Pic2",imgId,user.getParent());

            }else if(current==3){
                deviceTrackMaintanceEntityMapper.updatePic(lineName,"Pic3",imgId,user.getParent());

            }else if(current==4){
                deviceTrackMaintanceEntityMapper.updatePic(lineName,"Pic4",imgId,user.getParent());
            }else if(current==5){
                deviceTrackMaintanceEntityMapper.updatePic(lineName,"Pic5",imgId,user.getParent());
            }
        }


        return "OK";

    }


    @RequestMapping("/AddPhoto2")
    public Object addPhoto2(@RequestAttribute("username") String username,
                           @RequestParam(required = false) MultipartFile image,
                           String lineName,
                           @RequestParam(required = false) Integer current,
                            @RequestParam(required = false) Integer allLength,
                            @RequestParam(required = false) Integer curRow,
                            String longtitudeData,
                            String latitudeData,
                            String altitudeData,
                            String workContent,
                            String lateIntravl,
                            String remarks,
                            @RequestParam(required = false) String recordTime
    ) throws ParseException {
        logger.info(lineName);
        logger.info(recordTime);

        System.out.println(username);
        System.out.println(image);
        System.out.println(lineName);
        System.out.println(current);
        String []longData = {"1"};
        String []latData = {"2"};
        String []altData = {"3"};
        try {
            longData = longtitudeData.split(",");
            latData = latitudeData.split(",");
            altData = altitudeData.split(",");
        }catch (Exception e){

        }

        User user = userMapper.getUserByUserName(username);
        org.json.simple.JSONObject jsonObject2 = new org.json.simple.JSONObject();
        if(recordTime!=null){
            jsonObject2 = (org.json.simple.JSONObject) new JSONParser().parse(recordTime);
        }else {
            jsonObject2.put("startTime","2019-12-01 00:00:00");
            jsonObject2.put("endTime","2019-12-01 00:00:00");
        }

        Device_Track_MaintanceEntity device_track_maintanceEntity = new Device_Track_MaintanceEntity();
        device_track_maintanceEntity.setLinename(lineName);
        device_track_maintanceEntity.setUsername(user.getParent());
        device_track_maintanceEntity.setWorker(username);
        device_track_maintanceEntity.setLongtitudeCollect(longtitudeData);
        device_track_maintanceEntity.setLatitudeCollect(latitudeData);
        device_track_maintanceEntity.setAltitudeCollect(altitudeData);
        device_track_maintanceEntity.setWorkingContent(workContent);
        device_track_maintanceEntity.setRemarks(remarks);
        device_track_maintanceEntity.setReported(0);

        device_track_maintanceEntity.setAdcode(user.getAdcode());
        device_track_maintanceEntity.setStarttime(String.valueOf(jsonObject2.get("startTime")));
        device_track_maintanceEntity.setEndtime(String.valueOf(jsonObject2.get("endTime")));

        String startTime = String.valueOf(jsonObject2.get("startTime"));
        String endTime = String.valueOf(jsonObject2.get("endTime"));

        System.out.println(startTime);
        System.out.println(endTime);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos2 = new ParsePosition(0);

        Date start = formatter.parse(startTime, pos);

        Date end = formatter2.parse(endTime, pos2);

        System.out.println(start.getTime());

        System.out.println(end.getTime());

        System.out.println(end.getTime() - start.getTime());

        device_track_maintanceEntity.setTimeconsume(String.valueOf( ( end.getTime() - start.getTime() ) / 1000 ) );

        device_track_maintanceEntity.setStartpoint(longData[0] + "," + latData[0] + "," + altData[0]);
        device_track_maintanceEntity.setEndpoint(longData[longData.length-1] + "," + latData[latData.length-1] + "," + altData[altData.length-1]);

        JSONObject jsonObject = new JSONObject();

        if (image!=null) {
            String imgId = deviceService.saveImg2(image,null,null,username,current,null,device_track_maintanceEntity,6,user.getParent(),null,lineName);
//            if(current==1) {
//                deviceTrackMaintanceEntityMapper.updatePic(lineName,"Pic1",imgId,user.getParent());
//            }else if(current==2){
//                deviceTrackMaintanceEntityMapper.updatePic(lineName,"Pic2",imgId,user.getParent());
//
//            }else if(current==3){
//                deviceTrackMaintanceEntityMapper.updatePic(lineName,"Pic3",imgId,user.getParent());
//
//            }else if(current==4){
//                deviceTrackMaintanceEntityMapper.updatePic(lineName,"Pic4",imgId,user.getParent());
//            }else if(current==5){
//                deviceTrackMaintanceEntityMapper.updatePic(lineName,"Pic5",imgId,user.getParent());
//            }

            jsonObject.put("imgId",imgId);
            if(curRow!=null){
                if(curRow>=allLength-1){
                    jsonObject.put("isComp",true);
                }else {
                    jsonObject.put("isComp",false);
                }
            }
        }else {
            logger.info("无图片轨迹");
            jsonObject.put("isComp",true);
            device_track_maintanceEntity.setPic1("pic1");
            deviceTrackMaintanceEntityMapper.addMaintance(device_track_maintanceEntity);
        }
        return jsonObject;

    }


    @ApiOperation("上传维护信息")
    @PostMapping("/AddTrack")
    public Object addMaintenanceData(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String longtitudeData,
                                     String latitudeData,
                                     String altitudeData,
                                     String lineName,
                                     String workContent,
                                     String lateIntravl,
                                     String remarks,
                                     int current,
                                     String recordTime,
                                     HttpServletResponse response) throws Exception {

        logger.info("===开始记录数据===");
        logger.info(username);
        logger.info(String.valueOf(longtitudeData));
        logger.info(String.valueOf(latitudeData));
        logger.info(String.valueOf(altitudeData));
        logger.info(String.valueOf(lineName));
        logger.info(String.valueOf(workContent));
        logger.info(lateIntravl);
        logger.info(remarks);
        logger.info(recordTime);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) new JSONParser().parse(recordTime);

        System.out.println("image" + image);
        Device_Track_MaintanceEntity deviceTrackMaintanceEntity = new Device_Track_MaintanceEntity();

        String []longData = longtitudeData.split(",");
        String []latData = latitudeData.split(",");
        String []altData = altitudeData.split(",");

        System.out.println(longData);
        System.out.println(latData);
        System.out.println(altData);
        User user = userMapper.getUserByUserName(username);
        User user1 = userMapper.getUserByUserName(user.getParent());
        System.out.println("USername");


        deviceTrackMaintanceEntity.setLongtitudeCollect(longtitudeData);
        deviceTrackMaintanceEntity.setWorker(username);
        deviceTrackMaintanceEntity.setReported(0);
        deviceTrackMaintanceEntity.setAltitudeCollect(altitudeData);
        deviceTrackMaintanceEntity.setLatitudeCollect(latitudeData);
        deviceTrackMaintanceEntity.setLinename(lineName);
        deviceTrackMaintanceEntity.setWorkingContent(workContent);
        deviceTrackMaintanceEntity.setRemarks(remarks);
        deviceTrackMaintanceEntity.setUsername(user1.getUsername());
        deviceTrackMaintanceEntity.setAdcode(user1.getAdcode());
        deviceTrackMaintanceEntity.setStarttime(String.valueOf(jsonObject.get("startTime")));
        deviceTrackMaintanceEntity.setEndtime(String.valueOf(jsonObject.get("endTime")));

        String startTime = String.valueOf(jsonObject.get("startTime"));
        String endTime = String.valueOf(jsonObject.get("endTime"));

        System.out.println(startTime);
        System.out.println(endTime);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos2 = new ParsePosition(0);

        Date start = formatter.parse(startTime, pos);

        Date end = formatter2.parse(endTime, pos2);

        System.out.println(start.getTime());

        System.out.println(end.getTime());

        System.out.println(end.getTime() - start.getTime());

        deviceTrackMaintanceEntity.setTimeconsume(String.valueOf( ( end.getTime() - start.getTime() ) / 1000 ) );

        deviceTrackMaintanceEntity.setStartpoint(longData[0] + "," + latData[0] + "," + altData[0]);
        deviceTrackMaintanceEntity.setEndpoint(longData[longData.length-1] + "," + latData[latData.length-1] + "," + altData[altData.length-1]);

        deviceTrackMaintanceEntityMapper.updateMaintance(deviceTrackMaintanceEntity);

        return Result.ok();
        //return null;
    }


    @ApiOperation("上传维护信息")
    @PostMapping("/AddTrack2")
    public Object addMaintenanceData2(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String longtitudeData,
                                     String latitudeData,
                                     String altitudeData,
                                     String lineName,
                                     String workContent,
                                     String lateIntravl,
                                     String remarks,
                                     int current,
                                     String recordTime,
                                     HttpServletResponse response) throws Exception {

        logger.info("===开始记录数据===");
        logger.info(username);
        logger.info(String.valueOf(longtitudeData));
        logger.info(String.valueOf(latitudeData));
        logger.info(String.valueOf(altitudeData));
        logger.info(String.valueOf(lineName));
        logger.info(String.valueOf(workContent));
        logger.info(lateIntravl);
        logger.info(remarks);
        logger.info(recordTime);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) new JSONParser().parse(recordTime);

        System.out.println("image" + image);
        Device_Track_MaintanceEntity deviceTrackMaintanceEntity = new Device_Track_MaintanceEntity();

        String []longData = longtitudeData.split(",");
        String []latData = latitudeData.split(",");
        String []altData = altitudeData.split(",");

        System.out.println(longData);
        System.out.println(latData);
        System.out.println(altData);
        User user = userMapper.getUserByUserName(username);
        User user1 = userMapper.getUserByUserName(user.getParent());
        System.out.println("USername");


        deviceTrackMaintanceEntity.setLongtitudeCollect(longtitudeData);
        deviceTrackMaintanceEntity.setWorker(username);
        deviceTrackMaintanceEntity.setAltitudeCollect(altitudeData);
        deviceTrackMaintanceEntity.setLatitudeCollect(latitudeData);
        deviceTrackMaintanceEntity.setLinename(lineName);
        deviceTrackMaintanceEntity.setWorkingContent(workContent);
        deviceTrackMaintanceEntity.setRemarks(remarks);
        deviceTrackMaintanceEntity.setReported(0);
        deviceTrackMaintanceEntity.setUsername(user1.getUsername());
        deviceTrackMaintanceEntity.setAdcode(user1.getAdcode());
        deviceTrackMaintanceEntity.setStarttime(String.valueOf(jsonObject.get("startTime")));
        deviceTrackMaintanceEntity.setEndtime(String.valueOf(jsonObject.get("endTime")));

        String startTime = String.valueOf(jsonObject.get("startTime"));
        String endTime = String.valueOf(jsonObject.get("endTime"));

        System.out.println(startTime);
        System.out.println(endTime);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos2 = new ParsePosition(0);

        Date start = formatter.parse(startTime, pos);

        Date end = formatter2.parse(endTime, pos2);

        System.out.println(start.getTime());

        System.out.println(end.getTime());

        System.out.println(end.getTime() - start.getTime());

        deviceTrackMaintanceEntity.setTimeconsume(String.valueOf( ( end.getTime() - start.getTime() ) / 1000 ) );

        deviceTrackMaintanceEntity.setStartpoint(longData[0] + "," + latData[0] + "," + altData[0]);
        deviceTrackMaintanceEntity.setEndpoint(longData[longData.length-1] + "," + latData[latData.length-1] + "," + altData[altData.length-1]);

        deviceTrackMaintanceEntityMapper.addMaintance(deviceTrackMaintanceEntity);

        return Result.ok();
        //return null;
    }



    @RequestMapping("/deleteTrackById")
    public Object deleteTrackById(@RequestParam String id){
        return deviceTrackMaintanceEntityMapper.deleteById(id);
    }

    @RequestMapping("/TestReadBase64")
    public Object TestReadBase64(@RequestParam String[] base64){
        for (int i = 0; i < base64.length; i++) {
            System.out.println(base64[i]);
        }
        return "OK";
    }

    @RequestMapping("/addLineName")
    public Object addLineName(@RequestAttribute("username") String username,@RequestParam String linename){


        System.out.println(username);
        System.out.println(linename);
        User user = userMapper.getUserByUserName(username);
        Device_Track_MaintanceEntity deviceTrackMaintanceEntity = new Device_Track_MaintanceEntity();
        deviceTrackMaintanceEntity.setLinename(linename);
        deviceTrackMaintanceEntity.setUsername(user.getParent());
        deviceTrackMaintanceEntityMapper.addMaintance(deviceTrackMaintanceEntity);
        return "OK";
    }



}
