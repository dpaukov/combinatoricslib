/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.subsets;

import java.util.Iterator;

import org.paukov.combinatorics.IntegerFactory;
import org.paukov.combinatorics.IntegerGenerator;
import org.paukov.combinatorics.IntegerVector;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates all subsets of the specified set/vector of integer
 * values.
 * <p>
 * A set A is a subset of a set B if A is "contained" inside B. A and B may
 * coincide. The relationship of one set being a subset of another is called
 * inclusion or sometimes containment.
 * <p>
 * Examples:
 * <ul>
 * <li>The set (1, 2) is a proper subset of (1, 2, 3).
 * <li>Any set is a subset of itself, but not a proper subset.
 * <li>The empty set, denoted by (), is also a subset of any given set X.
 * </ul>
 * <p>
 * All subsets of (1, 2, 3) are:
 * <ol>
 * <li>()
 * <li>(1)
 * <li>(2)
 * <li>(1, 2)
 * <li>(3)
 * <li>(1, 3)
 * <li>(2, 3)
 * <li>(1, 2, 3)
 * </ol>
 * <p>
 * And code which generates all subsets of (1, 2, 3)
 * <p>
 * <blockquote>
 * 
 * <pre>
 * // Create an initial vector/set
 * IntegerVector initialSet = IntegerFactory.createIntegerVector(new int[] { 1, 2,
 * 		3 });
 * 
 * // Create an instance of the integer subset generator
 * IntegerGenerator gen = IntegerFactory.createIntegerSubSetGenerator(initialSet);
 * 
 * // Print the subsets
 * for (IntegerVector subSet : gen) {
 * 	System.out.println(subSet);
 * }
 * </pre>
 * 
 * </blockquote>
 * <p>
 * And the result of all 8 possible subsets
 * <p>
 * <blockquote>
 * 
 * <pre>
 *  IntegerVector=([], size=0)
 *  IntegerVector=([1], size=1)
 *  IntegerVector=([2], size=1)
 *  IntegerVector=([1, 2], size=2)
 *  IntegerVector=([3], size=1)
 *  IntegerVector=([1, 3], size=2)
 *  IntegerVector=([2, 3], size=2)
 *  IntegerVector=([1, 2, 3], size=3)
 * </pre>
 * 
 * </blockquote>
 * <p>
 * Version 2.0 of the combinatoricslib supports sets with duplicates. For
 * example, if the original vector contains duplicates like (1, 2, 1, 3), then
 * the result will contain 14 subsets (instead of 16): <blockquote>
 * 
 * <pre>
 * ()
 * (1)
 * (2)
 * (1, 2)
 * (1, 1)
 * (2, 1)
 * (1, 2, 1)
 * (3)
 * (1, 3)
 * (2, 3)
 * (1, 2, 3)
 * (1, 1, 3)
 * (2, 1, 3)
 * (1, 2, 1, 3)
 * </pre>
 * 
 * </blockquote>
 * <p>
 * If you still would like to treat the set with duplicates as not identical,
 * you should create a generator and set the second parameter of the method
 * <code>IntegerFactory.createIntegerSubSetGenerator()</code> as
 * <code>false</code>. In this case all 16 subsets will be generated.
 * <p>
 * <b>Note.</b> If the initial vector contains duplicates then the method
 * <code>getNumberOfGeneratedObjects</code> won't be able to return the number
 * of the sub sets/lists. It will throw a runtime exception
 * 
 * @author DmytroPaukov
 * @version 2.0
 * @see IntegerVector
 * @see IntegerSubSetIterator
 */
public class IntegerSubSetGenerator extends IntegerGenerator {

	protected final boolean _hasDuplicates;
	protected final boolean _treatAsIdentical;

	/**
	 * Core set
	 */
	protected final IntegerVector _originalVector;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original vector/set
	 */
	public IntegerSubSetGenerator(IntegerVector originalVector) {
		_hasDuplicates = originalVector.hasDuplicates();
		_treatAsIdentical = true;
		_originalVector = IntegerFactory.createIntegerVector(originalVector
				.getVector());
	}

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original vector/set
	 */
	public IntegerSubSetGenerator(IntegerVector originalVector,
			boolean treatAsIdentical) {
		_hasDuplicates = originalVector.hasDuplicates();
		_treatAsIdentical = treatAsIdentical;
		_originalVector = IntegerFactory.createIntegerVector(originalVector
				.getVector());
	}

	/**
	 * Returns the core set
	 */
	public IntegerVector getOriginalVector() {
		return _originalVector;
	}

	/**
	 * Returns the number of the subsets. If the original set contains
	 * duplicates this method will throw a runtime exception.
	 */
	public long getNumberOfGeneratedObjects() {

		if (isSubList())
			throw new RuntimeException("The initial vector has duplicates: "
					+ _originalVector);

		return Util.pow2(_originalVector.getSize());
	}

	/**
	 * Creates the iterator over the all subsets
	 */
	public Iterator<IntegerVector> iterator() {
		if (isSubList())
			return new IntegerSubListIterator(this);
		else
			return new IntegerSubSetIterator(this);
	}

	protected boolean isSubList() {
		return (_treatAsIdentical && _hasDuplicates);
	}

}
