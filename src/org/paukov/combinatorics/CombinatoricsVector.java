package org.paukov.combinatorics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a simple vector of elements of type <code>T</code>
 * 
 * @author Dmytro Paukov
 * @see Generator
 * @see Iterator
 * @param <T>
 *            Type of the elements
 */
class CombinatoricsVector<T> implements ICombinatoricsVector<T> {

	protected final List<T> _vector;

	/**
	 * Default constructor creates the empty vector
	 */
	CombinatoricsVector() {
		_vector = new ArrayList<T>();
	}

	/**
	 * Constructor from collection
	 * 
	 * @param vector
	 *            Initial collection to initialize the created vector
	 */
	CombinatoricsVector(Collection<? extends T> vector) {
		_vector = new ArrayList<T>(vector.size());
		_vector.addAll(vector);
	}
	
	/**
	 * Constructor from array
	 * 
	 * @param array
	 *            Initial array to initialize the created vector
	 */
	CombinatoricsVector(T[] array) {
		_vector = new ArrayList<T>(array.length);
		for (int i = 0; i < array.length; i++) {
			_vector.add(array[i]);
		}
	}
	

	/**
	 * Copy constructor
	 * 
	 * @param vector
	 *            Initial vector to initialize the created vector
	 */
	CombinatoricsVector(ICombinatoricsVector<T> vector) {
		_vector = new ArrayList<T>(vector.getSize());
		_vector.addAll(vector.getVector());
	}
	
	
	/**
	 * @see org.paukov.combinatorics.ICombinatoricsVector#setValue(int, T)
	 */
	@Override
	public void setValue(int index, T value) {
		try {
			_vector.set(index, value);
		} catch (IndexOutOfBoundsException ex) {
			_vector.add(index, value);
		}
	}

	/**
	 * @see org.paukov.combinatorics.ICombinatoricsVector#addValue(T)
	 */
	@Override
	public boolean addValue(T value) {
		return _vector.add(value);
	}

	/**
	 * @see org.paukov.combinatorics.ICombinatoricsVector#getValue(int)
	 */
	@Override
	public T getValue(int index) {
		return _vector.get(index);
	}

	/**
	 * @see org.paukov.combinatorics.ICombinatoricsVector#getSize()
	 */
	@Override
	public int getSize() {
		if (_vector == null) {
			return 0;
		}
		return _vector.size();
	}

	/**
	 * @see org.paukov.combinatorics.ICombinatoricsVector#hasDuplicates()
	 */
	@Override
	public boolean hasDuplicates() {
		Set<T> set = new HashSet<T>(_vector);
		return set.size() < _vector.size();
	}

	/**
	 * @see org.paukov.combinatorics.ICombinatoricsVector#getVector()
	 */
	@Override
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
	 * @see org.paukov.combinatorics.ICombinatoricsVector#clearVector()
	 */
	@Override
	public void clearVector() {
		_vector.clear();
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
