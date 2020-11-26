package org.roxfort.enaplo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "value")
    private int value;

    @Column(name = "date_recorded")
    private LocalDateTime dateRecorded;

    public Grade() {

    }

    public Grade(Student student, Subject subject, int value) {
        this.student = student;
        this.subject = subject;
        this.value = value;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getValue() {
        return value;
    }

    public LocalDateTime getDateRecorded() {
        return dateRecorded;
    }
}
