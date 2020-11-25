package org.roxfort.enaplo.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.roxfort.enaplo.model.Grade;
import org.roxfort.enaplo.model.House;
import org.roxfort.enaplo.model.Student;
import org.roxfort.enaplo.model.Subject;
import org.roxfort.enaplo.repository.GradeRepository;

import java.util.List;

public class GradeRepositoryImpl implements GradeRepository {
    private final SessionFactory sessionFactory;
    private Session session;

    public GradeRepositoryImpl() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(House.class)
                .addAnnotatedClass(Grade.class)
                .addAnnotatedClass(Subject.class)
                .buildSessionFactory();
    }

    @Override
    public List<Grade> getGrades(Student student) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            return session.createQuery("from Grade").list();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            session.close();
        }
    }
}
