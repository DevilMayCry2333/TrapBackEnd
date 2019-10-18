package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.AreaStatic;
import cn.huihongcloud.entity.WorkerStatic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.awt.geom.Area;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/10/3
 */
@Repository
public interface AnalysisMapper {

    List<Map<String, String>> getDistInfoByAdcode(@Param("adcode") String adcode, @Param("town") String town, @Param("key") String key);
    List<String> getYearList(@Param("adcode") String adcode, @Param("town") String town);
    List<Map<String, Object>> getMonthlyDataByAdcodeTownAndYear(@Param("adcode") String adcode, @Param("town") String town, @Param("year") int year);

    List<AreaStatic> getAreaStatic(String customProject,String startDate,String endDate);

    List<AreaStatic> getMonthStatic(String startDate,String endDate,String customProject);

    List<AreaStatic> getBatchStatic(String startDate,String endDate,String customProject);

    List<WorkerStatic> getWorkerStatic(String startDate, String endDate, String customProject);




}
