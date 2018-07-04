package com.reina.marco.sms.client.transactions;

import com.reina.marco.sms.domain.Person;
import com.reina.marco.sms.service.PersonServiceRemote;
import javax.naming.Context;
import javax.naming.InitialContext;

public class TransactionsManagementClient {
    
    public static void main(String[] args) throws Exception{
        
        Context jndi = new InitialContext();
        PersonServiceRemote personService = (PersonServiceRemote)jndi.lookup("java:global/sms-jee/PersonServiceImpl!com.reina.marco.sms.service.PersonServiceRemote");
        System.out.println("Starting testing of Transaction Management.");
        
        //Find a person
        Person person = personService.findPersonById(new Person(1));
        
        //Modify person
//        person.setMotherName("Change with error................................");
        person.setMotherName("Change without error");
        
        personService.modifyPerson(person);
        System.out.println("Object modified: " + person);
    }
}
