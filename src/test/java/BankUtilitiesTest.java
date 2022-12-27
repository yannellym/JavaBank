import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankUtilitiesTest {

    @Test
    void generateRandomInteger() {
        var utilities = new BankUtilities();
        assertEquals(1, 2, utilities.generateRandomInteger(1,2));
    }

    @Test
    void isIntStringInt() {
        var utilities = new BankUtilities();
        assertEquals(true, utilities.isInt("44"));
    }
    @Test
    void isStringInt() {
        var utilities = new BankUtilities();
        assertEquals(false, utilities.isInt("Forty Four"));
    }

    @Test
    void generateRandomInts() {
    }

    @Test
    void promptUserForString() {
    }

    @Test
    void verifyUser() {
    }

    @Test
    void getAccountIndex() {
    }
}