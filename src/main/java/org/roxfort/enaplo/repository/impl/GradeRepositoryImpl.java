package org.roxfort.enaplo.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.roxfort.enaplo.model.Grade;
import org.roxfort.enaplo.model.House;
import org.roxfort.enaplo.model.Student;
import org.roxfort.enaplo.model.Subject;
import org.roxfort.enaplo.repository.GradeRepository;

import java.util.ArrayList;
import java.util.Arrays;
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

    private List<Grade> getGrades() {
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

    @Override
    public List<Grade> getGrades(Student student) {
        for (Grade grade : getGrades()) {
            if (grade.getStudent().getFirstName().equals(student.getFirstName()) &&
                    grade.getStudent().getLastName().equals(student.getLastName()))
                return student.getGrades();
        }

        return null;
    }

    @Override
    public List<Integer> getGradeValues() {
        return new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    }

    @Override
    public void submitGrade(Grade grade) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            // TODO: Mentés adatbázisba ...
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException("Jegy rögzítése sikertelen!");
        } finally {
            session.close();
        }
    }
}
