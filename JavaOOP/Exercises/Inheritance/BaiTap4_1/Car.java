package Exercises.Inheritance.BaiTap4_1;

public class Car extends Vehicle {
    private int doors;
    private String fuelType;

    public Car(String brand, int year, double speed, int doors, String fuelType) {
        super(brand, year, speed);
        this.doors = doors;
        this.fuelType = fuelType;
    }
}
