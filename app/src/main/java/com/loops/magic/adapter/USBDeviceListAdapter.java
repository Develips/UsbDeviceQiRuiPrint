package com.loops.magic.adapter;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loops.magic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class USBDeviceListAdapter extends BaseAdapter {

    private Context mContext;
    private List<UsbDevice> devices;
    private OnItemClick onItemClick;
    private HashMap<Integer, View> lmap = new HashMap<Integer, View>();

    public USBDeviceListAdapter(Context ctx) {
        devices = new ArrayList<>();
        this.mContext = ctx;
    }

    public void setOnItemClick(OnItemClick click){
        this.onItemClick = click;
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int position) {
        return devices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final UsbDevice usbDevice = devices.get(position);
        VH holder;
        if (lmap.get(position) == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.usb_rec_item, parent, false);
            holder = new VH(convertView);
            convertView.setTag(holder);
        } else {
            convertView = lmap.get(position);
            holder = (VH) convertView.getTag();
        }
        holder.deviceName.setText(usbDevice.getDeviceName()+"");
        holder.deviceId.setText(usbDevice.getDeviceId()+"");
        holder.vendorId.setText(usbDevice.getVendorId()+"");
        holder.productId.setText(usbDevice.getProductId()+"");
        if (usbDevice.getVendorId() == 2501 && usbDevice.getProductId() == 1416){
            holder.deviceName.setText("打印机");
        }else if (usbDevice.getVendorId() == 1423 && usbDevice.getProductId() == 9815){
            holder.deviceName.setText("摄像头");
        }else{
            holder.deviceName.setText("未知");
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(usbDevice);
            }
        });
        return convertView;
    }

    public void setData(List<UsbDevice> data){
        this.devices = data;
        notifyDataSetChanged();
    }

    class VH {
        TextView deviceName,device,deviceId,vendorId,productId;

        public VH(@NonNull View itemView) {
            deviceName = itemView.findViewById(R.id.tv_device_name);
            device = itemView.findViewById(R.id.tv_device);
            deviceId = itemView.findViewById(R.id.tv_device_id);
            vendorId = itemView.findViewById(R.id.tv_vendor_id);
            productId = itemView.findViewById(R.id.tv_product_id);
        }
    }

    public interface OnItemClick{
        void onItemClick(UsbDevice device);
    }
}
