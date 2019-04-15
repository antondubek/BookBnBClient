package client.TabelModels;

import client.Book;
import client.Controller.ControllerBook;
import client.Controller.ControllerMain;
import client.Screens.MyBooksScreen;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * Table model class used to define how the my books table should look and
 * populates it with books.
 */
public class MyBooksTableModel extends AbstractTableModel {

    private ArrayList<Book> books;
    String headers[] = new String[]{"Title", "Author", "ISBN", "Available", "Loan Length"};
    MyBooksScreen screen;

    public MyBooksTableModel(ArrayList<Book> books, MyBooksScreen screen) {
        this.books = books;
        this.screen = screen;
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

        Book book = books.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return book.getTitle();
            case 1:
                return book.getAuthor();
            case 2:
                return book.getISBN();
            case 3:
                return book.getAvailability();
            case 4:
                return book.getLoanLength();
            default:
                return "";
        }

    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    /**
     * Will allow the user to toggle whether a book is available or not, unless
     * it is on loan and then it will not allow the toggling.
     *
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        Book book = books.get(rowIndex);

        if (!book.isLoaned && columnIndex == 3 || columnIndex == 4) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * listener for the tickBox which sets the availability of the selected book
     *
     * @param aValue
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if (aValue instanceof Boolean && columnIndex == 3) {
            books.get(rowIndex).setAvailability((Boolean) aValue);

            String ISBN = books.get(rowIndex).ISBN;
            Boolean available = books.get(rowIndex).availability;
            String copyID = books.get(rowIndex).copyID;
            String email = ControllerMain.email;

            ControllerBook.updateBookAvailability(email, ISBN, available, copyID);

            JOptionPane.showMessageDialog(screen, "Changed Availability");
            screen.populateMyBooksTable();
        }
    }
}
