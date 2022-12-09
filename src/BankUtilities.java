import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BankUtilities{
    Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param prompt a String that represents a prompt to print on the screen. Ex "Enter your name"
     * @return String. The input of the user.
     */
    public String promptUserForString(String prompt){
        System.out.println(prompt);
        String name = scanner.next();
        return name;
    }

    /**
     * @param prompt
     * @return a DOUBLE representing a positive number
     */
    public double promptUserForPositiveNumber(String prompt){
        System.out.println(prompt);
        Double positiveNum = scanner.nextDouble();

        while (positiveNum <= 0){
            System.out.println("Amount cannot be negative. Try again. ");
            return promptUserForPositiveNumber(prompt);
        }
        return positiveNum;
    }
    public long convertFromDollarsToCents(double dollars){

        return (long) dollars;
    }

    /**
     * @params minVal,  maxVal are both of type int. They represent the range
     * @return an int representing a random number
     */
    public int generateRandomInteger( int minVal, int maxVal){
        int randomNumber = ThreadLocalRandom.current().nextInt(minVal, maxVal + 1);
        return randomNumber;
    }


}
