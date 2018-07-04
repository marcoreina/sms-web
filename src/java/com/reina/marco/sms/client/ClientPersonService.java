package com.reina.marco.sms.client;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.reina.marco.sms.domain.Person;
import com.reina.marco.sms.service.PersonServiceRemote;

public class ClientPersonService {
	
	public static void main(String[] args) {
		
		System.out.println("Calling the EJB from the client.");
		
		try {
			Context jndi = new InitialContext();
			PersonServiceRemote personService = (PersonServiceRemote)jndi.lookup("java:global/sms-jee/PersonServiceImpl!com.reina.marco.sms.service.PersonServiceRemote");
			
			List<Person> persons = personService.listPersons();
			
			 for (Person person : persons) {
                System.out.println(person);
            }
			 
            System.out.println("\nEnding the EJB from the client.");
		} catch (NamingException e) {
			e.printStackTrace(System.out);
		}
		
	}
}
