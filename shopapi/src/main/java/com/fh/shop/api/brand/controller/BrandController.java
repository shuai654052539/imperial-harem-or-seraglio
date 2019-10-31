package com.fh.shop.api.brand.controller;

import com.fh.shop.api.brand.biz.IBrandService;
import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.brand.po.BrandParma;
import com.fh.shop.api.brand.po.DateTableResult;
import com.fh.shop.api.goods.common.Check;
import com.fh.shop.api.goods.common.ServerResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/brands")
@CrossOrigin("*")
public class BrandController {

    @Resource(name = "brandService")
    private IBrandService brandService;

    @GetMapping("/list")
    public ServerResponse list(){
        return brandService.list();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ServerResponse list(BrandParma brandParma){
        DateTableResult getlist = brandService.getlist(brandParma);
        return ServerResponse.success(getlist);
    }

    @RequestMapping(value = "/byId",method = RequestMethod.GET)
    public ServerResponse brandParma(Long id){
        return brandService.brandParma(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ServerResponse add(Brand brandParma){
        brandService.add(brandParma);
        return ServerResponse.success();
    }


    /*@RequestMapping(method = RequestMethod.GET)
    public ServerResponse get(){
        return brandService.list();
    }*/


    /*@CrossOrigin("*")
    @RequestMapping(method = RequestMethod.POST)
    public ServerResponse post(@RequestParam("c[]")String[] c){
        System.out.println("aaa");
        return brandService.list();
    }*/

    @RequestMapping(method = RequestMethod.DELETE)
    public ServerResponse delete(Long id){
        System.out.println(id);
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ServerResponse update(@RequestBody Brand brand){
        return brandService.update(brand);
    }


}
