package cn.huihongcloud.controller.newStatic;

import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.statistics.InputEntity;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.entity.workerStatic;
import cn.huihongcloud.mapper.AnalysisMapper;
import cn.huihongcloud.mapper.NewStaticMapper;
import cn.huihongcloud.service.UserService;
import cn.huihongcloud.util.StatisticsUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/statics")
public class newStatic {
    @Autowired
    UserService userService;
    @Autowired
    AnalysisMapper analysisMapper;
    @Autowired
    NewStaticMapper newStaticMapper;

    @RequestMapping("/area")
    public Object getAreaStatic(@RequestParam String ProjectAdminName,@RequestParam(required = false) String startDate,@RequestParam(required = false) String endDate){
        User user = userService.getUserByUserName(ProjectAdminName);
        User user1 = userService.getUserByUserName(user.getParent());
        return analysisMapper.getAreaStatic(user1.getUsername(),startDate,endDate);
    }
    @RequestMapping("/month")
    public Object getMonthStatic(@RequestParam String ProjectAdminName,@RequestParam(required = false) String startM,@RequestParam(required = false) String endM){
        User user = userService.getUserByUserName(ProjectAdminName);
        User user1 = userService.getUserByUserName(user.getParent());
        return analysisMapper.getMonthStatic(startM,endM,user1.getUsername());

    }

    @RequestMapping("/batch")
    public Object getBatchStatic(@RequestParam String ProjectAdminName,@RequestParam(required = false) String startM,@RequestParam(required = false) String endM){
        User user = userService.getUserByUserName(ProjectAdminName);
        User user1 = userService.getUserByUserName(user.getParent());
        return analysisMapper.getBatchStatic(startM,endM,user1.getUsername());

    }



    @RequestMapping("/worker")
    public Object getWorkerStatic(@RequestParam String ProjectAdminName,@RequestParam(required = false) String startM,@RequestParam(required = false) String endM) throws ParseException {
        User user = userService.getUserByUserName(ProjectAdminName);
        User user1 = userService.getUserByUserName(user.getParent());



        List<workerStatic> workerStaticList = analysisMapper.getWorkerStatic(startM,endM,user1.getUsername());
        Collections.sort(workerStaticList);


        String name = workerStaticList.get(0).getWorkerName();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date min = null;
        Date max = null;

        try {
            max = simpleDateFormat.parse(workerStaticList.get(0).getCurrentDate());
            min = simpleDateFormat.parse(workerStaticList.get(0).getCurrentDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //必须捕获异常


        for (int i = 0; i < workerStaticList.size();i++){
            System.out.println("数据出来");
            workerStatic ws = workerStaticList.get(i);
            System.out.println(ws.getNum());
            System.out.println(ws.getCurrentDate());
            System.out.println(ws.getWorkerName());
            System.out.println("数据结束");
        }

        workerStatic lastWs = null;
        workerStatic ws = null;

        lastWs = workerStaticList.get(0);
        ws=workerStaticList.get(1);
        int workDay = 1;

        JSONArray jsonArray = new JSONArray();

        //这里不知道为啥不行
        
        for (int i = 0 ; i < workerStaticList.size();){
            for (int j = i + 1 ; j < workerStaticList.size();j++){
                if(workerStaticList.get(i).getWorkerName().equals(workerStaticList.get(j).getWorkerName())){
                    System.out.println("进入IF");
                    workDay++;
                    workerStaticList.get(i).setNum(workerStaticList.get(i).getNum() + workerStaticList.get(j).getNum());
                }else {
                    System.out.println("进入ELSE");
                    JSONObject jsonObject = new JSONObject();
                    System.out.println("数据传输");
                    System.out.println(workerStaticList.get(i).getWorkerName());


                    jsonObject.put("Worker",workerStaticList.get(i).getWorkerName());
                    jsonObject.put("Num",workerStaticList.get(i).getNum());
                    jsonObject.put("day",workDay);
                    jsonObject.put("Avg",workerStaticList.get(i).getNum()*1.0/workDay);
                    workDay = 1;
                    jsonArray.add(jsonObject);
                    i = j;
//                    break;
                }
            }
        }

        return jsonArray;

    }

    @RequestMapping("/Desc")
    public Object getDes(@RequestAttribute("username") String user, @RequestParam(required = false)  String startDate, @RequestParam(required = false) String endDate,
                         String manager){
        User user1 = userService.getUserByUserName(manager);
        System.out.println(manager);
        User user2 = userService.getUserByUserName(user1.getParent());
        System.out.println(user2.getUsername());

        System.out.println(startDate);
        System.out.println(endDate);

        String dateString = null;

        if(endDate!=null){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos = new ParsePosition(0);
            Date currentTime_2 = formatter.parse(endDate, pos);

            currentTime_2.setTime(currentTime_2.getTime() + 24*3600*1000);

            System.out.println(currentTime_2.getDate());

            dateString = formatter.format(currentTime_2);

            System.out.println(dateString);

        }



        List<InputEntity> inputEntityForWorker =  newStaticMapper.getInputEntityForWorker(user2.getUsername(),startDate,dateString);

        System.out.println(inputEntityForWorker.size());
        for (InputEntity e:
             inputEntityForWorker) {
            System.out.println(e.getCustomtown());

        }
        if (inputEntityForWorker.isEmpty() || inputEntityForWorker.size()<2){
            return Result.failed();
        }
        return Result.ok(StatisticsUtil.getResult(inputEntityForWorker));
    }

}
