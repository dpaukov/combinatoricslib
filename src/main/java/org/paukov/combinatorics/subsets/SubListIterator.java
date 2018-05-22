/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.subsets;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Iterator over the all sublists (with duplicates)
 *
 * @param <T> Type of the elements in the lists
 * @author Dmytro Paukov
 * @version 2.0
 * @see ICombinatoricsVector
 * @see SubSetGenerator
 */
class SubListIterator<T> implements Iterator<ICombinatoricsVector<T>> {

  /**
   * Subset generator
   */
  final Generator<T> _generator;

  /**
   * Sub set iterator
   */
  final SubSetIterator<T> _subSetsIterator;

  /**
   * Set of the result vectors (with duplicates)
   */
  final Set<ICombinatoricsVector<T>> _result = new LinkedHashSet<>();

  /**
   * Current sublist
   */
  ICombinatoricsVector<T> _currentSubList = null;

  /**
   * Index of the current sub-list
   */
  long _currentIndex = 0;

  /**
   * Iterator over the result vectors
   */
  Iterator<ICombinatoricsVector<T>> _resultIterator = null;

  /**
   * Constructor
   *
   * @param generator The subset generator
   */
  SubListIterator(Generator<T> generator) {
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
      _result.add(subVector);
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
    return "SubListIterator=[#" + _currentIndex + ", " + _currentSubList + "]";
  }

}
