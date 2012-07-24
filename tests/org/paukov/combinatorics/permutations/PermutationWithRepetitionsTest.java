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
import org.paukov.combinatorics.IFilter;

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
				.iterator();

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
				.iterator();

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
				.iterator();

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
				.iterator();

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
				.iterator();

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
				.iterator();

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
	public void sizeofThreePermutations() {

		ICombinatoricsVector<String> originalVector = Factory
				.createVector(new String[] { "a", "b", "c", "d" });
		Generator<String> generator = Factory.createPermutationWithRepetitionGenerator(
				originalVector, 3);

		assertEquals(64, generator.getNumberOfGeneratedObjects());
		System.out.println("Number of permutations is: "
				+ generator.getNumberOfGeneratedObjects());

		for (ICombinatoricsVector<String> perm : generator)	{
				System.out.println("sizeofThreePermutations " + perm);
		}

		List<ICombinatoricsVector<String>> list = generator
				.generateFilteredObjects(new IFilter<ICombinatoricsVector<String>>() {
					@Override
					public boolean accepted(long index, ICombinatoricsVector<String> value) {
						return (!value.hasDuplicates());
					}
				});

		assertEquals(24, list.size());
		
		assertEquals("CombinatoricsVector=([c, b, a], size=3)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([d, b, a], size=3)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([b, c, a], size=3)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([d, c, a], size=3)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([b, d, a], size=3)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([c, d, a], size=3)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([c, a, b], size=3)", list.get(6)
				.toString());
		assertEquals("CombinatoricsVector=([d, a, b], size=3)", list.get(7)
				.toString());
		assertEquals("CombinatoricsVector=([a, c, b], size=3)", list.get(8)
				.toString());
		assertEquals("CombinatoricsVector=([d, c, b], size=3)", list.get(9)
				.toString());
		assertEquals("CombinatoricsVector=([a, d, b], size=3)", list.get(10)
				.toString());
		assertEquals("CombinatoricsVector=([c, d, b], size=3)", list.get(11)
				.toString());
		assertEquals("CombinatoricsVector=([b, a, c], size=3)", list.get(12)
				.toString());
		assertEquals("CombinatoricsVector=([d, a, c], size=3)", list.get(13)
				.toString());
		assertEquals("CombinatoricsVector=([a, b, c], size=3)", list.get(14)
				.toString());
		assertEquals("CombinatoricsVector=([d, b, c], size=3)", list.get(15)
				.toString());
		assertEquals("CombinatoricsVector=([a, d, c], size=3)", list.get(16)
				.toString());
		assertEquals("CombinatoricsVector=([b, d, c], size=3)", list.get(17)
				.toString());
		assertEquals("CombinatoricsVector=([b, a, d], size=3)", list.get(18)
				.toString());
		assertEquals("CombinatoricsVector=([c, a, d], size=3)", list.get(19)
				.toString());
		assertEquals("CombinatoricsVector=([a, b, d], size=3)", list.get(20)
				.toString());
		assertEquals("CombinatoricsVector=([c, b, d], size=3)", list.get(21)
				.toString());
		assertEquals("CombinatoricsVector=([a, c, d], size=3)", list.get(22)
				.toString());
		assertEquals("CombinatoricsVector=([b, c, d], size=3)", list.get(23)
				.toString());
	}
	
	@Test
	public void threeTuplesOfTwoElements() {

		ICombinatoricsVector<Integer> originalVector = Factory
				.createVector(new Integer[] { 0, 1 });
		Generator<Integer> generator = Factory.createPermutationWithRepetitionGenerator(
				originalVector, 3);

		assertEquals(8, generator.getNumberOfGeneratedObjects());
		System.out.println("Number of permutations is: "
				+ generator.getNumberOfGeneratedObjects());

		for (ICombinatoricsVector<Integer> perm : generator)	{
				System.out.println("threeTuplesOfTwoElements " + perm);
		}

		List<ICombinatoricsVector<Integer>> list = generator
				.generateAllObjects();

		assertEquals(8, list.size());
		
		assertEquals("CombinatoricsVector=([0, 0, 0], size=3)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([1, 0, 0], size=3)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([0, 1, 0], size=3)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([1, 1, 0], size=3)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([0, 0, 1], size=3)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([1, 0, 1], size=3)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([0, 1, 1], size=3)", list.get(6)
				.toString());
		assertEquals("CombinatoricsVector=([1, 1, 1], size=3)", list.get(7)
				.toString());
		
	}
}
