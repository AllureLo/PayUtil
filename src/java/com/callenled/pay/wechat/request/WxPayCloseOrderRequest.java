package com.callenled.pay.wechat.request;

import com.callenled.pay.wechat.BaseWxPayModel;
import com.callenled.pay.wechat.BaseWxPayRequest;
import com.callenled.pay.wechat.model.WxPayCloseOrderModel;
import com.callenled.pay.wechat.response.WxPayCloseOrderResponse;
import com.callenled.util.HttpUtil.Https;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayCloseOrderRequest implements BaseWxPayRequest<WxPayCloseOrderResponse> {

    private WxPayCloseOrderModel model;

    public WxPayCloseOrderRequest(WxPayCloseOrderModel model) {
        this.model = model;
    }

    @Override
    public String getUrl() {
        return "https://api.mch.weixin.qq.com/pay/closeorder";
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
    public Class<WxPayCloseOrderResponse> getClazz() {
        return WxPayCloseOrderResponse.class;
    }

    @Override
    public boolean signature() {
        return true;
    }
}
