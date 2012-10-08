/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.combination.multi;

import java.util.Iterator;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates multi-combinations (with repetitions) from specified
 * core set by specified length. Core set and length are specified in the
 * constructor of generator
 * <p>
 * A k-multicombination or k-combination with repetition of a finite set S is
 * given by a sequence of k not necessarily distinct elements of S, where order
 * is not taken into account.
 * <p>
 * As an example. Suppose there are 2 types of fruits (apple and orange) at a
 * grocery store, and you want to buy 3 pieces of fruit. You could select
 * <ul>
 * <li>(apple, apple, apple)
 * <li>(apple, apple, orange)
 * <li>(apple, orange, orange)
 * <li>(orange, orange, orange)
 * </ul>
 * <p>
 * Generate 3-combinations with repetitions of the set (apple, orange).
 * <p>
 * <p>
 * <blockquote>
 * 
 * <pre>
 * 
 * // Create the initial vector of (apple, orange)
 * ICombinatoricsVector&lt;String&gt; initialVector = Factory.createVector(new String[] {
 * 		&quot;apple&quot;, &quot;orange&quot; });
 * 
 * // Create a multi-combination generator to generate 3-combinations of
 * // the initial vector
 * Generator&lt;String&gt; gen = Factory.createMultiCombinationGenerator(initialVector,
 * 		3);
 * 
 * // Print all possible combinations
 * for (ICombinatoricsVector&lt;String&gt; combination : gen) {
 * 	System.out.println(combination);
 * }
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro Paukov
 * @version 2.0
 * @see MultiCombinationIterator
 * @param <T>
 *            Type of elements in the combination
 */
public class MultiCombinationGenerator<T> extends Generator<T> {

	protected final ICombinatoricsVector<T> _originalVector;
	protected final int _combinationLength;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original/initial vector which is used for combination
	 *            generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 */
	public MultiCombinationGenerator(ICombinatoricsVector<T> originalVector,
			int combinationsLength) {
		_originalVector = Factory.createVector(originalVector);
		if (combinationsLength < 0)
			_combinationLength = 0;
		else
			_combinationLength = combinationsLength;
	}

	/**
	 * Returns core set
	 * 
	 * @return Returns the _originalVector.
	 */
	public ICombinatoricsVector<T> getOriginalVector() {
		return _originalVector;
	}

	/**
	 * Returns length of combinations
	 * 
	 * @return Returns the _combinationLength.
	 */
	public int getCombinationLength() {
		return _combinationLength;
	}

	/**
	 * Returns number of generated combinations with repetitions
	 */
	public long getNumberOfGeneratedObjects() {
		return Util.combination(_originalVector.getSize() + _combinationLength
				- 1, _combinationLength);
	}

	/**
	 * Creates iterator of combinations with repetitions
	 */
	@Override
	public Iterator<ICombinatoricsVector<T>> iterator() {
		return new MultiCombinationIterator<T>(this);
	}
}
