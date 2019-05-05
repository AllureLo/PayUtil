package com.callenled.pay;


import com.callenled.pay.factory.PayFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public class Demo{
    public static void main(String[] args) {
        WxPayConfig wxPayConfig = new WxPayConfig();
        HttpServletRequest request;
        PayFactory.create(wxPayConfig).appOrder("", "", "", "", request);
    }
}
