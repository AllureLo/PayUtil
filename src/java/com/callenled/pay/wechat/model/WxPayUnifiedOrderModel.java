package com.callenled.pay.wechat.model;

import com.callenled.util.GsonUtil;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayUnifiedOrderModel extends BaseWxPayModel {
    private static final long serialVersionUID = -4355580936359359860L;

    /**
     * 应用ID
     */
    @SerializedName(value = "appid")
    private String appId;

    /**
     * 商户号
     */
    @SerializedName(value = "mch_id")
    private String mchId;

    /**
     * 商品主题
     */
    @SerializedName(value = "body")
    private String subject;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 商户订单号
     */
    @SerializedName(value = "out_trade_no")
    private String outTradeNo;

    /**
     * 货币类型
     */
    @SerializedName(value = "fee_type")
    private String feeType;

    /**
     * 总金额
     */
    @SerializedName(value = "total_fee")
    private int totalFee;

    /**
     * 终端IP
     */
    @SerializedName(value = "spbill_create_ip")
    private String spBillCreateIp;

    /**
     * 交易起始时间
     */
    @SerializedName(value = "time_start")
    private String timeStart;

    /**
     * 交易结束时间
     */
    @SerializedName(value = "time_expire")
    private String timeExpire;

    /**
     * 订单优惠标记
     */
    @SerializedName(value = "goods_tag")
    private String goodsTag;

    /**
     * 通知地址
     */
    @SerializedName(value = "notifyUrl")
    private String notifyUrl;

    /**
     * 交易类型
     */
    @SerializedName(value = "trade_type")
    private String tradeType;

    /**
     * 商品ID
     */
    @SerializedName(value = "product_id")
    private String productId;

    /**
     * 指定支付方式
     */
    @SerializedName(value = "limit_pay")
    private String limitPay;

    /**
     * 用户标识
     */
    private String openid;

    /**
     * 开发票入口开放标识
     */
    private String receipt;

    /**
     * 场景信息
     */
    @SerializedName(value = "scene_info")
    private String sceneInfo;

    public String getAppId() {
        return appId;
    }

    public String getMchId() {
        return mchId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpBillCreateIp() {
        return spBillCreateIp;
    }

    public void setSpBillCreateIp(String spBillCreateIp) {
        this.spBillCreateIp = spBillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public void setTotalAmount(Double totalAmount) {
        //乘数
        BigDecimal multiplier = new BigDecimal(100);
        //被乘数
        BigDecimal multiplicand = new BigDecimal(totalAmount);
        multiplicand = multiplicand.multiply(multiplier);
        this.setTotalFee(multiplicand.intValue());
    }

    public void setSceneInfo(String id, String name, String areaCode, String address) {
        SceneInfo info = new SceneInfo(id, name, areaCode, address);
        this.setSceneInfo(GsonUtil.gsonString(info));
    }

    public class SceneInfo implements Serializable {

        private static final long serialVersionUID = 4193641895780485369L;
        /**
         * 门店id
         */
        private String id;

        /**
         * 门店名称
         */
        private String name;

        /**
         * 门店行政区划码
         */
        @SerializedName(value = "area_code")
        private String areaCode;

        /**
         * 门店详细地址
         */
        private String address;

        public SceneInfo(String id, String name, String areaCode, String address) {
            this.id = id;
            this.name = name;
            this.areaCode = areaCode;
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static WxPayUnifiedOrderModel create(String outTradeNo, String subject, Double totalAmount, String notifyUrl) {
        return create(outTradeNo, subject, totalAmount, notifyUrl, null);
    }

    public static WxPayUnifiedOrderModel create(String outTradeNo, String subject, Double totalAmount, String notifyUrl, String openId) {
        WxPayUnifiedOrderModel model = new WxPayUnifiedOrderModel();
        model.setOutTradeNo(outTradeNo);
        model.setSubject(subject);
        model.setTotalAmount(totalAmount);
        model.setNotifyUrl(notifyUrl);
        model.setNonceStr(RandomStringUtils.randomAscii(32));
        model.setOpenid(openId);
        return model;
    }
}
