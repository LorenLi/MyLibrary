package com.loren.textlibrary.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Loren on 2017/4/28 16:12.
 * 不是邮箱格式验证，适合场景：获取邮箱类型（比如qq,126,gmail），然后跳入相应官网
 *
 Intent intent2 = new Intent();
 intent2.setAction("android.intent.action.VIEW");
 String email11 = "1321914113@126.com";
 String [] strs = email11.split("@");
 if(strs != null){
 String end = strs[strs.length - 1];
 if(Constant.emailMaps.containsKey(end)){
 String value = Constant.emailMaps.get(end);
 Uri content_url = Uri.parse(value);
 intent2.setData(content_url);
 startActivity(intent2);
 }
 }
 */

public class EmailUtils {

    public static Map<String, String> emailMaps = new HashMap<String, String>();

    static {
        emailMaps.put("qq.com", "http://mail.qq.com");
        emailMaps.put("gmail.com", "http://mail.google.com");
        emailMaps.put("sina.com", "http://mail.sina.com.cn");
        emailMaps.put("163.com", "http://mail.163.com");
        emailMaps.put("126.com", "http://mail.126.com");
        emailMaps.put("yeah.net", "http://www.yeah.net/");
        emailMaps.put("sohu.com", "http://mail.sohu.com/");
        emailMaps.put("tom.com", "http://mail.tom.com/");
        emailMaps.put("sogou.com", "http://mail.sogou.com/");
        emailMaps.put("139.com", "http://mail.10086.cn/");
        emailMaps.put("hotmail.com", "http://www.hotmail.com");
        emailMaps.put("live.com", "http://login.live.com/");
        emailMaps.put("live.cn", "http://login.live.cn/");
        emailMaps.put("live.com.cn", "http://login.live.com.cn");
        emailMaps.put("189.com", "http://webmail16.189.cn/webmail/");
        emailMaps.put("yahoo.com.cn", "http://mail.cn.yahoo.com/");
        emailMaps.put("yahoo.cn", "http://mail.cn.yahoo.com/");
        emailMaps.put("eyou.com", "http://www.eyou.com/");
        emailMaps.put("21cn.com", "http://mail.21cn.com/");
        emailMaps.put("188.com", "http://www.188.com/");
        emailMaps.put("foxmail.coom", "http://www.foxmail.com");
    }
}
