package org.ylxz.rest.client;

import javax.ws.rs.client.Client;  
import javax.ws.rs.client.ClientBuilder;  
import javax.ws.rs.client.Entity;  
import javax.ws.rs.client.WebTarget;  
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.core.Response;  
  
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;  
  
import org.ylxz.rest.bean.User;  
  
   
/** 
 * �û��ͻ��ˣ�����������Դ 
 * 2014-3-18 
 */  
public class UserClient {  
  
    private static String serverUri = "http://localhost:9090/RestDemo/rest";  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        addUser();
        getAllUsers();  
        updateUser();  
        getUserById();  
        getAllUsers();  
        delUser();  
        getAllUsers();  
  
    }  
    /** 
     * ����û� 
     */  
     private static void addUser() {  
         System.out.println("****�����û�006,Susan,21****");  
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
         System.out.println("****ɾ���û�006****");  
         Client client = ClientBuilder.newClient();  
         WebTarget target = client.target(serverUri + "/users/006");  
         Response response = target.request().delete();  
         response.close();  
    }  
       
       
    /** 
     * �޸��û� 
     */  
     private static void updateUser() {  
         System.out.println("****�޸��û�updateUser����age��21�ĳ�33****");  
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
         WebTarget target = client.target(serverUri + "/users/006");  
         Response response = target.request().get();  
         String value = response.readEntity(String.class);  
         System.out.println(value);
         //System.out.println(user.getUserId() + user.getUserName()  +  user.getAge());  
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