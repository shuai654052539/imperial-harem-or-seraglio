package com.fh.shop.api.goods.util;


public class CookieKey {
    public static String userKey(String data){
        return "user:"+data;
    }
    public static String resourAllKey(String data){
        return "resource:"+data;
    }

    public static String caiDanKey(String data){
        return "caiDan:"+data;
    }
    public static String userResourceAllKey(String data){
        return "userResourceAll:"+data;
    }

    public static String yanZhengMaKey(String data){
        return "yanZhengMa:"+data;
    }
    public static String getCartValue(String data){
        return "redis_cart:"+data;
    }
    public static String getMemberValue(String data){
        return "member:"+data;
    }


}
