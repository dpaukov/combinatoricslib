package org.paukov.combinatorics.subsets;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.util.Util;

/**
 * This generator generates all subsets of the specified set (core set)
 * 
 * @param <T>
 *            Type of elements in the set
 */
public class SubSetGenerator<T> extends Generator<T> {

    /**
     * Core set
     */
    protected final CombinatoricsVector<T> _coreSet;

    /**
     * Constructor
     * 
     * @param coreSet
     *            Core set
     */
    public SubSetGenerator(CombinatoricsVector<T> coreSet) {
	_coreSet = new CombinatoricsVector<T>(coreSet);
    }

    /**
     * Returns the core set
     */
    public CombinatoricsVector<T> getCoreObject() {
	return _coreSet;
    }

    /**
     * Returns the number of the subsets
     */
    public long getNumberOfGeneratedObjects() {
	return Util.pow2(_coreSet.getSize());
    }

    /**
     * Creates the iterator over the all subsets
     */
    public Iterator<CombinatoricsVector<T>> createIterator() {
	return new SubSetIterator<T>(this);
    }

}
