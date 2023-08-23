package com.example.carparkmanager;

import java.util.ArrayList;
import java.util.List;

public class CarPark {
    public List<Car> registeredCars;
    public List<Car> parkedCars;
    private int capacity;


    public CarPark(int capacity) {

        this.capacity = capacity;
        this.registeredCars = new ArrayList<>();
        this.parkedCars = new ArrayList<>();
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setRegisteredCars(List<Car> registeredCars) {
        this.registeredCars = registeredCars;
    }
    public void setParkedCars(List<Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    public void registerCar(Car car) {
        registeredCars.add(car);
    }

    public boolean isRegistered(Car car) {

        return registeredCars.contains(car);
    }

    public boolean isFull() {

        return parkedCars.size() == capacity;
    }
    public void addParkedCar(Car car) {
        // Add the car to the parkedCars list
        parkedCars.add(car);
    }
    public void parkCar(Car car) {
        parkedCars.add(car);
    }

    public void unparkCar(Car car) {
        parkedCars.remove(car);
    }

    public List<Car> getRegisteredCars() {
        return registeredCars;
    }

    public List<Car> getParkedCars() {
        return parkedCars;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupancy() {
        return parkedCars.size();
    }

    public void removeCar(Car car) {
        registeredCars.remove(car);
    }
}
