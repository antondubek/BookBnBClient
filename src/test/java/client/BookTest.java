package client;

import client.Book;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests for the Book class. Testing that all fields are assigned correctly when
 * creating a new book object. Also testing getters and setters methods.
 */
public class BookTest {

    private String ISBN = "9788532530837";
    private String author = "J. K. Rowling";
    private String title = "Harry Potter and the Half-Blood Prince";
    private boolean availability = true;
    private String copyID = "1";
    private boolean isLoaned = false;
    private String loanLength = "7";
    private Book testBook = new Book(ISBN, title, author, availability, copyID, isLoaned, loanLength);

    @Test
    public void createBook() {
        assertEquals(this.testBook.ISBN, "9788532530837");
        assertEquals(this.testBook.author, "J. K. Rowling");
        assertEquals(this.testBook.title, "Harry Potter and the Half-Blood Prince");
        assertEquals(this.testBook.availability, true);
        assertEquals(this.testBook.copyID, "1");
        assertFalse(this.testBook.isLoaned);
    }

    @Test
    public void getAttributes() {
        assertEquals(this.testBook.getISBN(), "9788532530837");
        assertEquals(this.testBook.getAuthor(), "J. K. Rowling");
        assertEquals(this.testBook.getTitle(), "Harry Potter and the Half-Blood Prince");
        assertTrue(this.testBook.getAvailability());
        assertEquals(this.testBook.getCopyID(), "1");
    }

    @Test
    public void setAvailability() {
        testBook.setAvailability(false);
        assertEquals(this.testBook.availability, false);
    }

    @Test
    public void TOString() {
        assertEquals(this.testBook.toString(), title);
    }
}
