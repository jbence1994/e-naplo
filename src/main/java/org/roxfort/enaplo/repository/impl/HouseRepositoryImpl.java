package org.roxfort.enaplo.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.roxfort.enaplo.model.Grade;
import org.roxfort.enaplo.model.House;
import org.roxfort.enaplo.model.Student;
import org.roxfort.enaplo.model.Subject;
import org.roxfort.enaplo.repository.HouseRepository;

import java.util.List;

public class HouseRepositoryImpl implements HouseRepository {
    private final SessionFactory sessionFactory;
    private Session session;

    public HouseRepositoryImpl() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(House.class)
                .addAnnotatedClass(Grade.class)
                .addAnnotatedClass(Subject.class)
                .buildSessionFactory();
    }

    public List<House> getHouses() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            return session.createQuery("from House").list();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> getStudents(String name) {
        for (House house : getHouses()) {
            if (house.getName().equals(name))
                return house.getStudents();
        }

        throw new RuntimeException("Ismeretlen ház");
    }
}
