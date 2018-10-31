package com.pioneerx.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pioneerx.hibernate.demo.entity.Course;
import com.pioneerx.hibernate.demo.entity.Instructor;
import com.pioneerx.hibernate.demo.entity.InstructorDetail;
import com.pioneerx.hibernate.demo.entity.Review;
import com.pioneerx.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

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
			
			// create a course
			Course tempCourse = new Course("Pacman - How to Score One Million Points");
			
			// save the course ... and leverage cascading ALL
			session.save(tempCourse);
			System.out.println("Saving course: " + tempCourse);
			System.out.println("Saving course's reviews: " + tempCourse.getReviews());
			
			// create the students
			Student student1 = new Student("John", "Doe", "john@doe.com");
			Student student2 = new Student("Mary", "Public", "mary@public.com");
			
			// add students to the course
			tempCourse.addStudent(student1);
			tempCourse.addStudent(student2);
			
			// save the students
			System.out.println("Saving studnet " + student1);
			session.save(student1);
			System.out.println("Saving student " + student2);
			session.save(student2);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			session.close();
			factory.close();
		}

	}

}
