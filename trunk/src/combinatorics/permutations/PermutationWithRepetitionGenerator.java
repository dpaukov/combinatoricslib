package combinatorics.permutations;

import combinatorics.CombinatoricsVector;
import combinatorics.Generator;
import combinatorics.Iterator;

/**
 * This generator generates permutation with repetitions from specified vector by specified length
 * 
 * @param <T> Type of elements in permutation
 */
public class PermutationWithRepetitionGenerator<T> extends Generator<T> {

  /**
   * Core vector which is used as the base for generator
   */
  protected final CombinatoricsVector<T> _coreVector;

  /**
   * Number of elements in the generated permutation
   */
  protected final int                    _permutationLength;

  /**
   * Constructor
   * 
   * @param coreVector core vector which is used as the base for generator
   * @param permutationLength Number of elements in the generated permutation
   */
  public PermutationWithRepetitionGenerator(CombinatoricsVector<T> coreVector, int permutationLength) {
    _coreVector = new CombinatoricsVector<T>(coreVector);
    _permutationLength = permutationLength;
  }

  /**
   * Creates iterator
   */
  @Override
  public Iterator<CombinatoricsVector<T>> createIterator() {
    return new PermutationWithRepetitionIterator<T>(this);
  }

  /**
   * Returns core vector
   */
  @Override
  public CombinatoricsVector<T> getCoreObject() {
    return _coreVector;
  }

  /**
   * Returns number of generated permutations
   */
  @Override
  public long getNumberOfGeneratedObjects() {
    return (long)Math.pow(_coreVector.getSize(), _permutationLength);
  }

  /**
   * Returns number of elements in the generated permutations
   */
  public int getPermutationLength() {
    return _permutationLength;
  }
}
