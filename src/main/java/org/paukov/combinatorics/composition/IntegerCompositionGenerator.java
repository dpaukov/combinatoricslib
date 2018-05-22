/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.composition;

import static org.paukov.combinatorics.IntegerFactory.createIntegerVector;

import java.util.Iterator;
import org.paukov.combinatorics.IntegerGenerator;
import org.paukov.combinatorics.IntegerVector;
import org.paukov.combinatorics.util.Util;

/**
 * A composition of an integer n is a way of writing n as the sum of a sequence of (strictly)
 * positive integers. This class generates the composition if a positive integer value.
 *
 * A composition of an integer n is a way of writing n as the sum of a sequence of (strictly)
 * positive integers. Two sequences that differ in the order of their terms define different
 * compositions of their sum, while they are considered to define the same partition of that
 * number.
 *
 * The sixteen compositions of 5 are: 5, 4+1, 3+2, 3+1+1, 2+3, 2+2+1, 2+1+2, 2+1+1+1, 1+4, 1+3+1,
 * 1+2+2, 1+2+1+1, 1+1+3, 1+1+2+1, 1+1+1+2, 1+1+1+1+1.
 *
 * Compare this with the seven partitions of 5: 5, 4+1, 3+2, 3+1+1, 2+2+1, 2+1+1+1, 1+1+1+1+1.
 *
 * Example. Generate compositions all possible integer compositions of 5.
 *
 * <pre>
 *   // Create an instance of the integer composition generator to generate all possible
 * compositions of 5
 *   IntegerGenerator gen = IntegerFactory.createIntegerCompositionGenerator(5);
 *
 *   // Print the compositions
 *   for (IntegerVector p : gen) {
 *      System.out.println(p);
 *   }
 * </pre>
 *
 * And the result
 *
 * <pre>
 *    IntegerVector=([5], size=1)
 *    IntegerVector=([1, 4], size=2)
 *    IntegerVector=([2, 3], size=2)
 *    IntegerVector=([1, 1, 3], size=3)
 *    IntegerVector=([3, 2], size=2)
 *    IntegerVector=([1, 2, 2], size=3)
 *    IntegerVector=([2, 1, 2], size=3)
 *    IntegerVector=([1, 1, 1, 2], size=4)
 *    IntegerVector=([4, 1], size=2)
 *    IntegerVector=([1, 3, 1], size=3)
 *    IntegerVector=([2, 2, 1], size=3)
 *    IntegerVector=([1, 1, 2, 1], size=4)
 *    IntegerVector=([3, 1, 1], size=3)
 *    IntegerVector=([1, 2, 1, 1], size=4)
 *    IntegerVector=([2, 1, 1, 1], size=4)
 *    IntegerVector=([1, 1, 1, 1, 1], size=5)
 * </pre>
 *
 * @author Dmytro Paukov
 * @version 2.0
 * @see IntegerCompositionIterator
 */
public class IntegerCompositionGenerator extends IntegerGenerator {

  public static final int MAXN = 100;

  protected final Integer _initialValue;

  /**
   * Constructor
   *
   * @param n A positive integer value
   */
  public IntegerCompositionGenerator(Integer n) {
    super();
    this._initialValue = n;
  }

  /**
   * Returns the value which is used for generating the compositions. This value is
   * returned as an element of the single value vector.
   */
  @Override
  public IntegerVector getOriginalVector() {
    return createIntegerVector(new int[]{_initialValue});
  }

  /**
   * Returns number of the compositions
   */
  @Override
  public long getNumberOfGeneratedObjects() {
    return Util.pow2(_initialValue - 1);
  }

  /**
   * Creates the iterator over all compositions
   */
  @Override
  public Iterator<IntegerVector> iterator() {
    return new IntegerCompositionIterator(this);
  }

}
