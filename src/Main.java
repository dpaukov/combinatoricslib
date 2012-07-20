import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.util.ComplexCombinationGenerator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// A list of elements
		String[] elements = new String[] { "A", "B", "B", "C" };
		
		// create a combinatorics vector of indexes  (1, 1, 3)
		ICombinatoricsVector<Integer> indixesVector = Factory
				.createVector(new Integer[] { 1, 1, 3 });

		// Create a complex-combination generator
		Generator<ICombinatoricsVector<Integer>> complexGenerator = new ComplexCombinationGenerator<Integer>(
				indixesVector, 2 );

		// Iterate the elements
		for (ICombinatoricsVector<ICombinatoricsVector<Integer>> combination : complexGenerator) {

			String str = ComplexCombinationGenerator
					.convertIndexes2String(elements, combination);

			System.out.println(str);
		}
	}
}
