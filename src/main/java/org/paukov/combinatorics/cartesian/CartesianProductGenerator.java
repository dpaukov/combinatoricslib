package org.paukov.combinatorics.cartesian;

import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.Iterator;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * This generator generates Cartesian product from specified multiple lists. Set of lists is
 * specified in the constructor of generator to generate k-element Cartesian product, where k is the
 * size of the set of lists.
 *
 * A simple k-element Cartesian product of a finite sets S(1), S(2)...S(k) is a set of all ordered
 * pairs (x(1), x(2)...x(k), where x(1) ∈ S(1), x(2) ∈ S(2) ... x(k) ∈ S(k)
 *
 * Example. Generate 3-element Cartesian product from (1, 2, 3), (4, 5, 6), (7, 8, 9).
 *
 * <pre>
 * ICombinatoricsVector&lt;Integer&gt; set01 = createVector(new Integer[] { 1, 2, 3 });
 * ICombinatoricsVector&lt;Integer&gt; set02 = createVector(new Integer[] { 4, 5, 6 });
 * ICombinatoricsVector&lt;Integer&gt; set03 = createVector(new Integer[] { 7, 8, 9 });
 *
 * ICombinatoricsVector&lt;ICombinatoricsVector&lt;Integer&gt;&gt; initialVector = createVector();
 * initialVector.addValue(set01);
 * initialVector.addValue(set02);
 * initialVector.addValue(set03);
 *
 * Generator&lt;Integer&gt; cartesianProduct = new CartesianProductGenerator&lt;Integer&gt;(initialVector);
 *
 * </pre>
 *
 * @param <T> Type of elements in the Cartesian product
 * @author Julius Iglesia, Dmytro Paukov
 * @version 2.2
 * @see CartesianProductIterator
 */
public class CartesianProductGenerator<T> extends Generator<T> {

  final ICombinatoricsVector<ICombinatoricsVector<T>> originalVector;

  /**
   * Constructor
   *
   * @param originalVector Vector which is used for generating the Cartesian product
   */
  CartesianProductGenerator(ICombinatoricsVector<ICombinatoricsVector<T>> originalVector) {
    this.originalVector = createVector(originalVector);
  }


  @Override
  public ICombinatoricsVector<T> getOriginalVector() {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getNumberOfGeneratedObjects() {
    long number = originalVector.getSize() > 0 ? 1 : 0;
    for (ICombinatoricsVector<T> vector : originalVector) {
      number *= vector.getSize();
    }
    return number;
  }

  @Override
  public Iterator<ICombinatoricsVector<T>> iterator() {
    return new CartesianProductIterator<>(this);
  }
}
