package org.paukov.combinatorics.combination.multi;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Iterator;

/**
 * Multi-combinations iterator
 * 
 * @param <T>
 *            Type of elements in a combination
 */
public class MultiCombinationIterator<T> extends
		Iterator<CombinatoricsVector<T>> {

	/**
	 * Generator
	 */
	protected final MultiCombinationGenerator<T> _generator;

	/**
	 * Current combination
	 */
	protected CombinatoricsVector<T> _currentCombination = null;

	/**
	 * Current index of combination
	 */
	protected long _currentIndex = 0;

	/**
	 * Size of core set
	 */
	protected final int _lengthN;

	/**
	 * Size of combination (number of elements) to generate
	 */
	protected final int _lengthK;

	/**
	 * Helper array
	 */
	private int[] _bitVector = null;

	/**
	 * Criteria to stop iteration
	 */
	private boolean _end = false;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            Multi-combinations generator
	 */
	public MultiCombinationIterator(MultiCombinationGenerator<T> generator) {
		_generator = generator;
		_lengthN = generator.getCoreObject().getSize();
		_currentCombination = new CombinatoricsVector<T>();
		_bitVector = new int[generator.getCombinationLength()];
		_lengthK = generator.getCombinationLength()-1;
		init();
	}

	/**
	 * Initial action for iteration
	 */
	private void init() {

		for (int i = 0; i < _generator.getCombinationLength(); i++) {
			_bitVector[i] = 0;
		}
		_end = false;
		_currentIndex = 0;
	}

	/**
	 * Returns current combination
	 */
	@Override
	public CombinatoricsVector<T> getCurrentItem() {
		return _currentCombination;
	}

	/**
	 * Returns true if all combinations were iterated, otherwise false
	 */
	@Override
	public boolean isDone() {
		return (_end == true);
	}

	/**
	 * Moves to the next combination
	 */
	@Override
	public CombinatoricsVector<T> next() {
		_currentIndex++;
		
		for (int i = 0; i < _generator.getCombinationLength(); i++) {
			int index = _bitVector[i];
			if (_generator.getCoreObject().getSize() > 0) {
				_currentCombination.setValue(i, _generator.getCoreObject()
						.getValue(index));
			}
		}

		_bitVector[_lengthK]++;
		if (_bitVector[_lengthK] > _lengthN - 1) {
			int index = -1;
			for (int i = 1; i <= _bitVector.length; i++) {
				if (_lengthK - i >= 0) {
					if (_bitVector[_lengthK - i] < _lengthN - 1) {
						index = _lengthK - i;
						break;
					}
				}
			}

			if (index != -1) {
				_bitVector[index]++;

				for (int j = 1; j < _bitVector.length - index; j++) {
					_bitVector[index + j] = _bitVector[index];
				}

			} else {
				_end = true;
			}

		}

		return getCurrentItem();
	}

	/**
	 * Returns current combination as a string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MultiCombinationIterator=[#" + _currentIndex + ", "
				+ _currentCombination + "]";
	}

}
