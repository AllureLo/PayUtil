package com.callenled.pay.config;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public abstract class BaseAlipayConfig extends BasePayConfig {
    /**
     * 获取 正式 App ID
     *
     * @return App ID
     */
    public abstract String getFormalAppID();

    /**
     * 获取 沙箱 App ID
     *
     * @return App ID
     */
    public abstract String getSandboxAppID();

    /**
     * 获取 正式 Mch ID
     *
     * @return Mch ID
     */
    public abstract String getFormalMchID();

    /**
     * 获取 沙箱 Mch ID
     *
     * @return Mch ID
     */
    public abstract String getSandboxMchID();

    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    public abstract String getPrivateKey();

    /**
     * 获取 API 密钥
     *
     * @return API密钥
     */
    public abstract String getPublicKey();

    /**
     * 阿里支付服务url
     *
     * @return serverUrl
     */
    public String getServerUrl() {
        return getUseSandbox() ? "https://openapi.alipaydev.com/gateway.do" : "https://openapi.alipay.com/gateway.do";
    }

    /**
     * 获取 App ID
     *
     * @return App ID
     */
    public String getAppID() {
        return getUseSandbox() ? getSandboxAppID() : getFormalAppID();
    }

    /**
     * 获取 Mch ID
     *
     * @return Mch ID
     */
    public String getMchID() {
        return getUseSandbox() ? getFormalMchID() : getSandboxMchID();
    }
}
