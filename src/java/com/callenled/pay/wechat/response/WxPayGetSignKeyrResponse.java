package com.callenled.pay.wechat.response;

import com.callenled.pay.wechat.api.BaseWxPayResponse;
import com.google.gson.annotations.SerializedName;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayGetSignKeyrResponse extends BaseWxPayResponse {

    private static final long serialVersionUID = 2755069772257100455L;
    /**
     * 沙箱 api 秘钥
     */
    @SerializedName(value = "sandbox_signkey")
    private String sandboxKey;

    public String getSandboxKey() {
        return sandboxKey;
    }

    public void setSandboxKey(String sandboxKey) {
        this.sandboxKey = sandboxKey;
    }
}
