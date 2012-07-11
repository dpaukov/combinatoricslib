package org.paukov.combinatorics.partition;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;

/**
 * In number theory, a partition of a positive integer n is a way of writing n
 * as a sum of positive integers. Two sums that differ only in the order of
 * their summands are considered to be the same partition; if order matters then
 * the sum becomes a composition. A summand in a partition is also called a
 * part.
 * <p>
 * WARNING. Be careful because number of all partitions can be very high even
 * for not great given N.
 * <p>
 * The partitions of 5 are listed below:
 * <ol>
 * <li>1 + 1 + 1 + 1 + 1
 * <li>2 + 1 + 1 + 1
 * <li>2 + 2 + 1
 * <li>3 + 1 + 1
 * <li>3 + 2
 * <li>4 + 1
 * <li>5
 * </ol>
 * <p>
 * The number of partitions of n is given by the partition function p(n). In
 * number theory, the partition function p(n) represents the number of possible
 * partitions of a natural number n, which is to say the number of distinct (and
 * order independent) ways of representing n as a sum of natural numbers.
 * <p>
 * This is a code:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * // create partition generator of 5
 * Generator&lt;Integer&gt; gen = new PartitionGenerator(5);
 * 
 * // create iterator
 * Iterator&lt;CombinatoricsVector&lt;Integer&gt;&gt; itr = gen.createIterator();
 * 
 * // go through the iterator
 * while (itr.hasNext()) {
 * 	CombinatoricsVector&lt;Integer&gt; partition = itr.next();
 * 	System.out.println(partition);
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
 *    PartitionIterator=[#1, CombinatoricsVector=[[1, 1, 1, 1, 1]], size=5]]
 *    PartitionIterator=[#2, CombinatoricsVector=[[2, 1, 1, 1]], size=4]]
 *    PartitionIterator=[#3, CombinatoricsVector=[[2, 2, 1]], size=3]]
 *    PartitionIterator=[#4, CombinatoricsVector=[[3, 1, 1]], size=3]]
 *    PartitionIterator=[#5, CombinatoricsVector=[[3, 2]], size=2]]
 *    PartitionIterator=[#6, CombinatoricsVector=[[4, 1]], size=2]]
 *    PartitionIterator=[#7, CombinatoricsVector=[[5]], size=1]]
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * @author Dmytro.Paukov
 * @see ICombinatoricsVector
 * @see PartitionIterator
 * 
 */
public class PartitionGenerator extends Generator<Integer> {

	public static final int MAXN = 100;

	protected final Integer _initialValue;

	/**
	 * Constructor
	 * 
	 * @param n
	 *            An integer value to generate partitions
	 */
	public PartitionGenerator(Integer n) {
		_initialValue = n;
	}

	/**
	 * Creates iterator to enumerate all partitions
	 */
	@Override
	public Iterator<ICombinatoricsVector<Integer>> createIterator() {
		return new PartitionIterator(this);
	}

	/**
	 * Returns value which is used to generate all partitions. This value
	 * returned as a element of vector. Vector has length of 1
	 */
	@Override
	public ICombinatoricsVector<Integer> getOriginalVector() {
		return Factory.createVector(new Integer[] { _initialValue });
	}

	/**
	 * Returns approximated number of partitions for given positive value. Exact
	 * value of number of partitions can be obtained from actual generated list
	 * of partitions. WARNING. Be careful because number of all partitions can
	 * be very high even for not great given N.
	 */
	@Override
	public long getNumberOfGeneratedObjects() {

		if (_initialValue == 0)
			return 0;

		if (_initialValue > 0 && _initialValue <= MAXN) {
			double result = 2.0 * _initialValue / 3.0;
			result = Math.exp(Math.PI * Math.sqrt(result));
			result /= 4.0 * _initialValue * Math.sqrt(3);
			return (long) result;
		}
		
		throw new RuntimeException(
				"Unable to calculate the number of the pertitions for the value N="
						+ _initialValue);
	}

}
