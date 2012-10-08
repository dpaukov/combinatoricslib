import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.IFilter;
import org.paukov.combinatorics.IntegerFactory;
import org.paukov.combinatorics.IntegerGenerator;
import org.paukov.combinatorics.IntegerVector;
import org.paukov.combinatorics.util.ComplexCombinationGenerator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * Examples
		 */
		permutationWithoutRepetitions();
		permutationWithRepetitions();

		simpleCombinations();
		multiCombinations();

		simpleSubSets();
		duplicateSubSets();

		integerPartition();

		integerComposition();

		complexCombinationIndexesExample();
		complexCombinationExample();

		simpleWithEqualElementsPermutation();

		/**
		 * Examples of the integer vectors
		 */
		simpleFiveIntegerCompositionRange();

		/**
		 * Filters
		 */
		filteredCombinations();

	}

	static void simpleCombinations() {

		System.out.println("simpleCombinations");

		// Create the initial vector
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "red", "black", "white", "green",
						"blue" });

		// Create a simple combination generator to generate 3-combinations of
		// the initial vector
		Generator<String> gen = Factory.createSimpleCombinationGenerator(
				initialVector, 3);

		// Print all possible combinations
		for (ICombinatoricsVector<String> combination : gen) {
			System.out.println(combination);
		}

	}

	static void multiCombinations() {

		System.out.println("multiCombinations");

		// Create the initial vector of (apple, orange)
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "apple", "orange" });

		// Create a multi-combination generator to generate 3-combinations of
		// the initial vector
		Generator<String> gen = Factory.createMultiCombinationGenerator(
				initialVector, 3);

		// Print all possible combinations
		for (ICombinatoricsVector<String> combination : gen) {
			System.out.println(combination);
		}

	}

	static void permutationWithRepetitions() {

		System.out.println("permutationWithRepetitions");

		// Create the initial set/vector of 2 elements (apple, orange)
		ICombinatoricsVector<String> originalVector = Factory
				.createVector(new String[] { "apple", "orange" });

		// Create the generator by calling the appropriate method in the Factory
		// class
		Generator<String> gen = Factory
				.createPermutationWithRepetitionGenerator(originalVector, 3);

		// Print the result
		for (ICombinatoricsVector<String> perm : gen)
			System.out.println(perm);

	}

	static void permutationWithoutRepetitions() {

		System.out.println("permutationWithoutRepetitions");

		// Create the initial set/vector of 3 elements (apple, orange, cherry)
		ICombinatoricsVector<String> originalVector = Factory
				.createVector(new String[] { "apple", "orange", "cherry" });

		// Create the permutation generator by calling the appropriate method in
		// the Factory class
		Generator<String> gen = Factory
				.createPermutationGenerator(originalVector);

		// Print the result
		for (ICombinatoricsVector<String> perm : gen)
			System.out.println(perm);

	}

	static void complexCombinationIndexesExample() {

		System.out.println("complexCombinationIndexesExample");

		// A list of elements
		String[] elements = new String[] { "A", "B", "B", "C" };

		// create a combinatorics vector of indexes (1, 1, 3)
		ICombinatoricsVector<Integer> indixesVector = Factory
				.createVector(new Integer[] { 1, 1, 3 });

		// Create a complex-combination generator
		Generator<ICombinatoricsVector<Integer>> complexGenerator = new ComplexCombinationGenerator<Integer>(
				indixesVector, 2);

		// Iterate the elements
		for (ICombinatoricsVector<ICombinatoricsVector<Integer>> combination : complexGenerator) {

			String str = ComplexCombinationGenerator.convertIndexes2String(
					elements, combination);

			System.out.println(str);
		}
	}

	static void complexCombinationExample() {

		System.out.println("complexCombinationExample");

		// create a combinatorics vector (A, B, B, C)
		ICombinatoricsVector<String> vector = Factory
				.createVector(new String[] { "A", "B", "B", "C" });

		// Create a complex-combination generator
		Generator<ICombinatoricsVector<String>> gen = new ComplexCombinationGenerator<String>(
				vector, 2);

		// Iterate the combinations
		for (ICombinatoricsVector<ICombinatoricsVector<String>> comb : gen) {
			System.out.println(ComplexCombinationGenerator.convert2String(comb)
					+ " - " + comb);
		}
	}

	static void simpleSubSets() {

		System.out.println("simpleSubSets");

		// Create an initial vector/set
		ICombinatoricsVector<String> initialSet = Factory
				.createVector(new String[] { "one", "two", "three" });

		// Create an instance of the subset generator
		Generator<String> gen = Factory.createSubSetGenerator(initialSet);

		// Print the subsets
		for (ICombinatoricsVector<String> subSet : gen) {
			System.out.println(subSet);
		}
	}

	static void duplicateSubSets() {

		System.out.println("duplicateSubSets");

		// Create an initial vector/set (a, b, a, c)
		ICombinatoricsVector<String> initialSet = Factory
				.createVector(new String[] { "a", "b", "a", "c" });

		// Create an instance of the subset generator
		Generator<String> gen = Factory
				.createSubSetGenerator(initialSet, false);

		// Print the subsets
		for (ICombinatoricsVector<String> subSet : gen) {
			System.out.println(subSet);
		}
	}

	static void integerPartition() {

		System.out.println("integerPartition");

		// Create an instance of the partition generator to generate all
		// possible partitions of 5
		Generator<Integer> gen = Factory.createPartitionGenerator(5);

		// Print the partitions
		for (ICombinatoricsVector<Integer> p : gen) {
			System.out.println(p);
		}
	}

	static void integerComposition() {

		System.out.println("integerComposition");

		// Create an instance of the integer composition generator to generate
		// all
		// possible compositions of 5
		Generator<Integer> gen = Factory.createCompositionGenerator(5);

		// Print the compositions
		for (ICombinatoricsVector<Integer> p : gen) {
			System.out.println(p);
		}
	}

	static void simpleWithEqualElementsPermutation() {

		System.out.println("simpleWithEqualElementsPermutation");

		// Create the initial vector
		ICombinatoricsVector<Integer> initialVector = Factory
				.createVector(new Integer[] { 1, 1, 2, 2 });

		// Create the generator
		Generator<Integer> generator = Factory
				.createPermutationGenerator(initialVector);

		for (ICombinatoricsVector<Integer> perm : generator) {
			System.out.println(perm);
		}
	}

	static void simpleFiveIntegerCompositionRange() {

		System.out.println("simpleFiveIntegerCompositionRange");

		IntegerGenerator compositionGenerator = IntegerFactory
				.createIntegerCompositionGenerator(5);

		List<IntegerVector> list = compositionGenerator.generateObjectsRange(5,
				7);

		for (IntegerVector vector : list) {
			System.out.println(vector);
		}
	}

	/**
	 * Print all 3-combinations of the set (apple, orange, cherry, melon) which
	 * contains orange
	 */
	static void filteredCombinations() {

		System.out.println("filteredCombinations");

		// Create the initial set/vector of 3 elements (apple, orange, cherry,
		// melon)
		ICombinatoricsVector<String> originalVector = Factory
				.createVector(new String[] { "apple", "orange", "cherry",
						"melon" });

		// Create the combination generator by calling the appropriate method in
		// the Factory class
		Generator<String> gen = Factory.createSimpleCombinationGenerator(
				originalVector, 3);

		// Create a filter and generate the results
		List<ICombinatoricsVector<String>> result = gen
				.generateFilteredObjects(new IFilter<ICombinatoricsVector<String>>() {

					// returns true if the value is accepted
					public boolean accepted(long index,
							ICombinatoricsVector<String> value) {
						return value.contains("orange");
					}

				});

		// Print the result
		for (ICombinatoricsVector<String> perm : result)
			System.out.println(perm);

	}

}
