package cn.huihongcloud.controller;

import cn.huihongcloud.service.DryInjectionService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dryWatch")
public class DryInjectionWatch {
    @Autowired
    DryInjectionService dryInjectionService;

    JSONObject jsonObject = new JSONObject();

    @RequestMapping("/detail")
    public Object DataDetail(@RequestParam int page,@RequestParam int limit,@RequestParam String username){
        jsonObject.put("Res",true);
        System.out.println(page);
        System.out.println(username);
        jsonObject.put("Data",dryInjectionService.getDryInjectionDetail(page,limit,username));
        jsonObject.put("total",dryInjectionService.getTotalNum(username));
        jsonObject.put("current",page);

        return jsonObject;
    }
}
