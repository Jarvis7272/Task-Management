package com.employee;

public class Employee {

	private int employee_id;
	private String firstName;
	private String lastName;
	private String gender;
	private String employeeType;
	private String employeeDomain;
	private String email;
	private String password;
	private String mobile;
	private String address;
	private int managerId;


	public int getId() {
		return employee_id;
	}

	public void setId(int employee_id) {
		this.employee_id = employee_id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployeeDomain() {
		return employeeDomain;
	}

	public void setEmployeeDomain(String employeeDomain) {
		this.employeeDomain = employeeDomain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
}
