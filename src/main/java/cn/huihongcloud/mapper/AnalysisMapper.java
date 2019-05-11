package cn.huihongcloud.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

}
