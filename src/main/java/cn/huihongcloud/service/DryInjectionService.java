package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.mapper.Device_Injection_maintanceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DryInjectionService {
    @Autowired
    private Device_Injection_maintanceEntityMapper deviceInjectionMaintanceEntityMapper;

    public List<Device_Injection_maintanceEntity> getDryInjectionDetail(int page,int limit,String username){
        return deviceInjectionMaintanceEntityMapper.selectAll(page*limit-limit,page*limit,username);
    }

    public int getTotalNum(String username){
        return deviceInjectionMaintanceEntityMapper.CountAll(username);
    }
}
