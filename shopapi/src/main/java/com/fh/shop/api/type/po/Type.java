package com.fh.shop.api.type.po;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

public class Type implements Serializable {
    private Integer id;
    private String name;
    @TableField("pid")
    private Integer father;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFather() {
        return father;
    }

    public void setFather(Integer father) {
        this.father = father;
    }
}
