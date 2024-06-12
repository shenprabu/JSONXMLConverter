package com.parser;

import com.parser.interfaces.XMLJSONConverter;

public class ConverterFactory {

    XMLJSONConverter cswXMLJSONConverter = null;

    public static XMLJSONConverter getConverter() {
        if(this.cswXMLJSONConverter == null) {
            this.cswXMLJSONConverter = new CSWXMLJSONConverter();
        }
        return this.cswXMLJSONConverter;
    }
}
