package com.callenled.pay.wechat.response;

import com.callenled.pay.wechat.BaseWxPayResponse;
import com.google.gson.annotations.SerializedName;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayCloseOrderResponse extends BaseWxPayResponse {
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
     * 随机字符串
     */
    @SerializedName(value = "nonce_str")
    private String nonceStr;

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

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
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
}
