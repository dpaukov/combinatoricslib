/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.permutations;

import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.Iterator;
import org.paukov.combinatorics.CombinatoricsFactory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * This generator generates permutations with repetitions from the specified vector by specified
 * length
 *
 * The permutation may have more elements than slots. For example, the three possible
 * permutation of 12 in three slots are: 111, 211, 121, 221, 112, 212, 122, and 222.
 *
 * Let's generate all possible permutations with repetitions of 3 elements from the set of apple and
 * orange:
 *
 * <pre>
 *
 * // Create the initial vector of 2 elements (apple, orange)
 * ICombinatoricsVector&lt;String&gt; originalVector = CombinatoricsFactory.createVector(new String[] {
 * &quot;apple&quot;, &quot;orange&quot; });
 *
 * // Create the generator by calling the appropriate method in the Factory class.
 * // Set the second parameter as 3, since we will generate 3-elemets permutations
 * Generator&lt;String&gt; gen = CombinatoricsFactory.createPermutationWithRepetitionGenerator(originalVector,
 * 3);
 *
 * // Print the result
 * for (ICombinatoricsVector&lt;String&gt; perm : gen)
 *    System.out.println( perm );
 * </pre>
 *
 * And the result
 *
 * <pre>
 *   CombinatoricsVector=([apple, apple, apple], size=3)
 *   CombinatoricsVector=([orange, apple, apple], size=3)
 *   CombinatoricsVector=([apple, orange, apple], size=3)
 *   CombinatoricsVector=([orange, orange, apple], size=3)
 *   CombinatoricsVector=([apple, apple, orange], size=3)
 *   CombinatoricsVector=([orange, apple, orange], size=3)
 *   CombinatoricsVector=([apple, orange, orange], size=3)
 *   CombinatoricsVector=([orange, orange, orange], size=3)
 * </pre>
 *
 * @param <T> Type of the elements in the permutations
 * @author Dmytro Paukov
 * @version 2.0
 * @see ICombinatoricsVector
 * @see PermutationWithRepetitionIterator
 * @see CombinatoricsFactory
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
   * @param originalVector core vector which is used as the base for generator
   * @param permutationLength Number of elements in the generated permutation
   */
  public PermutationWithRepetitionGenerator(
      ICombinatoricsVector<T> originalVector, int permutationLength) {
    _originalVector = createVector(originalVector);
    _permutationLength = permutationLength;
  }

  /**
   * Creates an iterator
   *
   * @see org.paukov.combinatorics.Generator#iterator()
   */
  @Override
  public Iterator<ICombinatoricsVector<T>> iterator() {
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
   * Returns the number of elements in the generated permutations
   */
  public int getPermutationLength() {
    return _permutationLength;
  }
}
