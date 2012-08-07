package org.paukov.combinatorics;

import java.util.ArrayList;
import java.util.Iterator;
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
 * There is a general pattern how to use the generators <blockquote>
 * 
 * <pre>
 * // create combinatorics vector
 * CombinatoricsVector&lt;T&gt; vector = new CombinatoricsVector&lt;T&gt;(array);
 * 
 * // create a concrete generator
 * Generator&lt;T&gt; generator = new&lt;Concrete&gt; Generator&lt;T&gt;(vector);
 * 
 * // create an iterator
 * Iterator&lt;CombinatoricsVector&lt;T&gt;&gt; iterator = generator.createIterator();
 * 
 * // iterate the generated objects
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
 * @see IntegerVector
 * @see Iterator
 * @version 2.0
 */
public abstract class IntegerGenerator implements IGenerator<IntegerVector> {

	/**
	 * Returns all generated vectors as a list
	 * 
	 * @return List of all generated objects/vectors
	 */
	public List<IntegerVector> generateAllObjects() {
		return generateFilteredObjects(null);
	}

	/**
	 * Returns the generated vectors filtered by a filter
	 * 
	 * @param filter
	 *            The filter to be applied to the generated result
	 * @return The list of the filtered vectors
	 */
	public List<IntegerVector> generateFilteredObjects(
			IFilter<IntegerVector> filter) {
		List<IntegerVector> list = new ArrayList<IntegerVector>();
		long index = 0;
		for (IntegerVector vector : this) {
			if (filter == null || filter.accepted(index, vector))
				list.add(vector);
			index++;
		}
		return list;
	}

	/**
	 * Returns vectors as a list for specified range of indexes (from the
	 * <code>startIndex</code> to <code>stopIndex</code>)
	 * 
	 * @return List of the generated objects/vectors
	 */
	public List<IntegerVector> generateObjectsRange(int startIndex,
			int stopIndex) {
		assert (startIndex <= stopIndex);
		List<IntegerVector> list = new ArrayList<IntegerVector>();
		Iterator<IntegerVector> iterator = this.iterator();
		int index = 1;
		while (iterator.hasNext()) {
			if (index >= startIndex && index <= stopIndex) {
				list.add(iterator.next());
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