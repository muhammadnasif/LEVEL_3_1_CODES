package com.example;

import com.controller.Button;
import com.controller.Controller;
import com.displayTechnology.Display;
import com.displayTechnology.LED;
import com.framework.Django;
import com.framework.Laravel;
import com.framework.Webserver;
import com.identity.Identification;
import com.identity.RFID;
import com.internetConnectivity.Ethernet;
import com.internetConnectivity.GSM;
import com.internetConnectivity.Internet;
import com.internetConnectivity.WIFI;
import com.storage.ExternalStorage;
import com.storage.Storage;
import com.weightMeasurement.Measurement;
import com.weightMeasurement.SensorMeasurement;

public class ATMega32 implements MicroprocesssorSpecs{
    @Override
    public Display displayFunc() {
        return new LED();
    }

    @Override
    public Identification identifier() {
        return new RFID();
    }

    @Override
    public Storage storageType() {
        return new ExternalStorage();
    }

    @Override
    public Measurement measurementSystem(String str) {
        return new SensorMeasurement();
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

        return null;
    }

    @Override
    public Controller controlSystem() {
        return new Button();
    }

    public String name(){
        return "ATMega32";
    }
}
