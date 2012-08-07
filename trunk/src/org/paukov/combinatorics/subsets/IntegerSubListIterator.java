package org.paukov.combinatorics.subsets;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.paukov.combinatorics.IntegerGenerator;
import org.paukov.combinatorics.IntegerVector;

/**
 * Iterator over the all sublists (with duplicates) of integer values
 * 
 * @author Dmytro Paukov
 * @see IntegerVector
 * @see IntegerSubSetGenerator
 * 
 */
public class IntegerSubListIterator implements Iterator<IntegerVector> {

	/**
	 * Subset generator
	 */
	protected final IntegerGenerator _generator;

	/**
	 * Current sublist
	 */
	protected IntegerVector _currentSubList = null;

	/**
	 * Index of the current sub-list
	 */
	protected long _currentIndex = 0;

	/**
	 * Sub set iterator
	 */
	protected final IntegerSubSetIterator _subSetsIterator;

	/**
	 * Set of the result vectors (with duplicates)
	 */
	protected final Set<IntegerVector> _result = new LinkedHashSet<IntegerVector>();

	/**
	 * Iterator over the result vectors
	 */
	protected Iterator<IntegerVector> _resultIterator = null;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            The subset generator
	 */
	public IntegerSubListIterator(IntegerGenerator generator) {
		_generator = generator;
		_subSetsIterator = new IntegerSubSetIterator(generator);
		init();
	}

	/**
	 * Initializes the iterator
	 * 
	 * @see org.paukov.combinatorics.iterator.Iterator#first()
	 */
	private void init() {
		_currentIndex = 0;
		while (_subSetsIterator.hasNext()) {
			IntegerVector subVector = _subSetsIterator.next();
			_result.add(subVector);
		}
		_resultIterator = _result.iterator();
	}

	/**
	 * Returns true if iteration is done, otherwise false
	 * 
	 * @see Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return _resultIterator.hasNext();
	}

	/**
	 * Returns the next sublist if it is available
	 * 
	 * @see Iterator#next()
	 */
	@Override
	public IntegerVector next() {
		_currentIndex++;
		_currentSubList = _resultIterator.next();
		return _currentSubList;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a string representation
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IntegerSubListIterator=[#" + _currentIndex + ", "
				+ _currentSubList + "]";
	}

}
