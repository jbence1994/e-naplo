package org.roxfort.enaplo.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.roxfort.enaplo.model.House;
import org.roxfort.enaplo.repository.HouseRepository;
import org.roxfort.enaplo.repository.impl.HouseRepositoryImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private TableView<House> tableViewHouses;

    @FXML
    private TableColumn<House, String> tableColumnHouseName;

    private final HouseRepository houseRepository;

    public HomeController() {
        houseRepository = new HouseRepositoryImpl();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableViewHouses();
    }

    private void initializeTableViewHouses() {
        ObservableList<House> houses = FXCollections.observableArrayList();
        houses.addAll(houseRepository.getHouses());

        tableColumnHouseName.setCellValueFactory(x -> new SimpleStringProperty(x.getValue().getName()));
        tableViewHouses.setItems(houses);
    }
}
