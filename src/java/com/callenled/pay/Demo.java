package com.callenled.pay;


import com.callenled.pay.factory.PayFactory;
import com.callenled.pay.service.exception.PayApiException;
import com.callenled.pay.util.WxPayUtil;
import com.callenled.pay.wechat.model.WxPayUnifiedOrderModel;
import com.callenled.util.GsonUtil;

import javax.servlet.http.*;
import java.util.Map;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public class Demo{
    public static void main(String[] args) {
        WxPayUnifiedOrderModel model = new WxPayUnifiedOrderModel();
        model.setSceneInfo("1", "测试", "ce", "as");

        String xml = WxPayUtil.object2XmlWithCreateSign(model, "sfasfasgasfsfs");
        System.out.println(xml);

        /*WxPayConfig wxPayConfig = new WxPayConfig();
        HttpServletRequest request = null;
        try {
            String result = PayFactory.create(wxPayConfig)
                    .appOrder("2301293141", "测试", 100, "/test", request);
            System.out.println(result);
        } catch (PayApiException e) {
            e.printStackTrace();
        }*/
    }
}
