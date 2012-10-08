/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.subsets;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.IFilter;

/**
 * @author Dmytro Paukov
 * 
 */
public class SubSetsTest {

	@Test
	public void simpleSubsetTest() {

		// create a combinatorics vector (A, B, C)
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "A", "B", "C" });

		// create a sub-set generator
		Generator<String> gen = Factory.createSubSetGenerator(initialVector);

		// create an iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

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
	public void simpleSubsetFroEachTest() {

		// create a combinatorics vector (A, B, C)
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "A", "B", "C" });

		// create a sub-set generator
		Generator<String> gen = Factory.createSubSetGenerator(initialVector);

		// verify the number of sunsets
		assertEquals(8, gen.getNumberOfGeneratedObjects());

		for( ICombinatoricsVector<String> combination : gen )
			System.out.println("For Each: " + combination);
		
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
		Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

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
		Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

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
		Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

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
	
	@Test
	public void upToTwoElementsSubsetTest() {

		// create a combinatorics vector (a, b, c, d)
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "a", "b", "c", "d" });

		// create a sub-set generator
		Generator<String> gen = Factory.createSubSetGenerator(initialVector);

		List<ICombinatoricsVector<String>> list = gen.generateFilteredObjects(new IFilter<ICombinatoricsVector<String>>() {
			
			@Override
			public boolean accepted(long index, ICombinatoricsVector<String> value) {
				return value.getSize() <= 2;
			}
		});

		assertEquals(11, list.size());

		assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());
		assertEquals("CombinatoricsVector=([a], size=1)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([b], size=1)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([a, b], size=2)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([c], size=1)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([a, c], size=2)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([b, c], size=2)", list.get(6)
				.toString());
		assertEquals("CombinatoricsVector=([d], size=1)", list.get(7)
				.toString());
		assertEquals("CombinatoricsVector=([a, d], size=2)", list.get(8)
				.toString());
		assertEquals("CombinatoricsVector=([b, d], size=2)", list.get(9)
				.toString());
		assertEquals("CombinatoricsVector=([c, d], size=2)", list.get(10)
				.toString());
	}

	
	@Test
	public void exactlyTwoElementsSubsetTest() {

		// create a combinatorics vector (a, b, c, d)
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "a", "b", "c", "d" });

		// create a sub-set generator
		Generator<String> gen = Factory.createSubSetGenerator(initialVector);

		List<ICombinatoricsVector<String>> list = gen.generateFilteredObjects(new IFilter<ICombinatoricsVector<String>>() {
			
			@Override
			public boolean accepted(long index, ICombinatoricsVector<String> value) {
				return value.getSize() == 2;
			}
		});

		assertEquals(6, list.size());

		assertEquals("CombinatoricsVector=([a, b], size=2)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([a, c], size=2)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([b, c], size=2)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([a, d], size=2)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([b, d], size=2)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([c, d], size=2)", list.get(5)
				.toString());
	}

	
	@Test
	public void treatAsDistinctSubsetTest() {

		// create a combinatorics vector (a, b, c, d)
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "a", "b", "b", "b" });

		// create a sub-set generator and don't treat the identical elements as identical
		Generator<String> gen = Factory.createSubSetGenerator(initialVector, false);

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(16, list.size());

		assertEquals("CombinatoricsVector=([], size=0)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([a], size=1)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([b], size=1)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([a, b], size=2)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([b], size=1)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([a, b], size=2)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([b, b], size=2)", list.get(6)
				.toString());
		assertEquals("CombinatoricsVector=([a, b, b], size=3)", list.get(7)
				.toString());
		assertEquals("CombinatoricsVector=([b], size=1)", list.get(8)
				.toString());
		assertEquals("CombinatoricsVector=([a, b], size=2)", list.get(9)
				.toString());
		assertEquals("CombinatoricsVector=([b, b], size=2)", list.get(10)
				.toString());
		assertEquals("CombinatoricsVector=([a, b, b], size=3)", list.get(11)
				.toString());
		assertEquals("CombinatoricsVector=([b, b], size=2)", list.get(12)
				.toString());
		assertEquals("CombinatoricsVector=([a, b, b], size=3)", list.get(13)
				.toString());
		assertEquals("CombinatoricsVector=([b, b, b], size=3)", list.get(14)
				.toString());
		assertEquals("CombinatoricsVector=([a, b, b, b], size=4)", list.get(15)
				.toString());
	}
	
}
