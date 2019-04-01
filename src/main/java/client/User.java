/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author er205
 */
public class User {
    public String name;
    public String email;
    public String city;

    /**
     * 
     * @param name
     * @param email
     * @param city 
     */
    public User(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.city = city;

    }
}
