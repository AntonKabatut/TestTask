import java.util.Scanner;

public class Main {

    /**
     * This task is solved with the help of Catalan numbers.
     * Catalan Numbers is a sequence of numbers expressing the number of valid parentheses sequences consisting of n
     * opening and n closing parentheses.
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double countOfBrackets = scanner.nextDouble();

        System.out.println((long) calcAmountOfOptions(countOfBrackets));

    }

    //Using the recursive method, we implement the formula for finding the catalan numbers
    private static double calcAmountOfOptions(double count) {

        if (count == 1) {
            return 1;
        }

        double result = (4 * count - 2) / (count + 1);

        return result * calcAmountOfOptions(count - 1);
    }

}
