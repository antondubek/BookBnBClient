package client;

/**
 * Lender class which contains all the information about the lender of a
 * specific book.
 */
public class Lender {

    private String name;
    private String loanLength;
    private String city;
    private String ID;
    private String copyID;

    public Lender(String name, String loanLength, String city, String ID, String copyID) {
        this.name = name;
        this.loanLength = loanLength;
        this.city = city;
        this.ID = ID;
        this.copyID = copyID;
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

    public String getCopyID() {
        return copyID;
    }

}
