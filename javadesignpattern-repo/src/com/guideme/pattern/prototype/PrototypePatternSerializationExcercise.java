package com.guideme.pattern.prototype;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

public class PrototypePatternSerializationExcercise {

	public static void main(String[] args) {
		Student s1 = new Student("John", "Dennis");
		Student s2 = SerializationUtils.roundtrip(s1);
		s2.firstName = "Phil";
		s2.lastName = "Coulson";
		System.out.println(s1);
		System.out.println(s2);
	}

}

class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4471601555000406253L;
	public String firstName, lastName;

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}