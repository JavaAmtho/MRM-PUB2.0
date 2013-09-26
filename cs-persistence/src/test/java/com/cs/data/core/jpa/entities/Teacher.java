package com.cs.data.core.jpa.entities;

import java.io.Serializable;
import java.util.List;

import com.cs.data.api.core.GenericDomain;

public class Teacher implements Serializable, GenericDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private List<Student> students;
	private List<String> phoneNumbers;

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Teacher(String id, List<Student> students) {
		super();
		this.id = id;
		this.students = students;
	}

	public Teacher(String id, List<Student> students,List<String> phoneNumbers) {
		super();
		this.id = id;
		this.students = students;
		this.phoneNumbers=phoneNumbers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
