package cn.huihongcloud.controller;

import cn.huihongcloud.entity.*;
import cn.huihongcloud.entity.beetle.BeetleInfo;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.OtherBeetleMapper;
import cn.huihongcloud.service.OtherBeetleService;
import cn.huihongcloud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 钟晖宏 on 2019/1/16
 */
@RestController
@RequestMapping("/auth_api")
public class OtherBeetleController {

    @Autowired
    private OtherBeetleService otherBeetleService;

    @Autowired
    private UserService userService;

    @Autowired
    private OtherBeetleMapper otherBeetleMapper;

    //其他天牛配置

    @ApiOperation("")
    @GetMapping("/deadTrees_Kill/town")
    public Object inject10(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return otherBeetleMapper.getDeadKillForTown(user.getAdcode());

    }

    @ApiOperation("")
    @PostMapping("/deadTrees_Kill/town")
    public Object inject11(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.insertDeadKillFOrTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/deadTrees_Kill/town")
    public Object inject12(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.deleteDeadKillForTOwn(beetleInfoId,user.getAdcode());
        return Result.ok();
    }



    @ApiOperation("")
    @GetMapping("/inject_workContent/town")
    public Object inject7(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return otherBeetleMapper.getInjectWorkContentForTown(user.getAdcode());

    }

    @ApiOperation("")
    @PostMapping("/inject_workContent/town")
    public Object inject8(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.insertInjectWorkContentForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/inject_workContent/town")
    public Object inject9(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.deleteInjectWorkContentForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }



    @ApiOperation("")
    @GetMapping("/inject_woodstatus/town")
    public Object inject4(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return otherBeetleMapper.getInjectWoodForTown(user.getAdcode());

    }

    @ApiOperation("")
    @PostMapping("/inject_woodstatus/town")
    public Object inject5(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.insertInjectWood(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/inject_woodstatus/town")
    public Object inject6(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.deleteInjectWood(beetleInfoId,user.getAdcode());
        return Result.ok();
    }


    @ApiOperation("")
    @GetMapping("/inject/town")
    public Object inject(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return otherBeetleMapper.getInjectForTown(user.getAdcode());

    }

    @ApiOperation("")
    @PostMapping("/inject/town")
    public Object inject2(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.insertInjectForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/inject/town")
    public Object inject3(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.deleteInjectForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }


    @ApiOperation("")
    @GetMapping("/medicine/town")
    public Object medicine1(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return otherBeetleMapper.getInjectForTown(user.getAdcode());

    }

    @ApiOperation("")
    @PostMapping("/medicine/town")
    public Object medicine2(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.insertInjectForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/medicine/town")
    public Object medicine3(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.deleteInjectForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @GetMapping("/medicine_workContent/town")
    public Object getworkContentForTown(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return otherBeetleMapper.getWorkContentForTown1(user.getAdcode());

    }

    @ApiOperation("")
    @PostMapping("/medicine_workContent/town")
    public Object addworkContentForTown(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.insertWorkContentForTOwn1(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/medicine_workContent/town")
    public Object deleteworkContentForTown(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.deleteWorkContentFOrTown1(beetleInfoId,user.getAdcode());
        return Result.ok();
    }


    @ApiOperation("")
    @GetMapping("/enemy_Type/town")
    public Object getEnemyTypeForTown(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return otherBeetleMapper.getEnemy_TypeForTown(user.getAdcode());

    }

    @ApiOperation("")
    @PostMapping("/enemy_Type/town")
    public Object addEnemyTypeForTown(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.insertEnemy_TypeForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/enemy_Type/town")
    public Object deleteEnemyTypeForTown(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.deleteEnemy_TypeForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    /**
     * 配置药剂的相关管理员
     * @return
     */

    @ApiOperation("")
    @GetMapping("/medicine_type/town")
    public Object getMedicineTypeForTown(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return otherBeetleMapper.getMedicine_TypeForTown(user.getAdcode());
    }

    @ApiOperation("")
    @PostMapping("/medicine_type/town")
    public Object addMedicineTypeForTown(@RequestAttribute("username") String username,Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.insertMedicine_TypeForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/medicine_type/town")
    public Object deleteMedicineTypeForTown(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.deleteMedicine_TypeForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }


    @GetMapping("/deadTrees_Kill")
    public Object e() {
        return Result.ok(otherBeetleMapper.getDead_Kill());
    }

    @PostMapping("/deadTrees_Kill")
    public Object f(String name) {
        otherBeetleMapper.insertDead_Kill(name);
        return Result.ok();
    }


    @PutMapping("/deadTrees_Kill")
    public Object g(deadTree_KillMethods deadTreeKillMethods) {
        otherBeetleMapper.updateDead_Kill(deadTreeKillMethods);
        return Result.ok();
    }

    @DeleteMapping("/deadTrees_Kill")
    public Object h(int id) {
        otherBeetleMapper.deleteDead_Kill(id);
        return Result.ok();
    }


    @GetMapping("/enemy_Type")
    public Object a() {
        return Result.ok(otherBeetleMapper.getEnemy_Type());
    }

    @PostMapping("/enemy_Type")
    public Object b(String name) {
        otherBeetleMapper.insertEnemy_Type(name);
        return Result.ok();
    }


    @PutMapping("/enemy_Type")
    public Object c(enemy_EneType enemyEneType) {
        otherBeetleMapper.updateEnemy_Type(enemyEneType);
        return Result.ok();
    }

    @DeleteMapping("/enemy_Type")
    public Object d(int id) {
        otherBeetleMapper.deleteEnemy_Type(id);
        return Result.ok();
    }

    /**
     *
     *
     * 配置一下medicine name
     */


    @GetMapping("/medicine_type")
    public Object a1() {
        return Result.ok(otherBeetleMapper.getMedicine_Type());
    }

    @PostMapping("/medicine_type")
    public Object b1(String name) {
        otherBeetleMapper.insertMedicine_Type(name);
        return Result.ok();
    }


    @PutMapping("/medicine_type")
    public Object c1(Medicine_nameEntity medicine_nameEntity) {
        otherBeetleMapper.updateMedicine_Type(medicine_nameEntity);
        return Result.ok();
    }

    @DeleteMapping("/medicine_type")
    public Object d1(int id) {
        otherBeetleMapper.deleteMedicine_Type(id);
        return Result.ok();
    }

    /**
     * 配置一下注剂名称
     * @return
     */
    @GetMapping("/injectName_type")
    public Object a11() {
        return Result.ok(otherBeetleMapper.getInjectname_Type());
    }

    @PostMapping("/injectName_type")
    public Object b11(String name) {
        otherBeetleMapper.insertInjectName_Type(name);
        return Result.ok();
    }


    @PutMapping("/injectName_type")
    public Object c11(InjectName injectName) {
        otherBeetleMapper.updateInjectName_Type(injectName);
        return Result.ok();
    }

    @DeleteMapping("/injectName_type")
    public Object d11(int id) {
        otherBeetleMapper.deleteInjectName_Type(id);
        return Result.ok();
    }

    /**
     * 配置一下注剂管理员
     * @return
     */
    @ApiOperation("")
    @GetMapping("/injectName_type/town")
    public Object getinjectNameTypeForTown(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return otherBeetleMapper.getinjectName_TypeForTown(user.getAdcode());
    }

    @ApiOperation("")
    @PostMapping("/injectName_type/town")
    public Object addinjectNameTypeForTown(@RequestAttribute("username") String username,Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.insertInjectName_TypeForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/injectName_type/town")
    public Object deleteinjectNameTypeForTown(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.deleteInjectForTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }
    @GetMapping("/medicine_workContent")
    public Object getMedicineWork() {
        return Result.ok(otherBeetleMapper.getMedicine_WorkContent());
    }

    @PostMapping("/medicine_workContent")
    public Object addMedicineWork(String name) {
        otherBeetleMapper.insertMedicine_WorkContent(name);
        return Result.ok();
    }


    @PutMapping("/medicine_workContent")
    public Object updateMedicineWork(Medicine_WorkContentEntity medicine_workContentEntity) {
        otherBeetleMapper.updateMedicine_WorkContent(medicine_workContentEntity);
        return Result.ok();
    }

    @DeleteMapping("/medicine_workContent")
    public Object deleteMedicineWork(int id) {
        otherBeetleMapper.deleteWorkContent1(id);
        return Result.ok();
    }


    @GetMapping("/inject_workContent")
    public Object getinjectWork() {
        return Result.ok(otherBeetleMapper.getInject_WorkContent());
    }

    @PostMapping("/inject_workContent")
    public Object addInjecta(String name) {
        otherBeetleMapper.insertInject_WorkContent(name);
        return Result.ok();
    }


    @PutMapping("/inject_workContent")
    public Object editInjecta(inject_WorkContent injectWorkContent) {
        otherBeetleMapper.updateInject_WorkContent(injectWorkContent);
        return Result.ok();
    }

    @DeleteMapping("/inject_workContent")
    public Object deleteInjeca(int id) {
        otherBeetleMapper.deleteWorkContent(id);
        return Result.ok();
    }


    @GetMapping("/inject_woodstatus")
    public Object getinjectWood() {
        return Result.ok(otherBeetleMapper.getInject_WoodStatus());
    }

    @PostMapping("/inject_woodstatus")
    public Object addInjectWood(String name) {
        otherBeetleMapper.insertInject_WoodStatus(name);
        return Result.ok();
    }


    @PutMapping("/inject_woodstatus")
    public Object editInjectWood(inject_WoodStatus woodStatus) {
        otherBeetleMapper.updateInject_WoodStatus(woodStatus);
        return Result.ok();
    }

    @DeleteMapping("/inject_woodstatus")
    public Object deleteInjectWood(int id) {
        otherBeetleMapper.deleteInject_WoodStatus(id);
        return Result.ok();
    }


    @GetMapping("/inject")
    public Object getinject() {
        return Result.ok(otherBeetleMapper.getInjectInfo());
    }

    @PostMapping("/inject")
    public Object addInjectInfo(String name) {
        otherBeetleMapper.insertInjectInfo(name);
        return Result.ok();
    }


    @PutMapping("/inject")
    public Object editInjectInfo(injectInfo injectInfo) {
        otherBeetleMapper.updateInjectInfo(injectInfo);
        return Result.ok();
    }

    @DeleteMapping("/inject")
    public Object deleteInjectInfo(int id) {
        otherBeetleMapper.deleteInjectInfo(id);
        return Result.ok();
    }


    @GetMapping("/workContent")
    public Object getWorkContent() {
        return Result.ok(otherBeetleService.getWorkContentList());
    }

    @PostMapping("/workContent")
    public Object addworkContentInfo(String name) {
        List<workContent> workContentList = otherBeetleService.getWorkContentList();
        int maxFvalue=0;
        for (workContent workContent:workContentList) {
            if(workContent.getFvalue()>maxFvalue)
                maxFvalue = workContent.getFvalue();
        }
        maxFvalue = maxFvalue + 1;
        otherBeetleMapper.insertWorkContent(name,maxFvalue);
        return Result.ok();
    }


    @ApiOperation("")
    @PutMapping("/workContent")
    public Object editworkContentInfo(workContent workcontent) {
        otherBeetleMapper.updateWorkContent(workcontent);
        return Result.ok();
    }

    @DeleteMapping("/workContent")
    public Object deleteWorkContentInfo(int id) {
        otherBeetleMapper.deleteWorkContent(id);
        return Result.ok();
    }

    // 这个就不分页了
    @ApiOperation("查看其他天牛列表")
    @GetMapping("/other_beetle")
    public Object getOtherBeetleList() {
        return Result.ok(otherBeetleService.getOtherBeetleInfoList());
    }

    @ApiOperation("")
    @PutMapping("/other_beetle")
    public Object editOtherBeetleInfo(BeetleInfo beetleInfo) {
        otherBeetleService.updateOtherBeetleInfo(beetleInfo);
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/other_beetle")
    public Object deleteOtherBeetleInfo(int id) {
        otherBeetleService.deleteOtherBeetleInfo(id);
        return Result.ok();
    }

    @PostMapping("/other_beetle")
    public Object addOtherBeetleInfo(String name) {
        otherBeetleService.addOtherBeetleInfo(name);
        return Result.ok();
    }

    @ApiOperation("")
    @GetMapping("/other_beetle/town")
    public Object getOtherBeetleListForTown(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return Result.ok(otherBeetleService.getOtherBeetleInfoListForTown(user.getAdcode()));
    }

    @ApiOperation("")
    @PostMapping("/other_beetle/town")
    public Object addOtherBeetleForTown(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleService.addOtherBeetleForTown(user.getAdcode(), beetleInfoId);
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/other_beetle/town")
    public Object deleteOtherBeetleForTown(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleService.deleteOtherBeetleInfoForTown(user.getAdcode(), beetleInfoId);
        return Result.ok();
    }

    @ApiOperation("")
    @GetMapping("/workContent/town")
    public Object getworkContentForTown2(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);

        return otherBeetleMapper.getWorkContentForTown(user.getAdcode());

    }

    @ApiOperation("")
    @PostMapping("/workContent/town")
    public Object addworkContentForTown2(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.insertWorkContentForTOwn(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/workContent/town")
    public Object deleteworkContentForTown2(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleMapper.deleteWorkContentFOrTown(beetleInfoId,user.getAdcode());
        return Result.ok();
    }

}
