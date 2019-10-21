package com.callenled.pay.service.impl;


import com.callenled.pay.config.BaseWxPayConfig;
import com.callenled.pay.exception.PayApiException;
import com.callenled.pay.service.WxPayService;
import com.callenled.pay.util.RequestUtil;
import com.callenled.pay.wechat.DefaultWxPayClient;
import com.callenled.pay.wechat.WxPayClient;
import com.callenled.pay.wechat.exception.WxPayApiException;
import com.callenled.pay.model.AppOrderModel;
import com.callenled.pay.model.JsApiOrderModel;
import com.callenled.pay.wechat.model.WxPayCloseOrderModel;
import com.callenled.pay.wechat.model.WxPayUnifiedOrderModel;
import com.callenled.pay.wechat.request.WxPayCloseOrderRequest;
import com.callenled.pay.wechat.request.WxPayUnifiedOrderRequest;
import com.callenled.pay.wechat.response.WxPayUnifiedOrderResponse;
import com.callenled.util.GsonUtil;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayServiceImpl implements WxPayService {

    /**
     * 交易类型
     * JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
     * MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
     */
    private static final String TRADE_TYPE_JSAPI =  "JSAPI";
    private static final String TRADE_TYPE_APP =  "APP";
    private static final String TRADE_TYPE_NATIVE =  "NATIVE";

    private WxPayClient wxPayClient;

    public WxPayServiceImpl(BaseWxPayConfig config) {
        this.wxPayClient = new DefaultWxPayClient(config);
    }

    /**
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param outTradeNo 商户订单号
     * @param body 商品描述
     * @param totalFee 支付金额
     * @param notifyUrl 通知地址
     * @param httpServletRequest http请求
     * @return Object
     */
    @Override
    public String appOrder(String outTradeNo, String body, int totalFee, String notifyUrl, HttpServletRequest httpServletRequest) throws PayApiException {
        WxPayUnifiedOrderModel model = WxPayUnifiedOrderModel.create(outTradeNo, body, totalFee, notifyUrl);
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
    @Override
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
     * @param body 商品描述
     * @param totalFee 支付金额
     * @param notifyUrl 通知地址
     * @param httpServletRequest http请求
     * @return String
     */
    @Override
    public String scanOrder(String outTradeNo, String body, int totalFee, String notifyUrl, HttpServletRequest httpServletRequest) throws PayApiException {
        WxPayUnifiedOrderModel model = WxPayUnifiedOrderModel.create(outTradeNo, body, totalFee, notifyUrl);
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
    @Override
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
    public String jsApiOrder(String outTradeNo, String body, int totalFee, String notifyUrl, String openId, HttpServletRequest httpServletRequest) throws PayApiException {
        WxPayUnifiedOrderModel model = WxPayUnifiedOrderModel.create(outTradeNo, body, totalFee, notifyUrl, openId);
        return jsApiOrder(model, httpServletRequest);
    }

    /**
     * 外部商户小程序或公众号唤起快捷SDK创建订单并支付
     *
     * @param model 请求数据
     * @param httpServletRequest http请求
     * @return
     * @throws PayApiException
     */
    @Override
    public String jsApiOrder(WxPayUnifiedOrderModel model, HttpServletRequest httpServletRequest) throws PayApiException {
        //交易类型
        model.setTradeType(TRADE_TYPE_JSAPI);
        //终端IP
        model.setSpBillCreateIp(RequestUtil.getIpAddress(httpServletRequest));
        //请求参数
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest(model);
        //返回参数
        JsApiOrderModel orderModel;
        try {
            //接口调用
            WxPayUnifiedOrderResponse response = wxPayClient.execute(request);
            //封装返回参数
            orderModel = new JsApiOrderModel();
            orderModel.setAppId(response.getAppId());
            orderModel.setPackages("prepay_id=" + response.getPrepayId());
            orderModel.setSignType("MD5");
            orderModel.setPackages("Sign=WXPay");
            orderModel.setNonceStr(RandomStringUtils.randomAscii(32));
            orderModel.setTimeStamp(Long.toString(System.currentTimeMillis()).substring(0, 10));
            //生成签名
            String sign = wxPayClient.createSign(orderModel);
            orderModel.setSignType(sign);
        } catch (WxPayApiException e) {
            throw new PayApiException(e.getMessage(), e);
        }
        return GsonUtil.gsonString(orderModel);
    }

    /**
     * 关闭订单
     *
     * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
     *
     * @param outTradeNo 商户订单号
     * @throws PayApiException
     */
    @Override
    public void closeOrder(String outTradeNo) throws PayApiException {
        //封装请求数据
        WxPayCloseOrderModel model = new WxPayCloseOrderModel();
        model.setOutTradeNo(outTradeNo);
        model.setNonceStr(RandomStringUtils.randomAlphabetic(32));
        //request封装
        WxPayCloseOrderRequest request = new WxPayCloseOrderRequest(model);

        try {
            wxPayClient.execute(request);
        } catch (WxPayApiException e) {
            throw new PayApiException(e.getMessage(), e);
        }
    }
}
