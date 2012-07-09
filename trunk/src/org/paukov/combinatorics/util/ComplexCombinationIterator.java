package org.paukov.combinatorics.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.combination.multi.MultiCombinationGenerator;
import org.paukov.combinatorics.permutations.PermutationGenerator;
import org.paukov.combinatorics.subsets.SubSetGenerator;

/**
 * @author Dmytro Paukov
 * 
 */
public class ComplexCombinationIterator<T>
		extends
		org.paukov.combinatorics.Iterator<CombinatoricsVector<CombinatoricsVector<T>>> {

	/**
	 * Generator
	 */
	protected final ComplexCombinationGenerator<T> _generator;

	/**
	 * Current combination
	 */
	protected CombinatoricsVector<CombinatoricsVector<T>> _currentComplexCombination = null;

	/**
	 * Current index of the combination
	 */
	protected long _currentIndex = 0;

	/**
	 * The set of the generated combinations
	 */
	protected Set<CombinatoricsVector<CombinatoricsVector<T>>> _resultSet = new LinkedHashSet<CombinatoricsVector<CombinatoricsVector<T>>>();

	/**
	 * The iterator over the result combinations
	 */
	protected Iterator<CombinatoricsVector<CombinatoricsVector<T>>> _resultIterator = null;

	/**
	 * Constructor
	 * 
	 * @param generator
	 *            Generator of the complex combinations
	 */
	public ComplexCombinationIterator(ComplexCombinationGenerator<T> generator) {
		_generator = generator;
		init();
	}

	/**
	 * Initialization of the iteration process
	 */
	private void init() {

		// 1. Generate all permutations from the original list
		Generator<T> permutationGenerator = new PermutationGenerator<T>(
				_generator.getCoreObject().getValue(0));
		List<CombinatoricsVector<T>> permutationsList = permutationGenerator
				.generateAllObjects();

		// 2. Generate all subsets of the original list
		Generator<T> subSetGenerator = new SubSetGenerator<T>(_generator
				.getCoreObject().getValue(0));
		List<CombinatoricsVector<T>> allSubsetsList = subSetGenerator
				.generateAllObjects();

		// If empty set has to be excluded, remove it from the sub sets
		if (_generator.excludeEmptySet())
			allSubsetsList.remove(new CombinatoricsVector<T>());

		// 3.1 Create a vector of all subsets
		CombinatoricsVector<CombinatoricsVector<T>> allSubsetsVector = new CombinatoricsVector<CombinatoricsVector<T>>(
				allSubsetsList);

		// 3.2 Create a simple generator to get all n-combination of the subsets
		Generator<CombinatoricsVector<T>> combinationGenerator = new MultiCombinationGenerator<CombinatoricsVector<T>>(
				allSubsetsVector, _generator.getCombinationLength());

		// 3.3 Get an iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<T>>> combinationIterator = combinationGenerator
				.createIterator();

		// 3.4 Create an intermediate combinations list to store the candidates
		List<CombinatoricsVector<CombinatoricsVector<T>>> intermediateCombinations = new ArrayList<CombinatoricsVector<CombinatoricsVector<T>>>();

		// 3.5 Search the combinations which are contained in the permutations
		while (combinationIterator.hasNext()) {

			// 3.5.1 Get a combination
			CombinatoricsVector<CombinatoricsVector<T>> combination = combinationIterator
					.next();

			/**
			 * 3.5.2 Construct a list/combinatorial vector of all elements from
			 * the combination.
			 */

			// 3.5.2.1 Put the first element into the list
			List<T> list = new ArrayList<T>();
			if (combination.getSize() > 0)
				list.addAll(combination.getValue(0).getVector());

			// 3.5.2.2 Add all other elements into the list
			for (int index = 1; index < combination.getSize(); index++)
				list.addAll(combination.getValue(index).getVector());

			// 3.5.2.3 Create a vector which contains all elements from the
			// combination
			CombinatoricsVector<T> vector = new CombinatoricsVector<T>(list);

			// 3.5.3 Add the combination into the intermediate result if the
			// constructed vector exists in the permutations list
			if (permutationsList.contains(vector)) {
				CombinatoricsVector<CombinatoricsVector<T>> intermediateCombination = new CombinatoricsVector<CombinatoricsVector<T>>(
						combination);

				if (!intermediateCombinations.contains(intermediateCombination))
					intermediateCombinations.add(intermediateCombination);
			}
		}

		// If order is not important, the intermediate combinations are the
		// final result
		// otherwise we need generate permutations
		if (!_generator.isOrderImportant()) {
			_resultSet.addAll(intermediateCombinations);
		} else {

			// 4. Generate permutations for each found combination and add them
			// into the final result list
			for (CombinatoricsVector<CombinatoricsVector<T>> combination : intermediateCombinations) {

				// 4.1 Create a permutation generator for each intermediate
				// combination
				Generator<CombinatoricsVector<T>> permutationGen = new PermutationGenerator<CombinatoricsVector<T>>(
						new CombinatoricsVector<CombinatoricsVector<T>>(
								combination));

				// 4.2 Generate all permutations
				List<CombinatoricsVector<CombinatoricsVector<T>>> permutations = permutationGen
						.generateAllObjects();

				// 4.3 Add then into the final result list
				_resultSet.addAll(permutations);
			}
		}

		_resultIterator = _resultSet.iterator();
		_currentIndex = 0;
	}

	/**
	 * Returns the current combination
	 */
	@Override
	public CombinatoricsVector<CombinatoricsVector<T>> getCurrentItem() {
		return _currentComplexCombination;
	}

	/**
	 * Returns true if all combinations were iterated, otherwise false
	 */
	@Override
	public boolean isDone() {
		return !_resultIterator.hasNext();
	}

	/**
	 * Moves to the next combination
	 */
	@Override
	public CombinatoricsVector<CombinatoricsVector<T>> next() {
		_currentIndex++;

		_currentComplexCombination = _resultIterator.next();

		return getCurrentItem();
	}

	/**
	 * Returns the current combination as a string
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComplexeCombinationIterator=[#" + _currentIndex + ", "
				+ _currentComplexCombination + "]";
	}

}