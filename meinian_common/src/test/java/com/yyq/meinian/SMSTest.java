package com.yyq.meinian;

import com.yyq.meinian.utils.HttpUtils;
import com.yyq.meinian.utils.SMSUtils;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: SMSTest
 * @Author yyq
 * @Date: 2021/11/3 18:54
 * @Version 1.0
 */
public class SMSTest {



    public void testSMSUtils() throws Exception {
        //17671274907
        SMSUtils.sendShortMessage("17671274907","xlj");
    }


    public  void testMSM() {
        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";
        String appcode = "64787a04eee44b2299fd2b2808215978";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "15171208342");
        querys.put("param", "code:2000720y");
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
