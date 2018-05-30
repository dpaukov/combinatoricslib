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
 * ICombinatoricsVector&lt;Integer&gt; set01 = createVector(1, 2, 3);
 * ICombinatoricsVector&lt;Integer&gt; set02 = createVector(4, 5, 6);
 * ICombinatoricsVector&lt;Integer&gt; set03 = createVector(7, 8, 9);
 *
 *  Generator&lt;Integer&gt; cartesianProduct = createCartesianProductGenerator(set01, set02, set03);
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
   * @param originalVector Vector which is used for generating the Cartesian product.
   */
  public CartesianProductGenerator(ICombinatoricsVector<ICombinatoricsVector<T>> originalVector) {
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
