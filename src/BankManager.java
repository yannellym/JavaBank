import java.util.Scanner;

public class BankManager extends Bank {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

    }

    public void printMenu(){
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

    public void promptForAccountNumberAndPIN(Bank bankObj){
        System.out.println("Please enter an account number: ");
        long accNumber = scanner.nextLong();
        for (long numberInArr : bankObj.accounts){
            if (numberInArr == accNumber){
                System.out.println("Please enter PIN:");
            }
        }
        System.out.println("Account not found for account number:" + accNumber);
    }
}
