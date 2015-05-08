package org.ionnic.app.home.model;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * 
 * @author ajesler
 * 
 */
public class PeopleWrapper {

	public static PeopleWrapper createNew(List<Person> people) {
		PeopleWrapper pw = new PeopleWrapper();
		pw.setPeople(people);
		return pw;
	}

	private List<Person> people;

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}
}
