package com.dharmik.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique=true, nullable=false)
	private String staffId;
	
	private String name;

	@OneToMany(mappedBy="supervisor",cascade=CascadeType.PERSIST)
	private Set<Student> supervisionGroup;
	
	@ManyToMany(mappedBy="qualifiedTutors")
	private Set<Subject> subjectQualifiedToTeach;
	

	public Tutor(String staffId,String name, int salary) {
		super();
		this.name = name;
		this.staffId = staffId;
		this.salary = salary;
		this.supervisionGroup = new HashSet<Student>();
		this.subjectQualifiedToTeach = new HashSet<Subject>();
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

	public void addStudentToSupervisionGroup(String name, String enrollmentID, Address address) {
		Student student = new Student(name,enrollmentID);
		student.allocateAddress(address);
		this.supervisionGroup.add(student);
		//since this is a bidirectional relationship,
		//we need to maintain both the relationship, else the tutor_fk will be null
		student.setSupervisor(this);
	}

	public Set<Student> getSupervisionGroup() {
		Set<Student> unmodifiable = Collections
				.unmodifiableSet(this.supervisionGroup);
		return unmodifiable;
	}

	public Set<Subject> getSubjectQualifiedToTeach() {
		return subjectQualifiedToTeach;
	}

	public void setSubjectQualifiedToTeach(Set<Subject> subjectQualifiedToTeach) {
		this.subjectQualifiedToTeach = subjectQualifiedToTeach;
	}
	
	public void addSubjectToQialification(Subject subject){
		this.subjectQualifiedToTeach.add(subject);
		subject.addTutorToSubject(this);
	}

}
