/**
 * 
 */
package org.paukov.combinatorics.permutations;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * @author Dmytro Paukov
 * 
 */
public class PermutationsTest {

	@Test
	public void simplePermutation() {

		ICombinatoricsVector<Integer> corePermutation = Factory
				.createVector(new Integer[] { 1, 2, 3 });
		Generator<Integer> generator = Factory.createPermutationGenerator(
				corePermutation);

		System.out.println("Number of permutations is: "
				+ generator.getNumberOfGeneratedObjects());
		assertEquals(6, generator.getNumberOfGeneratedObjects());

		Iterator<ICombinatoricsVector<Integer>> iterator = generator
				.createIterator();

		while (iterator.hasNext()) {
			iterator.next();
			System.out.println(iterator);
		}

		List<ICombinatoricsVector<Integer>> list = generator
				.generateAllObjects();

		assertEquals(6, list.size());

		assertEquals("CombinatoricsVector=([1, 2, 3], size=3)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([1, 3, 2], size=3)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([3, 1, 2], size=3)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([3, 2, 1], size=3)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([2, 3, 1], size=3)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([2, 1, 3], size=3)", list.get(5)
				.toString());
	}

	@Test
	public void simpleOnePermutation() {

		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(1);

		ICombinatoricsVector<Integer> corePermutation = Factory
				.createVector(array);
		Generator<Integer> generator = Factory.createPermutationGenerator(
				corePermutation);

		System.out.println("Number of permutations is: "
				+ generator.getNumberOfGeneratedObjects());
		assertEquals(1, generator.getNumberOfGeneratedObjects());

		Iterator<ICombinatoricsVector<Integer>> iterator = generator
				.createIterator();

		while (iterator.hasNext()) {
			iterator.next();
			System.out.println(iterator);
		}

		List<ICombinatoricsVector<Integer>> list = generator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([1], size=1)", list.get(0)
				.toString());
	}

	@Test
	public void simpleEmptyPermutation() {

		ArrayList<Integer> array = new ArrayList<Integer>();

		ICombinatoricsVector<Integer> corePermutation = Factory
				.createVector(array);
		Generator<Integer> generator = Factory.createPermutationGenerator(
				corePermutation);

		System.out.println("Number of permutations is: "
				+ generator.getNumberOfGeneratedObjects());
		assertEquals(0, generator.getNumberOfGeneratedObjects());

		Iterator<ICombinatoricsVector<Integer>> iterator = generator
				.createIterator();

		while (iterator.hasNext()) {
			iterator.next();
			System.out.println(iterator);
		}

		List<ICombinatoricsVector<Integer>> list = generator
				.generateAllObjects();

		assertEquals(0, list.size());
	}
}
