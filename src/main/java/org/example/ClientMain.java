package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerMain.class.getResource("/view/ClientFrom.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}
