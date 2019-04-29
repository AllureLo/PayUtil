package com.callenled.pay.wechat.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public abstract class BaseWxPayResponse implements Serializable {
    private static final long serialVersionUID = 2322151695731919093L;

    private static final String SUCCESS = "SUCCESS";
    /**
     * 返回状态码 SUCCESS/FAIL
     *
     * 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
     */
    @SerializedName(value = "return_code")
    private String returnCode;

    /**
     * 返回信息，如非空，为错误原因
     */
    @SerializedName(value = "return_msg")
    private String returnMsg;

    /**
     * 业务结果 SUCCESS/FAIL
     */
    @SerializedName(value = "result_code")
    private String resultCode;

    /**
     * 错误代码
     */
    @SerializedName(value = "err_code")
    private String errCode;

    /**
     * 错误代码描述
     */
    @SerializedName(value = "err_code_des")
    private String errCodeDes;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    @Deprecated
    public boolean isReturnSuccess() {
        return SUCCESS.equals(returnCode);
    }

    @Deprecated
    public boolean isResultSuccess() {
        return SUCCESS.equals(resultCode);
    }
}
