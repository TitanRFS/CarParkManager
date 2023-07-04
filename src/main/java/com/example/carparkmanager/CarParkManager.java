package com.example.carparkmanager;

import java.util.ArrayList;
import java.util.List;

public class CarParkManager {

    protected static List<Car> registeredCars;
    private List<Car> parkedCars;

    public CarParkManager() {
        registeredCars = new ArrayList<>();
        parkedCars = new ArrayList<>();
    }

    public static void addCar(String licensePlate, String ownerName) {
        Car newCar = new Car(licensePlate, ownerName);
        registeredCars.add(newCar);
    }
}





