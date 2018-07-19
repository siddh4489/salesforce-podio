/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfdc.podio.model;

/**
 *
 * @author Siddhrajsinh_Atodari
 */
public class SalesforcePodioItemModel {
    public String title;
    public String name;
    public String sfdcid;

    public String getSfdcid() {
        return sfdcid;
    }

    public void setSfdcid(String sfdcid) {
        this.sfdcid = sfdcid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
