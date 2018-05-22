/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.examples;

import static org.paukov.combinatorics.CombinatoricsFactory.createCompositionGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createMultiCombinationGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createPartitionGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createPermutationGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createPermutationWithRepetitionGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createSimpleCombinationGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createSubSetGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;
import static org.paukov.combinatorics.IntegerFactory.createIntegerCompositionGenerator;

import java.util.List;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.IFilter;
import org.paukov.combinatorics.IntegerGenerator;
import org.paukov.combinatorics.IntegerVector;
import org.paukov.combinatorics.util.ComplexCombinationGenerator;

/**
 * This class contains various examples of using the combinatoricslib
 *
 * @author Dmytro Paukov
 */
public class Main {

  public static void main(String[] args) {

    /**
     * Permutations
     */
    permutation_without_repetitions();
    permutation_with_repetitions();

    /**
     * Combinations
     */
    simple_combinations();
    multi_combinations();

    /**
     * Subsets
     */
    simple_subsets();
    duplicate_subsets();

    /**
     * Integer partitions and compositions
     */
    integer_partition();
    integer_composition();

    /**
     * Complex combination (List partitions)
     */
    complex_combination_indexes_example();
    complex_combination_example();

    simple_with_equal_elements_permutation();

    /**
     * Examples of the integer vectors
     */
    simple_five_integer_composition_range();

    /**
     * Filters
     */
    filtered_combinations();

    /**
     * Complex examples
     */
    all_permutations_of_all_combination();

  }

  static void simple_combinations() {

    System.out.println("===== Simple Combinations: =====");

    // Create the initial vector
    ICombinatoricsVector<String> initialVector = createVector("red", "black", "white", "green",
        "blue");

    // Create a simple combination generator to generate 3-combinations of
    // the initial vector
    Generator<String> gen = createSimpleCombinationGenerator(initialVector, 3);

    // Print all possible combinations
    for (ICombinatoricsVector<String> combination : gen) {
      System.out.println(combination);
    }

  }

  static void multi_combinations() {

    System.out.println("===== Multi Combinations: =====");

    // Create the initial vector of (apple, orange)
    ICombinatoricsVector<String> initialVector = createVector("apple", "orange");

    // Create a multi-combination generator to generate 3-combinations of
    // the initial vector
    Generator<String> gen = createMultiCombinationGenerator(initialVector, 3);

    // Print all possible combinations
    for (ICombinatoricsVector<String> combination : gen) {
      System.out.println(combination);
    }

  }

  static void permutation_with_repetitions() {

    System.out.println("===== Permutation With Repetitions: =====");

    // Create the initial set/vector of 2 elements (apple, orange)
    ICombinatoricsVector<String> originalVector = createVector("apple", "orange");

    // Create the generator by calling the appropriate method in the Factory
    // class
    Generator<String> gen = createPermutationWithRepetitionGenerator(originalVector, 3);

    // Print the result
    for (ICombinatoricsVector<String> perm : gen) {
      System.out.println(perm);
    }

  }

  static void permutation_without_repetitions() {

    System.out.println("===== Permutations Without Repetitions: =====");

    // Create the initial set/vector of 3 elements (apple, orange, cherry)
    ICombinatoricsVector<String> originalVector = createVector("apple", "orange", "cherry");

    // Create the permutation generator by calling the appropriate method in
    // the Factory class
    Generator<String> gen = createPermutationGenerator(originalVector);

    // Print the result
    for (ICombinatoricsVector<String> perm : gen) {
      System.out.println(perm);
    }

  }

  static void complex_combination_indexes_example() {

    System.out.println("===== Complex Combination Indexes Example (List partitions): =====");

    // A list of elements
    String[] elements = new String[]{"A", "B", "B", "C"};

    // create a combinatorics vector of indexes (1, 1, 3)
    ICombinatoricsVector<Integer> indixesVector = createVector(1, 1, 3);

    // Create a complex-combination generator
    Generator<ICombinatoricsVector<Integer>> complexGenerator = new ComplexCombinationGenerator<>(
        indixesVector, 2);

    // Iterate the elements
    for (ICombinatoricsVector<ICombinatoricsVector<Integer>> combination : complexGenerator) {

      String str = ComplexCombinationGenerator.convertIndexes2String(
          elements, combination);

      System.out.println(str);
    }
  }

  static void complex_combination_example() {

    System.out.println("complexCombinationExample");
    System.out.println("===== Complex Combination Example: =====");

    // create a combinatorics vector (A, B, B, C)
    ICombinatoricsVector<String> vector = createVector("A", "B", "B", "C");

    // Create a complex-combination generator
    Generator<ICombinatoricsVector<String>> gen = new ComplexCombinationGenerator<>(vector, 2);

    // Iterate the combinations
    for (ICombinatoricsVector<ICombinatoricsVector<String>> comb : gen) {
      System.out.println(ComplexCombinationGenerator.convert2String(comb)
          + " - " + comb);
    }
  }

  static void simple_subsets() {

    System.out.println("===== All subsets: =====");

    // Create an initial vector/set
    ICombinatoricsVector<String> initialSet = createVector("one", "two", "three");

    // Create an instance of the subset generator
    Generator<String> gen = createSubSetGenerator(initialSet);

    // Print the subsets
    for (ICombinatoricsVector<String> subSet : gen) {
      System.out.println(subSet);
    }
  }

  static void duplicate_subsets() {

    System.out.println("===== All subsets with duplicates: =====");

    // Create an initial vector/set (a, b, a, c)
    ICombinatoricsVector<String> initialSet = createVector("a", "b", "a", "c");

    // Create an instance of the subset generator
    Generator<String> gen = createSubSetGenerator(initialSet, false);

    // Print the subsets
    for (ICombinatoricsVector<String> subSet : gen) {
      System.out.println(subSet);
    }
  }

  static void integer_partition() {

    System.out.println("===== All integer partitions of 5: =====");

    // Create an instance of the partition generator to generate all
    // possible partitions of 5
    Generator<Integer> gen = createPartitionGenerator(5);

    // Print the partitions
    for (ICombinatoricsVector<Integer> p : gen) {
      System.out.println(p);
    }
  }

  static void integer_composition() {

    System.out.println("===== All integer combinations of 5: =====");

    // Create an instance of the integer composition generator to generate
    // all possible compositions of 5
    Generator<Integer> gen = createCompositionGenerator(5);

    // Print the compositions
    for (ICombinatoricsVector<Integer> p : gen) {
      System.out.println(p);
    }
  }

  static void simple_with_equal_elements_permutation() {

    System.out.println("===== Simple With Equal Elements Permutations: =====");

    // Create the initial vector
    ICombinatoricsVector<Integer> initialVector = createVector(1, 1, 2, 2);

    // Create the generator
    Generator<Integer> generator = createPermutationGenerator(initialVector);

    for (ICombinatoricsVector<Integer> perm : generator) {
      System.out.println(perm);
    }
  }

  static void simple_five_integer_composition_range() {

    System.out.println("===== Simple Five Integer Composition Range: =====");

    IntegerGenerator compositionGenerator = createIntegerCompositionGenerator(5);

    List<IntegerVector> list = compositionGenerator.generateObjectsRange(5, 7);

    for (IntegerVector vector : list) {
      System.out.println(vector);
    }
  }

  /**
   * Print all 3-combinations of the set (apple, orange, cherry, melon) which
   * contains orange
   */
  static void filtered_combinations() {

    System.out.println("===== Filtered Combinations: =====");

    // Create the initial set/vector of 3 elements (apple, orange, cherry,
    // melon)
    ICombinatoricsVector<String> originalVector = createVector("apple", "orange", "cherry",
        "melon");

    // Create the combination generator by calling the appropriate method in
    // the Factory class
    Generator<String> gen = createSimpleCombinationGenerator(originalVector, 3);

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
    for (ICombinatoricsVector<String> perm : result) {
      System.out.println(perm);
    }

  }

  /**
   * This example recreates the issue 14.
   * https://code.google.com/p/combinatoricslib/issues/detail?id=14
   *
   * It generates all permutations of a given length where repetition is NOT allowed,
   * from a larger set of elements.
   */
  static void all_permutations_of_all_combination() {

    System.out.println("===== All permutations of all combination: =====");

    // Create the initial vector of 4 elements (apple, orange, cherry, raspberry)
    ICombinatoricsVector<String> originalVector = createVector("apple", "orange", "cherry",
        "raspberry");

    // Create the combination generator by calling the appropriate method in the Factory class
    Generator<String> combinations = createSimpleCombinationGenerator(originalVector, 3);

    // Print all permutations for all simple 3-combinations
    for (ICombinatoricsVector<String> comb : combinations) {
      Generator<String> permutations = createPermutationGenerator(comb);
      for (ICombinatoricsVector<String> perm : permutations) {
        System.out.println(perm);
      }
    }
  }

}
