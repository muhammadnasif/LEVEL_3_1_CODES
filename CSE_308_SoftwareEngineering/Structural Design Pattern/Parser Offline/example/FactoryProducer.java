package com.example;

public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("Parser")){
            return new ParserFactory();
        }
        if(choice.equalsIgnoreCase("Aesthetics")){
            return new AestheticsFactory();
        }
        return null;
    }
}
