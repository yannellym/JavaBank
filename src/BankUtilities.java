import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BankUtilities extends Bank {
    Scanner scanner = new Scanner(System.in);
    public int generateRandomInteger(int minVal, int maxVal) {
        return ThreadLocalRandom.current().nextInt(minVal, maxVal + 1);
    }
    public double promptUserForPositiveNumber(String prompt) {
        System.out.println(prompt);
        double positiveNum = scanner.nextDouble();

        if(positiveNum <= 0) {
            System.out.println("Amount cannot be negative. Try again. ");
            return promptUserForPositiveNumber(prompt);
        }
        return positiveNum;
    }
    public String promptUserForString(String prompt) {
        System.out.println(prompt);
        return scanner.next();
    }
    public String generateRandomInts(int target){
        // set num to 0 to be our iterator
        int num = 0;
        // randomNums will be our initial string
        StringBuilder randomNums = new StringBuilder();
        // while num is less than the target number inputted
        // add a random number to the string, increase num
        // return the string randomNums
        while (num < target) {
            randomNums.append(generateRandomInteger(0, 9));
            num = num + 1;
        }
        return randomNums.toString();
    }
    public void openAccount() {
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
        System.out.println(" *** Account opened successfully! *** ");
        // prints out the account's information
        printAccountInfo(acc_number);
    }
    public ArrayList<Object> promptForAccountNumberAndPIN() {
        ArrayList<Object> res = new ArrayList<>();

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
    public Boolean verifyUser(long accountNumber, int accPin) {
        // looks through the all_accounts array list
        // if an account in the array list matches the account number provided
        // by the user, and if the pin in that account matches the pin provided
        // by the user, return true
        // if the pin is incorrect, print invalid pin and redirect to menu,
        // if account number doesn't exist, print no account found and redirect
        // to menu. If everything else doesn't execute, return false.
        for (Account singleAccount : all_accounts) {
            if (singleAccount.accNumber == accountNumber) {
                if (accPin == singleAccount.PIN) {
                    return true;
                } else {
                    System.out.println("Invalid PIN");
                }
            }
        }
        System.out.println("Account not found for account" + accountNumber);
        return false;
    }
    public int getAccountIndex(long accNumber){
        int accIndex = 0;
        // create a for loop that loops through the all_accounts arrayList
        // if the single account matches an account in that array
        // get the index, and set accIndex to that index.
        int i = 0;
        for (Account singleAccount : all_accounts) {
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

        // gets the account's SSN, and converts it to a string
        // takes this string and gets the last four digits of SSN
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
        // if the user is verified, ask to enter and verify the new PIN
        if(verified){
            System.out.println("Enter new PIN: ");
            int newPIN = scanner.nextInt();
            System.out.println("Enter new PIN again to confirm: ");
            int newPINconfirmed = scanner.nextInt();
            // If the new PIN matches the verified PIN,
            // call the getAccountIndex function and get the account's index
            // update the pin of that account and display a success message
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
        ArrayList<Object> info = promptForAccountNumberAndPIN();

        // the two values accessed from the info arrayList are cast into the correct type
        long accNumber = (long) info.get(0);
        int accPin = (int) info.get(1);
        // call the verifyUser function which will verify the user and return a boolean
        Boolean verified =  verifyUser(accNumber, accPin);
        // if the user is verified, ask to enter and verify the new PIN
        if(verified){
            System.out.println("Enter the amount to deposit in dollars and cents (e.g 2.57): ");
            double amountToDeposit = scanner.nextDouble();
            int accIndex = getAccountIndex(accNumber);
            double previousBalance = all_accounts.get(accIndex).getBalance();
            double newBalance = previousBalance + amountToDeposit;
            all_accounts.get(accIndex).setBalance(newBalance);
            System.out.printf("New balance: %g%n", all_accounts.get(accIndex).getBalance());
        } else{
            System.out.println("Wrong account number or PIN. Please try again.");
        }
    }
    public void transferBetweenAccounts() {
        System.out.println("Account to transfer from: ");
        ArrayList<Object> info = promptForAccountNumberAndPIN();

        // the two values accessed from the info arrayList are cast into the correct type
        long accNumber = (long) info.get(0);
        int accPin = (int) info.get(1);
        // call the verifyUser function which will verify the user and return a boolean
        Boolean verified =  verifyUser(accNumber, accPin);
        if (verified){
            System.out.println("Account to transfer to: ");
            ArrayList<Object> transferInfo = promptForAccountNumberAndPIN();

            // the two values accessed from the info arrayList are cast into the correct type
            long transferAccNumber = (long) transferInfo.get(0);
            int transferAccPin = (int) transferInfo.get(1);
            // call the verifyUser function which will verify the user and return a boolean
            Boolean transferVerified =  verifyUser(transferAccNumber, transferAccPin);
            if(transferVerified){
                System.out.println("Enter amount to transfer in dollars and cents. Ex 2.57 : ");
                double amountToTransfer = scanner.nextDouble();
                int indexOfFrom = getAccountIndex(accNumber);
                double balanceOfFrom = all_accounts.get(indexOfFrom).getBalance();
                if(amountToTransfer <= 0){
                    System.out.println("Amount cannot be negative. Try again.");
                    return;
                }
                // If the account to transfer from has less money than the transfer amount
                // Show "not enough funds" and have the user try again.
                if(balanceOfFrom > amountToTransfer){
                    int indexOfTo = getAccountIndex(transferAccNumber);
                    double balanceOfTo = all_accounts.get(indexOfTo).getBalance();
                    all_accounts.get(indexOfTo).setBalance(balanceOfTo + amountToTransfer);
                    all_accounts.get(indexOfFrom).setBalance(balanceOfFrom - amountToTransfer);
                    System.out.println("Transfer Complete! ");
                    System.out.printf("New balance in account: %d is: $ %g%n", accNumber, all_accounts.get(indexOfFrom).getBalance());
                    System.out.printf("New balance in account: %d is: $ %g%n", transferAccNumber,all_accounts.get(indexOfTo).getBalance());
                } else{
                    System.out.println("Not enough funds! Try again.");
                }
            }
        }
    }

    public void withdrawFromAccount() {
        ArrayList<Object> info = promptForAccountNumberAndPIN();

        // the two values accessed from the info arrayList are cast into the correct type
        long accNumber = (long) info.get(0);
        int accPin = (int) info.get(1);
        // call the verifyUser function which will verify the user and return a boolean
        Boolean verified =  verifyUser(accNumber, accPin);
        int index = getAccountIndex(accNumber);
        double balanceOfAcc = all_accounts.get(index).getBalance();
        // If the user is verified, ask them to enter a withdrawal amount
        // if the withdrawal amount is less than or equal to 0, print amount cannot be negative, return.
        if (verified){
            System.out.println("Enter an amount to withdraw in dollars and cents: ");
            double withdrawAmount = scanner.nextDouble();
            if(withdrawAmount <= 0){
                System.out.println("Amount cannot be negative. Try again.");
                return;
            }
            /*
             if the account has less money than what the user is trying to withdraw
             print no funds
             else, subtract the amount from the account's balance, print new acc balance
            */
            if (balanceOfAcc < withdrawAmount) {
                System.out.println("Not enough funds! Try again.");
            } else{
                double initialBal = all_accounts.get(index).getBalance();
                all_accounts.get(index).setBalance(initialBal - withdrawAmount);
                System.out.println("Dispensing money... Withdrawal Complete! ");
                System.out.printf("New balance is: $ %g%n", all_accounts.get(index).getBalance());
            }
        }
    }

    public void withdrawFromATM() {
        ArrayList<Object> info = promptForAccountNumberAndPIN();

        // the two values accessed from the info arrayList are cast into the correct type
        long accNumber = (long) info.get(0);
        int accPin = (int) info.get(1);
        // call the verifyUser function which will verify the user and return a boolean
        Boolean verified =  verifyUser(accNumber, accPin);
        int index = getAccountIndex(accNumber);
        double balanceOfAcc = all_accounts.get(index).getBalance();
        /*
         If the user is verified, ask them to enter a withdrawal amount
         if the withdrawal amount is less than or equal to 0, print amount cannot be negative, return.
        */
        if (verified) {
            System.out.println("Enter an amount to withdraw in dollars (no cents) in multiples of $5 (limit $1000) :");
            int withdrawAmount = scanner.nextInt();
            // if withdraw amount is less than 5, greater than 1000, or not divisible by 5
            // display "invalid amount", return to menu.
            if (withdrawAmount < 5 | withdrawAmount > 1000 | withdrawAmount % 5 != 0) {
                System.out.println("Invalid amount. Try again.");
            } else{
                /*
                 divide the original amount by 20, and take the result and multiply by 20
                 take the above result and subtract it from the original amount.
                 the above result become the new amount
                 Repeat the above for 10 and 5.
                */
                int twenty = Math.floorDiv(withdrawAmount, 20);
                int newAmount = twenty * 20;
                newAmount = withdrawAmount - newAmount;
                int ten = Math.floorDiv(newAmount , 10);
                newAmount = ten * 10;
                newAmount = newAmount - ten;
                int five = Math.floorDiv(newAmount , 5);
                /*
                 Withdraw from account and show number of bills
                 display final account balance.
                */
                all_accounts.get(index).setBalance(balanceOfAcc - withdrawAmount);
                System.out.printf("Number of 20-Dollar bills: %d%n", twenty);
                System.out.printf("Number of 10-Dollar bills: %d%n", ten);
                System.out.printf("Number of 5-Dollar bills: %d%n", five);
                System.out.printf("New balance: $ %g%n", all_accounts.get(index).getBalance());
            }
        }
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
