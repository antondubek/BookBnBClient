package client;

/**
 * Class which contains all the information about a book that is borrowed.
 *
 */
public class BorrowedBook extends Book {

    private String status;
    private String lenderName;
    private String startDate;
    private String endDate;
    private String requestNo;

    BorrowedBook(String ISBN, String title, String author, String status, String lenderName, String requestNo) {
        super(ISBN, title, author);
        this.status = status;
        this.lenderName = lenderName;
        this.requestNo = requestNo;
    }

    //overloaded constructor
    BorrowedBook(String ISBN, String title, String author, String status, String lenderName, String startDate, String endDate, String requestNo) {
        this(ISBN, title, author, status, lenderName, requestNo);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public String getLenderName() {
        return lenderName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
