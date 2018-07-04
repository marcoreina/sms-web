package com.reina.marco.sms.client;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.reina.marco.sms.domain.User;
import com.reina.marco.sms.service.UserServiceRemote;

public class ClientUserService {
	
	public static void main(String[] args) {
		
		System.out.println("Calling the EJB from the client.");
		
		try {
			Context jndi = new InitialContext();
			UserServiceRemote userService = (UserServiceRemote)jndi.lookup("java:global/sms-jee/UserServiceImpl!com.reina.marco.sms.service.UserServiceRemote");
			
			List<User> users = userService.listUsers();
			
			for (User user : users) {
                            System.out.println(user);
                        }
			 
                        System.out.println("\nEnding the EJB from the client.");
		} catch (NamingException e) {
			e.printStackTrace(System.out);
		}
		
	}
}
