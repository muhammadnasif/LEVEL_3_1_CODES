package com.identity;

public class RFID implements Identification{
    @Override
    public String identificationType() {
        return "RFID Identification";
    }
}
