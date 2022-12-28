import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void getAccNumberTrue() {
        var bank = new Bank();
        Account account1 = new Account(192837495, "Elizabeth", "Lopez", 987654229, 6569);
        bank.addAccountToBank(account1);
        assertEquals(192837495,account1.getAccNumber());
    }
    @Test
    void getAccNumberFalse() {
        var bank = new Bank();
        Account account1 = new Account(192837495, "Elizabeth", "Lopez", 987654229, 6569);
        bank.addAccountToBank(account1);
        assertNotEquals(192837491,account1.getAccNumber());
    }

    @Test
    void getOwnerFirstNameTrue() {
        var bank = new Bank();
        Account account1 = new Account(192837488, "Mary", "Lopez", 987654229, 7669);
        bank.addAccountToBank(account1);
        assertEquals("Mary",account1.getOwnerFirstName());
    }

    @Test
    void getOwnerFirstNameFalse() {
        var bank = new Bank();
        Account account1 = new Account(192837488, "Luis", "Hernandez", 987654229, 2269);
        bank.addAccountToBank(account1);
        assertNotEquals("Mary",account1.getOwnerFirstName());
    }

    @Test
    void getOwnerLastNameTrue() {
        var bank = new Bank();
        Account account1 = new Account(192837333, "Luis", "Hernandez", 987654378, 2269);
        bank.addAccountToBank(account1);
        assertEquals("Hernandez",account1.getOwnerLastName());
    }

    @Test
    void getOwnerLastNameFalse() {
        var bank = new Bank();
        Account account1 = new Account(192837333, "Louis", "Vuitton", 987654378, 2269);
        bank.addAccountToBank(account1);
        assertNotEquals("Gucci",account1.getOwnerLastName());
    }

    @Test
    void getSsnTrue() {
        var bank = new Bank();
        Account account1 = new Account(15437333, "Nelly", "Vuitton", 987654321, 2269);
        bank.addAccountToBank(account1);
        assertEquals(987654321,account1.getSsn());
    }

    @Test
    void getSsnFalse() {
        var bank = new Bank();
        Account account1 = new Account(15437333, "Rose", "Mayweather", 876543211, 2269);
        bank.addAccountToBank(account1);
        assertNotEquals(987654321,account1.getSsn());
    }

    @Test
    void getPINTrue() {
        var bank = new Bank();
        Account account1 = new Account(15437333, "Rose", "Mayweather", 876543211, 2269);
        bank.addAccountToBank(account1);
        assertEquals(2269, account1.getPIN());
    }

    @Test
    void getPINFalse() {
        var bank = new Bank();
        Account account1 = new Account(15437333, "Lays", "Chips", 876543211, 8181);
        bank.addAccountToBank(account1);
        assertNotEquals(8180, account1.getPIN());
    }

    @Test
    void getBalanceTrue() {
        var bank = new Bank();
        Account account1 = new Account(15437333, "Murray", "Cowardly", 876547654, 7890);
        bank.addAccountToBank(account1);
        assertEquals(0.0, account1.getBalance());
    }
    @Test
    void getBalanceFalse() {
        var bank = new Bank();
        Account account1 = new Account(15437333, "Murray", "Cowardly", 876547654, 7890);
        bank.addAccountToBank(account1);
        assertNotEquals(500.00, account1.getBalance());
    }

    @Test
    void depositTrue() {
        var bank = new Bank();
        Account account1 = new Account(15437978, "Steven", "Crown", 873337654, 0101);
        bank.addAccountToBank(account1);
        assertEquals(500.75, account1.deposit(500.75));
    }

    @Test
    void depositFalse() {
        var bank = new Bank();
        Account account1 = new Account(15437978, "Steven", "Crown", 873337654, 0101);
        bank.addAccountToBank(account1);
        assertNotEquals(500.00, account1.deposit(500.75));
    }

    @Test
    void withdrawTrue() {
        var bank = new Bank();
        Account account1 = new Account(15437123, "Jessica", "Brown", 123437654, 2222);
        bank.addAccountToBank(account1);
        account1.deposit(500.00);
        assertEquals(10.00, account1.withdraw(490));
    }
    @Test
    void withdrawTrueAlso() {
        var bank = new Bank();
        Account account1 = new Account(15437123, "Jessica", "Brown", 123437654, 2222);
        bank.addAccountToBank(account1);
        account1.deposit(500.00);
        assertEquals(0.0, account1.withdraw(500));
    }

    @Test
    void isValidPinTrue() {
        var bank = new Bank();
        Account account1 = new Account(15437123, "Jessica", "Brown", 123437654, 2222);
        bank.addAccountToBank(account1);
        assertEquals(2222, account1.getPIN());
    }
    @Test
    void isValidPinFalse() {
        var bank = new Bank();
        Account account1 = new Account(15437123, "Jessica", "Brown", 123437654, 2222);
        bank.addAccountToBank(account1);
        assertNotEquals(2221, account1.getPIN());
    }
}