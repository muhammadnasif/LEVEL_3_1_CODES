package com.example;

import com.aesthetics.Aesthetics;
import com.parser.Parser;

import java.util.Scanner;

public class AbstractDesign {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter filename with extension(.c .cpp .py):");
        String str = scan.nextLine();


        AbstractFactory parserFactory =FactoryProducer.getFactory("Parser");
        Parser parser_1 = parserFactory.getParser(str);
        parser_1.print();

        AbstractFactory aestheticsFactory = FactoryProducer.getFactory("Aesthetics");
        Aesthetics aesthetics_1 = aestheticsFactory.getAesthetics(str);
        aesthetics_1.print();
        
    }
}
