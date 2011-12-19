package org.paukov.combinatorics.util;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.math.BigDecimal;

import org.paukov.combinatorics.CombinatoricsVector;

/**
 * Utility class for combinatorial package
 * 
 * @author Dmytro.Paukov
 * @see CombinatoricsVector
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

    public static Point2D calculateIntersection(Line2D line1, Line2D line2) {
	double a, b, c, d, e, f, g, h;
	if (line1.intersectsLine(line2)) {
	    a = line1.getX1();
	    b = line1.getY1();
	    c = line2.getX1();
	    d = line2.getY1();
	    e = line1.getX2();
	    f = line1.getY2();
	    g = line2.getX2();
	    h = line2.getY2();

	    double x, y;

	    if (line1.getX2() == line1.getX1()
		    && line2.getY2() == line2.getY1()) {
		x = line1.getX1();
		y = line2.getY1();
	    } else if (line1.getY2() == line1.getY1()
		    && line2.getX2() == line2.getX1()) {
		x = line2.getX1();
		y = line1.getY1();
	    } else {

		// if (line1.getX2() < line1.getX1()) {
		// e = line1.getX1();
		// a = line1.getX2();
		// }
		// if (line1.getY2() < line1.getY1()) {
		// f = line1.getY1();
		// b = line1.getY2();
		// }
		// if (line2.getX2() < line2.getX1()) {
		// g = line2.getX1();
		// c = line2.getX2();
		// }
		// if (line2.getY2() < line2.getY1()) {
		// h = line2.getY1();
		// d = line2.getY2();
		// }
		double t1 = -a * d + a * h + b * c - b * g - c * h + d * g;
		double zn = a * d - a * h - b * c + b * g + c * f - d * e + e
			* h - f * g;

		if (zn == 0.0)
		    return null;

		t1 = t1 / zn;
		double t2 = a * d - a * f - b * c + b * e + c * f - d * e;
		t2 = t2 / (-zn);

		x = a + t1 * (e - a);
		y = b + t1 * (f - b);
	    }
	    return new Point2D.Double(x, y);
	}
	return null;
    }

    /**
     * Calculates determinant of the matrix a
     */
    public static double det(double[][] a, final double eps) {
	int n = a.length;
	double res = 1;
	boolean[] used = new boolean[n];
	for (int i = 0; i < n; i++) {
	    int p;
	    for (p = 0; p < n; p++)
		if (!used[p] && Math.abs(a[p][i]) > eps)
		    break;
	    if (p >= n)
		return 0;
	    res *= a[p][i];
	    used[p] = true;
	    double z = 1 / a[p][i];
	    for (int j = 0; j < n; j++)
		a[p][j] *= z;
	    for (int j = 0; j < n; ++j)
		if (j != p) {
		    z = a[j][i];
		    for (int k = 0; k < n; ++k)
			a[j][k] -= z * a[p][k];
		}
	}
	return res;
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
