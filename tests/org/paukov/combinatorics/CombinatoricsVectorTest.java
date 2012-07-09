package org.paukov.combinatorics;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CombinatoricsVectorTest {

	@Test
	public void testEqualVectors() {

		// create an array1
		List<String> array1 = new ArrayList<String>();
		array1.add("a");
		array1.add("b");
		array1.add("c");

		// create a combinatorics vector1
		CombinatoricsVector<String> vector1 = new CombinatoricsVector<String>(
				array1);

		assertEquals(3, vector1.getSize());
		assertEquals(false, vector1.hasDuplicates());

		List<String> array2 = new ArrayList<String>();
		array2.add("a");
		array2.add("b");
		array2.add("c");

		// create a combinatorics vector2
		CombinatoricsVector<String> vector2 = new CombinatoricsVector<String>(
				array2);

		assertEquals(array1, array2);
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
		CombinatoricsVector<String> vector1 = new CombinatoricsVector<String>(
				array1);

		assertEquals(3, vector1.getSize());
		assertEquals(false, vector1.hasDuplicates());

		List<String> array2 = new ArrayList<String>();
		array2.add("a");
		array2.add("b");
		array2.add("c");

		// create a combinatorics vector2
		CombinatoricsVector<String> vector2 = new CombinatoricsVector<String>(
				array2);

		assertEquals(3, vector2.getSize());

		assertFalse(array1.equals(array2));
		assertFalse(vector1.equals(vector2));
	}

	@Test
	public void testEmptyVectors() {

		// create a combinatorics vector1
		CombinatoricsVector<String> vector1 = new CombinatoricsVector<String>();

		assertEquals(0, vector1.getSize());
		assertEquals(false, vector1.hasDuplicates());

		// create a combinatorics vector2
		CombinatoricsVector<String> vector2 = new CombinatoricsVector<String>();

		assertEquals(0, vector2.getSize());

		assertTrue(vector1.equals(vector2));
	}

	@Test
	public void testDuplicatesVectors() {

		// create an array1
		List<String> array1 = new ArrayList<String>();
		array1.add("a");
		array1.add("b");
		array1.add("b");
		array1.add("c");

		// create a combinatorics vector1
		CombinatoricsVector<String> vector1 = new CombinatoricsVector<String>(
				array1);

		assertEquals(4, vector1.getSize());
		assertEquals(true, vector1.hasDuplicates());
	}
	
	@Test
	public void testComplexVectors1() {

		List<String> array3 = new ArrayList<String>();
		array3.add("a");
		array3.add("a");
		array3.add("a");

		List<String> array1 = new ArrayList<String>();
		array1.add("a");

		
		CombinatoricsVector<String> vector1 = new CombinatoricsVector<String>(
				array1);
		
		assertEquals(1, vector1.getSize());
		assertEquals(false, vector1.hasDuplicates());

		CombinatoricsVector<String> vector3 = new CombinatoricsVector<String>(
				array3);
		
		assertEquals(3, vector3.getSize());
		assertEquals(true, vector3.hasDuplicates());
		
		CombinatoricsVector<CombinatoricsVector<String>> vectorA = new CombinatoricsVector<CombinatoricsVector<String>>();
		vectorA.addValue(vector1);
		vectorA.addValue(vector3);
		
		assertEquals(2, vectorA.getSize());
		assertEquals(false, vectorA.hasDuplicates());
		
		CombinatoricsVector<CombinatoricsVector<String>> vectorB = new CombinatoricsVector<CombinatoricsVector<String>>();
		vectorB.addValue(vector3);
		vectorB.addValue(vector1);

		assertEquals(2, vectorB.getSize());
		assertEquals(false, vectorB.hasDuplicates());
		
		assertEquals(false,vectorA.equals(vectorB));
		
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
		
		CombinatoricsVector<String> vector1 = new CombinatoricsVector<String>(
				array1);
		
		assertEquals(1, vector1.getSize());
		assertEquals(false, vector1.hasDuplicates());

		CombinatoricsVector<String> vector3 = new CombinatoricsVector<String>(
				array3);
		
		assertEquals(3, vector3.getSize());
		assertEquals(true, vector3.hasDuplicates());
		
		CombinatoricsVector<String> vector2 = new CombinatoricsVector<String>(
				array2);
		
		assertEquals(2, vector2.getSize());
		assertEquals(true, vector2.hasDuplicates());
		
		CombinatoricsVector<CombinatoricsVector<String>> vectorA = new CombinatoricsVector<CombinatoricsVector<String>>();
		vectorA.addValue(vector1);
		vectorA.addValue(vector3);
		
		assertEquals(2, vectorA.getSize());
		assertEquals(false, vectorA.hasDuplicates());
		
		CombinatoricsVector<CombinatoricsVector<String>> vectorB = new CombinatoricsVector<CombinatoricsVector<String>>();
		vectorB.addValue(vector2);
		vectorB.addValue(vector2);

		assertEquals(2, vectorB.getSize());
		assertEquals(true, vectorB.hasDuplicates());
		
		assertEquals(false,vectorA.equals(vectorB));
		
	}

}
