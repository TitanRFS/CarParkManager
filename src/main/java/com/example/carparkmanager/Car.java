package com.example.carparkmanager;
import java.util.Objects;

public class Car {

    public String licensePlate;
    public String ownerName;

    public Car(String licensePlate, String ownerName) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwnerName() {
        return ownerName;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Car other = (Car) obj;
        return Objects.equals(licensePlate, other.licensePlate) &&
                Objects.equals(ownerName, other.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate, ownerName);

    }
}
