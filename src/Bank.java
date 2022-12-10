
public class Bank{
    public void main(String[] args) {
        Account[] all_accounts;
        // Allocating memory for 2 objects
        // of type student
        all_accounts = new Account[2];

        // Initializing the first element
        // of the array
        all_accounts[0] = new Account();
        all_accounts[0].setData(12345,"Nelly","Merc",4213, 9999, 500.58);
    }
}
