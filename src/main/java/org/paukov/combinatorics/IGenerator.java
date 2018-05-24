/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics;

import java.util.Iterator;
import java.util.List;

/**
 * This interface is implemented by all generic generators in the library.
 *
 * @param <T> The type of the elements.
 * @author Dmytro Paukov
 * @version 2.0
 */
public interface IGenerator<T> extends Iterable<T> {

  /**
   * Returns core/original vector. This is a main vector which is used for
   * generating other objects.
   *
   * @return The original vector which was passed into a constructor as an initializing parameter.
   */
  T getOriginalVector();

  /**
   * Returns the number of the the generated objects/vectors.
   *
   * @return Number of the generated vectors.
   */
  long getNumberOfGeneratedObjects();

  /**
   * Creates an iterator for enumerating all generated objects/vectors.
   *
   * @return The iterator over the generated objects/vectors.
   */
  Iterator<T> iterator();

  /**
   * Returns all generated vectors as a list.
   *
   * @return List of all generated objects/vectors.
   */
  List<T> generateAllObjects();

  /**
   * Returns the generated vectors filtered by a filter.
   *
   * @param filter The filter to be applied to the generated result.
   * @return The list of the filtered vectors.
   * @see IFilter
   */
  List<T> generateFilteredObjects(IFilter<T> filter);

  /**
   * Returns vectors as a list for specified range of indexes (from the
   * <code>startIndex</code> to <code>stopIndex</code>).
   *
   * @return List of the generated objects/vectors.
   */
  List<T> generateObjectsRange(long startIndex, long stopIndex);
}
