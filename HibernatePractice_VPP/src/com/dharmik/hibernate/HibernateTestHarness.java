package com.dharmik.hibernate;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;

import com.dharmik.domain.Student;
import com.dharmik.domain.Tutor;


public class HibernateTestHarness 
{

	public static void main(String[] args)
	{
		
		
		try{EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Tutor t1 = new Tutor( "David Banks", 2939393);
		em.persist(t1);
		
		// this only works because we are cascading from tutor to student
		t1.addStudentToSupervisionGroup("Marco Fortes", "1-FOR-2010");
		t1.addStudentToSupervisionGroup("Kath Grainer", "2-GRA-2009");
		
		tx.commit();
		em.close();}catch(Exception e){
			
		}
		
	}

}
