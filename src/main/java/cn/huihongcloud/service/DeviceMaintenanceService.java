package cn.huihongcloud.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.huihongcloud.entity.beetle.BeetleInfo;
import cn.huihongcloud.entity.device.*;
import cn.huihongcloud.entity.statistics.InputEntity;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceMaintenanceAbnormalDataMapper;
import cn.huihongcloud.mapper.DeviceMaintenanceMapper;
import cn.huihongcloud.mapper.UserMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 钟晖宏 on 2018/9/24
 */
@Service
public class DeviceMaintenanceService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeviceMaintenanceMapper deviceMaintenanceMapper;

    @Autowired
    private DeviceMaintenanceAbnormalDataMapper deviceMaintenanceAbnormalDataMapper;

    @Autowired
    private OtherBeetleService otherBeetleService;

    public List<DeviceMaintenance> getMaintenanceData(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role >= 1 && role < 4) {
            // 省到县级用户
            boolean reported = true;
            if (role == 3) {
                return deviceMaintenanceMapper.getMaintenanceDataByAdcodeAndTown(user.getAdcode(), user.getTown(), condition, date, endDate, null);
            }else {
                return deviceMaintenanceMapper.getMaintenanceDataByAdcodeAndTown(user.getAdcode(), user.getTown(), condition, date, endDate, reported);
            }
            } else if (role == 4) {
            // 管理员
            return deviceMaintenanceMapper.getMaintenanceDataByManager(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }
    public List<DeviceMaintenance> getMaintenanceData2(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role < 3) {
            // 省到县级用户
            boolean reported = true;

                return deviceMaintenanceMapper.getMaintenanceDataByAdcodeAndTown2(user.getAdcode(), user.getTown(), condition, date, endDate, reported);

        } else if (role == 3) {
            return deviceMaintenanceMapper.getMaintenanceDataByAdcodeAndTown2(user.getAdcode(), user.getTown(), condition, date, endDate, null);
        } else if (role == 4) {
            // 管理员
            return deviceMaintenanceMapper.getMaintenanceDataByManager2(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }
    public List<DeviceMaintenance> getMaintenanceData1(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role <3) {
            // 省到县级用户
            boolean reported = true;

            return deviceMaintenanceMapper.getMaintenanceDataByAdcodeAndTown1(user.getAdcode(), user.getTown(), condition, date, endDate,reported);
        } else if (role == 3) {

            return deviceMaintenanceMapper.getMaintenanceDataByAdcodeAndTown1(user.getAdcode(), user.getTown(), condition, date, endDate,null);
        }else if (role == 4) {
            // 管理员
            return deviceMaintenanceMapper.getMaintenanceDataByManager1(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }
    public List<DeviceMaintenance> getMaintenanceData4(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role < 3) {
            // 省到县级用户
            boolean reported = true;

            return deviceMaintenanceMapper.getMaintenanceDataByAdcodeAndTown4(user.getAdcode(), user.getTown(), condition, date, endDate,reported);
        } else if (role == 3) {

            return deviceMaintenanceMapper.getMaintenanceDataByAdcodeAndTown4(user.getAdcode(), user.getTown(), condition, date, endDate,null);
        }else if (role == 4) {
            // 管理员

            return deviceMaintenanceMapper.getMaintenanceDataByManager4(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername(),null);
        } else if (role == 5) {
            return null;
        }
        return null;
    }
//    public List<DeviceMaintenanceAbnormalData> getMaintenanceAbnormalData(User user, String condition, String date) {
//        int role = user.getRole();
//        if (role >= 2 && role <3) {
//            return deviceMaintenanceAbnormalDataMapper.getMaintenanceDataByUsername(user.getUsername(), condition, date);
//        } else {
//            return deviceMaintenanceAbnormalDataMapper.getMaintenanceDataByAdcodeAndTown(user.getAdcode(), user.getTown(), condition, date);
//        }
//    }

    public void addMaintenanceData(String username, DeviceMaintenance deviceMaintenance) {
        deviceMaintenance.setUsername(username);
        deviceMaintenanceMapper.addMaintenanceData(deviceMaintenance);
    }

    public void updateMaintenanceData(DeviceMaintenance deviceMaintenance) {
        deviceMaintenanceMapper.updateMaintenanceData(deviceMaintenance);
    }

    /**
     * 老师所描述的计算方式
     * @param data
     * @return
     */
    private List<DeviceMaintenanceForChart> calculateDataForCharts(List<DeviceMaintenance> data) {
        List<DeviceMaintenanceForChart> ret = new ArrayList<>();
        for (int i = 0; i < data.size() - 1; ++i) {
            DeviceMaintenance deviceMaintenanceA = data.get(i);
            DeviceMaintenance deviceMaintenanceB = data.get(i + 1);
//            int subs = Math.abs(deviceMaintenanceA.getNum() - deviceMaintenanceB.getNum());
            double subDaysDouble = ((deviceMaintenanceB.getDate().getTime()
                    - deviceMaintenanceA.getDate().getTime()) / 1000 / 3600 / 24.0);
            long subDays = Math.round(subDaysDouble);
            System.out.println(subDays);
            int avg = (int)(deviceMaintenanceB.getNum() / subDays);
            for (int j = 0; j < subDays; ++j) {
                DeviceMaintenanceForChart item = new DeviceMaintenanceForChart();
                BeanUtils.copyProperties(deviceMaintenanceA, item);
                item.setDate(new Date(deviceMaintenanceA.getDate().getTime() + 3600 * 24 * 1000L * j));
                item.setNum(avg);
                ret.add(item);
            }
        }
        return ret;
    }

    /**
     * 获取乡镇的统计信息
     * @param adcode
     * @param town
     * @return
     */
    public Object getStatisticsByTown(String adcode, String town, Boolean isChart) {
        Object list = null;
        try {
            list = deviceMaintenanceMapper.getStatisticsByTown(adcode, town);
            if (isChart)
                list = calculateDataForCharts((List<DeviceMaintenance>) list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取乡镇的统计信息
     * @param adcode
     * @return
     */
    public Object getStatisticsByArea(String adcode, Boolean isChart) {
        Object list = null;
        try {
            list = deviceMaintenanceMapper.getStatisticsByArea(adcode);
            if (isChart)
                list = calculateDataForCharts((List<DeviceMaintenance>) list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 获取根据adcode前缀的统计信息
     * @param adcode
     * @return
     */
    public Object getStatisticsLikeAdcode(String adcode, Boolean isChart) {
        Object list = null;
        try {
            list = deviceMaintenanceMapper.getStatisticsByLikeAdcode(adcode);
            if (isChart)
                list = calculateDataForCharts((List<DeviceMaintenance>) list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getChangeTimesByDeviceId(String deviceId) {
        return deviceMaintenanceMapper.getChangeTimesByDeviceId(deviceId);
    }

    public int deleteById(int id) {
        return deviceMaintenanceMapper.deleteById(id);
    }

    public void addList(List<DeviceMaintenance> deviceMaintenanceList) {
        for (DeviceMaintenance deviceMaintenance: deviceMaintenanceList) {
            deviceMaintenanceMapper.addMaintenanceData(deviceMaintenance);
        }
    }

    public void recordAbnormal(DeviceMaintenance deviceMaintenance) {
        DeviceMaintenanceAbnormalData deviceMaintenanceAbnormalData = new DeviceMaintenanceAbnormalData();
        BeanUtils.copyProperties(deviceMaintenance, deviceMaintenanceAbnormalData);
        deviceMaintenanceAbnormalData.setIsactive(0);
        if(deviceMaintenance.getImageId() != null && deviceMaintenance.getImageId() != ""){
            deviceMaintenance.setImgId(deviceMaintenance.getImageId());
        }
        deviceMaintenanceAbnormalDataMapper.insert(deviceMaintenanceAbnormalData);

    }

    public List<DeviceMaintenance> getMaintenanceDataById(User user,String id, String startDate, String endDate) {
        Integer role=user.getRole();
        if(role<3){
            Boolean reported = true;
            return deviceMaintenanceMapper.getMaintenanceDataById(id,startDate,endDate,reported);
        }

        return deviceMaintenanceMapper.getMaintenanceDataById(id,startDate,endDate,null);
    }
    public List<DeviceMaintenance> getMaintenanceDataByIdScan(String id) {

            return deviceMaintenanceMapper.getMaintenanceDataByIdScan(id);

    }
    public List<DeviceMaintenance> getMaintenanceDataByDeviceId(User user,String myusername,String deviceId, String startDate, String endDate) {
        Integer role=user.getRole();
        if(role<3){
            Boolean reported = true;
            return deviceMaintenanceMapper.getMaintenanceDataByDeviceId(myusername,deviceId,startDate,endDate,reported);
        }else if(role == 3 || role ==4) {

            return deviceMaintenanceMapper.getMaintenanceDataByDeviceId(myusername,deviceId, startDate, endDate, null);
        }else if (role == 5) {
            return null;
        }
        return null;
        }
    public DeviceMaintenance getMaintenanceDataById1(Integer id) {
        return deviceMaintenanceMapper.getMaintenanceDataById1(id);
    }

    public List<InputEntity> getInputEntityForWorker(String manager, String startDate, String endDate) {
        return deviceMaintenanceMapper.getInputEntityForWorker(manager, startDate, endDate);
    }

    public List<InputEntity> getInputEntityForTown(String adcode, String startDate, String endDate) {
        return deviceMaintenanceMapper.getInputEntityForTown(adcode, startDate, endDate);
    }

    public List<InputEntity> getInputEntityForArea(String code, String startDate, String endDate) {
        return deviceMaintenanceMapper.getInputEntityForArea(code, startDate, endDate);
    }

    public List<InputEntity> getInputEntityForCity(String code, String startDate, String endDate) {
        return deviceMaintenanceMapper.getInputEntityForCity(code, startDate, endDate);
    }
    public List<DeviceMaintenance>  getMonthSummaryByArea(String adcode,String startYear, String endYear){
        return deviceMaintenanceMapper.getMonthSummaryByArea(adcode,startYear,endYear);
    }
    public List<DeviceMaintenance>  getMonthSummaryByTown(String adcode,String startYear, String endYear){
        return deviceMaintenanceMapper.getMonthSummaryByTown(adcode, startYear,endYear);
    }
    public List<DeviceMaintenance>  getMonthSummaryByCity(String adcode,String startYear, String endYear){
        return deviceMaintenanceMapper.getMonthSummaryByCity(adcode,startYear,endYear);
    }
    public List<DeviceMaintenance>  getMonthSummaryByWorker(String adcode,String startYear, String endYear){
        return deviceMaintenanceMapper.getMonthSummaryByWorker(adcode, startYear,endYear);
    }
    public boolean report(List<Integer> ids) {
        for (Integer id: ids) {
            deviceMaintenanceMapper.reportData(id);
        }
        return true;
    }
    public List<DeviceMaintenance> getMaintenanceData3(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role < 3) {
            // 省到县级用户
            boolean reported = true;

            return deviceMaintenanceMapper.getMaintenanceDataByAdcodeAndTown4(user.getAdcode(), user.getTown(), condition, date, endDate,reported);
        }
        else if(role==3){
            return deviceMaintenanceMapper.getMaintenanceDataByAdcodeAndTown4(user.getAdcode(), user.getTown(), condition, date, endDate,null);
        }else if (role == 4 ) {
            // 管理员

            return deviceMaintenanceMapper.getMaintenanceDataByManager4(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername(),null);
        } else if (role == 5) {
            return null;
        }
        return null;
    }
    public Workbook exportExcel(User user, String condition,
                                String startDate, String endDate) {
        List<BeetleInfo> beetleInfoList = otherBeetleService.getOtherBeetleInfoList();
        List<DeviceMaintenance> maintenanceData = getMaintenanceData3(user, condition, startDate, endDate);
        List<DeviceMaintenanceOutput> outputList = new ArrayList<>();
        for (DeviceMaintenance deviceMaintenance: maintenanceData) {
            DeviceMaintenanceOutput opt = new DeviceMaintenanceOutput();
            BeanUtils.copyProperties(deviceMaintenance, opt);
            for (BeetleInfo beetleInfo: beetleInfoList) {
                if (deviceMaintenance.getOtherType() != null && deviceMaintenance.getOtherType().equals(beetleInfo.getId())) {
                    opt.setOtherTypeString(beetleInfo.getName());
                    break;
                }
            }
            if (deviceMaintenance.getWorkingContent() != null) {
                switch (deviceMaintenance.getWorkingContent()) {
                    case 0:
                        opt.setWorkingContentString("首次悬挂诱捕器");
                        break;
                    case 1:
                        opt.setWorkingContentString("换药+收虫");
                        break;
                    case 2:
                        opt.setWorkingContentString("收虫");
                        break;
                    case 3:
                        opt.setWorkingContentString("其他");
                        break;
                }
            }
            outputList.add(opt);
        }
        return ExcelExportUtil.exportExcel(new ExportParams("设备维护信息", "设备维护信息"), DeviceMaintenanceOutput.class, outputList);
    }
    public Workbook exportExcel1(User user, String condition,
                                String startDate, String endDate) {
        List<BeetleInfo> beetleInfoList = otherBeetleService.getOtherBeetleInfoList();
        List<DeviceMaintenance> maintenanceData = getMaintenanceData4(user, condition, startDate, endDate);
        List<DeviceMaintenanceOutput> outputList = new ArrayList<>();
        for (DeviceMaintenance deviceMaintenance: maintenanceData) {
            DeviceMaintenanceOutput opt = new DeviceMaintenanceOutput();
            BeanUtils.copyProperties(deviceMaintenance, opt);
            for (BeetleInfo beetleInfo: beetleInfoList) {
                if (deviceMaintenance.getOtherType() != null && deviceMaintenance.getOtherType().equals(beetleInfo.getId())) {
                    opt.setOtherTypeString(beetleInfo.getName());
                    break;
                }
            }
            if (deviceMaintenance.getWorkingContent() != null) {
                switch (deviceMaintenance.getWorkingContent()) {
                    case 0:
                        opt.setWorkingContentString("首次悬挂诱捕器");
                        break;
                    case 1:
                        opt.setWorkingContentString("换药+收虫");
                        break;
                    case 2:
                        opt.setWorkingContentString("收虫");
                        break;
                    case 3:
                        opt.setWorkingContentString("其他");
                        break;
                }
            }
            outputList.add(opt);
        }
        return ExcelExportUtil.exportExcel(new ExportParams("设备维护信息", "设备维护信息"), DeviceMaintenanceOutput.class, outputList);
    }
}
