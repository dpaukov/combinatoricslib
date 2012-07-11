package org.paukov.combinatorics.composition;

import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;

/**
 * Iterator for enumeration of all compositions
 * 
 * @author Dmytro Paukov
 * @see ICombinatoricsVector
 * @see CompositionGenerator
 */
public class CompositionIterator extends
		Iterator<ICombinatoricsVector<Integer>> {

	/**
	 * Generator
	 */
	protected final CompositionGenerator _generator;

	/**
	 * Current composition
	 */
	protected ICombinatoricsVector<Integer> _currentComposition = null;

	/**
	 * Current index of the weak composition
	 */
	protected long _currentIndex = 0;

	/**
	 * Subset generator
	 */
	protected final Generator<Integer> _subsetGenerator;

	/**
	 * Subset iterator
	 */
	protected final Iterator<ICombinatoricsVector<Integer>> _subsetIterator;

	/**
	 * Current subset
	 */
	protected ICombinatoricsVector<Integer> _currentSubset = null;

	/**
	 * Constructor of an iterator
	 * 
	 * @param _generator
	 */
	public CompositionIterator(CompositionGenerator generator) {
		super();
		_generator = generator;

		ICombinatoricsVector<Integer> coreSet = Factory.createVector();

		for (int i = 1; i < this._generator._initialValue; i++)
			coreSet.addValue(i);

		_subsetGenerator = Factory.createSubSetGenerator(coreSet);

		_subsetIterator = _subsetGenerator.createIterator();
	}

	/**
	 * Returns the next composition
	 */
	@Override
	public ICombinatoricsVector<Integer> next() {
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
	public ICombinatoricsVector<Integer> getCurrentItem() {

		_currentComposition = Factory.<Integer> createVector();

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
				_generator._initialValue - currentValueSubSet);

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
