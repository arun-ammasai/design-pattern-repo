package com.guideme.pattern.prototype;

import java.util.Arrays;

public class PrototypePatternClonableExcercise {

	public static void main(String[] args) throws Exception {
		Person john = new Person(new String[] { "John", "Dennis" }, new Address("Whithall St", 451));

		Person phil = (Person) john.clone();
		phil.names[0] = "Phil";
		phil.address.houseNumber = 547;

		System.out.println(john);
		System.out.println(phil);
	}
}

class Address implements Cloneable {
	public String streetName;
	public int houseNumber;

	public Address(String streetName, int houseNumber) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
	}

	@Override
	public String toString() {
		return "Address [streetName=" + streetName + ", houseNumber=" + houseNumber + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Address(streetName, houseNumber);
	}

}

class Person implements Cloneable {
	public String[] names;
	public Address address;

	public Person(String[] names, Address address) {
		this.names = names;
		this.address = address;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Person(names.clone(), (Address) address.clone());
	}

	@Override
	public String toString() {
		return "Person [names=" + Arrays.toString(names) + ", address=" + address + "]";
	}

}