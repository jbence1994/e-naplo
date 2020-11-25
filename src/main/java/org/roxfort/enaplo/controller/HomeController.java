package org.roxfort.enaplo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import org.roxfort.enaplo.model.*;
import org.roxfort.enaplo.repository.*;
import org.roxfort.enaplo.repository.impl.*;

import java.net.URL;
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

    @FXML
    private ComboBox<Subject> comboBoxSubjects;

    @FXML
    private ComboBox<Integer> comboBoxGradeValues;

    private final GradeRepository gradeRepository;
    private final HouseRepository houseRepository;
    private final SubjectRepository subjectRepository;

    public HomeController() {
        gradeRepository = new GradeRepositoryImpl();
        houseRepository = new HouseRepositoryImpl();
        subjectRepository = new SubjectRepositoryImpl();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableViewHouses();
        initializeComboBoxSubjects();
        initializeComboBoxGradeValues();
    }

    private void initializeTableViewHouses() {
        if (houseRepository.getHouses() == null) {
            tableViewHouses.setItems(null);
            return;
        }

        ObservableList<House> houses = FXCollections.observableArrayList();
        houses.addAll(houseRepository.getHouses());

        tableColumnHouseName.setCellValueFactory(house ->
                new SimpleStringProperty(house.getValue().getName()));
        tableViewHouses.setItems(houses);
    }

    private void initializeTableViewStudents(String houseName) {
        if (houseRepository.getStudents(houseName) == null) {
            tableViewStudents.setItems(null);
            return;
        }

        ObservableList<Student> students = FXCollections.observableArrayList();
        students.addAll(houseRepository.getStudents(houseName));

        tableColumnStudentFirstName.setCellValueFactory(student ->
                new SimpleStringProperty(student.getValue().getFirstName()));
        tableColumnStudentLastName.setCellValueFactory(student ->
                new SimpleStringProperty(student.getValue().getLastName()));
        tableViewStudents.setItems(students);
    }

    private void initializeTableViewGrades(Student student) {
        if (gradeRepository.getGrades(student) == null) {
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

    private void initializeComboBoxSubjects() {
        comboBoxSubjects.getItems().addAll(subjectRepository.getSubjects());
    }

    private void initializeComboBoxGradeValues() {
        comboBoxGradeValues.getItems().addAll(gradeRepository.getGradeValues());
    }

    private Student getSelectedStudent() {
        return tableViewStudents.getSelectionModel().getSelectedItem();
    }

    private House getSelectedHouse() {
        return tableViewHouses.getSelectionModel().getSelectedItem();
    }

    private Subject getSelectedSubject() {
        return comboBoxSubjects.getSelectionModel().getSelectedItem();
    }

    private Integer getSelectedGradeValue() {
        return comboBoxGradeValues.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void tableViewHouses_Select() {
        House selectedHouse = getSelectedHouse();
        initializeTableViewStudents(selectedHouse.getName());
    }

    @FXML
    private void tableViewStudents_Select() {
        Student selectedStudent = getSelectedStudent();
        initializeTableViewGrades(selectedStudent);
    }

    @FXML
    private void buttonSubmit_Click() {
        Student selectedStudent = getSelectedStudent();

        if (selectedStudent == null) {
            MessageBox.show(Alert.AlertType.WARNING, "Nincs kijelölt diák!");
            return;
        }

        Subject selectedSubject = getSelectedSubject();

        if (selectedSubject == null) {
            MessageBox.show(Alert.AlertType.WARNING, "Nincs kijelölt tantárgy!");
            return;
        }

        Integer gradeValue = getSelectedGradeValue();

        if (gradeValue == null) {
            MessageBox.show(Alert.AlertType.WARNING, "Nincs kijelölt érdemjegy!");
            return;
        }

        gradeRepository.submitGrade(new Grade(selectedStudent, selectedSubject, gradeValue));

        // TODO: re init table on right ...
    }
}
