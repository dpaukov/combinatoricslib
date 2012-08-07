package org.paukov.combinatorics;

import org.paukov.combinatorics.composition.IntegerCompositionGenerator;
import org.paukov.combinatorics.subsets.IntegerSubSetGenerator;

/**
 * This is a special factory to create integer vectors and generators like
 * 
 * @author Dmytro Paukov
 * @version 2.0
 * @see IntegerVector
 * @see IntegerGenerator
 */
public class IntegerFactory {

	/**
	 * Creates an empty integer vector instance
	 * 
	 * @return The empty vector
	 */
	public static IntegerVector createIntegerVector() {
		return new IntegerVector(0);
	}

	/**
	 * Creates an empty integer vector instance
	 * 
	 * @return The empty vector
	 */
	public static IntegerVector createIntegerVector(int size) {
		return new IntegerVector(size);
	}

	/**
	 * Creates an integer vector from the given integer array
	 * 
	 * @param array
	 *            The array
	 * @return The integer vector
	 */
	public static IntegerVector createIntegerVector(int[] array) {
		return new IntegerVector(array);
	}

	/**
	 * Creates an integer vector from the given integer array by the length
	 * <code>len</code>
	 * 
	 * @param array
	 *            The array
	 * @param len
	 *            the length
	 * @return The integer vector
	 */
	public static IntegerVector createIntegerVector(int[] array, int len) {
		return new IntegerVector(array, len);
	}

	/**
	 * Creates a sub-set generator
	 * 
	 * @param originalVector The initial vector
	 */
	public static IntegerGenerator createIntegerSubSetGenerator(
			IntegerVector originalVector) {
		return new IntegerSubSetGenerator(originalVector);
	}

	/**
	 * Creates a composition generator
	 * 
	 * @param n The initial value
	 */
	public static IntegerGenerator createIntegerCompositionGenerator(Integer n) {
		return new IntegerCompositionGenerator(n);
	}

	/**
	 * This method creates a combinatorics vector of (1, 2,.., n)
	 * 
	 * @param n
	 *            The value of the number of the elements
	 * @return The vector
	 */
	public static IntegerVector range(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Range value must be more or equal to 0");

		// If range is zero return an empty vector
		if (n == 0)
			return new IntegerVector();

		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = i + 1;

		return new IntegerVector(array);
	}

	/**
	 * This method creates a combinatorics vector of (from, from + 1,.., to-1,
	 * to)
	 * 
	 * @param from
	 *            The first value
	 * @param to
	 *            The second value
	 * @return The vector
	 */
	public static IntegerVector range(int from, int to) {
		if (from > to)
			throw new IllegalArgumentException(
					"From parameter must be less then To parameter");

		int[] array = new int[to - from + 1];
		for (int i = 0; i < to - from + 1; i++)
			array[i] = new Integer(i + from);

		return new IntegerVector(array);
	}
}
