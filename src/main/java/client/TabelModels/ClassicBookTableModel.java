package client.TabelModels;

import client.Book;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/* Table model class used to define how the table should look and populates
 * it with books.
 */
public class ClassicBookTableModel extends AbstractTableModel {

    private ArrayList<Book> books;
    String headers[] = new String[]{"Title", "Author", "ISBN"};

    public ClassicBookTableModel(ArrayList<Book> books) {
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

        Book book = books.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return book.getTitle();
            case 1:
                return book.getAuthor();
            case 2:
                return book.getISBN();
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
