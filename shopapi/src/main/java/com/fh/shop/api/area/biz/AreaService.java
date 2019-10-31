package com.fh.shop.api.area.biz;

import com.fh.shop.api.area.po.Member;
import com.fh.shop.api.goods.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AreaService {
    ServerResponse get(Integer id);

    ServerResponse getCode(String phone, HttpServletRequest request, HttpServletResponse response) throws IOException;

    ServerResponse addMember(Member member, String oldPassWord, String code);

  ServerResponse login(Member member);

  ServerResponse logins(String phone, String yanzhengma);
}
