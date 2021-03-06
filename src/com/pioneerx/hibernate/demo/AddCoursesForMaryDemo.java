package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.demo.entity.Course;
import com.pioneerx.hibernate.demo.entity.Instructor;
import com.pioneerx.hibernate.demo.entity.InstructorDetail;
import com.pioneerx.hibernate.demo.entity.Review;
import com.pioneerx.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();
			
			// get the student Mary from the db
			int maryId = 2;
			Student student = session.get(Student.class, maryId);
			
			// create more courses
			Course course1 = new Course("How to steel the guit.");
			Course course2 = new Course("Cryptocurrency");
			
			// add student Mary to those courses
			course1.addStudent(student);
			course2.addStudent(student);
			
			// save those courses
			session.save(course1);
			session.save(course2);
			
			// commit transaction
			session.getTransaction().commit();
			
			
		} finally {
			session.close();
			factory.close();
		}

	}

}
