package com.parser.test;

import com.parser.CSWXMLJSONConverter;
import com.parser.ConverterFactory;
import com.parser.utils.XMLUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class TestCSWXMLJSONConverter {

    @Test
    public void testConstructXMLJSONObject() throws JSONException {
        String testJSON = "{\n" +
                "    \"name\" : \"Shen\",\n" +
                "    \"experience\" : 5.8,\n" +
                "    \"shortlisted\" : true,\n" +
                "    \"score\": null,\n" +
                "    \"tech\" : [\"java\", \"js\", \"c#\", \"pgsql\"]\n" +
                "}";
        JSONObject testJSONObj = new JSONObject(testJSON);

        String expectation = "<object><string name=\"name\">Shen</string><null name=\"score\"/><number name=\"experience\">5.8</number>" +
                "<array name=\"tech\"><string>java</string><string>js</string><string>c#</string><string>pgsql</string></array>" +
                "<boolean name=\"shortlisted\">true</boolean></object>";
        String reality = ((CSWXMLJSONConverter) ConverterFactory.getConverter()).constructXML(testJSONObj, true).toString();

        Assert.assertEquals(expectation, reality);
    }

    @Test
    public void testConstructXMLJSONArray() throws JSONException {
        String testJson = "[\"java\", 7, null, true]";
        JSONArray testJSONArr = new JSONArray(testJson);

        String expectation = "<array><string>java</string><number>7</number><null/><boolean>true</boolean></array>";
        String reality = ((CSWXMLJSONConverter) ConverterFactory.getConverter()).constructXML(testJSONArr, true).toString();

        Assert.assertEquals(expectation, reality);
    }

    @Test
    public void testGetTag() {
        String expectation = "<null/>";
        String reality = ((CSWXMLJSONConverter) ConverterFactory.getConverter()).getTag(XMLUtil.NULL, null, null).toString();

        Assert.assertEquals(expectation, reality);
    }
}
