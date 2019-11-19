package cn.huihongcloud.service;

import cn.huihongcloud.component.BDComponent;
import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import cn.huihongcloud.entity.bd.BDInfo;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.device.DeviceImg;
import cn.huihongcloud.entity.device.DeviceOutput;
import cn.huihongcloud.entity.device.DeviceOutputManager;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceImgMapper;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.mapper.Device_DeadTrees_maintanceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
/**
 * Created by 钟晖宏 on 2018/6/28
 */
@Service
public class DeviceService {

    @Autowired
    private BDComponent bdComponent;

    @Autowired
    Device_DeadTrees_maintanceEntityMapper deviceDeadTreesMaintanceEntityMapper;


    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DeviceImgMapper deviceImgMapper;

    @Autowired
    private UserService userService;

    @Value("${device.img.path}")
    private String IMG_PATH;

    public Device packDevice(Double latitude, Double longitude, String deviceId) {
        Device device = new Device();
        device.setId(deviceId);
        BDInfo bdInfo = bdComponent.parseLocation(latitude, longitude);
        device.setLatitude(latitude);
        device.setLongitude(longitude);
        device.setAdcode(bdInfo.getResult().getAddressComponent().getAdcode());
        device.setProvince(bdInfo.getResult().getAddressComponent().getProvince());
        device.setCity(bdInfo.getResult().getAddressComponent().getCity());
        device.setArea(bdInfo.getResult().getAddressComponent().getArea());
        device.setTown(bdInfo.getResult().getAddressComponent().getTown());
        return device;
    }

    /**
     * 根据用户名获取下属的设备
     *
     * @param username 用户名
     * @return 设备列表
     */
    public List<Device> getDeviceByMap(String username) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceMapper.getDeviceByMap(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    /**
     * 根据地区获取设备
     *
     * @param adcode     地区代码
     * @param town       乡
     * @param searchText 搜索条件
     * @return 设备列表
     */
    public List<Device> getDeviceByLocation(String adcode, String town, String searchText) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceMapper.getDeviceByLocation(adcode, town, searchText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    /**
     * 获取管理员下属的设备
     *
     * @param manager 管理员用户名
     * @return 设备列表
     */
    public List<Device> getDeviceByManager(String manager) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceMapper.getDeviceByManager(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    public List<Device> getDeviceandWorkerByManager(String manager) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceMapper.getDeviceandWorkerByManager(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    public List<Device> getDeviceByWorker(String worker) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceMapper.getDeviceByWorker(worker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }


    /**
     * 增加设备关联
     *
     * @param deviceId 设备ID
     * @param username 用户名
     * @return 是否关联成功
     */
    public Boolean addDeviceRelation(String deviceId, String username) {
        Boolean result = false;
        try {
            int n = deviceMapper.addDeviceRelation(deviceId, username);
            if (n == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除设备关联
     *
     * @param deviceId 设备ID
     * @param username 用户名
     * @return 是否关联成功
     */
    public Boolean deleteDeviceRelation(String deviceId, String username) {
        Boolean result = false;
        try {
            int n = deviceMapper.deleteDeviceRelation(deviceId, username);
            if (n == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取未与当前用户关联且可关联的设备
     *
     * @param worker     工人用户名
     * @param searchText 搜索条件
     * @return 设备列表
     */
    public List<Device> getDeviceNotAssociatedWithWorker(String worker, String searchText) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceMapper.getDeviceNotAssociatedWithWorker(worker, searchText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    public List<String> getDeviceIdsAssociatedWithWorker(String worker) {
        List<String> deviceIdList = null;
        try {
            deviceIdList = deviceMapper.getDeviceIdsAssociatedWithWorker(worker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceIdList;
    }

    public String getWorkerAssociatedWithDeviceIds(String device_id) {
        String a = "";
        try {
            a = deviceMapper.getWorkerAssociatedWithDeviceIds(device_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 判断数据库中是否存在该设备
     *
     * @param id 设备ID
     * @return 是否存在
     */
    public Boolean isExist(String id) {
        Boolean result = false;
        try {
            result = deviceMapper.isExist(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新设备
     *
     * @param device 设备对象
     * @return 是否成功
     */
    public Boolean updateDevice(Device device) {
        Boolean result = false;
        try {
            int n = deviceMapper.updateDevice(device);
            if (n == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean updateDevice1(Device device) {
        Boolean result = false;
        try {
            int n = deviceMapper.updateDevice1(device);
            if (n == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 增加设备
     *
     * @param device
     * @return
     */
    public Boolean addDevice(Device device) {
        Boolean result = false;
        try {
            int n = deviceMapper.addDevice(device);
            if (n == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取以乡为单位的聚合点
     *
     * @param adcode
     * @param town
     * @return
     */
    public List<Device> getTownSpotDeviceByLocation(String adcode, String town) {
        List<Device> devices = null;
        try {
            devices = deviceMapper.getTownSpotDeviceByLocation(adcode, town);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devices;
    }

    /**
     * 获取以县为单位的聚合点
     *
     * @param adcode
     * @return
     */
    public List<Device> getAreaSpotDeviceByLocation(String adcode) {
        List<Device> devices = null;
        try {
            devices = deviceMapper.getAreaSpotDeviceByLocation(adcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devices;
    }

    /**
     * 获取以市为单位的聚合点
     *
     * @param adcode
     * @return
     */
    public List<Device> getCitySpotDeviceByLocation(String adcode) {
        List<Device> devices = null;
        try {
            if (adcode != null) {
//                if (adcode.)
//                devices = deviceMapper.getCitySpotDeviceByLocation(adcode.substring(0, 4));
                devices = deviceMapper.getCitySpotDeviceByLocation(adcode);
            } else
                devices = deviceMapper.getCitySpotDeviceByLocation(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devices;
    }

    /**
     * 获取以省为单位的聚合点
     *
     * @param adcode
     * @return
     */
    public List<Device> getProvinceSpotDeviceByLocation(String adcode) {
        List<Device> devices = null;
        try {
            if (adcode != null)
                devices = deviceMapper.getProvinceSpotDeviceByLocation(adcode.substring(0, 2));
            else
                devices = deviceMapper.getProvinceSpotDeviceByLocation(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devices;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * 生成设备码
     *
     * @param username
     * @return
     */
    public String generateDeviceCode(String username) {
        UUID uuid = UUID.randomUUID();
        MessageDigest messageDigest;
        String encodeStr = "";
        String postFix = username + new Date().getTime();
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(postFix.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return uuid + "-" + encodeStr;
    }

    /**
     * 保存上传的图片
     *
     * @param multipartFile
     * @param deviceId
     * @param username
     * @return
     */
    public String saveImg(MultipartFile multipartFile, String deviceId, String username) {
        UUID uuid = UUID.randomUUID();
        System.out.println("Save");
        System.out.println(IMG_PATH);

        MessageDigest messageDigest;
        String encodeStr = "";
        String postFix = username + new Date().getTime();
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(postFix.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String suffix = multipartFile.getOriginalFilename()
                .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String imgName = uuid.toString() + "-" + encodeStr + suffix;
        File filePath = new File(IMG_PATH);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File outfile = new File(IMG_PATH + imgName);
        try {
            multipartFile.transferTo(outfile);
            DeviceImg deviceImg = new DeviceImg();
            deviceImg.setDate(new Date());
            deviceImg.setDeviceId(deviceId);
            deviceImg.setImgName(imgName);
            deviceImg.setUsername(username);
            deviceImgMapper.insert(deviceImg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return imgName;
    }


    /**
     * 保存上传的图片
     *
     * @param multipartFile
     * @param deviceId
     * @param username
     * @return
     */
    public String saveImg2(MultipartFile multipartFile, String deviceId, String scanId,String username,
                           int current,Device_DeadTrees_maintanceEntity deviceDeadTreesMaintanceEntity,
                           int project,
                           String userParent,
                           Integer maxBatch) {
        UUID uuid = UUID.randomUUID();
        System.out.println("Save");
        System.out.println(IMG_PATH);

        MessageDigest messageDigest;
        String encodeStr = "";
        String postFix = username + new Date().getTime();
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(postFix.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String suffix = multipartFile.getOriginalFilename()
                .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String imgName = uuid.toString() + "-" + encodeStr + suffix;
        File filePath = new File(IMG_PATH);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File outfile = new File(IMG_PATH + imgName);
        try {
            multipartFile.transferTo(outfile);
            DeviceImg deviceImg = new DeviceImg();
            deviceImg.setDate(new Date());
            deviceImg.setDeviceId(deviceId);
            deviceImg.setScanId(scanId);
            deviceImg.setImgName(imgName);
            deviceImg.setUsername(username);
            deviceImg.setCurrentPic(String.valueOf(current));
            if(current==1){
                deviceDeadTreesMaintanceEntity.setPic(imgName);
//                deviceDeadTreesMaintanceEntityMapper.addMaintance(deviceDeadTreesMaintanceEntity);
            }else if(current==2){
//                deviceDeadTreesMaintanceEntityMapper.updatePic(deviceId,"Pic2",imgName,userParent,maxBatch);
//                deviceDeadTreesMaintanceEntityMapper.addMaintance(deviceDeadTreesMaintanceEntity);
            }else if(current==3){
//                deviceDeadTreesMaintanceEntityMapper.updatePic(deviceId,"Pic3",imgName,userParent,maxBatch);
//                deviceDeadTreesMaintanceEntityMapper.addMaintance(deviceDeadTreesMaintanceEntity);
            }
            deviceImgMapper.insert2(deviceImg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return imgName;
    }



    /**
     * 获取设备的图片列表
     *
     * @param deviceId
     * @return
     */
    public List<DeviceImg> getDeviceImgListByDeviceId(String deviceId) {
        List<DeviceImg> deviceImgList = null;
        try {
            deviceImgList = deviceImgMapper.selectByDeviceId(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceImgList;
    }

    /**
     * 检测数据库中是否存在该图片，防止恶意下载服务器上文件
     *
     * @param imgName
     * @return
     */
    public String getImgPath(String imgName) {
        String realImgName = deviceImgMapper.selectImgNameByImgName(imgName);
        if (realImgName != null) {
            return IMG_PATH + realImgName;
        } else {
            return "";
        }
    }

    public List<Object> getDeviceCountByUser(String username) {

        List<Object> ret = new ArrayList<>();
        User user = userService.getUserByUserName(username);

        if (user.getProvince() == null || user.getProvince().isEmpty())
            ret.add(deviceMapper.getDeviceCountByAdcode("province", user.getAdcode(), null));
        if (user.getCity() == null || user.getCity().isEmpty())
            ret.add(deviceMapper.getDeviceCountByAdcode("city", user.getAdcode(), null));
        if (user.getArea() == null || user.getArea().isEmpty())
            ret.add(deviceMapper.getDeviceCountByAdcode("area", user.getAdcode(), null));
        if (user.getTown() == null || user.getTown().isEmpty())
            ret.add(deviceMapper.getDeviceCountByAdcode("town", user.getAdcode(), null));
        if (user.getTown() != null) {
            ret.add(deviceMapper.getDeviceCountByAdcode("town", user.getAdcode(), user.getTown()));
        }
        return ret;


    }

    public Device getDeviceById(String id) {
        return deviceMapper.getDeviceById(id);
    }

    public List<Device> getDeviceInRegion(String adcode, String town) {
        return deviceMapper.getDeviceInRegion(adcode, town);
    }

    public Long countDeviceInRegion(String adcode, String town) {
        return deviceMapper.countDeviceInRegion(adcode, town);
    }

    public Long countDeviceInArea(String adcode) {
        return deviceMapper.countDeviceInArea(adcode);
    }

    public String getMaxDeviceIdInArea(String adcode) {
        return deviceMapper.getMaxDeviceIdInArea(adcode);
    }

    public boolean judgeDeviceRelation(String username, String deviceId) {
        return deviceMapper.judgeDeviceRelation(username, deviceId) > 0;
    }

    public List<String> getDeviceIdInTown(String townCode) {
        return deviceMapper.getDeviceIdInTown(townCode);
    }

    public List<String> getDeviceIdsCanAssociateWithWorker(String adcode, String manager) {
        return deviceMapper.getDeviceIdsCanAssociateWithWorker(adcode, manager);
    }
    public Device queryDeviceByDeviceid(String deviceId){
        return deviceMapper.queryDeviceByDeviceid(deviceId);
    }
    public Boolean clearWorkerDeviceRelation(String worker) {
        return deviceMapper.clearWorkerDeviceRelation(worker) > 0;
    }

    public Workbook exportQRCodeList(List<Device> list) {

        List<DeviceOutput> QRCodeList = new ArrayList<>();
        for (Device weather : list) {
            DeviceOutput weatherOutput = new DeviceOutput();
            BeanUtils.copyProperties(weather, weatherOutput);
            QRCodeList.add(weatherOutput);
        }

        return ExcelExportUtil.exportExcel(new ExportParams( "设备id信息", "设备id信息"), DeviceOutput.class, QRCodeList);

    }

    public Workbook exportQRCodeManagerList(List<Device> list) {

        List<DeviceOutputManager> QRCodeList = new ArrayList<>();
        for (Device weather : list) {
            DeviceOutputManager weatherOutput = new DeviceOutputManager();
            BeanUtils.copyProperties(weather, weatherOutput);
            QRCodeList.add(weatherOutput);
        }

        return ExcelExportUtil.exportExcel(new ExportParams( "设备id信息", "设备id信息"), DeviceOutputManager.class, QRCodeList);

    }
}
