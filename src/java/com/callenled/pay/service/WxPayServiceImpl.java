package com.callenled.pay.service;


import com.callenled.pay.config.BasePayConfig;
import com.callenled.pay.service.api.IWxPayService;
import com.callenled.pay.service.exception.PayApiException;
import com.callenled.pay.util.RequestUtil;
import com.callenled.pay.wechat.api.WxPayApiException;
import com.callenled.pay.wechat.model.AppOrderModel;
import com.callenled.pay.wechat.model.WxPayUnifiedOrderModel;
import com.callenled.pay.wechat.request.WxPayUnifiedOrderRequest;
import com.callenled.pay.wechat.response.WxPayUnifiedOrderResponse;
import com.callenled.util.GsonUtil;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayServiceImpl extends IWxPayService {

    public WxPayServiceImpl(BasePayConfig config) {
        super(config);
    }

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param outTradeNo 商户订单号
     * @param subject 交易标题
     * @param totalAmount 支付金额
     * @param notifyUrl 通知地址
     * @param httpServletRequest http请求
     * @return Object
     */
    public String appOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl, HttpServletRequest httpServletRequest) throws PayApiException {
        WxPayUnifiedOrderModel model = WxPayUnifiedOrderModel.create(outTradeNo, subject, totalAmount, notifyUrl);
        return appOrder(model, httpServletRequest);
    }

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param model 请求数据封装
     * @param httpServletRequest http请求
     * @return
     * @throws PayApiException
     */
    public String appOrder(WxPayUnifiedOrderModel model, HttpServletRequest httpServletRequest) throws PayApiException {
        //交易类型
        model.setTradeType(TRADE_TYPE_APP);
        //终端IP
        model.setSpBillCreateIp(RequestUtil.getIpAddress(httpServletRequest));
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
        return GsonUtil.gsonString(orderModel);
    }

    /**
     * 创建订单并扫码支付
     *
     * @param outTradeNo 商户订单号
     * @param subject 交易标题
     * @param totalAmount 支付金额
     * @param notifyUrl 通知地址
     * @param httpServletRequest http请求
     * @return String
     */
    public String scanOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl, HttpServletRequest httpServletRequest) throws PayApiException {
        WxPayUnifiedOrderModel model = WxPayUnifiedOrderModel.create(outTradeNo, subject, totalAmount, notifyUrl);
        return scanOrder(model, httpServletRequest);
    }

    /**
     * 创建订单并扫码支付
     *
     * @param model 请求数据封装
     * @param httpServletRequest http请求
     * @return
     * @throws PayApiException
     */
    public String scanOrder(WxPayUnifiedOrderModel model, HttpServletRequest httpServletRequest) throws PayApiException {
        //交易类型
        model.setTradeType(TRADE_TYPE_NATIVE);
        //终端IP
        model.setSpBillCreateIp(RequestUtil.getIpAddress(httpServletRequest));
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
