package com.jsh.erp.utils;

/**
 * @author 暗香
 */
@SuppressWarnings("serial")
public class JshException extends Exception {
    public long errorCode = -1;

    public String message;

    public JshException() {
        super();
    }

    public JshException(String message) {
        super(message);
        this.message = message;
    }

    public JshException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public JshException(Throwable cause) {
        super(cause);
    }

    public JshException(long errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public JshException(String message, long errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public JshException(String message, long errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public JshException(long errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
