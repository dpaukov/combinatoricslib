/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Dmytro Paukov
 * 
 */
public class IntegerVectorTest {

	@Test
	public void testEqualVectors() {

		// create a combinatorics vector1 (a, b, c)
		IntegerVector vector1 = IntegerFactory.createIntegerVector(new int[] {
				1, 2, 3 });

		assertEquals(3, vector1.getSize());
		assertEquals(false, vector1.hasDuplicates());
		assertEquals(false, vector1.isAllElementsEqual());

		int[] array2 = new int[] { 1, 2, 3 };

		// create a combinatorics vector2
		IntegerVector vector2 = IntegerFactory.createIntegerVector(array2);

		assertEquals(vector1, vector2);
	}

	@Test
	public void testNotEqualVectors() {

		// create an array1
		int[] array1 = new int[] { 1, 3, 2 };

		// create a combinatorics vector1
		IntegerVector vector1 = IntegerFactory.createIntegerVector(array1);

		assertEquals(3, vector1.getSize());
		assertEquals(false, vector1.hasDuplicates());

		int[] array2 = new int[] { 1, 2, 3 };

		// create a combinatorics vector2
		IntegerVector vector2 = IntegerFactory.createIntegerVector(array2);

		assertEquals(3, vector2.getSize());

		assertFalse(array1.equals(array2));
		assertFalse(vector1.equals(vector2));
	}

	@Test
	public void testEmptyVectors() {

		// create a combinatorics vector1
		IntegerVector vector1 = IntegerFactory.createIntegerVector();

		assertEquals(0, vector1.getSize());
		assertEquals(false, vector1.hasDuplicates());

		// create a combinatorics vector2
		IntegerVector vector2 = IntegerFactory.createIntegerVector();
		assertEquals(0, vector2.getSize());

		assertTrue(vector1.equals(vector2));
	}

	@Test
	public void testDuplicatesVectors() {

		// create a combinatorics vector (a, b, b, c)
		IntegerVector vector = IntegerFactory.createIntegerVector(new int[] {
				1, 2, 2, 3 });

		assertEquals(4, vector.getSize());
		assertEquals(true, vector.hasDuplicates());
		assertEquals(false, vector.isAllElementsEqual());
	}

	@Test
	public void testAllElementsEqualVectors() {

		// create a combinatorics vector (a, a, a, a)
		IntegerVector vector = IntegerFactory.createIntegerVector(new int[] {
				1, 1, 1, 1 });

		assertEquals(4, vector.getSize());
		assertEquals(true, vector.hasDuplicates());
		assertEquals(true, vector.isAllElementsEqual());
	}

	@Test
	public void testComplexVectors1() {

		int[] array3 = new int[] { 1, 1, 1 };

		int[] array1 = new int[] { 1 };

		IntegerVector vector1 = IntegerFactory.createIntegerVector(array1);

		assertEquals(1, vector1.getSize());
		assertEquals(false, vector1.hasDuplicates());

		IntegerVector vector3 = IntegerFactory.createIntegerVector(array3);

		assertEquals(3, vector3.getSize());
		assertEquals(true, vector3.hasDuplicates());

		ICombinatoricsVector<IntegerVector> vectorA = Factory.createVector();
		vectorA.addValue(vector1);
		vectorA.addValue(vector3);

		assertEquals(2, vectorA.getSize());
		assertEquals(false, vectorA.hasDuplicates());

		ICombinatoricsVector<IntegerVector> vectorB = Factory.createVector();
		vectorB.addValue(vector3);
		vectorB.addValue(vector1);

		assertEquals(2, vectorB.getSize());
		assertEquals(false, vectorB.hasDuplicates());

		assertEquals(false, vectorA.equals(vectorB));

	}

	@Test
	public void testComplexVectors2() {

		int[] array3 = new int[] { 1, 1, 1 };
		int[] array2 = new int[] { 1, 1 };
		int[] array1 = new int[] { 1 };
		IntegerVector vector1 = IntegerFactory.createIntegerVector(array1);

		assertEquals(1, vector1.getSize());
		assertEquals(false, vector1.hasDuplicates());

		IntegerVector vector3 = IntegerFactory.createIntegerVector(array3);

		assertEquals(3, vector3.getSize());
		assertEquals(true, vector3.hasDuplicates());
		assertEquals(true, vector3.isAllElementsEqual());

		IntegerVector vector2 = IntegerFactory.createIntegerVector(array2);

		assertEquals(2, vector2.getSize());
		assertEquals(true, vector2.hasDuplicates());

		ICombinatoricsVector<IntegerVector> vectorA = Factory.createVector();
		vectorA.addValue(vector1);
		vectorA.addValue(vector3);

		assertEquals(2, vectorA.getSize());
		assertEquals(false, vectorA.hasDuplicates());

		ICombinatoricsVector<IntegerVector> vectorB = Factory.createVector();
		vectorB.addValue(vector2);
		vectorB.addValue(vector2);

		assertEquals(2, vectorB.getSize());
		assertEquals(true, vectorB.hasDuplicates());

		assertEquals(false, vectorA.equals(vectorB));

	}

}
