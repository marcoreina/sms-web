package com.reina.marco.sms.client.cascade;

import com.reina.marco.sms.domain.Person;
import com.reina.marco.sms.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JPACascadePersistence {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsoleJpqlPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        //Step 1 - Create a new object
        //Object in transient state
        Person person = new Person("Hugo", "Sanchez", "Pinto", "hsanchez@mail.com", "33334444");
        
        //Create a user object (it has dependecy with Person)
        User user = new User("hsanchez", "123", person);
        
        //Step 2 - Start transaction
        tx.begin();
        
        //Step 3 - Execute SQL
        //We just need to persist the User object and the Person object will persited by cascade
        em.persist(user);
        
        //Step 4 - Commit/Rollback
        tx.commit();
        
        //Object in detached state
        log.debug("User persisted: " + user);
        log.debug("Person persisted: " + person);
        
        em.close();
    }
}
