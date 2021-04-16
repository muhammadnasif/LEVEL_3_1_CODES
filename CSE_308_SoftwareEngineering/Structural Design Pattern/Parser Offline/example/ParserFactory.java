package com.example;

import com.aesthetics.Aesthetics;
import com.parser.CPP_Parser;
import com.parser.C_Parser;
import com.parser.Parser;
import com.parser.Python_Parser;

public class ParserFactory extends AbstractFactory {
    @Override
    public Parser getParser(String parser) {
        if(parser == null){
            return null;
        }

        ///logic to implement if there is .py .cpp or .c extension
        String[] arrOfStr = parser.split("\\.", 0);
        parser = arrOfStr[1];


        if(parser.equalsIgnoreCase("c")){
            return new C_Parser();
        }else if(parser.equalsIgnoreCase("cpp")){
            return new CPP_Parser();
        }else if(parser.equalsIgnoreCase("py")){
            return new Python_Parser();
        }
        return null;
    }

    @Override
    Aesthetics getAesthetics(String aesthetics) {
        return null;
    }
}