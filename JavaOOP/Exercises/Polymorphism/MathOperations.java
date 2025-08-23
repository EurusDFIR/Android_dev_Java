package Exercises.Polymorphism;

import Exercises.Inheritance.BaiTap4_2.*;

public class MathOperations {
    public int multiply(int a, int b) {
        return a * b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public int multiply(int a, int b, int c) {
        return a * b * c;
    }

    Shape[] shapes = {
            new Square("yellow", 5),
            new Circle("Blue", 8)
    };
    {
        for (Shape shape : shapes) {
            shape.calculateArea();
        }
    }
}
