import java.util.ArrayList;

public class Bank{
    ArrayList <Account> all_accounts = new ArrayList<>(2);
    public Boolean addAccountToBank(Account newAccount){
      if(all_accounts.size() < 3){
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
}
