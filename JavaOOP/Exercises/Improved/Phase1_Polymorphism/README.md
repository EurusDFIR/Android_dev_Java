# Phase 1.3: Polymorphism - Comprehensive Understanding

## Lý thuyết cần nắm vững:

### 1. Types of Polymorphism

```java
// 1. Compile-time Polymorphism (Method Overloading)
public class Calculator {
    public int add(int a, int b) { return a + b; }
    public double add(double a, double b) { return a + b; }
    public int add(int a, int b, int c) { return a + b + c; }
}

// 2. Runtime Polymorphism (Method Overriding)
Shape shape = new Circle(); // Upcasting
shape.draw(); // Circle's draw() method called at runtime
```

### 2. Dynamic Method Dispatch

```java
// JVM determines which method to call at runtime based on actual object type
// Method resolution hierarchy:
// 1. Exact type match
// 2. Superclass method
// 3. Interface default method
```

### 3. Casting và Type Checking

```java
// Upcasting (implicit): Child -> Parent
// Downcasting (explicit): Parent -> Child (risky)
// instanceof operator để check type safely
if (shape instanceof Circle) {
    Circle circle = (Circle) shape; // Safe downcasting
}
```

## Bài tập thực hành:

### Bước 1: Tạo Shape hierarchy với đầy đủ polymorphic behavior

#### 1.1 Abstract Shape class

```java
public abstract class Shape {
    protected String color;
    protected Point2D position;
    protected final String id;
    protected static int shapeCounter = 0;

    // Constructor
    public Shape(String color, Point2D position) {
        this.id = "SHAPE_" + (++shapeCounter);
        this.color = color;
        this.position = position;
    }

    // Abstract methods - must be implemented
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    public abstract void draw(); // Simulation of drawing

    // Template method - uses abstract methods
    public final String getShapeInfo() {
        return String.format("Shape ID: %s, Type: %s, Color: %s, Area: %.2f, Perimeter: %.2f",
            id, getShapeType(), color, calculateArea(), calculatePerimeter());
    }

    // Concrete methods
    public void move(Point2D newPosition) {
        this.position = newPosition;
        System.out.println(getShapeType() + " moved to " + newPosition);
    }

    public void changeColor(String newColor) {
        String oldColor = this.color;
        this.color = newColor;
        System.out.println(getShapeType() + " color changed from " + oldColor + " to " + newColor);
    }

    // Virtual method to be overridden
    public String getShapeType() {
        return "Generic Shape";
    }

    // Object methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Shape shape = (Shape) obj;
        return Objects.equals(id, shape.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getShapeInfo();
    }
}
```

#### 1.2 Concrete Shape implementations

**Circle.java:**

```java
public class Circle extends Shape {
    private double radius;

    public Circle(String color, Point2D position, double radius) {
        super(color, position);
        setRadius(radius);
    }

    // Override abstract methods
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        System.out.printf("Drawing a %s circle with radius %.2f at position %s%n",
            color, radius, position);
    }

    @Override
    public String getShapeType() {
        return "Circle";
    }

    // Circle-specific methods
    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return 2 * radius;
    }

    // Override inherited method với specific behavior
    @Override
    public void move(Point2D newPosition) {
        super.move(newPosition); // Call parent implementation
        System.out.printf("Circle with radius %.2f is now at %s%n", radius, newPosition);
    }
}
```

**Rectangle.java:**

```java
public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String color, Point2D position, double width, double height) {
        super(color, position);
        setDimensions(width, height);
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void draw() {
        System.out.printf("Drawing a %s rectangle %.2fx%.2f at position %s%n",
            color, width, height, position);
    }

    @Override
    public String getShapeType() {
        return "Rectangle";
    }

    // Rectangle-specific methods
    public void setDimensions(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }

    public boolean isSquare() {
        return Math.abs(width - height) < 0.001; // Handle floating point precision
    }
}
```

**Triangle.java:**

```java
public class Triangle extends Shape {
    private double sideA, sideB, sideC;

    public Triangle(String color, Point2D position, double sideA, double sideB, double sideC) {
        super(color, position);
        setSides(sideA, sideB, sideC);
    }

    @Override
    public double calculateArea() {
        // Using Heron's formula
        double s = calculatePerimeter() / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public void draw() {
        System.out.printf("Drawing a %s triangle with sides %.2f, %.2f, %.2f at position %s%n",
            color, sideA, sideB, sideC, position);
    }

    @Override
    public String getShapeType() {
        return "Triangle";
    }

    public void setSides(double sideA, double sideB, double sideC) {
        if (!isValidTriangle(sideA, sideB, sideC)) {
            throw new IllegalArgumentException("Invalid triangle sides");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    private boolean isValidTriangle(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0 &&
               a + b > c && a + c > b && b + c > a;
    }

    public String getTriangleType() {
        if (sideA == sideB && sideB == sideC) return "Equilateral";
        if (sideA == sideB || sideB == sideC || sideA == sideC) return "Isosceles";
        return "Scalene";
    }
}
```

### Bước 2: Calculator với Method Overloading

#### 2.1 Enhanced MathOperations class

```java
public class MathOperations {
    // Method Overloading examples

    // Addition overloads
    public int add(int a, int b) {
        System.out.println("Adding two integers");
        return a + b;
    }

    public double add(double a, double b) {
        System.out.println("Adding two doubles");
        return a + b;
    }

    public int add(int a, int b, int c) {
        System.out.println("Adding three integers");
        return a + b + c;
    }

    public String add(String a, String b) {
        System.out.println("Concatenating two strings");
        return a + b;
    }

    // Array/varargs overloads
    public int add(int... numbers) {
        System.out.println("Adding array of integers");
        return Arrays.stream(numbers).sum();
    }

    // Generic method
    public <T extends Number> double add(T a, T b) {
        System.out.println("Adding two numbers (generic)");
        return a.doubleValue() + b.doubleValue();
    }

    // Multiplication overloads with different parameter types
    public int multiply(int a, int b) {
        return a * b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public long multiply(long a, long b) {
        return a * b;
    }

    // Matrix multiplication
    public int[][] multiply(int[][] matrix1, int[][] matrix2) {
        // Implementation for matrix multiplication
        // Demonstrates overloading with complex types
    }

    // Demonstrate method resolution
    public void demonstrateResolution() {
        System.out.println("=== Method Resolution Demo ===");

        // Exact match
        add(5, 10);           // Calls add(int, int)
        add(5.0, 10.0);       // Calls add(double, double)

        // Widening conversion
        add(5, 10.0);         // Calls add(double, double) - int widened to double

        // Varargs
        add(1, 2, 3, 4, 5);   // Calls add(int...)

        // Generic method
        add(Integer.valueOf(5), Double.valueOf(10.0)); // Calls generic add()
    }
}
```

### Bước 3: Polymorphism Demonstration Classes

#### 3.1 ShapeManager class

```java
public class ShapeManager {
    private List<Shape> shapes;

    public ShapeManager() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        System.out.println("Added " + shape.getShapeType() + " to collection");
    }

    // Polymorphic method calls
    public void drawAllShapes() {
        System.out.println("\n=== Drawing All Shapes ===");
        for (Shape shape : shapes) {
            shape.draw(); // Polymorphic call - actual method depends on object type
        }
    }

    public void displayShapeInfo() {
        System.out.println("\n=== Shape Information ===");
        for (Shape shape : shapes) {
            System.out.println(shape.toString()); // Polymorphic toString()
        }
    }

    public double calculateTotalArea() {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.calculateArea(); // Polymorphic calculation
        }
        return total;
    }

    public void moveAllShapes(Point2D offset) {
        for (Shape shape : shapes) {
            Point2D currentPos = shape.position;
            Point2D newPos = new Point2D(currentPos.getX() + offset.getX(),
                                       currentPos.getY() + offset.getY());
            shape.move(newPos); // Polymorphic move
        }
    }

    // Type-specific operations using instanceof
    public void performSpecificOperations() {
        System.out.println("\n=== Type-Specific Operations ===");
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                Circle circle = (Circle) shape; // Downcasting
                System.out.println("Circle diameter: " + circle.getDiameter());

            } else if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                System.out.println("Rectangle is square: " + rectangle.isSquare());

            } else if (shape instanceof Triangle) {
                Triangle triangle = (Triangle) shape;
                System.out.println("Triangle type: " + triangle.getTriangleType());
            }
        }
    }

    // Filter shapes by type
    public <T extends Shape> List<T> getShapesByType(Class<T> shapeType) {
        List<T> result = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shapeType.isInstance(shape)) {
                result.add(shapeType.cast(shape)); // Safe casting
            }
        }
        return result;
    }

    // Statistics
    public void printStatistics() {
        Map<String, Integer> typeCount = new HashMap<>();
        double totalArea = 0;

        for (Shape shape : shapes) {
            String type = shape.getShapeType();
            typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
            totalArea += shape.calculateArea();
        }

        System.out.println("\n=== Shape Statistics ===");
        typeCount.forEach((type, count) ->
            System.out.println(type + ": " + count + " shapes"));
        System.out.printf("Total area: %.2f%n", totalArea);
    }
}
```

#### 3.2 PolymorphismDemo class

```java
public class PolymorphismDemo {
    public static void main(String[] args) {
        demonstrateMethodOverloading();
        demonstrateRuntimePolymorphism();
        demonstrateShapePolymorphism();
        demonstrateCastingAndTypeChecking();
    }

    private static void demonstrateMethodOverloading() {
        System.out.println("========== METHOD OVERLOADING ==========");
        MathOperations math = new MathOperations();

        // Different method calls resolved at compile time
        System.out.println("5 + 10 = " + math.add(5, 10));
        System.out.println("5.5 + 10.5 = " + math.add(5.5, 10.5));
        System.out.println("5 + 10 + 15 = " + math.add(5, 10, 15));
        System.out.println("Hello + World = " + math.add("Hello", "World"));
        System.out.println("Sum of array = " + math.add(1, 2, 3, 4, 5));

        math.demonstrateResolution();
    }

    private static void demonstrateRuntimePolymorphism() {
        System.out.println("\n========== RUNTIME POLYMORPHISM ==========");

        // Array of Shape references pointing to different object types
        Shape[] shapes = {
            new Circle("Red", new Point2D(0, 0), 5),
            new Rectangle("Blue", new Point2D(10, 10), 4, 6),
            new Triangle("Green", new Point2D(20, 20), 3, 4, 5)
        };

        // Polymorphic method calls
        for (Shape shape : shapes) {
            System.out.println("\nShape Type: " + shape.getShapeType());
            shape.draw(); // Different draw() method called based on actual object type
            System.out.println("Area: " + shape.calculateArea());
            System.out.println("Perimeter: " + shape.calculatePerimeter());
        }
    }

    private static void demonstrateShapePolymorphism() {
        System.out.println("\n========== SHAPE MANAGEMENT ==========");

        ShapeManager manager = new ShapeManager();

        // Add different shapes
        manager.addShape(new Circle("Yellow", new Point2D(0, 0), 3));
        manager.addShape(new Rectangle("Purple", new Point2D(5, 5), 8, 4));
        manager.addShape(new Triangle("Orange", new Point2D(15, 15), 6, 8, 10));
        manager.addShape(new Circle("Pink", new Point2D(25, 25), 7));

        // Polymorphic operations
        manager.drawAllShapes();
        manager.displayShapeInfo();

        System.out.printf("Total area of all shapes: %.2f%n",
                         manager.calculateTotalArea());

        manager.moveAllShapes(new Point2D(10, 10));
        manager.performSpecificOperations();
        manager.printStatistics();
    }

    private static void demonstrateCastingAndTypeChecking() {
        System.out.println("\n========== CASTING & TYPE CHECKING ==========");

        List<Shape> shapes = Arrays.asList(
            new Circle("Black", new Point2D(0, 0), 4),
            new Rectangle("White", new Point2D(0, 0), 5, 5),
            new Triangle("Gray", new Point2D(0, 0), 5, 12, 13)
        );

        for (Shape shape : shapes) {
            System.out.println("\nProcessing: " + shape.getShapeType());

            // Type checking with instanceof
            if (shape instanceof Circle) {
                System.out.println("This is a Circle");
                Circle circle = (Circle) shape; // Safe downcasting
                System.out.println("Radius: " + circle.getRadius());
                System.out.println("Diameter: " + circle.getDiameter());

            } else if (shape instanceof Rectangle) {
                System.out.println("This is a Rectangle");
                Rectangle rect = (Rectangle) shape;
                System.out.println("Width: " + rect.getWidth());
                System.out.println("Height: " + rect.getHeight());
                System.out.println("Is Square: " + rect.isSquare());

            } else if (shape instanceof Triangle) {
                System.out.println("This is a Triangle");
                Triangle triangle = (Triangle) shape;
                System.out.println("Triangle Type: " + triangle.getTriangleType());
            }

            // Demonstrate unsafe casting (commented out)
            /*
            try {
                Circle circle = (Circle) shape; // This will fail for non-Circle shapes
            } catch (ClassCastException e) {
                System.out.println("Cannot cast " + shape.getShapeType() + " to Circle");
            }
            */
        }
    }
}
```

### Bước 4: Testing

#### 4.1 PolymorphismTest class

```java
public class PolymorphismTest {
    @Test
    public void testMethodOverloading() {
        MathOperations math = new MathOperations();

        // Test different overloaded methods
        assertEquals(15, math.add(5, 10));
        assertEquals(15.5, math.add(5.5, 10.0), 0.001);
        assertEquals(30, math.add(5, 10, 15));
        assertEquals("HelloWorld", math.add("Hello", "World"));
        assertEquals(15, math.add(1, 2, 3, 4, 5));
    }

    @Test
    public void testRuntimePolymorphism() {
        Shape circle = new Circle("Red", new Point2D(0, 0), 5);
        Shape rectangle = new Rectangle("Blue", new Point2D(0, 0), 4, 6);

        // Test polymorphic method calls
        assertEquals("Circle", circle.getShapeType());
        assertEquals("Rectangle", rectangle.getShapeType());

        assertTrue(circle.calculateArea() > 75); // π * 5² ≈ 78.54
        assertEquals(24, rectangle.calculateArea(), 0.001);
    }

    @Test
    public void testInstanceofAndCasting() {
        Shape shape = new Circle("Red", new Point2D(0, 0), 5);

        assertTrue(shape instanceof Circle);
        assertTrue(shape instanceof Shape);
        assertFalse(shape instanceof Rectangle);

        // Safe casting
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            assertEquals(5, circle.getRadius(), 0.001);
        }
    }

    @Test
    public void testPolymorphicCollection() {
        ShapeManager manager = new ShapeManager();
        manager.addShape(new Circle("Red", new Point2D(0, 0), 3));
        manager.addShape(new Rectangle("Blue", new Point2D(0, 0), 4, 5));

        double totalArea = manager.calculateTotalArea();
        assertTrue(totalArea > 0);
    }
}
```

## Đánh giá criteria:

1. **Method Overloading (20%)**

   - Correct overload implementations
   - Understanding of method resolution
   - Appropriate use cases

2. **Runtime Polymorphism (30%)**

   - Proper method overriding
   - Dynamic method dispatch understanding
   - Polymorphic behavior implementation

3. **Type System (25%)**

   - Correct use của instanceof
   - Safe casting practices
   - Understanding của upcasting/downcasting

4. **Code Design (15%)**

   - Clean polymorphic interfaces
   - Appropriate abstractions
   - Good encapsulation

5. **Testing (10%)**
   - Comprehensive polymorphism tests
   - Edge case coverage

## Expected deliverables:

1. `Point2D.java` - Utility class for coordinates
2. `Shape.java` - Abstract base class
3. `Circle.java`, `Rectangle.java`, `Triangle.java` - Concrete shapes
4. `MathOperations.java` - Method overloading examples
5. `ShapeManager.java` - Polymorphism demonstration
6. `PolymorphismDemo.java` - Main demonstration
7. `PolymorphismTest.java` - Comprehensive tests

## Timeline: 4-5 days

- Day 1: Lý thuyết và Shape hierarchy setup
- Day 2: Concrete shape implementations
- Day 3: MathOperations và method overloading
- Day 4: ShapeManager và polymorphism demo
- Day 5: Testing và refinement
