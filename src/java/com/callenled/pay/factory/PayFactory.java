package com.callenled.pay.factory;

import com.callenled.pay.config.BaseAlipayConfig;
import com.callenled.pay.config.BaseWxPayConfig;
import com.callenled.pay.service.AliPayService;
import com.callenled.pay.service.WxPayService;

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
     * 微信支付接口服务
     * @param config 配置文件
     * @return wxPayService
     */
    public static WxPayService create(BaseWxPayConfig config) {
        return new WxPayService(config);
    }

    /**
     * 支付宝接口服务
     * @param config 配置文件
     * @return alipayService
     */
    public static AliPayService create(BaseAlipayConfig config) {
        return new AliPayService(config);
    }
}
