package com.loops.magic.utils.print;

import java.io.Serializable;

/**
 * Created loops on 2019/3/27
 * description: 快递单，打印面单使用
 */
public class PostPrintInfoEntity implements Serializable {
    /**
     * SenderName : sample string 1
     * SenderMobile : sample string 2
     * SenderProvince : sample string 3
     * SenderCity : sample string 4
     * SenderArea : sample string 5
     * SenderAddress : sample string 6
     * ReceiverName : sample string 7
     * ReceiverMobile : sample string 8
     * ReceiverProvince : sample string 9
     * ReceiverCity : sample string 10
     * ReceiverArea : sample string 11
     * ReceiverAddress : sample string 12
     * ExpressCompanyName : sample string 13
     * PostOrderId : sample string 14
     * ChargeWeight : 15
     * OriginCode : sample string 16
     * OriginName : sample string 17
     * DestinationCode : sample string 18
     * DestinatioName : sample string 19
     * PackageCode : sample string 20
     * SortingCode : sample string 21
     * PostFee : 22.0
     * PremiumFee : 23.0
     * Remark : sample string 24
     * ExpressNumber : sample string 25
     * CreateTime : sample string 26
     * ProductName : sample string 27
     * MarkDestination : sample string 28,
     * PackageText : sample string 29,
     * OrgSortingCode : sample string 30
     * OrgSortingName : sample string 31
     * DestSortingCode : sample string 32
     * DestSortingName : sample string 33
     * OrgExtra : sample string 34
     * DestExtra : sample string 35
     */

    private String SenderName;              //寄件人姓名
    private String SenderMobile;            //寄件人手机
    private String SenderProvince;          //寄件人省份
    private String SenderCity;              //寄件人城市
    private String SenderArea;              //寄件人地区
    private String SenderAddress;           //寄件人地址
    private String ReceiverName;            //收件人姓名
    private String ReceiverMobile;          //收件人手机
    private String ReceiverProvince;        //收件人省份
    private String ReceiverCity;            //收件人城市
    private String ReceiverArea;            //收件人地区
    private String ReceiverAddress;         //收件人地址
    private String ExpressCompanyName;      //快递公司名称
    private String PostOrderId;             //寄件订单ID
    private int ChargeWeight;               //计费重量（克）
    private String OriginCode;              //始发地编码
    private String OriginName;              //始发网点
    private String DestinationCode;         //目的地编码
    private String DestinatioName;          //目的地
    private String PackageCode;             //集包编码
    private String SortingCode;             //分拣编码
    private double PostFee;                 //寄件费
    private double PremiumFee;              //保价费
    private String Remark;                  //备注
    private String ExpressNumber;           //运单号
    private String CreateTime;              //单据生成时间
    private String ProductName;             //物品
    private String MarkDestination;         //大头笔
    private String PackageText;             //集包地名称
    private String OrgSortingCode;          //始发分拣编码
    private String OrgSortingName;          //始发分拣名称
    private String DestSortingCode;         //目的分拣编码
    private String DestSortingName;         //目的分拣中心名称
    private String OrgExtra;                //始发其他信息
    private String DestExtra;               //目的其他信息

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String SenderName) {
        this.SenderName = SenderName;
    }

    public String getSenderMobile() {
        return SenderMobile;
    }

    public void setSenderMobile(String SenderMobile) {
        this.SenderMobile = SenderMobile;
    }

    public String getSenderProvince() {
        return SenderProvince;
    }

    public void setSenderProvince(String SenderProvince) {
        this.SenderProvince = SenderProvince;
    }

    public String getSenderCity() {
        return SenderCity;
    }

    public void setSenderCity(String SenderCity) {
        this.SenderCity = SenderCity;
    }

    public String getSenderArea() {
        return SenderArea;
    }

    public void setSenderArea(String SenderArea) {
        this.SenderArea = SenderArea;
    }

    public String getSenderAddress() {
        return SenderAddress;
    }

    public void setSenderAddress(String SenderAddress) {
        this.SenderAddress = SenderAddress;
    }

    public String getReceiverName() {
        return ReceiverName;
    }

    public void setReceiverName(String ReceiverName) {
        this.ReceiverName = ReceiverName;
    }

    public String getReceiverMobile() {
        return ReceiverMobile;
    }

    public void setReceiverMobile(String ReceiverMobile) {
        this.ReceiverMobile = ReceiverMobile;
    }

    public String getReceiverProvince() {
        return ReceiverProvince;
    }

    public void setReceiverProvince(String ReceiverProvince) {
        this.ReceiverProvince = ReceiverProvince;
    }

    public String getReceiverCity() {
        return ReceiverCity;
    }

    public void setReceiverCity(String ReceiverCity) {
        this.ReceiverCity = ReceiverCity;
    }

    public String getReceiverArea() {
        return ReceiverArea;
    }

    public void setReceiverArea(String ReceiverArea) {
        this.ReceiverArea = ReceiverArea;
    }

    public String getReceiverAddress() {
        return ReceiverAddress;
    }

    public void setReceiverAddress(String ReceiverAddress) {
        this.ReceiverAddress = ReceiverAddress;
    }

    public String getExpressCompanyName() {
        return ExpressCompanyName;
    }

    public void setExpressCompanyName(String ExpressCompanyName) {
        this.ExpressCompanyName = ExpressCompanyName;
    }

    public String getPostOrderId() {
        return PostOrderId;
    }

    public void setPostOrderId(String PostOrderId) {
        this.PostOrderId = PostOrderId;
    }

    public int getChargeWeight() {
        return ChargeWeight;
    }

    public void setChargeWeight(int ChargeWeight) {
        this.ChargeWeight = ChargeWeight;
    }

    public String getOriginCode() {
        return OriginCode;
    }

    public void setOriginCode(String OriginCode) {
        this.OriginCode = OriginCode;
    }

    public String getOriginName() {
        return OriginName;
    }

    public void setOriginName(String OriginName) {
        this.OriginName = OriginName;
    }

    public String getDestinationCode() {
        return DestinationCode;
    }

    public void setDestinationCode(String DestinationCode) {
        this.DestinationCode = DestinationCode;
    }

    public String getDestinatioName() {
        return DestinatioName;
    }

    public void setDestinatioName(String DestinatioName) {
        this.DestinatioName = DestinatioName;
    }

    public String getPackageCode() {
        return PackageCode;
    }

    public void setPackageCode(String PackageCode) {
        this.PackageCode = PackageCode;
    }

    public String getSortingCode() {
        return SortingCode;
    }

    public void setSortingCode(String SortingCode) {
        this.SortingCode = SortingCode;
    }

    public double getPostFee() {
        return PostFee;
    }

    public void setPostFee(double PostFee) {
        this.PostFee = PostFee;
    }

    public double getPremiumFee() {
        return PremiumFee;
    }

    public void setPremiumFee(double PremiumFee) {
        this.PremiumFee = PremiumFee;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getExpressNumber() {
        return ExpressNumber;
    }

    public void setExpressNumber(String ExpressNumber) {
        this.ExpressNumber = ExpressNumber;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getMarkDestination() {
        return MarkDestination;
    }

    public void setMarkDestination(String markDestination) {
        MarkDestination = markDestination;
    }

    public String getPackageText() {
        return PackageText;
    }

    public void setPackageText(String packageText) {
        PackageText = packageText;
    }

    public String getOrgSortingCode() {
        return OrgSortingCode;
    }

    public void setOrgSortingCode(String orgSortingCode) {
        OrgSortingCode = orgSortingCode;
    }

    public String getOrgSortingName() {
        return OrgSortingName;
    }

    public void setOrgSortingName(String orgSortingName) {
        OrgSortingName = orgSortingName;
    }

    public String getDestSortingCode() {
        return DestSortingCode;
    }

    public void setDestSortingCode(String destSortingCode) {
        DestSortingCode = destSortingCode;
    }

    public String getDestSortingName() {
        return DestSortingName;
    }

    public void setDestSortingName(String destSortingName) {
        DestSortingName = destSortingName;
    }

    public String getOrgExtra() {
        return OrgExtra;
    }

    public void setOrgExtra(String orgExtra) {
        OrgExtra = orgExtra;
    }

    public String getDestExtra() {
        return DestExtra;
    }

    public void setDestExtra(String destExtra) {
        DestExtra = destExtra;
    }

    @Override
    public String toString() {
        return "PostPrintInfoEntity{" +
                "SenderName='" + SenderName + '\'' +
                ", SenderMobile='" + SenderMobile + '\'' +
                ", SenderProvince='" + SenderProvince + '\'' +
                ", SenderCity='" + SenderCity + '\'' +
                ", SenderArea='" + SenderArea + '\'' +
                ", SenderAddress='" + SenderAddress + '\'' +
                ", ReceiverName='" + ReceiverName + '\'' +
                ", ReceiverMobile='" + ReceiverMobile + '\'' +
                ", ReceiverProvince='" + ReceiverProvince + '\'' +
                ", ReceiverCity='" + ReceiverCity + '\'' +
                ", ReceiverArea='" + ReceiverArea + '\'' +
                ", ReceiverAddress='" + ReceiverAddress + '\'' +
                ", ExpressCompanyName='" + ExpressCompanyName + '\'' +
                ", PostOrderId='" + PostOrderId + '\'' +
                ", ChargeWeight=" + ChargeWeight +
                ", OriginCode='" + OriginCode + '\'' +
                ", OriginName='" + OriginName + '\'' +
                ", DestinationCode='" + DestinationCode + '\'' +
                ", DestinatioName='" + DestinatioName + '\'' +
                ", PackageCode='" + PackageCode + '\'' +
                ", SortingCode='" + SortingCode + '\'' +
                ", PostFee=" + PostFee +
                ", PremiumFee=" + PremiumFee +
                ", Remark='" + Remark + '\'' +
                ", ExpressNumber='" + ExpressNumber + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", MarkDestination='" + MarkDestination + '\'' +
                ", PackageText='" + PackageText + '\'' +
                ", OrgSortingCode='" + OrgSortingCode + '\'' +
                ", OrgSortingName='" + OrgSortingName + '\'' +
                ", DestSortingCode='" + DestSortingCode + '\'' +
                ", DestSortingName='" + DestSortingName + '\'' +
                ", OrgExtra='" + OrgExtra + '\'' +
                ", DestExtra='" + DestExtra + '\'' +
                '}';
    }
}
