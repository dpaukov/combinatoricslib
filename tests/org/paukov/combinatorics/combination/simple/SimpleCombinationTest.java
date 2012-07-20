package org.paukov.combinatorics.combination.simple;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class SimpleCombinationTest {

	@Test
	public void simpleCombinationTest() {

		// create combinatorics vector
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "red", "black", "white", "green",
						"blue" });

		// create simple combination generator to generate 3-combination
		Generator<String> gen = Factory.createSimpleCombinationGenerator(
				initialVector, 3);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

		// print the number of combinations
		assertEquals(10, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(10, list.size());

		assertEquals("CombinatoricsVector=([red, black, white], size=3)", list
				.get(0).toString());
		assertEquals("CombinatoricsVector=([red, black, green], size=3)", list
				.get(1).toString());
		assertEquals("CombinatoricsVector=([red, black, blue], size=3)", list
				.get(2).toString());
		assertEquals("CombinatoricsVector=([red, white, green], size=3)", list
				.get(3).toString());
		assertEquals("CombinatoricsVector=([red, white, blue], size=3)", list
				.get(4).toString());
		assertEquals("CombinatoricsVector=([red, green, blue], size=3)", list
				.get(5).toString());
		assertEquals("CombinatoricsVector=([black, white, green], size=3)",
				list.get(6).toString());

		assertEquals("CombinatoricsVector=([black, white, blue], size=3)", list
				.get(7).toString());
		assertEquals("CombinatoricsVector=([black, green, blue], size=3)", list
				.get(8).toString());
		assertEquals("CombinatoricsVector=([white, green, blue], size=3)", list
				.get(9).toString());
	}

	@Test
	public void simpleEmptyCombinationTest() {

		// create array of initial items
		List<String> array = new ArrayList<String>();
		array.add("red");

		// create combinatorics vector
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(array);

		// create simple combination generator to generate 0-combination
		Generator<String> gen = Factory.createSimpleCombinationGenerator(
				initialVector, 0);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

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
	public void simpleOneCombinationTest() {

		// create array of initial items
		List<String> array = new ArrayList<String>();
		array.add("red");

		// create combinatorics vector
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(array);

		// create simple combination generator to generate 1-combination
		Generator<String> gen = Factory.createSimpleCombinationGenerator(
				initialVector, 1);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

		// print the number of combinations
		assertEquals(1, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([red], size=1)", list.get(0)
				.toString());
	}

	@Test
	public void simpleOneTwoCombinationTest() {

		// create array of initial items
		List<String> array = new ArrayList<String>();
		array.add("red");

		// create combinatorics vector
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(array);

		// create simple combination generator to generate 2-combination of 1
		Generator<String> gen = Factory.createSimpleCombinationGenerator(
				initialVector, 2);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

		// print the number of combinations
		assertEquals(0, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		assertEquals(false, itr.hasNext());

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();
		assertEquals(0, list.size());

	}

	@Test
	public void simpleTwoTwoCombinationTest() {

		// create combinatorics vector
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "red", "green" });

		// create simple combination generator to generate 2-combination of 2
		Generator<String> gen = Factory.createSimpleCombinationGenerator(
				initialVector, 2);

		// create iterator
		Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

		// print the number of combinations
		assertEquals(1, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([red, green], size=2)", list.get(0)
				.toString());

	}
}