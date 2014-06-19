/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.combination.simple;

import java.util.Iterator;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates simple combinations from specified core set by
 * specified length. Core set and length are specified in the constructor of
 * generator
 * <p>
 * A simple k-combination of a finite set S is a subset of k distinct elements
 * of S. Specifying a subset does not arrange them in a particular order. As an
 * example, a poker hand can be described as a 5-combination of cards from a
 * 52-card deck: the 5 cards of the hand are all distinct, and the order of the
 * cards in the hand does not matter.
 * <p>
 * Example. Generate 3-combination of the set (red, black, white, green, blue).
 * <p>
 * <blockquote>
 * 
 * <pre>
 * 
 * // Create the initial vector
 * ICombinatoricsVector&lt;String&gt; initialVector = Factory.createVector(new String[] {
 * 		&quot;red&quot;, &quot;black&quot;, &quot;white&quot;, &quot;green&quot;, &quot;blue&quot; });
 * 
 * // Create a simple combination generator to generate 3-combinations of the
 * // initial vector
 * Generator&lt;String&gt; gen = Factory.createSimpleCombinationGenerator(initialVector,
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
 * @see SimpleCombinationIterator
 * @param <T>
 *            Type of elements in the combination
 */
public class SimpleCombinationGenerator<T> extends Generator<T> {

	protected final ICombinatoricsVector<T> _originalVector;
	protected final int _combinationLength;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original vector which is used for generating the combination
	 * @param combinationsLength
	 *            Length of the combinations
	 */
	public SimpleCombinationGenerator(ICombinatoricsVector<T> originalVector,
			int combinationsLength) {
		_originalVector = Factory.createVector(originalVector);
		_combinationLength = combinationsLength;
	}

	/**
	 * Returns the original vector/set
	 * 
	 * @return Returns the _originalVector.
	 */
	public ICombinatoricsVector<T> getOriginalVector() {
		return _originalVector;
	}

	/**
	 * Returns the length of the combinations
	 * 
	 * @return Returns the _combinationLength.
	 */
	public int getCombinationLength() {
		return _combinationLength;
	}

	/**
	 * Returns the number of the generated combinations
	 */
	public long getNumberOfGeneratedObjects() {
		return Util.combination(_originalVector.getSize(), _combinationLength);
	}

	/**
	 * Creates an iterator of the simple combinations (without repetitions)
	 */
	@Override
	public Iterator<ICombinatoricsVector<T>> iterator() {
		return new SimpleCombinationIterator<T>(this);
	}
}
