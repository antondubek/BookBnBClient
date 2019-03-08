/**
 * Simple book object which stores the information needed for a book
 */
public class Book {

    private String ISBN;
    private String author;
    private String title;

    /**
     * Book Constructor
     * @param ISBN ISBN number of the book
     * @param author Author of the book
     * @param title Title of the book
     */
    public Book(String ISBN, String author, String title) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }
}
