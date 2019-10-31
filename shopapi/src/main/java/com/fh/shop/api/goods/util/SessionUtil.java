package com.fh.shop.api.goods.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class SessionUtil {
    public static String getSessionId(HttpServletRequest request, HttpServletResponse response){
        String sessionId = CookieUtil.readCookei(request, SystemConst.SESSIONID);
        if(StringUtils.isEmpty(sessionId)){
            sessionId=UUID.randomUUID().toString();
            CookieUtil.writeCookei(response,SystemConst.SESSIONID,sessionId);
        }
        return sessionId;
    }
}
