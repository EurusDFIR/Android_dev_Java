package Exercises.Inheritance.BaiTap4_1;

public class Motorcycle extends Vehicle {
    private int engineSize;

    public Motorcycle(String brand, int year, double speed, int engineSize) {
        super(brand, year, speed);
        this.engineSize = engineSize;
    }

}
