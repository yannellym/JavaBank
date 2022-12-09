import java.util.Scanner;

public class BankManager {
    Scanner scanner = new Scanner(System.in);
    Bank bank = new Bank();
    BankUtilities utilities = new BankUtilities();

    public void printMenu() {
        String menu = """
                   ============================================================
                   What do you want to do?
                  1. Open an account
                  2. Get account information and balance
                  3. Change PIN
                  4. Deposit money in account
                  5. Transfer money between accounts
                  6. Withdraw money from account
                  7. ATM withdrawal
                  8. Deposit change
                  9. Close an account
                  10. Add monthly interest to all accounts
                  11. End Program
                """;
        System.out.println(menu);
    }

    public void continousMenu() {
        printMenu();
        int user_choice = scanner.nextInt();
        // valid user choices
        int[] choices = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

//      if the user choice is a number
//      and the user choice is in the valid user choices
//      print the user's choice from the menu
//      else, if the user choice is not in the valid user choices, redirect them to the menu again
//      if the user choice is not a number, redirect them to the menu again
        try {
            for (int option : choices) {
                if (option == user_choice) {
                    switch (user_choice) {
                        case 1:
                            return utilities.openAccount();
                        case 2:
                            return utilities.getAccountInfoAndBalance();
                        case 3:
                            return utilities.changePin();
                        case 4:
                            return utilities.depositMoneyToAccount();
                        case 5:
                            return utilities.transferBetweenAccounts();
                        case 6:
                            return utilities.withdrawFromAccount();
                        case 7:
                            return utilities.withdrawFromATM();
                        case 8:
                            return utilities.depositChange();
                        case 9:
                            return utilities.closeAccount();
                        case 10:
                            utilities.addMonthlyInterest();
                            continousMenu();
                        case 11:
                            System.out.println("Thank you for using JavaBank. Have a good day!");
                            break;
                        continousMenu();
                    }
                } else {
                    System.out.println("Please enter a numeric character. Try again. ");
                    continousMenu();
                }
            }
        }
    }
}
//    public Account promptForAccountNumberAndPIN(Bank bank){
//        System.out.println("Please enter an account number: ");
//        long accNumber = scanner.nextLong();
//        for (singleAccount : bank.allAccounts){
//            if (singleAccount.accNumber == accNumber){
//                System.out.println("Please enter PIN:");
//                int accPin = scanner.nextInt();
//                if(accin != accNumber.PIN){
//                    System.out.println("Invalid PIN");
//                    return null;
//                }
//                else{
//                    return accNumber;
//                }
//            }
//        }
//        System.out.println("Account not found for account number:" + accNumber);
//        return null;
//    }

