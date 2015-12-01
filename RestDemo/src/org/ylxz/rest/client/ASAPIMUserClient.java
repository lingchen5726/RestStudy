package org.ylxz.rest.client;

import javax.ws.rs.client.Client;  
import javax.ws.rs.client.ClientBuilder;  
import javax.ws.rs.client.Entity;  
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;  
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.core.Response;  
  

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;  
  
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
    private static String asApimUsersUri = "http://10.25.31.83:8280/users/1.0.0"; 
    
    /** 
     * @param args 
     */  
    public static void main(String[] args) {
    	getUserById();
    	/*
        addUser();
        addUser();
        getAllUsers();  
        updateUser();  
        getUserById();  
        getAllUsers();  
        delUser();  
        getAllUsers();  
        */
    }  
    /** 
     * 添加用户 
     */  
     private static void addUser() {  
         System.out.println("****增加用户addUser****");  
         User user = new User("006","Susan","21");    
         Client client = ClientBuilder.newClient();  
         WebTarget target = client.target(serverUri + "/users");  
         Response response = target.request().buildPost(Entity.entity(user, MediaType.APPLICATION_XML)).invoke();  
         response.close();  
    }  
       
    /** 
     * 删除用户 
     */  
     private static void delUser() {  
         System.out.println("****删除用户****");  
         Client client = ClientBuilder.newClient();  
         WebTarget target = client.target(serverUri + "/users/006");  
         Response response = target.request().delete();  
         response.close();  
    }  
       
       
    /** 
     * 修改用户 
     */  
     private static void updateUser() {  
         System.out.println("****修改用户updateUser****");  
         User user = new User("006","Susan","33");    
         Client client = ClientBuilder.newClient();  
         WebTarget target = client.target(serverUri + "/users");  
         Response response = target.request().buildPut( Entity.entity(user, MediaType.APPLICATION_XML)).invoke();  
         response.close();  
    }  
    /** 
     * 根据id查询用户 
     */  
     private static void getUserById() {  
         System.out.println("****根据id查询用户****");  
         Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);// 注册json 支持  
         WebTarget target = client.target(asApimUsersUri + "/007");
         //Add header as curl -k -H "Authorization :Bearer 79d8bda8c598f69153859b0eb6a026" http://10.25.31.83:8280/users/1.0.0/007
         
         //Response response = target.request().get();
         /*
         */
         
         Invocation.Builder invocationBuilder = target.request();
         invocationBuilder.header("Authorization", "Bearer cf228e2d9a418d5241569b1447c3a976");
         Response response = invocationBuilder.get();
         
         User user = response.readEntity(User.class);  
         System.out.println(user.getUserId() + user.getUserName()  +  user.getAge());  
         response.close();  
    }  
    /** 
     * 查询所有用户 
     */  
     private static void getAllUsers() {  
         System.out.println("****查询所有getAllUsers****");  
           
         Client client = ClientBuilder.newClient();  
  
         WebTarget target = client.target(serverUri + "/users");  
         Response response = target.request().get();  
		String value = response.readEntity(String.class);  
		System.out.println(value);  
		response.close();  //关闭连接  
     }  
       
}  