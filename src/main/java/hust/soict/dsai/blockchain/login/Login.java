/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hust.soict.dsai.blockchain.login;

import static hust.soict.dsai.blockchain.login.User.getUserInformation;




import java.util.ArrayList;

/**
 *
 * @author 84913
 */
public class Login {
    
    
    public static String login(String username, String password) {
            
            
            User user = new User(username, password);
            ArrayList<User> userList = null;
            userList = getUserInformation();
            for(User u : userList){
                
                if((u.getUsername() == null ? user.getUsername() == null : u.getUsername().equals(user.getUsername())) && (u.getPassword() == null ? user.getPassword() == null : u.getPassword().equals(user.getPassword())) && "Admin".equals(u.getRole())){
                    
                    return "Admin";
                    
                    
                    
                    



                }
                else if((u.getUsername() == null ? user.getUsername() == null : u.getUsername().equals(user.getUsername())) && (u.getPassword() == null ? user.getPassword() == null : u.getPassword().equals(user.getPassword())) && "Manager".equals(u.getRole())){
                    
                    return "Manager";
                    
                }
                
            
            }
        return null;
            
    }
    
    public static String logout(){
        return "Default";
    }
    
}
