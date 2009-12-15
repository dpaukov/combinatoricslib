package combinatorics.combination.simple;

import combinatorics.CombinatoricsVector;
import combinatorics.Generator;
import combinatorics.Iterator;
import combinatorics.util.Util;

/**
 * This generator generates simple combinations from specified core set by specified length. Core set and length are
 * specified in the constructor of generator
 * 
 * @param <T> Type of elements in the combination
 */
public class SimpleCombinationGenerator<T> extends Generator<T> {

  protected final CombinatoricsVector<T> _coreSet;
  protected final int                    _combinationLength;

  /**
   * Constructor
   * 
   * @param coreSet Core set which is used for combination generation
   * @param combinationsLength Length of combination to generate
   */
  public SimpleCombinationGenerator(CombinatoricsVector<T> coreSet, int combinationsLength) {
    _coreSet = new CombinatoricsVector<T>(coreSet);
    _combinationLength = combinationsLength;
  }

  /**
   * Returns core set
   * 
   * @return Returns the _coreSet.
   */
  public CombinatoricsVector<T> getCoreObject() {
    return _coreSet;
  }

  /**
   * Returns length of combinations
   * 
   * @return Returns the _combinationLength.
   */
  public int getCombinationLength() {
    return _combinationLength;
  }

  /**
   * Returns number of generated combinations
   */
  public long getNumberOfGeneratedObjects() {
    return Util.combination(_coreSet.getSize(), _combinationLength);
  }

  /**
   * Creates iterator of simple combinations
   */
  public Iterator<CombinatoricsVector<T>> createIterator() {
    return new SimpleCombinationIterator<T>(this);
  }
}
