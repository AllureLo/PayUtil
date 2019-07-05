package com.callenled.pay.service;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.callenled.pay.config.BaseAlipayConfig;
import com.callenled.pay.config.BasePayConfig;
import com.callenled.pay.service.exception.PayApiException;

import javax.servlet.http.HttpServletRequest;

import static com.alipay.api.AlipayConstants.CHARSET_UTF8;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class AliPayService {

    private BaseAlipayConfig config;

    private AlipayClient alipayClient;

    public AliPayService(BasePayConfig config) {
        this.config = (BaseAlipayConfig) config;
        this.alipayClient = new DefaultAlipayClient(this.config.getServerUrl(), this.config.getAppID(), this.config.getPrivateKey(), "json", CHARSET_UTF8, this.config.getPublicKey(), "RSA2");
    }

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param outTradeNo 商户订单号
     * @param subject 交易标题
     * @param totalAmount 支付金额
     * @param notifyUrl 通知地址
     * @return Object
     */
    public String appOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl) throws PayApiException {
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setSubject(subject);
        model.setOutTradeNo(outTradeNo);
        model.setTotalAmount(String.format("%.2f", totalAmount));
        return appOrder(model, notifyUrl);
    }

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param model 请求数据封装
     * @param notifyUrl 通知地址
     * @return
     * @throws PayApiException
     */
    public String appOrder(AlipayTradeAppPayModel model, String notifyUrl) throws PayApiException {
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(config.getNotifyUrl(notifyUrl));
        AlipayTradeAppPayResponse response;
        try {
            response = alipayClient.sdkExecute(request);
        } catch (AlipayApiException e) {
            throw new PayApiException(e.getMessage(), e);
        }
        if (!response.isSuccess()) {
            throw new PayApiException(response.getCode(), response.getSubMsg());
        }
        return response.getBody();
    }

    /**
     * 创建订单并扫码支付
     *
     * @param outTradeNo 商户订单号
     * @param subject 交易标题
     * @param totalAmount 支付金额
     * @param notifyUrl 通知地址
     * @return String
     */
    public String scanOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl) throws PayApiException {
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setSubject(subject);
        model.setOutTradeNo(outTradeNo);
        model.setTotalAmount(String.format("%.2f", totalAmount));
        return scanOrder(model, notifyUrl);
    }

    /**
     * 创建订单并扫码支付
     *
     * @param model 请求数据封装
     * @param notifyUrl 通知地址
     * @return
     * @throws PayApiException
     */
    public String scanOrder(AlipayTradePrecreateModel model, String notifyUrl) throws PayApiException {
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.precreate
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizModel(model);
        request.setNotifyUrl(config.getNotifyUrl(notifyUrl));
        AlipayTradePrecreateResponse response;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            throw new PayApiException(e.getMessage(), e);
        }
        if (!response.isSuccess()) {
            throw new PayApiException(response.getCode(), response.getSubMsg());
        }
        return response.getQrCode();
    }
}
