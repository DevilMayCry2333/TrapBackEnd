package cn.huihongcloud.service;

import cn.huihongcloud.entity.beetle.BeetleInfo;
import cn.huihongcloud.entity.workContent;
import cn.huihongcloud.mapper.OtherBeetleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 钟晖宏 on 2019/1/16
 */
@Service
public class OtherBeetleService {

    @Autowired
    private OtherBeetleMapper otherBeetleMapper;

    public List<BeetleInfo> getOtherBeetleInfoList() {
        return otherBeetleMapper.queryBeetleInfo();
    }
    public List<workContent> getWorkContentList(){
        return otherBeetleMapper.getWorkContent();
    }

    public boolean updateOtherBeetleInfo(BeetleInfo beetleInfo) {
        return otherBeetleMapper.updateBeetleInfo(beetleInfo) == 1;
    }

    public boolean deleteOtherBeetleInfo(int id) {
        return otherBeetleMapper.deleteBeetleInfo(id) == 1;
    }

    public boolean addOtherBeetleInfo(String name) {
        BeetleInfo beetleInfo = new BeetleInfo();
        beetleInfo.setName(name);
        return otherBeetleMapper.insertBeetleInfo(beetleInfo) == 1;
    }

    public List<BeetleInfo> getOtherBeetleInfoListForTown(String adcode) {
        return otherBeetleMapper.queryBeetleInfoForTown(adcode);
    }

    public boolean addOtherBeetleForTown(String adcode, Integer otherBeetleInfoId) {
        return otherBeetleMapper.insertBeetleInfoMap(otherBeetleInfoId, adcode) == 1;
    }

    public boolean deleteOtherBeetleInfoForTown(String adcode, Integer otherBeetleInfoId) {
        return otherBeetleMapper.deleteBeetleInfoMap(otherBeetleInfoId, adcode) == 1;
    }
}
