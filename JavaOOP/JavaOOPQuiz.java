import java.util.Scanner;

// ===========================================
// JAVA OOP QUIZ - KIỂM TRA KIẾN THỨC
// ===========================================

public class JavaOOPQuiz {
    private static Scanner scanner = new Scanner(System.in);
    private static int score = 0;
    private static int totalQuestions = 0;

    public static void main(String[] args) {
        System.out.println("=== JAVA OOP KNOWLEDGE QUIZ ===");
        System.out.println("Trả lời các câu hỏi bằng cách nhập số tương ứng (1, 2, 3, 4)");
        System.out.println("Nhấn Enter để bắt đầu...");
        scanner.nextLine();

        // Encapsulation Questions
        encapsulationQuiz();

        // Inheritance Questions
        inheritanceQuiz();

        // Polymorphism Questions
        polymorphismQuiz();

        // Abstraction Questions
        abstractionQuiz();

        // Interface Questions
        interfaceQuiz();

        // Show final results
        showResults();
    }

    // ===========================================
    // ENCAPSULATION QUIZ
    // ===========================================
    private static void encapsulationQuiz() {
        System.out.println("\n=== PHẦN 1: ENCAPSULATION (Đóng gói) ===");

        // Question 1
        System.out.println("\nCâu 1: Access modifier nào được sử dụng để ẩn dữ liệu trong Encapsulation?");
        System.out.println("1. public");
        System.out.println("2. private");
        System.out.println("3. protected");
        System.out.println("4. default");
        checkAnswer(2, "private là access modifier để ẩn dữ liệu");

        // Question 2
        System.out.println("\nCâu 2: Getter method có tác dụng gì?");
        System.out.println("1. Thay đổi giá trị attribute");
        System.out.println("2. Truy cập giá trị attribute");
        System.out.println("3. Xóa attribute");
        System.out.println("4. Tạo attribute mới");
        checkAnswer(2, "Getter method dùng để truy cập (đọc) giá trị attribute");

        // Question 3
        System.out.println("\nCâu 3: Tại sao nên sử dụng validation trong setter method?");
        System.out.println("1. Để code chạy nhanh hơn");
        System.out.println("2. Để bảo vệ dữ liệu khỏi giá trị không hợp lệ");
        System.out.println("3. Để tiết kiệm memory");
        System.out.println("4. Để code ngắn hơn");
        checkAnswer(2, "Validation trong setter giúp bảo vệ dữ liệu khỏi giá trị không hợp lệ");
    }

    // ===========================================
    // INHERITANCE QUIZ
    // ===========================================
    private static void inheritanceQuiz() {
        System.out.println("\n=== PHẦN 2: INHERITANCE (Kế thừa) ===");

        // Question 1
        System.out.println("\nCâu 4: Từ khóa nào được sử dụng để kế thừa trong Java?");
        System.out.println("1. implements");
        System.out.println("2. inherits");
        System.out.println("3. extends");
        System.out.println("4. super");
        checkAnswer(3, "extends được sử dụng để kế thừa class");

        // Question 2
        System.out.println("\nCâu 5: super() được sử dụng để làm gì?");
        System.out.println("1. Gọi constructor của class con");
        System.out.println("2. Gọi constructor của class cha");
        System.out.println("3. Tạo object mới");
        System.out.println("4. Xóa object");
        checkAnswer(2, "super() gọi constructor của class cha");

        // Question 3
        System.out.println("\nCâu 6: @Override annotation có tác dụng gì?");
        System.out.println("1. Tạo method mới");
        System.out.println("2. Xóa method cũ");
        System.out.println("3. Ghi đè method của class cha");
        System.out.println("4. Copy method từ class khác");
        checkAnswer(3, "@Override đánh dấu việc ghi đè method của class cha");
    }

    // ===========================================
    // POLYMORPHISM QUIZ
    // ===========================================
    private static void polymorphismQuiz() {
        System.out.println("\n=== PHẦN 3: POLYMORPHISM (Đa hình) ===");

        // Question 1
        System.out.println("\nCâu 7: Method Overloading là gì?");
        System.out.println("1. Nhiều method cùng tên, khác tham số");
        System.out.println("2. Nhiều method khác tên, cùng tham số");
        System.out.println("3. Một method có nhiều return type");
        System.out.println("4. Method được gọi nhiều lần");
        checkAnswer(1, "Method Overloading: nhiều method cùng tên nhưng khác tham số");

        // Question 2
        System.out.println("\nCâu 8: Runtime Polymorphism xảy ra khi nào?");
        System.out.println("1. Compile time");
        System.out.println("2. Runtime khi gọi method override");
        System.out.println("3. Khi tạo object");
        System.out.println("4. Khi khai báo biến");
        checkAnswer(2, "Runtime Polymorphism xảy ra khi gọi method đã được override");

        // Question 3
        System.out.println("\nCâu 9: Điều kiện để Method Overloading?");
        System.out.println("1. Cùng tên method, khác số lượng hoặc kiểu tham số");
        System.out.println("2. Khác tên method");
        System.out.println("3. Cùng tên method, khác return type");
        System.out.println("4. Cùng tất cả thông tin");
        checkAnswer(1, "Method Overloading: cùng tên nhưng khác số lượng hoặc kiểu tham số");
    }

    // ===========================================
    // ABSTRACTION QUIZ
    // ===========================================
    private static void abstractionQuiz() {
        System.out.println("\n=== PHẦN 4: ABSTRACTION (Trừu tượng) ===");

        // Question 1
        System.out.println("\nCâu 10: Abstract class có thể được instantiate không?");
        System.out.println("1. Có, luôn luôn");
        System.out.println("2. Không, không bao giờ");
        System.out.println("3. Có, nhưng chỉ trong class con");
        System.out.println("4. Tùy thuộc vào abstract method");
        checkAnswer(2, "Abstract class không thể được instantiate trực tiếp");

        // Question 2
        System.out.println("\nCâu 11: Abstract method phải như thế nào?");
        System.out.println("1. Có implementation");
        System.out.println("2. Không có implementation");
        System.out.println("3. Có thể có hoặc không có implementation");
        System.out.println("4. Chỉ có return statement");
        checkAnswer(2, "Abstract method không có implementation (chỉ có signature)");

        // Question 3
        System.out.println("\nCâu 12: Class con của abstract class phải làm gì?");
        System.out.println("1. Kế thừa tất cả method");
        System.out.println("2. Override tất cả abstract method");
        System.out.println("3. Không cần làm gì");
        System.out.println("4. Chỉ override concrete method");
        checkAnswer(2, "Class con phải override tất cả abstract method của class cha");
    }

    // ===========================================
    // INTERFACE QUIZ
    // ===========================================
    private static void interfaceQuiz() {
        System.out.println("\n=== PHẦN 5: INTERFACE ===");

        // Question 1
        System.out.println("\nCâu 13: Từ khóa nào để implement interface?");
        System.out.println("1. extends");
        System.out.println("2. implements");
        System.out.println("3. interface");
        System.out.println("4. abstract");
        checkAnswer(2, "implements được sử dụng để implement interface");

        // Question 2
        System.out.println("\nCâu 14: Một class có thể implement bao nhiêu interface?");
        System.out.println("1. Chỉ 1");
        System.out.println("2. Tối đa 2");
        System.out.println("3. Tối đa 5");
        System.out.println("4. Không giới hạn");
        checkAnswer(4, "Một class có thể implement nhiều interface không giới hạn");

        // Question 3
        System.out.println("\nCâu 15: Interface method mặc định là gì? (Java 8 trước)");
        System.out.println("1. private");
        System.out.println("2. protected");
        System.out.println("3. public abstract");
        System.out.println("4. default");
        checkAnswer(3, "Interface method mặc định là public abstract (Java 8 trước)");
    }

    // ===========================================
    // UTILITY METHODS
    // ===========================================
    private static void checkAnswer(int correctAnswer, String explanation) {
        System.out.print("Câu trả lời của bạn: ");
        try {
            int userAnswer = scanner.nextInt();
            totalQuestions++;

            if (userAnswer == correctAnswer) {
                System.out.println("✅ ĐÚNG! " + explanation);
                score++;
            } else {
                System.out.println("❌ SAI! Đáp án đúng là: " + correctAnswer + " - " + explanation);
            }
        } catch (Exception e) {
            System.out.println("❌ Vui lòng nhập số từ 1-4!");
            scanner.nextLine(); // Clear buffer
            totalQuestions++;
        }

        // Pause before next question
        System.out.println("Nhấn Enter để tiếp tục...");
        scanner.nextLine(); // Clear buffer
        scanner.nextLine(); // Wait for Enter
    }

    private static void showResults() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           KẾT QUẢ CUỐI CÙNG");
        System.out.println("=".repeat(50));

        double percentage = (double) score / totalQuestions * 100;

        System.out.println("Số câu đúng: " + score + "/" + totalQuestions);
        System.out.println("Phần trăm: " + String.format("%.1f", percentage) + "%");

        // Grade evaluation
        if (percentage >= 90) {
            System.out.println("🏆 XẾP LOẠI: XUẤT SẮC!");
            System.out.println("Bạn đã nắm vững Java OOP!");
        } else if (percentage >= 80) {
            System.out.println("🥇 XẾP LOẠI: GIỎI!");
            System.out.println("Kiến thức của bạn rất tốt, cần ôn lại một ít!");
        } else if (percentage >= 70) {
            System.out.println("🥈 XẾP LOẠI: KHÁ!");
            System.out.println("Bạn cần ôn tập thêm để nắm vững hơn!");
        } else if (percentage >= 60) {
            System.out.println("🥉 XẾP LOẠI: TRUNG BÌNH!");
            System.out.println("Hãy đọc lại lý thuyết và làm thêm bài tập!");
        } else {
            System.out.println("📚 XẾP LOẠI: CẦN CỐ GẮNG!");
            System.out.println("Bạn cần học lại từ đầu và thực hành nhiều hơn!");
        }

        System.out.println("\n📖 GỢI Ý ÔN TẬP:");
        if (percentage < 80) {
            System.out.println("- Đọc lại file 'Java_OOP_Review_Guide.md'");
            System.out.println("- Chạy và phân tích code trong 'JavaOOPDemo.java'");
            System.out.println("- Thực hành viết code theo các bài tập");
            System.out.println("- Làm lại quiz này sau khi ôn tập");
        }

        System.out.println("\n🎯 KIẾN THỨC TRỌNG TÂM CẦN NHỚ:");
        System.out.println("1. Encapsulation: private attributes + getter/setter");
        System.out.println("2. Inheritance: extends + super() + @Override");
        System.out.println("3. Polymorphism: Overloading + Overriding");
        System.out.println("4. Abstraction: abstract class + abstract method");
        System.out.println("5. Interface: implements + multiple inheritance");

        System.out.println("\n" + "=".repeat(50));
        System.out.println("Cảm ơn bạn đã tham gia quiz! 🚀");
    }
}
