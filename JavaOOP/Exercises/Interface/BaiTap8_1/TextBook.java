package Exercises.Interface.BaiTap8_1;

public class TextBook extends Book {
    public TextBook(String title, String author, String isbn, boolean available) {
        super(title, author, isbn, available);
    }

    @Override
    public void getType() {
        System.out.println("Type: Textbook");
    }

}
