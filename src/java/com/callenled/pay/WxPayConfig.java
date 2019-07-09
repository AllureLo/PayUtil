package com.callenled.pay;

import com.callenled.pay.config.BaseWxPayConfig;

/**
 * @Author: Callenld
 * @Date: 19-5-5
 */
public class WxPayConfig extends BaseWxPayConfig {
    @Override
    public String getAppID() {
        return "wx4328742e0cc492c7";
    }

    @Override
    public String getMchID() {
        return "1511911781";
    }

    @Override
    public String getAppKey() {
        return "1511911781";
    }

    @Override
    public String getCertPath() {
        return null;
    }

    @Override
    public String getCertPass() {
        return null;
    }

    @Override
    public boolean getUseSandbox() {
        return true;
    }

    @Override
    public String getDomain() {
        return "https://www.test.com";
    }

    @Override
    public String getSandboxDomain() {
        return "https://www.test.com";
    }
}
