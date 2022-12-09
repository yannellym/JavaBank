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
        System.out.println("Please enter your menu choice: ");
        int user_choice = scanner.nextInt();
        // valid user choices
        int[] choices = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

//      if the user choice is a number
//      and the user choice is in the valid user choices
//      print the user's choice from the menu
//      else, if the user choice is not in the valid user choices, redirect them to the menu again
//      if the user choice is not a number, redirect them to the menu again
        try {
            Boolean checkChoice = false;
            for (int option : choices) {
                if (option == user_choice) {
                    checkChoice = true;
                }
            }
            if (checkChoice){
                switch (user_choice) {
                    case 1:
                        utilities.openAccount();
                        break;
                    case 2:
                        utilities.getAccountInfoAndBalance();
                        break;
                    case 3:
                        utilities.changePin();
                        break;
                    case 4:
                        utilities.depositMoneyToAccount();
                        break;
                    case 5:
                        utilities.transferBetweenAccounts();
                        break;
                    case 6:
                        utilities.withdrawFromAccount();
                        break;
                    case 7:
                        utilities.withdrawFromATM();
                        break;
                    case 8:
                        utilities.depositChange();
                        break;
                    case 9:
                        utilities.closeAccount();
                        break;
                    case 10:
                        utilities.addMonthlyInterest();
                        continousMenu();
                    case 11:
                        System.out.println("Thank you for using JavaBank. Have a good day!");
                        break;
                }
            }
            else{
                System.out.println("Please enter a numeric character. Try again. ");
                continousMenu();
            }
        } catch (Exception e) {
            System.out.println("Choice is not in options. Please try again");
            continousMenu();
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

