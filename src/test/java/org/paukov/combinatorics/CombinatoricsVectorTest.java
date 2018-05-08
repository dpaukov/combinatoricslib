/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.paukov.combinatorics.Factory.createVector;

/**
 * @author Dmytro Paukov
 */
public class CombinatoricsVectorTest {

    @Test
    public void testEqualVectors() {

        // create a combinatorics vector1 (a, b, c)
        ICombinatoricsVector<String> vector1 = createVector("a", "b", "c");

        assertEquals(3, vector1.getSize());
        assertEquals(false, vector1.hasDuplicates());
        assertEquals(false, vector1.isAllElementsEqual());

        List<String> array2 = new ArrayList<String>();
        array2.add("a");
        array2.add("b");
        array2.add("c");

        // create a combinatorics vector2
        ICombinatoricsVector<String> vector2 = createVector(array2);

        assertEquals(vector1, vector2);
    }

    @Test
    public void testNotEqualVectors() {

        // create an array1
        List<String> array1 = new ArrayList<String>();
        array1.add("a");
        array1.add("c");
        array1.add("b");

        // create a combinatorics vector1
        ICombinatoricsVector<String> vector1 = createVector(array1);

        assertEquals(3, vector1.getSize());
        assertEquals(false, vector1.hasDuplicates());

        List<String> array2 = new ArrayList<String>();
        array2.add("a");
        array2.add("b");
        array2.add("c");

        // create a combinatorics vector2
        ICombinatoricsVector<String> vector2 = createVector(array2);

        assertEquals(3, vector2.getSize());

        assertFalse(array1.equals(array2));
        assertFalse(vector1.equals(vector2));
    }

    @Test
    public void testEmptyVectors() {

        // create a combinatorics vector1
        ICombinatoricsVector<String> vector1 = createVector();

        assertEquals(0, vector1.getSize());
        assertEquals(false, vector1.hasDuplicates());

        // create a combinatorics vector2
        ICombinatoricsVector<String> vector2 = createVector();

        assertEquals(0, vector2.getSize());

        assertTrue(vector1.equals(vector2));
    }

    @Test
    public void testDuplicatesVectors() {

        // create a combinatorics vector (a, b, b, c)
        ICombinatoricsVector<String> vector = createVector("a", "b", "b", "c");

        assertEquals(4, vector.getSize());
        assertEquals(true, vector.hasDuplicates());
        assertEquals(false, vector.isAllElementsEqual());
    }

    @Test
    public void testAllElementsEqualVectors() {

        // create a combinatorics vector (a, a, a, a)
        ICombinatoricsVector<String> vector = createVector("a", "a", "a", "a");

        assertEquals(4, vector.getSize());
        assertEquals(true, vector.hasDuplicates());
        assertEquals(true, vector.isAllElementsEqual());
    }

    @Test
    public void testComplexVectors1() {

        List<String> array3 = new ArrayList<String>();
        array3.add("a");
        array3.add("a");
        array3.add("a");

        List<String> array1 = new ArrayList<String>();
        array1.add("a");

        ICombinatoricsVector<String> vector1 = createVector(array1);

        assertEquals(1, vector1.getSize());
        assertEquals(false, vector1.hasDuplicates());

        ICombinatoricsVector<String> vector3 = createVector(array3);

        assertEquals(3, vector3.getSize());
        assertEquals(true, vector3.hasDuplicates());

        ICombinatoricsVector<ICombinatoricsVector<String>> vectorA = createVector();
        vectorA.addValue(vector1);
        vectorA.addValue(vector3);

        assertEquals(2, vectorA.getSize());
        assertEquals(false, vectorA.hasDuplicates());

        ICombinatoricsVector<ICombinatoricsVector<String>> vectorB = createVector();
        vectorB.addValue(vector3);
        vectorB.addValue(vector1);

        assertEquals(2, vectorB.getSize());
        assertEquals(false, vectorB.hasDuplicates());

        assertEquals(false, vectorA.equals(vectorB));

    }

    @Test
    public void testComplexVectors2() {

        List<String> array3 = new ArrayList<String>();
        array3.add("a");
        array3.add("a");
        array3.add("a");

        List<String> array1 = new ArrayList<String>();
        array1.add("a");

        List<String> array2 = new ArrayList<String>();
        array2.add("a");
        array2.add("a");

        ICombinatoricsVector<String> vector1 = createVector(array1);

        assertEquals(1, vector1.getSize());
        assertEquals(false, vector1.hasDuplicates());

        ICombinatoricsVector<String> vector3 = createVector(array3);

        assertEquals(3, vector3.getSize());
        assertEquals(true, vector3.hasDuplicates());
        assertEquals(true, vector3.isAllElementsEqual());

        ICombinatoricsVector<String> vector2 = createVector(array2);

        assertEquals(2, vector2.getSize());
        assertEquals(true, vector2.hasDuplicates());

        ICombinatoricsVector<ICombinatoricsVector<String>> vectorA = createVector();
        vectorA.addValue(vector1);
        vectorA.addValue(vector3);

        assertEquals(2, vectorA.getSize());
        assertEquals(false, vectorA.hasDuplicates());

        ICombinatoricsVector<ICombinatoricsVector<String>> vectorB = createVector();
        vectorB.addValue(vector2);
        vectorB.addValue(vector2);

        assertEquals(2, vectorB.getSize());
        assertEquals(true, vectorB.hasDuplicates());

        assertEquals(false, vectorA.equals(vectorB));

    }

}
