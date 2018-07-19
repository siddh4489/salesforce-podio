/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfdc.podio.rest;

import com.sfdc.podio.operation.SalesforcePodioOperation;
import com.sfdc.podio.parser.SalesforcePodioDataParser;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Siddharaj Atodaria
 */
@Path("/podio")
public class SalesforcePodioRestApi {

    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/totalProperty")
    public Response getTotalItems() {
        String data = "{\"title\":\"Sergey\",\"name\":\"Kargopolov\"}";
        //Integer ItemId = SalesforcePodioOperation.newProperty(SalesforcePodioDataParser.jsonToObject(data));
        String output = "Total Items : " + SalesforcePodioOperation.getTotalItems();
        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("/newProperty")
    public Response newProperty(String data, @HeaderParam("itemId") Integer itemId) {
        String itemref = "";
        System.out.println(" data : " + data);
        System.out.println(" itemId : " + itemId);
        System.out.println(" called method : newProperty ");
        itemId = (itemId != null ? itemId : 0);
        if (SalesforcePodioOperation.existingPropertyCheck(itemId)) {
            System.out.println(" In  : Update Property ");
            boolean checkUpdate = SalesforcePodioOperation.updateProperty(itemId, SalesforcePodioDataParser.jsonToObject(data));
            System.out.println(" Update Property Status :" + checkUpdate);
            if (checkUpdate) {
                itemref = "Updated Successfully";
            } else {
                itemref = "Update Failed";
            }
        } else {
            System.out.println(" In : New Property");
            itemref = SalesforcePodioOperation.newProperty(SalesforcePodioOperation.getApplicationId("Test App"), SalesforcePodioDataParser.jsonToObject(data)).toString();
            System.out.println("Created Property itemid : " + itemref);
            itemref = "Created Property ItemId '" + itemref + "'";
        }
        return Response.status(201).entity("Status : " + itemref.toString()).build();
    }

}
