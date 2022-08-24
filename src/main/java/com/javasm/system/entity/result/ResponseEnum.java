package com.javasm.system.entity.result;

public enum ResponseEnum {
    LOGIN_SUCCESS(200,"登录成功"),
    LOGIN_ERROR(409,"用户名或密码错误"),
    REQ_SUCCESS(220,"请求成功"),
    QUERY_SUCCESS(240,"查询成功"),
    NO_DATA(290,"没有数据"),
    REQ_FAILED(230,"请求异常，请联系管理员"),
    DATA_ALREADY_CHANGE(280,"此数据已被修改，请刷新"),
    NO_LOGIN(300,"没有登录，请先登录"),
    LOGOUT_SUCCESS(320,"退出成功"),
    NO_PERMISSION(330,"没有权限"),
    UPLOAD_SUCCESS(340,"上传成功");
    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
