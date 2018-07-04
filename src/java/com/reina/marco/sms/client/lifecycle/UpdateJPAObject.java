package com.reina.marco.sms.client.lifecycle;

import com.reina.marco.sms.domain.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateJPAObject {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsoleJpqlPU");
        EntityManager em = emf.createEntityManager();
        
        //Step 1 - Start transaction 1
        EntityTransaction tx1 = em.getTransaction();
        tx1.begin();
        
        //Step 2 - Execute SQL (Select)
        Person person = em.find(Person.class, 1);
        
        //Step 3 - Finish transaction 1
        tx1.commit();
        
        //Object in detached state
        log.debug("Fetched object: " + person);
        
        //Step 4 - setValue (newValue)
        person.setFatherName("Nava");
        
        //Step 5 - Start transaction 2
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        //Step 6 - Execute SQL. This is a select, but as it is modified,
        //it will be an update when finishing the transaction.
        //As we already have the object, we just need a merge to resynchronize
        //The object to be merged must have an existing primary key
        em.merge(person);
        
        //Step 7 - Finish transaction 2
        //At the moment of the commit, changes are revised
        //between the object in the database and the object in memory, and the changes are applied
        tx2.commit();
        
        //Object in detached state alread modified
        log.debug("Object modified: " + person);
    }
}
