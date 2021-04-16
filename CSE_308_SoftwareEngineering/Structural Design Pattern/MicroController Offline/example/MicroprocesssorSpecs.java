package com.example;

import com.controller.Controller;
import com.displayTechnology.Display;
import com.framework.Webserver;
import com.identity.Identification;
import com.storage.Storage;
import com.internetConnectivity.Internet;
import com.weightMeasurement.Measurement;

public interface MicroprocesssorSpecs {
    public Display displayFunc();
    public Identification identifier();
    public Storage storageType();
    public Measurement measurementSystem(String str);
    public Webserver webserver(String str);
    public Internet internetConnection(String str);
    public Controller controlSystem();
    public String name();

}
