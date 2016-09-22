package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Math;

/**
 * Created by Ridiculous on 2016/5/26.
 */
public class Tools {
    public static String genStreamNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String no = "0000000000000000000000000000000000000000000"
                + sdf.format(new Date());
        return no;
    }

    public static String genVerCode() {
        int s = (int) (Math.random() * (9999 - 1000 + 1)) + 10000;
        return String.valueOf(s);
    }

    public static String genSecret(String id) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String atTime = sdf.format(new java.util.Date(System.currentTimeMillis()));
        return utils.Encrypt.MD5(atTime+id).substring(12);
    }

    public static String genOrderId(String str) {
        int s = (int) (Math.random() * (999999 - 100000 + 1)) + 1000000;
        return str + String.valueOf(s);
    }
    public static String getTimestamp() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String atTime = sdf.format(new java.util.Date(System.currentTimeMillis()));
        return atTime;
    }
    public static String checkMdn(String ip, String port) {
        String username = "ismp";
        String secret = "Ismp@0527";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new java.util.Date(System.currentTimeMillis()));
        String password = utils.Encrypt.MD5(username+secret+timestamp).toUpperCase();
        String address = "202.102.120.121";
        String messageId = timestamp + timestamp.substring(8);

        String url = "http://221.228.17.38:9002/Business/AskUserOnLineInfo";
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("Address", address);
        jsonParams.put("MessageID", messageId);
        jsonParams.put("Password", password);
        jsonParams.put("Username", username);
        jsonParams.put("OperType", "AskMDNByIP");
        jsonParams.put("PublicIP", ip);
        jsonParams.put("PublicPort", port);
        jsonParams.put("ReqTime", timestamp);
        JSONObject result = HttpRequest.doJsonPost(url, jsonParams);
        System.out.println(result);
        if (!result.equals("")) {
            if (result.getString("ResultCode").equals("0")) {
                JSONArray Resultinfo = JSON.parseArray(result.getString("ResultInfo"));
                JSONObject resultjson = (JSONObject) JSON.toJSON(Resultinfo.get(0));
                return resultjson.getString("Mdn");
            }
            else {
                return "";
            }
        }
        return "";
    }
}
