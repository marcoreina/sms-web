package com.reina.marco.sms.client.lifecycle;

import com.reina.marco.sms.domain.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersistJPAObject {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsoleJpqlPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        //Step 1 - Create a new object
        //Object in Transient state
        Person person = new Person("Pedro", "Luna", null, "pluna@mail.com", "19292943");
        
        //Step 2 - Start a transaction
        tx.begin();
        
        //Step 3 - Execute SQL
        em.persist(person);
        
        //Step 4 - Commit/rollback
        tx.commit();
        
        //Object in detached state
        log.debug("Persisted object: " + person);
        
        //Close EntityManager
        em.close();
    }
}
