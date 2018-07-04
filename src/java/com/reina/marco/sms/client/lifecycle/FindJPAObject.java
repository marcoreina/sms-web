package com.reina.marco.sms.client.lifecycle;

import com.reina.marco.sms.domain.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindJPAObject {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsoleJpqlPU");
        EntityManager em = emf.createEntityManager();
        
        //Step 1 - Start transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        //Step 2 - Execute SQL (Select)
        Person person = em.find(Person.class, 1);
        
        //Step 3 - Finish transaction
        tx.commit();
        
        //Object in detached state
        log.debug("Fetched object: " + person);
    }
}
