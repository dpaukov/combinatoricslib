/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author Dmytro Paukov
 */
public class CombinatoricsVectorTest {

  @Test
  public void testEqualVectors() {
    // create a combinatorics vector1 (a, b, c)
    ICombinatoricsVector<String> vector1 = createVector("a", "b", "c");

    assertEquals(3, vector1.getSize());
    assertFalse(vector1.hasDuplicates());
    assertFalse(vector1.isAllElementsEqual());

    List<String> array2 = new ArrayList<String>();
    array2.add("a");
    array2.add("b");
    array2.add("c");

    // create a combinatorics vector2
    ICombinatoricsVector<String> vector2 = createVector(array2);

    assertEquals(vector1, vector2);
  }

  @Test
  public void testNotEqualVectors() {
    // create an array1
    List<String> array1 = new ArrayList<String>();
    array1.add("a");
    array1.add("c");
    array1.add("b");

    // create a combinatorics vector1
    ICombinatoricsVector<String> vector1 = createVector(array1);

    assertEquals(3, vector1.getSize());
    assertFalse(vector1.hasDuplicates());

    List<String> array2 = new ArrayList<String>();
    array2.add("a");
    array2.add("b");
    array2.add("c");

    // create a combinatorics vector2
    ICombinatoricsVector<String> vector2 = createVector(array2);

    assertEquals(3, vector2.getSize());

    assertNotEquals(array1, array2);
    assertNotEquals(vector1, vector2);
  }

  @Test
  public void testEmptyVectors() {
    // create a combinatorics vector1
    ICombinatoricsVector<String> vector1 = createVector();

    assertEquals(0, vector1.getSize());
    assertFalse(vector1.hasDuplicates());

    // create a combinatorics vector2
    ICombinatoricsVector<String> vector2 = createVector();

    assertEquals(0, vector2.getSize());

    assertEquals(vector1, vector2);
  }

  @Test
  public void testDuplicatesVectors() {
    // create a combinatorics vector (a, b, b, c)
    ICombinatoricsVector<String> vector = createVector("a", "b", "b", "c");

    assertEquals(4, vector.getSize());
    assertTrue(vector.hasDuplicates());
    assertFalse(vector.isAllElementsEqual());
  }

  @Test
  public void testAllElementsEqualVectors() {
    // create a combinatorics vector (a, a, a, a)
    ICombinatoricsVector<String> vector = createVector("a", "a", "a", "a");

    assertEquals(4, vector.getSize());
    assertTrue(vector.hasDuplicates());
    assertTrue(vector.isAllElementsEqual());
  }

  @Test
  public void testComplexVectors1() {
    List<String> array3 = new ArrayList<String>();
    array3.add("a");
    array3.add("a");
    array3.add("a");

    List<String> array1 = new ArrayList<String>();
    array1.add("a");

    ICombinatoricsVector<String> vector1 = createVector(array1);

    assertEquals(1, vector1.getSize());
    assertFalse(vector1.hasDuplicates());

    ICombinatoricsVector<String> vector3 = createVector(array3);

    assertEquals(3, vector3.getSize());
    assertTrue(vector3.hasDuplicates());

    ICombinatoricsVector<ICombinatoricsVector<String>> vectorA = createVector();
    vectorA.addValue(vector1);
    vectorA.addValue(vector3);

    assertEquals(2, vectorA.getSize());
    assertFalse(vectorA.hasDuplicates());

    ICombinatoricsVector<ICombinatoricsVector<String>> vectorB = createVector();
    vectorB.addValue(vector3);
    vectorB.addValue(vector1);

    assertEquals(2, vectorB.getSize());
    assertFalse(vectorB.hasDuplicates());

    assertNotEquals(vectorA, vectorB);
  }

  @Test
  public void testComplexVectors2() {
    List<String> array3 = new ArrayList<>();
    array3.add("a");
    array3.add("a");
    array3.add("a");

    List<String> array1 = new ArrayList<>();
    array1.add("a");

    List<String> array2 = new ArrayList<>();
    array2.add("a");
    array2.add("a");

    ICombinatoricsVector<String> vector1 = createVector(array1);

    assertEquals(1, vector1.getSize());
    assertFalse(vector1.hasDuplicates());

    ICombinatoricsVector<String> vector3 = createVector(array3);

    assertEquals(3, vector3.getSize());
    assertTrue(vector3.hasDuplicates());
    assertTrue(vector3.isAllElementsEqual());

    ICombinatoricsVector<String> vector2 = createVector(array2);

    assertEquals(2, vector2.getSize());
    assertTrue(vector2.hasDuplicates());

    ICombinatoricsVector<ICombinatoricsVector<String>> vectorA = createVector();
    vectorA.addValue(vector1);
    vectorA.addValue(vector3);

    assertEquals(2, vectorA.getSize());
    assertFalse(vectorA.hasDuplicates());

    ICombinatoricsVector<ICombinatoricsVector<String>> vectorB = createVector();
    vectorB.addValue(vector2);
    vectorB.addValue(vector2);

    assertEquals(2, vectorB.getSize());
    assertTrue(vectorB.hasDuplicates());

    assertNotEquals(vectorA, vectorB);
  }
}
