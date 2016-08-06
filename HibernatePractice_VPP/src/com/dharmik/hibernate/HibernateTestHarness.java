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
	private static SessionFactory sessionFactory = null;

	public static void main(String[] args)
	{
		
		
		try{
			
			
			// create a new tutor, and a student
			/*Tutor newTutor = new Tutor("Adrian Nathan", 3876383);
			
			Student student1 = new Student ("Rebecca Soni", "1-SON-2012");
			Student student2 = new Student ("Zou Kai", "2-KAI-2009");
			Student student3 = new Student ("Chris Hoy", "3-HOY-1997");
			
			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.save(newTutor);
			
			newTutor.addStudentToSupervisionGroup(student1);
			newTutor.addStudentToSupervisionGroup(student2);
			newTutor.addStudentToSupervisionGroup(student3);
			
			//it will be solved when we use Cascade
			student1.setSupervisor(newTutor);
			student2.setSupervisor(newTutor);
			student3.setSupervisor(newTutor);
			
			// test out creating a couple of subjects
			Subject subject1 = new Subject("Math", 3);
			Subject subject2 = new Subject("Science", 6);
			
			session.save(subject1);
			session.save(subject2);
			
			newTutor.addSubjectToQialification(subject1);
			newTutor.addSubjectToQialification(subject2);

			
			Tutor secondTutor = new Tutor("Ben Ainslie", 3883833);
			session.save(secondTutor);
			
			subject2.addTutorToSubject(secondTutor);
			subject1.addTutorToSubject(secondTutor);
*/			
			/* Uncomment this code to query a tutor and print their supervision group 
			Tutor myTutor = (Tutor)session.get(Tutor.class, 1);
			Set<Student> students = myTutor.getSupervisionGroup();
			
			for(Student next: students)
			{
				System.out.println(next);
			}*/
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			Tutor myTutor = em.find(Tutor.class, 1);
			
			System.out.println(myTutor);
			
			Set<Student> students = myTutor.getSupervisionGroup();
			for(Student next: students)
			{
				System.out.println(next);
			}
					
			Student myStudent = em.find(Student.class, 2);
			System.out.println(myStudent);
			
			tx.commit();
			em.close();
			
		}catch(Exception e){
			
		}
		
	}

}
