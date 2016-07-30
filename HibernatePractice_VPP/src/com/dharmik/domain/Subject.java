package com.dharmik.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private int noOfSemesters;
	
	@ManyToMany
	private Set<Tutor> qualifiedTutors;
	
	Subject(){}

	public Subject(String name, int noOfSemesters) {
		super();
		this.name = name;
		this.noOfSemesters = noOfSemesters;
		this.qualifiedTutors = new HashSet<Tutor>();
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfSemesters() {
		return noOfSemesters;
	}

	public void setNoOfSemesters(int noOfSemesters) {
		this.noOfSemesters = noOfSemesters;
	}

	public Set<Tutor> getQualifiedTutors() {
		return qualifiedTutors;
	}

	public void setQualifiedTutors(Set<Tutor> qualifiedTutors) {
		this.qualifiedTutors = qualifiedTutors;
	}
	
	public void addTutorToSubject(Tutor tutor){
		this.qualifiedTutors.add(tutor);
	}
	
}
