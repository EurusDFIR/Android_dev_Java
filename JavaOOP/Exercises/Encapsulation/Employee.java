package Exercises.Encapsulation;

public class Employee {
    private String id;
    private String name;
    private double salary;

    public Employee(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // getter
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getSalary() {
        return this.salary;
    }

    // setter

    public void setId(String id) {
        if (this.id == null) {
            this.id = id;
        }
    }

    public void setName(String name) {
        if (this.name == null) {
            this.name = name;
        }

    }

    public void setSalary(double salary) {
        if (this.salary > 0) {
            this.salary = salary;
        }
    }

    public void displayInfo() {
        System.out.println("Thong tin nguoi dung: ");
        System.out.println("id: " + this.id);
        System.out.println("name: " + this.name);
        System.out.println("Salary: " + this.salary);

    }

    public static void main(String[] args) {
        Employee employee = new Employee("id1", "Violet", 999999);
        employee.displayInfo();
    }
}
