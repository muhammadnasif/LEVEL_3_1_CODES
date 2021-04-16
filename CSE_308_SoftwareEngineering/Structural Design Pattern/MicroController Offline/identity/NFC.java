package com.identity;

public class NFC implements Identification{
    @Override
    public String identificationType() {
        return "NFC Identification";
    }
}
