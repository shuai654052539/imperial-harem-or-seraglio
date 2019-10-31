package com.fh.shop.api.type.biz;


import com.fh.shop.api.goods.common.ServerResponse;

public interface TypeService {
    ServerResponse queryTypeTree();

    ServerResponse typeListById(Integer typeid);
}
