package Exercises.Interface.BaiTap8_1;

public abstract class Book {
    protected String title;
    protected String author;
    protected String isbn;
    protected boolean available;

    public Book(String title, String author, String isbn, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    public abstract void getType();
}
