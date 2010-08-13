package org.paukov.combinatorics.permutations;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates all permutations of specified core vector
 * 
 * @param <T>
 *            Type of elements in permutation
 */
public class PermutationGenerator<T> extends Generator<T> {

	/**
	 * Core vector
	 */
	protected final CombinatoricsVector<T> _corePermutation;

	/**
	 * Constructor
	 * 
	 * @param corePermutation
	 *            Vector which is used for permutation generation
	 */
	public PermutationGenerator(CombinatoricsVector<T> corePermutation) {
		_corePermutation = new CombinatoricsVector<T>(corePermutation);
	}

	/**
	 * Returns core permutation
	 * 
	 * @see org.paukov.combinatorics.Generator#getCoreObject()
	 */
	public CombinatoricsVector<T> getCoreObject() {
		return _corePermutation;
	}

	/**
	 * Returns number of all generated permutations
	 * 
	 * @see org.paukov.combinatorics.Generator#getNumberOfGeneratedObjects()
	 */
	public long getNumberOfGeneratedObjects() {
		if (_corePermutation.getSize() == 0)
			return 0;
		return Util.factorial(_corePermutation.getSize());
	}

	/**
	 * Creates iterator
	 * 
	 * @see org.paukov.combinatorics.Generator#createIterator()
	 */
	public Iterator<CombinatoricsVector<T>> createIterator() {
		return new PermutationIterator<T>(this);
	}

}
