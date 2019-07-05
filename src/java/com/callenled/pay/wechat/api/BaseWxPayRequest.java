package com.callenled.pay.wechat.api;

import com.callenled.util.HttpUtil.Https;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public interface BaseWxPayRequest<T extends BaseWxPayResponse> {

    /**
     * 请求链接
     *
     * @return url
     */
    String getUrl();

    /**
     * 请求方法
     *
     * @return http
     */
    Https getHttps();

    /**
     * 是否需要使用签名证书
     *
     * @return boolean
     */
    boolean isCert();

    /**
     * 请求参数
     *
     * @return data
     */
    BaseWxPayModel getData();

    /**
     * 响应参数
     *
     * @return clazz
     */
    Class<T> getClazz();

    /**
     * 是否需要校验签名
     *
     * @return boolean
     */
    boolean signature();
}
