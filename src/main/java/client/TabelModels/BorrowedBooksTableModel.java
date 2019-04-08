package client.TabelModels;

import client.BorrowedBook;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * Table model class used to define how the table should look for borrowed books
 * and populates it with books.
 */
public class BorrowedBooksTableModel extends AbstractTableModel {

    private ArrayList<BorrowedBook> books;
    String headers[];

    public BorrowedBooksTableModel(ArrayList<BorrowedBook> books) {
        this.books = books;
        headers = new String[]{"Title", "Author", "Borrowed From", "Status"};
    }

    public BorrowedBooksTableModel(ArrayList<BorrowedBook> books, Boolean isFollower) {
        this.books = books;
        headers = new String[]{"Title", "Author", "Loaned To", "Status"};
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        BorrowedBook book = books.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return book.getTitle();
            case 1:
                return book.getAuthor();
            case 2:
                return book.getLenderName();
            case 3:
                return getStatus(book);
            default:
                return "";
        }

    }

    /**
     * Will return the status of the book. This looks at whether the book is
     * pending or it is actually been borrowed and will return either pending of
     * the number of days remaining/overdue.
     *
     * @param book Borrowed book object
     * @return A string which shows the status of the book
     */
    private String getStatus(BorrowedBook book) {

        String status = book.getStatus();

        Date endDate = new Date();
        Date currentDate = new Date();

        if (status.equals("pending")) {
            return status;
        } else {

            //Get the end date of the book
            String end = book.getEndDate();

            //Set the date format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {
                endDate = sdf.parse(end);
            } catch (ParseException ex) {
                Logger.getLogger(BorrowedBooksTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }

            int days = daysBetween(currentDate, endDate);

            if (days > 0) {
                return (days + " days remaining");
            } else if (days == 0) {
                return ("Return today");
            } else {
                return (days + " days overdue");
            }

        }

    }

    /**
     * Calculates the number of days difference between two dates.
     *
     * @param dateOne First date
     * @param dateTwo Second date to compare against
     * @return number of days difference
     */
    public int daysBetween(Date dateOne, Date dateTwo) {
        return (int) ((dateTwo.getTime() - dateOne.getTime()) / (1000 * 60 * 60 * 24));
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        return String.class;

    }

}
