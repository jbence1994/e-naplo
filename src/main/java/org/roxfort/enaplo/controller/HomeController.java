package org.roxfort.enaplo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.roxfort.enaplo.model.Grade;
import org.roxfort.enaplo.model.House;
import org.roxfort.enaplo.model.Student;
import org.roxfort.enaplo.repository.GradeRepository;
import org.roxfort.enaplo.repository.HouseRepository;
import org.roxfort.enaplo.repository.impl.GradeRepositoryImpl;
import org.roxfort.enaplo.repository.impl.HouseRepositoryImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private TableView<House> tableViewHouses;

    @FXML
    private TableView<Student> tableViewStudents;

    @FXML
    private TableView<Grade> tableViewGrades;

    @FXML
    private TableColumn<House, String> tableColumnHouseName;

    @FXML
    private TableColumn<Student, String> tableColumnStudentFirstName;

    @FXML
    private TableColumn<Student, String> tableColumnStudentLastName;

    @FXML
    private TableColumn<Grade, String> tableColumnGradeSubjectName;

    @FXML
    private TableColumn<Grade, String> tableColumnGradeValue;

    private final HouseRepository houseRepository;
    private final GradeRepository gradeRepository;

    public HomeController() {
        houseRepository = new HouseRepositoryImpl();
        gradeRepository = new GradeRepositoryImpl();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableViewHouses();
    }

    private void initializeTableViewHouses() {
        ObservableList<House> houses = FXCollections.observableArrayList();
        houses.addAll(houseRepository.getHouses());

        tableColumnHouseName.setCellValueFactory(house ->
                new SimpleStringProperty(house.getValue().getName()));
        tableViewHouses.setItems(houses);
    }

    private void initializeTableViewStudents(String houseName) {
        ObservableList<Student> students = FXCollections.observableArrayList();
        students.addAll(houseRepository.getStudents(houseName));

        tableColumnStudentFirstName.setCellValueFactory(student ->
                new SimpleStringProperty(student.getValue().getFirstName()));
        tableColumnStudentLastName.setCellValueFactory(student ->
                new SimpleStringProperty(student.getValue().getLastName()));
        tableViewStudents.setItems(students);
    }

    private void initializeTableViewGrades(Student student) {
        List<Grade> studentGrades = gradeRepository.getGrades(student);

        if (studentGrades == null) {
            tableViewGrades.setItems(null);
            return;
        }

        ObservableList<Grade> grades = FXCollections.observableArrayList();
        grades.addAll(gradeRepository.getGrades(student));
        tableColumnGradeSubjectName.setCellValueFactory(grade ->
                new SimpleStringProperty(grade.getValue().getSubject().getName()));
        tableColumnGradeValue.setCellValueFactory(grade ->
                new SimpleStringProperty(String.valueOf(grade.getValue().getValue())));
        tableViewGrades.setItems(grades);
    }

    @FXML
    private void tableViewHouses_Select() {
        House selectedHouse = tableViewHouses.getSelectionModel().getSelectedItem();
        initializeTableViewStudents(selectedHouse.getName());
    }

    @FXML
    private void tableViewStudents_Select() {
        Student selectedStudent = tableViewStudents.getSelectionModel().getSelectedItem();
        initializeTableViewGrades(selectedStudent);
    }
}
