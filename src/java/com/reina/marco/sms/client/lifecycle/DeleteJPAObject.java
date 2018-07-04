package com.reina.marco.sms.client.lifecycle;

import com.reina.marco.sms.domain.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteJPAObject {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsoleJpqlPU");
        EntityManager em = emf.createEntityManager();
        
        //Step 1 - Start transaction 1
        EntityTransaction tx1 = em.getTransaction();
        tx1.begin();
        
        //Step 2 - Insert a new person
        Person person = new Person("Marco", "Reina", null, "mreina@mail.com", "11223344");
        em.persist(person);
        
        //Step 3 - Commit/rollback
        tx1.commit();
        
        log.debug("Inserted object: " + person);
        
        //Step 4 - Start transaction 2
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        //Step 5 - Execute SQL (select)
        Person person2 = em.find(Person.class, person.getIdPerson());
        
        //Step 6 - Finish transaction 2
        tx2.commit();
        
        //Object in detached state
        log.debug("Object found: " + person2);
        
        //Step 7 - Start transaction 3
        EntityTransaction tx3 = em.getTransaction();
        tx3.begin();
        
        //Step 8 - Execute SQL (delete)
        em.remove(person2);
        
        //Step 9 - Finish transaction 3
        //At commit, delete happens
        tx3.commit();
        
        //Object in Transient state
        //It's not possible to resynchronize it in another transaction
        //It's only in memmory, and outside this method, it won't exist anymore
        log.debug("Deleted object: " + person2);
    }
}
