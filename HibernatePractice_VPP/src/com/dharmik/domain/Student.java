package com.dharmik.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Represents a Student enrolled in the college management
 * system (CMS)
 */
@Entity
public class Student
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    private String enrollmentID;
    private String name;
    
    /*@ManyToOne
    @JoinColumn(name="TUTOR_FK")
    private Tutor supervisorName;*/
    
    /*
     * Required by Hibernate
     */
    public Student()
    {
    	
    }
    
    /**
     * Initialises a student with a particular tutor
     */
    /*public Student(String name, String tutorName)
    {
    	this.name = name;
    	this.supervisorName = supervisorName;
    }*/
    
    /**
     * Initialises a student with no pre set tutor
     */
    public Student(String name,String enrollMentID)
    {
    	this.name = name;
    	this.enrollmentID = enrollMentID;
    	//this.supervisorName = null;
    }
    
    public double calculateGradePointAverage()
    {
    	// some complex business logic!
    	// we won't need this method on the course, BUT it is import
    	// to remember that classes aren't just get/set pairs - we expect
    	// business logic in here as well.
    	return 0;
    }
    
    public String toString()
    {
    	return this.name;
    }
    
    public int getId()
    {
    	return this.id;
    }

	public String getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(String enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public Tutor getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(Tutor supervisorName) {
		this.supervisorName = supervisorName;
	}*/

	public void setId(int id) {
		this.id = id;
	}
}
