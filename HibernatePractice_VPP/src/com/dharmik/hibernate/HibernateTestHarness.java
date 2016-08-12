package com.dharmik.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.dharmik.domain.Address;
import com.dharmik.domain.Student;
import com.dharmik.domain.Subject;
import com.dharmik.domain.Tutor;

public class HibernateTestHarness 
{
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");

	public static void main(String[] args)
	{		
		//setUpData();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// let's do some queries!
		//-------------list of all tutors, whose students lives in city georgia
		/*List<Tutor> tutors = em.createQuery("select tutor from Tutor tutor join tutor.supervisionGroup as students "
				+ "join students.address as address"
				+ " where address.city=:city")
				.setParameter("city", "Georgia")
				.getResultList();
		
		for(Tutor tutor: tutors){
			System.out.println(tutor.getName());
		}*/
		
		//-----------Named queries -------------------------------
		List<Student> result = em.createNamedQuery("searchByName")
								.setParameter("name", "Marco Fortes")
								.getResultList();
		for(Student student: result){
			System.out.println(student.getName());
		}
		
		tx.commit();
		em.close();
	}
	
	public static void setUpData()
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// Some subjects
		Subject mathematics = new Subject("Mathematics", 2);
		Subject science = new Subject("Science", 2);
		Subject history = new Subject("History", 3);
		em.persist(mathematics);
		em.persist(science);
		em.persist(history);

		// This tutor will be very busy, with lots of students
		Tutor t1 = new Tutor("ABC123", "David Banks", 2939393);
		t1.addSubjectToQialification(mathematics);
		t1.addSubjectToQialification(science);
		
		// This tutor is new and has no students
		// But he will be able to teach science and mathematics
		Tutor t2 = new Tutor("DEF456", "Alan Bridges", 0);
		t2.addSubjectToQialification(mathematics);
		t2.addSubjectToQialification(science);
		
		// This tutor is the only tutor who can teach History
		Tutor t3 = new Tutor("GHI678", "Linda Histroia", 0);
		t3.addSubjectToQialification(history);
		
		em.persist(t1);
		em.persist(t2);
		em.persist(t3);

		// this only works because we are cascading from tutor to student
		//removed embedded objects address as of now
		Address address1 = new Address("1 The Street", "Anytown", "484848");
		Address address2 = new Address("2 Kaths Street", "Georgia", "939393");
		Address address3 = new Address("4 The Avenue", "Georgia", "939393");
		t1.addStudentToSupervisionGroup("Marco Fortes", "1-FOR-2010",address1);
		t1.addStudentToSupervisionGroup("Kath Grainer", "2-GRA-2009",address2);
		t3.addStudentToSupervisionGroup("Sandra Perkins", "3-PER-2009",address3);
		
		tx.commit();
		em.close();
	}

	
}
