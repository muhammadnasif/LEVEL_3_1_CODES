package com.example;

import com.aesthetics.Aesthetics;
import com.aesthetics.CPP_Aesthetics;
import com.aesthetics.C_Aesthetics;
import com.aesthetics.Python_Aesthetics;
import com.parser.Parser;

public class AestheticsFactory extends AbstractFactory {
    @Override
    Parser getParser(String parser) {
       return null;
    }

    @Override
    Aesthetics getAesthetics(String aesthetics) {
        if(aesthetics == null){
            return null;
        }
        String[] arrOfStr = aesthetics.split("\\.", 0);
        aesthetics = arrOfStr[1];

        if(aesthetics.equalsIgnoreCase("c")){
            return new C_Aesthetics();
        }else if(aesthetics.equalsIgnoreCase("cpp")){
            return new CPP_Aesthetics();
        }else if(aesthetics.equalsIgnoreCase("py")){
            return new Python_Aesthetics();
        }
        return null;
    }
}
