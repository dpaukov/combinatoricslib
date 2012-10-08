/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.subsets;

import java.util.Iterator;

import org.paukov.combinatorics.IntegerFactory;
import org.paukov.combinatorics.IntegerGenerator;
import org.paukov.combinatorics.IntegerVector;

/**
 * Iterator over the all subsets
 * 
 * @author Dmytro Paukov
 * @version 2.0
 * @see IntegerVector
 * @see IntegerSubSetGenerator
 * 
 */
public class IntegerSubSetIterator implements Iterator<IntegerVector> {

	/**
	 * Subset generator
	 */
	protected final IntegerGenerator _generator;

	/**
	 * Current subset
	 */
	protected IntegerVector _currentSubSet = null;

	/**
	 * Index of the current subset
	 */
	protected long _currentIndex = 0;

	/**
	 * Size of the subset
	 */
	protected final int _length;

	/**
	 * internal bit vector, representing the subset
	 */
	private int[] _bitVector = null;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            The subset generator
	 */
	public IntegerSubSetIterator(IntegerGenerator generator) {
		_generator = generator;
		_length = generator.getOriginalVector().getSize();
		_currentSubSet = IntegerFactory.createIntegerVector(0);
		_bitVector = new int[_length + 2];
		init();
	}

	/**
	 * initialize the iterator
	 * 
	 * @see org.paukov.combinatorics.iterator.Iterator#first()
	 */
	private void init() {
		_currentIndex = 0;
		for (int i = 0; i <= _length + 1; i++) {
			_bitVector[i] = 0;
		}

	}

	/**
	 * Returns true if iteration is done, otherwise false
	 * 
	 * @see Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return _bitVector[_length + 1] != 1;
	}

	/**
	 * Returns the next subset if it is available
	 * 
	 * @see Iterator#next()
	 */
	@Override
	public IntegerVector next() {
		_currentIndex++;

		int[] temp = new int[_length];
		int j = 0;
		for (int index = 1; index <= _length; index++) {
			if (_bitVector[index] == 1) {
				int value = _generator.getOriginalVector().getValue(index - 1);
				temp[j++] = value;
			}
		}
		_currentSubSet = IntegerFactory.createIntegerVector(temp, j);

		int i = 1;
		while (_bitVector[i] == 1) {
			_bitVector[i++] = 0;
		}
		_bitVector[i] = 1;
		return _currentSubSet;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Convert
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IntegerSubSetIterator=[#" + _currentIndex + ", "
				+ _currentSubSet + "]";
	}

}
