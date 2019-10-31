package com.fh.shop.api.brand.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.shop.api.brand.mapper.IBrandDao;
import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.brand.po.BrandParma;
import com.fh.shop.api.brand.po.DateTableResult;
import com.fh.shop.api.goods.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("brandService")
public class IBrandServiceImpl implements IBrandService {

    @Autowired
    private IBrandDao brandDao;


    @Override
    public ServerResponse list() {
        QueryWrapper<Brand> queryWrapper=new QueryWrapper();
        queryWrapper.eq("hotsell",1);
        queryWrapper.orderByDesc("sort");
        List<Brand> brands = brandDao.selectList(queryWrapper);
        return ServerResponse.success(brands);
    }

    @Override
    public ServerResponse update(Brand brand) {
        brandDao.updateById(brand);
        return ServerResponse.success();
    }

    @Override
    public DateTableResult getlist(BrandParma brandParma) {
        Page<Brand> page=new Page<>(brandParma.getStart()/brandParma.getLength()+1,brandParma.getLength());
        QueryWrapper queryWrapper=new QueryWrapper();
        IPage iPage = brandDao.selectPage(page, queryWrapper);
        return new DateTableResult(brandParma.getDraw(),iPage.getTotal(),iPage.getTotal(),iPage.getRecords());
    }

    @Override
    public void add(Brand brandParma) {
        brandDao.insert(brandParma);
    }

    @Override
    public ServerResponse brandParma(Long id) {
        Brand brand = brandDao.selectById(id);
        return ServerResponse.success(brand);
    }
}
