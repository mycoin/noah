package org.ionnic.app.home.service;

import java.util.List;

import org.ionnic.app.home.model.Person;

public interface IPeopleService {
	public int addPerson(Person person);

	public void deletePerson(int id);

	public List<Person> getPeople();

	public List<Person> getPeople(String name);

	public Person getPerson(int id);

	public void updatePerson(int id, Person person);
}
