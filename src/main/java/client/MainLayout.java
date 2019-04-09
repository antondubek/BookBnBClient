package client;

import client.Controller.ControllerMain;
import client.Screens.BrowseScreen;
import client.Screens.LoginRegisterScreen;
import client.Screens.MyBooksScreen;
import client.Screens.ProfileScreen;
import client.Screens.WelcomeScreen;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class that creates the main layout for the whole application. This implements
 * a card layout to allow the switching between screens and puts a navbar at the
 * top of the frame.
 *
 */
public class MainLayout extends JFrame {

    private static JPanel cards; //a panel that uses CardLayout
    private static CardLayout cardLayout;
    public static ProfileScreen profileCard;
    private static WelcomeScreen welcomeCard;
    private static BrowseScreen browseCard;
    private static LoginRegisterScreen loginCard;
    private static MyBooksScreen myBooksCard;
    private static NavBar navBar;
    private static Container pane;
    // button commands

    /**
     * Main layout constructor, first thing to be called
     */
    public MainLayout() {
        checkServerAvailable();
        setupFrame();
    }

    /**
     * Checks that the server is available and updates the value in the
     * controller
     */
    private void checkServerAvailable() {
        ControllerMain.getServerStatus();
    }

    /**
     * Setup method which creates the frame, loads screens and initialises the
     * card layout used.
     *
     */
    private static void setupFrame() {

        //Create a new JFrame
        JFrame frame = new JFrame("BookBnB");

        //Create the screens.
        profileCard = new ProfileScreen();
        welcomeCard = new WelcomeScreen();
        browseCard = new BrowseScreen();
        loginCard = new LoginRegisterScreen();
        myBooksCard = new MyBooksScreen();

        //Define a new cardlayout
        cardLayout = new CardLayout();

        //Create a new panel with the cardlayout.
        cards = new JPanel(cardLayout);
        cards.add(profileCard, "PROFILE");
        cards.add(welcomeCard, "WELCOME");
        cards.add(browseCard, "BROWSE");
        cards.add(loginCard, "LOGIN");
        cards.add(myBooksCard, "MYBOOKS");

        //Create a new navigation bar
        navBar = new NavBar(cardLayout, cards);

        //Add the card layout and navbar to the container
        pane = frame.getContentPane();
        pane.add(cards, BorderLayout.CENTER);
        pane.add(navBar, BorderLayout.PAGE_START);

        //Set the default close button to close the app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setCurrentScreen("WELCOME");

        //Set the size of the window and show it
        frame.setSize(700, 450);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Sets the current card that is showing on the screen
     */
    public static void setCurrentScreen(String cardName) {
        cardLayout.show(cards, cardName);
    }

    /**
     * Updates the navigation bar and loads data for the myBooks table once the
     * user logs in.
     */
    public static void loginUpdate() {
        navBar.loginUpdate();
        myBooksCard.populateTables();
    }

}
