package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.beetle.BeetleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 钟晖宏 on 2019/1/16
 */
@Repository
public interface OtherBeetleMapper {

    int insertBeetleInfo(BeetleInfo beetleInfo);

    List<BeetleInfo> queryBeetleInfo();

    int updateBeetleInfo(BeetleInfo beetleInfo);

    int deleteBeetleInfo(int id);

    List<BeetleInfo> queryBeetleInfoForTown(@Param("adcode") String adcode);

    int insertBeetleInfoMap(@Param("beetleInfoId") int beetleInfoId, @Param("adcode") String adcode);

    int deleteBeetleInfoMap(@Param("beetleInfoId") int beetleInfoId, @Param("adcode") String adcode);
}
