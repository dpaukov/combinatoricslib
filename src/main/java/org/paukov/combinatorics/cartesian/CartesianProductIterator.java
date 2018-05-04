package org.paukov.combinatorics.cartesian;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.Iterator;

/**
 * Iterator for the cartesian product generator
 *
 * @param <T> Type of the elements in the cartesian product
 * @author Julius Iglesia, Dmytro Paukov
 * @version 2.2
 * @see CartesianProductGenerator
 */
public class CartesianProductIterator<T> implements
        Iterator<ICombinatoricsVector<T>> {

    private ICombinatoricsVector<ICombinatoricsVector<T>> vector;

    private final int vectorSize;

    private int nextIndex;

    private final int[] indices;

    private ICombinatoricsVector<T> current;

    private int index = 0;

    private boolean hasEmptyList = false;

    CartesianProductIterator(CartesianProductGenerator<T> generator) {
        vector = generator.originalVector;
        vectorSize = generator.originalVector.getSize();

        // start from the last index
        nextIndex = vectorSize - 1;

        // for tracking the indices of the product
        indices = new int[this.vectorSize];

        // for the tracking the lengths of the lists
        for (int i = 0; i < vectorSize; i++) {
            hasEmptyList = hasEmptyList || vector.getValue(i).getSize() == 0;
        }
    }

    /**
     * Returns true if all cartesian products were iterated, otherwise false
     */
    @Override
    public boolean hasNext() {
        return !hasEmptyList && nextIndex >= 0;
    }

    /**
     * Moves to the next Cartesian product
     */
    @Override
    public ICombinatoricsVector<T> next() {
        if (index == 0) {
            return generateCartesianProduct();
        }

        if (nextIndex < 0) {
            throw new RuntimeException("No more cartesian product.");
        }

        // Move to the next element
        indices[nextIndex]++;

        for (int i = nextIndex + 1; i < vectorSize; i++) {
            indices[i] = 0;
        }

        return generateCartesianProduct();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "CartesianProductIterator=[#" + index + ", " + current + "]";
    }

    private ICombinatoricsVector<T> generateCartesianProduct() {
        current = Factory.createVector();
        for (int i = 0; i < vectorSize; i++) {
            current.addValue(vector.getValue(i).getValue(indices[i]));
        }

        // After generating the current, check if has still next cartesian product,
        // this will be used by #hasNext function
        checkIfHasNextCartesianProduct();
        index++;

        return current;
    }

    private void checkIfHasNextCartesianProduct() {
        // Check if has still cartesian product by finding an array that has more elements left
        nextIndex = vectorSize - 1;
        while (nextIndex >= 0 &&
                indices[nextIndex] + 1 >= vector.getValue(nextIndex).getSize()) {
            nextIndex--;
        }
    }
}
