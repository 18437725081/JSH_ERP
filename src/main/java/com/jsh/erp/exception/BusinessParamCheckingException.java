package com.jsh.erp.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 暗香
 */
@Slf4j
@Getter
public class BusinessParamCheckingException extends Exception {

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String reason;

    public BusinessParamCheckingException(int code, String reason) {
        super(reason);
        this.code = code;
        this.reason = reason;
    }
}
