import java.math.BigInteger;
import java.util.HashMap;

class FactorialUtil {

    // use of the cache speeds up the calculation of the factorial if there is more than one number to enter the input,
    // here the factorial of only one number is calculated in the program and this makes the cache meaningless,
    // but if the task is expanded, it will be useful
    private static HashMap<Integer, BigInteger> cache = new HashMap<>();

    //recursive method for calculating the factorial of a number
    static BigInteger factorial(int n) {
        //BigInteger used with the expectation that factorials of a large number can go beyond the limit of int,
        // and in some cases beyond limit of long
        BigInteger ret;

        if (n == 0) return BigInteger.ONE;
        if (null != (ret = cache.get(n))) return ret;
        ret = BigInteger.valueOf(n).multiply(factorial(n - 1));
        cache.put(n, ret);
        return ret;
    }

}
