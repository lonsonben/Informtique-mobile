package com.example.bluetoothdivicesscanner;

public class DeviceClass {
    private String deviceName;
    private String addressMac;
    private String type;

    public DeviceClass(String name, String addressMac, String type){
        this.deviceName = name;
        this.addressMac = addressMac;
        this.type = type;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getAddress() {
        return this.addressMac;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setAddress(String addressMac) {
        this.addressMac = addressMac;
    }

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }
}
