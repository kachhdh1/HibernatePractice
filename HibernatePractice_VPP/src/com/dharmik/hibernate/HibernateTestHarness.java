package com.dharmik.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.dharmik.domain.Student;


public class HibernateTestHarness 
{
	private static SessionFactory sessionFactory = null;

	public static void main(String[] args)
	{
		Student testStudent = new Student("Jessica Ennis", "Toni Minichiello");
		
		// save the student to the database

		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		try{
			session.save(testStudent);
			
			System.out.println("The student now has an id of " + testStudent.getId());
			
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
