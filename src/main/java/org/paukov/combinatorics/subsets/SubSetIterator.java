/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.subsets;

import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.Iterator;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Iterator over the all subsets
 *
 * @param <T> Type of the elements of the subsets
 * @author Dmytro Paukov
 * @version 2.0
 * @see ICombinatoricsVector
 * @see SubSetGenerator
 */
class SubSetIterator<T> implements Iterator<ICombinatoricsVector<T>> {

  /**
   * Subset generator
   */
  final Generator<T> _generator;
  /**
   * Size of the subset
   */
  final int _length;
  /**
   * Current subset
   */
  ICombinatoricsVector<T> _currentSubSet = null;
  /**
   * Index of the current subset
   */
  long _currentIndex = 0;
  /**
   * internal bit vector, representing the subset
   */
  private int[] _bitVector = null;

  /**
   * Constructor
   *
   * @param generator The subset generator
   */
  SubSetIterator(Generator<T> generator) {
    _generator = generator;
    _length = generator.getOriginalVector().getSize();
    _currentSubSet = createVector();
    _bitVector = new int[_length + 2];
    init();
  }

  /**
   * initialize the iterator
   *
   * @see org.paukov.combinatorics.iterator.Iterator#first()
   */
  private void init() {
    _currentIndex = 0;
    for (int i = 0; i <= _length + 1; i++) {
      _bitVector[i] = 0;
    }

  }

  /**
   * Returns true if iteration is done, otherwise false
   *
   * @see Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return _bitVector[_length + 1] != 1;
  }

  /**
   * Returns the next subset if it is available
   *
   * @see Iterator#next()
   */
  @Override
  public ICombinatoricsVector<T> next() {
    _currentIndex++;
    _currentSubSet.clearVector();
    for (int index = 1; index <= _length; index++) {
      if (_bitVector[index] == 1) {
        T value = _generator.getOriginalVector().getValue(index - 1);
        _currentSubSet.addValue(value);
      }
    }
    int i = 1;
    while (_bitVector[i] == 1) {
      _bitVector[i] = 0;
      i++;
    }
    _bitVector[i] = 1;

    return createVector(_currentSubSet);
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  /**
   * Convert
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SubSetIterator=[#" + _currentIndex + ", " + _currentSubSet + "]";
  }

}
