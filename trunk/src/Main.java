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
    
    ArrayList<String> array = new ArrayList<String>();
    array.add("red");
    array.add("black");
    array.add("white");
    array.add("green");
    array.add("blue");

    CombinatoricsVector<String> corePermutation = new CombinatoricsVector<String>(array);
//    Generator<String> generator = new PermutationGenerator<String>(corePermutation);
//    System.out.println("Number of permutations is: " + generator.getNumberOfGeneratedObjects());
//    Iterator<CombinatoricsVector<String>> iterator = generator.createIterator();
//    iterator.first();
//    while (!iterator.isDone()) {
//      iterator.next();
//      CombinatoricsVector<String> permutation = iterator.getCurrentItem();
//      System.out.println(permutation);
//    }
//    
//    Generator<String> subSetGenerator = new SubSetGenerator<String>(corePermutation);
//    Iterator<CombinatoricsVector<String>> subSetIterator = subSetGenerator.createIterator();
//    System.out.println("Number of subsets is: " + subSetGenerator.getNumberOfGeneratedObjects());
//    subSetIterator.first();
//    while (!subSetIterator.isDone()) {
//      subSetIterator.next();
//      CombinatoricsVector<String> subSet = subSetIterator.getCurrentItem();
//      System.out.println(subSetIterator);
//    }

    Generator<String> combinationGenerator = new SimpleCombinationGenerator<String>(corePermutation, 3);
    Iterator<CombinatoricsVector<String>> combinationIterator = combinationGenerator.createIterator();
    System.out.println("Number of combinations is: " + combinationGenerator.getNumberOfGeneratedObjects());
    combinationIterator.first();
    while (!combinationIterator.isDone()) {
      combinationIterator.next();
      CombinatoricsVector<String> combination = combinationIterator.getCurrentItem();
      System.out.println(combinationIterator);
    }

    System.out.println("GCD=" + Util.gcd(231, 525));
    System.out.println("LCM=" + Util.lcm(231, 525));

       
    Generator<String> permutationWithRepetitionGenerator = new PermutationWithRepetitionGenerator<String>(corePermutation, 3);
    Iterator<CombinatoricsVector<String>> permutationWithRepetitionIterator = permutationWithRepetitionGenerator.createIterator();
    System.out.println("Number of permutationWithRepetition is: " + permutationWithRepetitionGenerator.getNumberOfGeneratedObjects());
    permutationWithRepetitionIterator.first();
    while (!permutationWithRepetitionIterator.isDone()) {
      permutationWithRepetitionIterator.next();
      CombinatoricsVector<String> permutationWithRepetition = permutationWithRepetitionIterator.getCurrentItem();
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
