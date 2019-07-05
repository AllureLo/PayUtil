package com.callenled.pay.service.exception;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public class PayApiException extends Exception {
    private static final long serialVersionUID = 3476038597797994828L;
    private String errCode;
    private String errMsg;

    public PayApiException() {
    }

    public PayApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayApiException(String message) {
        super(message);
    }

    public PayApiException(Throwable cause) {
        super(cause);
    }

    public PayApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
