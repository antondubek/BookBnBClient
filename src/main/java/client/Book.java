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
    public boolean isLoaned;
    public String loanLength;

    /**
     * Book Constructor
     *
     * @param ISBN ISBN number of the book
     * @param title Title of the book
     * @param author Author of the book
     *
     */
    public Book(String ISBN, String title, String author) {
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;

    }

    /**
     * Overloaded Book Constructor
     *
     * @param ISBN ISBN number of the book
     * @param title Title of the book
     * @param author Author of the book
     * @param availability Whether the book is available or not
     * @param copyID The copyID of the book
     * @param isLoaned Whether the book is loaned or not
     * @param loanLength Length of loan the owner has set
     *
     */
    public Book(String ISBN, String title, String author, boolean availability,
            String copyID, boolean isLoaned, String loanLength) {
        this(ISBN, title, author);
        this.availability = availability;
        this.copyID = copyID;
        this.isLoaned = isLoaned;
        this.loanLength = loanLength;
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

    public String getCopyID() {
        return copyID;
    }
    public String getLoanLength(){
        return loanLength;
    }

    public void setLoanLength(String loanLength) {
        this.loanLength = loanLength;
    }
    

    public void setAvailability(boolean availability) {

        this.availability = availability;
    }
}
