# Bài tập: Hệ thống quản lý thư viện đơn giản

## Mục tiêu:

Tạo một hệ thống quản lý thư viện sử dụng các nguyên lý OOP: Abstraction, Inheritance, Polymorphism, Encapsulation

## Kiến thức cần nắm vững trước khi làm:

- Abstract classes và abstract methods
- Inheritance với từ khóa `extends` và `super`
- Method overriding với `@Override`
- Exception handling với `throw` và `try-catch`
- Polymorphism và type casting với `instanceof`

---

## Hướng dẫn chi tiết:

### Bước 1: Tạo Abstract Class LibraryItem

**Vai trò**: Class cha chung cho tất cả items trong thư viện (sách, DVD, tạp chí)

**Cần implement:**

#### 1.1 Fields và Constructor:

- **Fields**: `id` (String), `title` (String), `publicationYear` (int), `isAvailable` (boolean)
- **Access modifier**: Sử dụng `protected` cho fields để subclass có thể truy cập
- **Constructor**: Nhận 3 parameters (id, title, publicationYear), gán `isAvailable = true` mặc định
- **Getters**: Tạo getter methods cho tất cả fields

**Lưu ý quan trọng**:

- Tại sao dùng `protected`? Để subclass có thể truy cập trực tiếp fields
- Tại sao `isAvailable = true` mặc định? Item mới thêm vào thường có sẵn để mượn

#### 1.2 Abstract Methods:

- `public abstract double calculateLateFee(int daysLate)`:

  - **Mục đích**: Tính phí trễ hạn dựa trên số ngày trễ
  - **Tại sao abstract**: Mỗi loại item có cách tính phí khác nhau
  - **Parameter**: `daysLate` - số ngày trễ hạn (có thể âm, 0, hoặc dương)

- `public abstract String getItemDetails()`:
  - **Mục đích**: Trả về thông tin chi tiết của item
  - **Tại sao abstract**: Mỗi loại item có thông tin khác nhau cần hiển thị

#### 1.3 Concrete Methods:

- `public boolean borrowItem()`:

  - **Logic**: Kiểm tra `isAvailable`, nếu `false` thì `throw IllegalStateException`
  - **Nếu available**: Set `isAvailable = false`, return `true`
  - **Exception message**: "Item is already borrowed"

- `public boolean returnItem()`:
  - **Logic**: Kiểm tra `isAvailable`, nếu `true` thì `throw IllegalStateException`
  - **Nếu borrowed**: Set `isAvailable = true`, return `true`
  - **Exception message**: "Item is not currently borrowed"

#### 1.4 Override Object Methods:

- `toString()`: Format "ID: [id], Title: [title], Year: [year], Available: [Yes/No]"
- `equals()`: So sánh dựa trên `id` (2 items cùng ID là giống nhau)
- `hashCode()`: Dựa trên `id.hashCode()`

**Câu hỏi tự kiểm tra**:

- Tại sao không thể tạo object trực tiếp từ abstract class?
- Khi nào nên ném exception thay vì return false?

### Bước 2: Tạo Book Class

**Vai trò**: Concrete class kế thừa từ LibraryItem, đại diện cho sách trong thư viện

**Cần implement:**

#### 2.1 Additional Fields và Constructor:

- **Additional fields**: `author` (String), `isbn` (String), `pageCount` (int)
- **Access modifier**: Sử dụng `private` cho specific fields
- **Constructor**: Nhận 6 parameters (id, title, publicationYear, author, isbn, pageCount)
- **Super call**: Phải gọi `super(id, title, publicationYear)` đầu tiên
- **Getters**: Tạo getter methods cho các fields mới

**Lưu ý quan trọng**:

- Tại sao dùng `private` thay vì `protected`? Đây là thông tin riêng của Book
- Constructor phải gọi `super()` trước khi làm gì khác

#### 2.2 Implementation Abstract Methods:

- `calculateLateFee(int daysLate)`:

  - **Logic**: Nếu `daysLate <= 0` return `0.0`
  - **Rate**: $0.50 per day cho sách (tài liệu học tập nên phí thấp)
  - **Formula**: `daysLate * 0.5`
  - **Return type**: `double`

- `getItemDetails()`:
  - **Format**: "Book - Title: [title], Author: [author], ISBN: [isbn], Pages: [pageCount], Year: [year]"
  - **Sử dụng**: `String.format()` hoặc string concatenation
  - **Mục đích**: Hiển thị đầy đủ thông tin của sách

**Câu hỏi tự kiểm tra**:

- Tại sao phí sách thấp hơn DVD?
- Method nào được inherit từ parent class không cần override?

### Bước 3: Tạo DVD Class

**Vai trò**: Concrete class đại diện cho đĩa DVD trong thư viện

**Cần implement:**

#### 3.1 Additional Fields và Constructor:

- **Additional fields**: `director` (String), `duration` (int - phút), `rating` (String)
- **Constructor**: Nhận 6 parameters (id, title, publicationYear, director, duration, rating)
- **Validation ideas**: Duration > 0, rating không null/empty

#### 3.2 Implementation Abstract Methods:

- `calculateLateFee(int daysLate)`:

  - **Rate**: $1.00 per day cho DVD (giá trị cao hơn sách)
  - **Logic**: Tương tự Book nhưng khác rate

- `getItemDetails()`:
  - **Format**: "DVD - Title: [title], Director: [director], Duration: [duration] mins, Rating: [rating], Year: [year]"

**Câu hỏi tự kiểm tra**:

- Tại sao DVD có phí cao hơn sách?
- Làm thế nào để validate duration > 0?

### Bước 4: Tạo Magazine Class

**Vai trò**: Concrete class đại diện cho tạp chí trong thư viện

**Cần implement:**

#### 4.1 Additional Fields và Constructor:

- **Additional fields**: `issueNumber` (int), `publisher` (String)
- **Constructor**: Nhận 5 parameters (id, title, publicationYear, issueNumber, publisher)

#### 4.2 Implementation Abstract Methods:

- `calculateLateFee(int daysLate)`:

  - **Rate**: $0.25 per day cho magazine (rẻ nhất vì thường cũ nhanh)

- `getItemDetails()`:
  - **Format**: "Magazine - Title: [title], Issue: [issueNumber], Publisher: [publisher], Year: [year]"

### Bước 5: Tạo LibrarySystem Class (Management Class)

**Vai trò**: Class quản lý tất cả operations của thư viện

**Cần implement:**

#### 5.1 Fields và Constructor:

- **Field**: `List<LibraryItem> items` - danh sách tất cả items
- **Constructor**: Khởi tạo `items = new ArrayList<>()`
- **Tại sao List**: Cần lưu trữ nhiều items và có thể thêm/xóa

#### 5.2 Core Methods:

- `addItem(LibraryItem item)`:

  - **Mục đích**: Thêm item vào library
  - **Parameter**: LibraryItem (polymorphism - có thể là Book, DVD, Magazine)
  - **Action**: `items.add(item)` và print confirmation

- `findItemById(String id)`:

  - **Mục đích**: Tìm item theo ID
  - **Logic**: Loop through `items`, so sánh `item.getId()` với parameter
  - **Return**: LibraryItem nếu tìm thấy, `null` nếu không

- `displayAllItems()`:

  - **Mục đích**: Hiển thị tất cả items
  - **Logic**: Loop và gọi `item.toString()` cho mỗi item (polymorphism)

- `displayAvailableItems()`:
  - **Mục đích**: Hiển thị chỉ items có sẵn
  - **Logic**: Loop, check `item.isAvailable()`, nếu true thì gọi `item.getItemDetails()`

#### 5.3 Action Methods:

- `borrowItem(String id)`:

  - **Step 1**: Gọi `findItemById(id)`
  - **Step 2**: Nếu null, print "Item not found", return false
  - **Step 3**: Try-catch để gọi `item.borrowItem()`
  - **Success**: Print success message, return true
  - **Exception**: Print error message, return false

- `returnItem(String id, int daysLate)`:
  - **Step 1**: Tương tự borrowItem để find item
  - **Step 2**: Try-catch để gọi `item.returnItem()`
  - **Step 3**: Tính và hiển thị late fee bằng `item.calculateLateFee(daysLate)`
  - **Format**: "Late fee: $[amount]" (chỉ hiển thị nếu > 0)

**Câu hỏi tự kiểm tra**:

- Tại sao dùng List thay vì Array?
- Polymorphism được thể hiện ở đâu trong class này?

### Bước 6: Tạo LibraryDemo Class (Test Class)

**Vai trò**: Demonstrate và test tất cả functionality

**Cần implement:**

#### 6.1 Main Method Structure:

- **Section 1**: Tạo LibrarySystem và add various items
- **Section 2**: Display all items và available items
- **Section 3**: Demo borrowing operations (success và failure cases)
- **Section 4**: Demo returning với late fees
- **Section 5**: Demonstrate polymorphism

#### 6.2 Test Data Setup:

- **Books**: Ít nhất 2 books với thông tin khác nhau
- **DVDs**: Ít nhất 2 DVDs với thông tin khác nhau
- **Magazines**: Ít nhất 2 magazines với thông tin khác nhau
- **Variety**: Đảm bảo có các loại items để test polymorphism

#### 6.3 Test Scenarios:

- **Normal borrowing**: Mượn item có sẵn
- **Failed borrowing**: Thử mượn item đã được mượn
- **Normal returning**: Trả item với/không có late fee
- **Failed returning**: Thử trả item chưa mượn
- **Polymorphism demo**: Array/List chứa mixed types, gọi methods

#### 6.4 Expected Output Examples:

- "Added: Book - Title: Java Programming, Author: ..."
- "Successfully borrowed: Java Programming"
- "Cannot borrow item: Item is already borrowed"
- "Successfully returned: Java Programming"
- "Late fee: $2.50"

**Câu hỏi tự kiểm tra**:

- Làm thế nào để test exception handling?
- Polymorphism được demonstrate như thế nào?

---

## Tổng kết và Đánh giá:

### Learning Objectives Check:

1. **Abstraction**: LibraryItem abstract class với abstract methods ✓
2. **Inheritance**: Book, DVD, Magazine extends LibraryItem ✓
3. **Polymorphism**: LibraryItem references pointing to different concrete types ✓
4. **Encapsulation**: Private fields với public methods ✓

### Common Mistakes to Avoid:

- Quên gọi `super()` trong constructor
- Không handle exceptions properly trong LibrarySystem
- Không validate input parameters
- Không demonstrate polymorphism clearly

### Extension Ideas (Optional):

- Thêm search by title/author methods
- Implement Member class với borrowing limits
- Add due dates và automatic late fee calculation
- Create ItemType enum để categorize items

### Files cần tạo:

1. `LibraryItem.java` - Abstract class
2. `Book.java` - Concrete class
3. `DVD.java` - Concrete class
4. `Magazine.java` - Concrete class
5. `LibrarySystem.java` - Management class
6. `LibraryDemo.java` - Test/Demo class

### Timeline đề xuất:

- **Day 1**: LibraryItem abstract class
- **Day 2**: Book và DVD classes
- **Day 3**: Magazine và LibrarySystem classes
- **Day 4**: LibraryDemo và testing
- **Day 5**: Review và improvements
