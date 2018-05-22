/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.combination.multi;

import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.Iterator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Multi-combinations iterator for enumerating combinations with repetitions
 *
 * @param <T> Type of the elements in the combinations
 * @author Dmytro Paukov
 * @version 2.0
 * @see ICombinatoricsVector
 * @see MultiCombinationGenerator
 */
class MultiCombinationIterator<T> implements
    Iterator<ICombinatoricsVector<T>> {

  /**
   * Generator
   */
  final MultiCombinationGenerator<T> _generator;

  /**
   * Size of the original vector/set
   */
  final int _lengthN;

  /**
   * Size of the combinations (number of elements) to generate
   */
  final int _lengthK;

  /**
   * Current combination
   */
  ICombinatoricsVector<T> _currentCombination = null;

  /**
   * Index of the current combination
   */
  long _currentIndex = 0;

  /**
   * A helper array
   */
  private int[] _bitVector = null;

  /**
   * Criteria to stop iteration
   */
  private boolean _end = false;

  /**
   * Constructor
   *
   * @param generator Multi-combinations generator
   */
  MultiCombinationIterator(MultiCombinationGenerator<T> generator) {
    _generator = generator;
    _lengthN = generator.getOriginalVector().getSize();
    _currentCombination = createVector();
    _bitVector = new int[generator.getCombinationLength()];
    _lengthK = generator.getCombinationLength() - 1;
    init();
  }

  /**
   * Initialization of the iterator
   */
  private void init() {

    for (int i = 0; i < _generator.getCombinationLength(); i++) {
      _bitVector[i] = 0;
    }
    _end = false;
    _currentIndex = 0;

  }

  /**
   * Returns true if all combinations were iterated, otherwise false
   */
  @Override
  public boolean hasNext() {
    return (_end != true);
  }

  /**
   * Moves to the next combination
   */
  @Override
  public ICombinatoricsVector<T> next() {
    _currentIndex++;

    for (int i = 0; i < _generator.getCombinationLength(); i++) {
      int index = _bitVector[i];
      if (_generator.getOriginalVector().getSize() > 0) {
        _currentCombination.setValue(i, _generator.getOriginalVector()
            .getValue(index));
      }
    }

    if (_bitVector.length > 0) {
      _bitVector[_lengthK]++;

      if (_bitVector[_lengthK] > _lengthN - 1) {
        int index = -1;
        for (int i = 1; i <= _bitVector.length; i++) {
          if (_lengthK - i >= 0) {
            if (_bitVector[_lengthK - i] < _lengthN - 1) {
              index = _lengthK - i;
              break;
            }
          }
        }

        if (index != -1) {
          _bitVector[index]++;

          for (int j = 1; j < _bitVector.length - index; j++) {
            _bitVector[index + j] = _bitVector[index];
          }

        } else {
          _end = true;
        }

      }
    } else {
      _end = true;
    }

    // return the current combination
    return createVector(_currentCombination);
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the current combination as a string
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "MultiCombinationIterator=[#" + _currentIndex + ", " + _currentCombination + "]";
  }

}
