package com.aesthetics;

public class C_Aesthetics implements Aesthetics{
    @Override
    public String font() {
        return "Courier New";
    }

    @Override
    public String style() {
        return "Normal";
    }

    @Override
    public String color() {
        return "Blue";
    }

    @Override
    public void print() {
        System.out.println("Font "+font()+"\nColor "+color()+"\nStyle "+style());
    }


}
