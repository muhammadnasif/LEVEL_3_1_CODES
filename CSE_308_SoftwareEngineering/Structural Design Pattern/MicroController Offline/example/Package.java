package com.example;

public class Package {
    ATMega32 packageSilver(){

        ATMega32 atmega = new ATMega32();


        return atmega;
    }

    ArduinoMega packageGold(){
        ArduinoMega arMega = new ArduinoMega();
        return arMega;
    }

    RasberryPi packageDiamond(){
        RasberryPi rp = new RasberryPi();
        return rp;
    }

    RasberryPi packagePlatinum(){
        RasberryPi rp = new RasberryPi();
        return rp;
    }

}
