/**
 * 
 */
package org.paukov.combinatorics.composition;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * @author Dmytro.Paukov
 * 
 */
public class CompositionTest {

	@Test
	public void simpleFiveComposition() {

		Generator<Integer> compositionGenerator = Factory
				.createCompositionGenerator(5);
		Iterator<ICombinatoricsVector<Integer>> compositionIterator = compositionGenerator
				.createIterator();

		System.out.println("Number of compositions is: "
				+ compositionGenerator.getNumberOfGeneratedObjects());
		assertEquals(16, compositionGenerator.getNumberOfGeneratedObjects());

		while (compositionIterator.hasNext()) {
			compositionIterator.next();
			System.out.println(compositionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = compositionGenerator
				.generateAllObjects();

		assertEquals(16, list.size());

		assertEquals("CombinatoricsVector=([5], size=1)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([1, 4], size=2)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([2, 3], size=2)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([1, 1, 3], size=3)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([3, 2], size=2)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([1, 2, 2], size=3)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([2, 1, 2], size=3)", list.get(6)
				.toString());

		assertEquals("CombinatoricsVector=([1, 1, 1, 2], size=4)", list.get(7)
				.toString());
		assertEquals("CombinatoricsVector=([4, 1], size=2)", list.get(8)
				.toString());
		assertEquals("CombinatoricsVector=([1, 3, 1], size=3)", list.get(9)
				.toString());
		assertEquals("CombinatoricsVector=([2, 2, 1], size=3)", list.get(10)
				.toString());
		assertEquals("CombinatoricsVector=([1, 1, 2, 1], size=4)", list.get(11)
				.toString());
		assertEquals("CombinatoricsVector=([3, 1, 1], size=3)", list.get(12)
				.toString());
		assertEquals("CombinatoricsVector=([1, 2, 1, 1], size=4)", list.get(13)
				.toString());

		assertEquals("CombinatoricsVector=([2, 1, 1, 1], size=4)", list.get(14)
				.toString());
		assertEquals("CombinatoricsVector=([1, 1, 1, 1, 1], size=5)",
				list.get(15).toString());
	}

	@Test
	public void simpleFiveCompositionRange() {

		Generator<Integer> compositionGenerator = Factory
				.createCompositionGenerator(5);

		System.out.println("Number of compositions is: "
				+ compositionGenerator.getNumberOfGeneratedObjects());
		assertEquals(16, compositionGenerator.getNumberOfGeneratedObjects());

		List<ICombinatoricsVector<Integer>> list = compositionGenerator
				.generateObjectsRange(5, 7);

		assertEquals(3, list.size());

		assertEquals("CombinatoricsVector=([3, 2], size=2)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([1, 2, 2], size=3)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([2, 1, 2], size=3)", list.get(2)
				.toString());
	}

	@Test
	public void simpleOneComposition() {

		Generator<Integer> compositionGenerator = Factory
				.createCompositionGenerator(1);
		Iterator<ICombinatoricsVector<Integer>> compositionIterator = compositionGenerator
				.createIterator();

		System.out.println("Number of compositions is: "
				+ compositionGenerator.getNumberOfGeneratedObjects());
		assertEquals(1, compositionGenerator.getNumberOfGeneratedObjects());

		while (compositionIterator.hasNext()) {
			compositionIterator.next();
			System.out.println(compositionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = compositionGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([1], size=1)", list.get(0)
				.toString());

	}

	@Test
	public void simpleTwoComposition() {

		Generator<Integer> compositionGenerator = Factory
				.createCompositionGenerator(2);
		Iterator<ICombinatoricsVector<Integer>> compositionIterator = compositionGenerator
				.createIterator();

		System.out.println("Number of compositions is: "
				+ compositionGenerator.getNumberOfGeneratedObjects());
		assertEquals(2, compositionGenerator.getNumberOfGeneratedObjects());

		while (compositionIterator.hasNext()) {
			compositionIterator.next();
			System.out.println(compositionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = compositionGenerator
				.generateAllObjects();

		assertEquals(2, list.size());

		assertEquals("CombinatoricsVector=([2], size=1)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([1, 1], size=2)", list.get(1)
				.toString());

	}

	@Test
	public void simpleZeroComposition() {

		Generator<Integer> compositionGenerator = Factory
				.createCompositionGenerator(0);
		Iterator<ICombinatoricsVector<Integer>> compositionIterator = compositionGenerator
				.createIterator();

		System.out.println("Number of compositions is: "
				+ compositionGenerator.getNumberOfGeneratedObjects());
		assertEquals(1, compositionGenerator.getNumberOfGeneratedObjects());

		while (compositionIterator.hasNext()) {
			compositionIterator.next();
			System.out.println(compositionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = compositionGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([0], size=1)", list.get(0)
				.toString());

	}
}
