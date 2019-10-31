package com.fh.shop.api.brand.po;


public class BrandParma extends PageInfo {
    private String brandName;
    private String hotsell;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getHotsell() {
        return hotsell;
    }

    public void setHotsell(String hotsell) {
        this.hotsell = hotsell;
    }
}
