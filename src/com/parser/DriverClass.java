package com.parser;

import com.parser.interfaces.XMLJSONConverter;
import org.json.JSONException;

import java.io.IOException;


public class DriverClass {

    public static void main(String[] args) {

        XMLJSONConverter converter = ConverterFactory.getConverter();
        try {
            converter.convertJSONtoXML(args[0], args[1]);
            System.out.println("Finished !!");
        } catch (IOException | JSONException e) {
            System.out.println("Parsing Failed...");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Parameters. Parsing Failed...");
            e.printStackTrace();
        }

    }
}
