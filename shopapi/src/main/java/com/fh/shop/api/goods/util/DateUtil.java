package com.fh.shop.api.goods.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtil {
    public static final String  Y_M_D="yyyy-MM-dd";
    public static final String  FULL_YEAR="yyyy-MM-dd HH:mm:ss";
    public static final String  XUEHUATIME = "yyyyMMddHHmm";
    public static final String  YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String date2src(Date date,String pattern){
        if(date==null){
            return "";
        }
        SimpleDateFormat sim=new SimpleDateFormat(pattern);
        String format = sim.format(date);
        return format;
    }

    public static final Date str2Date(String date,String pattern){
        if(StringUtils.isEmpty(date)){
            return null;
        }
        SimpleDateFormat sim=new SimpleDateFormat(pattern);
        Date parse = null;
        try {
            parse = sim.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;

    }

    public static final String date2src(){
        DateTimeFormatter yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String format = LocalDateTime.now().format(yyyyMMddHHmm);
        return format + IdWorker.getId();
    }
}
