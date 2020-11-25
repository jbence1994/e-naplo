package org.roxfort.enaplo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(getClass()
                .getResource("/fonts/VarelaRound-Regular.ttf")
                .toExternalForm(), 10);

        Parent root = FXMLLoader.load(getClass()
                .getResource("/fxml/home.fxml"));

        primaryStage.setTitle("e-napló | Roxfort");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
