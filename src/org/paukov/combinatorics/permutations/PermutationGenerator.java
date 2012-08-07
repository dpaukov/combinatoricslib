package org.paukov.combinatorics.permutations;

import java.util.Iterator;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates all possible permutations of the specified initial
 * vector
 * <p>
 * A permutation is an ordering of a set in the context of all possible
 * orderings. For example, the set containing the first three digits, 123, has
 * six permutations: 123, 132, 213, 231, 312, and 321.
 * <p>
 * This is an example of the permutations of 3 string items (apple, orange,
 * cherry):
 * <p>
 * <blockquote>
 * 
 * <pre>
 * 
 * // Create the initial vector of 3 elements (apple, orange, cherry)
 * ICombinatoricsVector&lt;String&gt; originalVector = Factory
 * 		.createVector(new String[] { &quot;apple&quot;, &quot;orange&quot;, &quot;cherry&quot; });
 * 
 * // Create the permutation generator by calling the appropriate method in the
 * // Factory class
 * Generator&lt;String&gt; gen = Factory.createPermutationGenerator(originalVector);
 * 
 * // Print the result
 * for (ICombinatoricsVector&lt;String&gt; perm : gen)
 * 	System.out.println(perm);
 * 
 * </pre>
 * 
 * </blockquote>
 * <p>
 * And the result
 * <p>
 * <blockquote>
 * 
 * <pre>
 *   CombinatoricsVector=([apple, orange, cherry], size=3)
 *   CombinatoricsVector=([apple, cherry, orange], size=3)
 *   CombinatoricsVector=([cherry, apple, orange], size=3)
 *   CombinatoricsVector=([cherry, orange, apple], size=3)
 *   CombinatoricsVector=([orange, cherry, apple], size=3)
 *   CombinatoricsVector=([orange, apple, cherry], size=3)
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro.Paukov
 * @see ICombinatoricsVector
 * @see PermutationIterator
 * @see Factory
 * @param <T>
 *            Type of the elements in the permutations
 */
public class PermutationGenerator<T> extends Generator<T> {

	/**
	 * Initial vector
	 */
	protected final ICombinatoricsVector<T> _originalVector;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            Vector which is used for permutation generation
	 */
	public PermutationGenerator(ICombinatoricsVector<T> originalVector) {
		_originalVector = Factory.createVector(originalVector);
	}

	/**
	 * Returns core permutation
	 * 
	 * @see org.paukov.combinatorics.Generator#getOriginalVector()
	 */
	public ICombinatoricsVector<T> getOriginalVector() {
		return _originalVector;
	}

	/**
	 * Returns the number of all generated permutations
	 * 
	 * @see org.paukov.combinatorics.Generator#getNumberOfGeneratedObjects()
	 */
	public long getNumberOfGeneratedObjects() {
		if (_originalVector.getSize() == 0)
			return 0;
		return Util.factorial(_originalVector.getSize());
	}

	/**
	 * Creates an iterator
	 * 
	 * @see org.paukov.combinatorics.Generator#iterator()
	 */
	@Override
	public Iterator<ICombinatoricsVector<T>> iterator() {
		return new PermutationIterator<T>(this);
	}

}
