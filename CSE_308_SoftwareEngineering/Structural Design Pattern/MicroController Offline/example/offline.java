package com.example;

import java.util.Locale;
import java.util.Scanner;

public class offline {
    public static void main(String[] args) {
        PackageBuilder pb = new PackageBuilder();
        Scanner scan = new Scanner(System.in);

        System.out.println("Package: ");
        String str1=scan.nextLine();
        System.out.println("Framework: ");
        String str2=scan.nextLine();
        System.out.println("Internet Connection Type: ");
        String str3=scan.nextLine();

        pb.SelectPackage(str1.toLowerCase(Locale.ROOT),str2.toLowerCase(Locale.ROOT),str3.toLowerCase(Locale.ROOT));

    }
}
