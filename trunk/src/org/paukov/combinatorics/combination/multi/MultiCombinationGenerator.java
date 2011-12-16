package org.paukov.combinatorics.combination.multi;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates multi-combinations (with repetitions) from specified core set by
 * specified length. Core set and length are specified in the constructor of
 * generator
 * 
 * @param <T>
 *            Type of elements in the combination
 */
public class MultiCombinationGenerator<T> extends Generator<T> {

	protected final CombinatoricsVector<T> _coreSet;
	protected final int _combinationLength;

	/**
	 * Constructor
	 * 
	 * @param coreSet
	 *            Core set which is used for combination generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 */
	public MultiCombinationGenerator(CombinatoricsVector<T> coreSet,
			int combinationsLength) {
		_coreSet = new CombinatoricsVector<T>(coreSet);
		_combinationLength = combinationsLength;
	}

	/**
	 * Returns core set
	 * 
	 * @return Returns the _coreSet.
	 */
	public CombinatoricsVector<T> getCoreObject() {
		return _coreSet;
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
		return Util.combination(_coreSet.getSize() + _combinationLength - 1, _combinationLength);
	}

	/**
	 * Creates iterator of combinations with repetitions
	 */
	public Iterator<CombinatoricsVector<T>> createIterator() {
		return new MultiCombinationIterator<T>(this);
	}
}
