package org.paukov.combinatorics.composition;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.util.Util;

/**
 * A composition of an integer n is a way of writing n as the sum of a sequence
 * of (strictly) positive integers. This class generates the composition if a
 * positive integer value.
 * 
 * @author Dmytro.Paukov
 * 
 */
public class CompositionGenerator extends Generator<Integer> {

	public static final int MAXN = 100;

	protected final Integer _coreValue;

	/**
	 * Constructor
	 * 
	 * @param n
	 *            An positive integer value
	 */
	public CompositionGenerator(Integer n) {
		super();
		this._coreValue = n;
	}

	/**
	 * Returns value which is used to generate all compositions. This value
	 * returned as a element of vector. Vector has length of 1
	 */
	@Override
	public CombinatoricsVector<Integer> getCoreObject() {
		return new CombinatoricsVector<Integer>(1, _coreValue);
	}

	/**
	 * Returns number of the compositions
	 */
	@Override
	public long getNumberOfGeneratedObjects() {
		return Util.pow2(_coreValue - 1);
	}

	/**
	 * Creates the iterator over all compositions
	 */
	@Override
	public Iterator<CombinatoricsVector<Integer>> createIterator() {
		return new CompositionIterator(this);
	}

}
