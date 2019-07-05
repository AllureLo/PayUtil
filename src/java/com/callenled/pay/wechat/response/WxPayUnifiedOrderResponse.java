package com.callenled.pay.wechat.response;

import com.google.gson.annotations.SerializedName;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayUnifiedOrderResponse extends BaseWxPayResponse {
    private static final long serialVersionUID = -4210135404813560807L;

    /**
     * 应用APPID
     */
    @SerializedName(value = "appid")
    private String appId;

    /**
     * 商户号
     */
    @SerializedName(value = "mch_id")
    private String mchId;

    /**
     * 设备号
     */
    @SerializedName(value = "device_info")
    private String deviceInfo;

    /**
     * 随机字符串
     */
    @SerializedName(value = "nonce_str")
    private String nonceStr;

    /**
     * 签名
     */
    private String sign;

    /**
     * 交易类型
     */
    @SerializedName(value = "trade_type")
    private String tradeType;

    /**
     * 预支付交易会话标识
     */
    @SerializedName(value = "prepay_id")
    private String prepayId;

    /**
     * 二维码链接
     */
    @SerializedName(value = "code_url")
    private String codeUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
