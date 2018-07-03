import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

        sumOfDig(FactorialUtil.factorial(100));

    }

    //in this method we get from number each digit by transform it to char array
    //then sum all digits
    private static void sumOfDig(BigInteger number) {

        char[] digits = number.toString().toCharArray();
        int result = 0;

        for (char element : digits) {
            result += Integer.parseInt(String.valueOf(element));
        }

        System.out.println(result);
    }

}
