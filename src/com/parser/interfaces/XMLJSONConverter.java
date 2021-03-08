package com.parser.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public interface XMLJSONConverter {

    JSONObject readJSONFromFile(String inputJSONFileName) throws IOException, JSONException;

    void convertJSONtoXML(String inputJSONFileName, String outputXMLFileName) throws IOException, JSONException;

    boolean writeXMLToFile(String outputXMLFileName, String xml) throws IOException;

}
