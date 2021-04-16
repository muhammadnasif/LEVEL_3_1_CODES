package com.example;

public class PackageBuilder {
    void SelectPackage(String str,String f_work,String intConnection){
        Package pkg = new Package();
        MicroprocesssorSpecs product = null;
        if(str.equalsIgnoreCase("silver")){
             product = pkg.packageSilver();

        }
        else if(str.equalsIgnoreCase("gold")){
             product = pkg.packageGold();
        }
        else if(str.equalsIgnoreCase("diamond")){
             product = pkg.packageDiamond();
        }
        else if(str.equalsIgnoreCase("platinum")){
             product = pkg.packagePlatinum();
        }
        ShowSpecs(product,f_work,intConnection,str);
    }

    void ShowSpecs(MicroprocesssorSpecs ms,String f_work,String intConnection,String pkg){

        System.out.println(  "MicroController : "+ ms.name()+"\nDisplay Type:  "+ms.displayFunc().display()+"\nStorage Type: "+ms.storageType().storageType()
                +"\nIdentification Type: "+ms.identifier().identificationType()+"\nFramework: "+ms.webserver(f_work).type()
                +"\nInternet Connectivity: "+ms.internetConnection(intConnection).connectionType()+"\nController Type: "
                +ms.controlSystem().controllerType()+"\nMeasurement Type: "+ms.measurementSystem(pkg).measurementType()
        );
    }
}
