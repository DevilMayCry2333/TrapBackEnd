package cn.huihongcloud.service;

import cn.huihongcloud.entity.device.DeviceBeetle;
import cn.huihongcloud.mapper.DeviceBeetleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 钟晖宏 on 2018/7/20
 */
@Service
public class DeviceBeetleService {

    @Autowired
    private DeviceBeetleMapper deviceBeetleMapper;

    /**
     * 根据设备ID获取信息
     * @param deviceId
     * @return
     */
    public List<DeviceBeetle> selectByDeviceId(String deviceId) {
        try {
            return deviceBeetleMapper.selectByDeviceId(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 增加数据
     * @param deviceBeetle
     * @return
     */
    public boolean addData(DeviceBeetle deviceBeetle) {
        int n = 0;
        // 如果没有传入日期，则为当前时间
        if (deviceBeetle.getChangeDate() == null) {
            deviceBeetle.setChangeDate(new Date());
        }
        try {
            // 设置维护次数
            deviceBeetle.setChangeTimes(deviceBeetleMapper.getChangeTimesByDeviceId(deviceBeetle.getDeviceId() + 1));
            n = deviceBeetleMapper.addData(deviceBeetle);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return n == 1;
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    public boolean deleteData(int id) {
        int n = 0;
        try {
            n = deviceBeetleMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return n == 1;
    }

    /**
     * 更新数据
     * @param deviceBeetle
     * @return
     */
    public boolean updateData(DeviceBeetle deviceBeetle) {
        int n = 0;
        try {
            n = deviceBeetleMapper.updateData(deviceBeetle);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return n == 1;
    }

    /**
     * 获取乡镇的统计信息
     * @param adcode
     * @param town
     * @return
     */
    public List<DeviceBeetle> getStatisticsByTown(String adcode, String town, Boolean isChart) {
        List<DeviceBeetle> list = null;
        try {
            list = deviceBeetleMapper.getStatisticsByTown(adcode, town);
            if (isChart)
                list = calculateDataForCharts(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 老师所描述的计算方式
     * @param data
     * @return
     */
    private List<DeviceBeetle> calculateDataForCharts(List<DeviceBeetle> data) {
        List<DeviceBeetle> ret = new ArrayList<>();
        for (int i = 0; i < data.size() - 1; ++i) {
            DeviceBeetle deviceBeetleA = data.get(i);
            DeviceBeetle deviceBeetleB = data.get(i + 1);
            int subs = Math.abs(deviceBeetleB.getBeetleNum() - deviceBeetleA.getBeetleNum());
            double subDaysDouble = ((deviceBeetleB.getChangeDate().getTime() - deviceBeetleA.getChangeDate().getTime()) / 1000 / 3600 / 24.0);
            long subDays = Math.round(subDaysDouble);

            int avg = (int)(subs / subDays);
            for (int j = 0; j < subDays; ++j) {
                DeviceBeetle item = new DeviceBeetle();
                BeanUtils.copyProperties(deviceBeetleA, item);
                item.setChangeDate(new Date(deviceBeetleA.getChangeDate().getTime() + 3600 * 24 * 1000L * j));
                item.setBeetleNum(avg);
                ret.add(item);
            }
        }
        return ret;
    }

    /**
     * 获取乡镇的统计信息
     * @param adcode
     * @return
     */
    public List<DeviceBeetle> getStatisticsByArea(String adcode, Boolean isChart) {
        List<DeviceBeetle> list = null;
        try {
            list = deviceBeetleMapper.getStatisticsByArea(adcode);
            if (isChart)
                list = calculateDataForCharts(list);
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
    public List<DeviceBeetle> getStatisticsLikeAdcode(String adcode, Boolean isChart) {
        List<DeviceBeetle> list = null;
        try {
            list = deviceBeetleMapper.getStatisticsByLikeAdcode(adcode);
            if (isChart)
                list = calculateDataForCharts(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
