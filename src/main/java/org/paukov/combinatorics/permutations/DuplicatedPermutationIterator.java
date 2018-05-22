/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.permutations;

import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Iterator for the permutation generator which contains duplicates
 *
 * @param <T> Type of elements in the permutations
 * @author Dmytro Paukov
 * @version 2.0
 * @see ICombinatoricsVector
 * @see PermutationGenerator
 */
class DuplicatedPermutationIterator<T> implements
    Iterator<ICombinatoricsVector<T>> {

  /**
   * Generator
   */
  final Generator<T> _generator;

  /**
   * Number of elements in the permutations
   */
  final int _length;

  /**
   * Current permutation
   */
  ICombinatoricsVector<T> _currentPermutation;

  /**
   * Current index of current permutation
   */
  long _currentIndex = 0;

  ICombinatoricsVector<T> _initialOrderedPermutation;

  /**
   * Internal data
   */
  private int _data[] = null;
  private boolean _firstIteration = true;

  /**
   * Constructor
   *
   * @param generator Permutation generator
   */
  DuplicatedPermutationIterator(Generator<T> generator) {
    _generator = generator;
    _length = generator.getOriginalVector().getSize();
    _data = new int[_length];

    ICombinatoricsVector<T> originalVector = generator.getOriginalVector();
    ICombinatoricsVector<T> initialPermutation = createVector();

    // Create a set of the initial data
    Set<T> initialSet = new LinkedHashSet<T>(originalVector.getVector());
    initialSet.addAll(originalVector.getVector());

    // Create internal data
    int dataValue = 0;
    int dataIndex = 0;

    _initialOrderedPermutation = createVector(initialSet);

    for (int i = 0; i < _initialOrderedPermutation.getSize(); i++) {

      T value = _initialOrderedPermutation.getValue(i);
      dataValue++;

      if (!initialPermutation.contains(value)) {
        // Determine how many duplicates of the value in the original
        // vector
        int count = originalVector.countElements(value);

        for (int countIndex = 0; countIndex < count; countIndex++) {
          _data[dataIndex++] = dataValue;
          initialPermutation.addValue(value);
        }
      }
    }

    init();
  }

  private static void swap(int[] data, int k, int l) {
    int temp = data[k];
    data[k] = data[l];
    data[l] = temp;
  }

  private boolean isFinished() {
    int k = _data.length - 2;
    while (_data[k] >= _data[k + 1]) {
      k--;
      if (k < 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Initialize the iteration process
   */
  private void init() {
    _currentIndex = 0;

    _currentPermutation = createVector();

    for (int i = 0; i < _length; i++) {
      int index = _data[i] - 1;
      _currentPermutation.addValue(_initialOrderedPermutation
          .getValue(index));
    }
  }

  /**
   * Return true if the iteration process is finished
   */
  @Override
  public boolean hasNext() {
    return !isFinished() || _firstIteration;
  }

  /**
   * Moves to the next permutation
   */
  @Override
  public ICombinatoricsVector<T> next() {

    if (_firstIteration) {
      _firstIteration = false;
      return _currentPermutation;
    }

    int k = _data.length - 2;

    while (_data[k] >= _data[k + 1]) {
      k--;
    }

    int l = _data.length - 1;
    while (_data[k] >= _data[l]) {
      l--;
    }
    swap(_data, k, l);
    int length = _data.length - (k + 1);
    for (int i = 0; i < length / 2; i++) {
      swap(_data, k + 1 + i, _data.length - i - 1);
    }

    _currentIndex++;
    _currentPermutation = createVector();

    for (int i = 0; i < _length; i++) {
      int index = _data[i] - 1;
      _currentPermutation.addValue(_initialOrderedPermutation
          .getValue(index));
    }

    return _currentPermutation;
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
    return "DuplicatedPermutationIterator=[#" + _currentIndex + ", "
        + _currentPermutation + "]";
  }

}