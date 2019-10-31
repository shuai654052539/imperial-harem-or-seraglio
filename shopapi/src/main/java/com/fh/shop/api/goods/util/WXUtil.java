package com.fh.shop.api.goods.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fh.shop.api.sdk.MyConfig;
import com.github.wxpay.sdk.WXPay;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 */
public class WXUtil {

  /**
   *
   * @return
   */
     public static Map<String,String>  WXPayExample(String outtradeno, BigDecimal bigDecimal){
       MyConfig config = null;
       Map<String, String> resp = null;
       try {
         config = new MyConfig();
       } catch (Exception e) {
         e.printStackTrace();
       }
       WXPay wxpay = new WXPay(config);

       Map<String, String> data = new HashMap<String, String>();
       data.put("body", "腾讯充值中心-edrtfygutfQQ会员充值");
       data.put("out_trade_no", outtradeno);
       data.put("device_info", "");
       data.put("fee_type", "CNY");
       data.put("total_fee", bigDecimal.multiply(new BigDecimal("100")).intValue()+"");
       /*data.put("spbill_create_ip", "123.12.12.123");*/
       data.put("notify_url", "http://www.example.com/wxpay/notify");
       data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
       data.put("time_expire", DateUtil.date2src(DateUtils.addMinutes(new Date(),5),DateUtil.YYYYMMDDHHMMSS));  // 此处指定为扫码支付
       try {
         resp = wxpay.unifiedOrder(data);
         System.out.println(resp);
       } catch (Exception e) {
         e.printStackTrace();
       }
       return resp;
     }


}
