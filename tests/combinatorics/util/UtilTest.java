package combinatorics.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

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
    public void determinant1Test() {

	double result = Util.det(new double[][] { { 2, 4, 3, 5, 4 },
		{ 5, 4, 0, 2, 4 }, { 0, 5, 5, 2, 3 }, { 1, 0, 4, 3, 0 },
		{ 0, 5, 1, 4, 4 } }, 1e-10);

	assertEquals(279.0, result, 1e-10);
    }

    @Test
    public void determinant2Test() {

	double result = Util.det(new double[][] { { 3, 2, 2 }, { 0, 0, 5 },
		{ 4, 3, 1 } }, 1e-10);

	assertEquals(-5.0, result, 1e-10);
    }

    @Test
    public void determinant3Test() {

	double result = Util.det(new double[][] { { 2, 2, 2 }, { 1, 2, 0 },
		{ 2, 2, 0 } }, 1e-10);

	assertEquals(-4.0, result, 1e-10);
    }

    @Test
    public void determinantCrout1Test() {

	BigDecimal result = Util.detCrout(toBigDecimal(new double[][] {
		{ 2, 4, 3, 5, 4 }, { 5, 4, 0, 2, 4 }, { 0, 5, 5, 2, 3 },
		{ 1, 0, 4, 3, 0 }, { 0, 5, 1, 4, 4 } }), 5);

	assertEquals(279.0, result.doubleValue(), 1e-10);
    }

    @Test
    public void determinantCrout2Test() {

	BigDecimal result = Util.detCrout(toBigDecimal(new double[][] {
		{ 3, 2, 2 }, { 0, 0, 5 }, { 4, 3, 1 } }), 3);

	assertEquals(-5.0, result.doubleValue(), 1e-10);
    }

    @Test
    public void determinantCrout3Test() {

	BigDecimal result = Util.detCrout(toBigDecimal(new double[][] {
		{ 2, 2, 2 }, { 1, 2, 0 }, { 2, 2, 0 } }), 3);

	assertEquals(-4.0, result.doubleValue(), 1e-10);
    }
}
