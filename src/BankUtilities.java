import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BankUtilities extends Bank{
    Scanner scanner = new Scanner(System.in);


    /**
     *
     * @param prompt a String that represents a prompt to print on the screen. Ex "Enter your name"
     * @return String. The input of the user.
     */
    public String promptUserForString(String prompt){
        System.out.println(prompt);
        String name = scanner.next();
        return name;
    }

    /**
     * @param prompt
     * @return a DOUBLE representing a positive number
     */
    public double promptUserForPositiveNumber(String prompt){
        System.out.println(prompt);
        Double positiveNum = scanner.nextDouble();

        while (positiveNum <= 0){
            System.out.println("Amount cannot be negative. Try again. ");
            return promptUserForPositiveNumber(prompt);
        }
        return positiveNum;
    }
    public long convertFromDollarsToCents(double dollars){

        return (long) dollars;
    }

    /**
     * @params minVal,  maxVal are both of type int. They represent the range
     * @return an int representing a random number
     */
    public int generateRandomInteger( int minVal, int maxVal){
        int randomNumber = ThreadLocalRandom.current().nextInt(minVal, maxVal + 1);
        return randomNumber;
    }
    public void openAccount(){
        System.out.println("opening account ...");
        String firstName = promptUserForString("Enter your first name: ");
        String lastName = promptUserForString("Enter your last name: ");
        System.out.println("Enter your SSN: ");
        int SSN = scanner.nextInt();

        int num = 0;
        String randomNums = "";

        while( num < 10){
            randomNums = randomNums + generateRandomInteger(0,9);
            num = num + 1;
        }
        long acc_number = Long.parseLong(randomNums);
        Account newAccount = new Account(acc_number,firstName,lastName, SSN, 9999, 500.58);
        // System.out.println(newAccount.getClass().getSimpleName());
        all_accounts.add(newAccount);
        System.out.println("Account " + newAccount.accNumber + " was created.");
    }
    public void getAccountInfoAndBalance(){
        System.out.println("Getting account info and balance");
    }
    public void changePin(){
        System.out.println("Changing PIN");
    }
    public void depositMoneyToAccount(){
        System.out.println("Depositing Money to account");
    }
    public void transferBetweenAccounts(){
        System.out.println("Transferring money between accounts");
    }
    public void withdrawFromAccount(){
        System.out.println("Withdrawing money from account");
    }
    public void withdrawFromATM(){
        System.out.println("Withdrawing money from ATM");
    }
    public void depositChange(){
        System.out.println("Depositing Change to account");
    }
    public void closeAccount(){
        System.out.println("Closing account");
    }
    public void addMonthlyInterest(){
        System.out.println("Adding a monthly interest to all accounts");
    }
    public Account promptForAccountNumberAndPIN(){
        System.out.println("Please enter an account number: ");
        long accNumber = scanner.nextLong();
        for (Account singleAccount : all_accounts){
            if (singleAccount.accNumber == accNumber){
                System.out.println("Please enter PIN:");
                int accPin = scanner.nextInt();
                if(accPin != singleAccount.PIN){
                    System.out.println("Invalid PIN");
                }
                openAccount();
            }
        }
        System.out.println("Account not found for account number:" + accNumber);
        return null;
    }
}
