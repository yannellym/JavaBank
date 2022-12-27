import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void addAccountToBankTrue(){
        var bank = new Bank();
        Account account1 = new Account(192837495, "Elizabeth","Lopez", 987654229, 6569);
        Account account2 = new Account(192837488, "Clara","Merced", 987654399, 6969);
        bank.addAccountToBank(account1);
        assertTrue(bank.addAccountToBank(account2));
    }

    @Test
    void addAccountToBankFalse(){
        var bank = new Bank();
        Account account1 = new Account(192837495, "Elizabeth","Lopez", 987654229, 6569);
        Account account2 = new Account(192837488, "Clara","Merced", 987654399, 6969);
        Account account3 = new Account(192837466, "Laura","Ashley", 987654666, 6262);
        bank.addAccountToBank(account1);
        bank.addAccountToBank(account2);
        assertFalse(bank.addAccountToBank(account3));
    }

    @Test
    void removeAccountFromBankTrue() {
        var bank = new Bank();
        Account account1 = new Account(192837495, "Elizabeth", "Lopez", 987654229, 6569);
        Account account2 = new Account(192837488, "Clara", "Merced", 987654399, 6969);
        bank.addAccountToBank(account1);
        bank.addAccountToBank(account2);
        assertTrue(bank.removeAccountFromBank(account1));
    }

    @Test
    @DisplayName("Removes account from bank and returns false")
    void removeAccountFromBankFalse() {
        var bank = new Bank();
        Account account1 = new Account(192837495, "Elizabeth", "Lopez", 987654229, 6569);
        Account account2 = new Account(192837488, "Clara", "Merced", 987654399, 6969);
        Account account3 = new Account(192837466, "Laura","Ashley", 987654666, 6262);
        bank.addAccountToBank(account1);
        bank.addAccountToBank(account2);
        assertFalse(bank.removeAccountFromBank(account3));
    }
}