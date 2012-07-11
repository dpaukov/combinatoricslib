package org.paukov.combinatorics.permutations;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates all permutations of specified core vector
 * <p>
 * A permutation is an ordering of a set in the context of all possible
 * orderings. For example, the set containing the first three digits, 123, has
 * six permutations: 123, 132, 213, 231, 312, and 321.
 * <p>
 * This is a example of permutation of 3 string items:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * // create array of initial items
 * ArrayList&lt;String&gt; array = new ArrayList&lt;String&gt;();
 * array.add(&quot;one&quot;);
 * array.add(&quot;two&quot;);
 * array.add(&quot;three&quot;);
 * 
 * // create combinatorics vector
 * CombinatoricsVector&lt;String&gt; initialVector = new CombinatoricsVector&lt;String&gt;(
 * 		array);
 * 
 * // create permutation generator
 * Generator&lt;String&gt; generator = new PermutationGenerator&lt;String&gt;(initialVector);
 * 
 * // print the number of generated permutations
 * System.out.println(&quot;Number of permutations is: &quot;
 * 		+ generator.getNumberOfGeneratedObjects());
 * 
 * // create iterator
 * Iterator&lt;CombinatoricsVector&lt;String&gt;&gt; itr = generator.createIterator();
 * 
 * // go through the iterator
 * while (itr.hasNext()) {
 * 	CombinatoricsVector&lt;String&gt; permutation = itr.next();
 * 	System.out.println(permutation);
 * }
 * </pre>
 * 
 * </blockquote>
 * <p>
 * And the result
 * <p>
 * <blockquote>
 * 
 * <pre>
 *    Number of permutations is: 6
 *    CombinatoricsVector=[[one, two, three]], size=3]
 *    CombinatoricsVector=[[one, three, two]], size=3]
 *    CombinatoricsVector=[[three, one, two]], size=3]
 *    CombinatoricsVector=[[three, two, one]], size=3]
 *    CombinatoricsVector=[[two, three, one]], size=3]
 *    CombinatoricsVector=[[two, one, three]], size=3]
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro.Paukov
 * @see CombinatoricsVector
 * @see PermutationIterator
 * @param <T>
 *            Type of elements in permutation
 */
public class PermutationGenerator<T> extends Generator<T> {

	/**
	 * Core vector
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
	 * @see org.paukov.combinatorics.Generator#createIterator()
	 */
	public Iterator<ICombinatoricsVector<T>> createIterator() {
		return new PermutationIterator<T>(this);
	}

}
