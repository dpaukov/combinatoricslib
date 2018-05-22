/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.composition;

import static org.paukov.combinatorics.CombinatoricsFactory.createSubSetGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.Iterator;
import java.util.List;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Iterator for enumeration of all compositions
 *
 * @author Dmytro Paukov
 * @version 2.0
 * @see ICombinatoricsVector
 * @see CompositionGenerator
 */
class CompositionIterator implements
    Iterator<ICombinatoricsVector<Integer>> {

  final CompositionGenerator _generator;
  /**
   * Subset generator
   */
  final Generator<Integer> _subsetGenerator;
  /**
   * Subset iterator
   */
  final Iterator<ICombinatoricsVector<Integer>> _subsetIterator;
  /**
   * Current composition
   */
  ICombinatoricsVector<Integer> _currentComposition = null;
  /**
   * Current index of the weak composition
   */
  long _currentIndex = 0;
  /**
   * Current subset
   */
  ICombinatoricsVector<Integer> _currentSubset = null;

  /**
   * Constructor of the iterator
   *
   * @param generator The Composition generator
   */
  CompositionIterator(CompositionGenerator generator) {
    super();
    _generator = generator;

    ICombinatoricsVector<Integer> coreSet = createVector();

    for (int i = 1; i < this._generator._initialValue; i++) {
      coreSet.addValue(i);
    }

    _subsetGenerator = createSubSetGenerator(coreSet);

    _subsetIterator = _subsetGenerator.iterator();
  }

  /**
   * Returns the next composition
   */
  @Override
  public ICombinatoricsVector<Integer> next() {
    _currentIndex++;
    _currentSubset = this._subsetIterator.next();
    return getCurrentItem();
  }

  /**
   * Returns true when all composition are iterated
   */
  @Override
  public boolean hasNext() {
    return this._subsetIterator.hasNext();
  }

  /**
   * Returns current composition
   */
  private ICombinatoricsVector<Integer> getCurrentItem() {

    _currentComposition = createVector();

    List<Integer> vector = _currentSubset.getVector();
    java.util.Iterator<Integer> itr = vector.iterator();

    int currentValueSubSet = 0;
    int indexCompositionElement = 0;
    int valueCompositionElement = 0;

    while (itr.hasNext()) {
      Integer currentSubsetElement = itr.next();
      valueCompositionElement = currentSubsetElement - currentValueSubSet;
      _currentComposition.setValue(indexCompositionElement,
          valueCompositionElement);
      indexCompositionElement++;
      currentValueSubSet = currentSubsetElement;
    }
    _currentComposition.setValue(indexCompositionElement,
        _generator._initialValue - currentValueSubSet);

    return _currentComposition;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns composition as a string
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "CompositionIterator=[#" + _currentIndex + ", "
        + _currentComposition + "]";
  }

}
