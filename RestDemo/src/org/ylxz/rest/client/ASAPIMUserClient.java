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
 * �û��ͻ��ˣ�����������Դ 
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
     * ����û� 
     */  
     private static void addUser() {  
         System.out.println("****�����û�addUser****");  
         User user = new User("006","Susan","21");    
         Client client = ClientBuilder.newClient();  
         WebTarget target = client.target(serverUri + "/users");  
         Response response = target.request().buildPost(Entity.entity(user, MediaType.APPLICATION_XML)).invoke();  
         response.close();  
    }  
       
    /** 
     * ɾ���û� 
     */  
     private static void delUser() {  
         System.out.println("****ɾ���û�****");  
         Client client = ClientBuilder.newClient();  
         WebTarget target = client.target(serverUri + "/users/006");  
         Response response = target.request().delete();  
         response.close();  
    }  
       
       
    /** 
     * �޸��û� 
     */  
     private static void updateUser() {  
         System.out.println("****�޸��û�updateUser****");  
         User user = new User("006","Susan","33");    
         Client client = ClientBuilder.newClient();  
         WebTarget target = client.target(serverUri + "/users");  
         Response response = target.request().buildPut( Entity.entity(user, MediaType.APPLICATION_XML)).invoke();  
         response.close();  
    }  
    /** 
     * ����id��ѯ�û� 
     */  
     private static void getUserById() {  
         System.out.println("****����id��ѯ�û�****");  
         Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);// ע��json ֧��  
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
     * ��ѯ�����û� 
     */  
     private static void getAllUsers() {  
         System.out.println("****��ѯ����getAllUsers****");  
           
         Client client = ClientBuilder.newClient();  
  
         WebTarget target = client.target(serverUri + "/users");  
         Response response = target.request().get();  
		String value = response.readEntity(String.class);  
		System.out.println(value);  
		response.close();  //�ر�����  
     }  
       
}  