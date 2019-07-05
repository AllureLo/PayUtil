package com.callenled.pay.config;

/**
 * 微信支付配置接口，用户设置获取微信支付关键信息抽象方法
 *
 * @author callenled
 * @date 2018/8/17
 */
public abstract class BasePayConfig {

    /**
     * 是否使用沙箱测试
     *
     * true 使用沙箱
     * @return
     */
    public abstract boolean getUseSandbox();

    /**
     * 通知域名 - 正式环境
     *
     * @return
     */
    public abstract String getDomain();

    /**
     * 通知域名 - 沙箱环境
     *
     * @return
     */
    public abstract String getSandboxDomain();

    /**
     * 获取通知地址
     * @param api
     *
     * @return 知地址
     */
    public String getNotifyUrl(String api) {
        return (getUseSandbox() ? getSandboxDomain() : getDomain()) + api;
    }
}
