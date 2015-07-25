package org.ionnic.app.home.model;

public class Person {

	private int id;

	private String name;

	private String organisation;

	public Person() {
	}

	public Person(String n, String o) {
		this.name = n;
		this.organisation = o;
	}

	@Override
	// Person objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOrganisation() {
		return organisation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	@Override
	public String toString() {
		return String.format("name=%s, organisation=%s", this.name, this.organisation);
	}
}
