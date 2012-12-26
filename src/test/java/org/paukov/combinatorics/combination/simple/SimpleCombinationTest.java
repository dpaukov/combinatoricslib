/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.combination.simple;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.util.ComplexCombinationGenerator;

/**
 * @author Dmytro Paukov
 * 
 */
public class SimpleCombinationTest {

	@Test
	public void simpleCombination1Test() {

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
	public void simpleCombination2Test() {

		// create combinatorics vector
		ICombinatoricsVector<Integer> initialVector = Factory
				.createVector(new Integer[] { 1, 2, 3, 4, 5 });

		// create simple combination generator to generate 3-combination
		Generator<Integer> gen = Factory.createSimpleCombinationGenerator(
				initialVector, 3);

		// create iterator
		Iterator<ICombinatoricsVector<Integer>> itr = gen.iterator();

		// print the number of combinations
		assertEquals(10, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<Integer> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<Integer>> list = gen.generateAllObjects();

		assertEquals(10, list.size());

		assertEquals("CombinatoricsVector=([1, 2, 3], size=3)", list
				.get(0).toString());
		assertEquals("CombinatoricsVector=([1, 2, 4], size=3)", list
				.get(1).toString());
		assertEquals("CombinatoricsVector=([1, 2, 5], size=3)", list
				.get(2).toString());
		assertEquals("CombinatoricsVector=([1, 3, 4], size=3)", list
				.get(3).toString());
		assertEquals("CombinatoricsVector=([1, 3, 5], size=3)", list
				.get(4).toString());
		assertEquals("CombinatoricsVector=([1, 4, 5], size=3)", list
				.get(5).toString());
		assertEquals("CombinatoricsVector=([2, 3, 4], size=3)",
				list.get(6).toString());

		assertEquals("CombinatoricsVector=([2, 3, 5], size=3)", list
				.get(7).toString());
		assertEquals("CombinatoricsVector=([2, 4, 5], size=3)", list
				.get(8).toString());
		assertEquals("CombinatoricsVector=([3, 4, 5], size=3)", list
				.get(9).toString());
	}
	
	@Test
	public void simpleCombinationOfVectorsTest() {

		// create combinatorics vector
		ICombinatoricsVector<String> vectorA = Factory
				.createVector(new String[] { "A1", "A2", "A3" });
		
		ICombinatoricsVector<String> vectorB = Factory
				.createVector(new String[] { "B1", "B2", "B3" });
		
		ICombinatoricsVector<String> vectorC = Factory
				.createVector(new String[] { "C1", "C2", "C3" });
		
		// create combinatorics vector
		ICombinatoricsVector<ICombinatoricsVector<String>> initialVector = Factory.createVector();
		initialVector.addValue(vectorA);
		initialVector.addValue(vectorB);
		initialVector.addValue(vectorC);

		// create simple combination generator to generate 3-combination
		Generator<ICombinatoricsVector<String>> gen = Factory.createSimpleCombinationGenerator(
				initialVector, 2);

		// create iterator
		Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = gen.iterator();

		// print the number of combinations
		assertEquals(3, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr.next();
			
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = gen.generateAllObjects();

		assertEquals(3, list.size());

		assertEquals("CombinatoricsVector=([CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([B1, B2, B3], size=3)], size=2)", list
				.get(0).toString());
		assertEquals("CombinatoricsVector=([CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([C1, C2, C3], size=3)], size=2)", list
				.get(1).toString());
		assertEquals("CombinatoricsVector=([CombinatoricsVector=([B1, B2, B3], size=3), CombinatoricsVector=([C1, C2, C3], size=3)], size=2)", list
				.get(2).toString());
	}
	
	@Test
	public void simpleCombination1IteratorTest() {

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
		
		List<ICombinatoricsVector<String>> list = new ArrayList<ICombinatoricsVector<String>>();

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<String> combination = itr.next();
			System.out.println(combination);
			list.add( Factory.createVector(combination) );
		}

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
	
	
	class Apple {
		
		String color;
		
		Apple(String color){
			this.color=color;
		}
		
		public String toString() {
			return "(Apple: " + color + ")";
		}
		
	}
	
	@Test
	public void simpleObjectsCombinationTest() {

		// create combinatorics vector
		ICombinatoricsVector<Apple> initialVector = Factory.createVector();
		
		// Add 3 apples
		initialVector.addValue(new Apple("red"));
		initialVector.addValue(new Apple("green"));
		initialVector.addValue(new Apple("yellow"));

		// create simple combination generator to generate 2-combination of 3 apples
		Generator<Apple> gen = Factory.createSimpleCombinationGenerator(
				initialVector, 2);

		// create iterator
		Iterator<ICombinatoricsVector<Apple>> itr = gen.iterator();

		// print the number of combinations
		assertEquals(3, gen.getNumberOfGeneratedObjects());

		// go through the iterator
		while (itr.hasNext()) {
			ICombinatoricsVector<Apple> combination = itr.next();
			System.out.println(combination);
		}

		List<ICombinatoricsVector<Apple>> list = gen.generateAllObjects();

		assertEquals(3, list.size());

		assertEquals("CombinatoricsVector=([(Apple: red), (Apple: green)], size=2)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([(Apple: red), (Apple: yellow)], size=2)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([(Apple: green), (Apple: yellow)], size=2)", list.get(2)
				.toString());

	}
	
	@Test
	public void simpleObjectsModifiedCombinationTest() {

		// create combinatorics vector
		ICombinatoricsVector<Apple> initialVector = Factory.createVector();
		
		Apple red = new Apple("red");
		Apple green = new Apple("green");
		Apple yellow = new Apple("yellow");
		
		initialVector.addValue(red);
		initialVector.addValue(green);
		initialVector.addValue(yellow);

		// create simple combination generator to generate 2-combination of 3
		Generator<Apple> gen = Factory.createSimpleCombinationGenerator(
				initialVector, 2);

		// create iterator
		Iterator<ICombinatoricsVector<Apple>> itr = gen.iterator();

		// print the number of combinations
		assertEquals(3, gen.getNumberOfGeneratedObjects());

		// Print the first combination
		ICombinatoricsVector<Apple> combination = itr.next();
		System.out.println(combination);
		assertEquals("CombinatoricsVector=([(Apple: red), (Apple: green)], size=2)", combination.toString());
		
		// Modify the original red apple
		red.color = "brawn";
		
		// Print the next combination
		combination = itr.next();
		System.out.println(combination);
		assertEquals("CombinatoricsVector=([(Apple: brawn), (Apple: yellow)], size=2)", combination.toString());
		
		// Modify the original green apple
		green.color = "rose";

		// Print the next combination
		combination = itr.next();
		System.out.println(combination);
		assertEquals("CombinatoricsVector=([(Apple: rose), (Apple: yellow)], size=2)", combination.toString());

		// Modify the original yellow apple
		yellow.color = "white";
		
		List<ICombinatoricsVector<Apple>> list = gen.generateAllObjects();

		assertEquals(3, list.size());

		assertEquals("CombinatoricsVector=([(Apple: brawn), (Apple: rose)], size=2)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([(Apple: brawn), (Apple: white)], size=2)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([(Apple: rose), (Apple: white)], size=2)", list.get(2)
				.toString());

	}
}