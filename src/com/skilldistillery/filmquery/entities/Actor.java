package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Actor {

//	Variables
	private int id;
	private String firstName;
	private String lastName;
	private List<Film> films;

//	Method - No Arg Ctor
	public Actor() {
		super();
	}

//	Method - With Parameters
	public Actor(int id, String firstName, String lastName, List<Film> films) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.films = films;
	}

//	Method - Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	// Method - toString
	@Override
	public String toString() {
		return "Actor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

//	Method - Hashcodes & Equals
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return id == other.id;
	}

}
