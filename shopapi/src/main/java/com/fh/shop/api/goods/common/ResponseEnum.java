package com.fh.shop.api.goods.common;

/**
 *
 */
public enum ResponseEnum {

    LOGIN_USER_SUCCESS(200,"登录成功"),
    OPERATION_SUCCESS(200,"操作成功"),
    UPDATE_PASSWORD_SUCCESS(200,"修改成功"),
    LOGIN_PASSWORD_ERROR(400,"密码错误，请重新登录"),
    LOGIN_USER_ERROR(401,"用户名错误，请重新输入"),
    EMAIL_ERROR(406,"邮箱地址错误，请重新输入"),
    LOGIN_USER_PASSWORD_ISNOTNULL(402,"用户名密码不能为空"),
    LOGIN_USERSTATE_ERROR(403,"用户被锁定"),
    PASSWORD_IS_NULL_ERROR(404,"密码或确认密码不能为空"),
    USERNAME_EMAIL_IS_NULL(405,"用户名或邮箱地址不能为空"),
    PASSWORD_IS_ERROR(407,"原密码不正确"),
    HANDLER_IS_NULL(408,"头信息为空"),
    PAST_DUE(409,"登录过期"),
    HANDLER_IS_ATTACK(-1,"头信息丢失"),
    OPERATION_REPEATED(410,"操作重复"),
    ERROE_UNDERSTOCK(411,"购物失败，商品库存不足"),
    ERROE_ORDER_IS_NULL(412,"没有下单，去商城路逛逛吧"),
    QR_TIMEOUT(413,"二维码过期"),
    ;

    private Integer code;
    private String msg;

    private ResponseEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
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
}
