/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics;

/**
 * This is the interface for filtering the generated combinatorial vectors.
 *
 * @param <T> Type of the elements
 * @author Dmytro Paukov
 * @version 2.0
 * @see IGenerator#generateFilteredObjects
 */
public interface IFilter<T> {

  /**
   * This method defines a condition for a given value to be accepted by the
   * filter or not.
   *
   * @param index The index of the value.
   * @param value The value to be tested.
   * @return <code>true</code> if the value is accepted, otherwise <code>false</code>.
   */
  boolean accepted(long index, T value);

}
