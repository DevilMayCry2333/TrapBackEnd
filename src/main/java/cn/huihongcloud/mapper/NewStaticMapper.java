package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.statistics.InputEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewStaticMapper {
    List<InputEntity> getInputEntityForWorker(@Param("customProject") String customProject, @Param("startDate") String startDate,
                                              @Param("endDate") String endDate);
}
