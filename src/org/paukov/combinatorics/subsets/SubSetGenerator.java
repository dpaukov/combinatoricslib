package org.paukov.combinatorics.subsets;

import java.util.Iterator;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates all subsets of the specified set/vector.
 * <p>
 * A set A is a subset of a set B if A is "contained" inside B. A and B may
 * coincide. The relationship of one set being a subset of another is called
 * inclusion or sometimes containment.
 * <p>
 * Examples:
 * <ul>
 * <li>The set (1, 2) is a proper subset of (1, 2, 3).
 * <li>Any set is a subset of itself, but not a proper subset.
 * <li>The empty set, denoted by (), is also a subset of any given set X.
 * </ul>
 * <p>
 * All subsets of (1, 2, 3) are:
 * <ol>
 * <li>()
 * <li>(1)
 * <li>(2)
 * <li>(1, 2)
 * <li>(3)
 * <li>(1, 3)
 * <li>(2, 3)
 * <li>(1, 2, 3)
 * </ol>
 * <p>
 * And code which generates all subsets of (one, two, three)
 * <p>
 * <blockquote>
 * 
 * <pre>
 * // Create an initial vector/set
 * ICombinatoricsVector&lt;String&gt; initialSet = Factory.createVector(new String[] {
 * 		&quot;one&quot;, &quot;two&quot;, &quot;three&quot; });
 * 
 * // Create an instance of the subset generator
 * Generator&lt;String&gt; gen = Factory.createSubSetGenerator(initialSet);
 * 
 * // Print the subsets
 * for (ICombinatoricsVector&lt;String&gt; subSet : gen) {
 * 	System.out.println(subSet);
 * }
 * </pre>
 * 
 * </blockquote>
 * <p>
 * And the result of all 8 possible subsets
 * <p>
 * <blockquote>
 * 
 * <pre>
 *  CombinatoricsVector=([], size=0)
 *  CombinatoricsVector=([one], size=1)
 *  CombinatoricsVector=([two], size=1)
 *  CombinatoricsVector=([one, two], size=2)
 *  CombinatoricsVector=([three], size=1)
 *  CombinatoricsVector=([one, three], size=2)
 *  CombinatoricsVector=([two, three], size=2)
 *  CombinatoricsVector=([one, two, three], size=3)
 * </pre>
 * 
 * </blockquote>
 * <p>
 * Version 2.0 of the combinatoricslib supports sets with duplicates. For
 * example, if the original vector contains duplicates like (a, b, a, c), then
 * the result will contain 14 subsets (instead of 16): <blockquote>
 * 
 * <pre>
 * ()
 * (a)
 * (b)
 * (a, b)
 * (a, a)
 * (b, a)
 * (a, b, a)
 * (c)
 * (a, c)
 * (b, c)
 * (a, b, c)
 * (a, a, c)
 * (b, a, c)
 * (a, b, a, c)
 * </pre>
 * 
 * </blockquote>
 * <p>
 * If you still would like to treat the set with duplicates as not identical,
 * you should create a generator and set the second parameter of the method
 * <code>Factory.createSubSetGenerator()</code> as <code>false</code>. In this
 * case all 16 subsets will be generated.
 * <p>
 * 
 * <b>Note.</b> If the initial vector contains duplicates then the method
 * <code>getNumberOfGeneratedObjects</code> won't be able to return the number
 * of the sub sets/lists. It will throw a runtime exception
 * 
 * @author Dmytro.Paukov
 * @see ICombinatoricsVector
 * @see SubSetIterator
 * @param <T>
 *            Type of the elements in the set
 */
public class SubSetGenerator<T> extends Generator<T> {

	protected final boolean _hasDuplicates;
	protected final boolean _treatAsIdentical;

	/**
	 * Core set
	 */
	protected final ICombinatoricsVector<T> _originalVector;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original vector/set
	 */
	public SubSetGenerator(ICombinatoricsVector<T> originalVector) {
		_hasDuplicates = originalVector.hasDuplicates();
		_treatAsIdentical = true;
		_originalVector = Factory.createVector(originalVector);
	}

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Original vector/set
	 */
	public SubSetGenerator(ICombinatoricsVector<T> originalVector,
			boolean treatAsIdentical) {
		_hasDuplicates = originalVector.hasDuplicates();
		_treatAsIdentical = treatAsIdentical;
		_originalVector = Factory.createVector(originalVector);
	}

	/**
	 * Returns the core set
	 */
	public ICombinatoricsVector<T> getOriginalVector() {
		return _originalVector;
	}

	/**
	 * Returns the number of the subsets. If the original set contains
	 * duplicates this method will throw a runtime exception
	 * 
	 */
	public long getNumberOfGeneratedObjects() {

		if (isSubList())
			throw new RuntimeException("The initial vector has duplicates: "
					+ _originalVector);

		return Util.pow2(_originalVector.getSize());
	}

	/**
	 * Creates the iterator over the all subsets
	 */
	@Override
	public Iterator<ICombinatoricsVector<T>> iterator() {
		if (isSubList())
			return new SubListIterator<T>(this);
		else
			return new SubSetIterator<T>(this);
	}

	protected boolean isSubList() {
		return (_treatAsIdentical && _hasDuplicates);
	}

}
