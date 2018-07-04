package com.reina.marco.sms.client.associations;

import com.reina.marco.sms.domain.Person;
import com.reina.marco.sms.domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JPAAssociationsClient {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsoleJpqlPU");
        EntityManager em = emf.createEntityManager();
        
        List<Person> persons = em.createNamedQuery("Person.findAll").getResultList();
        
        em.close();
        
        printPersons(persons);
    }
    
    private static void printPersons(List<Person> persons){
        //Objects in detached state
        for (Person person : persons) {
            log.debug("Person: " + person);
            
            //Fetch users for each person
            for (User user : person.getUsers()) {
                log.debug("User: " + user);
            }
            break;
        }
    }
}
