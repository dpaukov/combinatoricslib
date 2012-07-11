package org.paukov.combinatorics.combination.multi;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.combination.multi.MultiCombinationGenerator;

public class MultiCombinationsTest {

	@Test
	public void multiCombinationTest() {

		// create combinatorics vector
		ICombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(
				new String[] { "A", "B", "C" });

		// create multi-combination generator to generate 3-combination
		Generator<String> gen = new MultiCombinationGenerator<String>(
				initialVector, 3);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
		assertEquals(10, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(10, list.size());

		assertEquals("CombinatoricsVector=([A, A, A], size=3)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([A, A, B], size=3)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([A, A, C], size=3)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([A, B, B], size=3)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([A, B, C], size=3)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([A, C, C], size=3)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([B, B, B], size=3)", list.get(6)
				.toString());

		assertEquals("CombinatoricsVector=([B, B, C], size=3)", list.get(7)
				.toString());
		assertEquals("CombinatoricsVector=([B, C, C], size=3)", list.get(8)
				.toString());
		assertEquals("CombinatoricsVector=([C, C, C], size=3)", list.get(9)
				.toString());
	}

	@Test
	public void multiCombinationOneTest() {

		// create array of initial items
		List<String> array = new ArrayList<String>();
		array.add("A");

		// create combinatorics vector
		ICombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(
				array);

		// create multi-combination generator to generate 3-combination
		Generator<String> gen = new MultiCombinationGenerator<String>(
				initialVector, 3);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
		assertEquals(1, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([A, A, A], size=3)", list.get(0)
				.toString());
	}

	@Test
	public void multiCombinationEmptyTest() {

		// create combinatorics vector
		ICombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(new String[] { "A", "B" });

		// create multi-combination generator to generate 0-combination
		Generator<String> gen = new MultiCombinationGenerator<String>(
				initialVector, 0);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
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
	public void multiCombinationEmptyEmptyTest() {

		// create array of initial empty items
		List<String> array = new ArrayList<String>();

		// create combinatorics vector
		ICombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(
				array);

		// create multi-combination generator to generate 0-combination
		Generator<String> gen = new MultiCombinationGenerator<String>(
				initialVector, 0);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
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
	public void multiCombinationEmptyOneTest() {

		// create array of initial empty items
		List<String> array = new ArrayList<String>();

		// create combinatorics vector
		ICombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(
				array);

		// create multi-combination generator to generate 1-combination
		Generator<String> gen = new MultiCombinationGenerator<String>(
				initialVector, 1);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
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
	public void multiCombinationMinusOneTest() {

		// create combinatorics vector (A, B, C)
		ICombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(new String[] { "A", "B", "C" });

		// create multi-combination generator to generate 3-combination
		Generator<String> gen = new MultiCombinationGenerator<String>(
				initialVector, -1);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
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

}
