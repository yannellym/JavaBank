import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankUtilitiesTest {

    @Test
    void generateRandomInteger() {
        var utilities = new BankUtilities();
        assertEquals(1, 2, utilities.generateRandomInteger(1,2));
    }

    @Test
    void isIntStringTrue() {
        assertTrue(BankUtilities.isInt("44"));
    }
    @Test
    void isIntStringFalse() {
        assertFalse(BankUtilities.isInt("Forty Four"));
    }

    @Test
    void generateRandomInts() {
        var utilities = new BankUtilities();
        assertEquals(9, utilities.generateRandomInts(9).length());
    }

    @Test
    void verifyUserTrue() {
        var utilities = new BankUtilities();
        Account account = new Account(192837465, "Nelly","Steven", 987654321, 6768);
        utilities.all_accounts.add(account);
        assertTrue(utilities.verifyUser(192837465, 6768));
    }
    @Test
    void verifyUserFalse() {
        var utilities = new BankUtilities();
        Account account = new Account(192837499, "Clara","Merced", 987654399, 6969);
        utilities.all_accounts.add(account);
        assertFalse(utilities.verifyUser(192837488, 6969));
    }

    @Test
    void getAccountIndexInAccounts() {
        var utilities = new BankUtilities();
        Account account = new Account(192837499, "Clara","Merced", 987654399, 6969);
        utilities.all_accounts.add(account);
        assertEquals(0, utilities.getAccountIndex(192837499));
    }
    @Test
    void getAccountIndexNotInAccounts() {
        var utilities = new BankUtilities();
        Account account1 = new Account(192837495, "Elizabeth","Lopez", 987654229, 6569);
        Account account2 = new Account(192837488, "Clara","Merced", 987654399, 6969);
        utilities.all_accounts.add(account1);
        utilities.all_accounts.add(account2);
        assertEquals(1, utilities.getAccountIndex(192837488));
    }
}