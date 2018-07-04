package com.reina.marco.sms.client.criteria;

import com.reina.marco.sms.domain.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CriteriaAPIClient {

    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsoleJpqlPU");
        EntityManager em = emf.createEntityManager();
        
        CriteriaBuilder cb = null;
        CriteriaQuery<Person> criteriaQuery = null;
        Root<Person> fromPerson = null;
        TypedQuery<Person> query = null;
        List<Person> persons = null;
        Person person = null;
        Integer idPersonParam = 1;
        
        //1) Find all persons
        log.debug("\n1) Find all persons:");
        
        //1. The EntityManager object creates a CriteriaBuilder instance
        cb = em.getCriteriaBuilder();
        
        //2. Creating a CriteriaQuery object
        criteriaQuery = cb.createQuery(Person.class);
        
        //3. Creating the root object of the query
        fromPerson = criteriaQuery.from(Person.class);
        
        //4. Select only the necessary from from
        criteriaQuery.select(fromPerson);
        
        //5. Creating the typeSafed query
        query = em.createQuery(criteriaQuery);
        
        //6. Executing the query
        persons = query.getResultList();
        displayPersons(persons);
        
        //2-a) Query of a Person with id = 1
        //jpql = "select p from Person p where p.idPerson = 1";
        log.debug("\n2-a) Query of a Person with id = 1:");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Person.class);
        fromPerson = criteriaQuery.from(Person.class);
        criteriaQuery.select(fromPerson).where(cb.equal(fromPerson.get("idPerson"), 1));
        person = em.createQuery(criteriaQuery).getSingleResult();
        log.debug(person);
        
        // 2-b) Query of a Person with id = 1
        // jpql = "select p from Person p where p.idPerson = 1";
        log.debug("\n2-b) Query of a Person with id = 1:");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Person.class);
        fromPerson = criteriaQuery.from(Person.class);
        criteriaQuery.select(fromPerson);
        
        //The Predicate class allows to agregate many criterias dinamically
        List<Predicate> criterias = new ArrayList<Predicate>();
        
        ParameterExpression<Integer> param = cb.parameter(Integer.class, "idPerson");
        criterias.add(cb.equal(fromPerson.get("idPerson"), param));
        
        //The criterias are aggregated
        if(criterias.isEmpty()){
            throw new RuntimeException("Without criteriaQuery");
        }
        else if(criterias.size() == 1){
            criteriaQuery.where(criterias.get(0));
        }
        else {
            criteriaQuery.where(cb.and(criterias.toArray(new Predicate[0])));
        }
        
        //Creating query with criterias
        query = em.createQuery(criteriaQuery);
        query.setParameter("idPerson", idPersonParam);
        
        //Executing query
        person = query.getSingleResult();
        log.debug(person);
    }
    
    private static void displayPersons(List<Person> persons){
        for (Person person : persons) {
            log.debug(person);
        }
    }
}
