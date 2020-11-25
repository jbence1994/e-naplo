package org.roxfort.enaplo.repository;

import org.roxfort.enaplo.model.House;
import org.roxfort.enaplo.model.Student;

import java.util.List;

public interface HouseRepository {
    List<House> getHouses();

    List<Student> getStudents(String name);
}
