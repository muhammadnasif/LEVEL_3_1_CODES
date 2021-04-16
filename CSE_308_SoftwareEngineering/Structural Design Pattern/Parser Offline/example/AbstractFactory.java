package com.example;

import com.aesthetics.Aesthetics;
import com.parser.Parser;

public abstract class AbstractFactory {
    abstract Parser getParser(String parser);
    abstract Aesthetics getAesthetics(String aesthetics);
}
