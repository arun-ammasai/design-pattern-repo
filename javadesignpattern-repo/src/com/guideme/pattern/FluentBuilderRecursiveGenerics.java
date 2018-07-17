package com.guideme.pattern;

public class FluentBuilderRecursiveGenerics {

	public static void main(String[] args) {
		EmployeeBuilder pb = new EmployeeBuilder();
		PersonDTO1 p = pb.byName("Arun").atWork("Architect").build();
		System.out.println(p);
	}
}

class PersonDTO1 {
	public String name, designation;

	public PersonDTO1() {
	}

	public PersonDTO1(String name, String designation) {
		this.name = name;
		this.designation = designation;
	}

	@Override
	public String toString() {
		return String.format("Person { %s - %s}", name, designation);
	}
}

class PersonBuilder1<SELF extends PersonBuilder1<SELF>> {
	PersonDTO1 person = new PersonDTO1();

	public SELF byName(String name) {
		person.name = name;
		return self();
	}

	public PersonDTO1 build() {
		return person;
	}

	public SELF self() {
		return (SELF) this;
	}
}

class EmployeeBuilder extends PersonBuilder1<EmployeeBuilder> {

	public EmployeeBuilder atWork(String designation) {
		person.designation = designation;
		return this;
	}

	@Override
	public EmployeeBuilder self() {
		return this;
	}
}
