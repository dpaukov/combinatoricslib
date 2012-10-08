/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.util;

import java.math.BigDecimal;


/**
 * Utility class for combinatorial package
 * 
 * @author Dmytro Paukov
 * @version 2.0
 */
public class Util {

	/**
	 * Get minimum of three values
	 */
	public static int minimum(int a, int b, int c) {
		int mi = a;
		if (b < mi)
			mi = b;

		if (c < mi)
			mi = c;

		return mi;
	}

	/**
	 * Calculates factorial of the given integer value <code>x</code>
	 * 
	 * @param x
	 *            Integer value
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
	 */
	public static long pow2(long x) {
		long result = 1;
		for (long i = 1; i <= x; i++) {
			result *= 2;
		}
		return result;
	}

	/**
	 * Calculates the number of k-combinations (each of size k) from a set with
	 * n elements (size n) (also known as the "choose function")
	 * 
	 * @param n
	 *            Value n
	 * @param k
	 *            Value k
	 */
	public static long combination(long n, long k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}

	/**
	 * Calculates greatest common divisor (GCD) of two integer values
	 * <code>a</code> and <code>b</code>
	 * 
	 * @param a
	 *            Value a
	 * @param b
	 *            Value b
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
	 * Calculates lowest common multiple (LCM) of two integer values
	 * <code>a</code> and <code>b</code>
	 * 
	 * @param a
	 *            Value a
	 * @param b
	 *            Value b
	 */
	public static long lcm(long a, long b) {
		return (a * b) / gcd(a, b);
	}

	/**
	 * The linear interpolant is the straight line between these points
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 * @param x
	 * @return y
	 */
	public static double linearInterpolation(double x0, double y0, double x1,
			double y1, double x) {
		return y0 + ((y1 - y0) / (x1 - x0)) * (x - x0);
	}

	/**
	 * Levenshtein distance is a measure of the similarity between two strings,
	 * which we will refer to as the source string <code>source</code> and the
	 * target string <code>target</code>. The distance is the number of
	 * deletions, insertions, or substitutions required to transform s into t.
	 * 
	 * @param source
	 *            the source string
	 * @param target
	 *            the target string
	 * @return Levenshtein distance
	 */
	public static int levenshteinDistance(String source, String target) {

		int d[][]; // matrix
		int n; // length of source
		int m; // length of target
		int i; // iterates through source
		int j; // iterates through target
		char s_i; // ith character of source
		char t_j; // jth character of target
		int cost; // cost

		// Step 1
		n = source.length();
		m = target.length();

		if (n == 0)
			return m;

		if (m == 0)
			return n;

		d = new int[n + 1][m + 1];

		// Step 2
		for (i = 0; i <= n; i++)
			d[i][0] = i;

		for (j = 0; j <= m; j++)
			d[0][j] = j;

		// Step 3
		for (i = 1; i <= n; i++) {
			s_i = source.charAt(i - 1);

			// Step 4
			for (j = 1; j <= m; j++) {
				t_j = target.charAt(j - 1);

				// Step 5
				if (s_i == t_j)
					cost = 0;
				else
					cost = 1;

				// Step 6
				d[i][j] = Util.minimum(d[i - 1][j] + 1, d[i][j - 1] + 1,
						d[i - 1][j - 1] + cost);
			}
		}

		// Step 7
		return d[n][m];
	}

	/**
	 * Calculates the determinant of the matrix a
	 */
	public static BigDecimal detCrout(BigDecimal a[][], int n) {
		try {
			for (int i = 0; i < n; i++) {
				boolean nonzero = false;
				for (int j = 0; j < n; j++)
					if (a[i][j].compareTo(new BigDecimal(0)) > 0)
						nonzero = true;
				if (!nonzero)
					return BigDecimal.ZERO;
			}

			BigDecimal scaling[] = new BigDecimal[n];
			for (int i = 0; i < n; i++) {
				BigDecimal big = new BigDecimal(0);
				for (int j = 0; j < n; j++)
					if (a[i][j].abs().compareTo(big) > 0)
						big = a[i][j].abs();
				scaling[i] = (new BigDecimal(1)).divide(big, 100,
						BigDecimal.ROUND_HALF_EVEN);
			}

			int sign = 1;

			for (int j = 0; j < n; j++) {

				for (int i = 0; i < j; i++) {
					BigDecimal sum = a[i][j];
					for (int k = 0; k < i; k++)
						sum = sum.subtract(a[i][k].multiply(a[k][j]));
					a[i][j] = sum;
				}

				BigDecimal big = new BigDecimal(0);
				int imax = -1;
				for (int i = j; i < n; i++) {
					BigDecimal sum = a[i][j];
					for (int k = 0; k < j; k++)
						sum = sum.subtract(a[i][k].multiply(a[k][j]));
					a[i][j] = sum;
					BigDecimal cur = sum.abs();
					cur = cur.multiply(scaling[i]);
					if (cur.compareTo(big) >= 0) {
						big = cur;
						imax = i;
					}
				}

				if (j != imax) {

					for (int k = 0; k < n; k++) {
						BigDecimal t = a[j][k];
						a[j][k] = a[imax][k];
						a[imax][k] = t;
					}

					BigDecimal t = scaling[imax];
					scaling[imax] = scaling[j];
					scaling[j] = t;

					sign = -sign;
				}

				if (j != n - 1)
					for (int i = j + 1; i < n; i++)
						a[i][j] = a[i][j].divide(a[j][j], 100,
								BigDecimal.ROUND_HALF_EVEN);

			}

			BigDecimal result = new BigDecimal(1);
			if (sign == -1)
				result = result.negate();
			for (int i = 0; i < n; i++)
				result = result.multiply(a[i][i]);

			return result;

		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}
}
