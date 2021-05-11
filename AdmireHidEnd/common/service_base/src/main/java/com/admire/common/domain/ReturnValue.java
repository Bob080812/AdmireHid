package com.admire.common.domain;

import com.admire.common.constant.HttpStatus;
import com.admire.common.util.StringUtils;

import java.util.HashMap;

public class ReturnValue extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 ReturnValue 对象，使其表示一个空消息。
     */
    public ReturnValue()
    {
    }

    /**
     * 初始化一个新创建的 ReturnValue 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     */
    public ReturnValue(int code, String msg)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 ReturnValue 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public ReturnValue(int code, String msg, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }
    /**
     * 初始化一个新创建的 ReturnValue 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public ReturnValue(int code, String msg,String key, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(key, data);
        }
    }
    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ReturnValue success()
    {
        return ReturnValue.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ReturnValue success(Object data)
    {
        return ReturnValue.success("操作成功", data);
    }
    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ReturnValue success(String msg,String key,Object data)
    {
        return new ReturnValue(HttpStatus.SUCCESS, msg, key,data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ReturnValue success(String msg)
    {
        return ReturnValue.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ReturnValue success(String msg, Object data)
    {
        return new ReturnValue(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ReturnValue error()
    {
        return ReturnValue.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ReturnValue error(String msg)
    {
        return ReturnValue.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ReturnValue error(String msg, Object data)
    {
        return new ReturnValue(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ReturnValue error(int code, String msg)
    {
        return new ReturnValue(code, msg, null);
    }
}
