package com.callenled.pay.wechat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public abstract class BaseWxPayModel implements Serializable {

    private static final long serialVersionUID = -3144811100146361544L;

    /**
     * 随机字符串
     */
    @SerializedName(value = "nonce_str")
    private String nonceStr;

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
    protected abstract void setAppId(String appId);

    /**
     * 微信支付分配的商户号
     * @param mchId 商户ID
     * @return
     */
    protected abstract void setMchId(String mchId);
}
