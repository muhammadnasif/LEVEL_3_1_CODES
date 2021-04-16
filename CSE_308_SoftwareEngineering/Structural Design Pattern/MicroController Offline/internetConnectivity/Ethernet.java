package com.internetConnectivity;

public class Ethernet implements Internet{
    @Override
    public String connectionType() {
        return "Ethernet";
    }
}
