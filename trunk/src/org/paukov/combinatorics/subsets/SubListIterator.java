package org.paukov.combinatorics.subsets;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Iterator over the all sublists (with duplicates)
 * 
 * @author Dmytro Paukov
 * @see ICombinatoricsVector
 * @see SubSetGenerator
 * 
 * @param <T>
 *            Type of the elements in the lists
 */
public class SubListIterator<T> implements
		Iterator<ICombinatoricsVector<T>> {

	/**
	 * Subset generator
	 */
	protected final Generator<T> _generator;

	/**
	 * Current sublist
	 */
	protected ICombinatoricsVector<T> _currentSubList = null;

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
	protected final Set<ICombinatoricsVector<T>> _result = new LinkedHashSet<ICombinatoricsVector<T>>();

	/**
	 * Iterator over the result vectors
	 */
	protected Iterator<ICombinatoricsVector<T>> _resultIterator = null;

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
			ICombinatoricsVector<T> subVector = _subSetsIterator.next();
			_result.add( subVector );
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
	public ICombinatoricsVector<T> next() {
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
		return "SubListIterator=[#" + _currentIndex + ", " + _currentSubList
				+ "]";
	}

}
