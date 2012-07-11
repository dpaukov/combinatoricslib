package org.paukov.combinatorics.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * @author Dmytro Paukov
 * 
 */
public class ComplexCombinationIterator<T>
		extends
		org.paukov.combinatorics.Iterator<ICombinatoricsVector<ICombinatoricsVector<T>>> {

	/**
	 * Generator
	 */
	protected final ComplexCombinationGenerator<T> _generator;

	/**
	 * Current combination
	 */
	protected ICombinatoricsVector<ICombinatoricsVector<T>> _currentComplexCombination = null;

	/**
	 * Current index of the combination
	 */
	protected long _currentIndex = 0;

	/**
	 * The set of the generated combinations
	 */
	protected Set<ICombinatoricsVector<ICombinatoricsVector<T>>> _resultSet = new LinkedHashSet<ICombinatoricsVector<ICombinatoricsVector<T>>>();

	/**
	 * The iterator over the result combinations
	 */
	protected Iterator<ICombinatoricsVector<ICombinatoricsVector<T>>> _resultIterator = null;

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
		Generator<T> permutationGenerator = Factory
				.createPermutationGenerator(_generator.getOriginalVector()
						.getValue(0));
		List<ICombinatoricsVector<T>> permutationsList = permutationGenerator
				.generateAllObjects();

		// 2. Generate all subsets of the original list
		Generator<T> subSetGenerator = Factory.createSubSetGenerator(_generator
				.getOriginalVector().getValue(0));
		List<ICombinatoricsVector<T>> allSubsetsList = subSetGenerator
				.generateAllObjects();

		// If empty set has to be excluded, remove it from the sub sets
		if (_generator.excludeEmptySet())
			allSubsetsList.remove(Factory.<T> createVector());

		// 3.1 Create a vector of all subsets
		ICombinatoricsVector<ICombinatoricsVector<T>> allSubsetsVector = Factory
				.createVector(allSubsetsList);

		// 3.2 Create a simple generator to get all n-combination of the subsets
		Generator<ICombinatoricsVector<T>> combinationGenerator = Factory
				.createMultiCombinationGenerator(allSubsetsVector,
						_generator.getCombinationLength());

		// 3.3 Get an iterator
		Iterator<ICombinatoricsVector<ICombinatoricsVector<T>>> combinationIterator = combinationGenerator
				.createIterator();

		// 3.4 Create an intermediate combinations list to store the candidates
		List<ICombinatoricsVector<ICombinatoricsVector<T>>> intermediateCombinations = new ArrayList<ICombinatoricsVector<ICombinatoricsVector<T>>>();

		// 3.5 Search the combinations which are contained in the permutations
		while (combinationIterator.hasNext()) {

			// 3.5.1 Get a combination
			ICombinatoricsVector<ICombinatoricsVector<T>> combination = combinationIterator
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
			ICombinatoricsVector<T> vector = Factory.createVector(list);

			// 3.5.3 Add the combination into the intermediate result if the
			// constructed vector exists in the permutations list
			if (permutationsList.contains(vector)) {

				if (!intermediateCombinations.contains(combination))
					intermediateCombinations.add(combination);
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
			for (ICombinatoricsVector<ICombinatoricsVector<T>> combination : intermediateCombinations) {

				// 4.1 Create a permutation generator for each intermediate
				// combination
				Generator<ICombinatoricsVector<T>> permutationGen = Factory
						.createPermutationGenerator(Factory
								.createVector(combination));

				// 4.2 Generate all permutations
				List<ICombinatoricsVector<ICombinatoricsVector<T>>> permutations = permutationGen
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
	public ICombinatoricsVector<ICombinatoricsVector<T>> getCurrentItem() {
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
	public ICombinatoricsVector<ICombinatoricsVector<T>> next() {
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