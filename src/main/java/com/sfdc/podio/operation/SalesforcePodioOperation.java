/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfdc.podio.operation;

import com.podio.APIApplicationException;
import com.podio.app.AppAPI;
import com.podio.app.Application;
import com.podio.item.FieldValuesUpdate;
import com.podio.item.Item;
import com.podio.item.ItemAPI;
import com.podio.item.ItemCreate;
import com.podio.item.ItemUpdate;
import com.podio.item.ItemsResponse;
import com.sfdc.podio.connection.SalesforcePodioConnectionPool;
import com.sfdc.podio.model.SalesforcePodioItemModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Siddhrajsinh_Atodari
 */
public class SalesforcePodioOperation {

    public static Integer getTotalItems() {
        ItemAPI itemAPI = SalesforcePodioConnectionPool.sfdcConncetion().getAPI(ItemAPI.class);
        ItemsResponse response = itemAPI.getItems(21212844, null, null, null, null);
        return response.getTotal();
    }

    public static Integer getApplicationId(String AppName) {
        AppAPI appAPI = SalesforcePodioConnectionPool.sfdcConncetion().getAPI(AppAPI.class);
        List<Application> apps = appAPI.getApps();

        Integer AppId = null;
        for (Application a : apps) {
            if (a.getConfiguration().getName().equalsIgnoreCase(AppName)) {
                AppId = a.getId();
            }

        }
        return AppId;
    }

    public static Integer newProperty(Integer AppId, SalesforcePodioItemModel modelObj) {
        int itemId = 400;
        try {
            ItemAPI itemAPI = SalesforcePodioConnectionPool.sfdcConncetion().getAPI(ItemAPI.class);
            List<FieldValuesUpdate> fieldList = new ArrayList<FieldValuesUpdate>();
            fieldList.add(new FieldValuesUpdate("title", "value", (modelObj.getTitle() != null && modelObj.getTitle() != "" ? modelObj.getTitle() : " ")));
            fieldList.add(new FieldValuesUpdate("name", "value", (modelObj.getName() != null && modelObj.getName() != "" ? modelObj.getName() : " ")));
            fieldList.add(new FieldValuesUpdate("salesforce-id", "value", (modelObj.getSfdcid() != null && modelObj.getSfdcid() != "" ? modelObj.getSfdcid() : " ")));
            itemId = itemAPI.addItem(AppId, new ItemCreate(null, fieldList, null, null), false);
        } catch (APIApplicationException ex) {
            System.out.println("newProperty :" + ex);
            return itemId;
        }
        return itemId;
    }

    public static boolean updateProperty(Integer ItemId, SalesforcePodioItemModel modelObj) {
        try {
            ItemAPI itemAPI = SalesforcePodioConnectionPool.sfdcConncetion().getAPI(ItemAPI.class);
            List<FieldValuesUpdate> lupdate = new ArrayList<FieldValuesUpdate>();
            lupdate.add(new FieldValuesUpdate("title", "value", (modelObj.getTitle() != null && modelObj.getTitle() != "" ? modelObj.getTitle() : " ")));
            lupdate.add(new FieldValuesUpdate("name", "value", (modelObj.getName() != null && modelObj.getName() != "" ? modelObj.getName() : " ")));
            lupdate.add(new FieldValuesUpdate("salesforce-id", "value", (modelObj.getSfdcid() != null && modelObj.getSfdcid() != "" ? modelObj.getSfdcid() : " ")));
            ItemUpdate updateObj = new ItemUpdate(null, lupdate);
            Item iobj = itemAPI.getItem(ItemId);
            updateObj.setRevision(iobj.getRevisions().get(0).getRevision());
            itemAPI.updateItem(ItemId, updateObj, true, false);
        } catch (APIApplicationException ex) {
            System.out.println("updateProperty :" + ex);
            return false;
        }

        return true;
    }

    public static boolean existingPropertyCheck(Integer itemId) {
        boolean existing = false;
        try {
            ItemAPI itemAPI = SalesforcePodioConnectionPool.sfdcConncetion().getAPI(ItemAPI.class);
            Item iobj = itemAPI.getItem(itemId);
            if (iobj != null) {
                existing = true;
            }
        } catch (APIApplicationException apiEx) {
            if (apiEx.getStatus().toString().equalsIgnoreCase("Forbidden")) {
                existing = false;
            }

        }

        return existing;
    }
}
