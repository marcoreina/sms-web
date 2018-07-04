package com.reina.marco.sms.client;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.reina.marco.sms.domain.Person;
import com.reina.marco.sms.service.PersonServiceRemote;

public class ClientPersonServiceWithIP {
	
public static void main(String[] args) {
		
		System.out.println("Calling the EJB from the client.");
		
		try {
			Properties props = new Properties();
			props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            
            props.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
            
			Context jndi = new InitialContext(props);
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
