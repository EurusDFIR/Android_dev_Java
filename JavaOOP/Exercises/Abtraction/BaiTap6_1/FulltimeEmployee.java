package Exercises.Abtraction.BaiTap6_1;

public class FulltimeEmployee extends Employee {

    public FulltimeEmployee(String id, String name, double salary) {
        super(id, name, salary);
    }

    @Override
    public double calculateSalary() {
        return 1000 * salary;
    }
}
