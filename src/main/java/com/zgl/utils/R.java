package com.zgl.utils;

public class R {
    private Integer code;
    private String message;
    private Object data;

    public Integer getCode() { return code; }

    public void setCode(Integer code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Object getData() { return data; }

    public void setData(Object data) { this.data = data; }

    public R(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static R success(){return new R(1000,"成功",null); }
    public static R success(Object data){return new R(1000,"成功",data); }
    public static R error(){return new R(1005,"失败，服务未知异常",null); }

}
