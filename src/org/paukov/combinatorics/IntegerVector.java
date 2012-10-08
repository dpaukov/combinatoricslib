/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a simple vector of elements of type <code>int</code>
 * 
 * @author Dmytro Paukov
 * @see IntegerGenerator
 * @see IntegerFactory
 * @version 2.0
 */
public class IntegerVector {

	protected final int[] _vector;

	/**
	 * Default constructor creates an empty vector
	 * <p>
	 * Notes: Use the factory method
	 * <code>IntegerFactory.createIntegerVector()</code> to create an empty
	 * vector
	 * 
	 * @see IntegerFactory
	 */
	public IntegerVector() {
		this(0);
	}

	/**
	 * Default constructor creates the empty vector
	 * <p>
	 * Notes: Use the factory method
	 * <code>IntegerFactory.createIntegerVector()</code> to create an empty
	 * vector
	 * 
	 * @see IntegerFactory
	 */
	public IntegerVector(int size) {
		_vector = new int[size];
	}

	/**
	 * Constructor from an array
	 * <p>
	 * Notes: Use the factory method
	 * <code>IntegerFactory.createIntegerVector()</code> to create a vector from
	 * an array
	 * 
	 * @see IntegerFactory
	 * 
	 * @param array
	 *            Initial array to initialize the created vector
	 */
	public IntegerVector(int[] array, int len) {
		_vector = new int[len];
		System.arraycopy(array, 0, _vector, 0, len);
	}

	/**
	 * Constructor from an integer array
	 * <p>
	 * Notes: Use the factory method
	 * <code>IntegerFactory.createIntegerVector()</code> to create a vector from
	 * an array
	 * 
	 * @see IntegerFactory
	 * 
	 * @param array
	 *            Initial array to initialize the created vector
	 */
	public IntegerVector(int[] array) {
		this(array, array.length);
	}

	/**
	 * Copy constructor
	 * <p>
	 * Notes: Use the factory method
	 * <code>IntegerFactory.createIntegerVector()</code> to create a vector from
	 * another vector
	 * 
	 * @param vector
	 *            Initial vector to initialize the created vector
	 */
	public IntegerVector(IntegerVector vector) {
		_vector = new int[vector.getSize()];
		System.arraycopy(vector._vector, 0, _vector, 0, vector.getSize());
	}

	/**
	 * Sets the value of the <code>index</code>-element
	 */
	public void setValue(int index, int value) {
		_vector[index] = value;
	}

	/**
	 * Gets the value of the given index
	 * 
	 * @return The value of the <code>index</code> element
	 */
	public int getValue(int index) {
		return _vector[index];
	}

	/**
	 * Returns the size of the vector
	 * 
	 * @return The size of the vector
	 */
	public int getSize() {
		if (_vector == null) {
			return 0;
		}
		return _vector.length;
	}

	/**
	 * This method detects duplicates in the vector
	 * 
	 * @return <code>true</code> if the vector has duplicates, otherwise
	 *         <code>false</code>
	 */
	public boolean hasDuplicates() {
		if (_vector.length <= 1) {
			return false;
		}
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < _vector.length; i++) {
			set.add(_vector[i]);
		}
		return set.size() < _vector.length;
	}

	/**
	 * This method detects if the vector's elements are equal. For example, this
	 * method returns true for a vector (a, a, a) and false for a vector (a, b,
	 * a)
	 * 
	 * @return <code>True</code> if the vector's elements are equal
	 */
	public boolean isAllElementsEqual() {
		if (_vector.length == 0) {
			return false;
		}
		if (_vector.length == 1) {
			return true;
		}
		for (int i = 0; i < _vector.length - 1; i++) {
			if (_vector[i] != _vector[i + 1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns vector as a list of elements
	 * 
	 * @return List of all elements
	 */
	public int[] getVector() {
		return _vector;
	}

	/**
	 * Hash code of the vector
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_vector == null) ? 0 : Arrays.hashCode(_vector));
		return result;
	}

	/**
	 * Equals
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntegerVector other = (IntegerVector) obj;
		if (_vector == other._vector) {
			return true;
		}
		if (_vector == null) {
			return false;
		}
		if (other._vector == null) {
			return false;
		}

		if (_vector.length != other._vector.length) {
			return false;
		}
		for (int i = 0; i < _vector.length; i++) {
			if (_vector[i] != other._vector[i]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns vector as a string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("IntegerVector=([");
		for (int i = 0; i < _vector.length; i++) {
			sb.append(_vector[i]);
			if (i < _vector.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("], size=" + getSize() + ")");
		return sb.toString();
	}

}
