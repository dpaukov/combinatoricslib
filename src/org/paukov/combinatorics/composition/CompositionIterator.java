package org.paukov.combinatorics.composition;

import java.util.List;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.subsets.SubSetGenerator;


/**
 * Iterator for enumeration of all compositions
 */
public class CompositionIterator extends Iterator<CombinatoricsVector<Integer>> {

	/**
	 * Generator
	 */
	protected final CompositionGenerator _generator;

	/**
	 * Current composition
	 */
	protected CombinatoricsVector<Integer> _currentComposition = null;

	/**
	 * Current index of the weak composition
	 */
	protected long _currentIndex = 0;

	/**
	 * Subset generator
	 */
	protected final SubSetGenerator<Integer> _subsetGenerator;

	/**
	 * Subset iterator
	 */
	protected final Iterator<CombinatoricsVector<Integer>> _subsetIterator;

	/**
	 * Current subset
	 */
	protected CombinatoricsVector<Integer> _currentSubset = null;

	/**
	 * Constructor of an iterator
	 * 
	 * @param _generator
	 */
	public CompositionIterator(CompositionGenerator _generator) {
		super();
		this._generator = _generator;

		CombinatoricsVector<Integer> coreSet = new CombinatoricsVector<Integer>();

		for (int i = 1; i < this._generator._coreValue; i++)
			coreSet.addValue(i);

		this._subsetGenerator = new SubSetGenerator<Integer>(coreSet);

		this._subsetIterator = this._subsetGenerator.createIterator();
	}

	/**
	 * Returns the next composition
	 */
	@Override
	public CombinatoricsVector<Integer> next() {
		_currentIndex++;
		_currentSubset = this._subsetIterator.next();
		return getCurrentItem();
	}

	/**
	 * Returns true when all composition are iterated
	 */
	@Override
	public boolean isDone() {
		return !this._subsetIterator.hasNext();
	}

	/**
	 * Returns current composition
	 */
	@Override
	public CombinatoricsVector<Integer> getCurrentItem() {

		_currentComposition = new CombinatoricsVector<Integer>();

		List<Integer> vector = _currentSubset.getVector();
		java.util.Iterator<Integer> itr = vector.iterator();

		int currentValueSubSet = 0;
		int indexCompositionElement = 0;
		int valueCompositionElement = 0;

		while (itr.hasNext()) {
			Integer currentSubsetElement = itr.next();
			valueCompositionElement = currentSubsetElement - currentValueSubSet;
			_currentComposition.setValue(indexCompositionElement,
					valueCompositionElement);
			indexCompositionElement++;
			currentValueSubSet = currentSubsetElement;
		}
		_currentComposition.setValue(indexCompositionElement,
				_generator._coreValue - currentValueSubSet);

		return _currentComposition;
	}

	/**
	 * Returns composition as a string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompositionIterator=[#" + _currentIndex + ", "
				+ _currentComposition + "]";
	}

}
