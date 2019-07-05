package com.callenled.pay.wechat.api;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public class WxPayApiException extends Exception {
    private static final long serialVersionUID = -8211404497415955780L;
    private String errCode;
    private String errMsg;

    public WxPayApiException() {
    }

    public WxPayApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxPayApiException(String message) {
        super(message);
    }

    public WxPayApiException(Throwable cause) {
        super(cause);
    }

    public WxPayApiException(String errCode, String errMsg) {
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
