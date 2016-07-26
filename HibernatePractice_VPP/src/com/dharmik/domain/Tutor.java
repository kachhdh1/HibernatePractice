package com.dharmik.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	// if we do not put the annotation of joinColumn,
	// hibernate will default and will make a
	// mapping table TUTOR_STUDENT instead.
	@OneToMany
	@JoinColumn(name = "TUTOR_FK")
	private Set<Student> supervisionGroup;

	public Tutor(String name, int salary) {
		super();
		this.name = name;
		this.salary = salary;
		this.supervisionGroup = new HashSet<Student>();
	}

	private int salary;

	// required by hibernate
	public Tutor() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void addStudentToSupervisionGroup(Student studentToAdd) {
		this.supervisionGroup.add(studentToAdd);
	}

	public Set<Student> getSupervisionGroup() {
		Set<Student> unmodifiable = Collections
				.unmodifiableSet(this.supervisionGroup);
		return unmodifiable;
	}

}
