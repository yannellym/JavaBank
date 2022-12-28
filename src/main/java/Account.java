public class Account extends Bank{
    private final long accNumber;
    private final String ownerFirstName;
    private final String ownerLastName;
    private long ssn;
    private int PIN;
    private double balance;
    public Account(long accNumber, String ownerFirstName, String ownerLastName, long ssn, int PIN){
        this.accNumber = accNumber;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.setSsn(ssn);
        this.setPIN(PIN);
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

    public double deposit(double depositAmount){
        double previousBalance = this.getBalance();
        double newBalance = previousBalance + depositAmount;
        this.setBalance(newBalance);
        return newBalance;
    }
    public double withdraw(double withdrawAmount){
        double initialBalance = this.getBalance();
        double newBalance = initialBalance - withdrawAmount;
        this.setBalance(newBalance);
        return newBalance;
    }
    public boolean isValidPin(int PIN) {
        // compare PIN with PIN on the account
        // if PINs match, return true , false otherwise.
        if (PIN == this.getPIN()) {
            return true;
        } else {
            System.out.println("Invalid PIN");
            return false;
        }
    }
}
