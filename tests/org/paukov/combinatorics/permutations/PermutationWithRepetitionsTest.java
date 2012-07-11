/**
 * 
 */
package org.paukov.combinatorics.permutations;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;

/**
 * @author Dmytro Paukov
 * 
 */
public class PermutationWithRepetitionsTest {

	@Test
	public void simpleTreeTwoPermutationWithRepetition() {

		ICombinatoricsVector<Integer> coreVector = Factory
				.createVector(new Integer[] { 1, 2, 3 });

		Generator<Integer> permutationWithRepetitionGenerator = Factory.createPermutationWithRepetitionGenerator(
				coreVector, 2);
		Iterator<ICombinatoricsVector<Integer>> permutationWithRepetitionIterator = permutationWithRepetitionGenerator
				.createIterator();

		assertEquals(9,
				permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());
		System.out.println("Number of permutationWithRepetition is: "
				+ permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());

		while (permutationWithRepetitionIterator.hasNext()) {
			permutationWithRepetitionIterator.next();
			System.out.println(permutationWithRepetitionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = permutationWithRepetitionGenerator
				.generateAllObjects();

		assertEquals(9, list.size());

		assertEquals("CombinatoricsVector=([1, 1], size=2)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([2, 1], size=2)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([3, 1], size=2)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([1, 2], size=2)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([2, 2], size=2)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([3, 2], size=2)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([1, 3], size=2)", list.get(6)
				.toString());
		assertEquals("CombinatoricsVector=([2, 3], size=2)", list.get(7)
				.toString());
		assertEquals("CombinatoricsVector=([3, 3], size=2)", list.get(8)
				.toString());
	}

	@Test
	public void simpleTreeOnePermutationWithRepetition() {

		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(1);
		array.add(2);
		array.add(3);

		ICombinatoricsVector<Integer> coreVector = Factory.createVector(array);

		Generator<Integer> permutationWithRepetitionGenerator = Factory.createPermutationWithRepetitionGenerator(
				coreVector, 1);
		Iterator<ICombinatoricsVector<Integer>> permutationWithRepetitionIterator = permutationWithRepetitionGenerator
				.createIterator();

		assertEquals(3,
				permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());
		System.out.println("Number of permutationWithRepetition is: "
				+ permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());

		while (permutationWithRepetitionIterator.hasNext()) {
			permutationWithRepetitionIterator.next();
			System.out.println(permutationWithRepetitionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = permutationWithRepetitionGenerator
				.generateAllObjects();

		assertEquals(3, list.size());

		assertEquals("CombinatoricsVector=([1], size=1)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([2], size=1)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([3], size=1)", list.get(2)
				.toString());
	}

	@Test
	public void simpleThreeZeroPermutationWithRepetition() {

		ICombinatoricsVector<Integer> coreVector = Factory
				.createVector(new Integer[] { 1, 2, 3 });

		Generator<Integer> permutationWithRepetitionGenerator = Factory.createPermutationWithRepetitionGenerator(
				coreVector, 0);
		Iterator<ICombinatoricsVector<Integer>> permutationWithRepetitionIterator = permutationWithRepetitionGenerator
				.createIterator();

		assertEquals(1,
				permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());
		System.out.println("Number of permutationWithRepetition is: "
				+ permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());

		while (permutationWithRepetitionIterator.hasNext()) {
			permutationWithRepetitionIterator.next();
			System.out.println(permutationWithRepetitionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = permutationWithRepetitionGenerator
				.generateAllObjects();

		assertEquals(1, list.size());
		assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());

	}

	@Test
	public void simpleOneOnePermutationWithRepetition() {

		ICombinatoricsVector<Integer> coreVector = Factory
				.createVector(new Integer[] { 1 });

		Generator<Integer> permutationWithRepetitionGenerator = Factory.createPermutationWithRepetitionGenerator(
				coreVector, 1);
		Iterator<ICombinatoricsVector<Integer>> permutationWithRepetitionIterator = permutationWithRepetitionGenerator
				.createIterator();

		assertEquals(1,
				permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());
		System.out.println("Number of permutationWithRepetition is: "
				+ permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());

		while (permutationWithRepetitionIterator.hasNext()) {
			permutationWithRepetitionIterator.next();
			System.out.println(permutationWithRepetitionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = permutationWithRepetitionGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([1], size=1)", list.get(0)
				.toString());
	}

	@Test
	public void simpleOneTwoPermutationWithRepetition() {

		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(1);

		ICombinatoricsVector<Integer> coreVector = Factory.createVector(array);

		Generator<Integer> permutationWithRepetitionGenerator = Factory.createPermutationWithRepetitionGenerator(
				coreVector, 2);
		Iterator<ICombinatoricsVector<Integer>> permutationWithRepetitionIterator = permutationWithRepetitionGenerator
				.createIterator();

		assertEquals(1,
				permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());
		System.out.println("Number of permutationWithRepetition is: "
				+ permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());

		while (permutationWithRepetitionIterator.hasNext()) {
			permutationWithRepetitionIterator.next();
			System.out.println(permutationWithRepetitionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = permutationWithRepetitionGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([1, 1], size=2)", list.get(0)
				.toString());
	}

	@Test
	public void simpleOneZeroPermutationWithRepetition() {

		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(1);

		ICombinatoricsVector<Integer> coreVector = Factory.createVector(array);

		Generator<Integer> permutationWithRepetitionGenerator = Factory.createPermutationWithRepetitionGenerator(
				coreVector, 0);
		Iterator<ICombinatoricsVector<Integer>> permutationWithRepetitionIterator = permutationWithRepetitionGenerator
				.createIterator();

		assertEquals(1,
				permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());
		System.out.println("Number of permutationWithRepetition is: "
				+ permutationWithRepetitionGenerator
						.getNumberOfGeneratedObjects());

		while (permutationWithRepetitionIterator.hasNext()) {
			permutationWithRepetitionIterator.next();
			System.out.println(permutationWithRepetitionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = permutationWithRepetitionGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());
	}
}
