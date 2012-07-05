package org.paukov.combinatorics;

/**
 * Iterator for enumerating a collection
 * 
 * @author Dmytro Paukov
 * @see CombinatoricsVector
 * @see Generator
 * 
 * @param <T>
 *            Type of elements in collections
 */
public abstract class Iterator<T> implements java.util.Iterator<T> {

	/**
	 * Initial action
	 */
	@Deprecated
	public void first() {
		// empty method
	}

	/**
	 * Checks if collection is finished
	 * 
	 * @return true if collection is finished, otherwise false
	 */
	public abstract boolean isDone();

	/**
	 * Returns the current element in the collection
	 * 
	 * @return The current element in the collection
	 */
	public abstract T getCurrentItem();

	@Override
	public boolean hasNext() {
		return !isDone();
	}

	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
