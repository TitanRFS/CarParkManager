package com.example.carparkmanager;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.carparkmanager.CarParkManager.registeredCars;

public class CarParkController {
    @FXML
    private TableView<Car> registeredCarsTable;
    @FXML
    private TableColumn<Car, String> licensePlateColumn;
    @FXML
    private TableColumn<Car, String> ownerNameColumn;
    @FXML
    private ComboBox<Car> parkingComboBox;
    @FXML
    private Button parkCarButton;
    @FXML
    private TableView<Car> parkedCarsTable;
    @FXML
    private TableColumn<Car, String> parkedLicensePlateColumn;
    @FXML
    private TableColumn<Car, String> parkedOwnerNameColumn;
    @FXML
    private TableColumn<Car, Button> unparkColumn;
    @FXML
    private TableColumn<Car, Button> removecarColumn;
    @FXML
    private Label occupancyLabel;
    @FXML
    private Label capacityLabel;

    public CarPark carPark;

    public void initialize() {
        // set up car park
        carPark = new CarPark(10);
        /**Car car1 = new Car("TAZ123", "Christos Gianniotis");
        Car car2 = new Car("DEF599", "George Papadopoulos");
        Car car3 = new Car("GHI789", "John Theodoropoulos");
        Car car4 = new Car("JKL101", "Maria Papadopoulou");
        Car car5 = new Car("MNO111", "Kostas Nikolopoulos");
        Car car6 = new Car("PQR222", "Nikos Afentopoulos");
        Car car7 = new Car("STU333", "Panagiotis Moskofidis");
        Car car8 = new Car("VWX444", "Dimitris Papadopoulos");
        Car car9 = new Car("YZA555", "Giannis Antentokounmpo");
        carPark.registerCar(car1);
        carPark.registerCar(car2);
        carPark.registerCar(car3);
        carPark.registerCar(car4);
        carPark.registerCar(car5);
        carPark.registerCar(car6);
        carPark.registerCar(car7);
        carPark.registerCar(car8);
        carPark.registerCar(car9);**/
        // set up capacity label
        // set up registered cars table
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        ownerNameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        registeredCarsTable.setItems(FXCollections.observableArrayList(carPark.getRegisteredCars()));
        // set up parking combo box
        parkingComboBox.setItems(FXCollections.observableArrayList(carPark.getRegisteredCars()));
        parkingComboBox.setCellFactory(column -> new ListCell<>() {
            @Override
            protected void updateItem(Car item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getLicensePlate());
                }
            }
        });
        parkingComboBox.setButtonCell(new ListCell<Car>() {
            @Override
            protected void updateItem(Car item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getLicensePlate());
                }
            }
        });

        // set up parked cars table
        // set up occupancy label
        // set up capacity label
        // set up registered cars table
        // set up parking combo box
        // set up parked cars table
        parkedLicensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        parkedOwnerNameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerName"));

        /**removecarColumn.setCellFactory(column -> {
            return new TableCell<Car, Button>() {
                final Button removecar = new Button("Remove");
                // override the updateItem() method to set the button as the cell's graphic
                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        setGraphic(removecar);
                        setText(null);
                        removecar.setOnAction(event -> {
                            Car car = getTableView().getItems().get(getIndex());
                            carPark.removeCar(car);
                            parkedCarsTable.setItems(FXCollections.observableArrayList(carPark.getRegisteredCars()));
                            parkingComboBox.setDisable(false);
                        });
                    }
                }
            };
        });**/
        unparkColumn.setCellFactory(column -> {
            // create a cell
            // override the updateItem() method to set the button as the cell's graphic
            // return the cell
            return new TableCell<Car, Button>() {
                final Button unparkButton = new Button("Unpark");
                // override the updateItem() method to set the button as the cell's graphic
                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        setGraphic(unparkButton);
                        setText(null);
                        unparkButton.setOnAction(event -> {
                            Car car = getTableView().getItems().get(getIndex());
                            carPark.unparkCar(car);
                            parkedCarsTable.setItems(FXCollections.observableArrayList(carPark.getParkedCars()));
                            parkingComboBox.setDisable(false);
                            setOccupancyLabel();
                        });
                    }
                }
            };
        });
    }
    @FXML
    public void handleSaveButton(ActionEvent event) {
        // Create a file chooser
        // Set the title of the file chooser
        // Add a filter to only show text files
        // Set the initial directory to the current working directory
        // Show the file chooser
        // If the user clicked the Save button, then save the car park data to the selected file
        // If the user clicked the Cancel button, then do nothing
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Car Park Data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        // Set the initial directory to the current working directory
        String currentDirectory = System.getProperty("user.dir");
        fileChooser.setInitialDirectory(new File(currentDirectory));
        // Show the file chooser
        // If the user clicked the Save button, then save the car park data to the selected file
        // If the user clicked the Cancel button, then do nothing
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                List<Car> registeredCars = carPark.getRegisteredCars();
                List<Car> parkedCars = carPark.getParkedCars();

                // Save registered cars
                writer.println("Registered Cars:");
                for (Car car : registeredCars) {
                    writer.println(car.getLicensePlate() + "," + car.getOwnerName());
                }

                // Save parked cars
                writer.println("\nPark Cars:");
                for (Car car : parkedCars) {
                    writer.println(car.getLicensePlate() + "," + car.getOwnerName());
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Car park data saved successfully.");
                alert.showAndWait();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save car park data.");
                alert.showAndWait();
            }
        }
    }
    @FXML
    public void handleLoadButton(ActionEvent event) {
        // Create a file chooser
        // Set the title of the file chooser
        // Add a filter to only show text files
        // Set the initial directory to the current working directory
        // Show the file chooser
        // If the user clicked the Load button, then load the car park data from the selected file
        // If the user clicked the Cancel button, then do nothing
        // Clear the car park
        // Load the registered cars
        // Load the parked cars
        // Update the registered cars table
        // Update the parked cars table
        // Update the parking combo box
        // Update the occupancy label
        // Update the capacity label
        carPark.getParkedCars().clear();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Car Park Data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        String currentDirectory = System.getProperty("user.dir");
        fileChooser.setInitialDirectory(new File(currentDirectory));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                List<Car> registeredCars = new ArrayList<>();
                List<Car> parkedCars = new ArrayList<>();
                String line;
                boolean isReadingParkedCars = false;

                while ((line = reader.readLine()) != null) {
                    if (line.equals("Park Cars:")) {
                        isReadingParkedCars = true;
                        continue;
                    }

                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String licensePlate = parts[0].trim();
                        String ownerName = parts[1].trim();
                        Car car = new Car(licensePlate, ownerName);

                        if (isReadingParkedCars) {
                            parkedCars.add(car);
                        } else {
                            registeredCars.add(car);
                        }
                    }
                }

                carPark.setRegisteredCars(registeredCars);
                carPark.setParkedCars(parkedCars);
                registeredCarsTable.setItems(FXCollections.observableArrayList(registeredCars));
                parkingComboBox.setItems(FXCollections.observableArrayList(registeredCars));
                // Update UI components for parked cars if necessary

                // Create a list to store the cars that need to be added to the car park
                List<Car> carsToAdd = new ArrayList<>();
                for (Car parkedCar : parkedCars) {
                    if (!registeredCars.contains(parkedCar)) {
                        carsToAdd.add(parkedCar);
                    }
                }

                // Add the parked cars to the car park
                for (Car car : carsToAdd) {
                    carPark.addParkedCar(car);
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Car park data loaded successfully.");
                alert.showAndWait();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load car park data.");
                alert.showAndWait();
            }
        // Update UI components for registered cars
        registeredCarsTable.setItems(FXCollections.observableArrayList(carPark.getRegisteredCars()));
        parkingComboBox.setItems(FXCollections.observableArrayList(carPark.getRegisteredCars()));
        // Update UI components for parked cars
        parkedCarsTable.setItems(FXCollections.observableArrayList(carPark.getParkedCars()));
        setOccupancyLabel();

        }
    }

    @FXML
    private void parkCar() {
        setOccupancyLabel();
        // park car
        // getSelectedItem() is used to get the selected item from the combo box
        Car selectedCar = parkingComboBox.getSelectionModel().getSelectedItem();

        // Check if the selected car is not already parked
        if (carPark.getParkedCars().contains(selectedCar)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The car is already parked.");
            alert.showAndWait();
            return;
        }

        // Park the car only if there is space available
        if (!carPark.isFull()) {
            carPark.parkCar(selectedCar);
            // update tables
            // setItems() is used to update the table view
            // observableArrayList() is used to convert the ArrayList to an ObservableList
            // which is required by the setItems() method
            parkedCarsTable.setItems(FXCollections.observableArrayList(carPark.getParkedCars()));
            parkingComboBox.setDisable(carPark.isFull());

            if (carPark.isFull()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "The car park is full.");
                alert.showAndWait();
            }

            setOccupancyLabel();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The car park is full.");
            alert.showAndWait();
        }
    }

    public void setCapacityLabel(int capacity) {

        capacityLabel.setText(Integer.toString(capacity));
    }

    public int setOccupancyLabel() {
        int numParkedCars = carPark.getParkedCars().size();
        occupancyLabel.setText(Integer.toString(numParkedCars));
        return numParkedCars;
    }
    @FXML
    public void generateReport() {
        // generate report
        // get the number of registered cars
        // get the number of parked cars
        // create a message with the number of registered cars and the number of parked cars
        // if the car park is full, add a message that the car park is full
        // create an alert with the message
        // show the alert
        int numRegisteredCars = carPark.getRegisteredCars().size();
        int numParkedCars = carPark.getParkedCars().size();

        String message = "Number of registered cars: " + numRegisteredCars + "\nNumber of parked cars: " + numParkedCars;
        if (numParkedCars == carPark.getCapacity()) {
            message += "\nThe car park is full.";
        } else {
            message += "\nThe car park is not full. Currently " + (carPark.getCapacity() - numParkedCars) + " spaces available.";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }
    @FXML
    public void handleAddCarButton(ActionEvent event) {
        // create dialog to get license plate number
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Car");
        dialog.setHeaderText(null);
        dialog.setContentText("License Plate Number:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String plateNumber = result.get();
            // create dialog to get owner name
            // create car
            // register car
            // update registered cars table
            dialog = new TextInputDialog();
            dialog.setTitle("Add Car");
            dialog.setHeaderText(null);
            dialog.setContentText("Owner Name:");
            if (carPark.getRegisteredCars().stream().anyMatch(car -> car.getLicensePlate().equals(plateNumber))) {
                // Display an error message
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "The car is already registered.");
                alert.showAndWait();
                return;
            }
            result = dialog.showAndWait();
            if (result.isPresent()) {
                String ownerName = result.get();
                Car car = new Car(plateNumber, ownerName);
                carPark.registerCar(car);
                registeredCarsTable.setItems(FXCollections.observableArrayList(carPark.getRegisteredCars()));
                parkingComboBox.setItems(FXCollections.observableArrayList(carPark.getRegisteredCars()));
            }
        }
    }

}
