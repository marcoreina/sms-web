package com.reina.marco.sms.client.jpql;

import com.reina.marco.sms.domain.Person;
import com.reina.marco.sms.domain.User;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientJPQL {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
        String jpql;
        Query query;
        List<Person> persons;
        Person person;
        Iterator iter;
        Object[] tupla;
        List<String> names;
        List<User> users;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsoleJpqlPU");
        EntityManager em = emf.createEntityManager();
        
        //1) Query all persons
        log.debug("\n1) Query all persons:");
        jpql = "select p from Person p";
        persons = em.createQuery(jpql).getResultList();
        displayPersons(persons);
        
        //2) Query person with id = 1
        log.debug("\n2) Query person with id = 1:");;
        jpql = "select p from Person p where p.idPerson = 1";
        person = (Person)em.createQuery(jpql).getSingleResult();
        log.debug(person);
        
        //3) Query a person by name
        log.debug("\n3) Query a person by name:");
        jpql = "select p from Person p where p.name = 'Carlos'";
        persons = em.createQuery(jpql).getResultList();
        displayPersons(persons);
        
        //4) Query of individual data creates an array(tupla) of the object type with 3 columns
        log.debug("\n4) Query of individual data creates an array(tupla) of the object type with 3 columns:");
        jpql = "select p.name as Name, p.fatherName as Father, p.motherName as Mother from Person p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tupla = (Object[])iter.next();
            String name = (String)tupla[0];
            String fatherName = (String)tupla[1];
            String motherName = (String)tupla[2];
            log.debug(name + " " + fatherName + " " + motherName);
        }
        
        //5) Get Person and the id, it'll create an array of type Object with 2 columns
        log.debug("\n5) Get Person and the id, it'll create an array of type Object with 2 columns:");
        jpql = "select p, p.idPerson from Person p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tupla = (Object[])iter.next();
            person = (Person)tupla[0];
            int idPerson = (Integer)tupla[1];
            log.debug("Person: " + person);
            log.debug("IdPerson: " + idPerson);
        }
        
        //6) Query all Persons
        log.debug("\n6) Query all Persons:");
        jpql = "select new com.reina.marco.sms.domain.Person(p.idPerson) from Person p";
        persons = em.createQuery(jpql).getResultList();
        displayPersons(persons);
        
        //7) Return the maximum and the minimum of idPerson (scalar results)
        log.debug("\n7) Return the maximum and the minimum of idPerson (scalar results):");
        jpql = "select min(p.idPerson) as MinId, max(p.idPerson) as MaxId, count(p.idPerson) as Counter from Person p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tupla = (Object[])iter.next();
            Integer idMin = (Integer)tupla[0];
            Integer idMax = (Integer)tupla[1];
            Long count = (Long)tupla[2];
            log.debug("idMin:" + idMin + ", idMax:" + idMax + ", count:" + count);
        }
        
        //8) Count persons' names which are distinct
        log.debug("\n8) Count persons' names which are distinct:");
        jpql = "select count(distinct p.name) from Person p";
        Long count = (Long)em.createQuery(jpql).getSingleResult();
        log.debug("no. persons with distinct names:" + count);
        
        //9) Concatenate the name and the fatherName
        log.debug("\n9) Concatenate the name and the fatherName:");
        jpql = "select concat(p.name, ' ', p.fatherName) as Name from Person p";
        names = em.createQuery(jpql).getResultList();
        for (String fullName : names) {
            log.debug(fullName);
        }
        
        //10) Get Person with id passed by parameter
        log.debug("\n10) Get Person with id passed by parameter:");
        int idPerson = 1;
        jpql = "select p from Person p where p.idPerson = :id";
        query = em.createQuery(jpql);
        query.setParameter("id", idPerson);
        person = (Person)query.getSingleResult();
        log.debug(person);
        
        //11) Get persons which name has the letter 'a', case insensitive
        log.debug("\n11) Get persons which name has the letter 'a', case insensitive:");
        String letter = "%a%";
        jpql = "select p from Person p where upper(p.name) like upper(:param1)";
        query = em.createQuery(jpql);
        query.setParameter("param1", letter);
        persons = query.getResultList();
        displayPersons(persons);
        
        //12) Use of between
        log.debug("\n12) Use of between:");
        jpql = "select p from Person p where p.idPerson between 1 and 3";
        persons = em.createQuery(jpql).getResultList();
        displayPersons(persons);
        
        //13) Use of order by
        log.debug("\n13) Use of order by:");
        jpql = "select p from Person p where p.idPerson > 3 order by p.name desc, p.fatherName desc";
        persons = em.createQuery(jpql).getResultList();
        displayPersons(persons);
        
        //14) Use of a subquery
        log.debug("\n14) Use of a subquery:");
        jpql = "select p from Person p where p.idPerson in (select min(p2.idPerson) from Person p2)";
        persons = em.createQuery(jpql).getResultList();
        displayPersons(persons);
        
        //15) Use of join with lazy loading
        log.debug("\n15) Use of join with lazy loading:");
        jpql = "select u from User u join u.person p";
        users = em.createQuery(jpql).getResultList();
        displayUsers(users);
        
        //16) Use of left join with eager loading
        log.debug("\n16) Use of left join with eager loading:");
        jpql = "select u from User u left join fetch u.person";
        users = em.createQuery(jpql).getResultList();
        displayUsers(users);
    }
    
    private static void displayPersons(List<Person> persons){
        for (Person person : persons) {
            log.debug(person);
        }
    }
    
    private static void displayUsers(List<User> users){
        for (User user : users) {
            log.debug(user);
        }
    }
}
