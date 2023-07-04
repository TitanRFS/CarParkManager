module com.example.carparkmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carparkmanager to javafx.fxml;
    exports com.example.carparkmanager;
}