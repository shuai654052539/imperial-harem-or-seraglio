package com.fh.shop.api.goods.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static String readCookei(HttpServletRequest request,String sessionId) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return "";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(sessionId)) {
                return cookie.getValue();
            }
        }
        return "";
    }
    public static void writeCookei(HttpServletResponse response,String sessionId,String value){
        Cookie cookie=new Cookie(sessionId,value);
        cookie.setDomain(SystemConst.DOMAIN);
        cookie.setPath("/");
        cookie.setSecure(false);
        response.addCookie(cookie);
    }
}
