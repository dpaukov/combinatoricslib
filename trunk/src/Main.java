import static org.junit.Assert.assertEquals;

import java.util.Iterator;

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

		complexCombinationExample();
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
