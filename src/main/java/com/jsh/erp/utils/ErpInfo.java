package com.jsh.erp.utils;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */
public enum ErpInfo {

    /** */
    OK(200, "成功"),
    /** */
    BAD_REQUEST(400, "请求错误或参数错误"),

    /** */
    UNAUTHORIZED(401, "未认证用户"),

    /** */
    INVALID_VERIFY_CODE(461, "错误的验证码"),

    /** */
    ERROR(500, "服务内部错误"),

    /** */
    WARING_MSG(201, "提醒信息"),

    /** */
    REDIRECT(301, "session失效，重定向"),

    /** */
    FORWARD_REDIRECT(302, "转发请求session失效"),

    /** */
    FORWARD_FAILED(303, "转发请求失败!");

    /**
     * 状态码
     */
    @Setter
    @Getter
    public final Integer code;

    /**
     * 状态信息
     */
    @Setter
    @Getter
    public final String name;


    /**
     * 定义枚举构造函数
     */
    ErpInfo(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
