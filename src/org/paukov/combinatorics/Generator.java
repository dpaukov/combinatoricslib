package org.paukov.combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all generators
 * <p>
 * This is a library written on Java to resolve some combinatorics issues such
 * as generating combinatorial objects (permutations, partitions, compositions,
 * subsets, combinations and etc).
 * <p>
 * Type of the items should be specified as type parameter of generator and
 * vector.
 * <p>
 * There is a general pattern how to use the generator <blockquote>
 * 
 * <pre>
 * // create combinatorics vector
 * CombinatoricsVector&lt;T&gt; vector = new CombinatoricsVector&lt;T&gt;(array);
 * 
 * // create a concrete generator
 * Generator&lt;T&gt; generator = new&lt;Concrete&gt; Generator&lt;T&gt;(vector);
 * 
 * // create iterator
 * Iterator&lt;CombinatoricsVector&lt;T&gt;&gt; iterator = generator.createIterator();
 * 
 * // go through the iterator
 * while (iterator.hasNext()) {
 * 	CombinatoricsVector&lt;T&gt; item = iterator.next();
 * 	System.out.println(item);
 * }
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro Paukov
 * @see CombinatoricsVector
 * @see Iterator
 * @param <T>
 *            Type of elements in the generated vectors
 */
public abstract class Generator<T> {

	/**
	 * Returns core/original object. This is a main vector which is used for
	 * generating other objects
	 * 
	 * @return The original vector which was passed into a constructor as an
	 *         initializing parameter
	 */
	public abstract CombinatoricsVector<T> getCoreObject();

	/**
	 * Returns a concrete value of the number of the generated vectors
	 * 
	 * @return Number of the generated vectors
	 */
	public abstract long getNumberOfGeneratedObjects();

	/**
	 * Creates an iterator for enumerating all generated vectors/objects
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