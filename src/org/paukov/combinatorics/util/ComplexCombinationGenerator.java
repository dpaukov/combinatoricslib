package org.paukov.combinatorics.util;

import java.util.Iterator;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;


/**
 * This generator generates non-overlapping sublists of a given vector/list.
 * 
 * <p>
 * It is possible to generate non-overlapping sublists of length <b>n</b> of a given <b>list</b>
 * <p>
 * For example, if a list is (A, B, B, C), then the non-overlapping sublists of length 2 will be: 
 * <ol>
 * <li>( (A), (B, B, C) )
 * <li>( (B, B, C), (A) )
 * <li>( (B), (A, B, C) )
 * <li>( (A, B, C), (B) )
 * <li>( (A, B), (B, C) )
 * <li>( (B, C), (A, B) )
 * <li>( (B, B), (A, C) )
 * <li>( (A, C), (B, B) )
 * <li>( (A, B, B), (C) )
 * <li>( (C), (A, B, B) ) 
 * </ol>
 * <p>
 * To do that you should use an instance of the complex combination generator 
 * <p>
 * <blockquote>
 * <pre>
 * // create a vector (A, B, B, C)
 * ICombinatoricsVector&lt;String&gt; vector = Factory.createVector(new String[] { "A", "B", "B", "C" });
 *
 * // Create a complex-combination generator
 * Generator&lt;ICombinatoricsVector&lt;String&gt;&gt; gen = new ComplexCombinationGenerator&lt;String&gt;(vector, 2);
 *
 * // Iterate the combinations
 * for (ICombinatoricsVector&lt;ICombinatoricsVector&lt;String&gt;&gt; comb : gen) {
 *    System.out.println(ComplexCombinationGenerator.convert2String(comb) + " - " + comb);
 * }
 * </pre>
 *
 * </blockquote>
 * <p>
 * And the result
 * <p>
 * <blockquote>
 *
 *<pre>
 * ([A],[B, B, C]) - CombinatoricsVector=([CombinatoricsVector=([A], size=1), CombinatoricsVector=([B, B, C], size=3)], size=2)
 * ([B, B, C],[A]) - CombinatoricsVector=([CombinatoricsVector=([B, B, C], size=3), CombinatoricsVector=([A], size=1)], size=2)
 * ([B],[A, B, C]) - CombinatoricsVector=([CombinatoricsVector=([B], size=1), CombinatoricsVector=([A, B, C], size=3)], size=2)
 * ([A, B, C],[B]) - CombinatoricsVector=([CombinatoricsVector=([A, B, C], size=3), CombinatoricsVector=([B], size=1)], size=2)
 * ([A, B],[B, C]) - CombinatoricsVector=([CombinatoricsVector=([A, B], size=2), CombinatoricsVector=([B, C], size=2)], size=2)
 * ([B, C],[A, B]) - CombinatoricsVector=([CombinatoricsVector=([B, C], size=2), CombinatoricsVector=([A, B], size=2)], size=2)
 * ([B, B],[A, C]) - CombinatoricsVector=([CombinatoricsVector=([B, B], size=2), CombinatoricsVector=([A, C], size=2)], size=2)
 * ([A, C],[B, B]) - CombinatoricsVector=([CombinatoricsVector=([A, C], size=2), CombinatoricsVector=([B, B], size=2)], size=2)
 * ([A, B, B],[C]) - CombinatoricsVector=([CombinatoricsVector=([A, B, B], size=3), CombinatoricsVector=([C], size=1)], size=2)
 * ([C],[A, B, B]) - CombinatoricsVector=([CombinatoricsVector=([C], size=1), CombinatoricsVector=([A, B, B], size=3)], size=2)
 *</pre>
 *
 *</blockquote>
 * @author Dmytro Paukov
 * @see ComplexCombinationIterator
 */
public class ComplexCombinationGenerator<T> extends
		Generator<ICombinatoricsVector<T>> {

	protected final ICombinatoricsVector<T> _originalVector;
	protected final int _combinationLength;
	protected final boolean _isOrderImportant;
	protected final boolean _excludeEmptySet;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original vector/set which is used for combination generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 */
	public ComplexCombinationGenerator(ICombinatoricsVector<T> originalVector,
			int combinationsLength) {
		this(originalVector, combinationsLength, true, true);
	}

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original vector/set which is used for combination generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 * @param isOrderImportant
	 *            true if the order of the generated combinations is important
	 * 
	 */
	public ComplexCombinationGenerator(ICombinatoricsVector<T> originalVector,
			int combinationsLength, boolean isOrderImportant) {
		this(originalVector, combinationsLength, isOrderImportant, false);
	}

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original vector/set which is used for combination generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 * @param isOrderImportant
	 *            true if the order of the generated combinations is important
	 * @param excludeEmptySet
	 *            true if the empty set has to be excluded from the generated
	 *            combinations
	 */
	public ComplexCombinationGenerator(ICombinatoricsVector<T> originalVector,
			int combinationsLength, boolean isOrderImportant,
			boolean excludeEmptySet) {

		if (combinationsLength > originalVector.getSize())
			throw new RuntimeException(
					"Unable to generate complex combinations, the requested combination length is more then the size of the original vector, length: "
							+ combinationsLength
							+ ", originalVector: "
							+ originalVector);

		_originalVector = Factory.createVector(originalVector);
		_combinationLength = combinationsLength;
		_isOrderImportant = isOrderImportant;
		_excludeEmptySet = excludeEmptySet;
	}

	/**
	 * Returns the original vector wrapped into one more vector.
	 * 
	 * @return Returns the _originalVector.
	 */
	public ICombinatoricsVector<ICombinatoricsVector<T>> getOriginalVector() {
		ICombinatoricsVector<ICombinatoricsVector<T>> result = Factory
				.<ICombinatoricsVector<T>> createVector();
		result.addValue(_originalVector);
		return result;
	}

	/**
	 * Returns length of the combinations
	 * 
	 * @return Returns the _combinationLength.
	 */
	public int getCombinationLength() {
		return _combinationLength;
	}

	/**
	 * Returns number of generated combinations
	 */
	public long getNumberOfGeneratedObjects() {
		// TODO: needs to be implemented
		throw new UnsupportedOperationException();
	}

	/**
	 * Creates an iterator of the complex combinations
	 */
	@Override
	public Iterator<ICombinatoricsVector<ICombinatoricsVector<T>>> iterator() {
		return new ComplexCombinationIterator<T>(this);
	}

	/**
	 * Is the order of the generated combinations important
	 */
	public boolean isOrderImportant() {
		return _isOrderImportant;
	}

	/**
	 * Returns true if the empty set has to be excluded from the generated
	 * combinations
	 */
	public boolean excludeEmptySet() {
		return _excludeEmptySet;
	}

	/**
	 * This method converts a composition into a string
	 * 
	 * @param vector
	 *            The vector
	 * @return A string representation of the given vector
	 */
	public static <T> String convert2String(
			ICombinatoricsVector<ICombinatoricsVector<T>> vector) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < vector.getSize(); i++) {
			sb.append(vector.getValue(i).getVector().toString());
			if (i != vector.getSize() - 1)
				sb.append(",");
		}
		sb.append(")");
		return sb.toString();

	}
	
	/**
	 * This method converts a composition into a string
	 * 
	 * @param vector
	 *            The vector
	 * @return A string representation of the given vector
	 */
	public static <T> String convertIndexes2String(T[] elements,
			ICombinatoricsVector<ICombinatoricsVector<Integer>> vector) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < vector.getSize(); i++) {
			sb.append("[");
			for (int j = 0; j < vector.getValue(i).getVector().size(); j++)	{
				sb.append(elements[vector.getValue(i).getValue(j)]);
				if (j != vector.getValue(i).getVector().size() - 1)
					sb.append(",");
			}
			sb.append("]");
			if (i != vector.getSize() - 1)
				sb.append(",");
		}
		sb.append(")");
		return sb.toString();

	}
}
