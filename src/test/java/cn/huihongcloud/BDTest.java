package cn.huihongcloud;

import cn.huihongcloud.component.BDComponent;
import cn.huihongcloud.entity.bd.BDInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 钟晖宏 on 2018/5/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BDTest {

    @Autowired
    private BDComponent bdComponent;

    private void print(double latitude, double longitude) {
        BDInfo bdInfo = bdComponent.parseLocation(latitude, longitude);
        ObjectMapper objectMapper = new ObjectMapper();
        BDInfo.Result.AddressComponent addressComponent = bdInfo.getResult().getAddressComponent();
        try {
            System.out.println(objectMapper.writeValueAsString(addressComponent));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testParse() {
        bdComponent.parseLocation(26.0833630478,119.2395248001);
    }

    @Test
    public void testMountain() throws JsonProcessingException {
        print(25.9773302343,119.1358018074); //点在旗山森林公园
        print(25.9510439385,119.0930228146); //白岩坑
        print(26.0144218586,119.1511133300); //刘村
        print(26.163924, 119.18854); //关口
    }
}
