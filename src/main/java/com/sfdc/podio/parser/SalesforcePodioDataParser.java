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
        modelObj.setTitle(object.get("title").toString());
        modelObj.setName(object.get("name").toString());
        return modelObj;
    }

    public static void main(String[] args) {
        String jsonString = "{\"title\":\"Sergey\",\"name\":\"Kargopolov\"}";

        JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(jsonString).getAsJsonObject();
        System.out.println("====>" + objectFromString.get("title1"));
    }
}
