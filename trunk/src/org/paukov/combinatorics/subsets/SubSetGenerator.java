package org.paukov.combinatorics.subsets;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates all subsets of the specified set (core set)
 * <p>
 * A set A is a subset of a set B if A is "contained" inside B. A and B may
 * coincide. The relationship of one set being a subset of another is called
 * inclusion or sometimes containment.
 * <p>
 * Examples:
 * <ul>
 * <li>The set {1, 2} is a proper subset of {1, 2, 3}.
 * <li>Any set is a subset of itself, but not a proper subset.
 * <li>The empty set, denoted by {}, is also a subset of any given set X.
 * </ul>
 * <p>
 * All subsets of {1, 2, 3} are:
 * <ol>
 * <li>{}
 * <li>{1}
 * <li>{2}
 * <li>{1, 2}
 * <li>{3}
 * <li>{1, 3}
 * <li>{2, 3}
 * <li>{1, 2, 3}
 * </ol>
 * <p>
 * And code which generates all subsets of {one, two, three}
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
 * // create subset generator
 * Generator&lt;String&gt; gen = new SubSetGenerator&lt;String&gt;(initialVector);
 * 
 * // create iterator
 * Iterator&lt;CombinatoricsVector&lt;String&gt;&gt; itr = gen.createIterator();
 * // print the number of subsets
 * System.out
 * 		.println(&quot;Number of subsets is: &quot; + gen.getNumberOfGeneratedObjects());
 * 
 * // go through the iterator
 * while (itr.hasNext()) {
 * 	CombinatoricsVector&lt;String&gt; subSet = itr.next();
 * 	System.out.println(subSet);
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
 *    Number of subsets is: 8
 *    SubSetIterator=[#1, CombinatoricsVector=[[]], size=0]]
 *    SubSetIterator=[#2, CombinatoricsVector=[[one]], size=1]]
 *    SubSetIterator=[#3, CombinatoricsVector=[[two]], size=1]]
 *    SubSetIterator=[#4, CombinatoricsVector=[[one, two]], size=2]]
 *    SubSetIterator=[#5, CombinatoricsVector=[[three]], size=1]]
 *    SubSetIterator=[#6, CombinatoricsVector=[[one, three]], size=2]]
 *    SubSetIterator=[#7, CombinatoricsVector=[[two, three]], size=2]]
 *    SubSetIterator=[#8, CombinatoricsVector=[[one, two, three]], size=3]]
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro.Paukov
 * @see CombinatoricsVector
 * @see SubSetIterator
 * @param <T>
 *            Type of elements in the set
 */
public class SubSetGenerator<T> extends Generator<T> {

	/**
	 * Core set
	 */
	protected final CombinatoricsVector<T> _coreSet;

	/**
	 * Constructor
	 * 
	 * @param coreSet
	 *            Core set
	 */
	public SubSetGenerator(CombinatoricsVector<T> coreSet) {
		_coreSet = new CombinatoricsVector<T>(coreSet);
	}

	/**
	 * Returns the core set
	 */
	public CombinatoricsVector<T> getCoreObject() {
		return _coreSet;
	}

	/**
	 * Returns the number of the subsets
	 */
	public long getNumberOfGeneratedObjects() {
		return Util.pow2(_coreSet.getSize());
	}

	/**
	 * Creates the iterator over the all subsets
	 */
	public Iterator<CombinatoricsVector<T>> createIterator() {
		return new SubSetIterator<T>(this);
	}

}
