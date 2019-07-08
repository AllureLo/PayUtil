package com.callenled.pay;


import com.callenled.pay.factory.PayFactory;
import com.callenled.pay.service.exception.PayApiException;

import javax.servlet.http.*;

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
            System.out.println(e.getMessage());
        }
    }
}
