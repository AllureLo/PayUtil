package com.callenled.pay.wechat.response;

import com.callenled.pay.wechat.BaseWxPayResponse;
import com.google.gson.annotations.SerializedName;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayOrderQueryResponse extends BaseWxPayResponse {
    private static final long serialVersionUID = -4210135404813560807L;

    /**
     * 应用APPID
     */
    @SerializedName(value = "appid")
    private String appId;

    /**
     * 商户号
     */
    @SerializedName(value = "mch_id")
    private String mchId;

    /**
     * 随机字符串
     */
    @SerializedName(value = "nonce_str")
    private String nonceStr;

    /**
     * 签名
     */
    private String sign;

    /**
     * 设备号
     */
    @SerializedName(value = "device_info")
    private String deviceInfo;

    /**
     * 用户标识
     */
    private String openid;

    /**
     * 是否关注公众账号
     */
    @SerializedName(value = "is_subscribe")
    private String isSubscribe;

    /**
     * 交易类型
     */
    @SerializedName(value = "trade_type")
    private String tradeType;

    /**
     * 交易状态
     * <p>
     * SUCCESS—支付成功
     * REFUND—转入退款
     * NOTPAY—未支付
     * CLOSED—已关闭
     * REVOKED—已撤销（刷卡支付）
     * USERPAYING--用户支付中
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    @SerializedName(value = "trade_state")
    private String tradeState;

    /**
     * 付款银行
     */
    @SerializedName(value = "bank_type")
    private String bankType;

    /**
     * 总金额
     */
    @SerializedName(value = "total_fee")
    private int totalFee;

    /**
     * 货币种类
     */
    @SerializedName(value = "fee_type")
    private int feeType;

    /**
     * 现金支付金额
     */
    @SerializedName(value = "cash_fee")
    private int cashFee;

    /**
     * 现金支付货币类型
     */
    @SerializedName(value = "cash_fee_type")
    private int cashFeeType;

    /**
     * 应结订单金额
     */
    @SerializedName(value = "settlement_total_fee")
    private int settlementTotalFee;

    /**
     * 代金券金额
     */
    @SerializedName(value = "coupon_fee")
    private int couponFee;

    /**
     * 代金券使用数量
     */
    @SerializedName(value = "coupon_count")
    private int couponCount;

    /**
     * 微信支付订单号
     */
    @SerializedName(value = "transaction_id")
    private String transactionId;

    /**
     * 微信支付订单号
     */
    @SerializedName(value = "out_trade_no")
    private String outTradeNo;

    /**
     * 附加数据
     */
    @SerializedName(value = "attach")
    private String attach;

    /**
     * 支付完成时间
     */
    @SerializedName(value = "time_end")
    private String timeEnd;

    /**
     * 交易状态描述
     */
    @SerializedName(value = "trade_state_desc")
    private String tradeStateDesc;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getFeeType() {
        return feeType;
    }

    public void setFeeType(int feeType) {
        this.feeType = feeType;
    }

    public int getCashFee() {
        return cashFee;
    }

    public void setCashFee(int cashFee) {
        this.cashFee = cashFee;
    }

    public int getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(int cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public int getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(int settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public int getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(int couponFee) {
        this.couponFee = couponFee;
    }

    public int getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(int couponCount) {
        this.couponCount = couponCount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }
}
