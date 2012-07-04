package org.paukov.combinatorics.combination.simple;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;

public class SimpleCombinationTest {

	@Test
	public void simpleCombinationTest() {

		// create array of initial items
		List<String> array = new ArrayList<String>();
		array.add("red");
		array.add("black");
		array.add("white");
		array.add("green");
		array.add("blue");

		// create combinatorics vector
		CombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(
				array);

		// create simple combination generator to generate 3-combination
		Generator<String> gen = new SimpleCombinationGenerator<String>(
				initialVector, 3);

		// create iterator
		Iterator<CombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
		assertEquals(10, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			CombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<CombinatoricsVector<String>> list = gen.generateAllObjects();

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

		assertEquals("CombinatoricsVector=([black, white, blue], size=3)",
				list.get(7).toString());
		assertEquals("CombinatoricsVector=([black, green, blue], size=3)",
				list.get(8).toString());
		assertEquals("CombinatoricsVector=([white, green, blue], size=3)",
				list.get(9).toString());
	}

	@Test
	public void simpleEmptyCombinationTest() {

		// create array of initial items
		List<String> array = new ArrayList<String>();
		array.add("red");

		// create combinatorics vector
		CombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(
				array);

		// create simple combination generator to generate 0-combination
		Generator<String> gen = new SimpleCombinationGenerator<String>(
				initialVector, 0);

		// create iterator
		Iterator<CombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
		assertEquals(1, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			CombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<CombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([], size=0)", list
				.get(0).toString());
	}
	
	@Test
	public void simpleOneCombinationTest() {

		// create array of initial items
		List<String> array = new ArrayList<String>();
		array.add("red");

		// create combinatorics vector
		CombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(
				array);

		// create simple combination generator to generate 1-combination
		Generator<String> gen = new SimpleCombinationGenerator<String>(
				initialVector, 1);

		// create iterator
		Iterator<CombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
		assertEquals(1, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			CombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<CombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([red], size=1)", list
				.get(0).toString());
	}
	
	@Test
	public void simpleOneTwoCombinationTest() {

		// create array of initial items
		List<String> array = new ArrayList<String>();
		array.add("red");

		// create combinatorics vector
		CombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(
				array);

		// create simple combination generator to generate 2-combination of 1
		Generator<String> gen = new SimpleCombinationGenerator<String>(
				initialVector, 2);

		// create iterator
		Iterator<CombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
		assertEquals(0, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		assertEquals(false, itr.hasNext());

		List<CombinatoricsVector<String>> list = gen.generateAllObjects();
		assertEquals(0, list.size());

	}
	
	@Test
	public void simpleTwoTwoCombinationTest() {

		// create array of initial items
		List<String> array = new ArrayList<String>();
		array.add("red");
		array.add("green");

		// create combinatorics vector
		CombinatoricsVector<String> initialVector = new CombinatoricsVector<String>(
				array);

		// create simple combination generator to generate 2-combination of 2
		Generator<String> gen = new SimpleCombinationGenerator<String>(
				initialVector, 2);

		// create iterator
		Iterator<CombinatoricsVector<String>> itr = gen.createIterator();

		// print the number of combinations
		assertEquals(1, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			CombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
		}

		List<CombinatoricsVector<String>> list = gen.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([red, green], size=2)", list
				.get(0).toString());

	}
}