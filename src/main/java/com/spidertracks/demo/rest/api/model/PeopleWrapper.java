package com.spidertracks.demo.rest.api.model;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * 
 * @author ajesler
 * 
 */
public class PeopleWrapper {

	private List<Person> people;

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public List<Person> getPeople() {
		return people;
	}

	public static PeopleWrapper createNew(List<Person> people) {
		PeopleWrapper pw = new PeopleWrapper();
		pw.setPeople(people);
		return pw;
	}
}
