package com.guideme.pattern.prototype;

public class PrototypePatternCopyConstructorExcercise {

	public static void main(String[] args) {
		Employee john = new Employee("John", new EmployeeAddress("Whitehall St", 123));
		Employee phil = new Employee(john);
		phil.name="Phil";
		System.out.println(john);
		System.out.println(phil);
	}

}

class EmployeeAddress {
	public String streetName;
	public int houseNumber;

	public EmployeeAddress(String streetName, int houseNumber) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
	}
	
	public EmployeeAddress(EmployeeAddress other) {
		streetName = other.streetName;
		houseNumber = other.houseNumber;
	}

	@Override
	public String toString() {
		return "Address [streetName=" + streetName + ", houseNumber=" + houseNumber + "]";
	}
}

class Employee {
	public String name;
	public EmployeeAddress address;

	public Employee(String name, EmployeeAddress address) {
		this.name = name;
		this.address = address;
	}
	
	public Employee(Employee other) {
		name = other.name;
		address = new EmployeeAddress(other.address);
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", address=" + address + "]";
	}
	
}
