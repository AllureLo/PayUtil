package com.callenled.pay.service;


import com.callenled.pay.config.BasePayConfig;
import com.callenled.pay.service.api.IPayService;
import com.callenled.pay.service.api.IWxPayService;
import com.callenled.pay.service.exception.PayApiException;
import com.callenled.pay.util.RequestUtil;
import com.callenled.pay.wechat.api.WxPayApiException;
import com.callenled.pay.wechat.model.AppOrderModel;
import com.callenled.pay.wechat.model.WxPayUnifiedOrderModel;
import com.callenled.pay.wechat.request.WxPayUnifiedOrderRequest;
import com.callenled.pay.wechat.response.WxPayUnifiedOrderResponse;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayServiceImpl extends IWxPayService implements IPayService {

    public WxPayServiceImpl(BasePayConfig config) {
        super(config);
    }

    @Override
    public Object appOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl, HttpServletRequest httpServletRequest) throws PayApiException {
        //封装请求数据
        WxPayUnifiedOrderModel model = new WxPayUnifiedOrderModel();
        model.setOutTradeNo(outTradeNo);
        model.setSubject(subject);
        model.setTotalAmount(totalAmount);
        model.setNotifyUrl(notifyUrl);
        model.setSpBillCreateIp(RequestUtil.getIpAddress(httpServletRequest));
        model.setTradeType(TRADE_TYPE_APP);
        model.setNonceStr(RandomStringUtils.randomAscii(32));
        //请求参数
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest(model);
        //返回参数
        AppOrderModel orderModel;
        try {
            //接口调用
            WxPayUnifiedOrderResponse response = wxPayClient.execute(request);
            //封装返回参数
            orderModel = new AppOrderModel();
            orderModel.setAppId(response.getAppId());
            orderModel.setPartnerId(response.getMchId());
            orderModel.setPrePayId(response.getPrepayId());
            orderModel.setPackages("Sign=WXPay");
            orderModel.setNonceStr(RandomStringUtils.randomAscii(32));
            orderModel.setTimestamp(Long.toString(System.currentTimeMillis()).substring(0, 10));
            //生成签名
            String sign = wxPayClient.createSign(orderModel);
            orderModel.setSign(sign);
        } catch (WxPayApiException e) {
            throw new PayApiException(e.getMessage(), e);
        }
        return orderModel;
    }

    @Override
    public String scanOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl, HttpServletRequest httpServletRequest) throws PayApiException {
        //封装请求数据
        WxPayUnifiedOrderModel model = new WxPayUnifiedOrderModel();
        model.setOutTradeNo(outTradeNo);
        model.setSubject(subject);
        model.setTotalAmount(totalAmount);
        model.setNotifyUrl(notifyUrl);
        model.setSpBillCreateIp(RequestUtil.getIpAddress(httpServletRequest));
        model.setTradeType(TRADE_TYPE_NATIVE);
        model.setNonceStr(RandomStringUtils.randomAscii(32));
        //请求参数
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest(model);
        WxPayUnifiedOrderResponse response;
        try {
            //接口调用
            response = wxPayClient.execute(request);
        } catch (WxPayApiException e) {
            throw new PayApiException(e.getMessage(), e);
        }
        return response.getCodeUrl();
    }
}
