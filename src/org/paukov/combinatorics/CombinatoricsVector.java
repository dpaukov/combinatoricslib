package org.paukov.combinatorics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represents a simple vector of elements of type <code>T</code>
 * 
 * @author Dmytro Paukov
 * @see Generator
 * @see Iterator
 * @param <T>
 *            Type of the elements
 */
public class CombinatoricsVector<T> {

	protected final ArrayList<T> _vector;

	/**
	 * Default constructor
	 */
	public CombinatoricsVector() {
		_vector = new ArrayList<T>();
	}

	/**
	 * Constructor
	 * 
	 * @param size
	 *            Size of the created vector
	 * @param defaulValue
	 *            Default/Initial value for the vector's elements
	 */
	public CombinatoricsVector(int size, T defaulValue) {
		_vector = new ArrayList<T>(size);
		for (int i = 0; i < size; i++) {
			_vector.add(defaulValue);
		}
	}

	/**
	 * Copy constructor
	 * 
	 * @param vector
	 *            Initial vector to initialize the created vector
	 */
	public CombinatoricsVector(CombinatoricsVector<T> vector) {
		_vector = new ArrayList<T>(vector.getSize());
		_vector.addAll(vector.getVector());
	}

	/**
	 * Constructor
	 * 
	 * @param vector
	 *            Initial collection to initialize the created vector
	 */
	public CombinatoricsVector(Collection<? extends T> vector) {
		_vector = new ArrayList<T>(vector.size());
		_vector.addAll(vector);
	}

	/**
	 * Sets value to position <code>index</code>. If the index is out of bounds
	 * the value will be added at the end of the vector
	 * 
	 * @param index
	 *            Position of element
	 * @param value
	 *            Value of element
	 */
	public void setValue(int index, T value) {
		try {
			_vector.set(index, value);
		} catch (IndexOutOfBoundsException ex) {
			_vector.add(index, value);
		}
	}

	/**
	 * Adds value to the vector.
	 * 
	 * @param value
	 *            Value of element
	 */
	public boolean addValue(T value) {
		return _vector.add(value);
	}

	/**
	 * Returns value of the <code>index</code>-element
	 * 
	 * @param index
	 *            The position of the element (Index of the element)
	 * @return Value of the element
	 */
	public T getValue(int index) {
		return _vector.get(index);
	}

	/**
	 * Returns size of the vector
	 * 
	 * @return Current size of the vector
	 */
	public int getSize() {
		if (_vector == null) {
			return 0;
		}
		return _vector.size();
	}

	/**
	 * Returns vector as a list of elements
	 * 
	 * @return List of all elements
	 */
	public List<T> getVector() {
		return _vector;
	}

	/**
	 * Hash code of the vector
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
		CombinatoricsVector<?> other = (CombinatoricsVector<?>) obj;
		if (_vector == null) {
			if (other._vector != null)
				return false;
		} else if (!_vector.equals(other._vector))
			return false;
		return true;
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
