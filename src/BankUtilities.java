import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BankUtilities extends Bank {
    Scanner scanner = new Scanner(System.in);
    public int generateRandomInteger(int minVal, int maxVal) {
        int randomNumber = ThreadLocalRandom.current().nextInt(minVal, maxVal + 1);
        return randomNumber;
    }
    public double promptUserForPositiveNumber(String prompt) {
        System.out.println(prompt);
        Double positiveNum = scanner.nextDouble();

        while (positiveNum <= 0) {
            System.out.println("Amount cannot be negative. Try again. ");
            return promptUserForPositiveNumber(prompt);
        }
        return positiveNum;
    }
    public String promptUserForString(String prompt) {
        System.out.println(prompt);
        String name = scanner.next();
        return name;
    }
    public void openAccount() {
        System.out.println("opening account ...");
        String firstName = promptUserForString("Enter your first name: ");
        String lastName = promptUserForString("Enter your last name: ");
        System.out.println("Enter your SSN: ");
        int SSN = scanner.nextInt();

        int num = 0;
        String randomNums = "";

        while (num < 10) {
            randomNums = randomNums + generateRandomInteger(0, 9);
            num = num + 1;
        }
        long acc_number = Long.parseLong(randomNums);
        Account newAccount = new Account(acc_number, firstName, lastName, SSN, 999);
        // System.out.println(newAccount.getClass().getSimpleName());
        all_accounts.add(newAccount);
        System.out.println("Account " + newAccount.accNumber + " was created.");
    }
    public ArrayList<Object> promptForAccountNumberAndPIN() {
        ArrayList<Object> res = new ArrayList<Object>();

        System.out.println("Please enter an account number: ");
        long accNumber = scanner.nextLong();

        System.out.println("Please enter PIN:");
        int accPin = scanner.nextInt();

        // add both variables to the arrayList
        res.add(accNumber);
        res.add(accPin);

        // returns an arrayList of two values (account number, and account pin)
        return res;
    }
    public void getAccountInfoAndBalance() {
        // call the promptForAccountNumberAndPin function which returns an ArrayList
        // save the response above in an arr called info of type ArrayList<Object>
        ArrayList<Object> info = promptForAccountNumberAndPIN();

        // the two values accessed from the info arrayList are cast into the correct type
        long accNumber = (long) info.get(0);
        int accPin = (int) info.get(1);

        for (Account singleAccount : all_accounts) {
            if (singleAccount.accNumber == accNumber) {
                if (accPin != singleAccount.PIN) {
                    System.out.println("Invalid PIN");
                    getAccountInfoAndBalance();
                }
            } else {
                System.out.println("Account not found for account number:" + accNumber);
                getAccountInfoAndBalance();
            }
        }
        return true;
    }
    }
    public long convertFromDollarsToCents(double dollars) {

        return (long) dollars;
    }
    public void changePin() {

        System.out.println("Changing PIN");
    }

    public void depositMoneyToAccount() {
        System.out.println("Depositing Money to account");
    }

    public void transferBetweenAccounts() {
        System.out.println("Transferring money between accounts");
    }

    public void withdrawFromAccount() {
        System.out.println("Withdrawing money from account");
    }

    public void withdrawFromATM() {
        System.out.println("Withdrawing money from ATM");
    }

    public void depositChange() {
        System.out.println("Depositing Change to account");
    }

    public void closeAccount() {
        System.out.println("Closing account");
    }

    public void addMonthlyInterest() {
        System.out.println("Adding a monthly interest to all accounts");
    }

}
