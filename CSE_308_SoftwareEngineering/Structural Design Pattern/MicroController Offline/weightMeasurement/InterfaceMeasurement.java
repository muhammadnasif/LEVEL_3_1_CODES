package com.weightMeasurement;

public class InterfaceMeasurement implements Measurement{
    @Override
    public String measurementType() {
        return "Weight module";
    }
}
