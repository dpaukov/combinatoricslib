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

		complexCombinationExample();
	}

	static void simpleCombinations() {

		System.out.println("simpleCombinations");

		// Create the initial vector
		ICombinatoricsVector<String> initialVector = Factory.createVector(
				new String[] { "red", "black", "white", "green", "blue" } );

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
		ICombinatoricsVector<String> initialVector = Factory.createVector(
				new String[] { "apple", "orange" } );

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

}
