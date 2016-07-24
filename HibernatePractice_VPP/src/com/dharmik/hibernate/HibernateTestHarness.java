package com.dharmik.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.dharmik.domain.Student;
import com.dharmik.domain.Tutor;


public class HibernateTestHarness 
{
	private static SessionFactory sessionFactory = null;

	public static void main(String[] args)
	{
		
		// save the student to the database

		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		try{
			// create a new tutor, and a student
			Student myStudent = new Student("Alicia Coutts", "5-COU-2009");
			Tutor newTutor = new Tutor ("DEF456", 939383);
			
			session.save(myStudent);
			session.save(newTutor);
			
			// make the student be supervised by that tutor
			myStudent.setSupervisorName(newTutor);
			
			// print out the supervisor for this tutor
			System.out.println(myStudent.getSupervisorName());
			
			tx.commit();
		}catch(Exception e){
			if(tx!=null)
				//you should throw an exception here
				tx.rollback();
		}
		finally{
			if(session!=null){
				session.close();
			}
		}
		
	}

	public static SessionFactory getSessionFactory()
	{
		if (sessionFactory == null)
		{
			Configuration configuration = new Configuration();
			configuration.configure();
			
			ServiceRegistry serviceRegistry = new 
					ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();        

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return sessionFactory;
	}			
}
