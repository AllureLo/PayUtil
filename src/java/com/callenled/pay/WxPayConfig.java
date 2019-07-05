package com.callenled.pay;

import com.callenled.pay.config.BaseWxPayConfig;

/**
 * @Author: Callenld
 * @Date: 19-5-5
 */
public class WxPayConfig extends BaseWxPayConfig {
    @Override
    public String getAppID() {
        return null;
    }

    @Override
    public String getMchID() {
        return null;
    }

    @Override
    public String getAppKey() {
        return null;
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
        return null;
    }

    @Override
    public String getSandboxDomain() {
        return null;
    }
}
