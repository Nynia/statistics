package action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.*;
import utils.FeeResult;
import utils.QueryResult;
import utils.spResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ridiculous on 2016/6/2.
 */
@Controller
public class adminAction {
    @Resource
    private UserService userService;
    @Resource
    private SpinfoSerivce spinfoSerivce;
    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private SubscribeInfoService subscribeInfoService;
    @Resource
    private SubscribeRecordService subscribeRecordService;

    @RequestMapping("/home")
    @ResponseBody
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.setViewName("home");
        return view;
    }
    @RequestMapping(value = "/productDetail/{productid}")

    public String productDetail(@PathVariable("productid") String productid, Model model) {
        System.out.println(productid);
        int[][] result = new int[30][3];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd000000");
        Calendar cal = Calendar.getInstance();//获取当前日期
        String today = sdf.format(cal.getTime());

        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -31);
        String last30Days = sdf.format(cal.getTime());

        List<String> serviceIds = new ArrayList<String>();
        serviceIds.add(productid);
        List<FocusedSubscribeRecord> SubscribeRecords =
                subscribeRecordService.getRecordInGivenTimeByServiceIds(serviceIds, last30Days, today);
        for (FocusedSubscribeRecord record: SubscribeRecords) {
            for (int i=1;i<=30; i++) {
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -1*i);
                String certaindaystart = sdf.format(cal.getTime());
                cal.add(Calendar.DATE, 1);
                String cetaindayend = sdf.format(cal.getTime());
                if (record.getRectime().compareTo(certaindaystart) >=0 && record.getRectime().compareTo(cetaindayend) <0) {
                    switch (Integer.parseInt(record.getSubtype())) {
                        case 1:
                            result[30-i][0] ++;
                            break;
                        case 2:
                            result[30-i][1] ++;
                            break;
                        case 3:
                            result[30-i][2] ++;
                            break;
                        default:
                            break;
                    }

                }
            }
        }
        FocusedProductInfo productInfo = productInfoService.getProductByProductId(productid);
        String productName = productInfo.getServicename();
        String orderflag = productInfo.getOrderflag();
        model.addAttribute("productid", productid);
        model.addAttribute("result", result);
        model.addAttribute("productname", productName);
        model.addAttribute("orderflag", orderflag);
        model.addAttribute("totaluser",subscribeInfoService.getUserCountByServiceId(productid));
        return "detail";
    }

    @RequestMapping("/query")
    @ResponseBody
    public JSONObject queryAmount(HttpServletRequest request) throws ParseException {
        String begintime = request.getParameter("start_date").replaceAll("-", "");
        String endtime = request.getParameter("end_date").replaceAll("-", "");

        String username = (String) request.getSession().getAttribute("username");
        System.out.println(username);
        System.out.println(begintime);
        System.out.println(endtime);

        User user = userService.getUserByName(username);

        Map<String, QueryResult> queryResultMap = new HashMap<String, QueryResult>();

        List<FocusedSpInfo> focusedSpInfos = new ArrayList<FocusedSpInfo>();
        List<FocusedProductInfo> focusedProductInfos = new ArrayList<FocusedProductInfo>();
        if (user != null) {
            String splist = user.getSplist();
            String[] sp_list = splist.split(",");
            System.out.println(sp_list);

            for (String item : sp_list) {
                //productid
                if (item.length() == 21) {
                    String spid = productInfoService.getSpIdByProductId(item);
                    FocusedSpInfo spInfo = spinfoSerivce.getSpinfoBySpid(spid);
                    int flag = 0;
                    if (spInfo != null) {
                        for (FocusedSpInfo fi : focusedSpInfos) {
                            if (fi.getSpid().equals(spid)) {
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 0)
                            focusedSpInfos.add(spInfo);
                    }
                    FocusedProductInfo focusedProductInfo = productInfoService.getProductByProductId(item);
                    if (focusedProductInfo != null) {
                        focusedProductInfos.add(focusedProductInfo);
                    }
                }
                //spid
                else {
                    FocusedSpInfo spInfo = spinfoSerivce.getSpinfoBySpid(item);
                    if (spInfo != null) {
                        focusedSpInfos.add(spInfo);
                        List<FocusedProductInfo> productInfos = productInfoService.getProductsBySpId(item);
                        for (int i = 0; i < productInfos.size(); i++) {
                            focusedProductInfos.add(productInfos.get(i));
                        }
                    }
                }
            }
            System.out.println(focusedProductInfos);

            //处理订购记录
            List<String> serviceIds = new ArrayList<String>();
            for (int i = 0; i < focusedProductInfos.size(); i++) {
                serviceIds.add(focusedProductInfos.get(i).getServiceid());
            }
            List<FocusedSubscribeRecord> SubscribeRecords =
                    subscribeRecordService.getRecordInGivenTimeByServiceIds(serviceIds, begintime, endtime);
            for (FocusedSubscribeRecord record : SubscribeRecords) {
                QueryResult queryResult = null;
                if (!queryResultMap.containsKey(record.getServiceid())) {
                    String orderflag = record.getSubtype().equals("3") ? "点播" : "包月";
                    queryResult = new QueryResult(record.getServiceid(), "", record.getSpid(), "", orderflag);
                } else {
                    queryResult = queryResultMap.get(record.getServiceid());
                }
                if (record.getSubtype().equals("1")) {
                    //订购
                    queryResult.addGivenOrderAmount();
                } else if (record.getSubtype().equals("2")) {
                    //退订
                   queryResult.addGivenCancelAmount();
                } else {
                    //点播
                   queryResult.addGivenFeeAmount();
                }
                queryResultMap.put(record.getServiceid(), queryResult);
            }

            List<FeeResult> subscribeInfoResults = subscribeInfoService.getFeeResult();
            for (FeeResult item : subscribeInfoResults) {
                QueryResult queryResult = null;
                if (!queryResultMap.containsKey(item.getProductId())) {
                    queryResult = new QueryResult(item.getProductId(), "", "", "", "");
                } else {
                    queryResult = queryResultMap.get(item.getProductId());
                }
                queryResult.setTotalUserAmount(item.getUserAmount());
            }

            for (Map.Entry<String, QueryResult> entry : queryResultMap.entrySet()) {
                System.out.println(entry.getKey() + "--->" + entry.getValue().getMonthOrderAmount()
                        + "--->" + entry.getValue().getMonthCancelAmount()
                        + "--->" + entry.getValue().getTotalUserAmount()
                        + "--->" + entry.getValue().getMonthFeeAmount());
            }
            //model.addAttribute("queryreuslts", queryResultMap);
            List<spResult> spResultList = new ArrayList<spResult>();
            List<QueryResult> productResultList = new ArrayList<QueryResult>();

            for (Map.Entry<String, QueryResult> entry : queryResultMap.entrySet()) {
                QueryResult qr = entry.getValue();
                for (int i = 0; i < focusedProductInfos.size(); i++) {
                    FocusedProductInfo pi = focusedProductInfos.get(i);
                    if (pi.getServiceid().equals(qr.getProductId())) {
                        qr.setProductName(pi.getServicename());
                    }
                }
                for (int i = 0; i < focusedSpInfos.size(); i++) {
                    FocusedSpInfo si = focusedSpInfos.get(i);
                    if (si.getSpid().equals(qr.getSpId())) {
                        qr.setSpName(si.getSpname());
                    }
                }
                if (qr.getGivenCancelAmount() + qr.getGivenOrderAmount() + qr.getGivenFeeAmount() > 5) {
                    productResultList.add(qr);
                }
            }
            for (FocusedSpInfo item : focusedSpInfos) {
                spResult sr = new spResult(item.getSpname());
                for (int i = 0; i < productResultList.size(); i++) {
                    QueryResult pr = productResultList.get(i);
                    if (pr.getSpId().equals(item.getSpid())) {
                        sr.setMonthlyfeeamount(sr.getMonthlyfeeamount() + pr.getMonthFeeAmount());
                        sr.setMonthlyorderamount(sr.getMonthlyorderamount() + pr.getMonthOrderAmount());
                        sr.setMonthlycancelamount(sr.getMonthlycancelamount() + pr.getMonthCancelAmount());

                        sr.setGivenfeeamount(sr.getGivenfeeamount() + pr.getGivenFeeAmount());
                        sr.setGivenorderamount(sr.getGivenorderamount()+ pr.getGivenOrderAmount());
                        sr.setGivencancelamount(sr.getGivencancelamount() + pr.getGivenCancelAmount());
                    }
                }
                spResultList.add(sr);
            }
            //model.addAttribute("spinfos", spResultList);
            //model.addAttribute("productinfos", productResultList);
            System.out.println(spResultList);
            JSONObject jsonResult = new JSONObject();
            JSONArray jsonArraySp = new JSONArray();
            JSONArray jsonArrayProduct = new JSONArray();
            for (spResult sr:spResultList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("spname", sr.getSpname());
                jsonObject.put("orderamount", sr.getGivenorderamount());
                jsonObject.put("cancelamount", sr.getGivencancelamount());
                jsonObject.put("feeamount", sr.getGivenfeeamount());

                jsonArraySp.add(jsonObject);
            }
            for (QueryResult qr:productResultList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("productname", qr.getProductName());
                jsonObject.put("productid", qr.getProductId());
                jsonObject.put("type", qr.getOrderFlag());
                jsonObject.put("spname", qr.getSpName());
                jsonObject.put("totaluser", qr.getTotalUserAmount());
                jsonObject.put("orderamount", qr.getGivenOrderAmount());
                jsonObject.put("cancelamount", qr.getGivenCancelAmount());
                jsonObject.put("feeamount",qr.getGivenFeeAmount());

                jsonArrayProduct.add(jsonObject);
            }
            System.out.println(jsonArrayProduct.toString());
            System.out.println(jsonArraySp.toString());
            jsonResult.put("spinfos", jsonArraySp);
            jsonResult.put("productinfos", jsonArrayProduct);
            System.out.println(jsonResult.toString());
            return jsonResult;
        }
        return null;
    }

    @RequestMapping("/queryProduct")
    @ResponseBody
    public List<FocusedProductInfo> queryProduct(HttpServletRequest request) {
        String spid = request.getParameter("spid");
        return productInfoService.getProductsBySpId(spid);
    }
}