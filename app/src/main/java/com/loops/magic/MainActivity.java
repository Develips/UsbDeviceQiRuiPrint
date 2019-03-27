package com.loops.magic;

import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.loops.magic.adapter.USBDeviceListAdapter;
import com.loops.magic.utils.JsonUtil;
import com.loops.magic.utils.print.PostPrintInfoEntity;
import com.loops.magic.utils.print.PrintModel;
import com.loops.magic.utils.usb.USBUtil;


/**
 * Created loops on 2019/3/27
 * description:
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "USB";
    private ListView listView;
    private USBDeviceListAdapter recAdapter;
    private UsbDevice usbDevice = null;
    private PostPrintInfoEntity InfoEntity = null;
    private PrintModel printModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        detectUsbWithBroadcast();
    }

    private void initData() {
        //得到本地json文本内容
        String foodJson = JsonUtil.getJson(this, "PrintTestData.json");
        InfoEntity = JsonUtil.JsonToObject(foodJson,PostPrintInfoEntity.class);
    }

    private void initView() {
        initData();
        findViewById(R.id.btn_refresh).setOnClickListener(this);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_print_a).setOnClickListener(this);
        findViewById(R.id.btn_print_b).setOnClickListener(this);
//        List<UsbDevice> usbDeviceList = USBUtil.getInstance().getDeviceList();
//        for (int i = 0; i < usbDeviceList.size(); i++) {
//            UsbDevice device = usbDeviceList.get(i);
//            if (device.getVendorId() == 2501 && device.getProductId() == 1416){
//                if (USBUtil.getInstance().hasPermission(device)) {
//                    //获取成功USB权限
//                } else {
//                    USBUtil.getInstance().requestPermission(device);
//                }
//                usbDevice = device;
//                Log.e("USBReceiver", "打印机"
//                        + "DeviceName: " + device.getDeviceName()+" , "
//                        + "VendorId: " + device.getVendorId()+" , "
//                        + "ProductId: " + device.getProductId()+" , "
//                );
//            }else if (device.getVendorId() == 1423 && device.getProductId() == 9815){
//                Log.e("USBReceiver", "摄像头"
//                        + "DeviceName: " + device.getDeviceName()+" , "
//                        + "VendorId: " + device.getVendorId()+" , "
//                        + "ProductId: " + device.getProductId()+" , "
//                );
//            }
//        }

        recAdapter = new USBDeviceListAdapter(this);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(recAdapter);
        recAdapter.setData(USBUtil.getInstance().getDeviceList());
        recAdapter.setOnItemClick(new USBDeviceListAdapter.OnItemClick() {
            @Override
            public void onItemClick(UsbDevice device) {
                if (USBUtil.getInstance().hasPermission(device)) {
                    //获取成功USB权限
                } else {
                    USBUtil.getInstance().requestPermission(device);
                }
                usbDevice = device;
            }
        });
    }

//    private QiRuiCommand printer = new QiRuiCommand();//打印工具类
//    public void getPrinterState() {
//        if (broadPath != null && isDeviceOpened()) {
//        int PaperState;
//            mOutputStream = (FileOutputStream) iOReadSeriaPort.getOutputStream();
//            mInputStream = (FileInputStream) iOReadSeriaPort.getInputStream();
    /**检测打印机状态，是否连接工控机*/
//        int printState = printer.QiRui_PrinterState();
//        if (printState == 1) {
//            PaperState = printer.QiRui_CheckPaper();
//            if (PaperState == 0) {
//                Log.e(TAG,"没有面单了");
//            } else if (PaperState == 1) {
//                /**打印机有纸*/
//                Log.e(TAG,"打印机有纸");
//            } else if (PaperState == 2) {
//                /**打印纸张即将用完，请及时更替*/
//                Log.e(TAG,"打印纸张即将用完，请及时更替");;
//            } else if (PaperState == 3) {
//                Log.e(TAG,"纸舱盖未关闭");
//            } else if (PaperState == 4) {
//                Log.e(TAG,"卡纸或纸张错误");
//            }
//        } else {
//            Log.e(TAG,"未检测到打印机,打印机连接失败");
//        }
//    } else {
//        Log.e(TAG,"打印设备未开启");
//    }

//    /**
//     * USB拔插监听
//     */
//    private void detectUsbWithBroadcast() {
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
//        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
//        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
//        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
//        filter.addAction("android.hardware.usb.action.USB_STATE");
//        registerReceiver(mUsbStateChangeReceiver, filter);
//    }
//    private BroadcastReceiver mUsbStateChangeReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.e(TAG, "onReceive: " + intent.getAction());
//            String action = intent.getAction();
//            UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
//            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
//                Log.e(TAG,"有新的设备插入了");
//                if (device != null) {
//                    if (device.getVendorId() == 2501 && device.getProductId() == 1416){
//                        Log.e("USBReceiver", "打印机已经接入："
//                                + "DeviceName: " + device.getDeviceName()+" , "
//                                + "VendorId: " + device.getVendorId()+" , "
//                                + "ProductId: " + device.getProductId()+" , "
//                        );
//                    }else if (device.getVendorId() == 1423 && device.getProductId() == 9815){
//                        Log.e("USBReceiver", "摄像头已经接入："
//                                + "DeviceName: " + device.getDeviceName()+" , "
//                                + "VendorId: " + device.getVendorId()+" , "
//                                + "ProductId: " + device.getProductId()+" , "
//                        );
//                    }
//                }
//            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
//                Log.e(TAG,"有设备拔出了");
//                if (device != null) {
//                    if (device.getVendorId() == 2501 && device.getProductId() == 1416) {
//                        Log.e("USBReceiver", "打印机已经拔出："
//                                + "DeviceName: " + device.getDeviceName() + " , "
//                                + "VendorId: " + device.getVendorId() + " , "
//                                + "ProductId: " + device.getProductId() + " , "
//                        );
//                    } else if (device.getVendorId() == 1423 && device.getProductId() == 9815) {
//                        Log.e("USBReceiver", "摄像头已经拔出："
//                                + "DeviceName: " + device.getDeviceName() + " , "
//                                + "VendorId: " + device.getVendorId() + " , "
//                                + "ProductId: " + device.getProductId() + " , "
//                        );
//                    }
//                }
//            }
//        }
//    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_refresh:
                recAdapter.setData(USBUtil.getInstance().getDeviceList());
                usbDevice = null;
                break;
            case R.id.btn_confirm:
                if (usbDevice == null) {
                    Toast.makeText(this, "还未选择USB", Toast.LENGTH_SHORT).show();
                } else {
                    if (USBUtil.getInstance().hasPermission(usbDevice)) {
                        //获取成功USB权限
                        if (USBUtil.getInstance().openPort(usbDevice)) {
                            printModel = new PrintModel(this);
                            if (InfoEntity != null) {
                                printModel.onSwitchPrint(InfoEntity);
                            } else {
                                Toast.makeText(this, "打印数据不能为空", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(this, "设备打开失败", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        USBUtil.getInstance().requestPermission(usbDevice);
                    }
                }
                break;
            case R.id.btn_get:
                if (usbDevice == null) {
                    Toast.makeText(this, "还未选择USB", Toast.LENGTH_SHORT).show();
                } else {
                    if (USBUtil.getInstance().hasPermission(usbDevice)) {
                        //获取成功USB权限
                        if (USBUtil.getInstance().openPort(usbDevice)) {
                            int PrinterState = USBUtil.getInstance().getQiRuiUsbPrinterState();
                            String SN = USBUtil.getInstance().getQiRui_GetSN().substring(0,10);
                            Log.e("USB-SN","USB-SN: "+SN);
                            if (SN.equals("0000000000")){
                                BaseApplication.getInstance().setUsbDeviceA(usbDevice);
                            }else if (SN.equals("1812150000")){
                                BaseApplication.getInstance().setUsbDeviceB(usbDevice);
                            }
                            if (PrinterState == 0){
                                Toast.makeText(this, "打印机连接失败", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(this, "打印机连接成功", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        USBUtil.getInstance().requestPermission(usbDevice);
                    }
                }
                break;
            case R.id.btn_print_a:
                UsbDevice usbDeviceA = BaseApplication.getInstance().getUsbDeviceA();
                if (usbDeviceA == null) {
                    Toast.makeText(this, "还未选择USB", Toast.LENGTH_SHORT).show();
                } else {
                    if (USBUtil.getInstance().hasPermission(usbDeviceA)) {
                        //获取成功USB权限
                        if (USBUtil.getInstance().openPort(usbDeviceA)) {
                            printModel = new PrintModel(this);
                            if (InfoEntity != null) {
                                printModel.onSwitchPrint(InfoEntity);
                            } else {
                                Toast.makeText(this, "打印数据不能为空", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(this, "设备打开失败", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        USBUtil.getInstance().requestPermission(usbDeviceA);
                    }
                }
                break;
            case R.id.btn_print_b:
                UsbDevice usbDeviceB = BaseApplication.getInstance().getUsbDeviceB();
                if (usbDeviceB == null) {
                    Toast.makeText(this, "还未选择USB", Toast.LENGTH_SHORT).show();
                } else {
                    if (USBUtil.getInstance().hasPermission(usbDeviceB)) {
                        //获取成功USB权限
                        if (USBUtil.getInstance().openPort(usbDeviceB)) {
                            printModel = new PrintModel(this);
                            if (InfoEntity != null) {
                                printModel.onSwitchPrint(InfoEntity);
                            } else {
                                Toast.makeText(this, "打印数据不能为空", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(this, "设备打开失败", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        USBUtil.getInstance().requestPermission(usbDeviceB);
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        USBUtil.getInstance().unRegisterReceiver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        USBUtil.getInstance().registerReceiver(this);
    }

}
