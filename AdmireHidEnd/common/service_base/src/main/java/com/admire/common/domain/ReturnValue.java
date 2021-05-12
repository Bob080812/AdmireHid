package com.admire.common.domain;

import com.admire.common.constant.HttpStatus;
import com.admire.common.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ReturnValue  {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> returnData = new HashMap<String, Object>();

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getReturnData() {
        return returnData;
    }

    public void setReturnData(Map<String, Object> returnData) {
        this.returnData = returnData;
    }

    private ReturnValue(){}

    //成功静态方法
    public static ReturnValue ok() {
        ReturnValue returnValue = new ReturnValue();
        returnValue.setSuccess(true);
        returnValue.setCode(200);
        returnValue.setMessage("成功");
        return returnValue;
    }

    //失败静态方法
    public static ReturnValue error() {
        ReturnValue returnValue = new ReturnValue();
        returnValue.setSuccess(false);
        returnValue.setCode(201);
        returnValue.setMessage("失败");
        return returnValue;
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
        this.returnData.put(key, value);
        return this;
    }

    public ReturnValue data(Map<String, Object> map){
        this.setReturnData(map);
        return this;
    }
}
