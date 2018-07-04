package com.reina.marco.sms.eis;

import java.util.List;

import com.reina.marco.sms.domain.Person;

public interface PersonDao {

	public List<Person> findAllPersons();
	
	public Person findPersonById(Person person);
	
	public Person findPersonByEmail(Person person);
	
	public void insertPerson(Person person);
	
	public void updatePerson(Person person);
	
	public void deletePerson(Person person);
}
