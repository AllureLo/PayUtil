package com.callenled.pay.service.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class AppOrderModel implements Serializable {

    private static final long serialVersionUID = -3902540978298288752L;
    /**
     * 应用ID
     */
    @SerializedName(value = "appid")
    private String appId;

    /**
     * 商户号
     */
    @SerializedName(value = "partnerid")
    private String partnerId;

    /**
     * 预支付交易会话ID
     */
    @SerializedName(value = "prepayid")
    private String prePayId;

    /**
     * 扩展字段
     */
    @SerializedName(value = "package")
    private String packages;

    /**
     * 随机字符串
     */
    @SerializedName(value = "noncestr")
    private String nonceStr;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 签名
     */
    private String sign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrePayId() {
        return prePayId;
    }

    public void setPrePayId(String prePayId) {
        this.prePayId = prePayId;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
