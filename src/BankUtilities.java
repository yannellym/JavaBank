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

    public static boolean isInt(String s){
        boolean res;

        try {
            Integer.parseInt(s);
            res = true;
        } catch (NumberFormatException e) {
            res = false;
        }

        return res;
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
    public String promptUserForString(String prompt) {
        System.out.println(prompt);
        return scanner.next();
    }
    public void openAccount() {
        String firstName = promptUserForString("Enter your first name: ");
        String lastName = promptUserForString("Enter your last name: ");
        System.out.println("Enter your SSN: ");
        long SSN = scanner.nextLong();

        if(isInt(firstName) | isInt(lastName) | firstName.length() < 3 | lastName.length() < 3){
            System.out.println("Names must be letters only.");
            openAccount();
        } else if(String.valueOf(SSN).length() != 9){
            System.out.println("SSN must be 9 digits long. Try again");
            openAccount();
        }else {
            // call the generateRandomInts function to generate random number
            // sequences for both the account numbers and the PIN
            // The function returns a string so this makes sure to parse it
            long acc_number = Long.parseLong(generateRandomInts(10));
            int pin = Integer.parseInt(generateRandomInts(4));
            // creates a new account
            Account newAccount = new Account(acc_number, firstName, lastName, SSN, pin);
            // adds new account to all_accounts in Bank array
            if (addAccountToBank(newAccount)) {
                System.out.println(" *** Account opened successfully! *** ");
                // prints out the account's information
                printAccountInfo(acc_number);
            }
        }
    }
    public ArrayList<Object> promptForAccountNumberAndPIN() {
        ArrayList<Object> res = new ArrayList<>();

        System.out.println("Please enter an account number: ");
        long accNumber = scanner.nextLong();

        System.out.println("Please enter PIN:");
        int accPin = scanner.nextInt();

        if((String.valueOf(accNumber)).length() != 10){
            System.out.println("Account number must be 10 digits long.");
            getAccountInfoAndBalance();
        } else if ((String.valueOf(accPin)).length() != 4){
            System.out.println("PIN must be 4 digits long.");
            getAccountInfoAndBalance();
        }
        // add both variables to the arrayList
        res.add(accNumber);
        res.add(accPin);

        // returns an arrayList of two values (account number, and account pin)
        return res;
    }
    public Boolean verifyUser(long accountNumber, int accPin) {
        /*
         looks through the all_accounts array list
         if an account in the array list matches the account number provided
         by the user, and if the pin in that account matches the pin provided
         by the user, return true
         if the pin is incorrect, print invalid pin and redirect to menu,
         if account number doesn't exist, print no account found and redirect
         to menu. If everything else doesn't execute, return false.
        */
        for (Account singleAccount : all_accounts) {
            if (singleAccount.getAccNumber() == accountNumber) {
                return singleAccount.isValidPin(accPin);
            }
        }
        System.out.printf("Account not found for account %d%n", accountNumber);
        return false;
    }
    public int getAccountIndex(long accNumber){
        int accIndex = 0;
        // create a for loop that loops through the all_accounts arrayList
        // if the single account matches an account in that array
        // get the index, and set accIndex to that index.
        int i = 0;
        for (Account singleAccount : all_accounts) {
            if (singleAccount.getAccNumber() == accNumber) {
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
            double newBalance = all_accounts.get(accIndex).deposit(amountToDeposit);
            System.out.printf("New balance: %g%n", newBalance);
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
                if(balanceOfFrom >= amountToTransfer){
                    int indexOfTo = getAccountIndex(transferAccNumber);
                    double newToBalance = all_accounts.get(indexOfTo).deposit(amountToTransfer);
                    double newFromBalance = all_accounts.get(indexOfFrom).withdraw(amountToTransfer);
                    System.out.println("Transfer Complete! ");
                    System.out.printf("New balance in account: %d is: $ %g%n", accNumber, newFromBalance);
                    System.out.printf("New balance in account: %d is: $ %g%n", transferAccNumber,newToBalance);
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
                double finalBal = all_accounts.get(index).withdraw(withdrawAmount);
                System.out.println("Dispensing money... Withdrawal Complete! ");
                System.out.printf("New balance is: $ %g%n",finalBal);
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
        /*
         If the user is verified, ask them to enter a withdrawal amount
         if the withdrawal amount is less than or equal to 0, print amount cannot be negative, return.
        */
        if (verified) {
            System.out.println("Enter an amount to withdraw in dollars (no cents) in multiples of $5 (limit $1000) :");
            int withdrawAmount = scanner.nextInt();
            // if withdraw amount is less than 5, greater than 1000, or not divisible by 5
            // display "invalid amount", return to menu.
            if(withdrawAmount > all_accounts.get(index).getBalance()){
                System.out.println("Insufficient funds. Please try again.");
            } else if (withdrawAmount < 5 | withdrawAmount > 1000 | withdrawAmount % 5 != 0) {
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
                newAmount -= ten * 10;
                int five = Math.floorDiv(newAmount , 5);
                /*
                 Withdraw from account and show number of bills
                 display final account balance.
                */
                double finalBal = all_accounts.get(index).withdraw(withdrawAmount);
                System.out.printf("Number of 20-Dollar bills: %d%n", twenty);
                System.out.printf("Number of 10-Dollar bills: %d%n", ten);
                System.out.printf("Number of 5-Dollar bills: %d%n", five);
                System.out.printf("New balance: $ %g%n", finalBal);
            }
        }
    }

    public void depositChange() {
        ArrayList<Object> info = promptForAccountNumberAndPIN();

        // the two values accessed from the info arrayList are cast into the correct type
        long accNumber = (long) info.get(0);
        int accPin = (int) info.get(1);
        // call the verifyUser function which will verify the user and return a boolean
        Boolean verified = verifyUser(accNumber, accPin);
        int index = getAccountIndex(accNumber);
        /*
         If the user is verified, ask them to enter a withdrawal amount
         if the withdrawal amount is less than or equal to 0, print amount cannot be negative, return.
        */
        if (verified) {
            // check each character from the user's input
            // match them with the value of the corresponding letter
            // add it to the deposit_amt variable
            // if coin not in the possible values, display it and don't add it to deposit
            System.out.println("Deposit coins (P: penny, N: nickel, D: dime, Q: Quarter, H: half-dollar, W: whole-dollar) Ex(QPDNNDHW):");
            String coins = scanner.next().toUpperCase();
            float deposit_amt = 0;
            for (char singleCoin : coins.toCharArray()) {
                switch (singleCoin) {
                    case 'P' -> deposit_amt += .01;
                    case 'N' -> deposit_amt += .05;
                    case 'D' -> deposit_amt += .10;
                    case 'Q' -> deposit_amt += .25;
                    case 'H' -> deposit_amt += .50;
                    case 'W' -> deposit_amt += 1;
                    default -> System.out.printf("Invalid coin: %s%n", singleCoin);
                }
            }
            // add the deposit amount to account
            // display the new balance
            double newBalance = all_accounts.get(index).deposit(deposit_amt);
            System.out.printf("$ %f in coins deposited into account %n", deposit_amt);
            System.out.printf("New balance: %g%n", newBalance);
        }
    }

    public void closeAccount() {
        ArrayList<Object> info = promptForAccountNumberAndPIN();

        // the two values accessed from the info arrayList are cast into the correct type
        long accNumber = (long) info.get(0);
        int accPin = (int) info.get(1);
        // call the verifyUser function which will verify the user and return a boolean
        Boolean verified = verifyUser(accNumber, accPin);
        int index = getAccountIndex(accNumber);
        /*
         If the user is verified, remove the account from the array list
         Print that the account has been closed
        */
        if (verified) {
            Boolean removed = removeAccountFromBank(all_accounts.get(index));
            if(removed){
                System.out.printf("Account %d has been closed %n", accNumber);
            } else {
                System.out.printf("Account %d has NOT been closed. Try again %n", accNumber);
            }
        }
    }
}
