package com.fh.shop.api.goods.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class  WangYiYunUtil {

    //发送验证码的请求路径URL
    private static final String
            SERVER_URL = "https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String
            APP_KEY = "a071ea92f96be819bf492a6f31b51b9e";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET = "4993af299dce";
    //随机数
    private static final String NONCE = "123456";

//短信模板ID
    //private static final String TEMPLATEID="3057527";

    //验证码长度，范围4～10，默认为4
    private static final String CODELEN = "6";

    public static String testDuanxin(String phone) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(SERVER_URL);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        /*
         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
         */
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("mobile", phone));
        nvps.add(new BasicNameValuePair("codeLen", CODELEN));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        /*
         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
         * 2.具体的code有问题的可以参考官网的Code状态表
         */
        String s = EntityUtils.toString(response.getEntity(), "utf-8");

        String stateCode = JSON.parseObject(s).getString("obj");

        return stateCode;

    }


    public static String httpClient(String url, Map<String,String> header,Map<String,String> parameter){
        CloseableHttpClient build = null;
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            build = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
        //设置头信息
        if (header != null && header.size() > 0) {
        Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            httpPost.addHeader(key,value);
        }
        }
        //设置参数信息
        if (parameter != null && parameter.size() > 0){
        List<BasicNameValuePair> list = new ArrayList();
        Iterator<Map.Entry<String, String>> iterator1 = parameter.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, String> next = iterator1.next();
            String key = next.getKey();
            String value = next.getValue();
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair(key, value);
            list.add(basicNameValuePair);
        }
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            response = build.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != response ) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpPost ) {
                httpPost.releaseConnection();
            }
            if (null != build ) {
                try {
                    build.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String sendMsg(String phone){
        //设置头信息
        Map<String,String> header = new HashMap();
        header.put("AppKey",APP_KEY);
        header.put("Nonce",NONCE);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        header.put("CurTime",curTime);
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);
        header.put("CheckSum",checkSum);
        header.put("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        //设置参数
        Map<String,String> parameter = new HashMap();
        parameter.put("mobile",phone);
        parameter.put("codeLen",CODELEN);
        //
        String result = httpClient(SERVER_URL, header, parameter);
        SMSCode smsCode = JSONObject.parseObject(result, SMSCode.class);

        return result;
    }

}


