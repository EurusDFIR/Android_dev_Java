package Exercises.Abtraction.BaiTap6_1;

public abstract class Employee {
    protected String id;
    protected String name;
    protected double salary;

    public Employee(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public abstract double calculateSalary();

}
