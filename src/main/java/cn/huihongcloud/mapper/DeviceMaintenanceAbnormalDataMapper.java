package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.device.DeviceMaintenanceAbnormalData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/10/5
 */
public interface DeviceMaintenanceAbnormalDataMapper extends BaseMapper<DeviceMaintenanceAbnormalData> {
    List<DeviceMaintenanceAbnormalData> getMaintenanceDataByAdcodeAndTown(@Param("adcode") String adcode, @Param("town") String town,
                                                              @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);

    List<DeviceMaintenanceAbnormalData> getMaintenanceDataByManager(@Param("adcode") String adcode, @Param("town") String town,
                                                        @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);
    int updateMaintenanceAbnormalData(DeviceMaintenanceAbnormalData deviceMaintenanceAbnormalData);

    int deleteById(int id);
    int reportData(@Param("id") Integer id);
}
