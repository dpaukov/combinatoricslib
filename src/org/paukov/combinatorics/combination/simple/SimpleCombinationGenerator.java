package org.paukov.combinatorics.combination.simple;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates simple combinations from specified core set by
 * specified length. Core set and length are specified in the constructor of
 * generator
 * <p>
 * A simple k-combination of a finite set S is a subset of k distinct elements
 * of S. Specifying a subset does not arrange them in a particular order. As an
 * example, a poker hand can be described as a 5-combination of cards from a
 * 52-card deck: the 5 cards of the hand are all distinct, and the order of the
 * cards in the hand does not matter.
 * <p>
 * Example. Generate 3-combination of the set {red, black, white, green, blue}.
 * <p>
 * <blockquote>
 * 
 * <pre>
 * // create array of initial items
 * ArrayList&lt;String&gt; array = new ArrayList&lt;String&gt;();
 * array.add(&quot;red&quot;);
 * array.add(&quot;black&quot;);
 * array.add(&quot;white&quot;);
 * array.add(&quot;green&quot;);
 * array.add(&quot;blue&quot;);
 * 
 * // create combinatorics vector
 * CombinatoricsVector&lt;String&gt; initialVector = new CombinatoricsVector&lt;String&gt;(
 * 		array);
 * 
 * // create simple combination generator to generate 3-combination
 * Generator&lt;String&gt; gen = new SimpleCombinationGenerator&lt;String&gt;(initialVector, 3);
 * 
 * // create iterator
 * Iterator&lt;CombinatoricsVector&lt;String&gt;&gt; itr = gen.createIterator();
 * 
 * // print the number of combinations
 * System.out.println(&quot;Number of combinations is: &quot;
 * 		+ gen.getNumberOfGeneratedObjects());
 * 
 * // go through the iterator
 * while (itr.hasNext()) {
 * 	CombinatoricsVector&lt;String&gt; combination = itr.next();
 * 	System.out.println(combination);
 * }
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro Paukov
 * @see SimpleCombinationIterator
 * @param <T>
 *            Type of elements in the combination
 */
public class SimpleCombinationGenerator<T> extends Generator<T> {

	protected final CombinatoricsVector<T> _coreSet;
	protected final int _combinationLength;

	/**
	 * Constructor
	 * 
	 * @param coreSet
	 *            Core set which is used for combination generation
	 * @param combinationsLength
	 *            Length of combination to generate
	 */
	public SimpleCombinationGenerator(CombinatoricsVector<T> coreSet,
			int combinationsLength) {
		_coreSet = new CombinatoricsVector<T>(coreSet);
		_combinationLength = combinationsLength;
	}

	/**
	 * Returns the original vector/set
	 * 
	 * @return Returns the _originalVector.
	 */
	public CombinatoricsVector<T> getCoreObject() {
		return _coreSet;
	}

	/**
	 * Returns the length of the combinations
	 * 
	 * @return Returns the _combinationLength.
	 */
	public int getCombinationLength() {
		return _combinationLength;
	}

	/**
	 * Returns the number of the generated combinations
	 */
	public long getNumberOfGeneratedObjects() {
		return Util.combination(_coreSet.getSize(), _combinationLength);
	}

	/**
	 * Creates an iterator of the simple combinations (without repetitions)
	 */
	public Iterator<CombinatoricsVector<T>> createIterator() {
		return new SimpleCombinationIterator<T>(this);
	}
}
