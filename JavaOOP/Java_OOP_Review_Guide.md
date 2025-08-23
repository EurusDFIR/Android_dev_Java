# HƯỚNG DẪN ÔN TẬP JAVA OOP

## MỤC LỤC

1. [Khái niệm cơ bản OOP](#1-khái-niệm-cơ-bản-oop)
2. [Class và Object](#2-class-và-object)
3. [Encapsulation (Đóng gói)](#3-encapsulation-đóng-gói)
4. [Inheritance (Kế thừa)](#4-inheritance-kế-thừa)
5. [Polymorphism (Đa hình)](#5-polymorphism-đa-hình)
6. [Abstraction (Trừu tượng)](#6-abstraction-trừu-tượng)
7. [Interface](#7-interface)
8. [Bài tập tổng hợp](#8-bài-tập-tổng-hợp)

---

## 1. KHÁI NIỆM CỞ BẢN OOP

### 🎯 **Lý thuyết**

OOP (Object-Oriented Programming) là phương pháp lập trình dựa trên khái niệm **Object** và **Class**.

**4 Tính chất cơ bản:**

- **Encapsulation** (Đóng gói): Che giấu dữ liệu bên trong
- **Inheritance** (Kế thừa): Tái sử dụng code từ class cha
- **Polymorphism** (Đa hình): Một hành động có nhiều cách thực hiện
- **Abstraction** (Trừu tượng): Ẩn đi chi tiết phức tạp

### 📝 **Bài tập 1.1**

```java
// Tạo class đơn giản để hiểu khái niệm Object
public class Student {
    String name;
    int age;

    void study() {
        System.out.println(name + " is studying");
    }
}
```

---

## 2. CLASS VÀ OBJECT

### 🎯 **Lý thuyết**

- **Class**: Khuôn mẫu để tạo object
- **Object**: Thể hiện cụ thể của class
- **Constructor**: Phương thức khởi tạo object

### 📝 **Ví dụ cơ bản**

```java
public class Car {
    // Attributes (thuộc tính)
    private String brand;
    private String color;
    private int speed;

    // Constructor
    public Car(String brand, String color) {
        this.brand = brand;
        this.color = color;
        this.speed = 0;
    }

    // Methods (phương thức)
    public void start() {
        System.out.println(brand + " is starting...");
    }

    public void accelerate(int increment) {
        speed += increment;
        System.out.println("Speed: " + speed + " km/h");
    }
}
```

### 🔥 **Bài tập 2.1**

Tạo class `Book` với:

- Attributes: `title`, `author`, `pages`, `isOpen`
- Constructor nhận `title`, `author`, `pages`
- Methods: `open()`, `close()`, `getInfo()`

### 🔥 **Bài tập 2.2**

Tạo class `BankAccount` với:

- Attributes: `accountNumber`, `balance`, `ownerName`
- Methods: `deposit()`, `withdraw()`, `checkBalance()`

---

## 3. ENCAPSULATION (ĐÓNG GÓI)

### 🎯 **Lý thuyết**

- Sử dụng **private** để ẩn dữ liệu
- Sử dụng **getter/setter** để truy cập dữ liệu
- Bảo vệ dữ liệu khỏi thay đổi không mong muốn

### 📝 **Ví dụ**

```java
public class Person {
    private String name;
    private int age;

    // Getter
    public String getName() {
        return name;
    }

    // Setter với validation
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("Invalid age!");
        }
    }

    public int getAge() {
        return age;
    }
}
```

### 🔥 **Bài tập 3.1**

Tạo class `Rectangle` với:

- Private attributes: `width`, `height`
- Getters/Setters với validation (> 0)
- Methods: `calculateArea()`, `calculatePerimeter()`

### 🔥 **Bài tập 3.2**

Tạo class `Employee` với:

- Private attributes: `id`, `name`, `salary`
- Getters/Setters với validation phù hợp
- Method `displayInfo()`

---

## 4. INHERITANCE (KẾ THỪA)

### 🎯 **Lý thuyết**

- Sử dụng từ khóa **extends**
- Class con kế thừa thuộc tính và phương thức của class cha
- **super()** để gọi constructor của class cha
- **@Override** để ghi đè phương thức

### 📝 **Ví dụ**

```java
// Class cha
public class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + " is eating");
    }

    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

// Class con
public class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);  // Gọi constructor cha
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof!");
    }

    public void wagTail() {
        System.out.println(name + " is wagging tail");
    }
}
```

### 🔥 **Bài tập 4.1**

Tạo hierarchy:

- Class `Vehicle` (brand, year, speed)
- Class `Car` extends Vehicle (doors, fuelType)
- Class `Motorcycle` extends Vehicle (engineSize)

### 🔥 **Bài tập 4.2**

Tạo hierarchy:

- Class `Shape` (color)
- Class `Circle` extends Shape (radius)
- Class `Square` extends Shape (side)
- Mỗi class có method `calculateArea()`

---

## 5. POLYMORPHISM (ĐA HÌNH)

### 🎯 **Lý thuyết**

- **Method Overriding**: Ghi đè phương thức ở class con
- **Method Overloading**: Nhiều phương thức cùng tên, khác tham số
- **Runtime Polymorphism**: Quyết định phương thức nào gọi lúc runtime

### 📝 **Ví dụ Method Overriding**

```java
public class Calculator {
    // Method Overloading
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

// Runtime Polymorphism
Animal[] animals = {
    new Dog("Buddy", 3, "Golden"),
    new Cat("Whiskers", 2, "Persian"),
    new Bird("Tweety", 1, "Canary")
};

for (Animal animal : animals) {
    animal.makeSound();  // Gọi method tương ứng của từng class
}
```

### 🔥 **Bài tập 5.1**

Tạo class `MathOperations` với method overloading cho:

- `multiply(int, int)`
- `multiply(double, double)`
- `multiply(int, int, int)`

### 🔥 **Bài tập 5.2**

Tạo array của `Shape` objects và gọi `calculateArea()` cho từng shape

---

## 6. ABSTRACTION (TRỪU TƯỢNG)

### 🎯 **Lý thuyết**

- **Abstract Class**: Class không thể tạo object trực tiếp
- **Abstract Method**: Method không có implementation
- Class con phải override tất cả abstract methods

### 📝 **Ví dụ**

```java
public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    // Abstract method
    public abstract double calculateArea();

    // Concrete method
    public void displayColor() {
        System.out.println("Color: " + color);
    }
}

public class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
```

### 🔥 **Bài tập 6.1**

Tạo abstract class `Employee` với:

- Abstract method `calculateSalary()`
- Classes con: `FullTimeEmployee`, `PartTimeEmployee`

### 🔥 **Bài tập 6.2**

Tạo abstract class `Game` với:

- Abstract methods: `start()`, `play()`, `end()`
- Classes con: `FootballGame`, `ChessGame`

---

## 7. INTERFACE

### 🎯 **Lý thuyết**

- Interface chỉ chứa abstract methods (Java 8+ có default methods)
- Class implement interface phải override tất cả methods
- Một class có thể implement nhiều interfaces

### 📝 **Ví dụ**

```java
public interface Drawable {
    void draw();
    void resize(int factor);
}

public interface Movable {
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
}

public class Square implements Drawable, Movable {
    private int x, y, size;

    @Override
    public void draw() {
        System.out.println("Drawing square at (" + x + "," + y + ")");
    }

    @Override
    public void resize(int factor) {
        size *= factor;
    }

    @Override
    public void moveUp() {
        y++;
    }

    // Implement other move methods...
}
```

### 🔥 **Bài tập 7.1**

Tạo interfaces:

- `Playable` (play(), pause(), stop())
- `Downloadable` (download(), getFileSize())
- Class `Video` implements cả hai

### 🔥 **Bài tập 7.2**

Tạo interface `Comparable` và implement cho class `Student` để so sánh theo điểm số

---

## 8. BÀI TẬP TỔNG HỢP

### 🎯 **Bài tập 8.1: Hệ thống quản lý thư viện**

Tạo các classes:

1. **Book** (abstract)

   - Attributes: title, author, isbn, available
   - Abstract method: getType()

2. **TextBook** extends Book
3. **Novel** extends Book
4. **Library**
   - ArrayList<Book> books
   - Methods: addBook(), removeBook(), findBook(), borrowBook()

### 🎯 **Bài tập 8.2: Hệ thống ngân hàng**

1. **Account** (abstract)

   - Attributes: accountNumber, balance, owner
   - Abstract method: calculateInterest()

2. **SavingsAccount** extends Account
3. **CheckingAccount** extends Account
4. **Bank**
   - Quản lý danh sách accounts
   - Methods: createAccount(), transfer(), deposit(), withdraw()

### 🎯 **Bài tập 8.3: Game RPG đơn giản**

1. **Character** (abstract)

   - Attributes: name, hp, level
   - Abstract method: attack()

2. **Warrior**, **Mage**, **Archer** extends Character
3. **Interfaces**: Healer, Defender
4. **Game** class để quản lý characters

---

## 🎯 **CHECKLIST ÔN TẬP**

### ✅ **Kiến thức cần nắm vững:**

- [ ] Hiểu 4 tính chất OOP và cách áp dụng
- [ ] Tạo class với constructor, getter/setter
- [ ] Sử dụng inheritance với super(), @Override
- [ ] Phân biệt method overloading vs overriding
- [ ] Tạo và sử dụng abstract class
- [ ] Tạo và implement interface
- [ ] Sử dụng polymorphism trong thực tế

### ✅ **Kỹ năng thực hành:**

- [ ] Thiết kế class hierarchy hợp lý
- [ ] Áp dụng encapsulation để bảo vệ dữ liệu
- [ ] Sử dụng interface để tạo contract
- [ ] Viết code clean, có tính tái sử dụng
- [ ] Debug và fix lỗi OOP

---

## 💡 **TIPS ÔN TẬP HIỆU QUẢ**

1. **Thực hành theo thứ tự**: Class → Inheritance → Polymorphism → Abstract → Interface
2. **Làm bài tập nhỏ trước**, sau đó chuyển sang bài tập tổng hợp
3. **Vẽ UML diagram** để hiểu rõ mối quan hệ giữa các class
4. **Code từng bước**, test từng method một
5. **Refactor code** để cải thiện thiết kế

---

_Happy Coding! 🚀_
