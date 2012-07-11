package org.paukov.combinatorics.combination.simple;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;

/**
 * Iterator of simple combinations
 * 
 * @author Dmytro Paukov
 * @see ICombinatoricsVector
 * @see SimpleCombinationGenerator
 * @param <T>
 *            Type of elements in a combination
 */
public class SimpleCombinationIterator<T> extends
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
		_currentSimpleCombination = new CombinatoricsVector<T>();
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
	 * Returns the current combination
	 */
	@Override
	public ICombinatoricsVector<T> getCurrentItem() {
		return new CombinatoricsVector<T>(_currentSimpleCombination);
	}

	/**
	 * Returns true if all combinations were iterated, otherwise false
	 */
	@Override
	public boolean isDone() {
		return (_endIndex == 0) || (_lengthK > _lengthN);
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

		return getCurrentItem();
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
