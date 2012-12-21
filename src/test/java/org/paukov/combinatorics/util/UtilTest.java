/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.paukov.combinatorics.util.Util;

/**
 * @author Dmytro Paukov
 * 
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
