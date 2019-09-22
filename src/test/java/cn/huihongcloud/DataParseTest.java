package cn.huihongcloud;

import cn.huihongcloud.server.DataParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 钟晖宏 on 2018/5/31
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataParseTest {

    @Autowired
    private DataParser dataParser;

    @Test
    public void test() {
        String data = "1003 17.08.11   14:19:15 05    119.246287 26.089366 -22.8 3.760 82";
        dataParser.parse("1003 17.08.11   14:19:15 05    119.246287 26.089366 -22.8 3.760 82");
        String[] datas = data.split("\\s+");
        System.out.println("id" + datas[0] + " longitude:" + datas[4] + "latitude:" + datas[5]);
    }
}
