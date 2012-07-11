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
	 * Creating combinatorics vectors
	 */

	public static <T> ICombinatoricsVector<T> createVector() {
		return new CombinatoricsVector<T>();
	}

	public static <T> ICombinatoricsVector<T> createVector(T[] array) {
		return new CombinatoricsVector<T>(array);
	}

	public static <T> ICombinatoricsVector<T> createVector(
			Collection<? extends T> collection) {
		return new CombinatoricsVector<T>(collection);
	}

	public static <T> ICombinatoricsVector<T> createVector(
			ICombinatoricsVector<T> vector) {
		return new CombinatoricsVector<T>(vector);
	}

	/**
	 * Creating generators
	 */

	public static <T> Generator<T> createSimpleCombinationGenerator(
			ICombinatoricsVector<T> originalVector, int combinationsLength) {
		return new SimpleCombinationGenerator<T>(originalVector,
				combinationsLength);
	}

	public static <T> Generator<T> createMultiCombinationGenerator(
			ICombinatoricsVector<T> originalVector, int combinationsLength) {
		return new MultiCombinationGenerator<T>(originalVector,
				combinationsLength);
	}

	public static <T> Generator<T> createPermutationGenerator(
			ICombinatoricsVector<T> originalVector) {
		return new PermutationGenerator<T>(originalVector);
	}

	public static <T> Generator<T> createPermutationWithRepetitionGenerator(
			ICombinatoricsVector<T> originalVector, int permutationLength) {
		return new PermutationWithRepetitionGenerator<T>(originalVector,
				permutationLength);
	}
	
	public static <T> Generator<T> createSubSetGenerator(
			ICombinatoricsVector<T> originalVector) {
		return new SubSetGenerator<T>(originalVector);
	}
	
	public static Generator<Integer> createCompositionGenerator(Integer n) {
		return new CompositionGenerator(n);
	} 
	
	public static Generator<Integer> createPartitionGenerator(Integer n) {
		return new PartitionGenerator(n);
	} 
}
