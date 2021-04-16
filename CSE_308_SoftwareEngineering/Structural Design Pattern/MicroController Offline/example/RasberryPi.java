package com.example;

import com.controller.Controller;
import com.controller.Touch;
import com.displayTechnology.Display;
import com.displayTechnology.TouchScreen;
import com.framework.Django;
import com.framework.Laravel;
import com.framework.Webserver;
import com.identity.Identification;
import com.identity.NFC;
import com.internetConnectivity.Ethernet;
import com.internetConnectivity.GSM;
import com.internetConnectivity.Internet;
import com.internetConnectivity.WIFI;
import com.storage.InternalStorage;
import com.storage.Storage;
import com.weightMeasurement.InterfaceMeasurement;
import com.weightMeasurement.Measurement;
import com.weightMeasurement.SensorMeasurement;

public class RasberryPi implements MicroprocesssorSpecs{
    @Override
    public Display displayFunc() {
        return new TouchScreen();
    }

    @Override
    public Identification identifier() {
        return new NFC();
    }


    @Override
    public Storage storageType() {
        return new InternalStorage();
    }

    @Override
    public Measurement measurementSystem(String str) {
        if(str.equalsIgnoreCase("Diamond")){
            return new SensorMeasurement();
        }
        else if(str.equalsIgnoreCase("Platinum")){
            return new InterfaceMeasurement();
        }
        return null;
    }

    @Override
    public Webserver webserver(String str) {
        if(str.equalsIgnoreCase("django")){
            return new Django();
        }
        else if(str.equalsIgnoreCase("laravel")){
            return new Laravel();
        }
        else if(str.equalsIgnoreCase("spring")){
            return new Laravel();
        }
        return null;
    }

    @Override
    public Internet internetConnection(String str) {
        if(str.equalsIgnoreCase("gsm")){
            return new GSM();
        }
        else if(str.equalsIgnoreCase("wifi")){
            return new WIFI();
        }
        else if(str.equalsIgnoreCase("ethernet")){
            return new Ethernet();
        }
        return null;
    }

    @Override
    public Controller controlSystem() {
        return new Touch();
    }

    public String name(){
        return "Rasberry Pi";
    }
}
