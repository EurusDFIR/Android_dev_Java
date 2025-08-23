// Demo class để test các khái niệm OOP cơ bản
// Tất cả classes được đặt trong 1 file để dễ chạy (không khuyến khích trong thực tế)

// =================== 1. ENCAPSULATION DEMO ===================
class Student {
    private String name;
    private int age;
    private double gpa;

    // Constructor
    public Student(String name, int age, double gpa) {
        this.name = name;
        setAge(age); // Sử dụng setter để validation
        setGpa(gpa);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters với validation
    public void setAge(int age) {
        if (age >= 0 && age <= 100) {
            this.age = age;
        } else {
            System.out.println("Invalid age!");
        }
    }

    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            System.out.println("GPA must be between 0.0 and 4.0!");
        }
    }

    public void displayInfo() {
        System.out.println("Student: " + name + ", Age: " + age + ", GPA: " + gpa);
    }
}

// =================== 2. INHERITANCE DEMO ===================
class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + " is eating");
    }

    public void sleep() {
        System.out.println(name + " is sleeping");
    }

    public void makeSound() {
        System.out.println(name + " makes a sound");
    }
}

class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age); // Gọi constructor của class cha
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof woof!");
    }

    public void wagTail() {
        System.out.println(name + " is wagging tail happily");
    }

    public void displayInfo() {
        System.out.println("Dog: " + name + ", Age: " + age + ", Breed: " + breed);
    }
}

class Cat extends Animal {
    private boolean isIndoor;

    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow meow!");
    }

    public void purr() {
        System.out.println(name + " is purring");
    }

    public void displayInfo() {
        System.out.println("Cat: " + name + ", Age: " + age +
                ", Indoor: " + (isIndoor ? "Yes" : "No"));
    }
}

// =================== 3. POLYMORPHISM DEMO ===================
class Calculator {
    // Method Overloading - cùng tên method, khác tham số
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public String add(String a, String b) {
        return a + b;
    }
}

// =================== 4. ABSTRACTION DEMO ===================
abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    // Abstract method - bắt buộc phải override
    public abstract double calculateArea();

    // Concrete method
    public void displayColor() {
        System.out.println("Color: " + color);
    }
}

class Rectangle extends Shape {
    private double width, height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    public void displayInfo() {
        System.out.println("Rectangle - Width: " + width + ", Height: " + height);
        displayColor();
        System.out.println("Area: " + calculateArea());
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public void displayInfo() {
        System.out.println("Circle - Radius: " + radius);
        displayColor();
        System.out.println("Area: " + String.format("%.2f", calculateArea()));
    }
}

// =================== 5. INTERFACE DEMO ===================
interface Drivable {
    void start();

    void stop();

    void accelerate(int speed);
}

interface Flyable {
    void takeOff();

    void land();

    void fly(int altitude);
}

class Car implements Drivable {
    private String brand;
    private boolean isRunning;
    private int speed;

    public Car(String brand) {
        this.brand = brand;
        this.isRunning = false;
        this.speed = 0;
    }

    @Override
    public void start() {
        isRunning = true;
        System.out.println(brand + " car is starting...");
    }

    @Override
    public void stop() {
        isRunning = false;
        speed = 0;
        System.out.println(brand + " car stopped");
    }

    @Override
    public void accelerate(int newSpeed) {
        if (isRunning) {
            this.speed = newSpeed;
            System.out.println(brand + " is moving at " + speed + " km/h");
        } else {
            System.out.println("Please start the car first!");
        }
    }
}

class Helicopter implements Drivable, Flyable {
    private String model;
    private boolean isRunning;
    private boolean isFlying;
    private int speed;
    private int altitude;

    public Helicopter(String model) {
        this.model = model;
        this.isRunning = false;
        this.isFlying = false;
        this.speed = 0;
        this.altitude = 0;
    }

    // Implement Drivable
    @Override
    public void start() {
        isRunning = true;
        System.out.println(model + " helicopter engine started");
    }

    @Override
    public void stop() {
        if (isFlying) {
            System.out.println("Cannot stop while flying! Land first.");
        } else {
            isRunning = false;
            speed = 0;
            System.out.println(model + " helicopter stopped");
        }
    }

    @Override
    public void accelerate(int newSpeed) {
        if (isRunning) {
            this.speed = newSpeed;
            System.out.println(model + " is moving at " + speed + " km/h");
        } else {
            System.out.println("Please start the helicopter first!");
        }
    }

    // Implement Flyable
    @Override
    public void takeOff() {
        if (isRunning) {
            isFlying = true;
            System.out.println(model + " is taking off...");
        } else {
            System.out.println("Start engine first!");
        }
    }

    @Override
    public void land() {
        if (isFlying) {
            isFlying = false;
            altitude = 0;
            System.out.println(model + " has landed safely");
        } else {
            System.out.println("Helicopter is not flying");
        }
    }

    @Override
    public void fly(int newAltitude) {
        if (isFlying) {
            this.altitude = newAltitude;
            System.out.println(model + " flying at " + altitude + " meters");
        } else {
            System.out.println("Take off first!");
        }
    }
}

// =================== MAIN CLASS ĐỂ DEMO ===================
public class JavaOOPDemo {
    public static void main(String[] args) {
        System.out.println("=== JAVA OOP CONCEPTS DEMO ===\n");

        // 1. ENCAPSULATION DEMO
        System.out.println("1. ENCAPSULATION (Đóng gói):");
        Student student1 = new Student("Alice", 20, 3.8);
        student1.displayInfo();

        student1.setAge(-5); // Invalid age - sẽ không thay đổi
        student1.setGpa(5.0); // Invalid GPA - sẽ không thay đổi
        student1.displayInfo();
        System.out.println();

        // 2. INHERITANCE DEMO
        System.out.println("2. INHERITANCE (Kế thừa):");
        Dog dog = new Dog("Buddy", 3, "Golden Retriever");
        Cat cat = new Cat("Whiskers", 2, true);

        dog.displayInfo();
        dog.eat(); // Method từ class cha
        dog.makeSound(); // Override method
        dog.wagTail(); // Method riêng của Dog

        System.out.println();

        cat.displayInfo();
        cat.eat(); // Method từ class cha
        cat.makeSound(); // Override method
        cat.purr(); // Method riêng của Cat
        System.out.println();

        // 3. POLYMORPHISM DEMO
        System.out.println("3. POLYMORPHISM (Đa hình):");

        // Method Overloading
        Calculator calc = new Calculator();
        System.out.println("add(5, 3) = " + calc.add(5, 3));
        System.out.println("add(5.5, 3.2) = " + calc.add(5.5, 3.2));
        System.out.println("add(1, 2, 3) = " + calc.add(1, 2, 3));
        System.out.println("add(\"Hello\", \" World\") = " + calc.add("Hello", " World"));

        // Runtime Polymorphism
        Animal[] animals = { dog, cat };
        for (Animal animal : animals) {
            animal.makeSound(); // Gọi method tương ứng của từng class
        }
        System.out.println();

        // 4. ABSTRACTION DEMO
        System.out.println("4. ABSTRACTION (Trừu tượng):");
        Rectangle rect = new Rectangle("Red", 5, 3);
        Circle circle = new Circle("Blue", 4);

        rect.displayInfo();
        System.out.println();
        circle.displayInfo();

        Shape[] shapes = { rect, circle };
        System.out.println("\nAll shapes areas:");
        for (Shape shape : shapes) {
            System.out.println("Area: " + String.format("%.2f", shape.calculateArea()));
        }
        System.out.println();

        // 5. INTERFACE DEMO
        System.out.println("5. INTERFACE:");
        Car car1 = new Car("Toyota");
        Helicopter heli = new Helicopter("Apache");

        System.out.println("Car demo:");
        car1.start();
        car1.accelerate(60);
        car1.stop();

        System.out.println("\nHelicopter demo:");
        heli.start();
        heli.takeOff();
        heli.fly(500);
        heli.accelerate(120);
        heli.land();
        heli.stop();

        System.out.println("\nInterface Polymorphism:");
        Drivable[] vehicles = { car1, heli };
        for (Drivable vehicle : vehicles) {
            vehicle.start();
            vehicle.accelerate(50);
            vehicle.stop();
            System.out.println();
        }

        System.out.println("=== END DEMO ===");
    }
}
