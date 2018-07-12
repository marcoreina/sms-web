package com.reina.marco.sms.eis;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reina.marco.sms.domain.Person;

@Stateless
public class PersonDaoImpl implements PersonDao {
	
	@PersistenceContext(unitName = "PersonPU")
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findAllPersons() {
		return em.createNamedQuery("Person.findAll").getResultList();
	}

	@Override
	public Person findPersonById(Person person) {
		return em.find(Person.class, person.getIdPerson());
	}

	@Override
	public Person findPersonByEmail(Person person) {
		Query query = em.createQuery("from Person p where p.email = :email");
		query.setParameter("email", person.getEmail());
		return (Person)query.getSingleResult();
	}

	@Override
	public void insertPerson(Person person) {
		em.persist(person);
	}

	@Override
	public void updatePerson(Person person) {
		em.merge(person);
	}

	@Override
	public void deletePerson(Person person) {
		person = em.getReference(Person.class, person.getIdPerson());
		em.remove(person);
	}

}
