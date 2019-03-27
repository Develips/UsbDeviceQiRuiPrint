package com.loops.magic.utils.usb;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.widget.Toast;

import com.loops.magic.BaseApplication;
import com.loops.magic.utils.print.QiRuiCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.loops.magic.utils.usb.USBReceiver.ACTION_USB_PERMISSION;

/**
 * Created loops on 2019/3/27
 * description: 
 */
public class USBUtil {

    private static USBUtil instance;
    private Context mContext;
    //    private UsbManager usbManager;
    private UsbInterface usbInterface;
    private UsbEndpoint usbEndpointIn,usbEndpointOut;
    private UsbDeviceConnection usbConnection;
    private USBReceiver usbReceiver;
    private PendingIntent mPermissionIntent;
    private QiRuiCommand qiRuiCommand = new QiRuiCommand();

    public USBUtil() {}

    public static USBUtil getInstance(){
        if (instance == null)
            synchronized (USBUtil.class) {
                if (instance == null)
                    instance = new USBUtil();
            }
        return instance;
    }

    public void init(Context context){
        this.mContext = context;
        //usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        usbReceiver = new USBReceiver();
    }

    public UsbManager getUsbManager(){
        return (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
    }

    /**
     * 获取 USB 设备列表
     * @return
     */
    public List<UsbDevice> getDeviceList() {
        HashMap<String, UsbDevice> deviceList = getUsbManager().getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        List<UsbDevice> usbDevices = new ArrayList<>();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            /**只显示是打印机的设备*/
            if (device.getVendorId() == 2501 && device.getProductId() == 1416){
                usbDevices.add(device);
            }
        }
        return usbDevices;
    }

    /**
     * 获取特定 USB 设备
     * @param vendorId   厂商ID
     * @param productId  产品ID
     * @return UsbDevice
     */
    public UsbDevice getUsbDevice(int vendorId, int productId) {
        HashMap<String, UsbDevice> deviceList = getUsbManager().getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            if (device.getVendorId() == vendorId && device.getProductId() == productId) {
                return device;
            }
        }
        return null;
    }

    /**
     * 打开设备
     * @param device
     * @return
     */
    public boolean openPort(UsbDevice device) {
        if (device == null)
            return false;
        //获取设备接口
        usbInterface = device.getInterface(0);
        // 判断是否有权限
        if (hasPermission(device)) {
            // 打开设备，获取 UsbDeviceConnection 对象，连接设备，用于后面的通讯
            usbConnection = getUsbManager().openDevice(device);
            if (usbConnection == null) {
                return false;
            }
            if (usbConnection.claimInterface(usbInterface, true)) {
                Toast.makeText(BaseApplication.getInstance(), "找到 USB 设备接口", Toast.LENGTH_SHORT).show();
            } else {
                usbConnection.close();
                Toast.makeText(BaseApplication.getInstance(), "没有找到 USB 设备接口", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(BaseApplication.getInstance(), "没有 USB 权限", Toast.LENGTH_SHORT).show();
            return false;
        }
        //获取接口上的两个端点，分别对应 OUT 和 IN
        for (int i = 0; i < usbInterface.getEndpointCount(); ++i) {
            UsbEndpoint point = usbInterface.getEndpoint(i);
            if (point.getDirection() == UsbConstants.USB_DIR_IN) {
                usbEndpointIn = point;
            } else {
                usbEndpointOut = point;
            }
        }
        return true;
    }

    public void closePort() {
        if (usbConnection == null)
            return;
        try {
            usbConnection.close();
            usbConnection.releaseInterface(usbInterface);
            usbConnection = null;
            usbEndpointIn = null;
            usbEndpointOut = null;
            //usbManager = null;
            usbInterface = null;
        } catch (Exception e) {
        }
    }

    /**
     * 发送数据
     * @param bytes
     * @return
     */
    public int sendMessage(byte[] bytes) {
        if (usbConnection != null)
            return usbConnection.bulkTransfer(usbEndpointOut, bytes, bytes.length, 500);
        return 0;
    }

    /**
     * 发送获取打印机状态指令
     * @return 打印机是否连接
     */
    public int getQiRuiUsbPrinterState() {
        usbConnection.claimInterface(usbInterface, true);
        return qiRuiCommand.QiRui_PrinterState(usbConnection,usbEndpointIn);
    }

    /**
     * 获取打印机唯一标识
     * @return
     */
    public String getQiRui_GetSN(){
        return qiRuiCommand.QiRui_GetSN(usbConnection,usbEndpointIn);
    }

    /**
     * 判断对应 USB 设备是否有权限
     */
    public boolean hasPermission(UsbDevice device) {
        return getUsbManager().hasPermission(device);
    }

    /**
     * 请求获取指定 USB 设备的权限
     */
    public void requestPermission(UsbDevice device) {
        if (device != null) {
            if (hasPermission(device)) {
                Toast.makeText(BaseApplication.getInstance(), "已经获取到权限", Toast.LENGTH_SHORT).show();
            } else {
                if (mPermissionIntent != null) {
                    getUsbManager().requestPermission(device, mPermissionIntent);
                    Toast.makeText(BaseApplication.getInstance(), "请求USB权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 权限获取
     */
    public void registerReceiver(Activity context) {
        mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        context.registerReceiver(usbReceiver, filter);
    }

    public void unRegisterReceiver(Activity context) {
        context.unregisterReceiver(usbReceiver);
        mPermissionIntent = null;
    }
}
