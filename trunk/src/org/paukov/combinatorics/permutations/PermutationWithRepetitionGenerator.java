package org.paukov.combinatorics.permutations;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;

/**
 * This generator generates permutation with repetitions from specified vector
 * by specified length
 * <p>
 * The permutation may have more elements than slots. For example, the three
 * possible permutation of 12 in three slots are: 111, 211, 121, 221, 112, 212,
 * 122, and 222.
 * <p>
 * This is a code:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * // create array of initial items
 * ArrayList&lt;String&gt; array = new ArrayList&lt;String&gt;();
 * array.add(&quot;one&quot;);
 * array.add(&quot;two&quot;);
 * 
 * // create combinatorics vector
 * CombinatoricsVector&lt;String&gt; initialVector = new CombinatoricsVector&lt;String&gt;(
 * 		array);
 * 
 * // create permutation with repetition generator, second parameter is a number of
 * // slots
 * Generator&lt;String&gt; gen = new PermutationWithRepetitionGenerator&lt;String&gt;(
 * 		initialVector, 3);
 * 
 * // create iterator
 * Iterator&lt;CombinatoricsVector&lt;String&gt;&gt; itr = gen.createIterator();
 * 
 * // print the number of generated permutations
 * System.out.println(&quot;Number of permutationWithRepetition is: &quot;
 * 		+ gen.getNumberOfGeneratedObjects());
 * 
 * // go through the iterator
 * while (itr.hasNext()) {
 * 	CombinatoricsVector&lt;String&gt; permutation = itr.next();
 * 	System.out.println(itr);
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
 *    Number of permutationWithRepetition is: 8
 *    PermutationWithRepetitionIterator=[#1, CombinatoricsVector=[[one, one, one]], size=3]]
 *    PermutationWithRepetitionIterator=[#2, CombinatoricsVector=[[two, one, one]], size=3]]
 *    PermutationWithRepetitionIterator=[#3, CombinatoricsVector=[[one, two, one]], size=3]]
 *    PermutationWithRepetitionIterator=[#4, CombinatoricsVector=[[two, two, one]], size=3]]
 *    PermutationWithRepetitionIterator=[#5, CombinatoricsVector=[[one, one, two]], size=3]]
 *    PermutationWithRepetitionIterator=[#6, CombinatoricsVector=[[two, one, two]], size=3]]
 *    PermutationWithRepetitionIterator=[#7, CombinatoricsVector=[[one, two, two]], size=3]]
 *    PermutationWithRepetitionIterator=[#8, CombinatoricsVector=[[two, two, two]], size=3]]
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro.Paukov
 * @see CombinatoricsVector
 * @see PermutationWithRepetitionIterator
 * 
 * @param <T>
 *            Type of elements in the permutations
 */
public class PermutationWithRepetitionGenerator<T> extends Generator<T> {

	/**
	 * Original vector which is used as the base for the permutations
	 */
	protected final ICombinatoricsVector<T> _originalVector;

	/**
	 * Number of elements in the generated permutation
	 */
	protected final int _permutationLength;

	/**
	 * Constructor
	 * 
	 * @param originalVector
	 *            core vector which is used as the base for generator
	 * @param permutationLength
	 *            Number of elements in the generated permutation
	 */
	public PermutationWithRepetitionGenerator(
			ICombinatoricsVector<T> originalVector, int permutationLength) {
		_originalVector = Factory.createVector(originalVector);
		_permutationLength = permutationLength;
	}

	/**
	 * Creates iterator
	 */
	@Override
	public Iterator<ICombinatoricsVector<T>> createIterator() {
		return new PermutationWithRepetitionIterator<T>(this);
	}

	/**
	 * Returns core vector
	 */
	@Override
	public ICombinatoricsVector<T> getOriginalVector() {
		return _originalVector;
	}

	/**
	 * Returns number of generated permutations
	 */
	@Override
	public long getNumberOfGeneratedObjects() {
		return (long) Math.pow(_originalVector.getSize(), _permutationLength);
	}

	/**
	 * Returns number of elements in the generated permutations
	 */
	public int getPermutationLength() {
		return _permutationLength;
	}
}
