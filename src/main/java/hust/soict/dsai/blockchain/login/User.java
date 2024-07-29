/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hust.soict.dsai.blockchain.login;

import hust.soict.dsai.blockchain.engine.FilePathsManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 84913
 */


public class User {
        public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
        public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
        private String username;
        private String password;
        private String role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
        
        public static ArrayList getUserInformation(){
        ArrayList<User> userlist = new ArrayList<>();
        
        BufferedReader br;  
        try {
            br = new BufferedReader(new FileReader(FilePathsManager.getUserFilePath()));
            String line = "";  
          
            
            while ((line = br.readLine()) != null) {
                        String[] values = line.split(",");
                        
                        String username = values[0];
                        String role = values[2];
                        String password = values[1];
                        User user = new User(username, password, role);
                        userlist.add(user);
                        
                    
                    
            }
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        }
        return userlist;
    }
}
