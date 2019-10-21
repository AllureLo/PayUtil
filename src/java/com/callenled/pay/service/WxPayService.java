package com.callenled.pay.service;

import com.callenled.pay.exception.PayApiException;
import com.callenled.pay.wechat.model.WxPayUnifiedOrderModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: callenled
 * @Date: 19-10-21 上午10:23
 */
public interface WxPayService extends PayService {

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param outTradeNo 商户订单号
     * @param body 商品描述
     * @param totalFee 支付金额
     * @param notifyUrl 通知地址
     * @param httpServletRequest http请求
     * @return Object
     * @throws PayApiException
     */
    String appOrder(String outTradeNo, String body, int totalFee, String notifyUrl, HttpServletRequest httpServletRequest) throws PayApiException;

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param model 请求数据封装
     * @param httpServletRequest http请求
     * @return
     * @throws PayApiException
     */
    String appOrder(WxPayUnifiedOrderModel model, HttpServletRequest httpServletRequest) throws PayApiException;

    /**
     * 创建订单并扫码支付
     *
     * @param outTradeNo 商户订单号
     * @param body 商品描述
     * @param totalFee 支付金额
     * @param notifyUrl 通知地址
     * @param httpServletRequest http请求
     * @return String
     */
    String scanOrder(String outTradeNo, String body, int totalFee, String notifyUrl, HttpServletRequest httpServletRequest) throws PayApiException;

    /**
     * 创建订单并扫码支付
     *
     * @param model 请求数据封装
     * @param httpServletRequest http请求
     * @return
     * @throws PayApiException
     */
    String scanOrder(WxPayUnifiedOrderModel model, HttpServletRequest httpServletRequest) throws PayApiException;

    /**
     * 外部商户小程序或公众号唤起快捷SDK创建订单并支付
     *
     * @param outTradeNo 商户订单号
     * @param body 商品描述
     * @param totalFee 支付金额
     * @param notifyUrl 通知地址
     * @param openId 用户标识
     * @param httpServletRequest http请求
     * @return orderModel
     * @throws PayApiException
     */
    String jsApiOrder(String outTradeNo, String body, int totalFee, String notifyUrl, String openId, HttpServletRequest httpServletRequest) throws PayApiException;

    /**
     * 外部商户小程序或公众号唤起快捷SDK创建订单并支付
     *
     * @param model 请求数据
     * @param httpServletRequest http请求
     * @return
     * @throws PayApiException
     */
    String jsApiOrder(WxPayUnifiedOrderModel model, HttpServletRequest httpServletRequest) throws PayApiException;

    /**
     * 关闭订单
     *
     * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
     *
     * @param outTradeNo 商户订单号
     * @throws PayApiException
     */
    void closeOrder(String outTradeNo) throws PayApiException;
}
