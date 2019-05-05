package com.callenled.pay.factory;

import com.callenled.pay.config.BaseAlipayConfig;
import com.callenled.pay.config.BaseWxPayConfig;
import com.callenled.pay.service.AlipayServiceImpl;
import com.callenled.pay.service.WxPayServiceImpl;
import com.callenled.pay.service.api.IPayService;

/**
 * @Author: Callenld
 * @Date: 19-5-5
 */
public class PayFactory {

    /**
     * 微信支付服务
     */
    private static IPayService wxPayService;

    /**
     * 支付宝服务
     */
    private static IPayService alipayService;

    /**
     * 私有化构造方法
     */
    private PayFactory() {}

    /**
     * 微信支付接口服务
     * @param config 配置文件
     * @return wxPayService
     */
    public static WxPayServiceImpl create(BaseWxPayConfig config) {
        if (wxPayService == null) {
            wxPayService = new WxPayServiceImpl(config);
        }
        return (WxPayServiceImpl) wxPayService;
    }

    /**
     * 支付宝接口服务
     * @param config 配置文件
     * @return alipayService
     */
    public static AlipayServiceImpl create(BaseAlipayConfig config) {
        if (alipayService == null) {
            alipayService = new AlipayServiceImpl(config);
        }
        return (AlipayServiceImpl) alipayService;
    }
}
