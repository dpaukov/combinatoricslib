/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.partition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Iterator for enumerating the all possible integer partitions
 * 
 * @author Dmytro Paukov
 * @see ICombinatoricsVector
 * @see PartitionGenerator
 * @version 2.0
 */
public class PartitionIterator implements
		Iterator<ICombinatoricsVector<Integer>> {

	/**
	 * Generator
	 */
	protected final PartitionGenerator _generator;

	/**
	 * Current partition
	 */
	protected ICombinatoricsVector<Integer> _currentPartition = null;

	/**
	 * Index of the current partition
	 */
	protected long _currentIndex = 0;

	/**
	 * Helper vectors
	 */
	private int[] _mVector = null;
	private int[] _zVector = null;

	/**
	 * Stop criteria
	 */
	private int _kIndex = 1;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            Generator
	 */
	public PartitionIterator(PartitionGenerator generator) {
		_generator = generator;
		_mVector = new int[generator._initialValue + 2];
		_zVector = new int[generator._initialValue + 2];
		init();
	}

	/**
	 * Initializes the iterator
	 */
	private void init() {

		if (_generator._initialValue < 1) {
			_kIndex = 0;
			return;
		}

		_currentIndex = 0;
		_kIndex = 1;

		setInternalVectorValue(-1, _zVector, 0);
		setInternalVectorValue(-1, _mVector, 0);

		setInternalVectorValue(0, _zVector, _generator._initialValue + 1);
		setInternalVectorValue(0, _mVector, 0);

		setInternalVectorValue(1, _zVector, 1);
		setInternalVectorValue(1, _mVector, _generator._initialValue);
	}

	/**
	 * Returns true if all partitions were enumerated
	 */
	@Override
	public boolean hasNext() {
		return _kIndex != 0;
	}

	/**
	 * Moves to the next partition
	 */
	@Override
	public ICombinatoricsVector<Integer> next() {
		_currentIndex++;
		createCurrentPartition(_kIndex);
		int sum = getInternalVectorValue(_kIndex, _mVector)
				* getInternalVectorValue(_kIndex, _zVector);
		if (getInternalVectorValue(_kIndex, _mVector) == 1) {
			_kIndex--;
			sum += getInternalVectorValue(_kIndex, _mVector)
					* getInternalVectorValue(_kIndex, _zVector);
		}
		if (getInternalVectorValue(_kIndex - 1, _zVector) == getInternalVectorValue(
				_kIndex, _zVector) + 1) {
			_kIndex--;
			setInternalVectorValue(_kIndex, _mVector,
					getInternalVectorValue(_kIndex, _mVector) + 1);
		} else {
			setInternalVectorValue(_kIndex, _zVector,
					getInternalVectorValue(_kIndex, _zVector) + 1);
			setInternalVectorValue(_kIndex, _mVector, 1);
		}
		if (sum > getInternalVectorValue(_kIndex, _zVector)) {
			setInternalVectorValue(_kIndex + 1, _zVector, 1);
			setInternalVectorValue(_kIndex + 1, _mVector, sum
					- getInternalVectorValue(_kIndex, _zVector));
			_kIndex++;
		}

		// return the current partition
		return _currentPartition;
	}

	/**
	 * Creates current partition based on the internal vectors
	 */
	private void createCurrentPartition(int k) {
		List<Integer> list = new ArrayList<Integer>();
		for (int index = 1; index <= k; index++) {
			for (int j = 0; j < getInternalVectorValue(index, _mVector); j++) {
				list.add(getInternalVectorValue(index, _zVector));
			}
		}
		_currentPartition = Factory.createVector(list);
	}

	private final int getInternalVectorValue(int index, int[] vector) {
		return vector[index + 1];
	}

	private final void setInternalVectorValue(int index, int[] vector, int value) {
		vector[index + 1] = value;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the current partition as a string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PartitionIterator=[#" + _currentIndex + ", "
				+ _currentPartition + "]";
	}

}
