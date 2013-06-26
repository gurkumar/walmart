package main.java.input;

import java.util.Date;

public class Employee {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private Address address;
	private Date dateEmployed;
	
	private Employee (Integer id) {
		this.id = id;
	}
	
	private Employee (Integer id, String firstName, String lastName) {
         this (id);
         this.firstName = firstName;
         this.lastName = lastName;
	}
	
	
	private Employee (Integer id, String firstName, String lastName, Address address) {
		this (id, firstName, lastName);
		this.address = address;
	}
	
	private Employee (Integer id, String firstName, String lastName, Address address, Date dateEmployed) {
		this (id, firstName, lastName, address);
		this.dateEmployed = dateEmployed;
	}
	
	public static Employee createEmployee (Integer id, String firstName, String lastName) {
		return new Employee (id, firstName, lastName);
	}
	
	public static Employee createEmployeeWithAddress (Integer id, String firstName, String lastName,
			Address address) {
		return new Employee (id, firstName, lastName, address);
	}
	
	public static Employee createEmployeeWithAddressDateEmployed (Integer id, String firstName, 
			String lastName, Address address, Date dateEmployed) {
		return new Employee (id, firstName, lastName, address, dateEmployed);
	}
	
	public Integer getId () {
		return this.id;
	}
	
	public String getFirstName () {
		return this.firstName;
	}
	
	public String getLastName () {
		return this.lastName;
	}
	
	public Address getAddress () {
		return this.address;
	}
	
	public Date getDateEmployed () {
		return this.dateEmployed;
	}
	
	

}
