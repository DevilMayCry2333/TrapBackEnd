package cn.huihongcloud.controller;

import cn.huihongcloud.service.NaturalEnemyService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/natural")
public class NaturalEnemy {
    @Autowired
    NaturalEnemyService naturalEnemyService;

    JSONObject jsonObject = new JSONObject();
    @RequestMapping("/detail")
    public Object NaturalDetail(){
        jsonObject.put("Res",true);
        return jsonObject;
    }
}
