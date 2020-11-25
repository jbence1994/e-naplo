package org.roxfort.enaplo.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.roxfort.enaplo.model.House;
import org.roxfort.enaplo.model.Student;
import org.roxfort.enaplo.repository.HouseRepository;

import java.util.List;

public class HouseRepositoryImpl implements HouseRepository {

    private final Session session;

    public HouseRepositoryImpl() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(House.class)
                .buildSessionFactory();

        session = sessionFactory.getCurrentSession();
    }


    public List<House> getHouses() {
        try {
            session.beginTransaction();
            return session.createQuery("from House").list();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            session.close();
        }
    }
}
