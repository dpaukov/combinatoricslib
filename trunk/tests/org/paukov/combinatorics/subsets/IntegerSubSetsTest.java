package org.paukov.combinatorics.subsets;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import org.paukov.combinatorics.IGenerator;
import org.paukov.combinatorics.IntegerFactory;
import org.paukov.combinatorics.IntegerGenerator;
import org.paukov.combinatorics.IntegerVector;

public class IntegerSubSetsTest {

	@Test
	public void simpleSubsetTest() {

		// create a combinatorics vector (A, B, C)
		IntegerVector initialVector = IntegerFactory
				.createIntegerVector(new int[] { 1, 2, 3 });

		// create a sub-set generator
		IGenerator<IntegerVector> gen = IntegerFactory
				.createIntegerSubSetGenerator(initialVector);

		// create an iterator
		Iterator<IntegerVector> itr = gen.iterator();

		// verify the number of sunsets
		assertEquals(8, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			IntegerVector subset = itr.next();
			System.out.println(subset);
		}

		List<IntegerVector> list = gen.generateAllObjects();

		assertEquals(8, list.size());

		assertEquals("IntegerVector=([], size=0)", list.get(0).toString());
		assertEquals("IntegerVector=([1], size=1)", list.get(1).toString());
		assertEquals("IntegerVector=([2], size=1)", list.get(2).toString());
		assertEquals("IntegerVector=([1, 2], size=2)", list.get(3).toString());
		assertEquals("IntegerVector=([3], size=1)", list.get(4).toString());
		assertEquals("IntegerVector=([1, 3], size=2)", list.get(5).toString());
		assertEquals("IntegerVector=([2, 3], size=2)", list.get(6).toString());
		assertEquals("IntegerVector=([1, 2, 3], size=3)", list.get(7)
				.toString());
	}

	@Test
	public void simpleSubsetFroEachTest() {

		// create a combinatorics vector (A, B, C)
		IntegerVector initialVector = IntegerFactory
				.createIntegerVector(new int[] { 1, 2, 3 });

		// create a sub-set generator
		IntegerGenerator gen = IntegerFactory
				.createIntegerSubSetGenerator(initialVector);

		// verify the number of sunsets
		assertEquals(8, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		for (IntegerVector subset : gen) {
			System.out.println(subset);
		}

		List<IntegerVector> list = gen.generateAllObjects();

		assertEquals(8, list.size());

		assertEquals("IntegerVector=([], size=0)", list.get(0).toString());
		assertEquals("IntegerVector=([1], size=1)", list.get(1).toString());
		assertEquals("IntegerVector=([2], size=1)", list.get(2).toString());
		assertEquals("IntegerVector=([1, 2], size=2)", list.get(3).toString());
		assertEquals("IntegerVector=([3], size=1)", list.get(4).toString());
		assertEquals("IntegerVector=([1, 3], size=2)", list.get(5).toString());
		assertEquals("IntegerVector=([2, 3], size=2)", list.get(6).toString());
		assertEquals("IntegerVector=([1, 2, 3], size=3)", list.get(7)
				.toString());
	}

	@Test
	public void emptySubsetTest() {

		// create the empty combinatorics vector
		IntegerVector initialVector = IntegerFactory.createIntegerVector();

		// create a sub-set generator
		IntegerGenerator gen = IntegerFactory
				.createIntegerSubSetGenerator(initialVector);

		// create an iterator
		Iterator<IntegerVector> itr = gen.iterator();

		// verify the number of sunsets
		assertEquals(1, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			IntegerVector combination = itr.next();
			System.out.println(combination);
		}

		List<IntegerVector> list = gen.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("IntegerVector=([], size=0)", list.get(0).toString());
	}

	@Test
	public void complexSubsetTest() {

		// create a combinatorics vector (A, A, B, B, C)
		IntegerVector initialVector = IntegerFactory
				.createIntegerVector(new int[] { 1, 1, 2, 2, 3 });

		// create a sub-set generator
		IntegerGenerator gen = IntegerFactory
				.createIntegerSubSetGenerator(initialVector);

		// create an iterator
		Iterator<IntegerVector> itr = gen.iterator();

		// go through the iterator
		while (itr.hasNext()) {
			IntegerVector combination = itr.next();
			System.out.println(combination);
		}

		List<IntegerVector> list = gen.generateAllObjects();

		assertEquals(18, list.size());

		assertEquals("IntegerVector=([], size=0)", list.get(0).toString());
		assertEquals("IntegerVector=([1], size=1)", list.get(1).toString());
		assertEquals("IntegerVector=([1, 1], size=2)", list.get(2).toString());
		assertEquals("IntegerVector=([2], size=1)", list.get(3).toString());
		assertEquals("IntegerVector=([1, 2], size=2)", list.get(4).toString());
		assertEquals("IntegerVector=([1, 1, 2], size=3)", list.get(5)
				.toString());
		assertEquals("IntegerVector=([2, 2], size=2)", list.get(6).toString());
		assertEquals("IntegerVector=([1, 2, 2], size=3)", list.get(7)
				.toString());
		assertEquals("IntegerVector=([1, 1, 2, 2], size=4)", list.get(8)
				.toString());
		assertEquals("IntegerVector=([3], size=1)", list.get(9).toString());
		assertEquals("IntegerVector=([1, 3], size=2)", list.get(10).toString());
		assertEquals("IntegerVector=([1, 1, 3], size=3)", list.get(11)
				.toString());
		assertEquals("IntegerVector=([2, 3], size=2)", list.get(12).toString());
		assertEquals("IntegerVector=([1, 2, 3], size=3)", list.get(13)
				.toString());
		assertEquals("IntegerVector=([1, 1, 2, 3], size=4)", list.get(14)
				.toString());
		assertEquals("IntegerVector=([2, 2, 3], size=3)", list.get(15)
				.toString());
		assertEquals("IntegerVector=([1, 2, 2, 3], size=4)", list.get(16)
				.toString());
		assertEquals("IntegerVector=([1, 1, 2, 2, 3], size=5)", list.get(17)
				.toString());
	}

	@Test
	public void complexSubsetTest2() {

		// create a combinatorics vector (A, A, A)
		IntegerVector initialVector = IntegerFactory
				.createIntegerVector(new int[] { 1, 1, 1 });

		// create a sub-set generator
		IntegerGenerator gen = IntegerFactory
				.createIntegerSubSetGenerator(initialVector);

		// create an iterator
		Iterator<IntegerVector> itr = gen.iterator();

		// go through the iterator
		while (itr.hasNext()) {
			IntegerVector combination = itr.next();
			System.out.println(combination);
		}

		List<IntegerVector> list = gen.generateAllObjects();

		assertEquals(4, list.size());

		assertEquals("IntegerVector=([], size=0)", list.get(0).toString());
		assertEquals("IntegerVector=([1], size=1)", list.get(1).toString());
		assertEquals("IntegerVector=([1, 1], size=2)", list.get(2).toString());
		assertEquals("IntegerVector=([1, 1, 1], size=3)", list.get(3)
				.toString());
	}

	@Test
	public void treatAsDistinctSubsetTest() {

		// create a combinatorics vector (a, b, c, d)
		IntegerVector initialVector = IntegerFactory
				.createIntegerVector(new int[] { 1, 2, 2, 2 });

		// create a sub-set generator and don't treat the identical elements as
		// identical
		IntegerGenerator gen = new IntegerSubSetGenerator(initialVector, false);

		List<IntegerVector> list = gen.generateAllObjects();

		assertEquals(16, list.size());

		assertEquals("IntegerVector=([], size=0)", list.get(0).toString());
		assertEquals("IntegerVector=([1], size=1)", list.get(1).toString());
		assertEquals("IntegerVector=([2], size=1)", list.get(2).toString());
		assertEquals("IntegerVector=([1, 2], size=2)", list.get(3).toString());
		assertEquals("IntegerVector=([2], size=1)", list.get(4).toString());
		assertEquals("IntegerVector=([1, 2], size=2)", list.get(5).toString());
		assertEquals("IntegerVector=([2, 2], size=2)", list.get(6).toString());
		assertEquals("IntegerVector=([1, 2, 2], size=3)", list.get(7)
				.toString());
		assertEquals("IntegerVector=([2], size=1)", list.get(8).toString());
		assertEquals("IntegerVector=([1, 2], size=2)", list.get(9).toString());
		assertEquals("IntegerVector=([2, 2], size=2)", list.get(10).toString());
		assertEquals("IntegerVector=([1, 2, 2], size=3)", list.get(11)
				.toString());
		assertEquals("IntegerVector=([2, 2], size=2)", list.get(12).toString());
		assertEquals("IntegerVector=([1, 2, 2], size=3)", list.get(13)
				.toString());
		assertEquals("IntegerVector=([2, 2, 2], size=3)", list.get(14)
				.toString());
		assertEquals("IntegerVector=([1, 2, 2, 2], size=4)", list.get(15)
				.toString());
	}

}
