// ===========================================
// BÀI TẬP THỰC HÀNH JAVA OOP - CODE TEMPLATES
// ===========================================
// File này chứa templates cho các bài tập
// Mỗi class nên được tạo trong file riêng trong thực tế

// =================== BÀI TẬP 2.1 ===================
// TODO: Tạo file Book.java với nội dung:
/*
 * public class Book {
 * /*
 * public class Book {
 * private String title;
 * private String author;
 * private int pages;
 * private boolean isOpen;
 * 
 * public Book(String title, String author, int pages) {
 * this.title = title;
 * this.author = author;
 * this.pages = pages;
 * this.isOpen = false;
 * }
 * 
 * public void open() {
 * if (!isOpen) {
 * isOpen = true;
 * System.out.println(title + " is now open");
 * }
 * }
 * 
 * public void close() {
 * if (isOpen) {
 * isOpen = false;
 * System.out.println(title + " is now closed");
 * }
 * }
 * 
 * public void getInfo() {
 * System.out.println("Title: " + title);
 * System.out.println("Author: " + author);
 * System.out.println("Pages: " + pages);
 * System.out.println("Status: " + (isOpen ? "Open" : "Closed"));
 * }
 * }
 */

// =================== BÀI TẬP 2.2 ===================
// TODO: Tạo file BankAccount.java với nội dung:
/*
 * class BankAccount {
 * private String accountNumber;
 * private double balance;
 * private String ownerName;
 * 
 * public BankAccount(String accountNumber, String ownerName) {
 * this.accountNumber = accountNumber;
 * this.ownerName = ownerName;
 * this.balance = 0.0;
 * }
 * 
 * public void deposit(double amount) {
 * if (amount > 0) {
 * balance += amount;
 * System.out.println("Deposited: $" + amount + ". New balance: $" + balance);
 * } else {
 * System.out.println("Invalid deposit amount!");
 * }
 * }
 * 
 * public boolean withdraw(double amount) {
 * if (amount > 0 && amount <= balance) {
 * balance -= amount;
 * System.out.println("Withdrawn: $" + amount + ". New balance: $" + balance);
 * return true;
 * } else {
 * System.out.println("Insufficient funds or invalid amount!");
 * return false;
 * }
 * }
 * 
 * public void checkBalance() {
 * System.out.println("Account: " + accountNumber +
 * ", Owner: " + ownerName +
 * ", Balance: $" + balance);
 * }
 * }
 */

// =================== BÀI TẬP 3.1 ===================
// TODO: Tạo file Rectangle.java với nội dung:
/*
 * class Rectangle {
 * private double width;
 * private double height;
 * 
 * public Rectangle(double width, double height) {
 * setWidth(width);
 * setHeight(height);
 * }
 * 
 * public void setWidth(double width) {
 * if (width > 0) {
 * this.width = width;
 * } else {
 * System.out.println("Width must be positive!");
 * }
 * }
 * 
 * public void setHeight(double height) {
 * if (height > 0) {
 * this.height = height;
 * } else {
 * System.out.println("Height must be positive!");
 * }
 * }
 * 
 * public double getWidth() {
 * return width;
 * }
 * 
 * public double getHeight() {
 * return height;
 * }
 * 
 * public double calculateArea() {
 * return width * height;
 * }
 * 
 * public double calculatePerimeter() {
 * return 2 * (width + height);
 * }
 * }
 */

// =================== BÀI TẬP 4.1 ===================
// TODO: Tạo file Vehicle.java với nội dung:
/*
 * class Vehicle {
 * protected String brand;
 * protected int year;
 * protected int speed;
 * 
 * public Vehicle(String brand, int year) {
 * this.brand = brand;
 * this.year = year;
 * this.speed = 0;
 * }
 * 
 * public void start() {
 * System.out.println(brand + " is starting...");
 * speed = 0;
 * }
 * 
 * public void accelerate(int increment) {
 * speed += increment;
 * System.out.println(brand + " speed: " + speed + " km/h");
 * }
 * 
 * public void displayInfo() {
 * System.out.println("Brand: " + brand + ", Year: " + year + ", Speed: " +
 * speed);
 * }
 * }
 */

// TODO: Tạo file Car.java với nội dung:
/*
 * class Car extends Vehicle {
 * private int doors;
 * private String fuelType;
 * 
 * public Car(String brand, int year, int doors, String fuelType) {
 * super(brand, year);
 * this.doors = doors;
 * this.fuelType = fuelType;
 * }
 * 
 * @Override
 * public void start() {
 * System.out.println("Car " + brand + " engine is starting...");
 * speed = 0;
 * }
 * 
 * public void honk() {
 * System.out.println(brand + " says: Beep beep!");
 * }
 * 
 * @Override
 * public void displayInfo() {
 * super.displayInfo();
 * System.out.println("Doors: " + doors + ", Fuel: " + fuelType);
 * }
 * }
 */

// TODO: Tạo file Motorcycle.java với nội dung:
/*
 * class Motorcycle extends Vehicle {
 * private int engineSize;
 * 
 * public Motorcycle(String brand, int year, int engineSize) {
 * super(brand, year);
 * this.engineSize = engineSize;
 * }
 * 
 * @Override
 * public void start() {
 * System.out.println("Motorcycle " + brand + " is revving up...");
 * speed = 0;
 * }
 * 
 * public void wheelie() {
 * if (speed > 30) {
 * System.out.println(brand + " is doing a wheelie!");
 * } else {
 * System.out.println("Need more speed for wheelie!");
 * }
 * }
 * 
 * @Override
 * public void displayInfo() {
 * super.displayInfo();
 * System.out.println("Engine size: " + engineSize + "cc");
 * }
 * }
 */

// =================== BÀI TẬP 5.1 ===================
// TODO: Tạo file MathOperations.java với nội dung:
/*
 * class MathOperations {
 * // Method Overloading examples
 * public int multiply(int a, int b) {
 * return a * b;
 * }
 * 
 * public double multiply(double a, double b) {
 * return a * b;
 * }
 * 
 * public int multiply(int a, int b, int c) {
 * return a * b * c;
 * }
 * 
 * public double multiply(double a, double b, double c) {
 * return a * b * c;
 * }
 * 
 * // Additional overloaded methods
 * public String multiply(String str, int times) {
 * StringBuilder result = new StringBuilder();
 * for (int i = 0; i < times; i++) {
 * result.append(str);
 * }
 * return result.toString();
 * }
 * }
 */

// =================== BÀI TẬP 6.1 ===================
// TODO: Tạo file Employee.java với nội dung:
/*
 * abstract class Employee {
 * protected String name;
 * protected int id;
 * 
 * public Employee(String name, int id) {
 * this.name = name;
 * this.id = id;
 * }
 * 
 * public abstract double calculateSalary();
 * 
 * public void displayInfo() {
 * System.out.println("ID: " + id + ", Name: " + name);
 * System.out.println("Salary: $" + calculateSalary());
 * }
 * 
 * public String getName() {
 * return name;
 * }
 * 
 * public int getId() {
 * return id;
 * }
 * }
 */

// TODO: Tạo file FullTimeEmployee.java với nội dung:
/*
 * class FullTimeEmployee extends Employee {
 * private double monthlySalary;
 * 
 * public FullTimeEmployee(String name, int id, double monthlySalary) {
 * super(name, id);
 * this.monthlySalary = monthlySalary;
 * }
 * 
 * @Override
 * public double calculateSalary() {
 * return monthlySalary;
 * }
 * 
 * @Override
 * public void displayInfo() {
 * super.displayInfo();
 * System.out.println("Employment Type: Full-time");
 * }
 * }
 */

// TODO: Tạo file PartTimeEmployee.java với nội dung:
/*
 * class PartTimeEmployee extends Employee {
 * private double hourlyRate;
 * private int hoursWorked;
 * 
 * public PartTimeEmployee(String name, int id, double hourlyRate, int
 * hoursWorked) {
 * super(name, id);
 * this.hourlyRate = hourlyRate;
 * this.hoursWorked = hoursWorked;
 * }
 * 
 * @Override
 * public double calculateSalary() {
 * return hourlyRate * hoursWorked;
 * }
 * 
 * public void setHoursWorked(int hours) {
 * this.hoursWorked = hours;
 * }
 * 
 * @Override
 * public void displayInfo() {
 * super.displayInfo();
 * System.out.println("Employment Type: Part-time");
 * System.out.println("Hours worked: " + hoursWorked + ", Rate: $" + hourlyRate
 * + "/hour");
 * }
 * }
 */

// =================== BÀI TẬP 7.1 ===================
// TODO: Tạo file Playable.java với nội dung:
/*
 * interface Playable {
 * void play();
 * void pause();
 * void stop();
 * boolean isPlaying();
 * }
 */

// TODO: Tạo file Downloadable.java với nội dung:
/*
 * interface Downloadable {
 * void download();
 * long getFileSize();
 * String getDownloadUrl();
 * }
 */

// TODO: Tạo file Video.java với nội dung:
/*
 * class Video implements Playable, Downloadable {
 * private String title;
 * private long fileSize;
 * private String downloadUrl;
 * private boolean isPlaying;
 * private boolean isPaused;
 * private boolean isDownloaded;
 * 
 * public Video(String title, long fileSize, String downloadUrl) {
 * this.title = title;
 * this.fileSize = fileSize;
 * this.downloadUrl = downloadUrl;
 * this.isPlaying = false;
 * this.isPaused = false;
 * this.isDownloaded = false;
 * }
 * 
 * @Override
 * public void play() {
 * if (!isDownloaded) {
 * System.out.println("Please download the video first!");
 * return;
 * }
 * 
 * if (!isPlaying) {
 * isPlaying = true;
 * isPaused = false;
 * System.out.println("Playing: " + title);
 * } else {
 * System.out.println(title + " is already playing");
 * }
 * }
 * 
 * @Override
 * public void pause() {
 * if (isPlaying && !isPaused) {
 * isPaused = true;
 * System.out.println("Paused: " + title);
 * } else {
 * System.out.println("Video is not playing or already paused");
 * }
 * }
 * 
 * @Override
 * public void stop() {
 * if (isPlaying) {
 * isPlaying = false;
 * isPaused = false;
 * System.out.println("Stopped: " + title);
 * } else {
 * System.out.println("Video is not playing");
 * }
 * }
 * 
 * @Override
 * public boolean isPlaying() {
 * return isPlaying && !isPaused;
 * }
 * 
 * @Override
 * public void download() {
 * if (!isDownloaded) {
 * System.out.println("Downloading " + title + "...");
 * System.out.println("Download completed. Size: " + (fileSize / 1024 / 1024) +
 * " MB");
 * isDownloaded = true;
 * } else {
 * System.out.println(title + " is already downloaded");
 * }
 * }
 * 
 * @Override
 * public long getFileSize() {
 * return fileSize;
 * }
 * 
 * @Override
 * public String getDownloadUrl() {
 * return downloadUrl;
 * }
 * 
 * public String getTitle() {
 * return title;
 * }
 * }
 */

// ===========================================
// HƯỚNG DẪN SỬ DỤNG FILE NÀY
// ===========================================

/*
 * Cách sử dụng file template này:
 * 
 * // 1. Copy từng phần code trong comment /*
 */
// 2. Tạo file.java riêng cho mỗi class 3. Paste code vào file tương ứng 4.
// Compile và test từng
// class

// Ví dụ:-
// Copy code
// trong comment
// đầu tiên→
// tạo file Book.java-
// Copy code
// trong comment
// thứ hai→
// tạo file BankAccount.java-
// Và tiếp tục...

// Lưu ý:
// Trong Java, mỗi

// public class phải
// ở trong
// file riêng
// có tên
// trùng với tên class.

// THỨ TỰ
// HỌC TẬP
// ĐỀ XUẤT:1.
// Làm Book
// và BankAccount

// trước (cơ bản)
// 2.

// Làm Rectangle (Encapsulation)
// 3. Làm Vehicle hierarchy (Inheritance)
// 4. Làm Employee hierarchy (Abstract)
// 5. Làm Video system (Interface)
// 6. Cuối cùng làm Library

// system (tổng hợp)

// */

// // ===========================================
// // MAIN CLASS ĐỂ TEST TẤT CẢ
// // ===========================================

// public class OOPTestMain {
// public static void main(String[] args) {
// System.out.println("=== JAVA OOP PRACTICE TESTS ===");
// System.out.println("Uncomment và test từng phần một:");
// System.out.println("1. Book class");
// System.out.println("2. BankAccount class");
// System.out.println("3. Rectangle class");
// System.out.println("4. Vehicle hierarchy");
// System.out.println("5. Employee hierarchy");
// System.out.println("6. Video interfaces");
// System.out.println("7. Library system");

// TODO: Uncomment để test từng phần
/*
 * // Test Book
 * Book book1 = new Book("Java Programming", "Oracle", 500);
 * book1.getInfo();
 * book1.open();
 * book1.close();
 * 
 * // Test BankAccount
 * BankAccount account = new BankAccount("123456", "John Doe");
 * account.deposit(1000);
 * account.withdraw(200);
 * account.checkBalance();
 * 
 * // Test Rectangle
 * Rectangle rect = new Rectangle(5, 3);
 * System.out.println("Area: " + rect.calculateArea());
 * System.out.println("Perimeter: " + rect.calculatePerimeter());
 * 
 * // Test Vehicle hierarchy
 * Vehicle[] vehicles = {
 * new Car("Toyota", 2020, 4, "Gasoline"),
 * new Motorcycle("Honda", 2019, 250)
 * };
 * 
 * for (Vehicle v : vehicles) {
 * v.start();
 * v.accelerate(50);
 * v.displayInfo();
 * }
 * 
 * // Test Employee hierarchy
 * Employee[] employees = {
 * new FullTimeEmployee("Alice", 1, 5000),
 * new PartTimeEmployee("Bob", 2, 20, 80)
 * };
 * 
 * for (Employee emp : employees) {
 * emp.displayInfo();
 * }
 * 
 * // Test Video
 * Video video = new Video("Tutorial", 104857600,
 * "http://example.com/video.mp4");
 * video.download();
 * video.play();
 * video.pause();
 * video.stop();
 */
// }
// }
