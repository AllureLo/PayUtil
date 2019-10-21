package com.callenled.pay;


import com.callenled.pay.factory.PayFactory;
import com.callenled.pay.util.WxPayUtil;
import com.callenled.pay.wechat.model.WxPayUnifiedOrderModel;

/**
 * @Author: Callenld
 * @Date: 19-4-28
 */
public class Demo{
    public static void main(String[] args) {
/*        WxPayUnifiedOrderModel model = new WxPayUnifiedOrderModel();
        model.setSceneInfo("1", "测试", "ce", "as");

        String xml = WxPayUtil.object2XmlWithCreateSign(model, "sfasfasgasfsfs");
        System.out.println(xml);*/

        WxPayConfig wxPayConfig = new WxPayConfig();
        PayFactory.createWxPay(wxPayConfig);
        System.out.println(PayFactory.buildWxPay() == null);
    }
}
