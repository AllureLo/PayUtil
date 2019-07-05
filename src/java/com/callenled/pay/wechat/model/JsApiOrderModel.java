package com.callenled.pay.wechat.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class JsApiOrderModel implements Serializable {

    private static final long serialVersionUID = 8889248149012144509L;
    /**
     * 应用ID
     */
    private String appId;

    /**
     * 预支付交易会话ID
     */
    @SerializedName(value = "package")
    private String packages;

    /**
     * 扩展字段
     */
    private String signType;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 时间戳
     */
    private String timeStamp;

    /**
     * 签名
     */
    private String paySign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
