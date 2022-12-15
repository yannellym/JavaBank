import java.util.Scanner;

public class BankManager extends Bank {
    Scanner scanner = new Scanner(System.in);
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

    public void continuousMenu() {
        printMenu();
        System.out.println("Please enter your menu choice: ");
        // parse the input of the user into an int.
        int user_choice = Integer.parseInt(scanner.next());
        // valid user choices
        int[] choices = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

//      if the user choice is a number
//      and the user choice is in the valid user choices
//      print the user's choice from the menu
//      else, if the user choice is not in the valid user choices, redirect them to the menu again
//      if the user choice is not a number, redirect them to the menu again
        try {
            int choice = user_choice;
            Boolean checkChoice = false;
            for (int option : choices) {
                if (option == choice) {
                    checkChoice = true;
                }
            }
            if (!checkChoice){
                System.out.println("This choice is not a menu option. Please try again");
                continuousMenu();
            }
        } catch (Exception InputMismatchException) {
            System.out.println("Choice is not a number. Please try again");
            continuousMenu();
        }

        if (user_choice == 11) {
            System.out.println("Thank you for using JavaBank. Have a good day!");
        } else {
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
                    continuousMenu();
                case 12:
                    System.out.println(utilities.all_accounts);
            }
            continuousMenu();
        }
    }
}
