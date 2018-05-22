/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;

/**
 * @author Dmytro Paukov
 */
public class UtilTest {

  private static BigDecimal[][] toBigDecimal(double[][] a) {
    int n = a.length;
    BigDecimal[][] b = new BigDecimal[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        b[i][j] = BigDecimal.valueOf(a[i][j]);
      }
    }
    return b;
  }

  @Test
  public void determinantCrout1Test() {

    BigDecimal result = Util.detCrout(toBigDecimal(new double[][]{
        {2, 4, 3, 5, 4}, {5, 4, 0, 2, 4}, {0, 5, 5, 2, 3},
        {1, 0, 4, 3, 0}, {0, 5, 1, 4, 4}}), 5);

    assertEquals(279.0, result.doubleValue(), 1e-10);
  }

  @Test
  public void determinantCrout2Test() {

    BigDecimal result = Util.detCrout(toBigDecimal(new double[][]{
        {3, 2, 2}, {0, 0, 5}, {4, 3, 1}}), 3);

    assertEquals(-5.0, result.doubleValue(), 1e-10);
  }

  @Test
  public void determinantCrout3Test() {

    BigDecimal result = Util.detCrout(toBigDecimal(new double[][]{
        {2, 2, 2}, {1, 2, 0}, {2, 2, 0}}), 3);

    assertEquals(-4.0, result.doubleValue(), 1e-10);
  }


  @Test
  public void test_pow2() {
    assertEquals(1L, Util.pow2(-1));
    assertEquals(1L, Util.pow2(0));
    assertEquals(2L, Util.pow2(1));
    assertEquals(4L, Util.pow2(2));
    assertEquals(8L, Util.pow2(3));
    assertEquals(16L, Util.pow2(4));
    assertEquals(32L, Util.pow2(5));
    assertEquals(64L, Util.pow2(6));

  }

  @Test
  public void test_factorial() {
    assertEquals(1L, Util.factorial(-1));
    assertEquals(1L, Util.factorial(0));
    assertEquals(1L, Util.factorial(1));
    assertEquals(2L, Util.factorial(2));
    assertEquals(6L, Util.factorial(3));
    assertEquals(24L, Util.factorial(4));
    assertEquals(3628800L, Util.factorial(10));
  }

  @Test
  public void test_big_factorial() {
    assertEquals(BigDecimal.ONE, Util.bigFactorial(-1));
    assertEquals(BigDecimal.ONE, Util.bigFactorial(0));
    assertEquals(BigDecimal.ONE, Util.bigFactorial(1));
    assertEquals(BigDecimal.valueOf(2), Util.bigFactorial(2));
    assertEquals(BigDecimal.valueOf(6), Util.bigFactorial(3));
    assertEquals(BigDecimal.valueOf(24), Util.bigFactorial(4));
    assertEquals(BigDecimal.valueOf(3628800), Util.bigFactorial(10));
    assertEquals(new BigDecimal("1307674368000"), Util.bigFactorial(15));
  }

  @Test
  public void test_minimum() {
    assertEquals(1, Util.minimum(1, 2, 3));
    assertEquals(1, Util.minimum(2, 1, 3));
    assertEquals(1, Util.minimum(2, 3, 1));
  }

  @Test
  public void test_gcd() {
    assertEquals(6, Util.gcd(12, 18));
    assertEquals(1, Util.gcd(103, 45));
    assertEquals(2, Util.gcd(64, 34));

    assertEquals(45, Util.gcd(0, 45));
    assertEquals(64, Util.gcd(64, 0));
  }

  @Test
  public void test_lcm() {
    assertEquals(36, Util.lcm(12, 18));
    assertEquals(2070, Util.lcm(46, 45));
    assertEquals(330, Util.lcm(66, 15));
  }

  @Test
  public void test_levenshteinDistance() {
    assertEquals(1, Util.levenshteinDistance("hello", "helo"));
    assertEquals(2, Util.levenshteinDistance("america", "amerigo"));
    assertEquals(4, Util.levenshteinDistance("abba", "abcadaba"));

    assertEquals(5, Util.levenshteinDistance("hello", ""));
    assertEquals(5, Util.levenshteinDistance("", "hello"));
  }
}
