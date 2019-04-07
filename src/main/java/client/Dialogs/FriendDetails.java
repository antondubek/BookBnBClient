package client.Dialogs;

import client.Book;
import client.ClassicBookTableModel;
import client.ClassicTableMouseListener;
import client.Controller;
import client.MainLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 * Dialog Box which shows the details of the user searched
 *
 * @author er205
 */
public class FriendDetails extends javax.swing.JDialog implements ActionListener {

    private Boolean isFollowed;
    private client.User user;

    private JMenuItem menuItemInfo;
    private JPopupMenu popupMenu;

    private ArrayList<Book> userBooks;

    /**
     * Constructor
     *
     * @param parent Parent Frame of the dialog, only used to centre the box on
     * top of the parent frame.
     * @param user user searched
     */
    public FriendDetails(java.awt.Frame parent, boolean modal, client.User user) {
        super(parent, "Friend Profile", modal);
        initComponents();
        setResizable(false);
        setLocationRelativeTo(parent);
        setUsersDetails(user);
        if (!user.email.equals("")) {
            this.user = user;
            populateTable(user.email);
            setButton();
        }
    }

    /**
     * Sets the colour and text of the Follow/Unfollow Button
     */
    public void setButton() {
        isFollowed = client.Controller.isFollowing(user.email);
        if (this.isFollowed) {
            follow.setText("Unfollow");
            follow.setBackground(Color.red);
            follow.setBorder(new LineBorder(Color.black));
        } else {
            follow.setText("Follow");
            follow.setBackground(new Color(0, 204, 255));
            follow.setBorder(new LineBorder(new Color(0, 204, 255)));
        }
    }

    /**
     * Sets the logged in user details
     *
     * @param user logged in user
     */
    public void setUsersDetails(client.User user) {
        name.setText(user.name + "'s Profile");
        email.setText("Email: " + user.email);
        city.setText("City: " + user.city);
    }

    /**
     * Sets the model for the browse books table and the user books table. This
     * will also populate the tables with data collected from the server via the
     * controller.
     */
    public void populateTable(String email) {

        userBooks = Controller.getSearchedUserBooks(email);
        ClassicBookTableModel userBooksTableModel = new ClassicBookTableModel(userBooks);

        bookTable.setModel(userBooksTableModel);
        bookTable.setAutoCreateRowSorter(true);

        //Add a popup menu with the request book item and add a listener
        popupMenu = new JPopupMenu();
        menuItemInfo = new JMenuItem("View Info");
        popupMenu.add(menuItemInfo);
        menuItemInfo.addActionListener(this);

        bookTable.setComponentPopupMenu(popupMenu);
        bookTable.addMouseListener(new ClassicTableMouseListener(bookTable));

    }

    /**
     * Action listener which deals with the clicking on the tables
     *
     * @param e Event e, generated by the action listener.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menu = (JMenuItem) e.getSource();

        if (menu == menuItemInfo) {
            processBookInfo();
        }
    }

    /**
     * Deals with the retrieval of the book to request and sending it to the
     * controller.
     */
    private void processBookInfo() {
        int row = bookTable.getSelectedRow();

        Book selectedBook = userBooks.get(row);

        System.out.println("View info clicked");
        System.out.println("Title: " + selectedBook.getTitle());

        //Launch a new bookInfoDialog
        Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        BookInfoDialog bookInfoDialog = new BookInfoDialog(topFrame, true, selectedBook);
        bookInfoDialog.setVisible(true);

    }

    /**
     * Method executed when the Follow button is pressed. Will send a post
     * request to the Server in order to follow a given user
     */
    public void onFollow() {
        Boolean response = client.Controller.followUnfollowUser(user.email, "/follow");
        if (!response) {
            errorLabel.setText("An Error occurred");
        } else {
            errorLabel.setText("");
        }
    }

    /**
     * Method executed when the Unfollow button is pressed. Will send a post
     * request to the Server in order to unfollow a given user
     */
    public void onUnfollow() {
        Boolean response = client.Controller.followUnfollowUser(user.email, "/follow/delete");
        if (!response) {
            errorLabel.setText("An Error occurred");
        } else {
            errorLabel.setText("");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        city = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        follow = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        name1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        name.setFont(new java.awt.Font("Lantinghei SC", 1, 18)); // NOI18N
        name.setForeground(new java.awt.Color(0, 204, 255));
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("Name");

        email.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(102, 102, 102));
        email.setText("Email");

        city.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        city.setForeground(new java.awt.Color(102, 102, 102));
        city.setText("City");

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(bookTable);

        follow.setBackground(new java.awt.Color(0, 204, 255));
        follow.setFont(new java.awt.Font("Lantinghei SC", 1, 13)); // NOI18N
        follow.setForeground(new java.awt.Color(255, 255, 255));
        follow.setText("FOLLOW");
        follow.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        follow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                followActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(204, 0, 0));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        name1.setFont(new java.awt.Font("Lantinghei SC", 1, 14)); // NOI18N
        name1.setForeground(new java.awt.Color(0, 204, 255));
        name1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name1.setText("Right click to view book info");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(follow, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(email)
                                    .addComponent(city))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(errorLabel)
                        .addGap(97, 97, 97))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name)
                    .addComponent(follow))
                .addGap(18, 18, 18)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email)
                .addGap(18, 18, 18)
                .addComponent(city)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name1)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Action listener which deals with the clicking on the Follow/Unfollow
     * Button
     *
     * @param evt Event evt, generated by the action listener.
     */
    private void followActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_followActionPerformed
        if (!isFollowed) {
            onFollow();
        } else {
            onUnfollow();
        }
        setButton();
        MainLayout.profileCard.displayFollowing();
    }//GEN-LAST:event_followActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookTable;
    private javax.swing.JLabel city;
    private javax.swing.JLabel email;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton follow;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name1;
    // End of variables declaration//GEN-END:variables
}
