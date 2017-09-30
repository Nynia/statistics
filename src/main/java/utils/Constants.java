package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ridiculous on 2016/5/26.
 */
public class Constants {
    public final static String DeviceID = "021155";
    public final static Map<String, String> resultCodeMap = new HashMap<String, String>();
    public final static Map<String, String> channelMap = new HashMap<String, String>();
    public final static Map<String, String> selfMusicProductMap = new HashMap<String, String>();
    public final static Map<String, String> ringCpMap = new HashMap<String, String>();
    public final static Map<String, String> cpMap = new HashMap<String, String>();
    static {
        resultCodeMap.put("0", "成功");
        resultCodeMap.put("103", "用户号码非法");
        resultCodeMap.put("108", "用户信息不存在");
        resultCodeMap.put("115", "用户已注销");
        resultCodeMap.put("116", "产品ID非法");
        resultCodeMap.put("118", "订购方用户号码非法");
        resultCodeMap.put("125", "用户状态不正常");
        resultCodeMap.put("126", "用户余额不足");
        resultCodeMap.put("127", "业务状态不正常");
        resultCodeMap.put("128", "产品状态不正常");
        resultCodeMap.put("130", "订购关系不存在");
        resultCodeMap.put("131", "订购关系已存在");
        resultCodeMap.put("132", "订购关系不正常");
        resultCodeMap.put("136", "黑名单用户");
        resultCodeMap.put("150", "参数非法");
        resultCodeMap.put("151", "IP非法");
        resultCodeMap.put("152", "暂未开通该功能");
        resultCodeMap.put("153", "token错误");
        resultCodeMap.put("154", "验证码超时");
        resultCodeMap.put("155", "验证码错误");
        resultCodeMap.put("156", "URL错误");
        resultCodeMap.put("157", "请求超时");
        resultCodeMap.put("158", "验证码发送失败");
        resultCodeMap.put("159", "获取号码失败");
        resultCodeMap.put("160", "产品ID未登记");
        resultCodeMap.put("999", "其他");

        //channelMap
        channelMap.put("5","爱音乐");
        channelMap.put("6","欣网");
        channelMap.put("35","vsop");
        channelMap.put("95","批操作");
        channelMap.put("0","SP");
        channelMap.put("38","SP");

        selfMusicProductMap.put("8203001387", "彩铃管家个人版");
        selfMusicProductMap.put("8209001391", "彩铃管家-讯飞版8元");
        selfMusicProductMap.put("8203001383", "彩铃管家10元档");
        selfMusicProductMap.put("8203001380", "彩铃管家15元档");
        selfMusicProductMap.put("8209001392", "彩铃管家-讯飞版15元");
        selfMusicProductMap.put("8203001381", "彩铃管家25元档");
        selfMusicProductMap.put("8203001382", "彩铃管家35元档");
        selfMusicProductMap.put("8208001388", "明星播报");
        selfMusicProductMap.put("8208001393", "明星播报默认铃音盒");
        selfMusicProductMap.put("8208001390", "明星播报8元版");
        selfMusicProductMap.put("8208001389", "明星播报15元版");
        selfMusicProductMap.put("810099991095", "潮流快递特惠铃音盒");
        selfMusicProductMap.put("810099990489", "最江南铃音盒");
        selfMusicProductMap.put("810099990490", "潮流快递铃音盒");
        selfMusicProductMap.put("8210001395", "一起秀铃音盒");
        selfMusicProductMap.put("8210001394", "翼企秀铃音盒");

        ringCpMap.put("8203001387", "8203");
        ringCpMap.put("8209001391", "8209");
        ringCpMap.put("8203001383", "8203");
        ringCpMap.put("8203001380", "8203");
        ringCpMap.put("8209001392", "8209");
        ringCpMap.put("8203001381", "8203");
        ringCpMap.put("8203001382", "8203");
        ringCpMap.put("8208001388", "8208");
        ringCpMap.put("8208001393", "8208");
        ringCpMap.put("8208001390", "8208");
        ringCpMap.put("8208001389", "8208");
        ringCpMap.put("810099991095", "2062");
        ringCpMap.put("810099990489", "2062");
        ringCpMap.put("810099990490", "2062");
        ringCpMap.put("8210001395", "8210");
        ringCpMap.put("8210001394", "8210");

        cpMap.put("8203", "汇龙");
        cpMap.put("8209", "科大");
        cpMap.put("8208", "杰度");
        cpMap.put("2062", "江苏公信");
        cpMap.put("8210", "海春轩");
        cpMap.put("8702", "音信通");
        cpMap.put("8703", "企业管家");
    }
}

