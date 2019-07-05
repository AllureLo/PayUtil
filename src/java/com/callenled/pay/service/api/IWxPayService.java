package com.callenled.pay.service.api;

import com.callenled.pay.config.BasePayConfig;
import com.callenled.pay.service.exception.PayApiException;
import com.callenled.pay.util.RequestUtil;
import com.callenled.pay.wechat.DefaultWxPayClient;
import com.callenled.pay.wechat.api.WxPayApiException;
import com.callenled.pay.wechat.api.WxPayClient;
import com.callenled.pay.wechat.model.JsApiOrderModel;
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
public class IWxPayService {

    /**
     * 交易类型
     * JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
     * MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
     */
    protected static final String TRADE_TYPE_JSAPI =  "JSAPI";
    protected static final String TRADE_TYPE_APP =  "APP";
    protected static final String TRADE_TYPE_NATIVE =  "NATIVE";

    protected WxPayClient wxPayClient;

    public IWxPayService(BasePayConfig config) {
        this.wxPayClient = new DefaultWxPayClient(config);
    }

    /**
     * 外部商户小程序或公众号唤起快捷SDK创建订单并支付
     *
     * @param outTradeNo 商户订单号
     * @param subject 交易标题
     * @param totalAmount 支付金额
     * @param notifyUrl 通知地址
     * @param openId 用户标识
     * @param httpServletRequest http请求
     * @return orderModel
     * @throws PayApiException
     */
    public String jsApiOrder(String outTradeNo, String subject, Double totalAmount, String notifyUrl, String openId, HttpServletRequest httpServletRequest) throws PayApiException {
        WxPayUnifiedOrderModel model = WxPayUnifiedOrderModel.create(outTradeNo, subject, totalAmount, notifyUrl, openId);
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
}
