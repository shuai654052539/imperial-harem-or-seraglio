package com.fh.shop.api.brand.po;

import java.io.Serializable;

public class Brand implements Serializable {
    private Long id;
    private String brandName;
    private String brandImgUrl;
    private Integer hotsell;
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getHotsell() {
        return hotsell;
    }

    public void setHotsell(Integer hotsell) {
        this.hotsell = hotsell;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandImgUrl() {
        return brandImgUrl;
    }

    public void setBrandImgUrl(String brandImgUrl) {
        this.brandImgUrl = brandImgUrl;
    }
}
