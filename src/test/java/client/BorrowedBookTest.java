package client;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BorrowedBookTest {
    public BorrowedBook testBorrowedBook;
    public String ISBN = "1112223334445";
    private String title = "Test";
    private String status = "pending";
    private String lenderName = "Sam";
    private String requestNo = "22";
    private String author = "Dan Brown";
    private String startDate = "23-04-2019";
    private String endDate = "25-04-2019";
    private String copyID = "9998887776665";
    private String loanLength = "7";


    @Before
    public void Setup(){
        testBorrowedBook = new BorrowedBook(ISBN, title, author, status, lenderName, startDate, endDate, requestNo, copyID);
    }

    @Test
    public void testConstructor(){
        assertEquals(testBorrowedBook.getISBN(), ISBN);
        assertEquals(testBorrowedBook.getTitle(), title);
        assertEquals(testBorrowedBook.getAuthor(), author);
        assertEquals(testBorrowedBook.getStatus(), status);
        assertEquals(testBorrowedBook.getLenderName(), lenderName);
        assertEquals(testBorrowedBook.getStartDate(), startDate);
        assertEquals(testBorrowedBook.getEndDate(), endDate);
        assertEquals(testBorrowedBook.getRequestNo(), requestNo);
        assertEquals(testBorrowedBook.getCopyID(), copyID);
    }

    @Test
    public void testSettersAndGetters() {

        testBorrowedBook.setStatus("Available");
        assertEquals(testBorrowedBook.getStatus(), "Available");

    }
}