package com.parser;

import com.parser.interfaces.XMLJSONConverter;

public class ConverterFactory {

    public static XMLJSONConverter getConverter() {
        return new CSWXMLJSONConverter();
    }
}
