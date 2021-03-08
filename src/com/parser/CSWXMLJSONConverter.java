package com.parser;

import com.parser.interfaces.XMLJSONConverter;
import com.parser.utils.XMLUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.Iterator;

public class CSWXMLJSONConverter implements XMLJSONConverter {

    @Override
    public JSONObject readJSONFromFile(String inputJSONFileName) throws IOException, JSONException {
        File file = new File("example.json");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String line;

        System.out.println("Reading JSON file...");
        StringBuilder jsonBuilder = new StringBuilder();
        while((line = bf.readLine()) != null) {
            jsonBuilder.append(line.trim());
        }
        bf.close();

        return new JSONObject(jsonBuilder.toString());
    }

    @Override
    public void convertJSONtoXML(String inputJSONFileName, String outputXMLFileName) throws IOException, JSONException {

        JSONObject json = readJSONFromFile(inputJSONFileName);

        System.out.println("Constructing XML...");
        StringBuilder xml = constructXML(json, true);

        writeXMLToFile(outputXMLFileName, xml.toString());
    }

    @Override
    public boolean writeXMLToFile(String outputXMLFileName, String xml) throws IOException {
        File file = new File(outputXMLFileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        System.out.println("Writing XML file...");
        BufferedWriter bf = new BufferedWriter(new FileWriter(file));
        bf.write(xml);
        bf.write("\n");

        bf.close();
        return true;
    }

    // helper methods
    public StringBuilder constructXML(JSONObject json, boolean isTopLevel) throws JSONException {
        StringBuilder xmlBuilder = new StringBuilder();

        Iterator<String> keys = json.keys();
        while(keys.hasNext()) {
            String propName = keys.next();
            xmlBuilder = getXML(json.get(propName), propName, xmlBuilder);
        }
        if(isTopLevel) {
            return new StringBuilder().append("<object>").append(xmlBuilder).append("</object>");
        }
        return xmlBuilder;
    }

    public StringBuilder constructXML(JSONArray jsonArray, boolean isTopLevel) throws JSONException {
        StringBuilder xmlBuilder = new StringBuilder();

        int i = 0; int len = jsonArray.length();
        while(i < len) {
            xmlBuilder = getXML(jsonArray.get(i), null, xmlBuilder);    // propName is null as jsonArray has only elements
            i++;
        }
        if(isTopLevel) {
            return new StringBuilder().append("<array>").append(xmlBuilder).append("</array>");
        }
        return xmlBuilder;
    }

    public StringBuilder getXML(Object obj, String propName, StringBuilder xmlBuilder) throws JSONException {

        String type = obj.getClass().toString();

        if(type.endsWith("JSONObject")) {   // class org.json.JSONObject

            StringBuilder jsonObjectXML = constructXML((JSONObject) obj, false);
            xmlBuilder.append(getTag(XMLUtil.OBJECT, propName, String.valueOf(jsonObjectXML)));

        } else if (type.endsWith("JSONArray")) {    // class org.json.JSONArray

            StringBuilder jsonArrayXML = constructXML((JSONArray) obj, false);
            xmlBuilder.append(getTag(XMLUtil.ARRAY, propName, String.valueOf(jsonArrayXML)));

        } else {
            String tagName = "";

            if(type.endsWith("Boolean")) {
                tagName = XMLUtil.BOOLEAN;
            } else if(type.endsWith("Integer") || type.endsWith("Double")) {
                tagName = XMLUtil.NUMBER;
            } else if(type.endsWith("String")) {
                tagName = XMLUtil.STRING;
            } else if(type.endsWith("Null")) {  // class org.json.JSONObject$Null
                tagName = XMLUtil.NULL;
            }
            xmlBuilder.append(getTag(tagName, propName, String.valueOf(obj)));
        }
        return xmlBuilder;
    }

    public StringBuilder getTag(String tagName, String propName, String value) {
        StringBuilder tag = new StringBuilder();
        tag.append("<").append(tagName);

        if(propName != null) {
            tag.append(" name=\"").append(propName).append("\"");
        }

        if(!tagName.equals(XMLUtil.NULL)) {
            tag.append(">").append(value).append("</").append(tagName).append(">");
        } else {
            tag.append("/>");
        }

        return tag;
    }
}
