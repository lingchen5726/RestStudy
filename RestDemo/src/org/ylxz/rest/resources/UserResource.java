package org.ylxz.rest.resources;

import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  

import javax.ws.rs.Path;  
import javax.ws.rs.Produces;  
import javax.ws.rs.PathParam;  
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;  
import javax.ws.rs.GET;  
import javax.ws.rs.POST;  
import javax.ws.rs.PUT;  
  

import org.ylxz.rest.bean.User;  
  
@Path("/users")  
public class UserResource {  
    private static Map<String,User> userMap = new HashMap<String,User>();//�洢�û�  
     /** 
     * ��ѯ���� 
     * @return 
     */  
    /*--
    @GET  
    @Produces(MediaType.APPLICATION_XML)  
    public List<User> getAllUsers(){       
        List<User> users = new ArrayList<User>();  
        User u1 = new User("001","WayLau","26");  
        User u2 = new User("002","King","23");  
        User u3 = new User("003","Susan","21");  
          
        userMap.put(u1.getUserId(), u1);  
        userMap.put(u2.getUserId(), u2);  
        userMap.put(u3.getUserId(), u3);  
          
        users.addAll( userMap.values() );  
        return users;  
    }  
    */
      
    @GET  
    @Path("/getUserXml")  
    @Produces(MediaType.APPLICATION_XML)  
    public User getUserXml() {  
     User user  = new User();  
     user.setAge("21");  
     user.setUserId("004");  
     user.setUserName("Amand");  
     return user;  
    }  
      
    @GET    
    @Path("/getUserJson")    
    @Produces(MediaType.APPLICATION_JSON)    
    public User getUserJson() {    
     User user  = new User();    
     user.setAge("27");    
     user.setUserId("005");    
     user.setUserName("Fmand");    
     return user;    
    }
    
    
    /** 
     * ���� 
     * @param user 
     */  
    @POST  
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})  
    public void createStudent(User user)  
    {  
        userMap.put(user.getUserId(), user );  
    }  
      
    /** 
     * ɾ�� 
     * @param id 
     */  
    @DELETE  
    @Path("{id}")  
    public void deleteStudent(@PathParam("id")String id){  
        userMap.remove(id);  
    }  
      
    /** 
     * �޸� 
     * @param user 
     */  
    @PUT  
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})  
    public void updateStudent(User user){  
        userMap.put(user.getUserId(), user );  
    }  
   
    /** 
     * ����id��ѯ 
     * @param id 
     * @return 
     */  
    @GET  
    @Path("{id}")  
    //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})  
    @Produces({MediaType.APPLICATION_JSON})
    public User getUserById(@PathParam("id") String id){  
        User u = userMap.get(id);  
        return u;  
    }  
     
    /** 
     * ��ѯ���� 
     * @return 
     */  
    @GET  
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})  
    public List<User> getAllUsers(){       
        List<User> users = new ArrayList<User>();     
        users.addAll( userMap.values() );    
        return users;  
    } 
}  