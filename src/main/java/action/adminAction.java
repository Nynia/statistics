package action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.CpUser;
import entity.OutputList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.outputlistService;
import service.cpuserService;
import utils.queryCondition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Ridiculous on 2016/6/2.
 */
@Controller
public class adminAction {
    @Resource
    private outputlistService outputlistService;
    @Resource
    private cpuserService cpuserService;

    @RequestMapping("/home")
    @ResponseBody
    public ModelAndView home (HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        view.setViewName("home");
        return view;
    }
    @RequestMapping("/query")
    @ResponseBody
    public JSONArray query (HttpServletRequest request) throws ParseException {
        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        List<CpUser> cpUsers = cpuserService.getAllCpUsers();
        JSONArray jsonArray = new JSONArray();
        for (int i=0; i<cpUsers.size(); i++) {
            System.out.println(cpUsers.get(i).getCpId() + cpUsers.get(i).getCpCompanyName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            queryCondition condition = new queryCondition(sdf.parse(start_date), sdf.parse(end_date), cpUsers.get(i).getCpId());
            List<OutputList> outputLists = outputlistService.getOutputByDate(condition);
            int c1 = 0;
            int c2 = 0;
            int c3 = 0;
            for (int j=0; j<outputLists.size(); j++) {
                OutputList ol = outputLists.get(j);
                if (ol.getOrderstringType() == 1) {
                    c1 ++;
                }
                else if (ol.getOrderstringType() == 2) {
                    c2 ++;
                }
                else {
                    c3 ++;
                    String user_id = ol.getUserId();
                    for (int k=j-1; k>=0; k--) {
                        if (outputLists.get(k).getUserId().equals(user_id)) {
                            long t1 = outputLists.get(k).getOrderTime().getTime();
                            long t2 = outputLists.get(j).getOrderTime().getTime();
                            if (t2 - t1<6000) {
                                c3 --;
                                c2 --;
                            }
                            break;
                        }
                    }
                }

            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", cpUsers.get(i).getCpCompanyName());
            jsonObject.put("c1", String.valueOf(c1));
            jsonObject.put("c2", String.valueOf(c2));
            jsonObject.put("c3", String.valueOf(c3));
            jsonArray.add(jsonObject);
        }
        for (int i=0; i<jsonArray.size(); i++) {
            System.out.println(jsonArray.get(i).toString());
        }
        System.out.println(jsonArray.toString());
        return jsonArray;
    }
}
