package org.paukov.combinatorics.subsets;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;

public class SubSetsTest {

	@Test
	public void simpleSubsetTest() {

		// create a combinatorics vector (A, B, C)
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "A", "B", "C" });

		// create a sub-set generator
		Generator<String> gen = Factory.createSubSetGenerator(initialVector);

		// create an iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.createIterator();

		// verify the number of sunsets
		assertEquals(8, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(8, list.size());

		assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());
		assertEquals("CombinatoricsVector=([A], size=1)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([B], size=1)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([A, B], size=2)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([C], size=1)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([A, C], size=2)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([B, C], size=2)", list.get(6)
				.toString());

		assertEquals("CombinatoricsVector=([A, B, C], size=3)", list.get(7)
				.toString());
	}

	@Test
	public void emptySubsetTest() {

		// create the empty combinatorics vector
		ICombinatoricsVector<String> initialVector = Factory
				.<String> createVector();

		// create a sub-set generator
		Generator<String> gen = Factory.createSubSetGenerator(initialVector);

		// create an iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.createIterator();

		// verify the number of sunsets
		assertEquals(1, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());
	}

	@Test
	public void complexSubsetTest() {

		// create a combinatorics vector (A, A, B, B, C)
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "A", "A", "B", "B", "C" });

		// create a sub-set generator
		Generator<String> gen = Factory.createSubSetGenerator(initialVector);

		// create an iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.createIterator();

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(18, list.size());

		assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());
		assertEquals("CombinatoricsVector=([A], size=1)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([A, A], size=2)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([B], size=1)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([A, B], size=2)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([A, A, B], size=3)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([B, B], size=2)", list.get(6)
				.toString());
		assertEquals("CombinatoricsVector=([A, B, B], size=3)", list.get(7)
				.toString());
		assertEquals("CombinatoricsVector=([A, A, B, B], size=4)", list.get(8)
				.toString());
		assertEquals("CombinatoricsVector=([C], size=1)", list.get(9)
				.toString());
		assertEquals("CombinatoricsVector=([A, C], size=2)", list.get(10)
				.toString());
		assertEquals("CombinatoricsVector=([A, A, C], size=3)", list.get(11)
				.toString());
		assertEquals("CombinatoricsVector=([B, C], size=2)", list.get(12)
				.toString());
		assertEquals("CombinatoricsVector=([A, B, C], size=3)", list.get(13)
				.toString());
		assertEquals("CombinatoricsVector=([A, A, B, C], size=4)", list.get(14)
				.toString());
		assertEquals("CombinatoricsVector=([B, B, C], size=3)", list.get(15)
				.toString());
		assertEquals("CombinatoricsVector=([A, B, B, C], size=4)", list.get(16)
				.toString());
		assertEquals("CombinatoricsVector=([A, A, B, B, C], size=5)",
				list.get(17).toString());
	}

	@Test
	public void complexSubsetTest2() {

		// create a combinatorics vector (A, A, A)
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "A", "A", "A" });

		// create a sub-set generator
		Generator<String> gen = Factory.createSubSetGenerator(initialVector);

		// create an iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.createIterator();

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(4, list.size());

		assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());
		assertEquals("CombinatoricsVector=([A], size=1)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([A, A], size=2)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([A, A, A], size=3)", list.get(3)
				.toString());
	}

}
