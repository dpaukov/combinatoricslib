/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 * Copyright 2016 Pieter Pareit pieter.pareit@gmail.com
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package be.ppareit.libcombinatorics;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.permutations.DuplicatedPermutationIterator;
import org.paukov.combinatorics.util.Util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This generator generates all possible anagrams of the specified initial
 * vector
 * <p>
 * An anagram is an ordering of a multiset in the context of all possible
 * orderings using all the original elements from the multiset exactly once.
 * For example, the set containing, aabc, has 12 anagrams:
 * aabc, aacb, abac, abca, acab, acba, baac, baca, bcaa, caab, caba, cbaa
 * <p>
 * 
 * @author Pieter Pareit
 * @version 1.0
 * @see ICombinatoricsVector
 * @see PermutationGenerator
 * @see DuplicatedPermutationIterator
 * @see Factory
 * @param <T>
 *            Type of the elements in the anagram
 */
public class AnagramGenerator<T> extends Generator<T> {

    /**
     * Initial vector
     */
    protected final ICombinatoricsVector<T> _originalVector;

    private Map<T, Integer> _frequenties;

    /**
     * Constructor
     *
     * @param originalVector Vector which is used for anagram generation
     */
    public AnagramGenerator(ICombinatoricsVector<T> originalVector) {
        _originalVector = Factory.createVector(originalVector);
    }

    /**
     * Returns core anagram
     *
     * @see org.paukov.combinatorics.Generator#getOriginalVector()
     */
    public ICombinatoricsVector<T> getOriginalVector() {
        return _originalVector;
    }

    /**
     * Returns the number of all generated anagrams
     *
     * @see org.paukov.combinatorics.Generator#getNumberOfGeneratedObjects()
     */
    public long getNumberOfGeneratedObjects() {
        if (_originalVector.getSize() == 0)
            return 0;

        if (_frequenties == null) {
            _frequenties = new HashMap<>();
            for (T element : _originalVector) {
                if (_frequenties.containsKey(element)) {
                    _frequenties.put(element, _frequenties.get(element) + 1);
                } else {
                    _frequenties.put(element, 1);
                }
            }
        }

        long count = Util.factorial(_originalVector.getSize());
        for (int freq : _frequenties.values()) {
            count /= Util.factorial(freq);
        }

        return Util.factorial(_originalVector.getSize());
    }

    /**
     * Creates an iterator
     *
     * @see org.paukov.combinatorics.Generator#iterator()
     */
    @Override
    public Iterator<ICombinatoricsVector<T>> iterator() {
        return new DuplicatedPermutationIterator<T>(this);
    }

}
