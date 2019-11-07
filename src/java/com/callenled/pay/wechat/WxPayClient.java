package com.callenled.pay.wechat;

import com.callenled.pay.wechat.exception.WxPayApiException;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public interface WxPayClient {

    /**
     * http请求调用微信接口
     *
     * @param request 请求参数
     * @param <T>     返回类型
     * @return BaseWxPayResponse
     * @throws WxPayApiException
     */
    <T extends BaseWxPayResponse> T execute(BaseWxPayRequest<T> request) throws WxPayApiException;

    /**
     * 生成签名
     *
     * @param object 对象参数
     * @return String
     * @throws WxPayApiException
     */
    String createSign(Object object) throws WxPayApiException;
}
