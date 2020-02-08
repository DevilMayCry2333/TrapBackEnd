package cn.huihongcloud.controller.newStatic;

import cn.huihongcloud.entity.WorkerStatic;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.statistics.InputEntity;
import cn.huihongcloud.entity.user.User;
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
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public Object getAreaStatic(@RequestParam String ProjectAdminName, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        User user = userService.getUserByUserName(ProjectAdminName);
        User user1 = userService.getUserByUserName(user.getParent());
        return analysisMapper.getAreaStatic(user1.getUsername(), startDate, endDate);
    }

    @RequestMapping("/month")
    public Object getMonthStatic(@RequestParam String ProjectAdminName, @RequestParam(required = false) String startM, @RequestParam(required = false) String endM) {
        User user = userService.getUserByUserName(ProjectAdminName);
        User user1 = userService.getUserByUserName(user.getParent());
        return analysisMapper.getMonthStatic(startM, endM, user1.getUsername());

    }

    @RequestMapping("/batch")
    public Object getBatchStatic(@RequestParam String ProjectAdminName, @RequestParam(required = false) String startM, @RequestParam(required = false) String endM) {
        User user = userService.getUserByUserName(ProjectAdminName);
        User user1 = userService.getUserByUserName(user.getParent());
        return analysisMapper.getBatchStatic(startM, endM, user1.getUsername());

    }


    @RequestMapping("/worker")
    public Object getWorkerStatic(@RequestParam String ProjectAdminName, @RequestParam(required = false) String startM, @RequestParam(required = false) String endM) throws ParseException {
        User user = userService.getUserByUserName(ProjectAdminName);
        User user1 = userService.getUserByUserName(user.getParent());
        List<WorkerStatic> workerStaticList = analysisMapper.getWorkerStatic(startM, endM, user1.getUsername());

        String name = workerStaticList.get(0).getWorkerName();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date min = null;
        Date max = null;

        try {
            max = simpleDateFormat.parse(workerStaticList.get(0).getCurrentDate());
            min = simpleDateFormat.parse(workerStaticList.get(0).getCurrentDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //必须捕获异常


        for (int i = 0; i < workerStaticList.size(); i++) {

            WorkerStatic ws = workerStaticList.get(i);




        }
        workerStaticList.forEach(ws -> {


        });

        WorkerStatic lastWs = null;
        WorkerStatic ws = null;

//        lastWs = workerStaticList.get(0);
//        ws = workerStaticList.get(1);
        int workDay = 1;

        JSONArray jsonArray = new JSONArray();

        workerStaticList.stream().collect(Collectors.groupingBy(WorkerStatic::getWorkerName)).forEach((worker,list) -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Worker", worker);
            int day = list.size();
            jsonObject.put("WorkRealName",list.stream().map(WorkerStatic::getRealName));
            jsonObject.put("day", day);
            double num = list.stream().mapToDouble(WorkerStatic::getNum).sum();
            double num1 = list.stream().mapToDouble(WorkerStatic::getNum1).sum();
            double num2 = list.stream().mapToDouble(WorkerStatic::getNum2).sum();
            double num3 = list.stream().mapToDouble(WorkerStatic::getNum3).sum();
            double num5 = list.stream().mapToDouble(WorkerStatic::getNum5).sum();
            jsonObject.put("Num", num);
            jsonObject.put("Num1",num1);
            jsonObject.put("Num2",num2);
            jsonObject.put("Num3",num3);
            jsonObject.put("Avg", num / day);
            jsonObject.put("Avg1",num3 / day);
            jsonObject.put("distinctId",num5);
            jsonArray.add(jsonObject);
        });

        jsonArray.sort(Comparator.comparing(obj -> (String) ((JSONObject) obj).get("Worker")));

        return jsonArray;
    }

    @RequestMapping("/Desc")
    public Object getDes(@RequestAttribute("username") String user, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate,
                         String manager) {
        User user1 = userService.getUserByUserName(manager);

        User user2 = userService.getUserByUserName(user1.getParent());





        String dateString = null;

        if (endDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos = new ParsePosition(0);
            Date currentTime_2 = formatter.parse(endDate, pos);

            currentTime_2.setTime(currentTime_2.getTime() + 24 * 3600 * 1000);



            dateString = formatter.format(currentTime_2);



        }


        List<InputEntity> inputEntityForWorker = newStaticMapper.getInputEntityForWorker(user2.getUsername(), startDate, dateString);


        for (InputEntity e :
                inputEntityForWorker) {


        }
        if (inputEntityForWorker.isEmpty() || inputEntityForWorker.size() < 2) {
            return Result.failed();
        }
        return Result.ok(StatisticsUtil.getResult(inputEntityForWorker));
    }

}
