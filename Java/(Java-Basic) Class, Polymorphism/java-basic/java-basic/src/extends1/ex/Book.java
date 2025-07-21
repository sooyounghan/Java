package extends1.ex;

public class Book extends Item {
    private String author;
    private String isbn;

    public Book(String title, int price, String author, String isbn) {
        super(title, price);
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("- 저자 : " + author +", isbn : " + isbn);
    }
}
