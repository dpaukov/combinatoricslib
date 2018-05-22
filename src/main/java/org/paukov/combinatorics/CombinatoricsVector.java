/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class represents a simple vector of element's type <code>T</code>
 *
 * @param <T> Type of the elements
 * @author Dmytro Paukov
 * @version 2.0
 * @see Generator
 * @see Iterator
 * @see CombinatoricsFactory
 */
public class CombinatoricsVector<T> implements ICombinatoricsVector<T> {

  protected final List<T> _vector;

  /**
   * Default constructor creates the empty vector
   * <p>
   * Notes: Use the factory method <code>Factory.createVector()</code> to
   * create an empty vector
   */
  public CombinatoricsVector() {
    _vector = new ArrayList<>();
  }

  /**
   * Constructor from a collection
   * <p>
   * Notes: Use the factory method <code>Factory.createVector()</code> to
   * create a vector from a collection
   *
   * @param vector Initial collection to initialize the created vector
   */
  public CombinatoricsVector(Collection<? extends T> vector) {
    _vector = new ArrayList<>(vector.size());
    _vector.addAll(vector);
  }

  /**
   * Constructor from an array
   * <p>
   * Notes: Use the factory method <code>Factory.createVector()</code> to
   * create a vector from an array
   *
   * @param array Initial array to initialize the created vector
   */
  public CombinatoricsVector(T... array) {
    _vector = new ArrayList<>(array.length);
    Collections.addAll(_vector, array);
  }

  /**
   * Copy constructor
   * <p>
   * <p>
   * Notes: Use the factory method <code>Factory.createVector()</code> to
   * create a vector from another vector
   *
   * @param vector Initial combinatorics vector to initialize the created vector
   */
  public CombinatoricsVector(ICombinatoricsVector<T> vector) {
    _vector = new ArrayList<>(vector.getSize());
    _vector.addAll(vector.getVector());
  }

  /**
   * Sets value to position <code>index</code>. If the index is out of bounds
   * the value will be added at the end of the vector
   *
   * @param index Position of element
   * @param value Value of the element to be set
   */
  @Override
  public void setValue(int index, T value) {
    if (index < _vector.size()) {
      _vector.set(index, value);
    } else {
      _vector.add(index, value);
    }
  }

  /**
   * Adds value to this vector.
   *
   * @param value Value of the element to be added
   */
  @Override
  public boolean addValue(T value) {
    return _vector.add(value);
  }

  /**
   * Returns value of the <code>index</code>-element
   *
   * @param index The position of the element (Index of the element)
   * @return Value of the element
   */
  @Override
  public T getValue(int index) {
    return _vector.get(index);
  }

  /**
   * Returns the size of the vector
   */
  @Override
  public int getSize() {
    if (_vector == null) {
      return 0;
    }
    return _vector.size();
  }

  /**
   * This method detects duplicates in the vector
   *
   * @return <code>true</code> if the vector has duplicates, otherwise <code>false</code>
   */
  @Override
  public boolean hasDuplicates() {
    if (_vector.size() <= 1) {
      return false;
    }
    Set<T> set = new HashSet<>(_vector);
    return set.size() < _vector.size();
  }

  /**
   * This method calculates the count of duplicates of a given value
   *
   * @return Number of the duplicates within the vector
   */
  @Override
  public int countElements(T value) {
    if (_vector.size() == 0) {
      return 0;
    }

    int count = 0;
    for (T element : _vector) {
      if (element == value || (element != null && element.equals(value))) {
        count++;
      }
    }
    return count;
  }

  /**
   * This method detects if the victor contains a given value
   *
   * @return <code>true</code> if the vector contains the value, otherwise <code>false</code>
   */
  @Override
  public boolean contains(T value) {
    return _vector.contains(value);
  }

  /**
   * This method detects if the vector's elements are equal. For example, this
   * method returns true for a vector (a, a, a) and false for a vector (a, b, a)
   *
   * @return <code>True</code> if the vector's elements are equal
   */
  @Override
  public boolean isAllElementsEqual() {
    if (_vector.size() == 0) {
      return false;
    }
    if (_vector.size() == 1) {
      return true;
    }
    Set<T> set = new HashSet<T>(_vector);
    return set.size() == 1;
  }

  /**
   * Returns this vector as a list
   */
  @Override
  public List<T> getVector() {
    return _vector;
  }

  /**
   * Hash code of the current vector
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_vector == null) ? 0 : _vector.hashCode());
    return result;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    CombinatoricsVector<?> other = (CombinatoricsVector<?>) obj;
    if (_vector == null) {
      if (other._vector != null) {
        return false;
      }
    } else if (!_vector.equals(other._vector)) {
      return false;
    }
    return true;
  }

  /**
   * Clear the elements
   */
  @Override
  public void clearVector() {
    _vector.clear();
  }

  /**
   * Returns an iterator over the current vector
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<T> iterator() {
    return _vector.iterator();
  }

  /**
   * Returns vector as a string
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "CombinatoricsVector=(" + _vector + ", size=" + getSize() + ")";
  }

}
