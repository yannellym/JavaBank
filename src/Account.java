public class Account extends Bank{
    private final long accNumber;
    private final String ownerFirstName;
    private final String ownerLastName;
    private long ssn;
    private int PIN;
    private float balance;


    public long getAccNumber() {
        return accNumber;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    Account(long accNumber, String ownerFirstName, String ownerLastName, long ssn, int PIN, float balance){
        this.accNumber = accNumber;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.ssn = ssn;
        this.PIN = PIN;
        this.balance = balance;
    }
    public long deposit(long depositAmount){
        // take the amount and deposit it into the account
        // return a long representing a new account balance
        return 1234;
    }
    public long withdraw(long withdrawAmount){
        // take the amount, subtract it from the account balance
        // return a long representing the new account balance
        return 5000;
    }
    public boolean isValidPin(String PIN){
        // compare pIN with PIN on the account
        // if PINS match, return true , false otherwise.
        return true;
    }
    public String toString(){
        // return a string that contains all values in account
        return "String";
    }

    public static void main(String[] args) {

    }
}
