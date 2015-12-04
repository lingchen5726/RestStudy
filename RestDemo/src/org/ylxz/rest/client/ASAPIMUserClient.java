package org.ylxz.rest.client;

import java.util.HashMap;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;  
import javax.ws.rs.client.ClientBuilder;  
import javax.ws.rs.client.Entity;  
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;  
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;  
  






import org.codehaus.jackson.jaxrs.JacksonJsonProvider;  
  
import org.glassfish.jersey.SslConfigurator;
import org.ylxz.rest.bean.User;  
  
   
/** 
 * 用户客户端，用来测试资源 
 * @author waylau.com 
 * 2014-3-18 
 */  
public class ASAPIMUserClient {  
  
	//App: UserApp
	//Key: YmjWpvFL7AUrmdO5WDkfXlDcuV0a
	//Secret: XRNtSEJTC3vC2uaTWU7PfptSuZoa
	//tmp token: 79d8bda8c598f69153859b0eb6a026
    private static String serverUri = "http://localhost:9090/RestDemo/rest"; 
    private static String asApimUsersUri = "http://localhost:8280/users/1.0.0"; 
    private static String tempToken = "3212f47ce21b63dab40b863e6a7e5";
    
    /** 
     * @param args 
     */  
    public static void main(String[] args) {
    	//getToken();
    	/*
        */
    	getAllUsers();
        addUser();
        getUserById();
        updateUser();
        getUserById();
        delUser();
        getUserById();
        getAllUsers();
    }  
    
    //TODO: the file location is fake, error
    private static void getToken() {
    	System.out.println("Entry of getToken");
    	//https://www.base64encode.org/
    	//key:secret is YmjWpvFL7AUrmdO5WDkfXlDcuV0a:XRNtSEJTC3vC2uaTWU7PfptSuZoa
    	//base64d is : WW1qV3B2Rkw3QVVybWRPNVdEa2ZYbERjdVYwYTpYUk50U0VKVEMzdkMydWFUV1U3UGZwdFN1Wm9h
    	//curl -k -d "grant_type=client_credentials" -H "Authorization: Basic WW1qV3B2Rkw3QVVybWRPNVdEa2ZYbERjdVYwYTpYUk50U0VKVEMzdkMydWFUV1U3UGZwdFN1Wm9h, Content-Type: application/x-www-form-urlencoded" https://10.25.31.83:8243/token
    		
    	SslConfigurator sslConfig = SslConfigurator.newInstance()
    	        .trustStoreFile("./truststore_client")
    	        .trustStorePassword("secret-password-for-truststore")
    	        .keyStoreFile("./keystore_client")
    	        .keyPassword("secret-password-for-keystore");
    	 
    	SSLContext sslContext = sslConfig.createSSLContext();
    	Client client = ClientBuilder.newBuilder().sslContext(sslContext).build().register(JacksonJsonProvider.class);
    	
            WebTarget target = client.target("https://10.25.31.83:8243/token");
            //MultivaluedMap<String, Object> headerMap = new MultivaluedMap<>(String, Object);
            //headerMap.add("Authorization", "Bearer cf228e2d9a418d5241569b1447c3a976");
            //headerMap.add("Content-Type", "application/x-www-form-urlencoded");
            Invocation.Builder invocationBuilder = target.request();
            invocationBuilder.header("Authorization", "Bearer WW1qV3B2Rkw3QVVybWRPNVdEa2ZYbERjdVYwYTpYUk50U0VKVEMzdkMydWFUV1U3UGZwdFN1Wm9h");
            invocationBuilder.header("Content-Type", "application/x-www-form-urlencoded");
            
            Response response = invocationBuilder.get();
            
            if (response.getStatus()!=200 && response.getStatus()!=204) {
           	 System.out.println(response.getStatus());
            }
            response.close();  
    }
    
    /** 
     * 添加用户 
     */  
     private static void addUser() {  
         System.out.println("To add User 006 Susan 21");  
         User user = new User("006","Susan","21");    
         Client client = ClientBuilder.newClient();  
         WebTarget target = client.target(asApimUsersUri);  
         
         Invocation.Builder invocationBuilder = target.request();
         invocationBuilder.header("Authorization", "Bearer "+tempToken);
         Response response = invocationBuilder.buildPost(Entity.entity(user, MediaType.APPLICATION_XML)).invoke();

         if (response.getStatus()!=200 && response.getStatus()!=204) {
        	 System.out.println(response.getStatus());
         }
         response.close();  
    }  
       
    /** 
     * 删除用户 
     */  
     private static void delUser() {  
         System.out.println("To delete User 006 Susan");  
         Client client = ClientBuilder.newClient();  
         WebTarget target = client.target(asApimUsersUri + "/006");  
         Invocation.Builder invocationBuilder = target.request();
         invocationBuilder.header("Authorization", "Bearer "+tempToken);
         Response response = invocationBuilder.delete();
         if (response.getStatus()!=200 && response.getStatus()!=204) {
        	 System.out.println(response.getStatus());
         }
         response.close();  
    }  
       
       
    /** 
     * 修改用户 
     */  
     private static void updateUser() {  
         System.out.println("To update user susan from 21 to 33");  
         User user = new User("006","Susan","33");    
         Client client = ClientBuilder.newClient();  
         WebTarget target = client.target(asApimUsersUri);  
         
         Invocation.Builder invocationBuilder = target.request();
         invocationBuilder.header("Authorization", "Bearer "+tempToken);
         Response response = invocationBuilder.buildPut(Entity.entity(user, MediaType.APPLICATION_XML)).invoke();
         //There is something error in the put
         if (response.getStatus()!=200 && response.getStatus()!=204) {
        	 System.out.println(response.getStatus());
         }
         
         response.close();  

     }  
    /** 
     * 根据id查询用户 
     */  
     private static void getUserById() {  
         System.out.println("****GetUserById*****");  
         Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);// 注册json 支持  
         WebTarget target = client.target(asApimUsersUri + "/006");
         Invocation.Builder invocationBuilder = target.request();
         invocationBuilder.header("Authorization", "Bearer "+tempToken);
         Response response = invocationBuilder.get();
         
         //User user = response.readEntity(User.class);
         String value = response.readEntity(String.class);  
         if ("".equals(value)) {
        	 System.out.println("No user found");
         } else {
        	 System.out.println(value);
         }
         //System.out.println(user.getUserId() + user.getUserName()  +  user.getAge());
         if (response.getStatus()!=200 && response.getStatus()!=204) {
        	 System.out.println(response.getStatus());
         }
         response.close();  
    }  
    /** 
     * 查询所有用户 
     */  
     private static void getAllUsers() {  
         System.out.println("*******getAllUsers****");  
           
         Client client = ClientBuilder.newClient();  
  
         WebTarget target = client.target(asApimUsersUri);  
         Invocation.Builder invocationBuilder = target.request();
         invocationBuilder.header("Authorization", "Bearer "+tempToken);
         Response response = invocationBuilder.get();
         
		String value = response.readEntity(String.class);  
		System.out.println(value);  
        if (response.getStatus()!=200 && response.getStatus()!=204) {
       	 System.out.println(response.getStatus());
        }
		response.close();  //关闭连接  
     }  
       
}  