package org.roxfort.enaplo.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private Button buttonSubmit;

    @FXML
    private Button buttonExit;

    public void buttonSubmit_Click() {
        // TODO: jegy be írás adatbázisba ...
    }

    public void buttonExit_Click() {
        Platform.exit();
    }
}
