package org.paukov.combinatorics.subsets;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.util.Util;

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
