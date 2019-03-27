package com.jsh.erp.exception;

import com.alibaba.fastjson.JSONObject;
import com.jsh.erp.constants.ExceptionConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author 暗香
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleException(Exception e, HttpServletRequest request) {

        JSONObject status = new JSONObject();

        // 针对业务参数异常的处理
        if (e instanceof BusinessParamCheckingException) {
            BusinessParamCheckingException exception = (BusinessParamCheckingException) e;
            status.put(ExceptionConstants.GLOBAL_RETURNS_CODE, exception.getCode());
            status.put(ExceptionConstants.GLOBAL_RETURNS_MESSAGE, exception.getReason());
            return status;
        }

        //针对业务运行时异常的处理
        if (e instanceof BusinessRunTimeException) {
            BusinessRunTimeException exception = (BusinessRunTimeException) e;
            status.put(ExceptionConstants.GLOBAL_RETURNS_CODE, exception.getCode());
            status.put(ExceptionConstants.GLOBAL_RETURNS_MESSAGE, exception.getReason());
            return status;
        }

        //未知異常
        status.put(ExceptionConstants.GLOBAL_RETURNS_CODE, ExceptionConstants.SERVICE_SYSTEM_ERROR_CODE);
        status.put(ExceptionConstants.GLOBAL_RETURNS_MESSAGE, ExceptionConstants.SERVICE_SYSTEM_ERROR_MSG);
        log.error("Global Exception Occured => url : {}, msg : {}", request.getRequestURL(), e.getMessage());
        e.printStackTrace();
        return status;
    }
}