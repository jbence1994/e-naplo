package org.roxfort.enaplo.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.roxfort.enaplo.model.House;
import org.roxfort.enaplo.model.Student;

import java.util.List;

public class StudentRepository {
    private final Session session;

    public StudentRepository() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(House.class)
                .buildSessionFactory();

        session = sessionFactory.getCurrentSession();
    }

    public List<Student> getStudents() {
        try {
            session.beginTransaction();
            return session.createQuery("from Student").list();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            session.close();
        }
    }
}
