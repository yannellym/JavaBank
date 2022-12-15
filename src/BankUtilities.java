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
    public String generateRandomInts(int target){
        // set num to 0 to be our iterator
        int num = 0;
        // randomNums will be our initial string
        String randomNums = "";
        // while num is less than the target number inputted
        // add a random number to the string, increase num
        // return the string randomNums
        while (num < target) {
            randomNums = randomNums + generateRandomInteger(0, 9);
            num = num + 1;
        }
        return randomNums;
    }
    public void openAccount() {
        System.out.println("opening account ...");
        String firstName = promptUserForString("Enter your first name: ");
        String lastName = promptUserForString("Enter your last name: ");
        System.out.println("Enter your SSN: ");
        int SSN = scanner.nextInt();

        // call the generateRandomInts function to generate random number
        // sequences for both the account numbers and the PIN
        // The function returns a string so this makes sure to parse it
        long acc_number = Long.parseLong(generateRandomInts(10));
        int pin = Integer.parseInt(generateRandomInts(4));
        // creates a new account
        Account newAccount = new Account(acc_number, firstName, lastName, SSN, pin);
        // adds new account to all_accounts in Bank array
        all_accounts.add(newAccount);
        // prints out the account's information
        printAccountInfo(acc_number);
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
    public Boolean verifyUser(long accNumber, int accPin){
        // looks through the all_accounts array list
        // if an account in the array list matches the account number provided
        // by the user, and if the pin in that account matches the pin provided
        // by the user, return true
        // if the pin is incorrect, print invalid pin and redirect to menu,
        // if account number doesn't exist, print no account found and redirect
        // to menu. If everything else doesn't execute, return false.
        for (Account singleAccount : all_accounts) {
            if (singleAccount.accNumber == accNumber) {
                if (accPin == singleAccount.PIN) {
                    return true;
                } else {
                    System.out.println("Invalid PIN");
                    getAccountInfoAndBalance();
                }
            } else {
                System.out.println("Account not found for account number:" + accNumber);
                getAccountInfoAndBalance();
            }
        }
        return false;
    }
    public int getAccountIndex(long accNumber){
        int accIndex = 0;
        // create a for loop that loops through the all_accounts arrayList
        // if the single account matches an account in that array
        // get the index, and set accIndex to that index.
        for (Account singleAccount : all_accounts) {
            // i  will initially be 0
            int i = 0;
            if (singleAccount.accNumber == accNumber) {
                accIndex = i;
            }
            // increment i for every step
            i++;
        }
        return accIndex;
    }
    public void getAccountInfoAndBalance() {
        // call the promptForAccountNumberAndPin function which returns an ArrayList
        // save the response above in an arr called info of type ArrayList<Object>
        ArrayList<Object> info = promptForAccountNumberAndPIN();

        // the two values accessed from the info arrayList are cast into the correct type
        long accNumber = (long) info.get(0);
        int accPin = (int) info.get(1);
        // call the verifyUser function which will verify the user and return a boolean
        Boolean verified =  verifyUser(accNumber, accPin);
        // if the user is verified, show the account's information
        // else, call this function again to prompt for account number and pin
        if (verified) {
            printAccountInfo(accNumber);
        }
    }
    public void printAccountInfo(long accNumber){
        int accIndex = getAccountIndex(accNumber);
        // save the account in a variable for easy access when printing out the information
        Account selectedAcct = all_accounts.get(accIndex);
        // variable will contain all the information from the selected account
        // Uses getters to access the information
        long ssn = selectedAcct.getSsn();
        String ssnString = Long.toString(ssn);
        String lastFour = ssnString.substring(ssnString.length()-4);

        String info = """
                Account Number : %d
                Owner First Name: %s
                Owner Last Name: %s
                Owner SSN: XXX-XX-%s
                PIN: %d
                Balance: $ %f
                """.formatted(selectedAcct.getAccNumber(), selectedAcct.getOwnerFirstName(),
                selectedAcct.getOwnerLastName(), lastFour,
                selectedAcct.getPIN(), selectedAcct.getBalance()
        );
        System.out.println(info);
    }
    public void changePin() {
        ArrayList<Object> info = promptForAccountNumberAndPIN();

        // the two values accessed from the info arrayList are cast into the correct type
        long accNumber = (long) info.get(0);
        int accPin = (int) info.get(1);
        // call the verifyUser function which will verify the user and return a boolean
        Boolean verified =  verifyUser(accNumber, accPin);
        if(verified){
            System.out.println("Enter new PIN: ");
            int newPIN = scanner.nextInt();
            System.out.println("Enter new PIN again to confirm: ");
            int newPINconfirmed = scanner.nextInt();
            if(newPIN == newPINconfirmed){
                int accIndex = getAccountIndex(accNumber);
                all_accounts.get(accIndex).setPIN(newPIN);
                System.out.println("PIN updated successfully!");
            } else{
                System.out.println("PINs do not match. Try again.");
            }
        } else{
            System.out.println("Wrong account number or PIN. Please try again.");
        }
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
