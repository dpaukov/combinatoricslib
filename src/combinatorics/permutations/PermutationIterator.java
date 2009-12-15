package combinatorics.permutations;

import combinatorics.CombinatoricsVector;
import combinatorics.Generator;
import combinatorics.Iterator;

/**
 * Iterator of permutation generator
 * 
 * @param <T> Type of elements of permutations
 */
public class PermutationIterator<T> implements Iterator<CombinatoricsVector<T>> {

  /**
   * Generator
   */
  protected final Generator<T>     _generator;

  /**
   * Current permutation
   */
  protected CombinatoricsVector<T> _currentPermutation;

  /**
   * Current index of current permutation
   */
  protected long                   _currentIndex = 0;

  /**
   * Number of elements in the permutations
   */
  protected final int              _length;

  /**
   * Internal data
   */
  private int[]                    _pZ           = null;
  private int[]                    _pP           = null;
  private int[]                    _pD           = null;
  private int                      m             = 0;
  private int                      w             = 0;
  private int                      pm            = 0;
  private int                      dm            = 0;
  private int                      zpm           = 0;

  /**
   * Constructor
   * 
   * @param generator Permutation generator
   */
  public PermutationIterator(Generator<T> generator) {
    _generator = generator;
    _length = generator.getCoreObject().getSize();
    _currentPermutation = new CombinatoricsVector<T>(generator.getCoreObject());
    _pZ = new int[_length + 2];
    _pP = new int[_length + 2];
    _pD = new int[_length + 2];
  }

  /**
   * Initialize the iteration process
   */
  @Override
  public void first() {
    _currentIndex = 0;

    m = 0;
    w = 0;
    pm = 0;
    dm = 0;
    zpm = 0;

    for (int i = 1; i <= _length; i++) {
      _pP[i] = i;
      _pZ[i] = i;
      _pD[i] = -1;
    }
    _pD[1] = 0;
    _pZ[_length + 1] = m = _length + 1;
    _pZ[0] = _pZ[_length + 1];

  }

  /**
   * Returns current permutation
   */
  @Override
  public CombinatoricsVector<T> getCurrentItem() {
    return _currentPermutation;
  }

  /**
   * Return true if iteration process is finished
   */
  @Override
  public boolean isDone() {
    return m == 1;
  }

  /**
   * Moves to the next permutation
   */
  @Override
  public void next() {

    for (int i = 1; i <= _length; i++) {
      int index = _pZ[i] - 1;
      _currentPermutation.setValue(i - 1, _generator.getCoreObject().getValue(index));
    }
    m = _length;
    while (_pZ[_pP[m] + _pD[m]] > m) {
      _pD[m] = -_pD[m];
      m--;
    }
    pm = _pP[m];
    dm = pm + _pD[m];
    w = _pZ[pm];
    _pZ[pm] = _pZ[dm];
    _pZ[dm] = w;
    zpm = _pZ[pm];
    w = _pP[zpm];
    _pP[zpm] = pm;
    _pP[m] = w;
    _currentIndex++;
  }

  /**
   * Returns permutation as a string
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "PermutationIterator=[#" + _currentIndex + ", " + _currentPermutation + "]";
  }

}
