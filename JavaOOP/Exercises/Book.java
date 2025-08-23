package Exercises;

public class Book {
    private String title;
    private String author;
    private String pages;
    private String isOpen;

    public Book(String title, String author, String pages, String isOpen) {
        this.title = title;
        this.author = author;
        this.pages = pages;

    }

    // is open
    public void open() {
        System.out.println("Book shop is Open");
    }

    public void close() {
        System.out.println("Book shop is close");
    }

    public void getInfo() {
        System.out.println(
                "Day la sach co ten " + this.title + ", Tac gia: " + this.author + ", So trang: " + this.pages);
    }

    public static void main(String[] agrs) {
        Book book = new Book(null, null, null, null);
    }
}
