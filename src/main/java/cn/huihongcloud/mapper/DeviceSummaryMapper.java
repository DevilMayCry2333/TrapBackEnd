package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.NewTrapDataSummary;
import cn.huihongcloud.entity.summary.DeviceDetail;
import cn.huihongcloud.entity.summary.SummaryEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2019/1/18
 */
@Repository
public interface DeviceSummaryMapper {
    List<SummaryEntity> queryDeviceSummaryByProvince(String adcode,String startDate, String endDate);
    List<SummaryEntity> queryDeviceSummaryByCity(String adcode,String startDate, String endDate);
    List<SummaryEntity> queryDeviceSummaryByArea(String adcode,String startDate, String endDate);
    List<SummaryEntity> queryDeviceSummaryByManager(String adcode,String startDate, String endDate);
    List<SummaryEntity> queryWorkerSummaryByAdcode(String adcode,String startDate, String endDate);
    List<SummaryEntity> queryWorkerSummaryByManager(@Param("manager") String manager,String startDate, String endDate);
    Map<String, Long> queryDeviceSum(String adcode,String startDate,String endDate);
    Map<String, Long> queryDeviceSum4(String adcode,String startDate,String endDate);
    List<DeviceDetail> queryDeviceDetail(@Param("adcode") String adcode);
    List<NewTrapDataSummary> selectByColName(String colName,String startDate,String endDate,String searchText,String username);
}
