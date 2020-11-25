package org.roxfort.enaplo.repository;

import org.roxfort.enaplo.model.Grade;
import org.roxfort.enaplo.model.Student;

import java.util.List;

public interface GradeRepository {
    List<Grade> getGrades(Student student);

    List<Integer> getGradeValues();
}
