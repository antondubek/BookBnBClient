/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.Screens.BrowseScreen;
import client.Screens.ProfileScreen;
import client.Screens.WelcomeScreen;
import client.Screens.LoginRegisterScreen;
import client.Screens.MyBooksScreen;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author amakepeace
 */
public class MainLayout extends JFrame{
    
    private static JPanel cards; //a panel that uses CardLayout
    private static CardLayout cardLayout;
    private static JPanel profileCard;
    private static JPanel welcomeCard;
    private static JPanel browseCard;
    private static JPanel loginCard;
    private static JPanel myBooksCard;
        // button commands
    
    public MainLayout(){
        setupStuff();
    }
    
    private static void setupStuff(){

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
        JPanel navBar = new NavBar(cardLayout, cards);
 
        //Add the card layout and navbar to the container
        Container pane = frame.getContentPane();
        pane.add(cards, BorderLayout.CENTER);
        pane.add(navBar, BorderLayout.PAGE_START);
 
        //Set the default close button to close the app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set the size of the window and show it
        frame.setSize(700, 400);
        frame.setVisible(true);
    }
    
    public static void setCurrentScreen(String cardName){
        cardLayout.show(cards, cardName);
    }
    
    
    
}
