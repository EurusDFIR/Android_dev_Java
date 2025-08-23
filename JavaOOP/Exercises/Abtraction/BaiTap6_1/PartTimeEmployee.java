package Exercises.Abtraction.BaiTap6_1;

public class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String id, String name, double salary) {
        super(id, name, salary);

    }

    @Override
    public double calculateSalary() {
        return 500 * salary;
    }

}
