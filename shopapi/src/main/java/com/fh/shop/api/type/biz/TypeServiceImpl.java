package com.fh.shop.api.type.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.goods.common.ServerResponse;
import com.fh.shop.api.goods.util.JedisUtil;
import com.fh.shop.api.type.mapper.TypeDao;
import com.fh.shop.api.type.po.Type;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("typeService")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;


    @Override
    public ServerResponse queryTypeTree() {
        String typeList = JedisUtil.get("typeList");
        if(StringUtils.isNotEmpty(typeList)){
            List<Type> types = JSONObject.parseArray(typeList, Type.class);
            return ServerResponse.success(types);
        }
        List<Type> types = typeDao.selectList(null);
        String s = JSON.toJSONString(types);
        JedisUtil.set("typeList",s);
        return ServerResponse.success(types);
    }

    @Override
    public ServerResponse typeListById(Integer typeid) {
        return null;
    }
}
