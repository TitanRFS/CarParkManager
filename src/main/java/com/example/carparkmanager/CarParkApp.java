package com.example.carparkmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CarParkApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CarPark.fxml"));
        Parent root = loader.load();
        CarParkController controller = loader.getController();
        controller.setCapacityLabel(10);
        controller.setOccupancyLabel();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Park");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}