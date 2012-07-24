/**
 * 
 */
package org.paukov.combinatorics.partition;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.IFilter;

import static org.junit.Assert.*;

/**
 * @author Dmytro Paukov
 * 
 */
public class PartitionsTest {

	@Test
	public void simplePartition() {

		Generator<Integer> partitionGenerator = Factory
				.createPartitionGenerator(5);
		Iterator<ICombinatoricsVector<Integer>> partitionIterator = partitionGenerator
				.iterator();

		System.out.println("Number of partition is: "
				+ partitionGenerator.getNumberOfGeneratedObjects());
		assertEquals(8, partitionGenerator.getNumberOfGeneratedObjects());

		while (partitionIterator.hasNext()) {
			partitionIterator.next();
			System.out.println(partitionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = partitionGenerator
				.generateAllObjects();

		assertEquals(7, list.size());

		assertEquals("CombinatoricsVector=([1, 1, 1, 1, 1], size=5)",
				list.get(0).toString());
		assertEquals("CombinatoricsVector=([2, 1, 1, 1], size=4)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([2, 2, 1], size=3)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([3, 1, 1], size=3)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([3, 2], size=2)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([4, 1], size=2)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([5], size=1)", list.get(6)
				.toString());
	}

	@Test
	public void simpleTwoPartition() {

		Generator<Integer> partitionGenerator = Factory
				.createPartitionGenerator(2);
		Iterator<ICombinatoricsVector<Integer>> partitionIterator = partitionGenerator
				.iterator();

		System.out.println("Number of partition is: "
				+ partitionGenerator.getNumberOfGeneratedObjects());
		assertEquals(2, partitionGenerator.getNumberOfGeneratedObjects());

		while (partitionIterator.hasNext()) {
			partitionIterator.next();
			System.out.println(partitionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = partitionGenerator
				.generateAllObjects();

		assertEquals(2, list.size());

		assertEquals("CombinatoricsVector=([1, 1], size=2)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([2], size=1)", list.get(1)
				.toString());
	}

	@Test
	public void simpleOnePartition() {

		Generator<Integer> partitionGenerator = Factory
				.createPartitionGenerator(1);
		Iterator<ICombinatoricsVector<Integer>> partitionIterator = partitionGenerator
				.iterator();

		System.out.println("Number of partition is: "
				+ partitionGenerator.getNumberOfGeneratedObjects());
		assertEquals(1, partitionGenerator.getNumberOfGeneratedObjects());

		while (partitionIterator.hasNext()) {
			partitionIterator.next();
			System.out.println(partitionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = partitionGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals("CombinatoricsVector=([1], size=1)", list.get(0)
				.toString());
	}

	@Test
	public void simpleEmptyPartition() {

		Generator<Integer> partitionGenerator = Factory
				.createPartitionGenerator(0);
		Iterator<ICombinatoricsVector<Integer>> partitionIterator = partitionGenerator
				.iterator();

		System.out.println("Number of partition is: "
				+ partitionGenerator.getNumberOfGeneratedObjects());
		assertEquals(0, partitionGenerator.getNumberOfGeneratedObjects());

		while (partitionIterator.hasNext()) {
			partitionIterator.next();
			System.out.println(partitionIterator);
		}

		List<ICombinatoricsVector<Integer>> list = partitionGenerator
				.generateAllObjects();

		assertEquals(0, list.size());
	}

	@Test
	public void mostThreeIntegersPartition() {

		Generator<Integer> partitionGenerator = Factory
				.createPartitionGenerator(8);

		List<ICombinatoricsVector<Integer>> list = partitionGenerator
				.generateFilteredObjects(new IFilter<ICombinatoricsVector<Integer>>() {

					@Override
					public boolean accepted(long index,
							ICombinatoricsVector<Integer> value) {
						return value.getSize() <= 3;
					}
				});

		assertEquals(10, list.size());

		assertEquals("CombinatoricsVector=([3, 3, 2], size=3)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([4, 2, 2], size=3)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([4, 3, 1], size=3)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([4, 4], size=2)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([5, 2, 1], size=3)", list.get(4)
				.toString());
		assertEquals("CombinatoricsVector=([5, 3], size=2)", list.get(5)
				.toString());
		assertEquals("CombinatoricsVector=([6, 1, 1], size=3)", list.get(6)
				.toString());
		assertEquals("CombinatoricsVector=([6, 2], size=2)", list.get(7)
				.toString());
		assertEquals("CombinatoricsVector=([7, 1], size=2)", list.get(8)
				.toString());
		assertEquals("CombinatoricsVector=([8], size=1)", list.get(9)
				.toString());
	}

	@Test
	public void exactlyThreeIntegersPartition() {

		Generator<Integer> partitionGenerator = Factory
				.createPartitionGenerator(8);

		List<ICombinatoricsVector<Integer>> list = partitionGenerator
				.generateFilteredObjects(new IFilter<ICombinatoricsVector<Integer>>() {

					@Override
					public boolean accepted(long index,
							ICombinatoricsVector<Integer> value) {
						return value.getSize() == 3;
					}
				});

		assertEquals(5, list.size());

		assertEquals("CombinatoricsVector=([3, 3, 2], size=3)", list.get(0)
				.toString());
		assertEquals("CombinatoricsVector=([4, 2, 2], size=3)", list.get(1)
				.toString());
		assertEquals("CombinatoricsVector=([4, 3, 1], size=3)", list.get(2)
				.toString());
		assertEquals("CombinatoricsVector=([5, 2, 1], size=3)", list.get(3)
				.toString());
		assertEquals("CombinatoricsVector=([6, 1, 1], size=3)", list.get(4)
				.toString());
	}
}
