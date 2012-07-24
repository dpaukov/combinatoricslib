package org.paukov.combinatorics;

import static org.junit.Assert.*;


import org.junit.Test;

public class FactoryTest {

	@Test
	public void testRange1() {

		// create a combinatorics vector (1, 2, 3)
		ICombinatoricsVector<Integer> vector = Factory.range(3);
		
		System.out.println(vector);

		assertEquals(3, vector.getSize());
		assertEquals(false, vector.hasDuplicates());

		assertEquals((Integer)1, vector.getValue(0));
		assertEquals((Integer)2, vector.getValue(1));
		assertEquals((Integer)3, vector.getValue(2));
		
	}
	
	@Test
	public void testRange2() {

		// create a combinatorics vector ()
		ICombinatoricsVector<Integer> vector = Factory.range(0);
		
		System.out.println(vector);

		assertEquals(0, vector.getSize());
		assertEquals(false, vector.hasDuplicates());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRange3() {

		// create a combinatorics vector from -1
		Factory.range(-1);
		
	}
	
	@Test
	public void testRangeFromTo1() {

		// create a combinatorics vector (2, 3, 4, 5)
		ICombinatoricsVector<Integer> vector = Factory.range(2,5);
		
		System.out.println(vector);

		assertEquals(4, vector.getSize());
		assertEquals(false, vector.hasDuplicates());

		assertEquals((Integer)2, vector.getValue(0));
		assertEquals((Integer)3, vector.getValue(1));
		assertEquals((Integer)4, vector.getValue(2));
		assertEquals((Integer)5, vector.getValue(3));
		
	}
	
	@Test
	public void testRangeFromTo2() {

		// create a combinatorics vector (2)
		ICombinatoricsVector<Integer> vector = Factory.range(2,2);
		
		System.out.println(vector);

		assertEquals(1, vector.getSize());
		assertEquals(false, vector.hasDuplicates());

		assertEquals((Integer)2, vector.getValue(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRangeFromTo3() {

		// create an error
		Factory.range(2,1);
	}

}
