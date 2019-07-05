package com.callenled.pay.wechat;

import com.callenled.pay.config.BasePayConfig;
import com.callenled.pay.config.BaseWxPayConfig;
import com.callenled.pay.util.FieldUtil;
import com.callenled.pay.util.WxPayUtil;
import com.callenled.pay.wechat.exception.*;
import com.callenled.pay.wechat.model.BaseWxPayModel;
import com.callenled.pay.wechat.request.BaseWxPayRequest;
import com.callenled.pay.wechat.response.BaseWxPayResponse;
import com.callenled.util.HttpUtil;

import java.lang.reflect.Field;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public class DefaultWxPayClient implements WxPayClient {

    private BaseWxPayConfig config;

    public DefaultWxPayClient(BasePayConfig config) {
        this.config = (BaseWxPayConfig) config;
    }

    @Override
    public <T extends BaseWxPayResponse> T execute(BaseWxPayRequest<T> request) throws WxPayApiException {
        BaseWxPayModel model = request.getData();
        //设置appID
        model.setAppId(config.getAppID());
        //设置商户号ID
        model.setMchId(config.getMchID());
        //若有通知地址
        try {
            Field field = FieldUtil.getDeclaredField(model, "notifyUrl");
            if (field != null) {
                field.setAccessible(true);
                String notifyUrl = field.get(model).toString();
                field.set(model, config.getNotifyUrl(notifyUrl));
            }
        } catch (IllegalAccessException e) {
            throw new WxPayApiException(e.getMessage(), e);
        }
        //签名
        String sign = this.createSign(model);
        model.setSign(sign);
        //生成xml
        String xml = WxPayUtil.object2Xml(model);
        //http请求微信接口
        if (request.isCert()) {
            xml = HttpUtil.builder()
                    .ajaxJson(xml)
                    .setSSLContext(config.getCertContext())
                    .doHttp(config.getUrl(request.getUrl()), request.getHttps())
                    .toJson();
        } else {
            xml = HttpUtil.builder()
                    .ajaxJson(xml)
                    .doHttp(config.getUrl(request.getUrl()), request.getHttps())
                    .toJson();
        }
        BaseWxPayResponse response = WxPayUtil.xml2Object(xml, request.getClazz(), request.signature(), config.getKey());
        if (!response.isReturnSuccess()) {
            throw new WxPayApiException(response.getReturnMsg());
        } else if (!response.isResultSuccess()) {
            throw new WxPayApiException(response.getErrCode(), response.getErrCodeDes());
        }
        return (T) response;
    }

    @Override
    public String createSign(Object object) throws WxPayApiException {
        return WxPayUtil.createSign(object, config.getKey());
    }
}
