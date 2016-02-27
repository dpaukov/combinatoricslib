[![Build Status](https://secure.travis-ci.org/dpaukov/combinatoricslib.svg)](http://travis-ci.org/dpaukov/combinatoricslib)
[![Coverage Status](https://coveralls.io/repos/dpaukov/combinatoricslib/badge.svg?branch=master)](https://coveralls.io/r/dpaukov/combinatoricslib?branch=master) 
[![Maven Central](https://img.shields.io/maven-central/v/com.googlecode.combinatoricslib/combinatoricslib.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.googlecode.combinatoricslib%22%20AND%20a%3A%22combinatoricslib%22)

combinatoricslib 2.2
====================

Very simple java library to generate permutations, combinations and other combinatorial sequences

1. [Simple permutations](#1-simple-permutations)
2. [Permutations with repetitions](#2-permutations-with-repetitions)
3. [Simple combinations](#3-simple-combinations)
4. [Combinations with repetitions](#4-combinations-with-repetitions)
5. [Subsets](#5-subsets)
6. [Integer Partitions](#6-integer-partitions)
7. [List Partitions](#7-list-partitions)
8. [Integer Compositions](#8-integer-compositions)
9. [The latest release](#the-latest-release)

You can use the following table to select a generator:

| Description                      | Is Order Important? | Is Repetition Allowed? | Method  |
|----------------------------------|:-------------------:|:----------------------:|---------| 
| [Permutations with repetitions](#2-permutations-with-repetitions) | Yes | Yes | Factory.createPermutationWithRepetitionGenerator() |
| [Simple permutations](#1-simple-permutations) | Yes | No | Factory.createPermutationGenerator() |
| [Combinations with repetitions](#4-combinations-with-repetitions) | No | Yes | Factory.createMultiCombinationGenerator()|
| [Simple combinations](#3-simple-combinations) | No | No | Factory.createSimpleCombinationGenerator() |


###1. Simple permutations
A permutation is an ordering of a set in the context of all possible orderings. 
For example, the set containing the first three digits, 123, has six permutations: 123, 132, 213, 231, 312, and 321.

This is an example of the permutations of 3 string items (apple, orange, cherry):

```java
   ICombinatoricsVector<String> originalVector = Factory.createVector(new String[] { "apple", "orange", "cherry" });
   Generator<String> gen = Factory.createPermutationGenerator(originalVector);
   // Print the result
   for (ICombinatoricsVector<String> perm : gen)
      System.out.println(perm);
```

The result:
```
   CombinatoricsVector=([apple, orange, cherry], size=3)
   CombinatoricsVector=([apple, cherry, orange], size=3)
   CombinatoricsVector=([cherry, apple, orange], size=3)
   CombinatoricsVector=([cherry, orange, apple], size=3)
   CombinatoricsVector=([orange, cherry, apple], size=3)
   CombinatoricsVector=([orange, apple, cherry], size=3)
```
The generator can produce the permutations even if the initial vector has duplicates. 
For example, all permutations of (1,1,2,2) are:
```java
   ICombinatoricsVector<Integer> initialVector = Factory.createVector(new Integer[] { 1, 1, 2, 2 });
   Generator<Integer> generator = Factory.createPermutationGenerator(initialVector);
   for (ICombinatoricsVector<Integer> perm : generator) {
      System.out.println(perm);
   }
```

The result of all possible permutations
```
   CombinatoricsVector=([1, 1, 2, 2], size=4)
   CombinatoricsVector=([1, 2, 1, 2], size=4)
   CombinatoricsVector=([1, 2, 2, 1], size=4)
   CombinatoricsVector=([2, 1, 1, 2], size=4)
   CombinatoricsVector=([2, 1, 2, 1], size=4)
   CombinatoricsVector=([2, 2, 1, 1], size=4)
```

###2. Permutations with repetitions
The permutation may have more elements than slots. For example, all possible permutation of '12' 
in three slots are: 111, 211, 121, 221, 112, 212, 122, and 222.

Let's generate all possible permutations with repetitions of 3 elements from the set of apple and orange.

```java
   // Create an initial vector of 2 elements (apple, orange)
   ICombinatoricsVector<String> originalVector = Factory.createVector(new String[] { "apple", "orange" });
   Generator<String> gen = Factory.createPermutationWithRepetitionGenerator(originalVector, 3);
   for (ICombinatoricsVector<String> perm : gen)
      System.out.println( perm );
```
And the result of 8 permutations
```
   CombinatoricsVector=([apple, apple, apple], size=3)
   CombinatoricsVector=([orange, apple, apple], size=3)
   CombinatoricsVector=([apple, orange, apple], size=3)
   CombinatoricsVector=([orange, orange, apple], size=3)
   CombinatoricsVector=([apple, apple, orange], size=3)
   CombinatoricsVector=([orange, apple, orange], size=3)
   CombinatoricsVector=([apple, orange, orange], size=3)
   CombinatoricsVector=([orange, orange, orange], size=3)
```

###3. Simple combinations
A simple k-combination of a finite set S is a subset of k distinct elements of S. 
Specifying a subset does not arrange them in a particular order. As an example, a poker hand can 
be described as a 5-combination of cards from a 52-card deck: the 5 cards of the hand are all distinct, 
and the order of the cards in the hand does not matter.

Let's generate all 3-combination of the set of 5 colors (red, black, white, green, blue).
```java
   ICombinatoricsVector<String> initialVector = Factory.createVector(
      new String[] { "red", "black", "white", "green", "blue" } );

   // Create a simple combination generator to generate 3-combinations of the initial vector
   Generator<String> gen = Factory.createSimpleCombinationGenerator(initialVector, 3);
   for (ICombinatoricsVector<String> combination : gen) {
      System.out.println(combination);
   }
```
And the result of 10 combinations
```
   CombinatoricsVector=([red, black, white], size=3)
   CombinatoricsVector=([red, black, green], size=3)
   CombinatoricsVector=([red, black, blue], size=3)
   CombinatoricsVector=([red, white, green], size=3)
   CombinatoricsVector=([red, white, blue], size=3)
   CombinatoricsVector=([red, green, blue], size=3)
   CombinatoricsVector=([black, white, green], size=3)
   CombinatoricsVector=([black, white, blue], size=3)
   CombinatoricsVector=([black, green, blue], size=3)
   CombinatoricsVector=([white, green, blue], size=3)
```

###4. Combinations with repetitions
A k-multicombination or k-combination with repetition of a finite set S is given by a sequence of 
k not necessarily distinct elements of S, where order is not taken into account.

As an example. Suppose there are 2 types of fruits (apple and orange) at a grocery store, 
and you want to buy 3 pieces of fruit. You could select
- (apple, apple, apple)
- (apple, apple, orange)
- (apple, orange, orange)
- (orange, orange, orange)

Let's generate all 3-combinations with repetitions of the set (apple, orange).
```java
   ICombinatoricsVector<String> initialVector = Factory.createVector(
      new String[] { "apple", "orange" } );
   Generator<String> gen = Factory.createMultiCombinationGenerator(initialVector, 3);
   // Print all possible combinations
   for (ICombinatoricsVector<String> combination : gen) {
      System.out.println(combination);
   }
```
And the result of 4 multi-combinations
```
   CombinatoricsVector=([apple, apple, apple], size=3)
   CombinatoricsVector=([apple, apple, orange], size=3)
   CombinatoricsVector=([apple, orange, orange], size=3)
   CombinatoricsVector=([orange, orange, orange], size=3)
```

###5. Subsets
A set A is a subset of a set B if A is "contained" inside B. A and B may coincide. 
The relationship of one set being a subset of another is called inclusion or sometimes containment.

Examples:

The set (1, 2) is a proper subset of (1, 2, 3).
Any set is a subset of itself, but not a proper subset.
The empty set, denoted by ∅, is also a subset of any given set X.
All subsets of (1, 2, 3) are:

- ()
- (1)
- (2)
- (1, 2)
- (3)
- (1, 3)
- (2, 3)
- (1, 2, 3)

And code which generates all subsets of (one, two, three)

```java
   ICombinatoricsVector<String> initialSet = Factory.createVector(new String[] { "one", "two", "three" });
   Generator<String> gen = Factory.createSubSetGenerator(initialSet);
   for (ICombinatoricsVector<String> subSet : gen) {
      System.out.println(subSet);
   }
```
And the result of all possible 8 subsets
```
   CombinatoricsVector=([], size=0)
   CombinatoricsVector=([one], size=1)
   CombinatoricsVector=([two], size=1)
   CombinatoricsVector=([one, two], size=2)
   CombinatoricsVector=([three], size=1)
   CombinatoricsVector=([one, three], size=2)
   CombinatoricsVector=([two, three], size=2)
   CombinatoricsVector=([one, two, three], size=3)
```

###6. Integer Partitions
In number theory, a partition of a positive integer n is a way of writing n as a sum of positive integers. 
Two sums that differ only in the order of their summands are considered to be the same partition; 
if order matters then the sum becomes a composition. A summand in a partition is also called a part.

The partitions of 5 are listed below:

- 1 + 1 + 1 + 1 + 1
- 2 + 1 + 1 + 1
- 2 + 2 + 1
- 3 + 1 + 1
- 3 + 2
- 4 + 1
- 5

The number of partitions of n is given by the partition function p(n). In number theory, the partition 
function p(n) represents the number of possible partitions of a natural number n, 
which is to say the number of distinct (and order independent) ways of representing n as a sum of natural numbers.

Let's generate all possible partitions of 5:
```java
   Generator<Integer> gen = Factory.createPartitionGenerator(5);
   // Print the partitions
   for (ICombinatoricsVector<Integer> p : gen) {
      System.out.println(p);
   }
```
And the result of all 7 integer possible partitions
```
   CombinatoricsVector=([1, 1, 1, 1, 1], size=5)
   CombinatoricsVector=([2, 1, 1, 1], size=4)
   CombinatoricsVector=([2, 2, 1], size=3)
   CombinatoricsVector=([3, 1, 1], size=3)
   CombinatoricsVector=([3, 2], size=2)
   CombinatoricsVector=([4, 1], size=2)
   CombinatoricsVector=([5], size=1)
```

###7. List Partitions
It is possible to generate non-overlapping sublists of length n of a given list

For example, if a list is (A, B, B, C), then the non-overlapping sublists of length 2 will be:

- ( (A), (B, B, C) )
- ( (B, B, C), (A) )
- ( (B), (A, B, C) )
- ( (A, B, C), (B) )
- ( (A, B), (B, C) )
- ( (B, C), (A, B) )
- ( (B, B), (A, C) )
- ( (A, C), (B, B) )
- ( (A, B, B), (C) )
- ( (C), (A, B, B) )

To do that you should use an instance of the complex combination generator
```java
   // Create a vector (A, B, B, C)
   ICombinatoricsVector<String> vector = Factory.createVector(new String[] { "A", "B", "B", "C" });

   // Create a complex-combination generator
   Generator<ICombinatoricsVector<String>> gen = new ComplexCombinationGenerator<String>(vector, 2);

   // Iterate the combinations
   for (ICombinatoricsVector<ICombinatoricsVector<String>> comb : gen) {
      System.out.println(ComplexCombinationGenerator.convert2String(comb) + " - " + comb);
   }
```
And the result
```
   ([A],[B, B, C]) - CombinatoricsVector=([CombinatoricsVector=([A], size=1), CombinatoricsVector=([B, B, C], size=3)], size=2)
   ([B, B, C],[A]) - CombinatoricsVector=([CombinatoricsVector=([B, B, C], size=3), CombinatoricsVector=([A], size=1)], size=2)
   ([B],[A, B, C]) - CombinatoricsVector=([CombinatoricsVector=([B], size=1), CombinatoricsVector=([A, B, C], size=3)], size=2)
   ([A, B, C],[B]) - CombinatoricsVector=([CombinatoricsVector=([A, B, C], size=3), CombinatoricsVector=([B], size=1)], size=2)
   ([A, B],[B, C]) - CombinatoricsVector=([CombinatoricsVector=([A, B], size=2), CombinatoricsVector=([B, C], size=2)], size=2)
   ([B, C],[A, B]) - CombinatoricsVector=([CombinatoricsVector=([B, C], size=2), CombinatoricsVector=([A, B], size=2)], size=2)
   ([B, B],[A, C]) - CombinatoricsVector=([CombinatoricsVector=([B, B], size=2), CombinatoricsVector=([A, C], size=2)], size=2)
   ([A, C],[B, B]) - CombinatoricsVector=([CombinatoricsVector=([A, C], size=2), CombinatoricsVector=([B, B], size=2)], size=2)
   ([A, B, B],[C]) - CombinatoricsVector=([CombinatoricsVector=([A, B, B], size=3), CombinatoricsVector=([C], size=1)], size=2)
   ([C],[A, B, B]) - CombinatoricsVector=([CombinatoricsVector=([C], size=1), CombinatoricsVector=([A, B, B], size=3)], size=2)
```
###8. Integer Compositions
A composition of an integer n is a way of writing n as the sum of a sequence of (strictly) positive integers. Two sequences that differ in the order of their terms define different compositions of their sum, while they are considered to define the same partition of that number (see. Integer Partitions above).

The 16 compositions of 5 are:

- 5
- 4+1
- 3+2
- 3+1+1
- 2+3
- 2+2+1
- 2+1+2
- 2+1+1+1
- 1+4
- 1+3+1
- 1+2+2
- 1+2+1+1
- 1+1+3
- 1+1+2+1
- 1+1+1+2
- 1+1+1+1+1.

Compare this with the seven partitions of 5 (see Integer Partitions above):

- 5
- 4+1
- 3+2
- 3+1+1
- 2+2+1
- 2+1+1+1
- 1+1+1+1+1.

Example. Generate all possible integer compositions of 5.
```java
   // Create an instance of the integer composition generator to generate all possible compositions of 5
   Generator<Integer> gen = Factory.createCompositionGenerator(5);

   // Print the compositions
   for (ICombinatoricsVector<Integer> p : gen) {
      System.out.println(p);
   }
```
And the result
```
   CombinatoricsVector=([5], size=1)
   CombinatoricsVector=([1, 4], size=2)
   CombinatoricsVector=([2, 3], size=2)
   CombinatoricsVector=([1, 1, 3], size=3)
   CombinatoricsVector=([3, 2], size=2)
   CombinatoricsVector=([1, 2, 2], size=3)
   CombinatoricsVector=([2, 1, 2], size=3)
   CombinatoricsVector=([1, 1, 1, 2], size=4)
   CombinatoricsVector=([4, 1], size=2)
   CombinatoricsVector=([1, 3, 1], size=3)
   CombinatoricsVector=([2, 2, 1], size=3)
   CombinatoricsVector=([1, 1, 2, 1], size=4)
   CombinatoricsVector=([3, 1, 1], size=3)
   CombinatoricsVector=([1, 2, 1, 1], size=4)
   CombinatoricsVector=([2, 1, 1, 1], size=4)
   CombinatoricsVector=([1, 1, 1, 1, 1], size=5)
```

### The latest release

The latest release of the library is available through The Maven Central Repository [here](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.googlecode.combinatoricslib%22%20AND%20a%3A%22combinatoricslib%22)
Include the following section into your `pom.xml` file.

```xml
<dependency>
  <groupId>com.googlecode.combinatoricslib</groupId>
  <artifactId>combinatoricslib</artifactId>
  <version>2.1</version>
  <scope>compile</scope>
</dependency>
```