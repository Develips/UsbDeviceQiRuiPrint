package com.loops.magic.utils.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.widget.Toast;

import com.loops.magic.BaseApplication;

/**
 * Created loops on 2019/3/27
 * description: 
 */
public class USBReceiver extends BroadcastReceiver {

    public static final String ACTION_USB_PERMISSION = "com.android.USB_PERMISSION";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
        if (ACTION_USB_PERMISSION.equals(action)) {
            // 获取权限结果的广播
            synchronized (this) {
                if (device != null) {
                    //call method to set up device communication
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        Log.e("USBReceiver", "获取权限成功：" + device.getDeviceName());
                    } else {
                        Log.e("USBReceiver", "获取权限失败：" + device.getDeviceName());
                    }
                }
            }
        }else if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
            // 有新的设备插入了，在这里一般会判断这个设备是不是我们想要的，是的话就去请求权限
            /**有新设备，重新启动USB服务，重新刷新USB列表*/
            //USBUtil.getInstance().init(BaseApplication.getInstance());
            if (device != null) {
                if (device.getVendorId() == 2501 && device.getProductId() == 1416){
                    Toast.makeText(BaseApplication.getInstance(), "打印机已经接入", Toast.LENGTH_SHORT).show();
                    Log.e("USBReceiver", "打印机已经接入："
                            + "DeviceName: " + device.getDeviceName()+" , "
                            + "VendorId: " + device.getVendorId()+" , "
                            + "ProductId: " + device.getProductId()+" , "
                    );
                }else if (device.getVendorId() == 1423 && device.getProductId() == 9815){
                    Toast.makeText(BaseApplication.getInstance(), "摄像头已经接入", Toast.LENGTH_SHORT).show();
                    Log.e("USBReceiver", "摄像头已经接入："
                            + "DeviceName: " + device.getDeviceName()+" , "
                            + "VendorId: " + device.getVendorId()+" , "
                            + "ProductId: " + device.getProductId()+" , "
                    );
                }else{
                    Log.e("USBReceiver", "未知设备已经接入："
                            + "DeviceName: " + device.getDeviceName()+" , "
                            + "VendorId: " + device.getVendorId()+" , "
                            + "ProductId: " + device.getProductId()+" , "
                    );
                }
            }
        } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
            // 有设备拔出了
            if (device != null) {
                if (device.getVendorId() == 2501 && device.getProductId() == 1416) {
                    Toast.makeText(BaseApplication.getInstance(), "打印机已经拔出", Toast.LENGTH_SHORT).show();
                    Log.e("USBReceiver", "打印机已经拔出："
                            + "DeviceName: " + device.getDeviceName() + " , "
                            + "VendorId: " + device.getVendorId() + " , "
                            + "ProductId: " + device.getProductId() + " , "
                    );
                } else if (device.getVendorId() == 1423 && device.getProductId() == 9815) {
                    Toast.makeText(BaseApplication.getInstance(), "摄像头已经拔出", Toast.LENGTH_SHORT).show();
                    Log.e("USBReceiver", "摄像头已经拔出："
                            + "DeviceName: " + device.getDeviceName() + " , "
                            + "VendorId: " + device.getVendorId() + " , "
                            + "ProductId: " + device.getProductId() + " , "
                    );
                }else{
                    Log.e("USBReceiver", "未知设备已经拔出："
                            + "DeviceName: " + device.getDeviceName()+" , "
                            + "VendorId: " + device.getVendorId()+" , "
                            + "ProductId: " + device.getProductId()+" , "
                    );
                }
            }
        }
    }
}
