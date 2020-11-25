package org.roxfort.enaplo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "grade", fetch = FetchType.EAGER)
    public final List<Grade> grades;

    public Subject() {
        grades = new ArrayList<>();
    }
}
