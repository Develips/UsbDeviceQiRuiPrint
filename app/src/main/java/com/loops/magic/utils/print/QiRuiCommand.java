package com.loops.magic.utils.print;//

import android.graphics.Bitmap;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.util.Log;

import com.loops.magic.utils.usb.USBUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created loops on 2019/3/27
 * description: 
 */
public class QiRuiCommand {
    public String Version = "20160512_Ver3.0";
    public String Author = "LJF-Studio.COM";
    private int PaperState = 0;
    private boolean Picked = false;
    private boolean IsBusy = true;
    private boolean CutState = false;
    private static final int OPT_CODE_NOP = 0;
    private static final int OPT_CODE_READY = 10;
    private static final int OPT_CODE_OVER = 11;
    private static final int OPT_CODE_TEXT = 12;
    private static final int OPT_CODE_LINE = 13;
    private static final int OPT_CODE_BARCODE = 14;
    private static final int OPT_CODE_QRCODE = 15;
    private static final int OPT_CODE_BITMAP = 16;

    public QiRuiCommand() {
    }

    public byte[] QiRui_CreatePage(int width, int height) {
        String cmd = "SIZE " + width + " mm," + height + " mm\r\n";

        try {
            Log.e("USB","SSSS: "+cmd.getBytes("gb2312"));
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    private String sQiRui_CreatePage(int width, int height) {
        String cmd = "SIZE " + width + " mm," + height + " mm\r\n";
        return cmd;
    }

    public byte[] QiRui_PrintPage(int count) {
        String cmd = "PRINT " + count + "\r\n";

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    private String sQiRui_PrintPage(int count) {
        String cmd = "PRINT " + count + "\r\n";
        return cmd;
    }

    public byte[] QiRui_Text(int x, int y, String font, int rotation, int xmulti, int ymulti, String content) {
        String _content = content.replace("\"", "\\[\"]");
        String cmd = "TEXT " + x + "," + y + "," + "\"" + font + "\"" + "," + rotation + "," + xmulti + "," + ymulti + "," + "\"" + _content + "\"\r\n";

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var11) {
            var11.printStackTrace();
            return null;
        }
    }

    private String sQiRui_Text(int x, int y, String font, int rotation, int xmulti, int ymulti, String content) {
        String _content = content.replace("\"", "\\[\"]");
        String cmd = "TEXT " + x + "," + y + "," + "\"" + font + "\"" + "," + rotation + "," + xmulti + "," + ymulti + "," + "\"" + _content + "\"\r\n";
        return cmd;
    }

    public byte[] QiRui_Text(int x, int y, String font, int rotation, int xmulti, int ymulti, boolean isBold, String content) {
        String cmd = null;
        String _content = content.replace("\"", "\\[\"]");
        if(isBold) {
            cmd = "TEXT " + x + "," + y + "," + "\"" + font + "\"" + "," + rotation + "," + xmulti + "," + ymulti + ",B1," + "\"" + _content + "\"\r\n";
        } else {
            cmd = "TEXT " + x + "," + y + "," + "\"" + font + "\"" + "," + rotation + "," + xmulti + "," + ymulti + "," + "\"" + _content + "\"\r\n";
        }

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var12) {
            var12.printStackTrace();
            return null;
        }
    }

    private String sQiRui_Text(int x, int y, String font, int rotation, int xmulti, int ymulti, boolean isBold, String content) {
        String _content = content.replace("\"", "\\[\"]");
        String cmd = null;
        if(isBold) {
            cmd = "TEXT " + x + "," + y + "," + "\"" + font + "\"" + "," + rotation + "," + xmulti + "," + ymulti + ",B1," + "\"" + _content + "\"\r\n";
        } else {
            cmd = "TEXT " + x + "," + y + "," + "\"" + font + "\"" + "," + rotation + "," + xmulti + "," + ymulti + "," + "\"" + _content + "\"\r\n";
        }

        return cmd;
    }

    public byte[] QiRui_Textbox(int x, int y, String font, int rotation, int xmulti, int ymulti, int width, int linespace, String content) {
        String _content = content.replace("\"", "\\[\"]");
        String cmd = null;
        if(linespace >= 24) {
            cmd = "TEXTBOX " + x + "," + y + "," + "\"" + font + "\"" + "," + rotation + "," + xmulti + "," + ymulti + "," + width + ", L" + linespace + "," + "\"" + _content + "\"\r\n";
        } else {
            cmd = "TEXTBOX " + x + "," + y + "," + "\"" + font + "\"" + "," + rotation + "," + xmulti + "," + ymulti + "," + width + "," + "\"" + _content + "\"\r\n";
        }

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var13) {
            var13.printStackTrace();
            return null;
        }
    }

    public byte[] QiRui_DrawLine(int x, int y, int width, int height, int dotted) {
        String cmd = "BAR " + x + "," + y + "," + width + "," + height + "," + dotted + "\r\n";

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
            return null;
        }
    }

    private String sQiRui_DrawLine(int x, int y, int width, int height, int dotted) {
        String cmd = "BAR " + x + "," + y + "," + width + "," + height + "," + dotted + "\r\n";
        return cmd;
    }

    public byte[] QiRui_DrawLine(int start_x, int start_y, int end_x, int end_y, int width, int dottedType) {
        String cmd = null;
        switch(dottedType) {
            case 0:
                cmd = "LINE " + start_x + "," + start_y + "," + end_x + "," + end_y + "," + width + "\r\n";
                break;
            case 1:
                cmd = "LINE " + start_x + "," + start_y + "," + end_x + "," + end_y + "," + width + ",M1\r\n";
                break;
            case 2:
                cmd = "LINE " + start_x + "," + start_y + "," + end_x + "," + end_y + "," + width + ",M2\r\n";
                break;
            case 3:
                cmd = "LINE " + start_x + "," + start_y + "," + end_x + "," + end_y + "," + width + ",M3\r\n";
                break;
            case 4:
                cmd = "LINE " + start_x + "," + start_y + "," + end_x + "," + end_y + "," + width + ",M4\r\n";
        }

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
            return null;
        }
    }

    private String sQiRui_DrawLine(int start_x, int start_y, int end_x, int end_y, int width, int dottedType) {
        String cmd = null;
        switch(dottedType) {
            case 0:
                cmd = "LINE " + start_x + "," + start_y + "," + end_x + "," + end_y + "," + width + "\r\n";
                break;
            case 1:
                cmd = "LINE " + start_x + "," + start_y + "," + end_x + "," + end_y + "," + width + ",M1\r\n";
                break;
            case 2:
                cmd = "LINE " + start_x + "," + start_y + "," + end_x + "," + end_y + "," + width + ",M2\r\n";
                break;
            case 3:
                cmd = "LINE " + start_x + "," + start_y + "," + end_x + "," + end_y + "," + width + ",M3\r\n";
                break;
            case 4:
                cmd = "LINE " + start_x + "," + start_y + "," + end_x + "," + end_y + "," + width + ",M4\r\n";
        }

        return cmd;
    }

    public byte[] QiRui_DrawPic(int x, int y, int width, int height, String octetStr) {
        String cmd = "BITMAP " + x + "," + y + "," + width / 8 + "," + height + ",1,";
        byte[] pixel = new byte[octetStr.length() / 2];

        for(int bcmd = 0; bcmd < pixel.length; ++bcmd) {
            try {
                pixel[bcmd] = (byte)(~(255 & Integer.parseInt(octetStr.substring(bcmd * 2, bcmd * 2 + 2), 16)));
            } catch (Exception var11) {
                var11.printStackTrace();
            }
        }

        byte[] var12;
        try {
            var12 = cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var10) {
            var10.printStackTrace();
            var12 = null;
        }

        byte[] totalcmd = new byte[var12.length + pixel.length];
        System.arraycopy(var12, 0, totalcmd, 0, var12.length);
        System.arraycopy(pixel, 0, totalcmd, var12.length, pixel.length);
        return totalcmd;
    }

    private String sQiRui_DrawPic(int x, int y, int width, int height, String octetStr) {
        String cmd = "BITMAP " + x + "," + y + "," + width / 8 + "," + height + ",1,";
        byte[] pixel = new byte[octetStr.length() / 2];

        for(int isoString = 0; isoString < pixel.length; ++isoString) {
            try {
                pixel[isoString] = (byte)(~(255 & Integer.parseInt(octetStr.substring(isoString * 2, isoString * 2 + 2), 16)));
            } catch (Exception var11) {
                var11.printStackTrace();
            }
        }

        String var12 = null;

        try {
            var12 = new String(pixel, "gb2312");
        } catch (UnsupportedEncodingException var10) {
            var10.printStackTrace();
        }

        return cmd + var12 + "\r\n";
    }

    public byte[] QiRui_DrawBar(int x, int y, int type, int height, int hri, int rotation, int cellwidth, String content) {
        String CodeType = "128";
        switch(type) {
            case 0:
                CodeType = "128";
                break;
            case 1:
                CodeType = "39";
                break;
            case 2:
                CodeType = "93";
                break;
            case 3:
                CodeType = "ITF";
                break;
            case 4:
                CodeType = "UPCA";
                break;
            case 5:
                CodeType = "UPCE";
                break;
            case 6:
                CodeType = "CODABAR";
                break;
            case 7:
                CodeType = "EAN8";
                break;
            case 8:
                CodeType = "EAN13";
        }

        String cmd = "BARCODE " + x + "," + y + "," + "\"" + CodeType + "\"" + "," + height + "," + hri + "," + rotation + "," + cellwidth + "," + cellwidth + "," + "\"" + content + "\"\r\n";

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var12) {
            var12.printStackTrace();
            return null;
        }
    }

    private String sQiRui_DrawBar(int x, int y, int type, int height, int hri, int rotation, int cellwidth, String content) {
        String CodeType = "128";
        switch(type) {
            case 0:
                CodeType = "128";
                break;
            case 1:
                CodeType = "39";
                break;
            case 2:
                CodeType = "93";
                break;
            case 3:
                CodeType = "ITF";
                break;
            case 4:
                CodeType = "UPCA";
                break;
            case 5:
                CodeType = "UPCE";
                break;
            case 6:
                CodeType = "CODABAR";
                break;
            case 7:
                CodeType = "EAN8";
                break;
            case 8:
                CodeType = "EAN13";
        }

        String cmd = "BARCODE " + x + "," + y + "," + "\"" + CodeType + "\"" + "," + height + "," + hri + "," + rotation + "," + cellwidth + "," + cellwidth + "," + "\"" + content + "\"\r\n";
        return cmd;
    }

    public byte[] QiRui_DrawQRCode(int x, int y, int ecc, int rotation, int cellwidth, String content) {
        String strECC = "Q";
        switch(ecc) {
            case 0:
                strECC = "L";
                break;
            case 1:
                strECC = "M";
                break;
            case 2:
                strECC = "Q";
                break;
            case 3:
                strECC = "H";
        }

        String cmd = "QRCODE " + x + "," + y + "," + strECC + "," + cellwidth + ",A," + rotation + ",M2,S7," + "\"" + content + "\"\r\n";

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var10) {
            var10.printStackTrace();
            return null;
        }
    }

    private String sQiRui_DrawQRCode(int x, int y, int ecc, int rotation, int cellwidth, String content) {
        String strECC = "Q";
        switch(ecc) {
            case 0:
                strECC = "L";
                break;
            case 1:
                strECC = "M";
                break;
            case 2:
                strECC = "Q";
                break;
            case 3:
                strECC = "H";
        }

        String cmd = "QRCODE " + x + "," + y + "," + strECC + "," + cellwidth + ",A," + rotation + ",M2,S7," + "\"" + content + "\"\r\n";
        return cmd;
    }

    public byte[] QiRui_DrawPic(int x, int y, Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int bytewidth;
        if(width % 8 != 0) {
            bytewidth = width / 8 + 1;
        } else {
            bytewidth = width / 8;
        }

        String cmd = "BITMAP " + x + "," + y + "," + bytewidth + "," + height + ",1,";

        byte[] bcmd;
        try {
            bcmd = cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var16) {
            var16.printStackTrace();
            bcmd = null;
        }

        byte[] pixel = new byte[width * height];
        int comlen = 0;

        for(int totalcmd = 0; totalcmd < height; ++totalcmd) {
            for(int j = 0; j < bytewidth; ++j) {
                byte temp = 0;

                for(int k = 0; k < 8; ++k) {
                    if(j * 8 + k < width) {
                        int pixelColor = bitmap.getPixel(j * 8 + k, totalcmd);
                        if(pixelColor != -1 && pixelColor != 0) {
                            temp |= (byte)(128 >> k);
                        }
                    }
                }

                pixel[comlen++] = temp;
            }
        }

        byte[] var17 = new byte[bcmd.length + pixel.length];
        System.arraycopy(bcmd, 0, var17, 0, bcmd.length);
        System.arraycopy(pixel, 0, var17, bcmd.length, pixel.length);
        return var17;
    }

    public byte[] QiRui_DrawBox(int sx, int sy, int ex, int ey, int thickness, int radius) {
        String cmd = "BOX " + sx + "," + sy + "," + ex + "," + ey + "," + thickness + "," + radius + "\r\n";

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public byte[] QiRui_DrawCircle(int x, int y, int radius, int thinkness) {
        String cmd = "CIRCLE " + x + "," + y + "," + radius + "," + thinkness + "\r\n";

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public byte[] QiRui_Update(String filename) {
        try {
            return filename.getBytes("gb2312");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public byte[] QiRui_Density(int density) {
        String sDensity = "DENSITY " + density + "\r\n";

        try {
            return sDensity.getBytes("gb2312");
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public byte[] QiRui_Speed(int speed) {
        String sDensity = "SPEED " + speed + "\r\n";

        try {
            return sDensity.getBytes("gb2312");
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public byte[] QiRui_Cls() {
        String cmd = "CLS\r\n";

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    private String sQiRui_Cls() {
        String cmd = "CLS\r\n";
        return cmd;
    }

    public byte[] QiRui_Cut(boolean isCut) {
        String cmd;
        if(isCut) {
            cmd = "SET CUTTER 1\r\n";

            try {
                return cmd.getBytes("gb2312");
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
                return null;
            }
        } else {
            cmd = "SET CUTTER OFF\r\n";

            try {
                return cmd.getBytes("gb2312");
            } catch (UnsupportedEncodingException var5) {
                var5.printStackTrace();
                return null;
            }
        }
    }

    private String sQiRui_Cut(boolean isCut) {
        String cmd;
        if(isCut) {
            cmd = "SET CUTTER 1\r\n";
        } else {
            cmd = "SET CUTTER OFF\r\n";
        }

        return cmd;
    }

    public byte[] QiRui_Direction(int direction, int mirror) {
        String cmd = "DIRECTION " + direction + "," + mirror + "\r\n";

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    private String sQiRui_Direction(int direction, int mirror) {
        String cmd = "DIRECTION " + direction + "," + mirror + "\r\n";
        return cmd;
    }

    public byte[] QiRui_SetGap(boolean isEnable) {
        String cmd;
        if(isEnable) {
            cmd = "SET GAP ON\r\n";

            try {
                return cmd.getBytes("gb2312");
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
                return null;
            }
        } else {
            cmd = "SET GAP OFF\r\n";

            try {
                return cmd.getBytes("gb2312");
            } catch (UnsupportedEncodingException var5) {
                var5.printStackTrace();
                return null;
            }
        }
    }

    private String sQiRui_SetGap(boolean isEnable) {
        String cmd;
        if(isEnable) {
            cmd = "SET GAP ON\r\n";
        } else {
            cmd = "SET GAP OFF\r\n";
        }

        return cmd;
    }

    public byte[] QiRui_DrawDataMatrix(int x, int y, int width, int hight, String content) {
        String cmd = "DMATRIX " + x + "," + y + "," + width + "," + hight + "," + "\"" + content + "\"\r\n";

        try {
            return cmd.getBytes("gb2312");
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
            return null;
        }
    }

    /**
     * USB打印机状态获取
     * @param usbConnection
     * @param usbEndpointIn
     * @return
     */
    public int QiRui_PrinterState(UsbDeviceConnection usbConnection, UsbEndpoint usbEndpointIn){
        String cmd = "READSTA \r\n";
        try {
            USBUtil.getInstance().sendMessage(cmd.getBytes("gb2312"));
            Thread.sleep(500);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.PaperState = 0;
        this.IsBusy = true;
        this.Picked = false;
        this.CutState = false;

        byte[] bytes = new byte[usbEndpointIn.getMaxPacketSize()];
        int ret = usbConnection.bulkTransfer(usbEndpointIn, bytes, bytes.length, 500);
        if (ret < 0 )
            return 0;
        try {
            String str = new String(bytes, "gb2312");
            String[] state = str.split(",");
            Log.e("USBREAD", "state - > "+state.length);
            Log.e("USBREAD", "state[0] - > "+state[0].replace("READSTA","").trim());
            if (state.length>1){
                Log.e("USBREAD", "state[1] - > "+state[1]);
                Log.e("USBREAD", "state[2] - > "+state[2]);
                Log.e("USBREAD", "state[3] - > "+state[3]);
            }
            Log.e("USBREAD", "str - > "+str);
            String state0 = state[0].replace("READSTA","").trim();
            if(state0.equals("NOPAPER")) {
                this.PaperState = 0;//无纸
            } else if(state0.equals("PAPER")) {
                this.PaperState = 1;//有纸
            } else if(state0.equals("PAPEREND")) {
                this.PaperState = 2;//纸张快用完了
            } else if(state0.equals("LIBOPEN")) {
                this.PaperState = 3;//纸仓盖未关闭
            }
            if(state[1].equals("WAITPICK")) {
                this.Picked = false;//面单未取走
            } else if(state[1].equals("PICK")) {
                this.Picked = true;//面单已经取走
            }
            if(state[2].equals("IDLE")) {
                this.IsBusy = false;//空闲
            } else if(state[2].equals("BUSY")) {
                this.IsBusy = true;//繁忙
            }
            if(state[3].equals("CUTERERR")) {
                this.CutState = false;//切刀不正常
            } else if(state[3].equals("CUTEROK")) {
                this.CutState = true;//切刀正常
            }
            return 1;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取 USB 打印机版本号
     * @param usbConnection
     * @param usbEndpointIn
     * @return
     */
    public String QiRui_GetSN(UsbDeviceConnection usbConnection, UsbEndpoint usbEndpointIn) {
        String cmd = "READC PRDOCUTID\r\n";
        try {
            USBUtil.getInstance().sendMessage(cmd.getBytes("gb2312"));
            Thread.sleep(500);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[usbEndpointIn.getMaxPacketSize()];
        int ret = usbConnection.bulkTransfer(usbEndpointIn, bytes, bytes.length, 500);
        if (ret > 0){
            try {
                String str = new String(bytes, "gb2312");
                Log.e("USBREAD","USB_SN: "+str.substring(10));
                return str;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 串口打印机状态获取
     * @param mFileInputStream
     * @param mFileOutputStream
     * @return
     */
    public int QiRui_PrinterState(FileInputStream mFileInputStream, FileOutputStream mFileOutputStream) {
        try {
            int cmd = mFileInputStream.available();
            if(cmd > 0) {
                byte[] e = new byte[cmd];
                if(mFileInputStream.read(e, 0, e.length) != -1) {
                    ;
                }
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        this.PaperState = 0;
        this.IsBusy = true;
        this.Picked = false;
        this.CutState = false;
        String cmd1 = "READSTA \r\n";

        try {
            mFileOutputStream.write(cmd1.getBytes("gb2312"));
            Thread.sleep(500L);
        } catch (UnsupportedEncodingException var10) {
            var10.printStackTrace();
        } catch (IOException var11) {
            var11.printStackTrace();
        } catch (InterruptedException var12) {
            var12.printStackTrace();
        }

        try {
            int e1 = mFileInputStream.available();
            if(e1 <= 0) {
                return 0;
            }

            byte[] _tt = new byte[e1];
            if(mFileInputStream.read(_tt, 0, _tt.length) != -1) {
                String str = new String(_tt, "gb2312");
                String[] state = str.split("[ ,]");
                if(state[1].equals("LIBOPEN")) {
                    this.PaperState = 3;
                } else if(state[1].equals("NOPAPER")) {
                    this.PaperState = 0;
                } else if(state[1].equals("PAPEREND")) {
                    this.PaperState = 2;
                } else if(state[1].equals("PAPER")) {
                    this.PaperState = 1;
                }

                if(state[2].equals("WAITPICK")) {
                    this.Picked = false;
                } else if(state[2].equals("PICK")) {
                    this.Picked = true;
                }

                if(state[4].equals("CUTERERR")) {
                    this.CutState = false;
                } else if(state[4].equals("CUTEROK")) {
                    this.CutState = true;
                }

                if(state[3].equals("IDLE")) {
                    this.IsBusy = false;
                } else if(state[3].equals("BUSY")) {
                    this.IsBusy = true;
                }

                return 1;
            }
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        return 0;
    }

    public int QiRui_CheckPaper() {
        return this.PaperState;
    }

    public boolean QiRui_isPicked() {
        return this.Picked;
    }

    public boolean QiRui_CheckCut() {
        return this.CutState;
    }

    public boolean QiRui_isBusy() {
        return this.IsBusy;
    }

    public String QiRui_GetSN(FileInputStream mFileInputStream, FileOutputStream mFileOutputStream) {
        try {
            int cmd = mFileInputStream.available();
            if(cmd > 0) {
                byte[] e = new byte[cmd];
                if(mFileInputStream.read(e, 0, e.length) != -1) {
                    ;
                }
            }
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        String cmd1 = "READC PRDOCUTID\r\n";

        try {
            mFileOutputStream.write(cmd1.getBytes("gb2312"));
            Thread.sleep(1000L);
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        } catch (InterruptedException var11) {
            var11.printStackTrace();
        }

        try {
            int e1 = mFileInputStream.available();
            if(e1 > 0) {
                byte[] tt = new byte[e1];
                if(mFileInputStream.read(tt, 0, tt.length) != -1) {
                    String str = new String(tt, "gb2312");
                    return str;
                }
            }
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return null;
    }

    public String QiRui_GetVersion(FileInputStream mFileInputStream, FileOutputStream mFileOutputStream) {
        try {
            int cmd = mFileInputStream.available();
            if(cmd > 0) {
                byte[] e = new byte[cmd];
                if(mFileInputStream.read(e, 0, e.length) != -1) {
                    ;
                }
            }
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        String cmd1 = "READC VERSION \r\n";

        try {
            mFileOutputStream.write(cmd1.getBytes("gb2312"));
            Thread.sleep(1000L);
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        } catch (InterruptedException var11) {
            var11.printStackTrace();
        }

        try {
            int e1 = mFileInputStream.available();
            if(e1 <= 0) {
                return null;
            }

            byte[] tt = new byte[e1];
            if(mFileInputStream.read(tt, 0, tt.length) != -1) {
                String str = new String(tt, "gb2312");
                return str;
            }
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return null;
    }

    public int QiRui_CheckPaper(FileInputStream mFileInputStream, FileOutputStream mFileOutputStream) {
        try {
            int cmd = mFileInputStream.available();
            if(cmd > 0) {
                byte[] e = new byte[cmd];
                if(mFileInputStream.read(e, 0, e.length) != -1) {
                    ;
                }
            }
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        String cmd1 = "READSTA \r\n";

        try {
            mFileOutputStream.write(cmd1.getBytes("gb2312"));
            Thread.sleep(200L);
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        } catch (InterruptedException var11) {
            var11.printStackTrace();
        }

        try {
            int e1 = mFileInputStream.available();
            if(e1 > 0) {
                byte[] tt = new byte[e1];

                while(mFileInputStream.read(tt, 0, tt.length) != -1) {
                    String str = new String(tt, "gb2312");
                    String[] state = str.split("[ ,]");
                    if(state[1].equals("LIBOPEN")) {
                        return 3;
                    }

                    if(state[1].equals("NOPAPER")) {
                        return 0;
                    }

                    if(state[1].equals("PAPEREND")) {
                        return 2;
                    }

                    if(state[1].equals("PAPER")) {
                        return 1;
                    }
                }
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        return 0;
    }

    public boolean QiRui_isPicked(FileInputStream mFileInputStream, FileOutputStream mFileOutputStream) {
        try {
            int cmd = mFileInputStream.available();
            if(cmd > 0) {
                byte[] e = new byte[cmd];
                if(mFileInputStream.read(e, 0, e.length) != -1) {
                    ;
                }
            }
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        String cmd1 = "READSTA \r\n";

        try {
            mFileOutputStream.write(cmd1.getBytes("gb2312"));
            Thread.sleep(200L);
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        } catch (InterruptedException var11) {
            var11.printStackTrace();
        }

        try {
            int e1 = mFileInputStream.available();
            if(e1 > 0) {
                byte[] tt = new byte[e1];

                while(mFileInputStream.read(tt, 0, tt.length) != -1) {
                    String str = new String(tt, "gb2312");
                    String[] state = str.split("[ ,]");
                    if (state.length >= 3) {
                        if (state[2].equals("WAITPICK")) {
                            return false;
                        }

                        if (state[2].equals("PICK")) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        return false;
    }

    public boolean QiRui_CheckCut(FileInputStream mFileInputStream, FileOutputStream mFileOutputStream) {
        try {
            int cmd = mFileInputStream.available();
            if(cmd > 0) {
                byte[] e = new byte[cmd];
                if(mFileInputStream.read(e, 0, e.length) != -1) {
                    ;
                }
            }
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        String cmd1 = "READSTA \r\n";

        try {
            mFileOutputStream.write(cmd1.getBytes("gb2312"));
            Thread.sleep(200L);
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        } catch (InterruptedException var11) {
            var11.printStackTrace();
        }

        try {
            int e1 = mFileInputStream.available();
            if(e1 > 0) {
                byte[] tt = new byte[e1];

                while(mFileInputStream.read(tt, 0, tt.length) != -1) {
                    String str = new String(tt, "gb2312");
                    String[] state = str.split("[ ,]");
                    if(state[4].equals("CUTERERR")) {
                        return false;
                    }

                    if(state[4].equals("CUTEROK")) {
                        return true;
                    }
                }
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        return false;
    }

    public boolean QiRui_isBusy(FileInputStream mFileInputStream, FileOutputStream mFileOutputStream) {
        try {
            int cmd = mFileInputStream.available();
            if(cmd > 0) {
                byte[] e = new byte[cmd];
                if(mFileInputStream.read(e, 0, e.length) != -1) {
                    ;
                }
            }
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        String cmd1 = "READSTA \r\n";

        try {
            mFileOutputStream.write(cmd1.getBytes("gb2312"));
            Thread.sleep(200L);
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        } catch (InterruptedException var11) {
            var11.printStackTrace();
        }

        try {
            int e1 = mFileInputStream.available();
            if(e1 > 0) {
                byte[] tt = new byte[e1];

                while(mFileInputStream.read(tt, 0, tt.length) != -1) {
                    String str = new String(tt, "gb2312");
                    String[] state = str.split("[ ,]");
                    if(state[3].equals("IDLE")) {
                        return false;
                    }

                    if(state[3].equals("BUSY")) {
                        return true;
                    }
                }
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        return false;
    }

    public List<byte[]> QiRui_ParserFCboxJsonToQR(String jsonstring) {
        ArrayList dataList = new ArrayList();
        String TSPLresult = "";
        boolean LabelEndFlag = false;

        try {
            JSONObject e = new JSONObject(jsonstring);
            JSONArray nameList = e.getJSONArray("opts");
            int length = nameList.length();

            for(int i = 0; i < length; ++i) {
                String content = "";
                boolean level = false;
                boolean unitHeight = false;
                boolean qrVersion = false;
                boolean height = false;
                boolean ratio = false;
                boolean width = false;
                boolean endY = false;
                boolean endX = false;
                boolean beginY = false;
                boolean beginX = false;
                boolean fontType = false;
                boolean optCode = false;
                boolean isBold = false;
                JSONObject oj = nameList.getJSONObject(i);
                int var28 = oj.getInt("optCode");
                int var30;
                int var31;
                int var34;
                int var36;
                switch(var28) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    default:
                        break;
                    case 10:
                        dataList.add(this.QiRui_CreatePage(100, 180));
                        dataList.add(this.QiRui_Direction(0, 0));
                        dataList.add(this.QiRui_SetGap(true));
                        dataList.add(this.QiRui_Cut(true));
                        break;
                    case 11:
                        LabelEndFlag = true;
                        dataList.add(this.QiRui_PrintPage(1));
                        break;
                    case 12:
                        var30 = oj.getInt("x");
                        var31 = oj.getInt("y");
                        int var29 = oj.getInt("fontType");
                        isBold = oj.getBoolean("isBold");
                        content = oj.getString("content");
                        String font = "TSS24.BF2";
                        byte xmulti = 0;
                        byte ymulti = 0;
                        if(2 == var29) {
                            font = "TSS24.BF2";
                        } else if(1 == var29) {
                            font = "TSS16.BF2";
                        } else if(3 == var29) {
                            font = "TSS24.BF2";
                            xmulti = 1;
                            ymulti = 2;
                        } else if(4 == var29) {
                            font = "TSS32.BF2";
                        } else if(5 == var29) {
                            font = "TSS24.BF2";
                            xmulti = 2;
                            ymulti = 2;
                        } else if(6 == var29) {
                            font = "TSS24.BF2";
                            xmulti = 3;
                            ymulti = 3;
                        }

                        dataList.add(this.QiRui_Text(var30, var31, font, 0, xmulti, ymulti, isBold, content));
                        break;
                    case 13:
                        var30 = oj.getInt("beginX");
                        var31 = oj.getInt("beginY");
                        int var32 = oj.getInt("endX");
                        int var33 = oj.getInt("endY");
                        var34 = oj.getInt("width");
                        dataList.add(this.QiRui_DrawLine(var30, var31, var32, var33, var34 + 1, 0));
                        break;
                    case 14:
                        var30 = oj.getInt("x");
                        var31 = oj.getInt("y");
                        var34 = oj.getInt("width");
                        int var35 = oj.getInt("ratio");
                        var36 = oj.getInt("height");
                        content = oj.getString("number");
                        dataList.add(this.QiRui_DrawBar(var30, var31, 0, var36, 0, 0, 3, content));
                        break;
                    case 15:
                        var30 = oj.getInt("x");
                        var31 = oj.getInt("y");
                        int var37 = oj.getInt("qrVersion");
                        int var38 = oj.getInt("unitHeight");
                        int var39 = oj.getInt("level");
                        content = oj.getString("content");
                        dataList.add(this.QiRui_DrawQRCode(var30, var31, var39 - 1, 0, 4, content));
                        break;
                    case 16:
                        var30 = oj.getInt("x");
                        var31 = oj.getInt("y");
                        var34 = oj.getInt("width");
                        var36 = oj.getInt("height");
                        content = oj.getString("octetStr");
                        TSPLresult = TSPLresult + this.sQiRui_DrawPic(var30, var31, var34, var36, content);
                        dataList.add(this.QiRui_DrawPic(var30, var31, var34, var36, content));
                }
            }
        } catch (JSONException var27) {
            throw new RuntimeException(var27);
        }

        return LabelEndFlag?dataList:null;
    }

    public List<byte[]> QiRui_ParserFCboxJsonToQR(JSONObject result) {
        ArrayList dataList = new ArrayList();
        String TSPLresult = "";
        boolean LabelEndFlag = false;

        try {
            JSONArray e = result.getJSONArray("opts");
            int length = e.length();

            for(int i = 0; i < length; ++i) {
                String content = "";
                boolean level = false;
                boolean unitHeight = false;
                boolean qrVersion = false;
                boolean height = false;
                boolean ratio = false;
                boolean width = false;
                boolean endY = false;
                boolean endX = false;
                boolean beginY = false;
                boolean beginX = false;
                boolean fontType = false;
                boolean optCode = false;
                boolean isBold = false;
                JSONObject oj = e.getJSONObject(i);
                int var27 = oj.getInt("optCode");
                int var29;
                int var30;
                int var33;
                int var35;
                switch(var27) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    default:
                        break;
                    case 10:
                        dataList.add(this.QiRui_CreatePage(100, 180));
                        dataList.add(this.QiRui_Direction(0, 0));
                        dataList.add(this.QiRui_SetGap(true));
                        dataList.add(this.QiRui_Cut(true));
                        break;
                    case 11:
                        LabelEndFlag = true;
                        dataList.add(this.QiRui_PrintPage(1));
                        break;
                    case 12:
                        var29 = oj.getInt("x");
                        var30 = oj.getInt("y");
                        int var28 = oj.getInt("fontType");
                        isBold = oj.getBoolean("isBold");
                        content = oj.getString("content");
                        String font = "TSS24.BF2";
                        byte xmulti = 0;
                        byte ymulti = 0;
                        if(2 == var28) {
                            font = "TSS24.BF2";
                        } else if(1 == var28) {
                            font = "TSS16.BF2";
                        } else if(3 == var28) {
                            font = "TSS24.BF2";
                            xmulti = 1;
                            ymulti = 2;
                        } else if(4 == var28) {
                            font = "TSS32.BF2";
                        } else if(5 == var28) {
                            font = "TSS24.BF2";
                            xmulti = 2;
                            ymulti = 2;
                        } else if(6 == var28) {
                            font = "TSS24.BF2";
                            xmulti = 3;
                            ymulti = 3;
                        }

                        dataList.add(this.QiRui_Text(var29, var30, font, 0, xmulti, ymulti, isBold, content));
                        break;
                    case 13:
                        var29 = oj.getInt("beginX");
                        var30 = oj.getInt("beginY");
                        int var31 = oj.getInt("endX");
                        int var32 = oj.getInt("endY");
                        var33 = oj.getInt("width");
                        dataList.add(this.QiRui_DrawLine(var29, var30, var31, var32, var33 + 1, 0));
                        break;
                    case 14:
                        var29 = oj.getInt("x");
                        var30 = oj.getInt("y");
                        var33 = oj.getInt("width");
                        int var34 = oj.getInt("ratio");
                        var35 = oj.getInt("height");
                        content = oj.getString("number");
                        dataList.add(this.QiRui_DrawBar(var29, var30, 0, var35, 0, 0, 3, content));
                        break;
                    case 15:
                        var29 = oj.getInt("x");
                        var30 = oj.getInt("y");
                        int var36 = oj.getInt("qrVersion");
                        int var37 = oj.getInt("unitHeight");
                        int var38 = oj.getInt("level");
                        content = oj.getString("content");
                        dataList.add(this.QiRui_DrawQRCode(var29, var30, var38 - 1, 0, 4, content));
                        break;
                    case 16:
                        var29 = oj.getInt("x");
                        var30 = oj.getInt("y");
                        var33 = oj.getInt("width");
                        var35 = oj.getInt("height");
                        content = oj.getString("octetStr");
                        TSPLresult = TSPLresult + this.sQiRui_DrawPic(var29, var30, var33, var35, content);
                        dataList.add(this.QiRui_DrawPic(var29, var30, var33, var35, content));
                }
            }
        } catch (JSONException var26) {
            throw new RuntimeException(var26);
        }

        return LabelEndFlag?dataList:null;
    }
}
