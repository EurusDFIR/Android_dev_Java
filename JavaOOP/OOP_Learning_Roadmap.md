# OOP Learning Roadmap - Từ Cơ Bản Đến Thực Tiễn

## Phase 1: OOP Fundamentals Review & Practice

### 1.1 Encapsulation - Cải thiện bài tập hiện tại

#### Lý thuyết bổ sung:

- **Data Validation**: Kiểm tra dữ liệu đầu vào
- **Immutable Objects**: Đối tượng không thể thay đổi
- **Access Modifiers**: public, private, protected, package-private
- **Defensive Programming**: Bảo vệ dữ liệu khỏi invalid states

#### Bài tập cải thiện Employee class:

```java
// Yêu cầu cụ thể:
1. Sửa logic setter để validate properly
2. Thêm business rules (salary > 0, name không empty, id format)
3. Implement defensive copying cho mutable objects
4. Thêm validation methods riêng biệt
5. Create immutable EmployeeInfo class để return data safely
```

### 1.2 Inheritance - Mở rộng bài tập Vehicle

#### Lý thuyết bổ sung:

- **Method Overriding**: Ghi đè phương thức
- **Super keyword**: Gọi constructor và method của class cha
- **Access control trong inheritance**
- **Final classes và methods**

#### Bài tập cải thiện:

```java
// Yêu cầu:
1. Thêm methods trong Vehicle: start(), stop(), getInfo()
2. Override toString(), equals(), hashCode()
3. Implement specific behaviors trong Car và Motorcycle
4. Thêm validation trong constructors
5. Tạo VehicleManager class để manage collection of vehicles
```

### 1.3 Polymorphism - Làm rõ khái niệm

#### Lý thuyết:

- **Method Overloading vs Overriding**
- **Runtime vs Compile-time polymorphism**
- **Upcasting và Downcasting**
- **instanceof operator**

#### Bài tập cải thiện MathOperations:

```java
// Yêu cầu:
1. Tạo Calculator class với overloaded methods
2. Tạo Shape hierarchy với calculateArea() polymorphism
3. Demonstrate dynamic method dispatch
4. Implement visitor pattern đơn giản
5. Array/List of different types với polymorphic behavior
```

### 1.4 Abstraction & Interface - Hoàn thiện

#### Lý thuyết:

- **Abstract class vs Interface**
- **Default methods trong interface (Java 8+)**
- **Multiple inheritance với interfaces**
- **Functional interfaces**

#### Bài tập cải thiện:

```java
// Yêu cầu:
1. Thêm concrete implementations cho Employee hierarchy
2. Interface segregation principle
3. Composition vs inheritance
4. Callback mechanisms với interfaces
```

## Phase 2: Intermediate Concepts

### 2.1 Exception Handling

#### Lý thuyết:

- **Checked vs Unchecked exceptions**
- **Custom exception classes**
- **Exception chaining**
- **Best practices**

#### Bài tập:

```java
// Tạo robust Employee management system:
1. InvalidEmployeeDataException
2. EmployeeNotFoundException
3. SalaryCalculationException
4. Proper exception handling trong all methods
```

### 2.2 Collections Framework

#### Lý thuyết:

- **List, Set, Map interfaces**
- **ArrayList vs LinkedList**
- **HashMap vs TreeMap**
- **Iterators và enhanced for loops**

#### Bài tập:

```java
// Employee Management System:
1. Store employees trong different collections
2. Search và sort employees
3. Custom Comparators
4. Stream API basics
```

### 2.3 Generics Basics

#### Lý thuyết:

- **Type safety**
- **Generic classes và methods**
- **Wildcards cơ bản**
- **Type erasure**

#### Bài tập:

```java
// Generic Repository pattern:
1. Repository<T> interface
2. EmployeeRepository extends Repository<Employee>
3. Type-safe collections
4. Generic utility methods
```

## Phase 3: Design Principles

### 3.1 SOLID Principles

#### Lý thuyết từng principle với examples:

**Single Responsibility Principle:**

```java
// BAD: Employee class doing too much
class Employee {
    // employee data
    // salary calculation
    // email sending
    // database operations
}

// GOOD: Separated responsibilities
class Employee { /* just employee data */ }
class SalaryCalculator { /* salary logic */ }
class EmailService { /* email operations */ }
class EmployeeRepository { /* database operations */ }
```

**Open/Closed Principle:**

```java
// Extend behavior without modifying existing code
abstract class Shape {
    abstract double calculateArea();
}
// New shapes can be added without changing existing code
```

#### Bài tập thực hành:

```java
// Refactor existing exercises để follow SOLID:
1. Break down large classes
2. Create abstractions
3. Use dependency injection
4. Make code extensible
```

### 3.2 Basic Design Patterns

#### 3.2.1 Factory Pattern

**Lý thuyết:**

- **Creational pattern**
- **Encapsulate object creation**
- **Polymorphic object creation**

**Bài tập:**

```java
// VehicleFactory example:
public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type, String... params) {
        switch(type) {
            case CAR: return new Car(params);
            case MOTORCYCLE: return new Motorcycle(params);
            default: throw new IllegalArgumentException();
        }
    }
}
```

#### 3.2.2 Observer Pattern

**Lý thuyết:**

- **Behavioral pattern**
- **One-to-many dependency**
- **Event notification**

**Bài tập:**

```java
// Employee status change notifications:
interface EmployeeObserver {
    void onEmployeeHired(Employee employee);
    void onEmployeeFired(Employee employee);
}

class HR implements EmployeeObserver { /* implementation */ }
class Payroll implements EmployeeObserver { /* implementation */ }
```

#### 3.2.3 Strategy Pattern

**Lý thuyết:**

- **Algorithm family**
- **Runtime algorithm selection**
- **Composition over inheritance**

**Bài tập:**

```java
// Different salary calculation strategies:
interface SalaryCalculationStrategy {
    double calculate(Employee employee);
}

class FullTimeSalaryStrategy implements SalaryCalculationStrategy { }
class PartTimeSalaryStrategy implements SalaryCalculationStrategy { }
class ContractorSalaryStrategy implements SalaryCalculationStrategy { }
```

## Phase 4: Practical Applications

### 4.1 Building a Complete System

**Lý thuyết về Architecture:**

- **Layered Architecture**
- **Separation of Concerns**
- **Dependency Management**

**Mini Project: Student Management System**

```
Structure:
├── model/          # Employee, Student, Course classes
├── repository/     # Data access interfaces & implementations
├── service/        # Business logic layer
├── exception/      # Custom exceptions
├── util/          # Utility classes
└── main/          # Application entry point
```

### 4.2 Code Quality Practices

**Lý thuyết:**

- **Naming conventions**
- **Code comments vs self-documenting code**
- **Method length và complexity**
- **DRY principle**

**Bài tập:**

```java
// Refactor existing code để improve quality:
1. Meaningful variable names
2. Extract methods
3. Remove code duplication
4. Add proper javadoc comments
```

### 4.3 Basic Testing

**Lý thuyết:**

- **Unit testing concepts**
- **Test-driven development basics**
- **Assertions**
- **Test organization**

**Bài tập:**

```java
// Write tests for Employee class:
public class EmployeeTest {
    @Test
    public void testValidSalarySet() { }

    @Test
    public void testInvalidSalaryThrowsException() { }

    @Test
    public void testEmployeeEquality() { }
}
```

## Phase 5: Integration & Real-world Simulation

### 5.1 Simple CRUD Application

**Lý thuyết:**

- **CRUD operations**
- **Data persistence concepts**
- **File I/O basics**

**Project: Employee Management Console App**

```java
// Features:
1. Add/Edit/Delete employees
2. Search employees
3. Save/Load from file
4. Simple menu system
5. Error handling
```

### 5.2 Performance Considerations

**Lý thuyết:**

- **Big O notation basics**
- **Collection performance**
- **Memory usage**
- **Profiling basics**

### 5.3 Scalability Basics

**Lý thuyết:**

- **Interface-based design**
- **Modular programming**
- **Configuration management**
- **Logging basics**

## Progression Timeline:

**Week 1-2:** Phase 1 - Strengthen OOP fundamentals
**Week 3-4:** Phase 2 - Intermediate concepts  
**Week 5-6:** Phase 3 - Design principles & basic patterns
**Week 7-8:** Phase 4 - Code quality & testing
**Week 9-10:** Phase 5 - Complete application

## Đánh giá từng phase:

- **Coding exercises** (40%)
- **Code review** (30%)
- **Concept explanation** (20%)
- **Best practices application** (10%)

Mỗi phase sẽ có lý thuyết đầy đủ trước khi implement, đảm bảo hiểu concepts trước khi áp dụng!
