package com.callenled.pay.service;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.callenled.pay.exception.PayApiException;

/**
 * @Author: callenled
 * @Date: 19-10-21 上午10:24
 */
public interface AliPayService extends PayService {

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param outTradeNo  商户订单号
     * @param subject     交易标题
     * @param totalAmount 支付金额
     * @param notifyUrl   通知地址
     * @return Object
     * @throws PayApiException
     */
    String appOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl) throws PayApiException;

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param model     请求数据封装
     * @param notifyUrl 通知地址
     * @return
     * @throws PayApiException
     */
    String appOrder(AlipayTradeAppPayModel model, String notifyUrl) throws PayApiException;

    /**
     * 创建订单并扫码支付
     *
     * @param outTradeNo  商户订单号
     * @param subject     交易标题
     * @param totalAmount 支付金额
     * @param notifyUrl   通知地址
     * @return String
     * @throws PayApiException
     */
    String scanOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl) throws PayApiException;

    /**
     * 创建订单并扫码支付
     *
     * @param model     请求数据封装
     * @param notifyUrl 通知地址
     * @return
     * @throws PayApiException
     */
    String scanOrder(AlipayTradePrecreateModel model, String notifyUrl) throws PayApiException;
}
