/**
 * 
 */
package org.paukov.combinatorics.composition;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import org.paukov.combinatorics.IGenerator;
import org.paukov.combinatorics.IntegerFactory;
import org.paukov.combinatorics.IntegerGenerator;
import org.paukov.combinatorics.IntegerVector;

/**
 * @author Dmytro.Paukov
 * 
 */
public class IntegerCompositionTest {

	@Test
	public void simpleFiveComposition() {

		IGenerator<IntegerVector> compositionGenerator = IntegerFactory
				.createIntegerCompositionGenerator(5);
		Iterator<IntegerVector> compositionIterator = compositionGenerator
				.iterator();

		System.out.println("Number of compositions is: "
				+ compositionGenerator.getNumberOfGeneratedObjects());
		assertEquals(16, compositionGenerator.getNumberOfGeneratedObjects());

		while (compositionIterator.hasNext()) {
			compositionIterator.next();
			System.out.println(compositionIterator);
		}

		List<IntegerVector> list = compositionGenerator.generateAllObjects();

		assertEquals(16, list.size());

		assertEquals("IntegerVector=([5], size=1)", list.get(0).toString());
		assertEquals("IntegerVector=([1, 4], size=2)", list.get(1).toString());
		assertEquals("IntegerVector=([2, 3], size=2)", list.get(2).toString());
		assertEquals("IntegerVector=([1, 1, 3], size=3)", list.get(3)
				.toString());
		assertEquals("IntegerVector=([3, 2], size=2)", list.get(4).toString());
		assertEquals("IntegerVector=([1, 2, 2], size=3)", list.get(5)
				.toString());
		assertEquals("IntegerVector=([2, 1, 2], size=3)", list.get(6)
				.toString());

		assertEquals("IntegerVector=([1, 1, 1, 2], size=4)", list.get(7)
				.toString());
		assertEquals("IntegerVector=([4, 1], size=2)", list.get(8).toString());
		assertEquals("IntegerVector=([1, 3, 1], size=3)", list.get(9)
				.toString());
		assertEquals("IntegerVector=([2, 2, 1], size=3)", list.get(10)
				.toString());
		assertEquals("IntegerVector=([1, 1, 2, 1], size=4)", list.get(11)
				.toString());
		assertEquals("IntegerVector=([3, 1, 1], size=3)", list.get(12)
				.toString());
		assertEquals("IntegerVector=([1, 2, 1, 1], size=4)", list.get(13)
				.toString());

		assertEquals("IntegerVector=([2, 1, 1, 1], size=4)", list.get(14)
				.toString());
		assertEquals("IntegerVector=([1, 1, 1, 1, 1], size=5)", list.get(15)
				.toString());
	}

	@Test
	public void simpleFiveCompositionRange() {

		IntegerGenerator compositionGenerator = IntegerFactory
				.createIntegerCompositionGenerator(5);

		System.out.println("Number of compositions is: "
				+ compositionGenerator.getNumberOfGeneratedObjects());
		assertEquals(16, compositionGenerator.getNumberOfGeneratedObjects());

		List<IntegerVector> list = compositionGenerator.generateObjectsRange(5,
				7);

		assertEquals(3, list.size());

		assertEquals("IntegerVector=([3, 2], size=2)", list.get(0).toString());
		assertEquals("IntegerVector=([1, 2, 2], size=3)", list.get(1)
				.toString());
		assertEquals("IntegerVector=([2, 1, 2], size=3)", list.get(2)
				.toString());
	}

	@Test
	public void simpleOneComposition() {

		IntegerGenerator compositionGenerator = IntegerFactory
				.createIntegerCompositionGenerator(1);
		Iterator<IntegerVector> compositionIterator = compositionGenerator
				.iterator();

		System.out.println("Number of compositions is: "
				+ compositionGenerator.getNumberOfGeneratedObjects());
		assertEquals(1, compositionGenerator.getNumberOfGeneratedObjects());

		while (compositionIterator.hasNext()) {
			compositionIterator.next();
			System.out.println(compositionIterator);
		}

		List<IntegerVector> list = compositionGenerator.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("IntegerVector=([1], size=1)", list.get(0).toString());

	}

	@Test
	public void simpleTwoComposition() {

		IntegerGenerator compositionGenerator = IntegerFactory
				.createIntegerCompositionGenerator(2);
		Iterator<IntegerVector> compositionIterator = compositionGenerator
				.iterator();

		System.out.println("Number of compositions is: "
				+ compositionGenerator.getNumberOfGeneratedObjects());
		assertEquals(2, compositionGenerator.getNumberOfGeneratedObjects());

		while (compositionIterator.hasNext()) {
			compositionIterator.next();
			System.out.println(compositionIterator);
		}

		List<IntegerVector> list = compositionGenerator.generateAllObjects();

		assertEquals(2, list.size());

		assertEquals("IntegerVector=([2], size=1)", list.get(0).toString());
		assertEquals("IntegerVector=([1, 1], size=2)", list.get(1).toString());

	}

	@Test
	public void simpleZeroComposition() {

		IntegerGenerator compositionGenerator = IntegerFactory
				.createIntegerCompositionGenerator(0);
		Iterator<IntegerVector> compositionIterator = compositionGenerator
				.iterator();

		System.out.println("Number of compositions is: "
				+ compositionGenerator.getNumberOfGeneratedObjects());
		assertEquals(1, compositionGenerator.getNumberOfGeneratedObjects());

		while (compositionIterator.hasNext()) {
			compositionIterator.next();
			System.out.println(compositionIterator);
		}

		List<IntegerVector> list = compositionGenerator.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("IntegerVector=([0], size=1)", list.get(0).toString());

	}
}
