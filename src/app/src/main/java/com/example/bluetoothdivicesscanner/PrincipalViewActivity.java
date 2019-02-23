package com.example.bluetoothdivicesscanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

public class PrincipalViewActivity extends AppCompatActivity {
    private static final int REQUEST_BLUETOOTH = 1;
    private ListView listView;
    private ArrayAdapter aAdapter;
    private BluetoothAdapter bAdapter;
    private ArrayList<DeviceClass> mDeviceList = new ArrayList<DeviceClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_view);

        listView = (ListView) findViewById(R.id.deviceList);
        bAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bAdapter == null) {
             new AlertDialog.Builder(this)
                    .setTitle("Not compatible")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        if (!bAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);
        }

        Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
        if(pairedDevices.size()>0){
            for(BluetoothDevice device: pairedDevices){
                mDeviceList.add(new DeviceClass(device.getName(),
                                                device.getAddress(),
                                                getBTMajorDeviceClass(device.getBluetoothClass().getMajorDeviceClass())));
                Log.i("Name: " + device.getName() + "\n" , " MAC Address: " + device.getAddress());
            }
        }

        if(mDeviceList.size() == 0) {
            mDeviceList.add(new DeviceClass("No Devices Found", "", ""));
        }

        aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, mDeviceList);
        listView.setAdapter(aAdapter);
    }


    private String getBTMajorDeviceClass(int major){
        switch(major){
            case BluetoothClass.Device.Major.AUDIO_VIDEO:
                return "AUDIO_VIDEO";
            case BluetoothClass.Device.Major.COMPUTER:
                return "COMPUTER";
            case BluetoothClass.Device.Major.HEALTH:
                return "HEALTH";
            case BluetoothClass.Device.Major.IMAGING:
                return "IMAGING";
            case BluetoothClass.Device.Major.MISC:
                return "MISC";
            case BluetoothClass.Device.Major.NETWORKING:
                return "NETWORKING";
            case BluetoothClass.Device.Major.PERIPHERAL:
                return "PERIPHERAL";
            case BluetoothClass.Device.Major.PHONE:
                return "PHONE";
            case BluetoothClass.Device.Major.TOY:
                return "TOY";
            case BluetoothClass.Device.Major.UNCATEGORIZED:
                return "UNCATEGORIZED";
            case BluetoothClass.Device.Major.WEARABLE:
                return "AUDIO_VIDEO";
            default: return "unknown";
        }
    }
}
