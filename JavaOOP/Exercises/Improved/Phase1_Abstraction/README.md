# Phase 1.4: Abstraction & Interface - Hoàn thiện

## Lý thuyết cần nắm vững:

### 1. Abstract Classes vs Interfaces

```java
// Abstract Class:
// - Can have concrete methods
// - Can have instance variables
// - Single inheritance only
// - Constructor allowed

// Interface:
// - Only method signatures (before Java 8)
// - Default và static methods (Java 8+)
// - Multiple inheritance supported
// - No constructor
```

### 2. When to use Abstract Class vs Interface

```java
// Use Abstract Class when:
// - You want to share code among related classes
// - You need to declare non-public members
// - You want to provide default implementation

// Use Interface when:
// - You expect unrelated classes to implement
// - You want to support multiple inheritance
// - You want to specify behavior contract only
```

### 3. Interface Evolution (Java 8+)

```java
interface ModernInterface {
    // Abstract method (implicit public abstract)
    void abstractMethod();

    // Default method (Java 8+)
    default void defaultMethod() {
        System.out.println("Default implementation");
    }

    // Static method (Java 8+)
    static void staticMethod() {
        System.out.println("Static method in interface");
    }

    // Private method (Java 9+) - helper methods
    private void helperMethod() {
        System.out.println("Private helper");
    }
}
```

## Bài tập thực hành:

### Bước 1: Cải thiện Employee Abstraction

#### 1.1 Enhanced Employee Abstract Class

```java
public abstract class Employee {
    // Protected fields for subclass access
    protected final String employeeId;
    protected final String name;
    protected final LocalDate hireDate;
    protected final Department department;
    protected double baseSalary;
    protected EmployeeStatus status;

    // Constructor
    public Employee(String employeeId, String name, Department department, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
        this.hireDate = LocalDate.now();
        this.status = EmployeeStatus.ACTIVE;
    }

    // Abstract methods - must be implemented by subclasses
    public abstract double calculateMonthlySalary();
    public abstract double calculateAnnualBonus();
    public abstract int getMaxVacationDays();
    public abstract EmployeeType getEmployeeType();

    // Template method pattern - defines algorithm structure
    public final double calculateTotalCompensation() {
        double monthly = calculateMonthlySalary();
        double bonus = calculateAnnualBonus();
        double benefits = calculateBenefits();
        return (monthly * 12) + bonus + benefits;
    }

    // Hook method - can be overridden by subclasses
    protected double calculateBenefits() {
        return baseSalary * 0.15; // Default 15% benefits
    }

    // Concrete methods shared by all employees
    public void promote(double salaryIncrease) {
        this.baseSalary += salaryIncrease;
        System.out.println(name + " promoted with salary increase: $" + salaryIncrease);
    }

    public boolean isEligibleForPromotion() {
        return Years.between(hireDate, LocalDate.now()).getValue() >= 1;
    }

    public void changeStatus(EmployeeStatus newStatus) {
        EmployeeStatus oldStatus = this.status;
        this.status = newStatus;
        System.out.println(name + " status changed from " + oldStatus + " to " + newStatus);
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public Department getDepartment() { return department; }
    public double getBaseSalary() { return baseSalary; }
    public LocalDate getHireDate() { return hireDate; }
    public EmployeeStatus getStatus() { return status; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(employeeId, employee.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public String toString() {
        return String.format("%s[ID=%s, Name=%s, Type=%s, Salary=$%.2f]",
            getClass().getSimpleName(), employeeId, name, getEmployeeType(), baseSalary);
    }
}
```

#### 1.2 Supporting Enums và Classes

```java
// EmployeeType.java
public enum EmployeeType {
    FULL_TIME("Full-time", 40),
    PART_TIME("Part-time", 20),
    CONTRACTOR("Contractor", 0),
    INTERN("Intern", 40);

    private final String displayName;
    private final int standardHours;

    EmployeeType(String displayName, int standardHours) {
        this.displayName = displayName;
        this.standardHours = standardHours;
    }

    public String getDisplayName() { return displayName; }
    public int getStandardHours() { return standardHours; }
}

// EmployeeStatus.java
public enum EmployeeStatus {
    ACTIVE, ON_LEAVE, SUSPENDED, TERMINATED
}

// Department.java
public enum Department {
    IT("Information Technology", "IT"),
    HR("Human Resources", "HR"),
    FINANCE("Finance", "FIN"),
    MARKETING("Marketing", "MKT"),
    OPERATIONS("Operations", "OPS");

    private final String fullName;
    private final String code;

    Department(String fullName, String code) {
        this.fullName = fullName;
        this.code = code;
    }

    public String getFullName() { return fullName; }
    public String getCode() { return code; }
}
```

### Bước 2: Interface Design

#### 2.1 Behavioral Interfaces

```java
// Payable.java - Interface for entities that can receive payment
public interface Payable {
    double calculatePay();

    default PaymentMethod getPreferredPaymentMethod() {
        return PaymentMethod.BANK_TRANSFER;
    }

    // Static utility method
    static double calculateTax(double amount, double taxRate) {
        return amount * taxRate;
    }
}

// Promotable.java - Interface for entities that can be promoted
public interface Promotable {
    boolean isEligibleForPromotion();
    void promote(double salaryIncrease);

    default double getPromotionThreshold() {
        return 0.10; // 10% default increase
    }
}

// Trainable.java - Interface for entities that can receive training
public interface Trainable {
    void attendTraining(Training training);
    List<Training> getCompletedTrainings();
    boolean isTrainingRequired();

    default int getMaxTrainingHours() {
        return 40; // Default 40 hours per year
    }
}

// Manageable.java - Interface for entities that can manage others
public interface Manageable {
    void addSubordinate(Employee employee);
    void removeSubordinate(String employeeId);
    List<Employee> getSubordinates();

    default int getMaxSubordinates() {
        return 10; // Default max team size
    }

    default void conductPerformanceReview(Employee employee) {
        System.out.println("Conducting performance review for: " + employee.getName());
    }
}
```

#### 2.2 Functional Interfaces (Java 8+)

```java
// Custom functional interfaces for employee operations
@FunctionalInterface
public interface SalaryCalculator {
    double calculate(Employee employee);

    // Default method for combining calculators
    default SalaryCalculator and(SalaryCalculator other) {
        return emp -> this.calculate(emp) + other.calculate(emp);
    }
}

@FunctionalInterface
public interface EmployeeFilter {
    boolean test(Employee employee);

    // Static factory methods
    static EmployeeFilter byDepartment(Department dept) {
        return emp -> emp.getDepartment() == dept;
    }

    static EmployeeFilter bySalaryRange(double min, double max) {
        return emp -> emp.getBaseSalary() >= min && emp.getBaseSalary() <= max;
    }
}

@FunctionalInterface
public interface EmployeeProcessor {
    void process(Employee employee);

    // Default method for chaining processors
    default EmployeeProcessor andThen(EmployeeProcessor after) {
        return emp -> {
            this.process(emp);
            after.process(emp);
        };
    }
}
```

### Bước 3: Concrete Employee Implementations

#### 3.1 FullTimeEmployee

```java
public class FullTimeEmployee extends Employee implements Payable, Promotable, Trainable, Manageable {
    private final double annualSalary;
    private final List<Employee> subordinates;
    private final List<Training> completedTrainings;
    private final BenefitsPackage benefitsPackage;

    public FullTimeEmployee(String employeeId, String name, Department department,
                           double annualSalary, BenefitsPackage benefitsPackage) {
        super(employeeId, name, department, annualSalary);
        this.annualSalary = annualSalary;
        this.benefitsPackage = benefitsPackage;
        this.subordinates = new ArrayList<>();
        this.completedTrainings = new ArrayList<>();
    }

    // Abstract methods implementation
    @Override
    public double calculateMonthlySalary() {
        return annualSalary / 12;
    }

    @Override
    public double calculateAnnualBonus() {
        // Full-time employees get 10% bonus based on performance
        return annualSalary * 0.10;
    }

    @Override
    public int getMaxVacationDays() {
        return 25; // Full-time gets 25 days
    }

    @Override
    public EmployeeType getEmployeeType() {
        return EmployeeType.FULL_TIME;
    }

    // Payable interface
    @Override
    public double calculatePay() {
        return calculateMonthlySalary();
    }

    // Promotable interface
    @Override
    public boolean isEligibleForPromotion() {
        return super.isEligibleForPromotion() &&
               completedTrainings.size() >= 2; // Additional requirement
    }

    // Trainable interface
    @Override
    public void attendTraining(Training training) {
        completedTrainings.add(training);
        System.out.println(getName() + " completed training: " + training.getName());
    }

    @Override
    public List<Training> getCompletedTrainings() {
        return new ArrayList<>(completedTrainings); // Defensive copy
    }

    @Override
    public boolean isTrainingRequired() {
        return completedTrainings.size() < 4; // Requirement: 4 trainings per year
    }

    // Manageable interface
    @Override
    public void addSubordinate(Employee employee) {
        if (subordinates.size() < getMaxSubordinates()) {
            subordinates.add(employee);
            System.out.println(employee.getName() + " added as subordinate to " + getName());
        } else {
            throw new IllegalStateException("Maximum subordinates limit reached");
        }
    }

    @Override
    public void removeSubordinate(String employeeId) {
        subordinates.removeIf(emp -> emp.getEmployeeId().equals(employeeId));
    }

    @Override
    public List<Employee> getSubordinates() {
        return new ArrayList<>(subordinates); // Defensive copy
    }

    @Override
    protected double calculateBenefits() {
        return benefitsPackage.getTotalValue();
    }
}
```

#### 3.2 PartTimeEmployee

```java
public class PartTimeEmployee extends Employee implements Payable, Trainable {
    private final double hourlyRate;
    private final int hoursPerWeek;
    private final List<Training> completedTrainings;

    public PartTimeEmployee(String employeeId, String name, Department department,
                           double hourlyRate, int hoursPerWeek) {
        super(employeeId, name, department, hourlyRate * hoursPerWeek * 52); // Annual equivalent
        this.hourlyRate = hourlyRate;
        this.hoursPerWeek = hoursPerWeek;
        this.completedTrainings = new ArrayList<>();
    }

    @Override
    public double calculateMonthlySalary() {
        return hourlyRate * hoursPerWeek * 4.33; // Average weeks per month
    }

    @Override
    public double calculateAnnualBonus() {
        return hourlyRate * hoursPerWeek * 2; // 2 weeks bonus
    }

    @Override
    public int getMaxVacationDays() {
        return hoursPerWeek >= 20 ? 15 : 10; // Prorated vacation
    }

    @Override
    public EmployeeType getEmployeeType() {
        return EmployeeType.PART_TIME;
    }

    @Override
    public double calculatePay() {
        return calculateMonthlySalary();
    }

    @Override
    public void attendTraining(Training training) {
        completedTrainings.add(training);
        System.out.println(getName() + " (Part-time) completed training: " + training.getName());
    }

    @Override
    public List<Training> getCompletedTrainings() {
        return new ArrayList<>(completedTrainings);
    }

    @Override
    public boolean isTrainingRequired() {
        return completedTrainings.size() < 2; // Lower requirement for part-time
    }

    @Override
    public int getMaxTrainingHours() {
        return 20; // Reduced hours for part-time
    }

    @Override
    protected double calculateBenefits() {
        return getBaseSalary() * 0.05; // Reduced benefits for part-time
    }

    // Part-time specific methods
    public double getHourlyRate() { return hourlyRate; }
    public int getHoursPerWeek() { return hoursPerWeek; }
    public double getWeeklyPay() { return hourlyRate * hoursPerWeek; }
}
```

#### 3.3 Contractor

```java
public class Contractor extends Employee implements Payable {
    private final double contractAmount;
    private final LocalDate contractStartDate;
    private final LocalDate contractEndDate;
    private final ContractType contractType;

    public Contractor(String employeeId, String name, Department department,
                     double contractAmount, LocalDate contractEndDate, ContractType contractType) {
        super(employeeId, name, department, contractAmount);
        this.contractAmount = contractAmount;
        this.contractStartDate = LocalDate.now();
        this.contractEndDate = contractEndDate;
        this.contractType = contractType;
    }

    @Override
    public double calculateMonthlySalary() {
        long contractMonths = ChronoUnit.MONTHS.between(contractStartDate, contractEndDate);
        return contractMonths > 0 ? contractAmount / contractMonths : contractAmount;
    }

    @Override
    public double calculateAnnualBonus() {
        return 0; // Contractors don't get bonuses
    }

    @Override
    public int getMaxVacationDays() {
        return 0; // No vacation for contractors
    }

    @Override
    public EmployeeType getEmployeeType() {
        return EmployeeType.CONTRACTOR;
    }

    @Override
    public double calculatePay() {
        return calculateMonthlySalary();
    }

    @Override
    protected double calculateBenefits() {
        return 0; // No benefits for contractors
    }

    // Contractor-specific methods
    public boolean isContractActive() {
        LocalDate now = LocalDate.now();
        return !now.isBefore(contractStartDate) && !now.isAfter(contractEndDate);
    }

    public long getRemainingContractDays() {
        return ChronoUnit.DAYS.between(LocalDate.now(), contractEndDate);
    }

    public ContractType getContractType() { return contractType; }
    public LocalDate getContractEndDate() { return contractEndDate; }
}
```

### Bước 4: Supporting Classes

#### 4.1 Training class

```java
public class Training {
    private final String name;
    private final String description;
    private final int hours;
    private final TrainingType type;
    private final LocalDate completionDate;

    public Training(String name, String description, int hours, TrainingType type) {
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.type = type;
        this.completionDate = LocalDate.now();
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getHours() { return hours; }
    public TrainingType getType() { return type; }
    public LocalDate getCompletionDate() { return completionDate; }

    @Override
    public String toString() {
        return String.format("Training[%s, %d hours, %s]", name, hours, type);
    }
}

// TrainingType.java
public enum TrainingType {
    TECHNICAL, LEADERSHIP, SAFETY, COMPLIANCE, SOFT_SKILLS
}

// ContractType.java
public enum ContractType {
    FIXED_TERM, PROJECT_BASED, CONSULTING, FREELANCE
}
```

### Bước 5: Demonstration và Testing

#### 5.1 AbstractionDemo class

```java
public class AbstractionDemo {
    public static void main(String[] args) {
        demonstrateAbstractClassUsage();
        demonstrateInterfaceImplementation();
        demonstratePolymorphismWithInterfaces();
        demonstrateFunctionalInterfaces();
    }

    private static void demonstrateAbstractClassUsage() {
        System.out.println("========== ABSTRACT CLASS DEMONSTRATION ==========");

        // Cannot instantiate abstract class
        // Employee emp = new Employee(); // Compilation error

        List<Employee> employees = Arrays.asList(
            new FullTimeEmployee("FT001", "John Doe", Department.IT, 80000,
                new BenefitsPackage(15000)),
            new PartTimeEmployee("PT001", "Jane Smith", Department.HR, 25, 20),
            new Contractor("CT001", "Bob Johnson", Department.MARKETING, 60000,
                LocalDate.now().plusMonths(6), ContractType.PROJECT_BASED)
        );

        for (Employee emp : employees) {
            System.out.println("\n--- " + emp.getName() + " ---");
            System.out.println("Type: " + emp.getEmployeeType());
            System.out.println("Monthly Salary: $" + emp.calculateMonthlySalary());
            System.out.println("Annual Bonus: $" + emp.calculateAnnualBonus());
            System.out.println("Total Compensation: $" + emp.calculateTotalCompensation());
        }
    }

    private static void demonstrateInterfaceImplementation() {
        System.out.println("\n========== INTERFACE IMPLEMENTATION ==========");

        FullTimeEmployee manager = new FullTimeEmployee("FT002", "Alice Manager",
            Department.IT, 100000, new BenefitsPackage(20000));

        PartTimeEmployee assistant = new PartTimeEmployee("PT002", "Charlie Assistant",
            Department.IT, 20, 15);

        // Demonstrate multiple interface implementations
        System.out.println("--- Payable Interface ---");
        System.out.println("Manager Pay: $" + manager.calculatePay());
        System.out.println("Assistant Pay: $" + assistant.calculatePay());

        System.out.println("\n--- Trainable Interface ---");
        Training javaTraining = new Training("Java Advanced", "Advanced Java concepts",
            40, TrainingType.TECHNICAL);
        manager.attendTraining(javaTraining);
        assistant.attendTraining(javaTraining);

        System.out.println("Manager needs training: " + manager.isTrainingRequired());
        System.out.println("Assistant needs training: " + assistant.isTrainingRequired());

        System.out.println("\n--- Manageable Interface ---");
        manager.addSubordinate(assistant);
        System.out.println("Manager's subordinates: " + manager.getSubordinates().size());
        manager.conductPerformanceReview(assistant);
    }

    private static void demonstratePolymorphismWithInterfaces() {
        System.out.println("\n========== INTERFACE POLYMORPHISM ==========");

        List<Payable> payableEntities = Arrays.asList(
            new FullTimeEmployee("FT003", "David", Department.FINANCE, 75000,
                new BenefitsPackage(12000)),
            new PartTimeEmployee("PT003", "Eva", Department.OPERATIONS, 22, 25),
            new Contractor("CT002", "Frank", Department.IT, 80000,
                LocalDate.now().plusMonths(12), ContractType.CONSULTING)
        );

        double totalPayroll = 0;
        for (Payable payable : payableEntities) {
            double pay = payable.calculatePay();
            totalPayroll += pay;
            System.out.println("Pay: $" + pay + " | Method: " +
                payable.getPreferredPaymentMethod());
        }
        System.out.println("Total Monthly Payroll: $" + totalPayroll);
    }

    private static void demonstrateFunctionalInterfaces() {
        System.out.println("\n========== FUNCTIONAL INTERFACES ==========");

        List<Employee> employees = Arrays.asList(
            new FullTimeEmployee("FT004", "Grace", Department.HR, 70000,
                new BenefitsPackage(10000)),
            new PartTimeEmployee("PT004", "Henry", Department.MARKETING, 18, 30),
            new Contractor("CT003", "Ivy", Department.FINANCE, 75000,
                LocalDate.now().plusMonths(8), ContractType.FIXED_TERM)
        );

        // Using functional interfaces
        SalaryCalculator bonusCalculator = emp -> emp.calculateAnnualBonus();
        EmployeeFilter highEarners = emp -> emp.getBaseSalary() > 50000;
        EmployeeProcessor promotionProcessor = emp -> {
            if (emp instanceof Promotable && ((Promotable) emp).isEligibleForPromotion()) {
                System.out.println(emp.getName() + " is eligible for promotion");
            }
        };

        employees.stream()
            .filter(highEarners::test)
            .forEach(emp -> {
                System.out.println(emp.getName() + " - Bonus: $" + bonusCalculator.calculate(emp));
                promotionProcessor.process(emp);
            });
    }
}
```

## Đánh giá criteria:

1. **Abstract Class Design (25%)**

   - Proper abstraction level
   - Template method pattern usage
   - Concrete method implementation

2. **Interface Design (25%)**

   - Interface segregation
   - Default method usage
   - Multiple inheritance handling

3. **Implementation Quality (25%)**

   - Correct abstract method implementation
   - Interface method implementation
   - Code organization

4. **Polymorphism với Interfaces (15%)**

   - Interface-based polymorphism
   - Type checking và casting
   - Functional interface usage

5. **Testing (10%)**
   - Abstract class testing
   - Interface implementation testing
   - Polymorphic behavior testing

## Expected deliverables:

1. `Employee.java` - Enhanced abstract class
2. Supporting enums: `EmployeeType.java`, `Department.java`, etc.
3. Interfaces: `Payable.java`, `Promotable.java`, `Trainable.java`, `Manageable.java`
4. Functional interfaces: `SalaryCalculator.java`, `EmployeeFilter.java`, `EmployeeProcessor.java`
5. Concrete classes: `FullTimeEmployee.java`, `PartTimeEmployee.java`, `Contractor.java`
6. Supporting classes: `Training.java`, `BenefitsPackage.java`
7. `AbstractionDemo.java` - Comprehensive demonstration
8. `AbstractionTest.java` - Test suite

## Timeline: 5-6 days

- Day 1: Lý thuyết và abstract Employee class
- Day 2: Interface design và implementation
- Day 3: Concrete employee classes
- Day 4: Functional interfaces và advanced features
- Day 5: Demonstration và testing
- Day 6: Review và refinement
