import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.util.ComplexCombinationGenerator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		permutationWithoutRepetitions();
		permutationWithRepetitions();

		simpleCombinations();
		multiCombinations();

		simpleSubSets();
		duplicateSubSets();

		integerPartition();
		
		integerComposition();

		complexCombinationExample();
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

	static void complexCombinationExample() {

		System.out.println("permutationWithoutRepetitions");

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

		// Create an instance of the integer composition generator to generate all
		// possible compositions of 5
		Generator<Integer> gen = Factory.createCompositionGenerator(5);

		// Print the compositions
		for (ICombinatoricsVector<Integer> p : gen) {
			System.out.println(p);
		}
	}

}
