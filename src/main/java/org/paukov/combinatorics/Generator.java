/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract base class for all generic generators of the library
 *
 * This is a library written on Java to resolve some combinatorics issues such
 * as generating combinatorial objects (permutations, partitions, compositions,
 * subsets, combinations and etc).
 *
 * Type of the items should be specified as a parameter of generators and
 * vectors.
 *
 * There is a general pattern how to use the generators
 * <pre>
 * // create the initial vector or set
 * ICombinatoricsVector&lt;T&gt; vector = CombinatoricsFactory.createVector(new &lt;T&gt;[]{ elements } );
 *
 * // create a concrete generator
 * Generator&lt;T&gt; generator = CombinatoricsFactory.create&lt;Concrete&gt;Generator(vector);
 *
 * // iterate the generated objects
 * for (ICombinatoricsVector&lt;T&gt; v : generator) {
 * 	System.out.println( v );
 * }
 * </pre>
 *
 * @param <T> Type of the elements in the generated vectors
 * @author Dmytro Paukov
 * @version 2.0
 * @see ICombinatoricsVector
 * @see Iterator
 * @see CombinatoricsFactory
 */
public abstract class Generator<T> implements
    IGenerator<ICombinatoricsVector<T>> {

  /**
   * Creates an iterator for enumerating all generated objects/vectors
   *
   * @return The iterator over the generated objects/vectors
   * @deprecated This method will removed in the near future. Use the method <code>iterator()</code>
   * instead of this method
   */
  @Deprecated
  public Iterator<ICombinatoricsVector<T>> createIterator() {
    return iterator();
  }

  /**
   * Returns all generated vectors as a list
   *
   * @return List of all generated objects/vectors
   */
  public List<ICombinatoricsVector<T>> generateAllObjects() {
    return generateFilteredObjects(null);
  }

  /**
   * Returns the generated vectors filtered by a filter
   *
   * @param filter The filter to be applied to the generated result
   * @return The list of the filtered vectors
   */
  public List<ICombinatoricsVector<T>> generateFilteredObjects(
      IFilter<ICombinatoricsVector<T>> filter) {
    List<ICombinatoricsVector<T>> list = new ArrayList<>();
    long index = 0;
    for (ICombinatoricsVector<T> vector : this) {
      if (filter == null || filter.accepted(index, vector)) {
        list.add(vector);
      }
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
  public List<ICombinatoricsVector<T>> generateObjectsRange(long startIndex,
      long stopIndex) {
    assert (startIndex <= stopIndex);
    List<ICombinatoricsVector<T>> list = new ArrayList<>();
    Iterator<ICombinatoricsVector<T>> iterator = this.iterator();
    long index = 1;
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