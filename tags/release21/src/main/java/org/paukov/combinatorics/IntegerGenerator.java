/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a special abstract class for all integer generators
 * <p>
 * There is a general pattern how to use the integer generators <blockquote>
 * 
 * <pre>
 * // create an integer vector
 * IntegerVector vector = new IntegerVector(new int[] { elements });
 * 
 * // create a concrete integer generator
 * IntegerGenerator generator = IntegerFactory.create &lt; Concrete &gt; Generator(vector);
 * 
 * // Iterate the generated objects
 * for (IntegerVector v : generator) {
 * 	System.out.println(v);
 * }
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro Paukov
 * @see IntegerVector
 * @see IntegerFactory
 * @see IGenerator
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
	 * @see IFilter
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