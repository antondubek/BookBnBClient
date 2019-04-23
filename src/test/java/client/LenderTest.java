package client;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LenderTest {
    private Lender testLender;
    private String ID = "1";
    private String name = "Jimbo";
    private String city = "Sydney";
    private String loanLength = "14";
    private String copyID = "1";

    @Before
    public void Setup(){
        testLender = new Lender(name, loanLength, city, ID, copyID);
    }

    @Test
    public void testConstructor(){
        assertEquals(testLender.getID(), ID);
        assertEquals(testLender.getName(), name);
        assertEquals(testLender.getCity(), city);
        assertEquals(testLender.getLoanLength(), loanLength);
        assertEquals(testLender.getCopyID(), copyID);
    }

}