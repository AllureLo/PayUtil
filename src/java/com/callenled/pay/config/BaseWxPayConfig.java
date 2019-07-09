package com.callenled.pay.config;

import com.callenled.pay.util.WxPayUtil;
import com.callenled.pay.wechat.exception.WxPayApiException;
import com.callenled.pay.wechat.model.WxPayGetSignKeyModel;
import com.callenled.pay.wechat.response.WxPayGetSignKeyrResponse;
import com.callenled.util.HttpUtil;
import org.apache.commons.lang3.RandomStringUtils;

import javax.net.ssl.SSLContext;
import java.util.Objects;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public abstract class BaseWxPayConfig extends BasePayConfig {

    /**
     * 获取验签秘钥API
     */
    private static final String SIGN_KEY = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";

    /**
     * 签名证书
     */
    private SSLContext sslContext;

    /**
     * 获取 App ID
     *
     * @return App ID
     */
    public abstract String getAppID();

    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    public abstract String getMchID();

    /**
     * 获取 商户平台设置的密钥
     *
     * @return API密钥
     */
    public abstract String getAppKey();

    /**
     * 签名证书 路径
     *
     * @return API证书路径
     */
    public abstract String getCertPath();

    /**
     * 签名证书 秘钥
     *
     * @return API证书秘钥
     */
    public abstract String getCertPass();

    /**
     * 正式-沙箱环境 微信接口url
     * @param url 微信接口url
     *
     * @return url
     */
    public String getUrl(String url) {
        if (getUseSandbox()) {
            url = url.replace("exception.mch.weixin.qq.com", "exception.mch.weixin.qq.com/sandboxnew");
        }
        return url;
    }

    /**
     * 获取 API 密钥
     *
     * @return API密钥
     * @throws WxPayApiException
     */
    public String getKey() throws WxPayApiException {
        if (getUseSandbox()) {
            return getSandboxKey();
        }
        return getAppKey();
    }

    /**
     * 获取 签名证书
     *
     * @return SSLContext
     * @throws WxPayApiException
     */
    public SSLContext getCertContext() throws WxPayApiException {
        if (Objects.isNull(sslContext)) {
            try {
                sslContext = HttpUtil.getSSLContext(getCertPath(), getCertPass());
            } catch (Exception e) {
                throw new WxPayApiException(e.getMessage(), e);
            }
        }
        return sslContext;
    }

    /**
     * 获取 沙箱 API 密钥
     *
     * @return API 密钥
     * @throws WxPayApiException
     */
    private String getSandboxKey() throws WxPayApiException {
        WxPayGetSignKeyModel model = new WxPayGetSignKeyModel();
        model.setMchId(getMchID());
        model.setNonceStr(RandomStringUtils.randomAlphabetic(32));
        //签名
        String sign = WxPayUtil.createSign(model, getAppKey());
        model.setSign(sign);
        String xml = WxPayUtil.object2Xml(model);
        String result = HttpUtil.builder()
                .ajaxJson(xml)
                .doHttp(SIGN_KEY, HttpUtil.Https.POST)
                .toJson();
        WxPayGetSignKeyrResponse response = WxPayUtil.xml2Object(result, WxPayGetSignKeyrResponse.class);
        if (!response.isReturnSuccess()) {
            throw new WxPayApiException(response.getReturnMsg());
        }
        return response.getSandboxKey();
    }
}
