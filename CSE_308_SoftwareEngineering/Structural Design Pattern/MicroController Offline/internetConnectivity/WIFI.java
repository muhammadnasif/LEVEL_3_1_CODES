package com.internetConnectivity;
public class WIFI implements Internet{
    @Override
    public String connectionType() {
        return "Wifi";
    }
}
