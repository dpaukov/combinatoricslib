package combinatorics.subsets;

import combinatorics.CombinatoricsVector;
import combinatorics.Generator;
import combinatorics.Iterator;

public class SubSetIterator<T> implements Iterator<CombinatoricsVector<T>> {

  protected final Generator<T> _generator;
  protected CombinatoricsVector<T>   _currentSubSet = null;
  protected long                     _currentIndex = 0;
  protected final int                _length;

  private int[]                      _bitVector     = null;

  public SubSetIterator(Generator<T> generator) {
    _generator = generator;
    _length = generator.getCoreObject().getSize();
    _currentSubSet = new CombinatoricsVector<T>();
    _bitVector = new int[_length + 2];
  }

  /*
   * (non-Javadoc)
   * @see combinatorics.iterator.Iterator#first()
   */
  @Override
  public void first() {
    _currentIndex = 0;
    for (int i = 0; i <= _length + 1; i++) {
      _bitVector[i] = 0;
    }

  }

  /*
   * (non-Javadoc)
   * @see combinatorics.iterator.Iterator#getCurrentItem()
   */
  @Override
  public CombinatoricsVector<T> getCurrentItem() {
    return _currentSubSet;
  }

  /*
   * (non-Javadoc)
   * @see combinatorics.iterator.Iterator#isDone()
   */
  @Override
  public boolean isDone() {
    return _bitVector[_length + 1] == 1;
  }

  /*
   * (non-Javadoc)
   * @see combinatorics.iterator.Iterator#next()
   */
  @Override
  public void next() {
    _currentIndex++;
    _currentSubSet.getVector().clear();
    for (int index = 1; index <= _length; index++) {
      if (_bitVector[index] == 1) {
        T value = _generator.getCoreObject().getValue(index - 1);
        _currentSubSet.getVector().add(value);
      }
    }
    int i = 1;
    while (_bitVector[i] == 1) {
      _bitVector[i] = 0;
      i++;
    }
    _bitVector[i] = 1;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SubSetIterator=[#" + _currentIndex + ", " + _currentSubSet + "]";
  }

}
