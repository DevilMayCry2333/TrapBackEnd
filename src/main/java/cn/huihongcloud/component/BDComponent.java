package cn.huihongcloud.component;

import cn.huihongcloud.entity.bd.BDInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by 钟晖宏 on 2018/5/31
 */

@Component

public class BDComponent {

    @Value("${cn.huihongcloud.ak}")
    private String AK;

    private static String REVERSE_BASE_URL = "http://api.map.baidu.com/geocoder/v2/?output=json&pois=0&coordtype=wgs84ll&extensions_town=true";

    OkHttpClient httpClient;

    public BDComponent() {
        httpClient = new OkHttpClient();
    }

    public BDInfo parseLocation(double latitude, double longitude) {
        Request request = new Request.Builder()
                .url(REVERSE_BASE_URL + "&location=" + latitude + "," + longitude + "&ak=" + AK)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            String res = response.body().string();
            res = res.replace("district", "area");
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(res, BDInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
