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
import utils.*;

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
    @Resource
    private CrSubscribeService crSubscribeService;
    @Resource
    private  CrRingService crRingService;

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
    @RequestMapping(value = "/ringDetail")
    @ResponseBody
    public JSONObject ringDetail(Model model,HttpServletRequest request) {
        String ringid = request.getParameter("ringid");
        System.out.println(ringid);

        String begin = request.getParameter("start_date");
        String end = request.getParameter("end_date");

        List<CrSubscribe> crResultList = new ArrayList<CrSubscribe>();
        crResultList = crSubscribeService.getSubscribeinGivenTimeByRingid(ringid, begin, end);
        List<singleRingDetail> singleRingDetailList = new ArrayList<singleRingDetail>();
        Map<String,singleRingDetail> stringsingleRingDetailMap = new HashMap<String, singleRingDetail>();
        for (CrSubscribe item: crResultList) {
            singleRingDetail temp_singlering = null;
            String downmethod = item.getDownmethod().toString();
            if (stringsingleRingDetailMap.containsKey(downmethod)) {
                 temp_singlering = stringsingleRingDetailMap.get(downmethod);
            }
            else {
                temp_singlering = new singleRingDetail(item.getRingid(),item.getRingname(),item.getCpid(),
                        item.getCpname(),downmethod);
                temp_singlering.setChannelname(Constants.channelMap.get(downmethod));
            }
            switch (Integer.valueOf(item.getAreano().substring(0,4))) {
                case 1:
                    temp_singlering.setA1(temp_singlering.getA1()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 2:
                    temp_singlering.setA2(temp_singlering.getA2()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 3:
                    temp_singlering.setA3(temp_singlering.getA3()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 4:
                    temp_singlering.setA4(temp_singlering.getA4()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 5:
                    temp_singlering.setA5(temp_singlering.getA5()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 6:
                    temp_singlering.setA6(temp_singlering.getA6()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 7:
                    temp_singlering.setA7(temp_singlering.getA7()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 8:
                    temp_singlering.setA8(temp_singlering.getA8()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 9:
                    temp_singlering.setA9(temp_singlering.getA9()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 10:
                    temp_singlering.setA10(temp_singlering.getA10()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 11:
                    temp_singlering.setA11(temp_singlering.getA11()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 12:
                    temp_singlering.setA12(temp_singlering.getA12()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                case 13:
                    temp_singlering.setA13(temp_singlering.getA13()+1);
                    temp_singlering.setTotal(temp_singlering.getTotal() + 1);
                    break;
                default:
                    break;
            }
            stringsingleRingDetailMap.put(downmethod,temp_singlering);
        }
        JSONArray jsonArray = new JSONArray();
        for(Map.Entry<String, singleRingDetail> entry:stringsingleRingDetailMap.entrySet()) {
            singleRingDetail sr = entry.getValue();
            //singleRingDetailList.add(sr);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ringid", sr.getRingid());
            jsonObject.put("ringname", sr.getRingname());
            jsonObject.put("cpid", sr.getCpid());
            jsonObject.put("cpname", sr.getCpname());
            jsonObject.put("a1",sr.getA1());
            jsonObject.put("a2",sr.getA2());
            jsonObject.put("a3",sr.getA3());
            jsonObject.put("a4",sr.getA4());
            jsonObject.put("a5",sr.getA5());
            jsonObject.put("a6",sr.getA6());
            jsonObject.put("a7",sr.getA7());
            jsonObject.put("a8",sr.getA8());
            jsonObject.put("a9",sr.getA9());
            jsonObject.put("a10",sr.getA10());
            jsonObject.put("a11",sr.getA11());
            jsonObject.put("a12",sr.getA12());
            jsonObject.put("a13",sr.getA13());
            jsonObject.put("total",sr.getTotal());
            jsonObject.put("channel",sr.getChannelname());
            jsonArray.add(jsonObject);
        }
        JSONObject jsonresult = new JSONObject();

        jsonresult.put("channelinfos", jsonArray);
        System.out.println(jsonresult.toString());
        return jsonresult;
    }
    @RequestMapping("/query")
    @ResponseBody
    public JSONObject queryAmount(HttpServletRequest request) throws ParseException {
        String begintime = request.getParameter("start_date");
        String endtime = request.getParameter("end_date");
        JSONObject jsonResult = new JSONObject();

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
            if (user.getPrivilege().equals("3")) {
                JSONArray jsonArrayRing = new JSONArray();
                List<CrSubscribe> crSubscribeList = crSubscribeService.getSubscribeinGivenTime(begintime, endtime);
                List<crResult> crResultList = new ArrayList<crResult>();
                Map<String, crResult> stringcrResultMap = new HashMap<String, crResult>();
                for (CrSubscribe item:crSubscribeList) {
                    crResult crResult_t = null;
                    if (stringcrResultMap.containsKey(item.getRingid())) {
                        crResult_t = stringcrResultMap.get(item.getRingid());
                    }
                    else {
                        crResult_t = new crResult(item.getRingid(),item.getRingname(),item.getCpid(),item.getCpname());
                    }
                    //订购
                    if (item.getDowntime().compareTo(begintime) >= 0 && item.getDowntime().compareTo(endtime) <0) {
                        crResult_t.setGivenorderamount(crResult_t.getGivenorderamount() + 1);
                    }
                    //退订
                    if (item.getModdate() != null && item.getModdate().compareTo(begintime) >= 0 && item.getModdate().compareTo(endtime) < 0) {
                        crResult_t.setGivencancelamount(crResult_t.getGivencancelamount() + 1);
                    }
                    stringcrResultMap.put(item.getRingid(),crResult_t);
                }
                for(Map.Entry<String, crResult> entry:stringcrResultMap.entrySet()) {
                    crResult cr = entry.getValue();
                    int totalCount = crRingService.getCountByRingid(cr.getRingid());
                    if (totalCount ==0) {
                        System.out.println(cr.getRingid());
                    }
                    cr.setTotalamount(totalCount);
                    crResultList.add(cr);
                }
                Collections.sort(crResultList, new Comparator<crResult>() {
                    public int compare(crResult arg0, crResult arg1) {
                        return arg1.getGivenorderamount()-(arg0.getGivenorderamount());
                    }
                });
                for (crResult item:crResultList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ringid", item.getRingid());
                    jsonObject.put("ringname", item.getRingname());
                    jsonObject.put("cpid", item.getCpid());
                    jsonObject.put("cpname", item.getCpname());
                    jsonObject.put("givenorderamount", item.getGivenorderamount());
                    jsonObject.put("givencancelamount", item.getGivencancelamount());
                    jsonObject.put("totalamount",item.getTotalamount());
                    jsonArrayRing.add(jsonObject);
                }
                jsonResult.put("ringinfos", jsonArrayRing);
                return jsonResult;
            }
            begintime = begintime.replaceAll("[-\\s:]", "");
            endtime = endtime.replaceAll("[-\\s:]","");
            System.out.println(begintime);
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