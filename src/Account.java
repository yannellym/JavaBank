public class Account {
    public long accNumber;
    public String ownerFirstName;
    public String ownerLastName;
    public long ssn;
    public int PIN;
    public double balance;
    public Account(long accNumber, String ownerFirstName, String ownerLastName, long ssn, int PIN){
        this.accNumber = accNumber;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.setSsn(ssn);
        this.PIN = PIN;
        this.balance = 0.00;
    }
    public long getAccNumber() {
        return accNumber;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }
    public String getOwnerLastName() {
        return ownerLastName;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
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
}
