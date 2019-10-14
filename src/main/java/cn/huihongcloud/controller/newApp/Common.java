package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class Common {
    @Autowired
    DeviceMapper deviceMapper;
    @RequestMapping("/bindId")
    public Object BindId(@RequestParam String scanId,@RequestParam String serial){
        System.out.println(scanId);
        System.out.println(serial);

        Device toBeUpdated = deviceMapper.getDeviceBySerial(serial);
        return deviceMapper.updateScanId(toBeUpdated.getId(),scanId);
    }
}
