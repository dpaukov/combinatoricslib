package org.paukov.combinatorics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents a simple vector of elements of type <code>int</code>
 * 
 * @author Dmytro Paukov
 * @see Generator
 * @see Iterator
 */
public class IntegerVector {

	protected final int[] _vector;

	/**
	 * Default constructor creates the empty vector
	 */
	IntegerVector() {
		this(0);
	}

	/**
	 * Default constructor creates the empty vector
	 */
	IntegerVector(int size) {
		_vector = new int[size];
	}

	/**
	 * Constructor from array
	 * 
	 * @param array
	 *            Initial array to initialize the created vector
	 */
	IntegerVector(int[] array, int len) {
		_vector = new int[len];
		System.arraycopy(array, 0, _vector, 0, len);
	}

	IntegerVector(int[] array) {
		this(array, array.length);
	}

	/**
	 * Copy constructor
	 * 
	 * @param vector
	 *            Initial vector to initialize the created vector
	 */
	IntegerVector(IntegerVector vector) {
		_vector = new int[vector.getSize()];
		System.arraycopy(vector._vector, 0, _vector, 0, vector.getSize());
	}

	/**
	 * @see org.paukov.combinatorics.ICombinatoricsVector#setValue(int, T)
	 */
	public void setValue(int index, int value) {
		_vector[index] = value;
	}

	/**
	 * @see org.paukov.combinatorics.ICombinatoricsVector#getValue(int)
	 */
	public int getValue(int index) {
		return _vector[index];
	}

	/**
	 * @see org.paukov.combinatorics.ICombinatoricsVector#getSize()
	 */
	public int getSize() {
		if (_vector == null) {
			return 0;
		}
		return _vector.length;
	}

	/**
	 * @see org.paukov.combinatorics.ICombinatoricsVector#hasDuplicates()
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
	 * @see org.paukov.combinatorics.ICombinatoricsVector#isAllElementsEqual()
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
	 * @see org.paukov.combinatorics.ICombinatoricsVector#getVector()
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
