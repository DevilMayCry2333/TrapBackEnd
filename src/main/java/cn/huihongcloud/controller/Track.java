package cn.huihongcloud.controller;

import cn.huihongcloud.service.TrackService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/track")
public class Track {
    @Autowired
    TrackService trackService;

    JSONObject jsonObject = new JSONObject();

    @RequestMapping("/detail")
    public Object detail(@RequestParam String username,@RequestParam int page,@RequestParam int limit){
        jsonObject.put("Data",trackService.selectAll(username, page*limit-limit, page*limit));
        jsonObject.put("total",trackService.countAll(username));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;
    }

    @RequestMapping("/areaDetail")
    public Object areaDetail(@RequestParam String username,@RequestParam int page,@RequestParam int limit){
        jsonObject.put("Data",trackService.selectAllByArea(username, page*limit-limit, page*limit));
        jsonObject.put("total",trackService.countAllArea(username));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;
    }
}
