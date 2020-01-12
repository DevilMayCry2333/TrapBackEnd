package cn.huihongcloud.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 表锁定
 */

@Repository
public interface LockerMapper {

    int getLocks(@Param("province") String province, @Param("tableName") String tableName);

    int deleteLocks(@Param("province") String province, @Param("tableName") String tableName);

    int insertLocks(@Param("province") String province,
                    @Param("tableName") String tableName,
                    @Param("locker") String locker);

}
