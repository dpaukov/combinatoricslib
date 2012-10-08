/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.combination.simple;

import java.util.Iterator;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Iterator for the simple combination generator
 * 
 * @author Dmytro Paukov
 * @version 2.0
 * @see ICombinatoricsVector
 * @see SimpleCombinationGenerator
 * @param <T>
 *            Type of the elements in the combinations
 */
public class SimpleCombinationIterator<T> implements
		Iterator<ICombinatoricsVector<T>> {

	/**
	 * Generator
	 */
	protected final SimpleCombinationGenerator<T> _generator;

	/**
	 * Current simple combination
	 */
	protected ICombinatoricsVector<T> _currentSimpleCombination = null;

	/**
	 * Index of the current combination
	 */
	protected long _currentIndex = 0;

	/**
	 * Size of the original vector/set
	 */
	protected final int _lengthN;

	/**
	 * Size of the generated combination.
	 */
	protected final int _lengthK;

	/**
	 * Helper array
	 */
	private int[] _bitVector = null;

	/**
	 * Criteria to stop iteration
	 */
	private int _endIndex = 0;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            Generator of the simple combinations
	 */
	public SimpleCombinationIterator(SimpleCombinationGenerator<T> generator) {
		_generator = generator;
		_lengthN = generator.getOriginalVector().getSize();
		_lengthK = generator.getCombinationLength();
		_currentSimpleCombination = Factory.createVector();
		_bitVector = new int[_lengthK + 1];
		init();
	}

	/**
	 * Initialization
	 */
	private void init() {

		for (int i = 0; i <= _lengthK; i++) {
			_bitVector[i] = i;
		}
		if (_lengthN > 0) {
			_endIndex = 1;
		}
		_currentIndex = 0;
	}

	/**
	 * Returns true if all combinations were iterated, otherwise false
	 */
	@Override
	public boolean hasNext() {
		return !((_endIndex == 0) || (_lengthK > _lengthN));
	}

	/**
	 * Moves to the next combination
	 */
	@Override
	public ICombinatoricsVector<T> next() {
		_currentIndex++;

		for (int i = 1; i <= _lengthK; i++) {
			int index = _bitVector[i] - 1;
			if (_generator.getOriginalVector().getSize() > 0) {
				_currentSimpleCombination.setValue(i - 1, _generator
						.getOriginalVector().getValue(index));
			}
		}

		_endIndex = _lengthK;

		while (_bitVector[_endIndex] == _lengthN - _lengthK + _endIndex) {
			_endIndex--;
			if (_endIndex == 0)
				break;
		}
		_bitVector[_endIndex]++;
		for (int i = _endIndex + 1; i <= _lengthK; i++) {
			_bitVector[i] = _bitVector[i - 1] + 1;
		}

		// return the current combination
		return Factory.createVector(_currentSimpleCombination);
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the current combination as a string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleCombinationIterator=[#" + _currentIndex + ", "
				+ _currentSimpleCombination + "]";
	}

}
