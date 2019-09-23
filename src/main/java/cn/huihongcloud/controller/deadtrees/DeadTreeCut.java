package cn.huihongcloud.controller.deadtrees;

import cn.huihongcloud.service.DeadTreeCutService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deadTree")
public class DeadTreeCut {
    @Autowired
    DeadTreeCutService deadTreeCutService;

    JSONObject jsonObject = new JSONObject();
    @RequestMapping("/detail")
    public Object detail(@RequestParam String username,@RequestParam int page,@RequestParam int limit){
        jsonObject.put("Res",true);
        System.out.println(page);
        System.out.println(limit);

        jsonObject.put("Data",deadTreeCutService.selectAll(username,page*limit-limit,limit));
        jsonObject.put("total",deadTreeCutService.countAll(username));
        jsonObject.put("current",page);

        return jsonObject;
    }

    @RequestMapping("/areaDetail")
    public Object areaDetail(@RequestParam String username,@RequestParam int page,@RequestParam int limit){
        jsonObject.put("Data",deadTreeCutService.selectAllByArea(username, page*limit-limit, limit));
        jsonObject.put("total",deadTreeCutService.countAllByArea(username));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;
    }

    @RequestMapping("/searchDetail")
    public Object searchDetail(@RequestParam int page,@RequestParam int limit,@RequestParam String username,@RequestParam String startDate,@RequestParam String endDate,@RequestParam String colName,@RequestParam String searchText,@RequestParam String adcode){
        jsonObject.put("Res",true);
        System.out.println(page);
        System.out.println(limit);
        jsonObject.put("Data",deadTreeCutService.selectByDateAndColSearch(username,startDate,endDate,colName,searchText,page*limit-limit,page*limit,adcode));
        jsonObject.put("total",deadTreeCutService.countAll(username));
        jsonObject.put("current",page);
        System.out.println(jsonObject);

        return jsonObject;

    }

    @RequestMapping("/selectAll")
    public Object selectAll(@RequestParam String username,@RequestParam String adcode,@RequestParam int page,@RequestParam int limit){
        jsonObject.put("Data",deadTreeCutService.selectAllByAdcode(adcode, page*limit-limit, page*limit));
        jsonObject.put("total",deadTreeCutService.countAll(username));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;

    }



}
