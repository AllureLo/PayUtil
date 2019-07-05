package com.callenled.pay;


import com.callenled.pay.factory.PayFactory;
import com.callenled.pay.service.exception.PayApiException;
import com.callenled.pay.wechat.model.AppOrderModel;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public class Demo{
    public static void main(String[] args) {
        WxPayConfig wxPayConfig = new WxPayConfig();
        HttpServletRequest request = null;
        try {
            String result = PayFactory.create(wxPayConfig)
                    .appOrder("", "", 100.0, "", request);
            System.out.println(result);
        } catch (PayApiException e) {
            e.printStackTrace();
        }
    }
}
