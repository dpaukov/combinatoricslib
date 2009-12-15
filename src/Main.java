import java.util.ArrayList;

import combinatorics.CombinatoricsVector;
import combinatorics.Generator;
import combinatorics.Iterator;
import combinatorics.combination.simple.SimpleCombinationGenerator;
import combinatorics.partition.PartitionGenerator;
import combinatorics.permutations.PermutationGenerator;
import combinatorics.permutations.PermutationWithRepetitionGenerator;
import combinatorics.subsets.SubSetGenerator;
import combinatorics.util.Util;

public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    ArrayList<Integer> array = new ArrayList<Integer>();
    array.add(1);
    array.add(2);
    array.add(3);
    array.add(4);

    CombinatoricsVector<Integer> corePermutation = new CombinatoricsVector<Integer>(array);
    Generator<Integer> generator = new PermutationGenerator<Integer>(corePermutation);
    System.out.println("Number of permutations is: " + generator.getNumberOfGeneratedObjects());
    Iterator<CombinatoricsVector<Integer>> iterator = generator.createIterator();
    iterator.first();
    while (!iterator.isDone()) {
      iterator.next();
      CombinatoricsVector<Integer> permutation = iterator.getCurrentItem();
      System.out.println(iterator);
    }

    Generator<Integer> subSetGenerator = new SubSetGenerator<Integer>(corePermutation);
    Iterator<CombinatoricsVector<Integer>> subSetIterator = subSetGenerator.createIterator();
    System.out.println("Number of subsets is: " + subSetGenerator.getNumberOfGeneratedObjects());
    subSetIterator.first();
    while (!subSetIterator.isDone()) {
      subSetIterator.next();
      CombinatoricsVector<Integer> subSet = subSetIterator.getCurrentItem();
      System.out.println(subSetIterator);
    }

    Generator<Integer> combinationGenerator = new SimpleCombinationGenerator<Integer>(corePermutation, 2);
    Iterator<CombinatoricsVector<Integer>> combinationIterator = combinationGenerator.createIterator();
    System.out.println("Number of combinations is: " + combinationGenerator.getNumberOfGeneratedObjects());
    combinationIterator.first();
    while (!combinationIterator.isDone()) {
      combinationIterator.next();
      CombinatoricsVector<Integer> combination = combinationIterator.getCurrentItem();
      System.out.println(combinationIterator);
    }

    System.out.println("GCD=" + Util.gcd(231, 525));
    System.out.println("LCM=" + Util.lcm(231, 525));

    int k = 3;
    int n = 4;

    int[] b = new int[k + 1];
    for (int j = 0; j <= k; j++) {
      b[j] = 0;
    }
    while (b[k] != 1) {
      for (int j = k - 1; j >= 0; j--) {
        System.out.print(b[j] + ", ");
      }
      System.out.println("");
      int i = 0;
      while (b[i] == n - 1) {
        b[i] = 0;
        i++;
      }
      b[i]++;
    }
    
    Generator<Integer> permutationWithRepetitionGenerator = new PermutationWithRepetitionGenerator<Integer>(corePermutation, 4);
    Iterator<CombinatoricsVector<Integer>> permutationWithRepetitionIterator = permutationWithRepetitionGenerator.createIterator();
    System.out.println("Number of permutationWithRepetition is: " + permutationWithRepetitionGenerator.getNumberOfGeneratedObjects());
    permutationWithRepetitionIterator.first();
    while (!permutationWithRepetitionIterator.isDone()) {
      permutationWithRepetitionIterator.next();
      CombinatoricsVector<Integer> permutationWithRepetition = permutationWithRepetitionIterator.getCurrentItem();
      System.out.println(permutationWithRepetitionIterator);
    }
    
    Generator<Integer> partitionGenerator = new PartitionGenerator(5);
    Iterator<CombinatoricsVector<Integer>> partitionIterator = partitionGenerator.createIterator();
    System.out.println("Number of partition is: " + partitionGenerator.getNumberOfGeneratedObjects());
    partitionIterator.first();
    while (!partitionIterator.isDone()) {
      partitionIterator.next();
      CombinatoricsVector<Integer> partition = partitionIterator.getCurrentItem();
      System.out.println(partitionIterator);
    }
  }

}
