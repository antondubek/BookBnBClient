import static org.junit.Assert.*;
import org.junit.Test;

import client.Book;
public class BookTest {

    // Creating a Book object
    private String ISBN = "9788532530837";
    private String author = "J. K. Rowling";
    private String title = "Harry Potter and the Half-Blood Prince";
    private boolean availability = true;

    private Book testBook = new Book(ISBN, author, title, availability);

    @Test
    public void createBook() {
        assertEquals(this.testBook.ISBN, "9788532530837");
        assertEquals(this.testBook.author, "J. K. Rowling");
        assertEquals(this.testBook.title, "Harry Potter and the Half-Blood Prince");
        assertEquals(this.testBook.availability, true);
    }

    @Test
    public void getAttributes() {
        assertEquals(this.testBook.getISBN(), "9788532530837");
        assertEquals(this.testBook.getAuthor(), "J. K. Rowling");
        assertEquals(this.testBook.getTitle(), "Harry Potter and the Half-Blood Prince");
        assertTrue(this.testBook.getAvailability());
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