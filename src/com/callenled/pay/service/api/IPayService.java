package com.callenled.pay.service.api;

import com.callenled.pay.service.exception.PayApiException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public interface IPayService {

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     * @param outTradeNo 商户订单号
     * @param subject 交易标题
     * @param totalAmount 支付金额
     * @param notifyUrl 通知地址
     * @param httpServletRequest http请求
     * @return
     */
    Object appOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl, HttpServletRequest httpServletRequest) throws PayApiException;

    /**
     * 创建订单并扫码支付
     * @param outTradeNo 商户订单号
     * @param subject 交易标题
     * @param totalAmount 支付金额
     * @param notifyUrl 通知地址
     * @param httpServletRequest http请求
     * @return
     */
    String scanOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl, HttpServletRequest httpServletRequest) throws PayApiException;
}
