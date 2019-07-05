package com.callenled.pay.wechat.request;

import com.callenled.pay.wechat.model.BaseWxPayModel;
import com.callenled.pay.wechat.model.WxPayUnifiedOrderModel;
import com.callenled.pay.wechat.response.WxPayUnifiedOrderResponse;
import com.callenled.util.HttpUtil.Https;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayUnifiedOrderRequest implements BaseWxPayRequest<WxPayUnifiedOrderResponse> {

    private WxPayUnifiedOrderModel model;

    public WxPayUnifiedOrderRequest(WxPayUnifiedOrderModel model) {
        this.model = model;
    }

    @Override
    public String getUrl() {
        return "https://api.mch.weixin.qq.com/pay/unifiedorder";
    }

    @Override
    public Https getHttps() {
        return Https.POST;
    }

    @Override
    public boolean isCert() {
        return false;
    }

    @Override
    public BaseWxPayModel getData() {
        return model;
    }

    @Override
    public Class<WxPayUnifiedOrderResponse> getClazz() {
        return WxPayUnifiedOrderResponse.class;
    }

    @Override
    public boolean signature() {
        return true;
    }
}
