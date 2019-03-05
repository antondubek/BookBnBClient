import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddBook extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel Buttons;
    private JTextField ISBNTxt;
    private JTextField authorTxt;
    private JTextField titleTxt;
    private JTextField editionTxt;
    private JPanel Content;
    private JPanel Title;
    private JLabel errorTxt;

    public AddBook(Frame parent) {
        super(parent, "BookBnB : Register", true);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        setupListeners();
        pack();
        //setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private void setupListeners(){
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }


    private void onOK() {

        String ISBN = ISBNTxt.getText().trim();
        String author = authorTxt.getText().trim();
        String title = titleTxt.getText().trim();
        String edition = editionTxt.getText().trim();

        boolean addedCorrectly = Controller.addBook(ISBN, author, title, edition);

        System.out.println(addedCorrectly);

        if(addedCorrectly){
            dispose();
        } else {
            errorTxt.setText("Error occurred, please check values or try later");
            pack();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
