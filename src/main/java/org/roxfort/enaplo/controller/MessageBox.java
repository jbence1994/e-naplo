package org.roxfort.enaplo.controller;

import javafx.scene.control.Alert;

public abstract class MessageBox {

    public static void show(Alert.AlertType alertType, String headerText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.show();
    }
}
