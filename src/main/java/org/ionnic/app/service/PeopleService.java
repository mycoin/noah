package org.ionnic.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.ionnic.app.model.Person;
import org.springframework.stereotype.Service;

@Service("peopleService")
public class PeopleService implements IPeopleService {

	private Map<Integer, Person> people = new HashMap<Integer, Person>();
	private AtomicInteger idGen = new AtomicInteger();

	public PeopleService() {
		// create some dummy data
		addPerson(new Person("Jane Doe", "Spidertracks"));
		addPerson(new Person("Joshua Teller", "FAA"));
		addPerson(new Person("Wee Teller", "FA"));
		addPerson(new Person("Joshua", "FAA"));
		addPerson(new Person("Joshus.a Teller", "FSAA"));
	}

	@Override
	public int addPerson(Person person) throws IllegalArgumentException {

		if (people.containsValue(person)) {
			throw new IllegalArgumentException("Person " + person + " already exists.");
		}

		int id = idGen.incrementAndGet();
		person.setId(id);
		people.put(id, person);

		return id;
	}

	@Override
	public void deletePerson(int id) throws IllegalArgumentException {
		if (!people.containsKey(id)) {
			throw new IllegalArgumentException("Unable to find person with id " + id);
		}

		people.remove(id);

	}

	@Override
	public List<Person> getPeople() {
		return new ArrayList<Person>(people.values());
	}

	@Override
	public List<Person> getPeople(String name) {

		name = name.toLowerCase();
		List<Person> matchingPeople = new ArrayList<Person>();

		for (Person p : people.values()) {
			if (p.getName().toLowerCase().contains(name)) {
				matchingPeople.add(p);
			}
		}

		return matchingPeople;
	}

	@Override
	public Person getPerson(int id) throws IllegalArgumentException {
		Person p = people.get(id);
		if (p == null) {
			throw new IllegalArgumentException("Could not find person with id:" + id);
		}
		return p;
	}

	@Override
	public void updatePerson(int id, Person person) throws IllegalArgumentException {

		if (!people.containsKey(id)) {
			throw new IllegalArgumentException("Unable to find person with id " + id);
		}

		people.put(id, person);

	}
}
