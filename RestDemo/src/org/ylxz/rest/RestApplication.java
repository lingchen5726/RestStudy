package org.ylxz.rest;


import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {  
    public RestApplication() {  
   
     //���������ڵİ�·��  
     packages("org.ylxz.rest.resources");  
     //ע��JSONת����  
     register(JacksonJsonProvider.class);  
   
    }  
}  
