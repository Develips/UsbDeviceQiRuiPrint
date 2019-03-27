package com.loops.magic;

import android.hardware.usb.UsbDevice;

import com.loops.magic.utils.usb.USBUtil;

/**
 * Created loops on 2019/3/27
 * description: 你喜欢的人又不喜欢你
 */
public class BaseApplication extends android.app.Application {

    private static BaseApplication instance;
    private UsbDevice usbDeviceA,usbDeviceB;

    public static BaseApplication getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Not yet initialized");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        USBUtil.getInstance().init(this);
    }

    public UsbDevice getUsbDeviceA() {
        return usbDeviceA;
    }

    public void setUsbDeviceA(UsbDevice usbDeviceA) {
        this.usbDeviceA = usbDeviceA;
    }

    public UsbDevice getUsbDeviceB() {
        return usbDeviceB;
    }

    public void setUsbDeviceB(UsbDevice usbDeviceB) {
        this.usbDeviceB = usbDeviceB;
    }
}
