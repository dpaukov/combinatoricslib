/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.composition;

import java.util.Iterator;

import org.paukov.combinatorics.IntegerFactory;
import org.paukov.combinatorics.IntegerGenerator;
import org.paukov.combinatorics.IntegerVector;
import org.paukov.combinatorics.util.Util;

/**
 * A composition of an integer n is a way of writing n as the sum of a sequence
 * of (strictly) positive integers. This class generates the composition if a
 * positive integer value.
 * <p>
 * A composition of an integer n is a way of writing n as the sum of a sequence
 * of (strictly) positive integers. Two sequences that differ in the order of
 * their terms define different compositions of their sum, while they are
 * considered to define the same partition of that number.
 * <p>
 * The sixteen compositions of 5 are:
 * <ol>
 * <li>5
 * <li>4+1
 * <li>3+2
 * <li>3+1+1
 * <li>2+3
 * <li>2+2+1
 * <li>2+1+2
 * <li>2+1+1+1
 * <li>1+4
 * <li>1+3+1
 * <li>1+2+2
 * <li>1+2+1+1
 * <li>1+1+3
 * <li>1+1+2+1
 * <li>1+1+1+2
 * <li>1+1+1+1+1.
 * </ol>
 * <p>
 * Compare this with the seven partitions of 5:
 * <ol>
 * <li>5
 * <li>4+1
 * <li>3+2
 * <li>3+1+1
 * <li>2+2+1
 * <li>2+1+1+1
 * <li>1+1+1+1+1.
 * </ol>
 * <p>
 * Example. Generate compositions all possible integer compositions of 5.
 * <p>
 * <blockquote>
 * 
 * <pre>
 *   // Create an instance of the integer composition generator to generate all possible compositions of 5
 *   IntegerGenerator gen = IntegerFactory.createIntegerCompositionGenerator(5);
 *
 *   // Print the compositions
 *   for (IntegerVector p : gen) {
 *      System.out.println(p);
 *   }
 * </pre>
 * 
 * </blockquote>
 * <p>
 * And the result
 * <p>
 * <blockquote>
 * 
 * <pre>
 *    IntegerVector=([5], size=1)
 *    IntegerVector=([1, 4], size=2)
 *    IntegerVector=([2, 3], size=2)
 *    IntegerVector=([1, 1, 3], size=3)
 *    IntegerVector=([3, 2], size=2)
 *    IntegerVector=([1, 2, 2], size=3)
 *    IntegerVector=([2, 1, 2], size=3)
 *    IntegerVector=([1, 1, 1, 2], size=4)
 *    IntegerVector=([4, 1], size=2)
 *    IntegerVector=([1, 3, 1], size=3)
 *    IntegerVector=([2, 2, 1], size=3)
 *    IntegerVector=([1, 1, 2, 1], size=4)
 *    IntegerVector=([3, 1, 1], size=3)
 *    IntegerVector=([1, 2, 1, 1], size=4)
 *    IntegerVector=([2, 1, 1, 1], size=4)
 *    IntegerVector=([1, 1, 1, 1, 1], size=5)
 * </pre>
 * 
 * </blockquote>
 * <p>
 * <p>
 * 
 * @author Dmytro Paukov
 * @see IntegerCompositionIterator
 * @version 2.0
 * 
 */
public class IntegerCompositionGenerator extends IntegerGenerator {

	public static final int MAXN = 100;

	protected final Integer _initialValue;

	/**
	 * Constructor
	 * 
	 * @param n
	 *            A positive integer value
	 */
	public IntegerCompositionGenerator(Integer n) {
		super();
		this._initialValue = n;
	}

	/**
	 * Returns value which is used to generate all compositions. This value
	 * returned as a element of vector. Vector has length of 1
	 */
	@Override
	public IntegerVector getOriginalVector() {
		return IntegerFactory.createIntegerVector(new int[] { _initialValue });
	}

	/**
	 * Returns number of the compositions
	 */
	@Override
	public long getNumberOfGeneratedObjects() {
		return Util.pow2(_initialValue - 1);
	}

	/**
	 * Creates the iterator over all compositions
	 */
	@Override
	public Iterator<IntegerVector> iterator() {
		return new IntegerCompositionIterator(this);
	}

}
