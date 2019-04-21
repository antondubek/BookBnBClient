/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Controller;

import client.Book;
import client.BorrowedBook;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author acm35
 */
public class ControllerBook extends ControllerMain {

    /**
     * Retrieve all of the browsable books of the system
     *
     * @return arrayList containing all the book objects
     */
    public static ArrayList<Book> getAllBooks() {
        String response = sendGetRequest("/book?command=all");

        System.out.println("LOG: Retrieving all books from server");
        System.out.println(response);

        if (response.length() == 0) {
            return new ArrayList<Book>();
        }

        JSONArray allBooks = new JSONArray(response.toString());

        //System.out.println("All books response = " + response.toString());
        ArrayList<Book> books = new ArrayList<Book>();
        for (int i = 0; i < allBooks.length(); i++) {
            JSONObject currentBook = allBooks.getJSONObject(i);

            books.add(new Book(currentBook.getString("ISBN"), currentBook.getString("title"),
                    currentBook.getString("author"), false, null, false));
        }

        return books;

    }

    /**
     * Adds a book to the servers collection
     *
     * @param ISBN ISBN of the book
     * @param author Author of the book
     * @param title Title of the book
     * @param edition Edition of the book
     * @return True (Successfully added) False (Addition not successful)
     */
    public static boolean addBook(String ISBN, String author, String title, String edition) {

        JSONObject data = new JSONObject();
        data.put("email", email);
        data.put("ISBN", ISBN);
        data.put("author", author);
        data.put("title", title);
        data.put("edition", edition);

        System.out.println("LOG: Registering new book with server");

        boolean successful = sendPostGetResponse("/profile/addBook", data);

        if (successful) {
            System.out.println("LOG: Book registration successful");
        } else {
            System.out.println("LOG: Book registration failed");
        }

        return successful;
    }

    /**
     * Sends a Post Request in order to change the availability status of the
     * book
     *
     * @param email email of the book that needs to change availability of
     * @param ISBN ISBN of the book that needs to change availability of
     * @param availability availability of the book that needs to change
     * availability of
     */
    public static void updateBookAvailability(String email, String ISBN, Boolean availability, String copyID) {
        JSONObject data = new JSONObject();
        data.put("email", email);
        data.put("ISBN", ISBN);
        data.put("available", availability);
        data.put("copyID", copyID);

        Boolean response = sendPostGetResponse("/profile/books/availability", data);
        getUserBooks();
    }

    /**
     * Gets the books belonging to the user
     *
     * @return An arrayList of all the book objects which belong to the user
     */
    public static ArrayList<Book> getUserBooks() {
        JSONObject data = new JSONObject();
        data.put("email", email);

        System.out.println("LOG: Retrieving user books from server");

        return getBooks(data);
    }

    /**
     * Retrieves the books of a certain searched user
     *
     * @param email of the searched user
     * @return books of searched user
     */
    public static ArrayList<Book> getSearchedUserBooks(String email) {
        JSONObject data = new JSONObject();
        data.put("email", email);

        System.out.println("LOG: Retrieving searched user books from server");
        return getBooks(data);

    }

    /**
     * Breaks down the logic for retrieving the books of a certain user
     *
     * @param data contains the email of the user
     * @return the books of the user
     */
    public static ArrayList<Book> getBooks(JSONObject data) {

        String response = sendPostGetData("/profile/books", data);
        System.out.println("LOG: Books received");
        System.out.println(response);
        JSONArray userBooks = new JSONArray(response);

        ArrayList<Book> books = new ArrayList<Book>();
        for (int i = 0; i < userBooks.length(); i++) {
            JSONObject currentBook = userBooks.getJSONObject(i);

            books.add(new Book(currentBook.getString("ISBN"), currentBook.getString("title"), currentBook.getString("author"),
                    currentBook.getBoolean("available"), currentBook.getString("copyID"), currentBook.getBoolean("isLoaned")));

        }

        return books;
    }

    /**
     * Requests all the books the user is borrowing from the server
     *
     * @return A list of borrowed books
     */
    public static ArrayList<BorrowedBook> getBorrowedBooks() {

        JSONObject data = new JSONObject();
        data.put("email", email);

        String response = sendPostGetData("/request/borrow", data);

        System.out.println("LOG: Retrieving borrowed books");

        System.out.println("Borrowed Books: " + response);

        if (response.length() == 0) {
            return new ArrayList<>();
        }

        ArrayList<BorrowedBook> books = loadBorrowedBooks(response);

        return books;

    }

    /**
     * Requests all the books the user is loaning from the server
     *
     * @return A list of borrowed books
     */
    public static ArrayList<BorrowedBook> getLoanedBooks() {

        JSONObject data = new JSONObject();
        data.put("email", email);

        String response = sendPostGetData("/request/loan", data);

        System.out.println("LOG: Retrieving loaned books");

        System.out.println("Loaned Books: " + response);

        if (response.length() == 0) {
            return new ArrayList<>();
        }

        ArrayList<BorrowedBook> books = loadBorrowedBooks(response);

        return books;

    }

    /**
     * Takes in a response from the server in a JSON format and create an
     * arraylist of borrowed books from that.
     *
     * @param response JSON formatted string of borrowed books
     * @return An arraylist of borrowed books
     */
    private static ArrayList<BorrowedBook> loadBorrowedBooks(String response) {

        JSONArray borrowedBooks = new JSONArray(response.toString());

        ArrayList<BorrowedBook> books = new ArrayList<BorrowedBook>();
        for (int i = 0; i < borrowedBooks.length(); i++) {
            JSONObject currentBook = borrowedBooks.getJSONObject(i);

            books.add(new BorrowedBook(currentBook.getString("ISBN"), currentBook.getString("title"),
                    currentBook.getString("author"), currentBook.getString("status"), currentBook.getString("name"),
                    currentBook.getString("startDate"), currentBook.getString("endDate"),
                    currentBook.getString("requestNumber"), currentBook.getString("copyID")));
        }

        return books;

    }

    /**
     * Sends the request to the server to recall a book
     *
     * @param book Book to request
     * @return True (Recall complete) or False (recall failed)
     */
    public static boolean recallBook(BorrowedBook book) {

        JSONObject data = new JSONObject();
        data.put("copyID", book.getCopyID());
        data.put("requestNumber", book.getRequestNo());

        System.out.println("Recalling Book with copyID:" + book.getCopyID()
                + "and requestNo:" + book.getRequestNo());

        return sendPostGetResponse("/recall", data);
    }

    /**
     * Registers a book as returned with the server. This will be called when an
     * owner of a book says that the book has been returned from the
     * MyBooksScreen.
     *
     * @param book Book to be returned
     * @return True (Return was successful) or False (it failed for some backend
     * reason)
     */
    public static boolean returnBook(BorrowedBook book) {

        JSONObject data = new JSONObject();
        data.put("requestNumber", book.getRequestNo());

        System.out.println("Returning a book with request no:" + book.getRequestNo());

        return sendPostGetResponse("/returnBook", data);

    }
    
    /**
     * Gets the rating of a book
     * @param ISBN of the book to get the rating 
     * @return the average rating of the book
     */
    public static String getBookRating(String ISBN) {
        JSONObject data = new JSONObject();
        data.put("ISBN", ISBN);
        
        System.out.println("LOG: Getting Book Review");
        
        String response = sendPostGetData("/rating/book", data);
        JSONObject rating = new JSONObject(response);
        
        return rating.getString("AverageRating");
    }
    
    /**
     * Sends a request to set the book review
     * @param ISBN of the book
     * @param review review set by the user
     * @param rating rating set by the user
     * @return 
     */
    public static boolean setBookRating(String ISBN, String review, String rating){
        
        JSONObject data = new JSONObject();
        data.put("email", email);
        data.put("ISBN", ISBN);
        data.put("rating", rating);
        data.put("review", review);
        
        System.out.println("LOG: Sending review");
        
        return sendPostGetResponse("/rating/book/set", data);
    }

}
