package org.paukov.combinatorics;

import java.util.Collection;

import org.paukov.combinatorics.combination.multi.MultiCombinationGenerator;
import org.paukov.combinatorics.combination.simple.SimpleCombinationGenerator;
import org.paukov.combinatorics.composition.CompositionGenerator;
import org.paukov.combinatorics.partition.PartitionGenerator;
import org.paukov.combinatorics.permutations.PermutationGenerator;
import org.paukov.combinatorics.permutations.PermutationWithRepetitionGenerator;
import org.paukov.combinatorics.subsets.SubSetGenerator;

public class Factory {

	/**
	 * Creates an empty vector instance
	 * @return The empty vector
	 */
	public static <T> ICombinatoricsVector<T> createVector() {
		return new CombinatoricsVector<T>();
	}

	/**
	 * Creates a combinatorics vector from an array
	 * @param array The array
	 * @return The combinatorics vector
	 */
	public static <T> ICombinatoricsVector<T> createVector(T[] array) {
		return new CombinatoricsVector<T>(array);
	}

	/**
	 * Creates a combinatorics vector from a collection 
	 * @param collection The collection
	 * @return The combinatorics vector
	 */
	public static <T> ICombinatoricsVector<T> createVector(
			Collection<? extends T> collection) {
		return new CombinatoricsVector<T>(collection);
	}

	/**
	 * Creates a copy of the given combinatorics vector
	 * @param vector The combinatorics vector to be copied
	 * @return The copy of the given vector
	 */
	public static <T> ICombinatoricsVector<T> createVector(
			ICombinatoricsVector<T> vector) {
		return new CombinatoricsVector<T>(vector);
	}

	/**
	 * Creates a simple combination generator
	 *  
	 * @param originalVector
	 * @param combinationsLength
	 */
	public static <T> Generator<T> createSimpleCombinationGenerator(
			ICombinatoricsVector<T> originalVector, int combinationsLength) {
		return new SimpleCombinationGenerator<T>(originalVector,
				combinationsLength);
	}

	/**
	 * Creates a multi-combination generator
	 * 
	 * @param originalVector
	 * @param combinationsLength
	 */
	public static <T> Generator<T> createMultiCombinationGenerator(
			ICombinatoricsVector<T> originalVector, int combinationsLength) {
		return new MultiCombinationGenerator<T>(originalVector,
				combinationsLength);
	}

	/**
	 * Creates a permutation generator
	 * 
	 * @param originalVector
	 */
	public static <T> Generator<T> createPermutationGenerator(
			ICombinatoricsVector<T> originalVector) {
		return new PermutationGenerator<T>(originalVector);
	}

	/**
	 * Creates a permutation with repetitions generator
	 * 
	 * @param originalVector
	 * @param permutationLength
	 */
	public static <T> Generator<T> createPermutationWithRepetitionGenerator(
			ICombinatoricsVector<T> originalVector, int permutationLength) {
		return new PermutationWithRepetitionGenerator<T>(originalVector,
				permutationLength);
	}
	
	
	/**
	 * Creates a sub-set generator
	 * 
	 * @param originalVector
	 */
	public static <T> Generator<T> createSubSetGenerator(
			ICombinatoricsVector<T> originalVector) {
		return new SubSetGenerator<T>(originalVector);
	}
	
	
	/**
	 * Creates a composition generator
	 * @param n
	 */
	public static Generator<Integer> createCompositionGenerator(Integer n) {
		return new CompositionGenerator(n);
	} 
	
	
	/**
	 * Creates a partition generator
	 * 
	 * @param n
	 */
	public static Generator<Integer> createPartitionGenerator(Integer n) {
		return new PartitionGenerator(n);
	} 
}
