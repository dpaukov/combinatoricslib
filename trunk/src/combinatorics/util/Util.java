package combinatorics.util;

/**
 * Utility class for combinatorial package
 */
public class Util {

  /**
   * Calculates factorial of the given integer value <code>x</code>
   * 
   * @param x Integer value
   */
  public static long factorial(long x) {
    long result = 1;
    for (long i = 2; i <= x; i++) {
      result *= i;
    }
    return result;
  }

  /**
   * Calculates 2 in power of integer value <code>x</code>
   * 
   * @param x
   * @return
   */
  public static long pow2(long x) {
    long result = 1;
    for (long i = 1; i <= x; i++) {
      result *= 2;
    }
    return result;
  }

  /**
   * Calculates the number of k-combinations (each of size k) from a set with n elements (size n) (also known as the
   * "choose function")
   * 
   * @param n Value n
   * @param k Value k
   */
  public static long combination(long n, long k) {
    return factorial(n) / (factorial(k) * factorial(n - k));
  }

  /**
   * Calculates greatest common divisor (GCD) of two integer values <code>a</code> and <code>b</code>
   * 
   * @param a Value a
   * @param b Value b
   */
  public static long gcd(long a, long b) {
    if (a == 0)
      return b;
    if (b == 0)
      return a;
    if (a == b)
      return a;
    if (a == 1 | b == 1)
      return 1;
    if ((a % 2 == 0) & (b % 2 == 0))
      return 2 * gcd(a / 2, b / 2);
    if ((a % 2 == 0) & (b % 2 != 0))
      return gcd(a / 2, b);
    if ((a % 2 != 0) & (b % 2 == 0))
      return gcd(a, b / 2);
    return gcd(b, Math.abs(a - b));
  }

  /**
   * Calculates lowest common multiple (LCM) of two integer values <code>a</code> and <code>b</code>
   * 
   * @param a Value a
   * @param b Value b
   */
  public static long lcm(long a, long b) {
    return (a * b) / gcd(a, b);
  }
}
