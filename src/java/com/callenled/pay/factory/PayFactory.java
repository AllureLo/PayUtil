package com.callenled.pay.factory;

import com.callenled.pay.config.BaseAlipayConfig;
import com.callenled.pay.config.BaseWxPayConfig;
import com.callenled.pay.service.AliPayService;
import com.callenled.pay.service.WxPayService;
import com.callenled.pay.service.impl.AliPayServiceImpl;
import com.callenled.pay.service.impl.WxPayServiceImpl;

/**
 * @Author: Callenld
 * @Date: 19-5-5
 */
public class PayFactory {

    /**
     * 私有化构造方法
     */
    private PayFactory() {}

    /**
     * 微信支付service
     */
    private static WxPayService wxPayService;

    /**
     * 创建微信支付服务
     * @param config 微信支付配置文件
     */
    public static void createWxPay(BaseWxPayConfig config) {
        wxPayService = new WxPayServiceImpl(config);
    }

    /**
     * 微信支付接口服务
     * @return wxPayService
     */
    public static WxPayService buildWxPay() {
        return wxPayService;
    }

    /**
     * 支付宝支付service
     */
    private static AliPayService aliPayService;

    /**
     * 创建支付宝支付服务
     * @param config 支付宝支付支付配置文件
     */
    public static void createAliPay(BaseAlipayConfig config) {
        aliPayService = new AliPayServiceImpl(config);
    }

    /**
     * 支付宝接口服务
     * @return alipayService
     */
    public static AliPayService buildAliPay() {
        return aliPayService;
    }
}
