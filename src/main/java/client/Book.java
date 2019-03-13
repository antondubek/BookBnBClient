package client;

/**
 * Simple book object which stores the information needed for a book
 */
public class Book {

    public String ISBN;
    public String author;
    public String title;
    public boolean availability;

    /**
     * client.Book Constructor
     * @param ISBN ISBN number of the book
     * @param author Author of the book
     * @param title Title of the book
     */
    public Book(String ISBN, String author, String title, boolean availability) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.availability = availability;

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

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {

        this.availability = availability;
    }
}
