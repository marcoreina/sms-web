package com.reina.marco.sms.client.lifecycle;

import com.reina.marco.sms.domain.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateJPAObjectWideSession {

    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsoleJpqlPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        //Step 1 - Start transaction
        tx.begin();
        
        //Step 2 - Execute SQL (select)
        //It can be a find or a merge if we already have the object
        Person person = em.find(Person.class, 1);
        
        log.debug("Object found: " + person);
        
        //Step 3 - setValue (newValue)
        person.setMotherName("Aguirre");
        
        person.setMotherName("Torres");
        
        //Step 4 - Finish transaction
        //Execute the update
        tx.commit();
        
        //Object in detached state
        log.debug("Modified object: " + person);
    }
}
