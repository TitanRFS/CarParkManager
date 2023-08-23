package com.example.carparkmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;


public class CarParkApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CarPark.fxml"));
        Parent root = loader.load();
        CarParkController controller = loader.getController();
        controller.setCapacityLabel(10);
        controller.setOccupancyLabel();
        Scene scene = new Scene(root);
        URL url = this.getClass().getResource("CarParkDesign.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.getIcons().add(new Image("file:src/main/resources/com/example/carparkmanager/car-park.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Park");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}