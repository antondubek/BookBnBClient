package client.TabelModels;

import client.BorrowedBook;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Table model class used to define how the table should look for borrowed books
 * and populates it with books.
 */
public class BorrowedBooksTableModel extends AbstractTableModel {

    private ArrayList<BorrowedBook> books;
    String headers[] = new String[]{"Title", "Author", "Borrowed From", "Status"};

    public BorrowedBooksTableModel(ArrayList<BorrowedBook> books) {
        this.books = books;
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
                return book.getStatus();
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

        return String.class;

    }

}
