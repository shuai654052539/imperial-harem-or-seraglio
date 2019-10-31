package com.fh.shop.api.goods.common;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    private ServerResponse(){

    }
    private ServerResponse(Integer code,String msg,Object data){
          this.code=code;
          this.msg=msg;
          this.data=data;
    }

    public static ServerResponse success(){
        return new ServerResponse(200,"ok",null);
    }
    public static ServerResponse success(Object data){
        return new ServerResponse(200,"ok",data);
    }
    public static ServerResponse error(){
        return new ServerResponse(-1,"error",null);
    }

    public static ServerResponse success(ResponseEnum responseEnum){
        return new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),null);
    }
    public static ServerResponse success(ResponseEnum responseEnum,Object object){
        return new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),object);
    }

    public static ServerResponse error(ResponseEnum responseEnum,Object data){
        return new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),data);
    }
    public static ServerResponse error(String data){
        return new ServerResponse(-1,"error",data);
    }

    public static ServerResponse error(Integer code,String msg){
        return new ServerResponse(code,msg,null);
    }

    public static ServerResponse error(ResponseEnum responseEnum){
        return new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}