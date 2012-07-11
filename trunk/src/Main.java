import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.util.ComplexCombinationGenerator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// create a combinatorics vector (a, a, a, a)
		ICombinatoricsVector<String> initialVector = Factory
				.createVector(new String[] { "a", "a", "a", "a" });

		// Create a complex-combination generator
		Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				initialVector, 2);

		// Create a complex-combination iterator
		Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {

			ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
					.next();

			String str = ComplexCombinationGenerator
					.convert2String(combination);

			System.out.println(str);
		}
	}
}
