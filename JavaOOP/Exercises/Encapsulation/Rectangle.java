package Exercises.Encapsulation;

public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;

    }

    // getter
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // setter
    public void setWidth(double width) {
        if (this.width >= 0) {
            this.width = width;
        }

    }

    public void setHeight(double height) {
        if (this.height >= 0) {
            this.height = height;
        }
    }

    public double calculateArea() {
        return this.height * this.width;

    }

    public double calculatePerimeter() {
        return 2 * (this.height + this.width);
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(5, 6);
        System.out.println(rectangle.calculateArea());
        System.out.println(rectangle.calculatePerimeter());

    }

}
