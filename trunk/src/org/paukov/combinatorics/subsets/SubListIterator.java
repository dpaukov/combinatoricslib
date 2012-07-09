package org.paukov.combinatorics.subsets;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;

/**
 * Iterator over the all sublists (with duplicates)
 * 
 * @author Dmytro.Paukov
 * @see CombinatoricsVector
 * @see SubSetGenerator
 * 
 * @param <T>
 *            Type of the elements in the lists
 */
public class SubListIterator<T> extends
		org.paukov.combinatorics.Iterator<CombinatoricsVector<T>> {

	/**
	 * Subset generator
	 */
	protected final Generator<T> _generator;

	/**
	 * Current sublist
	 */
	protected CombinatoricsVector<T> _currentSubList = null;

	/**
	 * Index of the current sub-list
	 */
	protected long _currentIndex = 0;

	/**
	 * Sub set iterator
	 */
	protected final SubSetIterator<T> _subSetsIterator;

	/**
	 * Set of the result vectors (with duplicates)
	 */
	protected final Set<CombinatoricsVector<T>> _result = new LinkedHashSet<CombinatoricsVector<T>>();

	/**
	 * Iterator over the result vectors
	 */
	protected Iterator<CombinatoricsVector<T>> _resultIterator = null;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            The subset generator
	 */
	public SubListIterator(Generator<T> generator) {
		_generator = generator;
		_subSetsIterator = new SubSetIterator<T>(generator);
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
			CombinatoricsVector<T> subVector = _subSetsIterator.next();
			_result.add(new CombinatoricsVector<T>(subVector));
		}
		_resultIterator = _result.iterator();
	}

	/**
	 * Returns the current sublist
	 * 
	 * @see org.paukov.combinatorics.iterator.Iterator#getCurrentItem()
	 */
	@Override
	public CombinatoricsVector<T> getCurrentItem() {
		return _currentSubList;
	}

	/**
	 * Returns true if iteration is done, otherwise false
	 * 
	 * @see org.paukov.combinatorics.iterator.Iterator#isDone()
	 */
	@Override
	public boolean isDone() {
		return !_resultIterator.hasNext();
	}

	/**
	 * Returns the next sublist if it is available
	 * 
	 * @see org.paukov.combinatorics.iterator.Iterator#next()
	 */
	@Override
	public CombinatoricsVector<T> next() {
		_currentIndex++;
		_currentSubList = _resultIterator.next();
		return getCurrentItem();
	}

	/**
	 * Returns a string representation
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubListIterator=[#" + _currentIndex + ", " + _currentSubList
				+ "]";
	}

}
