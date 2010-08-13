package org.paukov.combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all generators
 * 
 * @param <T>
 *            Type of elements in the generated vectors
 */
public abstract class Generator<T> {

	/**
	 * Returns core object. This is a main vector which is used for generating
	 * other objects
	 * 
	 * @return core vector
	 */
	public abstract CombinatoricsVector<T> getCoreObject();

	/**
	 * Returns concrete value of number of generated vectors
	 * 
	 * @return Value of generated vectors
	 */
	public abstract long getNumberOfGeneratedObjects();

	/**
	 * Creates iterator for enumeration all generated vectors
	 * 
	 * @return Iterator of vectors
	 */
	public abstract Iterator<CombinatoricsVector<T>> createIterator();

	/**
	 * Returns all generated vectors as a list
	 * 
	 * @return List of all generated objects
	 */
	public List<CombinatoricsVector<T>> generateAllObjects() {
		List<CombinatoricsVector<T>> list = new ArrayList<CombinatoricsVector<T>>();
		Iterator<CombinatoricsVector<T>> iterator = createIterator();
		while (iterator.hasNext()) {
			CombinatoricsVector<T> vector = new CombinatoricsVector<T>(
					iterator.next());
			list.add(vector);
		}
		return list;
	}

	/**
	 * Returns vectors as a list for specified range of indexes (from the
	 * <code>startIndex</code> to <code>stopIndex</code>)
	 * 
	 * @return List of generated vectors
	 */
	public List<CombinatoricsVector<T>> generateObjectsRange(int startIndex,
			int stopIndex) {
		assert (startIndex <= stopIndex);
		List<CombinatoricsVector<T>> list = new ArrayList<CombinatoricsVector<T>>();
		Iterator<CombinatoricsVector<T>> iterator = createIterator();
		int index = 1;
		while (iterator.hasNext()) {
			if (index >= startIndex && index <= stopIndex) {
				CombinatoricsVector<T> vector = new CombinatoricsVector<T>(
						iterator.next());
				list.add(vector);
			} else if (index > stopIndex) {
				return list;
			} else {
				iterator.next();
			}
			index++;
		}
		return list;
	}

}