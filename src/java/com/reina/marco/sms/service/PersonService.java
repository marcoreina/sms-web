package com.reina.marco.sms.service;

import java.util.List;

import javax.ejb.Local;

import com.reina.marco.sms.domain.Person;

@Local
public interface PersonService {

	public List<Person> listPersons();
	
	public Person findPersonById(Person person);
	
	public Person findPersonByEmail(Person person);
	
	public void registerPerson(Person person);
	
	public void modifyPerson(Person person);
	
	public void deletePerson(Person person);
}
