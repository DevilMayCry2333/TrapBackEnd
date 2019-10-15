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
        JSONArray jsonArray = new JSONArray();

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



        int tian = -1;
        int cnt = 0;

        int Num = 0;

        String tmp = workerStaticList.get(0).getWorkerName();

        int myNum = workerStaticList.get(0).getNum();
        String myDate = workerStaticList.get(0).getCurrentDate();
        String myName = workerStaticList.get(0).getWorkerName();

        int flag = 0;


        for (workerStatic ws:workerStaticList){


            System.out.println(ws.getNum());
            System.out.println(ws.getCurrentDate());
            System.out.println(ws.getWorkerName());


            if(cnt == workerStaticList.size()-1){
//                if(tian>1)
//                    tian--;
                JSONObject jsonObject = new JSONObject();

//                System.out.println("====开始记录2====");
//                System.out.println(myName);
//                System.out.println(myNum);
//                System.out.println(tian);
//                System.out.println(ws.getNum()/tian);


                jsonObject.put("Worker",myName);
                jsonObject.put("Num", myNum);
                jsonObject.put("day",tian);
                jsonObject.put("Avg",myNum/tian);

//                System.out.println("******结束记录2*****");

//                tmp = ws.getWorkerName();
                tian = 1;
                jsonArray.add(jsonObject);

                myNum = ws.getNum();
                myDate = ws.getCurrentDate();
                myName = ws.getWorkerName();
                flag = 0;


            }


            if(tmp.equals(ws.getWorkerName())){
                if(tian==-1)
                    tian = 1;
                else
                    tian++;

//                System.out.println("===当前天数====");
//                System.out.println(myName);
//                System.out.println(myNum);
//                System.out.println(tian);
//                System.out.println(ws.getCurrentDate());
//                System.out.println("*****当前天数结束*****");
                flag = 1;


            }else{
//                if(tian>1)
//                    tian--;

                JSONObject jsonObject = new JSONObject();
//                System.out.println("=====开始记录===");
//                System.out.println(myName);
//                System.out.println(myNum);
//                System.out.println(tian);
//                System.out.println(ws.getNum()/tian);

                    jsonObject.put("Worker",myName);
                    jsonObject.put("Num", myNum);
                    jsonObject.put("day",tian);
                    jsonObject.put("Avg",myNum/tian);


                System.out.println("******结束记录*****");

                tmp = ws.getWorkerName();
                tian = 1;
                jsonArray.add(jsonObject);

                myNum = ws.getNum();
                myDate = ws.getCurrentDate();
                myName = ws.getWorkerName();

                flag = 0;


            }

            cnt++;

        }
//
//        for (workerStatic ws: workerStaticList) {
//
//            System.out.println("工人名字");
//            System.out.println(ws.getWorkerName());
//            System.out.println("工人数量");
//            System.out.println(ws.getNum());
//            System.out.println("工人日期");
//
//            System.out.println(ws.getCurrentDate());
//
//            JSONObject jsonObject = new JSONObject();
//
//
//
//            if(ws.getWorkerName().equals(name)){
//                tian++;
//                System.out.println("1:" + tian);
//                if(simpleDateFormat.parse(ws.getCurrentDate()).getTime() < min.getTime()){
//                    min = simpleDateFormat.parse(ws.getCurrentDate());
//
//                }
//                if(simpleDateFormat.parse(ws.getCurrentDate()).getTime() > max.getTime()){
//                    max = simpleDateFormat.parse(ws.getCurrentDate());
//                }
//
//                Num = ws.getNum();
//
//
//            }else {
//
//                System.out.println("2:" + tian);
//
//
//                System.out.println("=====天=====");
//                System.out.println(tian);
//
//                jsonObject.put("day",tian);
//
//                tian--;
//
//                System.out.println("====工人====");
//                System.out.println(name);
//
//                jsonObject.put("Worker",name);
//
//                System.out.println("====数量====");
//
//
//                if(Num>0){
//                    System.out.println(Num);
//                    jsonObject.put("Num", Num);
//                    long daySub = (max.getTime()-min.getTime())/1000/60/60/24;
//
//                    if(daySub<=1)
//                        daySub = 1;
//
//                    System.out.println(daySub);
//
//                    jsonObject.put("Avg",Num/daySub);
//
////                    Num = 0;
//
//                }else {
//                    System.out.println(ws.getNum());
//                    jsonObject.put("Num",ws.getNum());
//
//                    long daySub = (max.getTime()-min.getTime())/1000/60/60/24;
//                    if(daySub<=1)
//                        daySub = 1;
//
//                    System.out.println(daySub);
//                    jsonObject.put("Avg",ws.getNum()/daySub);
//
//
//                }
//
//                jsonArray.add(jsonObject);
//                tian = 1;
//                name = ws.getWorkerName();
//                min = simpleDateFormat.parse(ws.getCurrentDate());
//                max = simpleDateFormat.parse(ws.getCurrentDate());
//
//            }
//            if(cnt == workerStaticList.size()-1){
//
//
//                if(Num>0){
//                    System.out.println("===天====");
//                    System.out.println(tian);
//
//                    jsonObject.put("day",tian);
//
//                    System.out.println("===工人====");
//                    System.out.println(name);
//
//
//                    jsonObject.put("Worker",name);
//
//                    jsonObject.put("Num",Num);
//
//                    long daySub = (max.getTime()-min.getTime())/1000/60/60/24;
//                    if(daySub<=1)
//                        daySub = 1;
//                    System.out.println(daySub);
//                    jsonObject.put("Avg",Num/daySub);
////                    Num = 0;
//
//                }else {
//                    System.out.println("===天====");
//                    System.out.println(tian);
//
//                    jsonObject.put("day",tian);
//
//                    System.out.println("===工人====");
//                    System.out.println(name);
//
//
//                    jsonObject.put("Worker",name);
//
//                    jsonObject.put("Num",ws.getNum());
//
//                    long daySub = (max.getTime()-min.getTime())/1000/60/60/24;
//                    if(daySub<=1)
//                        daySub = 1;
//                    System.out.println(daySub);
//                    jsonObject.put("Avg",ws.getNum()/daySub);
//                }
//
//
//                jsonArray.add(jsonObject);
//                tian = 1;
//                name = ws.getWorkerName();
//                min = simpleDateFormat.parse(ws.getCurrentDate());
//                max = simpleDateFormat.parse(ws.getCurrentDate());
//            }
//            cnt++;
//        }

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
