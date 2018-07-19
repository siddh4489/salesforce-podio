/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfdc.podio.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sfdc.podio.model.SalesforcePodioItemModel;

/**
 *
 * @author Siddhrajsinh_Atodari
 */
public class SalesforcePodioDataParser {

    public static SalesforcePodioItemModel jsonToObject(String jsonData) {
        SalesforcePodioItemModel modelObj = new SalesforcePodioItemModel();
        JsonParser jsonParser = new JsonParser();
        JsonObject object = jsonParser.parse(jsonData).getAsJsonObject();
        fieldList.add(new FieldValuesUpdate("title", "value", (modelObj.getTitle() != null && modelObj.getTitle() != "" ? modelObj.getTitle() : " ")));
        fieldList.add(new FieldValuesUpdate("name", "value", (modelObj.getName() != null && modelObj.getName() != "" ? modelObj.getName() : " ")));
        fieldList.add(new FieldValuesUpdate("salesforce-id", "value", (modelObj.getSfdcid() != null && modelObj.getSfdcid() != "" ? modelObj.getSfdcid() : " ")));
        return modelObj;
    }

    public static void main(String[] args) {
        String jsonString = "{\"title\":\"Sergey\",\"name\":\"Kargopolov\"}";

        JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(jsonString).getAsJsonObject();
        System.out.println("====>" + objectFromString.get("title1"));
    }
}
