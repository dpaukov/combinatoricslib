package combinatorics.subsets;

import combinatorics.CombinatoricsVector;
import combinatorics.Generator;
import combinatorics.Iterator;
import combinatorics.util.Util;

public class SubSetGenerator<T> extends Generator<T> {

  protected final CombinatoricsVector<T> _coreSet;

  public SubSetGenerator(CombinatoricsVector<T> coreSet) {
    _coreSet = new CombinatoricsVector<T>(coreSet);
  }

  public CombinatoricsVector<T> getCoreObject() {
    return _coreSet;
  }

  public long getNumberOfGeneratedObjects() {
    return Util.pow2(_coreSet.getSize());
  }

  public Iterator<CombinatoricsVector<T>> createIterator() {
    return new SubSetIterator<T>(this);
  }

}
