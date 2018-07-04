package com.reina.marco.sms.service;

import java.util.List;

import javax.ejb.Remote;

import com.reina.marco.sms.domain.Person;

@Remote
public interface PersonServiceRemote {

	public List<Person> listPersons();
	
	public Person findPersonById(Person person);
	
	public Person findPersonByEmail(Person person);
	
	public void registerPerson(Person person);
	
	public void modifyPerson(Person person);
	
	public void deletePerson(Person person);
}
