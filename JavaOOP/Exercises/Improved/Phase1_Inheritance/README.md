# Phase 1.2: Inheritance - Cải thiện Vehicle Hierarchy

## Lý thuyết cần nắm vững:

### 1. Method Overriding vs Overloading

```java
// Overriding: Same signature, different implementation in subclass
// Overloading: Same name, different parameters in same class
```

### 2. Super Keyword Usage

```java
// super() - call parent constructor
// super.methodName() - call parent method
// When to use và best practices
```

### 3. Access Control trong Inheritance

```java
// protected: accessible trong subclasses
// package-private: accessible trong same package
// private: not inherited
// public: fully inherited
```

### 4. Abstract Classes vs Concrete Classes

```java
// When to make class/method abstract
// Template Method pattern basics
```

## Bài tập thực hành:

### Bước 1: Phân tích vấn đề trong Vehicle hierarchy hiện tại

**Vấn đề tìm thấy:**

1. `Vehicle` class chỉ có constructor, không có behavior
2. `Car` và `Motorcycle` không override methods
3. Thiếu toString(), equals(), hashCode()
4. Không có polymorphic behavior demonstration
5. Thiếu validation và business logic

### Bước 2: Tạo Vehicle hierarchy cải thiện

#### 2.1 Tạo enums và utility classes

```java
// VehicleType.java
public enum VehicleType {
    CAR("Car", 4),
    MOTORCYCLE("Motorcycle", 2),
    TRUCK("Truck", 6),
    BICYCLE("Bicycle", 2);

    private final String displayName;
    private final int defaultWheels;

    // Constructor, getters, methods
}

// FuelType.java
public enum FuelType {
    GASOLINE(8.5, "Gasoline"),
    DIESEL(9.2, "Diesel"),
    ELECTRIC(0.0, "Electric"),
    HYBRID(7.0, "Hybrid");

    private final double averageConsumption; // liters per 100km
    private final String displayName;
}

// VehicleStatus.java
public enum VehicleStatus {
    PARKED, RUNNING, MAINTENANCE, OUT_OF_SERVICE
}
```

#### 2.2 Cải thiện Vehicle class

```java
// Abstract Vehicle class với đầy đủ functionality
public abstract class Vehicle {
    // Protected fields cho subclass access
    protected final String vehicleId;
    protected final String brand;
    protected final int year;
    protected double maxSpeed;
    protected VehicleStatus status;
    protected double mileage;
    protected final LocalDateTime manufacturedDate;

    // Constructor với validation
    public Vehicle(String vehicleId, String brand, int year, double maxSpeed)

    // Abstract methods - must be implemented by subclasses
    public abstract void start();
    public abstract void stop();
    public abstract VehicleType getVehicleType();
    public abstract double calculateFuelConsumption(double distance);

    // Concrete methods - shared behavior
    public void accelerate(double speedIncrease)
    public void decelerate(double speedDecrease)
    public void performMaintenance()
    public boolean isReadyToDrive()

    // Getters và setters với validation
    public void setMaxSpeed(double maxSpeed)
    public void addMileage(double distance)

    // Template method pattern
    public final String getVehicleInfo() {
        return String.format("%s - %s (%d) - %s - %.1f km",
            getVehicleType().getDisplayName(), brand, year,
            status, mileage);
    }

    // Override Object methods
    @Override public boolean equals(Object obj)
    @Override public int hashCode()
    @Override public String toString()
}
```

#### 2.3 Implement concrete classes

**Car.java:**

```java
public class Car extends Vehicle {
    private final int numberOfDoors;
    private final FuelType fuelType;
    private final boolean isAutomatic;
    private double currentFuelLevel; // percentage

    // Constructor calling super()
    public Car(String vehicleId, String brand, int year, double maxSpeed,
               int numberOfDoors, FuelType fuelType, boolean isAutomatic)

    // Override abstract methods với Car-specific implementation
    @Override public void start() {
        // Car-specific start sequence
        // Check fuel level, doors closed, etc.
    }

    @Override public void stop() {
        // Car-specific stop sequence
    }

    @Override public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }

    @Override public double calculateFuelConsumption(double distance) {
        // Car-specific fuel calculation
        // Consider weight, engine type, driving conditions
    }

    // Car-specific methods
    public void lockDoors()
    public void unlockDoors()
    public void refuel(double amount)
    public boolean canTakePassengers(int passengers)

    // Override toString() để include Car-specific info
    @Override public String toString() {
        return super.toString() + String.format(" | %d doors, %s, %s",
            numberOfDoors, fuelType, isAutomatic ? "Automatic" : "Manual");
    }
}
```

**Motorcycle.java:**

```java
public class Motorcycle extends Vehicle {
    private final int engineCapacity; // cc
    private final boolean hasSidecar;
    private boolean helmetRequired;

    // Constructor
    public Motorcycle(String vehicleId, String brand, int year, double maxSpeed,
                     int engineCapacity, boolean hasSidecar)

    // Override abstract methods với Motorcycle-specific implementation
    @Override public void start() {
        // Motorcycle-specific start sequence
        // Check helmet, kickstand, etc.
    }

    @Override public void stop() {
        // Motorcycle-specific stop sequence
    }

    @Override public VehicleType getVehicleType() {
        return VehicleType.MOTORCYCLE;
    }

    @Override public double calculateFuelConsumption(double distance) {
        // Motorcycle-specific fuel calculation
        // Generally more efficient than cars
    }

    // Motorcycle-specific methods
    public void kickStart()
    public void putDownKickstand()
    public boolean requiresHelmet()

    // Override toString()
    @Override public String toString() {
        return super.toString() + String.format(" | %dcc engine, %s",
            engineCapacity, hasSidecar ? "With sidecar" : "Solo");
    }
}
```

### Bước 3: Polymorphism demonstration

#### 3.1 VehicleManager class

```java
public class VehicleManager {
    private List<Vehicle> vehicles;
    private Map<String, Vehicle> vehicleRegistry;

    public VehicleManager() {
        vehicles = new ArrayList<>();
        vehicleRegistry = new HashMap<>();
    }

    // Polymorphic methods
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        vehicleRegistry.put(vehicle.getVehicleId(), vehicle);
    }

    public void startAllVehicles() {
        for (Vehicle vehicle : vehicles) {
            vehicle.start(); // Polymorphic call
        }
    }

    public void displayAllVehicles() {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString()); // Polymorphic call
        }
    }

    public double calculateTotalFuelConsumption(double distance) {
        double total = 0;
        for (Vehicle vehicle : vehicles) {
            total += vehicle.calculateFuelConsumption(distance); // Polymorphic
        }
        return total;
    }

    // Search methods demonstrating polymorphism
    public List<Vehicle> findVehiclesByType(VehicleType type) {
        return vehicles.stream()
            .filter(v -> v.getVehicleType() == type)
            .collect(Collectors.toList());
    }

    public List<Car> getAllCars() {
        return vehicles.stream()
            .filter(v -> v instanceof Car)
            .map(v -> (Car) v) // Downcasting
            .collect(Collectors.toList());
    }
}
```

#### 3.2 VehicleFactory class

```java
public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type, Map<String, Object> parameters) {
        String id = (String) parameters.get("id");
        String brand = (String) parameters.get("brand");
        int year = (Integer) parameters.get("year");
        double maxSpeed = (Double) parameters.get("maxSpeed");

        switch (type) {
            case CAR:
                return new Car(id, brand, year, maxSpeed,
                    (Integer) parameters.get("doors"),
                    (FuelType) parameters.get("fuelType"),
                    (Boolean) parameters.get("automatic"));

            case MOTORCYCLE:
                return new Motorcycle(id, brand, year, maxSpeed,
                    (Integer) parameters.get("engineCapacity"),
                    (Boolean) parameters.get("hasSidecar"));

            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        }
    }

    // Overloaded factory methods
    public static Car createCar(String id, String brand, int year, double maxSpeed) {
        // Default car configuration
    }

    public static Motorcycle createMotorcycle(String id, String brand, int year, double maxSpeed) {
        // Default motorcycle configuration
    }
}
```

### Bước 4: Testing và Validation

#### 4.1 VehicleTest class

```java
public class VehicleTest {
    // Test inheritance
    @Test public void testVehicleInheritance()
    @Test public void testMethodOverriding()
    @Test public void testPolymorphism()

    // Test each vehicle type
    @Test public void testCarSpecificMethods()
    @Test public void testMotorcycleSpecificMethods()

    // Test object methods
    @Test public void testEqualsAndHashCode()
    @Test public void testToStringMethods()

    // Test polymorphic behavior
    @Test public void testPolymorphicMethodCalls()
    @Test public void testInstanceofOperator()
    @Test public void testDowncasting()

    // Test factory pattern
    @Test public void testVehicleFactory()
}
```

### Bước 5: Demonstration

#### 5.1 InheritanceDemo class

```java
public class InheritanceDemo {
    public static void main(String[] args) {
        // Demonstrate:
        // 1. Object creation và inheritance
        // 2. Method overriding
        // 3. Polymorphism
        // 4. Upcasting và downcasting
        // 5. Factory pattern usage
        // 6. Collection of different vehicle types

        demonstrateInheritance();
        demonstratePolymorphism();
        demonstrateFactoryPattern();
        demonstrateVehicleManager();
    }

    private static void demonstrateInheritance() {
        // Create vehicles và show inheritance
    }

    private static void demonstratePolymorphism() {
        // Array/List of vehicles with polymorphic calls
    }

    private static void demonstrateFactoryPattern() {
        // Create vehicles using factory
    }

    private static void demonstrateVehicleManager() {
        // Use VehicleManager to show real-world usage
    }
}
```

## Đánh giá criteria:

1. **Inheritance Implementation (30%)**

   - Proper use của super keyword
   - Correct method overriding
   - Access modifier understanding

2. **Polymorphism (25%)**

   - Dynamic method dispatch
   - Proper upcasting/downcasting
   - instanceof usage

3. **Code Design (25%)**

   - Logical class hierarchy
   - Appropriate abstractions
   - Clean method organization

4. **Object Methods (10%)**

   - equals(), hashCode(), toString() implementation
   - Consistency between methods

5. **Testing (10%)**
   - Comprehensive test coverage
   - Edge case testing

## Expected deliverables:

1. `VehicleType.java`, `FuelType.java`, `VehicleStatus.java` - Enums
2. `Vehicle.java` - Abstract base class
3. `Car.java`, `Motorcycle.java` - Concrete implementations
4. `VehicleManager.java` - Polymorphism demonstration
5. `VehicleFactory.java` - Factory pattern
6. `VehicleTest.java` - Test class
7. `InheritanceDemo.java` - Demonstration code

## Timeline: 4-5 days

- Day 1: Lý thuyết và Vehicle abstract class
- Day 2: Car và Motorcycle implementations
- Day 3: VehicleManager và polymorphism
- Day 4: Factory pattern và testing
- Day 5: Review và refinement
