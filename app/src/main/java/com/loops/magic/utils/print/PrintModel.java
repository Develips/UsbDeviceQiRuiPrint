package com.loops.magic.utils.print;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.loops.magic.R;
import com.loops.magic.utils.usb.USBUtil;

/**
 * Created loops on 2019/3/27
 * description: 
 */
public class PrintModel {

    QiRuiCommand printer = new QiRuiCommand();//打印工具类
    Context mContext;
    USBUtil usbUtil;
    int maxWidth = 770, maxHeight = 1400;

    public PrintModel(Context context) {
        usbUtil = USBUtil.getInstance();
        mContext = context;
        // 创建页面
        usbUtil.sendMessage(printer.QiRui_CreatePage(100, 180));
        // 设置打印方向
        usbUtil.sendMessage(printer.QiRui_Direction(0, 0));
        // 始能切刀，没打完一张自动切纸
        usbUtil.sendMessage(printer.QiRui_Cut(true));
        // 设置缝隙定位
        usbUtil.sendMessage(printer.QiRui_SetGap(true));
        // 设置速度3
        usbUtil.sendMessage(printer.QiRui_Speed(6));
        // 设置浓度
        usbUtil.sendMessage(printer.QiRui_Density(5));
        // 清除页面缓冲区
        usbUtil.sendMessage(printer.QiRui_Cls());
    }

    public void closeIO(){
        if (usbUtil != null)
            usbUtil.closePort();
        usbUtil = null;
    }

    /**
     * 根据快递公司ID打印对应公司面单
     * @param entity
     */
    public void onSwitchPrint(PostPrintInfoEntity entity){
        switch (entity.getExpressCompanyName()){
            case "百世快递":
                printBS(entity);
                break;
            case "中通快递":
                printZTO(entity);//已更新 OK
                break;
            case "申通快递":
                printSTO(entity);//已更新 OK
                break;
            case "圆通速递":
                printYTO(entity);//已更新 OK
                break;
            case "顺丰速运":
                printSF(entity);
                break;
            case "韵达快递":
                printYD(entity);//已更新 OK
                break;
            case "天天快递":
                printTTK(entity);
                break;
            default:
                printBS(entity);
                break;
        }
    }

    /**1.百世*/
    public void printBS(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (15 / 170.0000f)), maxWidth, 4, 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (35 / 170.0000f)), maxWidth, 4, 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (50 / 170.0000f)), maxWidth, 4, 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (65 / 170.0000f)), maxWidth, 4, 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (85 / 170.0000f)), maxWidth, 4, 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (100 / 170.0000f)), maxWidth, 4, 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (115 / 170.0000f)), maxWidth, 4, 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (130 / 170.0000f)), maxWidth, 4, 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(maxWidth/2, 10, 4, (int) (1390 * (15 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (15 / 170.0000f)), 4, (int) (1390 * (50 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2)), (int) (1390 * (35 / 170.0000f)), 4, (int) (1390 * (15 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2)), (int) (1390 * (65 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2)), (int) (1390 * (115 / 170.0000f)), 4, (int) (1390 * (15 / 170.0000f)), 0));
        //画内容
        //logo
//            usbUtil.sendMessage(printer.QiRui_DrawPic(60, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.bs_logo_h)));
        //条形码
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 + (maxWidth / 10.0000f * 0.5)), (int) (1390 * (3 / 170.0000f)), 128, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar(60, (int) (1390 * (87 / 170.0000f)), 128, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        //画字
        usbUtil.sendMessage(printer.QiRui_Text(44, (int) (1390 * (20 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(44, (int) (1390 * (24 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(44, (int) (1390 * (28 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "人"));
        //画字
        usbUtil.sendMessage(printer.QiRui_Text(44, (int) (1390 * (37 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(44, (int) (1390 * (41 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(44, (int) (1390 * (45 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "人"));
        //画字
        usbUtil.sendMessage(printer.QiRui_Text(44, (int) (1390 * (52 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "目"));
        usbUtil.sendMessage(printer.QiRui_Text(44, (int) (1390 * (56 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "的"));
        usbUtil.sendMessage(printer.QiRui_Text(44, (int) (1390 * (60 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "地"));
        //画收件人
        usbUtil.sendMessage(printer.QiRui_Text(130, (int) (1390 * (18 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "收件人：" + entity.getReceiverName()));
        usbUtil.sendMessage(printer.QiRui_Text(130, (int) (1390 * (22 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "电话：" + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Textbox(130, (int) (1390 * (26 / 170.0000f)), "TSS24.BF2", 1, 1, 0, maxWidth - 130, 10, "地址：" + entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()));
        //画寄件人
        usbUtil.sendMessage(printer.QiRui_Text(130, (int) (1390 * (36 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄件人：" + entity.getSenderName()));
        usbUtil.sendMessage(printer.QiRui_Text(130, (int) (1390 * (39.5 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "电话：" + entity.getSenderMobile()));
        usbUtil.sendMessage(printer.QiRui_Textbox(130, (int) (1390 * (42.5 / 170.0000f)), "TSS24.BF2", 1, 1, 0, (int) (maxWidth/2 + (maxWidth / 10.0000f * 1.9) - 130), 8, "地址：" + entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()));
        //画寄件信息
        String temp_pay = "";
        switch (0) {
            case 0:
                temp_pay = "现付";
                break;
            case 1:
                temp_pay = "到付";
                break;
            case 2:
                temp_pay = "大客户";
                break;
            case 3:
                temp_pay = "微信支付";
                break;
            case 4:
                temp_pay = "支付宝";
                break;
        }
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.5)), (int) (1390 * (36 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "付款方式：" + temp_pay));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.5)), (int) (1390 * (39.5 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄件费用：" + entity.getPostFee()));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.5)), (int) (1390 * (42.5 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "损保金额：" + entity.getPremiumFee()));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.5)), (int) (1390 * (46 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签单返还："));
        //画目的地
        usbUtil.sendMessage(printer.QiRui_Text(130, (int) (1390 * (52 / 170.0000f)), "TSS24.BF2", 0, 3, 3, true, entity.getReceiverProvince() + "-" + entity.getReceiverCity()));
        //画二维条码
        usbUtil.sendMessage(printer.QiRui_DrawQRCode(45, (int) (1390 * (68 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        usbUtil.sendMessage(printer.QiRui_Text((90 + (int) (1390 * (18 / 170.0000f))), (int) (1390 * (67 / 170.0000f)), "TSS32.BF2", 0, 1, 1, "收件人签收"));
        usbUtil.sendMessage(printer.QiRui_Text((90 + (int) (1390 * (18 / 170.0000f))), (int) (1390 * (78 / 170.0000f)), "TSS16.BF2", 0, 1, 1, "运单号码：" + entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_Text((90 + (int) (1390 * (18 / 170.0000f))), (int) (1390 * (81 / 170.0000f)), "TSS16.BF2", 0, 1, 1, "打印日期：" + "2018/12/20"));

        //画始发和到达站点
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.3)), (int) (1390 * (68 / 170.0000f)), "TSS24.BF2", 0, 1, 1, "始发：" + entity.getSenderCity()));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.3)), (int) (1390 * (73 / 170.0000f)), "TSS24.BF2", 0, 1, 1, "到达：" + entity.getReceiverCity()));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3)), (int) (1390 * (78 / 170.0000f)), "TSS24.BF2", 0, 2, 2, "A01"));

        //收件人
        usbUtil.sendMessage(printer.QiRui_Text(48, (int) (1390 * (102 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "收件人：" + entity.getReceiverName()));
        usbUtil.sendMessage(printer.QiRui_Text(48, (int) (1390 * (106 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "电话：" + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Textbox(48, (int) (1390 * (110 / 170.0000f)), "TSS24.BF2", 1, 1, 0, maxWidth - 130, 10, "地址：" + entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()));
        //画寄件人
        usbUtil.sendMessage(printer.QiRui_Text(48, (int) (1390 * (116 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄件人：" + entity.getSenderName()));
        usbUtil.sendMessage(printer.QiRui_Text(48, (int) (1390 * (119.5 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "电话：" + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Textbox(48, (int) (1390 * (123 / 170.0000f)), "TSS24.BF2", 1, 1, 0, (int) (maxWidth/2 + (maxWidth / 10.0000f * 1.9) - 48), 8, "地址：" + entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()));
        //画寄件人信息
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.5)), (int) (1390 * (117 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "付款方式：" + temp_pay));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.5)), (int) (1390 * (120 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄件费用：" + entity.getPostFee()));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.5)), (int) (1390 * (123 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "损保金额：" + 1000));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.5)), (int) (1390 * (126 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签单返还："));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**2.顺丰*/
    public void printSF(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (18 / 170.0000f)), 740, 4, 0));//18
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (38 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (58 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine((maxWidth/2), (int) (1390 * (73 / 170.0000f)), maxWidth/2, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (88 / 170.0000f)), 740, 4, 0));//30
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (108 / 170.0000f)), 740, 8, 0));//20
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (128/ 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (148/ 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (158 / 170.0000f)), 740, 4, 0));//10
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2)), (int) (1390 * (18 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((maxWidth/2), (int) (1390 * (58 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 3)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((maxWidth/2), (int) (1390 * (108 / 170.0000f)), 4, (int) (1390 * (40 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 3)), (int) (1390 * (148 / 170.0000f)), 4, (int) (1390 * (23 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 - (maxWidth / 10.0000f * 3)), (int) (1390 * (148 / 170.0000f)), 4, (int) (1390 * (23 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar(60, (int) (1390 * (22 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()+""));
        usbUtil.sendMessage(printer.QiRui_DrawBar(maxWidth/2 + 30, (int) (1390 * (112 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()+""));
        /**目的地编码*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2) + 10), (int) (1390 * (20 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "顺丰标快"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2) + 10), (int) (1390 * (27 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "目的地："));
        if (entity.getDestinationCode() != null) {
            usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth / 2 + (maxWidth / 10.0000f * 2) + 10), (int) (1390 * (31 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getDestinationCode()));
        }
        /**收方*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (40 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "收方："));
        usbUtil.sendMessage(printer.QiRui_Text(160, (int) (1390 * (40 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()+""));
        usbUtil.sendMessage(printer.QiRui_Text(160, (int) (1390 * (50 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"，"+entity.getReceiverMobile()));
        /**月结账号*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (60 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "月结账号："));
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (66 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄付现金"));
        /**代收款、卡号、费用*/
        usbUtil.sendMessage(printer.QiRui_Text((maxWidth/2)+10, (int) (1390 * (60 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "代收货款："+ entity.getPostFee() +"元"));
        usbUtil.sendMessage(printer.QiRui_Text((maxWidth/2)+10, (int) (1390 * (66 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "卡号:"));
        usbUtil.sendMessage(printer.QiRui_Text((maxWidth/2)+10, (int) (1390 * (75 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "运费："+ entity.getPostFee() +"元"));
        usbUtil.sendMessage(printer.QiRui_Text((maxWidth/2)+10, (int) (1390 * (81 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "费用合计："+ entity.getPostFee() +"元"));
        /**寄方*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "寄方："));
        String adders = entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()+"";
        subString(160, subString(160, 90,adders,10)+5 ,entity.getSenderName()+"，"+entity.getSenderMobile(),50);
        /**收件员签：时间日期*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收件员："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (100 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄件时间："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (103 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getCreateTime()+""));
        /**收方签署、时间日期*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3)) + 10, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收方签署："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3)) + 10, (int) (1390 * (100 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "日期："));
        /*************************** 画内容---下部分 *************************/
        /**logo*/
        //顺丰标识，上
        usbUtil.sendMessage(printer.QiRui_DrawPic(60, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_sf)));
        //顺丰电话log
        usbUtil.sendMessage(printer.QiRui_DrawPic((int) (maxWidth/2 + (maxWidth / 10.0000f * 2)), (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_sf)));
        //顺丰标识，下
        usbUtil.sendMessage(printer.QiRui_DrawPic(60, (int) (1390 * (110 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_sf)));
        /**
         * 寄
         * 方
         * */
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (130 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (140 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "方"));
        subString(90, subString(90, 130,adders,12)+5,entity.getSenderName()+"，"+entity.getSenderMobile(),50);
        /**
         * 收
         * 方
         * */
        usbUtil.sendMessage(printer.QiRui_Text((maxWidth/2)+10, (int) (1390 * (130 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "收"));
        usbUtil.sendMessage(printer.QiRui_Text((maxWidth/2)+10, (int) (1390 * (140 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "方"));
        subString((maxWidth/2)+60, subString((maxWidth/2)+60, 130,adders,12)+5,entity.getReceiverName()+"，"+entity.getReceiverMobile(),50);
        /**备注信息*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (152 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "数量"));
        //usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (160 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "1"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (200 + (maxWidth/2) - (maxWidth / 10.0000f * 3)), (int) (1390 * (152 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "托寄物"));
        //usbUtil.sendMessage(printer.QiRui_Text((int) (10 + (maxWidth/2) - (maxWidth / 10.0000f * 3)), (int) (1390 * (160 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "MacBook Pro"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3)) + 10, (int) (1390 * (152 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "备注"));
        if (entity.getRemark() != null) {
            subString(420, 160, entity.getRemark() + "", 6);
            //usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth / 2 + (maxWidth / 10.0000f * 3)) + 10, (int) (1390 * (160 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getRemark() + ""));
        }
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**3.韵达*/
    public void printYD(PostPrintInfoEntity entity) {
        //画框架
        //usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (15 / 170.0000f)), 770, 4, 1));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (30 / 170.0000f)), 770, 4, 1));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (40 / 170.0000f)), 770, 4, 1));//10
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (55 / 170.0000f)), 770, 4, 1));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (67 / 170.0000f)), 770, 4, 1));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (87 / 170.0000f)), 770, 4, 1));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (110 / 170.0000f)), 770, 8, 1));//23
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (122/ 170.0000f)), 770, 4, 1));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (134/ 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), 4, 1));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (146 / 170.0000f)), 770, 4, 1));//12
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))+50, (int) (1390 * (2 / 170.0000f)), 4, (int) (1390 * (13 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(200, (int) (1390 * (87 / 170.0000f)), 4, (int) (1390 * (23 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (87 / 170.0000f)), 4, (int) (1390 * (23 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (122 / 170.0000f)), 4, (int) (1390 * (24 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**标准快递*/
        subString((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))+55,2,"标准快递",2);
        /**条形码*/
        usbUtil.sendMessage(printer.QiRui_DrawBar(150, (int) (1390 * (69 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar(maxWidth/2 - 30, (int) (1390 * (112 / 170.0000f)), 0, (int) (1390 * (4 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**编码*/
        if (entity.getPackageCode() != null) {
            usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (17 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity() + entity.getPackageCode()+""));
        }else{
            usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (17 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity()+""));
        }
        /**集包地*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (31 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "集"));
        usbUtil.sendMessage(printer.QiRui_Text(120, (int) (1390 * (31 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getPackageText()+""));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (45 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"  "+entity.getReceiverMobile()));
        subString(100,46,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()+"",21);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (58 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (57 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"  "+entity.getSenderMobile()));
        subString(100,61,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),21);
        /**长文本描述、签收署名*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (89 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "2018/12/01"));
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (93 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "03:23:45"));
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (97 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "76/95"));
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (103 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "由此处撕开"));
        subString(210,89,"快件送达收件人地址,经收件人或收件人(寄件人)允许的代表签字,视为送达,您的签字代表您已验收此包裹,并已确认商品信息无误,包装完好,没有划痕、破损等表面质量问题。",16);
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth/2 + 120 , (int) (1390 * (105 / 170.0000f)) + 10, "TSS24.BF2", 0, 1, 1, false, "签收栏"));

        /*************************** 画内容---下部分 *************************/
        /**logo*/
        //申通标识，上
        usbUtil.sendMessage(printer.QiRui_DrawPic(20, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_yd)));
        //申通标识，下
        usbUtil.sendMessage(printer.QiRui_DrawPic(20, (int) (1390 * (112 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_yd)));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (125 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (123 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"  "+entity.getReceiverMobile()));
        subString(100,127,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()+"",20);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (136 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (135 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"  "+entity.getSenderMobile()));
        subString(100,139,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),20);
        /**二维码*/
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (88 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (124 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        /**备注信息*/
        if (entity.getRemark() != null) {
            subString(20, 147, entity.getRemark()!=null?entity.getRemark():"", 23);
        }
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3.2)), 1355, "TSS24.BF2", 0, 1, 1, false, "已验视"));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**4.申通*/
    public void printSTO(PostPrintInfoEntity entity) {
        //画框架
        //usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (15 / 170.0000f)), 770, 4, 1));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (30 / 170.0000f)), 770, 4, 1));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (40 / 170.0000f)), 770, 4, 1));//10
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (55 / 170.0000f)), 770, 4, 1));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (67 / 170.0000f)), 770, 4, 1));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (87 / 170.0000f)), 770, 4, 1));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (110 / 170.0000f)), 770, 8, 1));//23
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (122/ 170.0000f)), 770, 4, 1));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (134/ 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), 4, 1));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (146 / 170.0000f)), 770, 4, 1));//12
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))+50, (int) (1390 * (2 / 170.0000f)), 4, (int) (1390 * (13 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(200, (int) (1390 * (87 / 170.0000f)), 4, (int) (1390 * (23 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (87 / 170.0000f)), 4, (int) (1390 * (23 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (122 / 170.0000f)), 4, (int) (1390 * (24 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**标准快递*/
        subString((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))+55,2,"标准快递",2);
        /**条形码*/
        usbUtil.sendMessage(printer.QiRui_DrawBar(150, (int) (1390 * (69 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar(maxWidth/2 - 30, (int) (1390 * (112 / 170.0000f)), 0, (int) (1390 * (4 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**编码*/
        if (entity.getPackageCode() != null) {
            usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (17 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity() + entity.getPackageCode()+""));
        }else{
            usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (17 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity()+""));
        }
        /**集包地*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (31 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "集"));
        usbUtil.sendMessage(printer.QiRui_Text(120, (int) (1390 * (31 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getPackageText()+""));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (45 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"  "+entity.getReceiverMobile()));
        subString(100,46,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()+"",21);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (58 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (57 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"  "+entity.getSenderMobile()));
        subString(100,61,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),21);
        /**长文本描述、签收署名*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (89 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "2018/12/01"));
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (93 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "03:23:45"));
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (97 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "76/95"));
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (103 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "打印时间"));
        subString(210,89,"快件送达收件人地址,经收件人或收件人(寄件人)允许的代表签字,视为送达,您的签字代表您已验收此包裹,并已确认商品信息无误,包装完好,没有划痕、破损等表面质量问题。",16);
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth/2 + 120 , (int) (1390 * (105 / 170.0000f)) + 10, "TSS24.BF2", 0, 1, 1, false, "签收栏"));

        /*************************** 画内容---下部分 *************************/
        /**logo*/
        //申通标识，上
        usbUtil.sendMessage(printer.QiRui_DrawPic(20, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_sto)));
        //申通标识，下
        usbUtil.sendMessage(printer.QiRui_DrawPic(20, (int) (1390 * (112 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_sto)));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (125 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (123 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"  "+entity.getReceiverMobile()));
        subString(100,127,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()+"",20);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (136 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (135 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"  "+entity.getSenderMobile()));
        subString(100,139,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),20);
        /**二维码*/
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (88 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (124 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        /**备注信息*/
        if (entity.getRemark() != null) {
            subString(20, 147, entity.getRemark()!=null?entity.getRemark():"", 23);
        }
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3.2)), 1355, "TSS24.BF2", 0, 1, 1, false, "已验视"));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**5.圆通*/
    public void printYTO(PostPrintInfoEntity entity) {
        //画框架
        //usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (15 / 170.0000f)), 800, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (30 / 170.0000f)), 800, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (40 / 170.0000f)), 800, 4, 0));//10
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (55 / 170.0000f)), 800, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (67 / 170.0000f)), 800, 4, 0));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (87 / 170.0000f)), 800, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (110 / 170.0000f)), 800, 8, 0));//23
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (125/ 170.0000f)), 800, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (140/ 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (155 / 170.0000f)), 800, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(0, (int) (1390 * (165 / 170.0000f)), 800, 4, 0));//10
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine(200, (int) (1390 * (87 / 170.0000f)), 4, (int) (1390 * (23 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (87 / 170.0000f)), 4, (int) (1390 * (23 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (125 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (32 / 170.0000f)), 0, (int) (1390 * (6 / 170.0000f)), 0, 0, 3, entity.getExpressNumber()+""));
        usbUtil.sendMessage(printer.QiRui_DrawBar(280, (int) (1390 * (70 / 170.0000f)), 0, (int) (1390 * (10 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()+""));
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (112 / 170.0000f)), 0, (int) (1390 * (6 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()+""));
        /**编码*/
        if (entity.getPackageCode() != null) {
            usbUtil.sendMessage(printer.QiRui_Text(300, (int) (1390 * (17 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getPackageCode() + ""));
        }
        /**收件人*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (45 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(80, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+" "+entity.getReceiverMobile()));
        subString(80,46,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress(),21);
        /**寄件人*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (58 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(80, (int) (1390 * (57 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+" "+entity.getSenderMobile()));
        subString(80,61,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),21);
        /**签字*/
        usbUtil.sendMessage(printer.QiRui_Text(20 , (int) (1390 * (88 / 170.0000f)) + 10, "TSS24.BF2", 0, 1, 1, false, "2018/12/12"));
        usbUtil.sendMessage(printer.QiRui_Text(20 , (int) (1390 * (92 / 170.0000f)) + 10, "TSS24.BF2", 0, 1, 1, false, "17:50:20"));
        usbUtil.sendMessage(printer.QiRui_Text(20 , (int) (1390 * (96 / 170.0000f)) + 10, "TSS24.BF2", 0, 1, 1, false, "打印时间"));
        usbUtil.sendMessage(printer.QiRui_Text(20 , (int) (1390 * (100 / 170.0000f)) + 10, "TSS24.BF2", 0, 1, 1, false, "数量:"));
        usbUtil.sendMessage(printer.QiRui_Text(20 , (int) (1390 * (104 / 170.0000f)) + 10, "TSS24.BF2", 0, 1, 1, false, "重量:"));
        subString(202,88,"快件送达收件人地址,经收件人或收件人(寄件人)允许的代表签字,视为送达,您的签字代表您已验收此包裹,并已确认商品信息无误,包装完好,没有划痕、破损等表面质量问题。",16);
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth/2 + 80 , (int) (1390 * (106 / 170.0000f)) + 10, "TSS24.BF2", 0, 1, 1, false, "签收栏"));
        /**二维码*/
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (89 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (127 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));

        /*************************** 画内容---下部分 *************************/
        /**logo*/
        usbUtil.sendMessage(printer.QiRui_DrawPic(20, (int) (1390 * (2 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_yto)));
        usbUtil.sendMessage(printer.QiRui_DrawPic(20, (int) (1390 * (112 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_yto)));
        /**收件人*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (130 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(80, (int) (1390 * (126 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+" "+entity.getReceiverMobile()));
        subString(80,130,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress(),17);
        /**寄件人*/
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (145 / 170.0000f)), "TSS24.BF2", 0, 2, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(80, (int) (1390 * (141 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+" "+entity.getSenderMobile()));
        subString(80,145,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),17);

        /**备注信息*/
        if (entity.getRemark() != null) {
            subString(20, 156, entity.getRemark() + "", 20);
        }
        usbUtil.sendMessage(printer.QiRui_Text(20, (int) (1390 * (166 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "订单号: "));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**6.中通*/
    public void printZTO(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (20 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (30 / 170.0000f)), 740, 4, 0));//10
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (40 / 170.0000f)), 740, 4, 0));//10
        //usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (46 / 170.0000f)), (int) (maxWidth/2 - (maxWidth / 10.0000f * 2.8)), 4, 0));//8
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (60 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (80 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (110 / 170.0000f)), 740, 8, 0));//30
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (130/ 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (155/ 170.0000f)), 740, 4, 0));//25
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (165 / 170.0000f)), 740, 4, 0));//10
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine(110, (int) (1390 * (40 / 170.0000f)), 4, (int) (1390 * (40 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (30 / 170.0000f)), 4, (int) (1390 * (10 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(maxWidth/2, (int) (1390 * (80 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(maxWidth/2, (int) (1390 * (130 / 170.0000f)), 4, (int) (1390 * (25 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth / 10.0000f * 5) + 20, (int) (1390 * (3 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()+""));
        usbUtil.sendMessage(printer.QiRui_DrawBar(60, (int) (1390 * (112 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()+""));
        /**编码*/
        if (entity.getPackageCode() != null) {
            usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (22 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity() + entity.getPackageCode() + ""));
        }else{
            usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (22 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity()+ ""));
        }
        /**城市、时间*/
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (33 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity()+ ""));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 10, (int) (1390 * (33 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getCreateTime()+""));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3.2)), (int) (1390 * (52 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "已验视"));
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (49 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收件"));
        usbUtil.sendMessage(printer.QiRui_Text(120, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        subString(120,46,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress() + "",21);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (69 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄件"));
        usbUtil.sendMessage(printer.QiRui_Text(120, (int) (1390 * (62 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        subString(120,66,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()+"",21);
        /**付款方式*/
        usbUtil.sendMessage(printer.QiRui_Text(35, (int) (1390 * (82 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "付款方式:"));
        usbUtil.sendMessage(printer.QiRui_Text(35, (int) (1390 * (86 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "品名:"+" "+"货单号:"+" "));
        usbUtil.sendMessage(printer.QiRui_Text(35, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "声明价值:"+" "+"重量(KG)"));
        if (entity.getRemark()!=null){
            subString(35,94, "备注:"+entity.getRemark(), 23);
        }else{
            subString(35,94, "备注:", 23);
        }
        /**收件人签字*/
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth/2 + 5, (int) (1390 * (82 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收件人/签收时间:"));
        subString(maxWidth/2 + 5,88,"您的签字代表您已验收此包裹,并已确认商品信息无误,包装完好,没有划痕、破损等表面质量问题。",15);
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth - maxWidth/5, (int) (1390 * (105 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "年"));
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth - maxWidth/5 + 100, (int) (1390 * (105 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "月"));
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth - maxWidth/5 + 200, (int) (1390 * (105 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "日"));

        /*************************** 画内容---下部分 *************************/
        /**logo*/
        //中通标识，上
        usbUtil.sendMessage(printer.QiRui_DrawPic(40, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_zto)));
        //中通标识，下
        usbUtil.sendMessage(printer.QiRui_DrawPic((int) (maxWidth / 10.0000f * 5) + 50, (int) (1390 * (120 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_zto)));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(35, (int) (1390 * (132 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收件: "+entity.getReceiverName()+" " + entity.getReceiverMobile()));
        subString(35,136,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress() + "",14);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth/2 + 5, (int) (1390 * (132 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄件: "+entity.getSenderName()+" " + entity.getSenderMobile()));
        subString(maxWidth/2 + 5,136,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()+"",14);
        /**品名、备注*/
        usbUtil.sendMessage(printer.QiRui_Text(35, (int) (1390 * (156 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "品名: "+"文件 "+" 申明价值:"));
        if (entity.getRemark()!=null){
            subString(35, 160, "重量(KG): "+" 备注:"+entity.getRemark(), 23);
        }else{
            subString(35, 160, "重量(KG): "+" 备注:", 23);
        }
        usbUtil.sendMessage(printer.QiRui_Text(35, (int) (1390 * (167 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "打印时间:"+ "\"2018/12/20\"" +"  快递员/签名时间:"));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**7.国通*/
    public void printGTO(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (12 / 170.0000f)), 740, 4, 0));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (27 / 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (38 / 170.0000f)), 740, 4, 0));//11
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (46 / 170.0000f)), (int) (maxWidth/2 - (maxWidth / 10.0000f * 2.8)), 4, 0));//8
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (53 / 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (68 / 170.0000f)), 740, 4, 0));//30
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (88 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (108 / 170.0000f)), 740, 8, 0));//20
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (123/ 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (138/ 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (153 / 170.0000f)), 740, 4, 0));//15
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (38 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (38 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar(300, (int) (1390 * (72 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar(300, (int) (1390 * (110 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**编码*/
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (15 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderProvince()+entity.getSenderCity()));
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (30 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, entity.getPackageCode()));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (46 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (40 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        subString(100,44,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress(),21);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (57 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (61 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (55 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        subString(100,59,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),21);
        /**服务信息*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (41 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "服务信息"));
        /**长文本描述、签收署名*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签收人："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (101 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "时间："));

        /*************************** 画内容---下部分 *************************/
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()));
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()));
        /**二维码*/
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (90 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (128 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        /**备注信息*/
        if (entity.getRemark() != null){
            subString(40,155,entity.getRemark()+"",23);
        }
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3.2)), 1355, "TSS24.BF2", 0, 1, 1, false, "已验视"));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**8.邮政*/
    public void printChinaPost(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (12 / 170.0000f)), 740, 4, 0));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (27 / 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (38 / 170.0000f)), 740, 4, 0));//11
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (46 / 170.0000f)), (int) (maxWidth/2 - (maxWidth / 10.0000f * 2.8)), 4, 0));//8
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (53 / 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (68 / 170.0000f)), 740, 4, 0));//30
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (88 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (108 / 170.0000f)), 740, 8, 0));//20
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (123/ 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (138/ 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (153 / 170.0000f)), 740, 4, 0));//15
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (38 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (27 / 170.0000f)), 4, (int) (1390 * (41 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar(300, (int) (1390 * (72 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (110 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**编码*/
        if (entity.getSenderArea() != null) {
            usbUtil.sendMessage(printer.QiRui_Text(300, (int) (1390 * (15 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderArea()));
        }
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (30 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, entity.getSenderProvince()+entity.getSenderCity()));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (46 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (40 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        subString(100,44,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress(),21);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (57 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (61 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (55 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        subString(100,59,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),21);
        /**服务信息*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (41 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "服务信息"));
        /**长文本描述、签收署名*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签收人："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (101 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "时间："));

        /*************************** 画内容---下部分 *************************/
        /**logo*/
        //邮政标识，上
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_ems)));
        //邮政标识，下
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (110 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_ems)));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()));
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()));
        /**二维码*/
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (128 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        /**备注信息*/
        if (entity.getRemark() != null) {
            subString(40, 155, entity.getRemark() + "", 23);
        }
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3.2)), 1355, "TSS24.BF2", 0, 1, 1, false, "已验视"));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**9.宅急送*/
    public void printZJS(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (12 / 170.0000f)), 740, 4, 0));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (27 / 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (38 / 170.0000f)), 740, 4, 0));//11
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (46 / 170.0000f)), (int) (maxWidth/2 - (maxWidth / 10.0000f * 2.8)), 4, 0));//8
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (53 / 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (68 / 170.0000f)), 740, 4, 0));//30
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (88 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (108 / 170.0000f)), 740, 8, 0));//20
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (123/ 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (138/ 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (153 / 170.0000f)), 740, 4, 0));//15
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (38 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (27 / 170.0000f)), 4, (int) (1390 * (41 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar(300, (int) (1390 * (72 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (110 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**快递种类：标准快递*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2) + 10), (int) (1390 * (3 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "标准快递"));
        /**编码*/
        usbUtil.sendMessage(printer.QiRui_Text(250, (int) (1390 * (15 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getPackageCode()));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (46 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (40 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        subString(100,44,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress(),21);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (57 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (61 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (55 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        subString(100,59,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),21);
        /**服务信息*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (41 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "服务"));
        /**长文本描述、签收署名*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签收人："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (101 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "时间："));

        /*************************** 画内容---下部分 *************************/
        /**logo*/
        //邮政标识，上
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_zjs)));
        //邮政标识，下
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (110 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_zjs)));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()));
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()));
        /**二维码*/
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (90 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (128 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        /**备注信息*/
        subString(40,155,entity.getRemark()+"",23);
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3.2)), 1355, "TSS24.BF2", 0, 1, 1, false, "已验视"));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**10.天天*/
    public void printTTK(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (12 / 170.0000f)), 740, 4, 0));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (27 / 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (38 / 170.0000f)), 740, 4, 0));//11
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (46 / 170.0000f)), (int) (maxWidth/2 - (maxWidth / 10.0000f * 2.8)), 4, 0));//8
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (53 / 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (68 / 170.0000f)), 740, 4, 0));//30
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (88 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (108 / 170.0000f)), 740, 8, 0));//20
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (123/ 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (138/ 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (153 / 170.0000f)), 740, 4, 0));//15
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (38 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (27 / 170.0000f)), 4, (int) (1390 * (41 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar(300, (int) (1390 * (72 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (110 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**快递种类：标准快递*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2) + 10), (int) (1390 * (3 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "天天快递"));
        /**编码*/
        usbUtil.sendMessage(printer.QiRui_Text(250, (int) (1390 * (15 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity()));
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (30 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, entity.getPackageCode()));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 10, (int) (1390 * (30 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getPackageCode()));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (46 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (40 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        subString(100,44,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress(),21);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (57 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (61 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (55 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        subString(100,59,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),21);
        /**服务信息*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (41 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "服务"));
        /**长文本描述、签收署名*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签收人："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (101 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "时间："));
        //subString((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 10,90,"您的快递由太平洋公司投保",6);
        /*************************** 画内容---下部分 *************************/
        /**logo*/
        //邮政标识，上
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_tt)));
        //邮政标识，下
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (110 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_tt)));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()));
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()));
        /**备注信息*/
        if (entity.getRemark() != null) {
            subString(40, 155, entity.getRemark() + "", 23);
        }
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3.2)), 1355, "TSS24.BF2", 0, 1, 1, false, "已验视"));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**11.德邦*/
    public void printDEB(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (12 / 170.0000f)), 740, 4, 0));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (27 / 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (38 / 170.0000f)), 740, 4, 0));//11
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (46 / 170.0000f)), (int) (maxWidth/2 - (maxWidth / 10.0000f * 2.8)), 4, 0));//8
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (53 / 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (68 / 170.0000f)), 740, 4, 0));//30
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (88 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (108 / 170.0000f)), 740, 8, 0));//20
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (123/ 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (138/ 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (153 / 170.0000f)), 740, 4, 0));//15
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (38 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (38 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (30 / 170.0000f)), 0, (int) (1390 * (6 / 170.0000f)), 0, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar(300, (int) (1390 * (72 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (110 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**快递种类：标准快递*/
        //usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2) + 10), (int) (1390 * (3 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "天天快递"));
        /**编码*/
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (15 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity()));
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (30 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, entity.getPackageCode()));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (46 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (40 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        subString(100,44,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress(),21);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (57 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (61 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (55 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        subString(100,59,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),21);
        /**服务信息*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (41 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "服务信息"));
        /**长文本描述、签收署名*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签收人："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (101 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "时间："));
        /*************************** 画内容---下部分 *************************/
        /**logo*/
        //邮政标识，上
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_ems)));
        //邮政标识，下
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (110 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_ems)));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()));
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()));
        /**二维码*/
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (90 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com"));
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (128 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com"));
        /**备注信息*/
        if (entity.getRemark() != null) {
            subString(40, 155, entity.getRemark() + "", 23);
        }
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3.2)), 1355, "TSS24.BF2", 0, 1, 1, false, "已验视"));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**12.优速*/
    public void printUCE(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (12 / 170.0000f)), 740, 4, 0));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (27 / 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (38 / 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//11
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (33 / 170.0000f)), (int) (maxWidth/2 - (maxWidth / 10.0000f * 2.8)), 4, 0));//8
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (53 / 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (68 / 170.0000f)), 740, 4, 0));//30
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (88 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (108 / 170.0000f)), 740, 8, 0));//20
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (123/ 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (138/ 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (153 / 170.0000f)), 740, 4, 0));//15
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (38 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (27 / 170.0000f)), 4, (int) (1390 * (41 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar(300, (int) (1390 * (72 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (110 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**编码*/
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (15 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity()));
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (30 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, entity.getPackageCode()));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (46 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (40 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        subString(100,44,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress(),21);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (57 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (61 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (55 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        subString(100,59,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),21);
        /**服务信息*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (29 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "服务"));
        /**长文本描述、签收署名*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签收人："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (101 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "时间："));
        /*************************** 画内容---下部分 *************************/
        /**logo*/
        //优速标识，上
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_uc)));
        //优速标识，下
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (110 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_uc)));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress()));
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress()));
        /**二维码*/
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (90 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (128 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        /**备注信息*/
        if (entity.getRemark() != null) {
            subString(40, 155, entity.getRemark() + "", 23);
        }
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3.2)), 1355, "TSS24.BF2", 0, 1, 1, false, "已验视"));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**12.EMS*/
    public void printEMS(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (22 / 170.0000f)), 740, 4, 0));//22
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (44 / 170.0000f)), 740, 4, 0));//22
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (66 / 170.0000f)), 740, 4, 0));//22
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (88 / 170.0000f)), 740, 4, 0));//22
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (108 / 170.0000f)), 740, 8, 0));//20
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (123/ 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (143/ 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (158 / 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 - (maxWidth / 10.0000f * 1)), (int) (1390 * (1 / 170.0000f)), 4, (int) (1390 * (21 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(maxWidth/2, (int) (1390 * (22 / 170.0000f)), 4, (int) (1390 * (22 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5)), (int) (1390 * (66 / 170.0000f)), 4, (int) (1390 * (22 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5)), (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (143 / 170.0000f)), 4, (int) (1390 * (29 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 - (maxWidth / 10.0000f * 1)) + 50, (int) (1390 * (5 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar(50, (int) (1390 * (110 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**编码*/
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (8 / 170.0000f)), "TSS24.BF2", 0, 2, 2, false, "标准快递"));
        /**寄方*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (24 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄方："));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (24 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        subString(40,28,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),13);
        /**收方*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (46 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收方："));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (46 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        subString(40,50,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress(),30);
        /**付款方式*/
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (68 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "付款方式："));
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (72 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "计费重量(KG)："));
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (76 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "保价金额(元)："));
        /**长文本描述、签收署名*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5)) + 10, (int) (1390 * (68 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收件人/代收人："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5)) + 10, (int) (1390 * (73 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签收时间："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5)) + 80 , (int) (1390 * (78 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "年"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5)) + 160 , (int) (1390 * (78 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "月"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5)) + 240, (int) (1390 * (78 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "日"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5)) + 320, (int) (1390 * (78 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "时"));
        //subString(40,90,"上海市徐汇区漕河泾开发区地铁站5号口，上海市徐汇区漕河泾开发区地铁站5号口，上海市徐汇区漕河泾开发区地铁站5号口，上海市徐汇区漕河泾开发区地铁站5号口",30);
        /*************************** 画内容---下部分 *************************/
        /**logo*/
        usbUtil.sendMessage(printer.QiRui_DrawPic(maxWidth/2 + 100, (int) (1390 * (110 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_ems)));
        /**寄方*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (125 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄方："));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (125 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()));
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (129 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderMobile()));
        subString(40,133,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),11);
        /**收方*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5))+10, (int) (1390 * (125 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收方："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5))+70, (int) (1390 * (125 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        subString((int) (maxWidth/2 - (maxWidth / 10.0000f * 0.5))+10,129,entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea() + entity.getReceiverAddress(),15);
        /**二维码*/
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (145 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        /**备注信息*/
        subString(40,145,entity.getRemark()+"",23);
        ///subString(40,160,"网址：http://www.diyibox.com/ 客服电话：400-086-8784",30);
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**13.全峰*/
    public void printQF(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (12 / 170.0000f)), 740, 4, 0));//12
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (27 / 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (38 / 170.0000f)), 740, 4, 0));//11
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.2)), (int) (1390 * (46 / 170.0000f)), (int) (maxWidth/2 - (maxWidth / 10.0000f * 2.2)), 4, 0));//8
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (53 / 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.2))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (68 / 170.0000f)), 740, 4, 0));//30
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (88 / 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (108 / 170.0000f)), 740, 8, 0));//20
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (123/ 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (138/ 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8))-30, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (153 / 170.0000f)), 740, 4, 0));//15
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (38 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.2)), (int) (1390 * (27 / 170.0000f)), 4, (int) (1390 * (41 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (88 / 170.0000f)), 4, (int) (1390 * (20 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)), (int) (1390 * (123 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar(300, (int) (1390 * (72 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (110 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**快递种类：标准快递*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2) + 10), (int) (1390 * (3 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, "全峰标快"));
        /**编码*/
        if (entity.getSenderArea() != null) {
            usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (15 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity() + entity.getSenderArea()+""));
        }else{
            usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (15 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSenderCity()+""));
        }
        usbUtil.sendMessage(printer.QiRui_Text(50, (int) (1390 * (30 / 170.0000f)), "TSS24.BF2", 0, 1, 1, true, entity.getPackageCode()));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.2)) + 10, (int) (1390 * (30 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getPackageCode()));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (42 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (46 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (40 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (44 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea()));
        subString(100,48,entity.getReceiverAddress(),21);
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (57 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (61 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (55 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (59 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea()));
        subString(100,63,entity.getSenderAddress(),21);
        /**服务信息*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.2)) + 20, (int) (1390 * (41 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "服务"));
        /**长文本描述、签收署名*/
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (90 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签收人："));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (101 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "时间："));
        /*************************** 画内容---下部分 *************************/
        /**logo*/
        //标识，上
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (3 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_qf)));
        //标识，下
        usbUtil.sendMessage(printer.QiRui_DrawPic(45, (int) (1390 * (110 / 170.0000f)), BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_qf)));
        /**收件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (127 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "收"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (125 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverName()+"," + entity.getReceiverMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (129 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverProvince() + entity.getReceiverCity() + entity.getReceiverArea()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (131 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getReceiverAddress()));
        /**寄件*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "件"));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (140 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (144 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (148 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderAddress()));
        /**二维码*/
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (90 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        usbUtil.sendMessage(printer.QiRui_DrawQRCode((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (125 / 170.0000f)), 1, 0, 6, "http://www.diyibox.com/"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (142 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "扫码下载"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2.8)) + 20, (int) (1390 * (146 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "惊喜即来"));
        /**备注信息*/
        if (entity.getRemark() != null) {
            subString(40, 155, entity.getRemark() + "", 23);
        }
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 3.2)), 1355, "TSS24.BF2", 0, 1, 1, false, "已验视"));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }
    /**14.京东*/
    public void printJD(PostPrintInfoEntity entity) {
        //画框架
        usbUtil.sendMessage(printer.QiRui_DrawBox(30, 10, maxWidth, maxHeight, 4, 0));//文本框
        //起始X、Y、线宽、线高、返回值0
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (30 / 170.0000f)), 740, 4, 0));//30
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (53 / 170.0000f)), 740, 4, 0));//23
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (68 / 170.0000f)), 740, 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (83 / 170.0000f)), (int) (maxWidth/2 - (maxWidth / 10.0000f * 1)), 4, 0));//15
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (102 / 170.0000f)), 740, 8, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (108 / 170.0000f)), 740, 8, 0));//20
        //下半横线
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (118/ 170.0000f)), 740, 4, 0));//10
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (138/ 170.0000f)), 740, 4, 0));//20
        usbUtil.sendMessage(printer.QiRui_DrawLine(30, (int) (1390 * (158 / 170.0000f)), (int) (maxWidth/2 + (maxWidth / 10.0000f * 1))-30, 4, 0));//20
        //竖线
        usbUtil.sendMessage(printer.QiRui_DrawLine(maxWidth/2, (int) (1390 * (30 / 170.0000f)), 4, (int) (1390 * (38 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine(90, (int) (1390 * (53 / 170.0000f)), 4, (int) (1390 * (49 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (53 / 170.0000f)), 4, (int) (1390 * (49 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 2)), (int) (1390 * (68 / 170.0000f)), 4, (int) (1390 * (30 / 170.0000f)), 0));
        usbUtil.sendMessage(printer.QiRui_DrawLine((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)), (int) (1390 * (118 / 170.0000f)), 4, (int) (1390 * (55 / 170.0000f)), 0));
        /*************************** 画内容---上部分 *************************/
        /**条形码*/
        // X、Y、type=0、height条码高度、hri字符现实方式：2-居中显示；otation旋转角度：0，单元宽度：3，条码内容
        //mySerialPort.Write(printer.QiRui_DrawBar(80, 300, 0, 90, 2, 0, 4,    "873456093465"));
        usbUtil.sendMessage(printer.QiRui_DrawBar(100, (int) (1390 * (5 / 170.0000f)), 0, (int) (1390 * (10 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_DrawBar(50, (int) (1390 * (123 / 170.0000f)), 0, (int) (1390 * (8 / 170.0000f)), 2, 0, 3, entity.getExpressNumber()));
        /**始发地*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (32 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "始发地:"));
        usbUtil.sendMessage(printer.QiRui_Text(130, (int) (1390 * (32 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getOriginName()));
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (36 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSortingCode()));
        /**目的地*/
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth/2 + 10, (int) (1390 * (32 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "目的地:"));
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth/2 + 100, (int) (1390 * (32 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getDestinatioName()));
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth/2 + 60, (int) (1390 * (36 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, entity.getSortingCode()));
        /**个人信息*/
        subString(100,55,entity.getSenderName(),7);
        usbUtil.sendMessage(printer.QiRui_Text(maxWidth/2 + 10, (int) (1390 * (55 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "1"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 10, (int) (1390 * (55 / 170.0000f)), "TSS24.BF2", 0, 2, 2, true, "1/1"));
        /**客户信息*/
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (80 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "客"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (84 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "户"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (88 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "信"));
        usbUtil.sendMessage(printer.QiRui_Text(45, (int) (1390 * (92 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "息"));
        subString(100,70,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),13);
        usbUtil.sendMessage(printer.QiRui_Text(100, (int) (1390 * (92 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 5, (int) (1390 * (70 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "客户"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 5, (int) (1390 * (74 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "签字"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 5, (int) (1390 * (85 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "应收"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 1)) + 5, (int) (1390 * (89 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "金额"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 2)) + 200, (int) (1390 * (87 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "元"));
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 0.8)), (int) (1390 * (103 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getCreateTime()));
        /*************************** 画内容---下部分 *************************/
        usbUtil.sendMessage(printer.QiRui_Text(150, (int) (1390 * (110 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "运单号"+entity.getExpressNumber()));
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (119 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "客户信息:"));
        usbUtil.sendMessage(printer.QiRui_Text(150, (int) (1390 * (119 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderName()+"," + entity.getSenderMobile()));
        /**寄方信息*/
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (140 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄方信息:"));
        subString(150,140,entity.getSenderProvince() + entity.getSenderCity() + entity.getSenderArea() + entity.getSenderAddress(),13);
        usbUtil.sendMessage(printer.QiRui_Text(40, (int) (1390 * (152 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, "寄方电话:"));
        usbUtil.sendMessage(printer.QiRui_Text(150, (int) (1390 * (152 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getSenderMobile()));
        /**备注信息*/
        if (entity.getRemark() != null) {
            subString(40, 160, "备注：" + entity.getRemark() + "", 14);
        }
        usbUtil.sendMessage(printer.QiRui_Text((int) (maxWidth/2 + (maxWidth / 10.0000f * 0.8)), (int) (1390 * (173 / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, entity.getCreateTime()));
        //打印
        usbUtil.sendMessage(printer.QiRui_PrintPage(1));
    }

    /**
     *
     * @param x
     * @param y
     * @param txt 文本
     * @param limit 限定长度
     */
    private int subString(int x, int y, String txt, int limit){
        int num = txt.length() / limit;
        int residue = txt.length() % limit;
        int h = 0;
        if (txt.length() > limit){
            for (int i = 0; i < num; i++) {
                int start = i*limit;
                int end = (i+1)*limit;
                int height = i*4;
                usbUtil.sendMessage(printer.QiRui_Text(x, (int) (1390 * ((y+height) / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, txt.substring(start,end)));
            }
        }else{
            usbUtil.sendMessage(printer.QiRui_Text(x, (int) (1390 * (y / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, txt));
        }
        if (residue!=0){
            h = num*4;
            usbUtil.sendMessage(printer.QiRui_Text(x, (int) (1390 * ((y+h) / 170.0000f)), "TSS24.BF2", 0, 1, 1, false, txt.substring(txt.length()-residue)));
        }
        return y+h;
    }
}
