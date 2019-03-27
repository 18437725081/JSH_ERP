package com.jsh.erp.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 暗香
 */
@Slf4j
@Data
public class BusinessRunTimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String reason;

    public BusinessRunTimeException(int code, String reason) {
        super(reason);
        this.code = code;
        this.reason = reason;
    }
}
