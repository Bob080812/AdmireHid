package com.admire.utils.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//统一返回结果的类
@Data
public class ReturnValue {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private ReturnValue() {}

    //成功静态方法
    public static ReturnValue ok() {
        ReturnValue r = new ReturnValue();
        r.setSuccess(true);
        r.setCode(20000);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static ReturnValue error() {
        ReturnValue r = new ReturnValue();
        r.setSuccess(false);
        r.setCode(20001);
        r.setMessage("失败");
        return r;
    }

    public ReturnValue success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ReturnValue message(String message){
        this.setMessage(message);
        return this;
    }

    public ReturnValue code(Integer code){
        this.setCode(code);
        return this;
    }

    public ReturnValue data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ReturnValue data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
