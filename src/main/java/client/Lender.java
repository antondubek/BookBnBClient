/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * 
 */
public class Lender {
   
    private String name;
    private String loanLength;
    private String city;
    private String ID;

    public Lender(String name, String loanLength, String city, String ID) {
        this.name = name;
        this.loanLength = loanLength;
        this.city = city;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getLoanLength() {
        return loanLength;
    }

    public String getCity() {
        return city;
    }

    public String getID() {
        return ID;
    }
    
    
}
