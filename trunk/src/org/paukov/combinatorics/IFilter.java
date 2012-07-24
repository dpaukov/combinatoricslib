package org.paukov.combinatorics;

/**
 * This is an interface for filtering the generated combinatorial vectors
 * 
 * @author Dmytro Paukov
 * 
 * @param <T>
 */
public interface IFilter<T> {

	/**
	 * This method defines a condition for a given value to be accepted by the
	 * filter or not
	 * 
	 * @param index
	 *            The index of the value
	 * @param value
	 *            The value to be tested
	 * @return <code>true</code> if the value has to be accepted, otherwise
	 *         <code>false</code>
	 */
	boolean accepted(long index, T value);

}
