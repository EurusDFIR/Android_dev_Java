package Exercises.Inheritance.BaiTap4_2;

public class Square extends Shape {
    private double side;

    public Square(String color, double side) {
        super(color);
        this.side = side;

    }

    @Override
    public double calculateArea() {
        return this.side * this.side;
    }
}
