package com.callenled.pay.wechat.model;

import com.callenled.pay.wechat.BaseWxPayModel;
import com.google.gson.annotations.SerializedName;

/**
 * @Author: callenled
 * @Date: 19-7-5 下午5:47
 */
public class WxPayCloseOrderModel extends BaseWxPayModel {

    /**
     * 应用ID
     */
    @SerializedName(value = "appid")
    private String appId;

    /**
     * 商户号
     */
    @SerializedName(value = "mch_id")
    private String mchId;

    /**
     * 商户订单号
     */
    @SerializedName(value = "out_trade_no")
    private String outTradeNo;

    public String getAppId() {
        return appId;
    }

    public String getMchId() {
        return mchId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    protected void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    protected void setMchId(String mchId) {
        this.mchId = mchId;
    }
}
