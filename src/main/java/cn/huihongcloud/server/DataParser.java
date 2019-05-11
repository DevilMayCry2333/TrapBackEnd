package cn.huihongcloud.server;

import cn.huihongcloud.entity.device.Device;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2018/5/31
 */

@Component
public class DataParser {

    // todo id 可以为字符串 正则需要做修改
    private String format = "^\\d+\\s{1,4}" +
            "\\d{2}\\.(0[1-9]|1[0-2])\\.(0[1-9]|[1-2]\\d|3[0-1])\\s" +
            "{1,4}([0-1]\\d|2[0-3]):([0-4]\\d|5[0-9]):([0-4]\\d|5[0-9])\\s" +
            "{1,4}\\d{2,}\\s" +
            "{1,4}\\d{1,3}\\.\\d{6}\\s" +
            "{1,4}\\d{1,3}\\.\\d{6}\\s" +
            "{1,4}(|-)\\d{1,4}\\.\\d\\s" +
            "{1,4}([0-9]|[1-9]\\d|100)\\.\\d{1,3}\\s" +
            "{1,4}\\d{1,3}$";

    public Device parse(String data) {
        if (data.matches(format)) {
            data =  data.replace("\t", " ");
            String []datas = data.split("\\s+");
            Device device = new Device();
            device.setId(datas[0]);
            device.setLongitude(Double.parseDouble(datas[4]));
            device.setLatitude(Double.parseDouble(datas[5]));
            device.setReceiveDate(new Date());
            return device;
        }
        return null;
    }

}
