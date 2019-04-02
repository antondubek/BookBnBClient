package client;

/**
 * Simple book object which stores the information needed for a book
 */
public class Book {

    public String ISBN;
    public String author;
    public String title;
    public boolean availability;
    public String copyID;

    /**
     * client.Book Constructor
     * @param ISBN ISBN number of the book
     * @param title Title of the book
     * @param author Author of the book
     *
     */
    public Book(String ISBN, String title, String author, boolean availability, String copyID) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.availability = availability;
        this.copyID = copyID;

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
    
    public String copyID() {
        return copyID;
    }

    public void setAvailability(boolean availability) {

        this.availability = availability;
    }
}
