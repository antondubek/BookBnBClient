package client.Controller;

import client.Book;
import client.BorrowedBook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControllerBookTest {
    String ISBN = "1234567890";
    String title = "Hello from JUNIT";
    String author = "Who knows";
    String status = "Available";
    String lenderName = "Jimbo";
    String startDate = "2014-13-24";
    String endDate = "2019-04-38";
    String requestNo = "42";
    String copyID = "345";
    boolean availability = false;
    boolean isLoaned = true;
    String loanLength = "22";

    JSONObject JSONBorrowedBook;
    JSONObject JSONBook;

    @Before
    public void before(){
        BorrowedBook borrowedBook = new BorrowedBook(ISBN, title,author,status,lenderName,startDate,endDate,requestNo,copyID);
        JSONBorrowedBook = new JSONObject();
        JSONBorrowedBook.put("ISBN", ISBN);
        JSONBorrowedBook.put("title", title);
        JSONBorrowedBook.put("author", author);
        JSONBorrowedBook.put("status", status);
        JSONBorrowedBook.put("name", lenderName);
        JSONBorrowedBook.put("startDate", startDate);
        JSONBorrowedBook.put("endDate", endDate);
        JSONBorrowedBook.put("requestNumber", requestNo);
        JSONBorrowedBook.put("copyID", copyID);

        Book normalBook = new Book(ISBN, title, author, availability, copyID, isLoaned, loanLength);
        JSONBook = new JSONObject();
        JSONBook.put("ISBN", ISBN);
        JSONBook.put("title", title);
        JSONBook.put("author", author);
        JSONBook.put("available", availability);
        JSONBook.put("copyID", copyID);
        JSONBook.put("isLoaned", isLoaned);
        JSONBook.put("loanLength", loanLength);

    }


    @Test
    public void loadBorrowedBooksTest(){

        JSONArray bookArray = new JSONArray();
        bookArray.put(JSONBorrowedBook);

        ArrayList<BorrowedBook> books = ControllerBook.loadBorrowedBooks(bookArray.toString());

        BorrowedBook book = books.get(0);

        assertEquals(book.getISBN(), ISBN);
        assertEquals(book.getTitle(), title);
        assertEquals(book.getAuthor(), author);
        assertEquals(book.getStatus(), status);
        assertEquals(book.getLenderName(), lenderName);
        assertEquals(book.getStartDate(), startDate);
        assertEquals(book.getEndDate(), endDate);
        assertEquals(book.getRequestNo(), requestNo);
        assertEquals(book.getCopyID(), copyID);
    }

    @Test
    public void loadBook(){

        JSONArray bookArray = new JSONArray();
        bookArray.put(JSONBook);

        ArrayList<Book> books = ControllerBook.loadBookFull(bookArray.toString());

        Book book = books.get(0);

        assertEquals(book.getISBN(), ISBN);
        assertEquals(book.getAuthor(), author);
        assertEquals(book.getTitle(), title);
        assertEquals(book.getAvailability(), availability);
        assertEquals(book.getCopyID(), copyID);
        assertEquals(book.isLoaned, isLoaned);
        assertEquals(book.loanLength, loanLength);

    }


}