/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.permutations;

import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Iterator for the permutation with repetitions
 *
 * @param <T> Type of the elements in the permutations
 * @author Dmytro Paukov
 * @version 2.0
 * @see ICombinatoricsVector
 * @see PermutationWithRepetitionGenerator
 */
class PermutationWithRepetitionIterator<T extends Object> implements
    Iterator<ICombinatoricsVector<T>> {

  /**
   * Generator
   */
  final PermutationWithRepetitionGenerator<T> _generator;

  /**
   * Number of elements in the core vector
   */
  final int _n;

  /**
   * Number of elements in the generated permutations
   */
  final int _k;

  /**
   * Current permutation
   */
  ICombinatoricsVector<T> _currentPermutation = null;

  /**
   * Index of the current permutation
   */
  long _currentIndex = 0;

  /**
   * Internal data
   */
  private int[] _bitVector = null;

  /**
   * Constructor
   *
   * @param generator Generator
   */
  PermutationWithRepetitionIterator(
      PermutationWithRepetitionGenerator<T> generator) {
    _generator = generator;
    _n = generator.getOriginalVector().getSize();
    _k = generator.getPermutationLength();

    List<T> list = new ArrayList<T>(_k);
    T defaultValue = generator.getOriginalVector().getValue(0);
    for (int i = 0; i < _k; i++) {
      list.add(defaultValue);
    }

    _currentPermutation = createVector(list);

    _bitVector = new int[_k + 2];
    init();
  }

  /**
   * Initializes iteration process
   *
   * @see org.paukov.combinatorics.Iterator#first()
   */
  private void init() {
    for (int j = 0; j <= _k; j++) {
      _bitVector[j] = 0;
    }
    _currentIndex = 0;
  }

  /**
   * Returns true if all permutations have been iterated
   *
   * @see Iterator#hasNext()
   */
  @Override
  public boolean hasNext() {
    return (_bitVector[_k] != 1);
  }

  /**
   * Moves to the next permutation
   *
   * @see Iterator#next()
   */
  @Override
  public ICombinatoricsVector<T> next() {
    _currentIndex++;

    for (int j = _k - 1; j >= 0; j--) {
      int index = _bitVector[j];
      _currentPermutation.setValue(j, _generator.getOriginalVector()
          .getValue(index));
    }

    int i = 0;
    while (_bitVector[i] == _n - 1) {
      if (i < _k + 1) {
        _bitVector[i] = 0;
      } else {
        _bitVector[_k] = 1;
        return createVector(_currentPermutation);
      }
      i++;
    }

    _bitVector[i]++;
    return createVector(_currentPermutation);

  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the current permutation as a string
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "PermutationWithRepetitionIterator=[#" + _currentIndex + ", " + _currentPermutation
        + "]";
  }
}
