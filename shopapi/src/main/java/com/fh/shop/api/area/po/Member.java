package com.fh.shop.api.area.po;

import java.io.Serializable;

public class Member implements Serializable {
    private Long id;
    private String userName;
    private String realName;
    private String phone;
    private String passWord;
    private String areaid1;
    private String areaid2;
    private String areaid3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAreaid1() {
        return areaid1;
    }

    public void setAreaid1(String areaid1) {
        this.areaid1 = areaid1;
    }

    public String getAreaid2() {
        return areaid2;
    }

    public void setAreaid2(String areaid2) {
        this.areaid2 = areaid2;
    }

    public String getAreaid3() {
        return areaid3;
    }

    public void setAreaid3(String areaid3) {
        this.areaid3 = areaid3;
    }
}
