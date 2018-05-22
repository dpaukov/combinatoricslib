/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.combination.multi;

import static org.junit.Assert.assertEquals;
import static org.paukov.combinatorics.CombinatoricsFactory.createMultiCombinationGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.util.ComplexCombinationGenerator;

/**
 * @author Dmytro Paukov
 */
public class MultiCombinationsTest {

  @Test
  public void multiCombinationTest() {

    // create combinatorics vector
    ICombinatoricsVector<String> initialVector = createVector("A", "B", "C");

    // create multi-combination generator to generate 3-combination
    Generator<String> gen = createMultiCombinationGenerator(initialVector, 3);

    // create iterator
    Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

    // print the number of combinations
    assertEquals(10, gen.getNumberOfGeneratedObjects());

    // go through the iterator
    while (itr.hasNext()) {
      ICombinatoricsVector<String> combination = itr.next();
      System.out.println(combination);
    }

    List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

    assertEquals(10, list.size());

    assertEquals("CombinatoricsVector=([A, A, A], size=3)", list.get(0)
        .toString());
    assertEquals("CombinatoricsVector=([A, A, B], size=3)", list.get(1)
        .toString());
    assertEquals("CombinatoricsVector=([A, A, C], size=3)", list.get(2)
        .toString());
    assertEquals("CombinatoricsVector=([A, B, B], size=3)", list.get(3)
        .toString());
    assertEquals("CombinatoricsVector=([A, B, C], size=3)", list.get(4)
        .toString());
    assertEquals("CombinatoricsVector=([A, C, C], size=3)", list.get(5)
        .toString());
    assertEquals("CombinatoricsVector=([B, B, B], size=3)", list.get(6)
        .toString());

    assertEquals("CombinatoricsVector=([B, B, C], size=3)", list.get(7)
        .toString());
    assertEquals("CombinatoricsVector=([B, C, C], size=3)", list.get(8)
        .toString());
    assertEquals("CombinatoricsVector=([C, C, C], size=3)", list.get(9)
        .toString());
  }

  @Test
  public void multiCombinationVectorsTest() {

    ICombinatoricsVector<String> vectorA = createVector("A1", "A2", "A3");
    ICombinatoricsVector<String> vectorB = createVector("B1", "B2", "B3");
    ICombinatoricsVector<String> vectorC = createVector("C1", "C2", "C3");

    // create combinatorics vector
    ICombinatoricsVector<ICombinatoricsVector<String>> initialVector = createVector(vectorA,
        vectorB, vectorC);

    // create multi-combination generator to generate 3-combination
    Generator<ICombinatoricsVector<String>> gen = createMultiCombinationGenerator(initialVector, 3);

    // create iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = gen.iterator();

    // print the number of combinations
    assertEquals(10, gen.getNumberOfGeneratedObjects());

    // go through the iterator
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr.next();

      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = gen.generateAllObjects();

    assertEquals(10, list.size());

    assertEquals(
        "CombinatoricsVector=([CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([A1, A2, A3], size=3)], size=3)",
        list.get(0)
            .toString());
    assertEquals(
        "CombinatoricsVector=([CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([B1, B2, B3], size=3)], size=3)",
        list.get(1)
            .toString());
    assertEquals(
        "CombinatoricsVector=([CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([C1, C2, C3], size=3)], size=3)",
        list.get(2)
            .toString());
    assertEquals(
        "CombinatoricsVector=([CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([B1, B2, B3], size=3), CombinatoricsVector=([B1, B2, B3], size=3)], size=3)",
        list.get(3)
            .toString());
    assertEquals(
        "CombinatoricsVector=([CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([B1, B2, B3], size=3), CombinatoricsVector=([C1, C2, C3], size=3)], size=3)",
        list.get(4)
            .toString());
    assertEquals(
        "CombinatoricsVector=([CombinatoricsVector=([A1, A2, A3], size=3), CombinatoricsVector=([C1, C2, C3], size=3), CombinatoricsVector=([C1, C2, C3], size=3)], size=3)",
        list.get(5)
            .toString());
    assertEquals(
        "CombinatoricsVector=([CombinatoricsVector=([B1, B2, B3], size=3), CombinatoricsVector=([B1, B2, B3], size=3), CombinatoricsVector=([B1, B2, B3], size=3)], size=3)",
        list.get(6)
            .toString());

    assertEquals(
        "CombinatoricsVector=([CombinatoricsVector=([B1, B2, B3], size=3), CombinatoricsVector=([B1, B2, B3], size=3), CombinatoricsVector=([C1, C2, C3], size=3)], size=3)",
        list.get(7)
            .toString());
    assertEquals(
        "CombinatoricsVector=([CombinatoricsVector=([B1, B2, B3], size=3), CombinatoricsVector=([C1, C2, C3], size=3), CombinatoricsVector=([C1, C2, C3], size=3)], size=3)",
        list.get(8)
            .toString());
    assertEquals(
        "CombinatoricsVector=([CombinatoricsVector=([C1, C2, C3], size=3), CombinatoricsVector=([C1, C2, C3], size=3), CombinatoricsVector=([C1, C2, C3], size=3)], size=3)",
        list.get(9)
            .toString());
  }

  @Test
  public void multiCombinationOneTest() {
    // create combinatorics vector
    ICombinatoricsVector<String> initialVector = createVector("A");

    // create multi-combination generator to generate 3-combination
    Generator<String> gen = createMultiCombinationGenerator(initialVector, 3);

    // create iterator
    Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

    // print the number of combinations
    assertEquals(1, gen.getNumberOfGeneratedObjects());

    // go through the iterator
    while (itr.hasNext()) {
      ICombinatoricsVector<String> combination = itr.next();
      System.out.println(combination);
    }

    List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

    assertEquals(1, list.size());

    assertEquals("CombinatoricsVector=([A, A, A], size=3)", list.get(0)
        .toString());
  }

  @Test
  public void multiCombinationEmptyTest() {

    // create combinatorics vector
    ICombinatoricsVector<String> initialVector = createVector("A", "B");

    // create multi-combination generator to generate 0-combination
    Generator<String> gen = createMultiCombinationGenerator(initialVector, 0);

    // create iterator
    Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

    // print the number of combinations
    assertEquals(1, gen.getNumberOfGeneratedObjects());

    // go through the iterator
    while (itr.hasNext()) {
      ICombinatoricsVector<String> combination = itr.next();
      System.out.println(combination);
    }

    List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

    assertEquals(1, list.size());

    assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());

  }

  @Test
  public void multiCombinationEmptyEmptyTest() {
    // create combinatorics vector
    ICombinatoricsVector<String> initialVector = createVector(new ArrayList<String>());

    // create multi-combination generator to generate 0-combination
    Generator<String> gen = createMultiCombinationGenerator(initialVector, 0);

    // create iterator
    Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

    // print the number of combinations
    assertEquals(1, gen.getNumberOfGeneratedObjects());

    // go through the iterator
    while (itr.hasNext()) {
      ICombinatoricsVector<String> combination = itr.next();
      System.out.println(combination);
    }

    List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

    assertEquals(1, list.size());

    assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());

  }

  @Test
  public void multiCombinationEmptyOneTest() {
    // create combinatorics vector
    ICombinatoricsVector<String> initialVector = createVector(new ArrayList<String>());

    // create multi-combination generator to generate 1-combination
    Generator<String> gen = createMultiCombinationGenerator(initialVector, 1);

    // create iterator
    Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

    // print the number of combinations
    assertEquals(1, gen.getNumberOfGeneratedObjects());

    // go through the iterator
    while (itr.hasNext()) {
      ICombinatoricsVector<String> combination = itr.next();
      System.out.println(combination);
    }

    List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

    assertEquals(1, list.size());

    assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());

  }

  @Test
  public void multiCombinationMinusOneTest() {

    // create combinatorics vector (A, B, C)
    ICombinatoricsVector<String> initialVector = createVector("A", "B", "C");

    // create multi-combination generator to generate 3-combination
    Generator<String> gen = createMultiCombinationGenerator(initialVector, -1);

    // create iterator
    Iterator<ICombinatoricsVector<String>> itr = gen.iterator();

    // print the number of combinations
    assertEquals(1, gen.getNumberOfGeneratedObjects());

    // go through the iterator
    while (itr.hasNext()) {
      ICombinatoricsVector<String> combination = itr.next();
      System.out.println(combination);
    }

    List<ICombinatoricsVector<String>> list = gen.generateAllObjects();

    assertEquals(1, list.size());

    assertEquals("CombinatoricsVector=([], size=0)", list.get(0).toString());
  }

}
