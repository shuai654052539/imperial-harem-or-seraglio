package com.fh.shop.api.brand.biz;


import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.brand.po.BrandParma;
import com.fh.shop.api.brand.po.DateTableResult;
import com.fh.shop.api.goods.common.ServerResponse;

public interface IBrandService {

    ServerResponse list();

    ServerResponse update(Brand brand);

    DateTableResult getlist(BrandParma brandParma);

    void add(Brand brandParma);

    ServerResponse brandParma(Long id);

}
