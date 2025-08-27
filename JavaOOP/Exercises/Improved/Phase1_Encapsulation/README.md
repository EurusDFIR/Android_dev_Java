# Phase 1.1: Encapsulation - Cải thiện Employee Class

## Lý thuyết cần nắm vững:

### 1. Data Validation Strategies

```java
// 3 cách validate data:
// 1. Constructor validation
// 2. Setter validation
// 3. Separate validation methods
```

### 2. Defensive Programming

```java
// Bảo vệ dữ liệu khỏi:
// - Null values
// - Invalid ranges
// - Malformed data
// - Concurrent modifications
```

### 3. Immutable Objects

```java
// Objects không thể thay đổi sau khi tạo
// Advantages: Thread-safe, predictable, cacheable
// Disadvantages: Memory usage, performance cost of creating new objects
```

## Bài tập thực hành:

### Bước 1: Phân tích vấn đề trong Employee class hiện tại

**Vấn đề tìm thấy:**

1. `setId()` chỉ set khi id == null (logic sai)
2. `setName()` chỉ set khi name == null (logic sai)
3. `setSalary()` kiểm tra salary > 0 nhưng so sánh với field cũ, không phải parameter mới
4. Thiếu validation cho constructor
5. Không có error handling

### Bước 2: Tạo Employee class cải thiện

**Yêu cầu cụ thể:**

#### 2.1 Tạo validation utilities

```java
// Tạo file ValidationUtils.java với:
public class ValidationUtils {
    // Validate employee ID format: "EMP" + 4 digits
    public static boolean isValidEmployeeId(String id)

    // Validate name: không null, không empty, chỉ chứa letters và spaces
    public static boolean isValidName(String name)

    // Validate salary: > 0 và <= 1,000,000
    public static boolean isValidSalary(double salary)

    // Utility method để trim và capitalize name
    public static String formatName(String name)
}
```

#### 2.2 Tạo custom exceptions

```java
// Tạo InvalidEmployeeDataException.java
public class InvalidEmployeeDataException extends Exception {
    private String fieldName;
    private Object invalidValue;

    // Constructor với message, fieldName, invalidValue
    // Getter methods
    // Override toString() để show detailed error
}
```

#### 2.3 Cải thiện Employee class

```java
// Yêu cầu implement:
public class Employee {
    // 1. Fields với proper access modifiers
    private final String id;  // immutable
    private String name;
    private double salary;
    private final LocalDateTime createdAt; // immutable timestamp

    // 2. Constructor với full validation
    public Employee(String id, String name, double salary) throws InvalidEmployeeDataException

    // 3. Getters (no setters cho immutable fields)

    // 4. Setters với proper validation
    public void setName(String name) throws InvalidEmployeeDataException
    public void setSalary(double salary) throws InvalidEmployeeDataException

    // 5. Business methods
    public double getAnnualSalary()
    public double getMonthlySalary()
    public boolean isHighEarner() // salary > 100,000

    // 6. Override Object methods
    @Override public boolean equals(Object obj)
    @Override public int hashCode()
    @Override public String toString()

    // 7. Defensive copying method
    public EmployeeInfo getEmployeeInfo() // return immutable view
}
```

#### 2.4 Tạo immutable EmployeeInfo class

```java
// Immutable data holder cho external access
public final class EmployeeInfo {
    private final String id;
    private final String name;
    private final double salary;
    private final LocalDateTime createdAt;

    // Constructor
    // Getters only (no setters)
    // Override toString(), equals(), hashCode()
}
```

### Bước 3: Testing và Validation

#### 3.1 Tạo EmployeeTest class

```java
// Test scenarios:
public class EmployeeTest {
    // Valid data tests
    @Test public void testValidEmployeeCreation()
    @Test public void testValidNameUpdate()
    @Test public void testValidSalaryUpdate()

    // Invalid data tests
    @Test public void testInvalidIdThrowsException()
    @Test public void testInvalidNameThrowsException()
    @Test public void testInvalidSalaryThrowsException()

    // Edge cases
    @Test public void testNullValuesHandling()
    @Test public void testEmptyStringHandling()
    @Test public void testBoundaryValues()

    // Business logic tests
    @Test public void testAnnualSalaryCalculation()
    @Test public void testHighEarnerClassification()

    // Object behavior tests
    @Test public void testEqualsAndHashCode()
    @Test public void testToStringFormat()
    @Test public void testImmutableIdField()
}
```

#### 3.2 Tạo EmployeeManager class

```java
// Để demonstrate encapsulation trong context lớn hơn
public class EmployeeManager {
    private List<Employee> employees;

    // Methods với proper encapsulation:
    public void addEmployee(Employee employee)
    public Employee findEmployeeById(String id)
    public List<EmployeeInfo> getAllEmployeeInfo() // return immutable views
    public boolean updateEmployeeSalary(String id, double newSalary)
    public double getTotalPayroll()
    public List<EmployeeInfo> getHighEarners()
}
```

### Bước 4: Demonstration Code

#### 4.1 Tạo EncapsulationDemo class

```java
public class EncapsulationDemo {
    public static void main(String[] args) {
        // Demonstrate:
        // 1. Valid object creation
        // 2. Exception handling for invalid data
        // 3. Proper data access through getters
        // 4. Immutable field protection
        // 5. Defensive copying
        // 6. Business logic encapsulation
    }
}
```

## Đánh giá criteria:

1. **Validation Logic (25%)**

   - Proper input validation trong constructor và setters
   - Meaningful error messages
   - Edge case handling

2. **Encapsulation Quality (25%)**

   - Appropriate access modifiers
   - Immutable fields properly protected
   - No direct field access from outside

3. **Error Handling (20%)**

   - Custom exceptions usage
   - Proper exception propagation
   - Graceful error recovery

4. **Code Quality (20%)**

   - Clean, readable code
   - Proper naming conventions
   - Method organization

5. **Testing Coverage (10%)**
   - All scenarios tested
   - Edge cases covered
   - Business logic verified

## Expected deliverables:

1. `ValidationUtils.java` - Input validation utilities
2. `InvalidEmployeeDataException.java` - Custom exception
3. `Employee.java` - Improved employee class
4. `EmployeeInfo.java` - Immutable data holder
5. `EmployeeManager.java` - Context class demonstrating encapsulation
6. `EmployeeTest.java` - Comprehensive test class
7. `EncapsulationDemo.java` - Demonstration code

## Timeline: 3-4 days

- Day 1: Lý thuyết và ValidationUtils
- Day 2: Employee class improvements
- Day 3: EmployeeManager và testing
- Day 4: Review và refinement
