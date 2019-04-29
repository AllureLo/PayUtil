package com.callenled.pay.wechat.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public abstract class BaseWxPayModel implements Serializable {

    private static final long serialVersionUID = -3144811100146361544L;

    /**
     * 签名
     */
    private String sign;

    /**
     * 签名类型
     *
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     */
    @SerializedName(value = "sign_type")
    private String signType;

    /**
     * 设备号
     *
     * 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @SerializedName(value = "device_info")
    private String deviceInfo;

    /**
     * 随机字符串
     */
    @SerializedName(value = "nonce_str")
    private String nonceStr;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
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

    /**
     * 微信支付分配的公众账号ID
     * @param appId 公众账号ID
     * @return
     */
    public abstract void setAppId(String appId);

    /**
     * 微信支付分配的商户号
     * @param mchId 商户ID
     * @return
     */
    public abstract void setMchId(String mchId);
}
