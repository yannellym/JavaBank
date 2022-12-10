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
        // System.out.println("opening account");

        BankUtilities.all_accounts[0] = new Account();
        BankUtilities.all_accounts[0].setData(12345,"Nelly","Merc",4213, 9999, 500.58);
        System.out.println(BankUtilities.all_accounts);
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
