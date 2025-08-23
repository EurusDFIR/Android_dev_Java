package Exercises.Inheritance.BaiTap4_2;

import java.lang.Math;

public class Circle extends Shape {
    private double radius = 0.0;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * this.radius * this.radius;
    }

}
