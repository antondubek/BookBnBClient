package client;

public class BorrowedBook extends Book {

    private String status;
    private String lenderName;
    private String startDate;
    private String endDate;

    BorrowedBook(String ISBN, String title, String author, String status, String lenderName) {
        super(ISBN, title, author);
        this.status = status;
        this.lenderName = lenderName;
    }

    //overloaded constructor
    BorrowedBook(String ISBN, String title, String author, String status, String lenderName, String startDate, String endDate) {
        this(ISBN, title, author, status, lenderName);
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
    
    
}
