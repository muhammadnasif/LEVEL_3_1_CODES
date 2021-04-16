package com.weightMeasurement;

public class SensorMeasurement implements Measurement{
    @Override
    public String measurementType() {
        return "Load Sensor";
    }
}
