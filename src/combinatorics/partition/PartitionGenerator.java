package combinatorics.partition;

import combinatorics.CombinatoricsVector;
import combinatorics.Generator;
import combinatorics.Iterator;

/**
 * In number theory, a partition of a positive integer n is a way of writing n as a sum of positive integers. Two sums
 * that differ only in the order of their summands are considered to be the same partition WARNING. Be careful because
 * number of all partitions can be very high even for not great given N.
 */
public class PartitionGenerator extends Generator<Integer> {

  public static final int MAXN = 100;

  protected final Integer _coreValue;

  /**
   * Constructor
   * 
   * @param n Value to generate partitions
   */
  public PartitionGenerator(Integer n) {
    _coreValue = n;
  }

  /**
   * Creates iterator to enumerate all partitions
   */
  @Override
  public Iterator<CombinatoricsVector<Integer>> createIterator() {
    return new PartitionIterator(this);
  }

  /**
   * Returns value which is used to generate all partitions. This value returned as a element of vector. Vector has
   * length of 1
   */
  @Override
  public CombinatoricsVector<Integer> getCoreObject() {
    return new CombinatoricsVector<Integer>(1, _coreValue);
  }

  /**
   * Returns approximated number of partitions for given positive value. Exact value of number of partitions can be
   * obtained from actual generated list of partitions. WARNING. Be careful because number of all partitions can be very
   * high even for not great given N.
   */
  @Override
  public long getNumberOfGeneratedObjects() {
    double result = 2.0 * _coreValue / 3.0;
    result = Math.exp(Math.PI * Math.sqrt(result));
    result /= 4.0 * _coreValue * Math.sqrt(3);
    return (long)result;
  }

}
