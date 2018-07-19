/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfdc.podio.connection;

import com.podio.APIFactory;
import com.podio.ResourceFactory;
import com.podio.oauth.OAuthClientCredentials;
import com.podio.oauth.OAuthUsernameCredentials;
import com.sfdc.podio.util.SalesforcePodioConfiguration;
import java.util.Properties;

/**
 *
 * @author Siddhrajsinh_Atodaria
 * 
 * SalesforcePodioConnectionPool class is used for connection between Salesforce and podio system.
 */
public class SalesforcePodioConnectionPool {

    static APIFactory apiFactoryConnection;
    /** sfdcConncetion method return connection object between sfdc-podio. */
    public static APIFactory sfdcConncetion() {
        Properties prop = SalesforcePodioConfiguration.loadConfiguration();
        String username = (System.getenv("PODIO_USERNAME") != null ? 
                                   System.getenv("PODIO_USERNAME") : 
                                   prop.getProperty("username"));
        String password = (System.getenv("PODIO_PASSWORD") != null ? 
                                   System.getenv("PODIO_PASSWORD") : 
                                   prop.getProperty("password"));
        String client = (System.getenv("PODIO_CLIENT") != null ? 
                                 System.getenv("PODIO_CLIENT") : 
                                 prop.getProperty("client"));
        String Secret = (System.getenv("PODIO_SECRET") != null ? 
                                 System.getenv("PODIO_SECRET") : 
                                 prop.getProperty("secret"));

        ResourceFactory resourceFactory = new ResourceFactory(
                new OAuthClientCredentials(client, Secret),
                new OAuthUsernameCredentials(username, password));
        apiFactoryConnection = new APIFactory(resourceFactory);
        return apiFactoryConnection;
    }
}
