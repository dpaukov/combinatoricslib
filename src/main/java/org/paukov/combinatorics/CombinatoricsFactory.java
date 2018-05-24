/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics;

import java.util.Collection;
import org.paukov.combinatorics.cartesian.CartesianProductGenerator;
import org.paukov.combinatorics.combination.multi.MultiCombinationGenerator;
import org.paukov.combinatorics.combination.simple.SimpleCombinationGenerator;
import org.paukov.combinatorics.composition.CompositionGenerator;
import org.paukov.combinatorics.partition.PartitionGenerator;
import org.paukov.combinatorics.permutations.PermutationGenerator;
import org.paukov.combinatorics.permutations.PermutationWithRepetitionGenerator;
import org.paukov.combinatorics.subsets.SubSetGenerator;

/**
 * This is a factory class to create combinatorics vectors and concrete generators.
 *
 * @author Dmytro Paukov
 * @version 2.0
 * @see ICombinatoricsVector
 * @see IGenerator
 */
public class CombinatoricsFactory {

  /**
   * Creates an empty vector instance.
   *
   * @return The empty vector.
   */
  public static <T> ICombinatoricsVector<T> createVector() {
    return new CombinatoricsVector<>();
  }

  /**
   * Creates a combinatorics vector from an array.
   *
   * @param array The array.
   * @return The combinatorics vector.
   */
  @SafeVarargs
  public static <T> ICombinatoricsVector<T> createVector(T... array) {
    return new CombinatoricsVector<T>(array);
  }

  /**
   * Creates a combinatorics vector from a collection.
   *
   * @param collection The collection.
   * @return The combinatorics vector.
   */
  public static <T> ICombinatoricsVector<T> createVector(
      Collection<? extends T> collection) {
    return new CombinatoricsVector<>(collection);
  }

  /**
   * Creates a copy of a given combinatorics vector.
   *
   * @param vector The combinatorics vector to be copied.
   * @return The copy of the given vector.
   */
  public static <T> ICombinatoricsVector<T> createVector(
      ICombinatoricsVector<T> vector) {
    return new CombinatoricsVector<>(vector);
  }

  /**
   * This method creates a combinatorics vector of (1, 2,.., n).
   *
   * @param n The number of the elements.
   * @return The vector.
   */
  public static ICombinatoricsVector<Integer> range(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Range value must be more or equal to 0");
    }

    // If range is zero return an empty vector
    if (n == 0) {
      return new CombinatoricsVector<>();
    }

    Integer[] array = new Integer[n];
    for (int i = 0; i < n; i++) {
      array[i] = i + 1;
    }

    return new CombinatoricsVector<>(array);
  }

  /**
   * This method creates a combinatorics vector of (from, from + 1,.., to-1,
   * to).
   *
   * @param from The first value.
   * @param to The second value.
   * @return The vector.
   */
  public static ICombinatoricsVector<Integer> range(int from, int to) {
    if (from > to) {
      throw new IllegalArgumentException("From parameter must be less then To parameter");
    }

    Integer[] array = new Integer[to - from + 1];
    for (int i = 0; i < to - from + 1; i++) {
      array[i] = i + from;
    }

    return new CombinatoricsVector<>(array);
  }

  /**
   * Creates a simple combination generator.
   *
   * @param originalVector The original vector.
   * @param combinationsLength The combinations length.
   * @return An instance of the simple combinations generator.
   * @see SimpleCombinationGenerator
   */
  public static <T> Generator<T> createSimpleCombinationGenerator(
      ICombinatoricsVector<T> originalVector, int combinationsLength) {
    return new SimpleCombinationGenerator<>(originalVector,
        combinationsLength);
  }

  /**
   * Creates a multi-combination generator.
   *
   * @param originalVector The original vector.
   * @param combinationsLength The combination length.
   * @return An instance of the multi-combinations generator.
   * @see MultiCombinationGenerator
   */
  public static <T> Generator<T> createMultiCombinationGenerator(
      ICombinatoricsVector<T> originalVector, int combinationsLength) {
    return new MultiCombinationGenerator<>(originalVector,
        combinationsLength);
  }

  /**
   * Creates a permutation generator.
   *
   * @param originalVector The original vector.
   * @return An instance of the simple permutation generator.
   * @see PermutationGenerator
   */
  public static <T> Generator<T> createPermutationGenerator(
      ICombinatoricsVector<T> originalVector) {
    return new PermutationGenerator<>(originalVector);
  }

  /**
   * Creates a permutation generator.
   *
   * @param originalVector The original vector.
   * @param treatAsIdentical True if the generator should treat the vector as identical.
   * @return An instance of the simple permutation generator.
   * @see PermutationGenerator
   */
  public static <T> Generator<T> createPermutationGenerator(
      ICombinatoricsVector<T> originalVector, boolean treatAsIdentical) {
    return new PermutationGenerator<>(originalVector, treatAsIdentical);
  }

  /**
   * Creates a permutation with repetitions generator.
   *
   * @param originalVector The original vector.
   * @param permutationLength The length of the generated permutations.
   * @return An instance of the generator.
   * @see PermutationWithRepetitionGenerator
   */
  public static <T> Generator<T> createPermutationWithRepetitionGenerator(
      ICombinatoricsVector<T> originalVector, int permutationLength) {
    return new PermutationWithRepetitionGenerator<>(originalVector,
        permutationLength);
  }

  /**
   * Creates a sub-set generator. This generator supports vector with
   * duplicates.
   *
   * @param originalVector The original vector/set.
   * @return An instance of the generator.
   * @see SubSetGenerator
   */
  public static <T> Generator<T> createSubSetGenerator(
      ICombinatoricsVector<T> originalVector) {
    return new SubSetGenerator<>(originalVector);
  }

  /**
   * Creates a sub-set generator.
   *
   * @param originalVector The initial vector.
   * @param treatAsIdentical <code>true</code> if the sub sets have to be treated as identical.
   * @return An instance of the generator.
   * @see SubSetGenerator
   */
  public static <T> Generator<T> createSubSetGenerator(
      ICombinatoricsVector<T> originalVector, boolean treatAsIdentical) {
    return new SubSetGenerator<>(originalVector, treatAsIdentical);
  }

  /**
   * Creates a composition generator of the given integer value <code>n</code>.
   *
   * @param n The initial value.
   * @return An instance of the generator.
   * @see CompositionGenerator
   */
  public static Generator<Integer> createCompositionGenerator(Integer n) {
    return new CompositionGenerator(n);
  }

  /**
   * Creates a partition generator of the given integer value <code>n</code>.
   *
   * @param n The initial value.
   * @return An instance of the generator.
   * @see PartitionGenerator
   */
  public static Generator<Integer> createPartitionGenerator(Integer n) {
    return new PartitionGenerator(n);
  }

  /**
   * Creates a cartesian product generator for a given number of vectors.
   *
   * @param array The array of the vectors.
   * @param <T> Type of the elements within the vectors.
   * @return An instance of the cartesian product generator.
   * @see CartesianProductGenerator
   */
  @SafeVarargs
  public static <T> Generator<T> createCartesianProductGenerator(ICombinatoricsVector<T>... array) {
    return new CartesianProductGenerator<>(createVector(array));
  }
}
