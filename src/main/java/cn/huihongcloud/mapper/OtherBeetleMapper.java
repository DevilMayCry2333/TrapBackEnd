package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.*;
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

    int insertWorkContent(String name,Integer Fvalue);

    int insertInjectInfo(String name);

    int insertInject_WoodStatus(String name);

    int insertInject_WorkContent(String name);

    int insertMedicine_WorkContent(String name);

    int insertEnemy_Type(String name);

    int insertDead_Kill(String name);

    List<BeetleInfo> queryBeetleInfo();

    int insertInjectForTown(int id,String adcode);

    int updateBeetleInfo(BeetleInfo beetleInfo);

    int updateWorkContent(workContent workcontent);

    int updateInjectInfo(injectInfo injectInfo);

    int updateInject_WoodStatus(inject_WoodStatus injectWoodStatus);

    int updateInject_WorkContent(inject_WorkContent injectWorkContent);

    int updateMedicine_WorkContent(Medicine_WorkContentEntity medicine_workContentEntity);

    int updateEnemy_Type(enemy_EneType enemyEneType);

    int updateDead_Kill(deadTree_KillMethods deadTreeKillMethods);

    int deleteBeetleInfo(int id);

    int deleteInjectInfo(int id);

    int deleteInject_WoodStatus(int id);

    int deleteInject_WorkContent(int id);

    int deleteEnemy_Type(int id);

    int deleteDead_Kill(int id);

    int deleteEnemy_TypeForTown(int id,String adcode);

    int deleteInjectForTown(int id,String adcode);

    List<BeetleInfo> queryBeetleInfoForTown(@Param("adcode") String adcode);

    List<workContent> getWorkContent();

    int insertBeetleInfoMap(@Param("beetleInfoId") int beetleInfoId, @Param("adcode") String adcode);

    int insertEnemy_TypeForTown(int id,String adcode);

    int insertWorkContentForTOwn(int id,String adcode);

    int deleteBeetleInfoMap(@Param("beetleInfoId") int beetleInfoId, @Param("adcode") String adcode);

    int deleteWorkContent(int id);

    int deleteWorkContent1(int id);

    int deleteWorkContentFOrTown(int id,String adcode);

    List<injectInfo> getInjectInfo();

    List<inject_WoodStatus> getInject_WoodStatus();

    List<inject_WorkContent> getInject_WorkContent();

    List<enemy_EneType> getEnemy_Type();

    List<deadTree_KillMethods> getDead_Kill();

    List<enemy_EneType> getEnemy_TypeForTown(String adcode);

    List<workContent> getWorkContentForTown(String adcode);

    List<injectInfo> getInjectForTown(String adcode);

    List<inject_WoodStatus> getInjectWoodForTown(String adcode);

    int insertInjectWood(int id,String adcode);

    int deleteInjectWood(int id,String adcode);

    List<inject_WorkContent> getInjectWorkContentForTown(String adcode);

    int insertInjectWorkContentForTown(int id,String adcode);

    int deleteInjectWorkContentForTown(int id,String adcode);

    List<deadTree_KillMethods> getDeadKillForTown(String adcode);

    int insertDeadKillFOrTown(int id,String adcode);

    int deleteDeadKillForTOwn(int id,String adcode);

}
