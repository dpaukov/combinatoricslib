/**
 * 
 */
package org.paukov.combinatorics.partition;

import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.partition.PartitionGenerator;

import static org.junit.Assert.*;


/**
 * @author Dmytro Paukov
 * 
 */
public class PartitionsTest {

	@Test
	public void simplePartition() {

		Generator<Integer> partitionGenerator = new PartitionGenerator(5);
		Iterator<ICombinatoricsVector<Integer>> partitionIterator = partitionGenerator
				.createIterator();

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

		assertEquals("CombinatoricsVector=([1, 1, 1, 1, 1], size=5)", list
				.get(0).toString());
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

		Generator<Integer> partitionGenerator = new PartitionGenerator(2);
		Iterator<ICombinatoricsVector<Integer>> partitionIterator = partitionGenerator
				.createIterator();

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

		Generator<Integer> partitionGenerator = new PartitionGenerator(1);
		Iterator<ICombinatoricsVector<Integer>> partitionIterator = partitionGenerator
				.createIterator();

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

		Generator<Integer> partitionGenerator = new PartitionGenerator(0);
		Iterator<ICombinatoricsVector<Integer>> partitionIterator = partitionGenerator
				.createIterator();

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
}
