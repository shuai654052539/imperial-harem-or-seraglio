package com.fh.shop.api.type.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.type.po.Type;

public interface TypeDao extends BaseMapper<Type> {
    void addTypeTree(Type type);
}
