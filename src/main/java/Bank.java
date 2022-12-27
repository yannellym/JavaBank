import java.util.ArrayList;
import java.util.Scanner;

public class Bank{
    Scanner scanner = new Scanner(System.in);
    ArrayList <Account> all_accounts = new ArrayList<>(2);
    public Boolean addAccountToBank(Account newAccount){
      if(all_accounts.size() < 2){
        all_accounts.add(newAccount);
        return true;
      } else{
          System.out.println("No more accounts available.");
      }
      return false;
    }
    public Boolean removeAccountFromBank(Account singleAccount){
        for(Account individual_account: all_accounts){
            if(individual_account.getAccNumber() == singleAccount.getAccNumber()){
                int index = all_accounts.indexOf(singleAccount);
                all_accounts.remove(index);
                return true;
            }
        }
        return false;
    }
    public void addMonthlyInterest() {
         /*
            calculate the monthly rate by taking the annual rate and multiplying it by
             the account's balance. Then, divided this by 12, signifying the months in a year
             finally, divide by 100 to get the interest.
         */
        System.out.println("Enter an annual interest rate percentage: ");
        float annualRate = scanner.nextFloat();
        for (Account singleAccount: all_accounts) {
            System.out.println(singleAccount.getAccNumber());
            double monthlyRate = ((annualRate * singleAccount.getBalance()) / 12) / 100;
            double newBalance = singleAccount.deposit(monthlyRate);
            System.out.printf("Deposited interest: %g into account number: %d%n", monthlyRate, singleAccount.getAccNumber());
            System.out.printf("New balance: %g%n", newBalance);
        }
    }
}
