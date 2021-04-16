package com.storage;

public class ExternalStorage implements Storage{
    @Override
    public String storageType() {
        return "External Storage";
    }
}
