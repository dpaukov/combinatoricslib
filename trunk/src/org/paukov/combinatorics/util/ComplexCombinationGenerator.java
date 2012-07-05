package org.paukov.combinatorics.util;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;


public class ComplexCombinationGenerator<T> extends Generator<CombinatoricsVector<T>> {

	protected final CombinatoricsVector<T> _originalVector;
	protected final int _combinationLength;
	protected final boolean _isOrderImportant;
	protected final boolean _excludeEmptySet;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Core set which is used for combination generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 */
	public ComplexCombinationGenerator(CombinatoricsVector<T> originalVector,
			int combinationsLength) {
		_originalVector = new CombinatoricsVector<T>(originalVector);
		_combinationLength = combinationsLength;
		_isOrderImportant = true;
		_excludeEmptySet = false;
	}

	
	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Core set which is used for combination generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 */
	public ComplexCombinationGenerator(CombinatoricsVector<T> originalVector,
			int combinationsLength, boolean isOrderImportant, boolean excludeEmptySet) {
		_originalVector = new CombinatoricsVector<T>(originalVector);
		_combinationLength = combinationsLength;
		_isOrderImportant = isOrderImportant;
		_excludeEmptySet = excludeEmptySet;
	}
	
	/**
	 * Returns the original vector wrapped into one more vector.
	 * 
	 * @return Returns the _originalVector.
	 */
	public CombinatoricsVector<CombinatoricsVector<T>> getCoreObject() {
		CombinatoricsVector<CombinatoricsVector<T>> result = new CombinatoricsVector<CombinatoricsVector<T>>();
		result.addValue(_originalVector);
		return result;
	}

	/**
	 * Returns length of the combinations
	 * 
	 * @return Returns the _combinationLength.
	 */
	public int getCombinationLength() {
		return _combinationLength;
	}

	/**
	 * Returns number of generated combinations
	 */
	public long getNumberOfGeneratedObjects() {
		// TODO: needs to be implemented
		throw new UnsupportedOperationException();
	}

	/**
	 * Creates an iterator of the complex combinations
	 */
	public Iterator<CombinatoricsVector<CombinatoricsVector<T>>> createIterator() {
		return new ComplexCombinationIterator<T>(this);
	}
	
	public boolean isOrderImportant() {
		return _isOrderImportant;
	}
	
	public boolean excludeEmptySet() {
		return _excludeEmptySet;
	}
}
