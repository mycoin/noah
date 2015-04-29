package org.ionnic.app.service;

import java.util.List;

import org.ionnic.app.model.Person;

public interface IPeopleService {
	public int addPerson(Person person);
	public void deletePerson(int id);
	public List<Person> getPeople();
	public List<Person> getPeople(String name);
	public Person getPerson(int id);
	public void updatePerson(int id, Person person);
}
