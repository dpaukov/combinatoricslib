/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.combinatorics.util;

import static org.junit.Assert.assertEquals;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.paukov.combinatorics.IFilter;

/**
 * @author Dmytro Paukov
 */
public class ComplexCombinationTest {

  @Test
  public void test0() {

    // create a combinatorics vector (a, b, c, d)
    ICombinatoricsVector<String> initialVector = createVector("a", "b", "c", "d");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<>(
        initialVector, 2, true, false);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(16, list.size());

    assertEquals("([],[a, b, c, d])",
        ComplexCombinationGenerator.convert2String(list.get(0)));
    assertEquals("([a, b, c, d],[])",
        ComplexCombinationGenerator.convert2String(list.get(1)));
    assertEquals("([a],[b, c, d])",
        ComplexCombinationGenerator.convert2String(list.get(2)));
    assertEquals("([b, c, d],[a])",
        ComplexCombinationGenerator.convert2String(list.get(3)));
    assertEquals("([b],[a, c, d])",
        ComplexCombinationGenerator.convert2String(list.get(4)));
    assertEquals("([a, c, d],[b])",
        ComplexCombinationGenerator.convert2String(list.get(5)));
    assertEquals("([a, b],[c, d])",
        ComplexCombinationGenerator.convert2String(list.get(6)));
    assertEquals("([c, d],[a, b])",
        ComplexCombinationGenerator.convert2String(list.get(7)));
    assertEquals("([c],[a, b, d])",
        ComplexCombinationGenerator.convert2String(list.get(8)));
    assertEquals("([a, b, d],[c])",
        ComplexCombinationGenerator.convert2String(list.get(9)));
    assertEquals("([a, c],[b, d])",
        ComplexCombinationGenerator.convert2String(list.get(10)));
    assertEquals("([b, d],[a, c])",
        ComplexCombinationGenerator.convert2String(list.get(11)));
    assertEquals("([b, c],[a, d])",
        ComplexCombinationGenerator.convert2String(list.get(12)));
    assertEquals("([a, d],[b, c])",
        ComplexCombinationGenerator.convert2String(list.get(13)));
    assertEquals("([a, b, c],[d])",
        ComplexCombinationGenerator.convert2String(list.get(14)));
    assertEquals("([d],[a, b, c])",
        ComplexCombinationGenerator.convert2String(list.get(15)));
  }

  @Test
  public void test1() {

    // create a combinatorics vector (a, b, b, c)
    ICombinatoricsVector<String> initialVector = createVector("a", "b", "b", "c");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<>(
        initialVector, 2, true, true);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(10, list.size());

    assertEquals("([a],[b, b, c])",
        ComplexCombinationGenerator.convert2String(list.get(0)));
    assertEquals("([b, b, c],[a])",
        ComplexCombinationGenerator.convert2String(list.get(1)));
    assertEquals("([b],[a, b, c])",
        ComplexCombinationGenerator.convert2String(list.get(2)));
    assertEquals("([a, b, c],[b])",
        ComplexCombinationGenerator.convert2String(list.get(3)));
    assertEquals("([a, b],[b, c])",
        ComplexCombinationGenerator.convert2String(list.get(4)));
    assertEquals("([b, c],[a, b])",
        ComplexCombinationGenerator.convert2String(list.get(5)));
    assertEquals("([b, b],[a, c])",
        ComplexCombinationGenerator.convert2String(list.get(6)));
    assertEquals("([a, c],[b, b])",
        ComplexCombinationGenerator.convert2String(list.get(7)));
    assertEquals("([a, b, b],[c])",
        ComplexCombinationGenerator.convert2String(list.get(8)));
    assertEquals("([c],[a, b, b])",
        ComplexCombinationGenerator.convert2String(list.get(9)));

  }

  @Test
  public void test2() {

    // create a combinatorics vector (a, a, a, a)
    ICombinatoricsVector<String> initialVector = createVector("a", "a", "a", "a");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<>(
        initialVector, 2, true, true);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(3, list.size());

    assertEquals("([a],[a, a, a])",
        ComplexCombinationGenerator.convert2String(list.get(0)));
    assertEquals("([a, a, a],[a])",
        ComplexCombinationGenerator.convert2String(list.get(1)));
    assertEquals("([a, a],[a, a])",
        ComplexCombinationGenerator.convert2String(list.get(2)));

  }

  @Test
  public void test3() {

    // create a combinatorics vector (a, a, a, b)
    ICombinatoricsVector<String> initialVector = createVector("a", "a", "a", "b");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<>(
        initialVector, 2, true, true);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(6, list.size());

    assertEquals("([a],[a, a, b])",
        ComplexCombinationGenerator.convert2String(list.get(0)));
    assertEquals("([a, a, b],[a])",
        ComplexCombinationGenerator.convert2String(list.get(1)));
    assertEquals("([a, a],[a, b])",
        ComplexCombinationGenerator.convert2String(list.get(2)));
    assertEquals("([a, b],[a, a])",
        ComplexCombinationGenerator.convert2String(list.get(3)));
    assertEquals("([a, a, a],[b])",
        ComplexCombinationGenerator.convert2String(list.get(4)));
    assertEquals("([b],[a, a, a])",
        ComplexCombinationGenerator.convert2String(list.get(5)));

  }

  @Test
  public void test4() {

    // create a combinatorics vector (a, a)
    ICombinatoricsVector<String> initialVector = createVector("a", "a");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<>(
        initialVector, 2, true, true);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(1, list.size());

    assertEquals("([a],[a])",
        ComplexCombinationGenerator.convert2String(list.get(0)));

  }

  @Test
  public void test5() {

    // create a combinatorics vector (a, a)
    ICombinatoricsVector<String> initialVector = createVector("a", "a");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
        initialVector, 1, true, true);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(1, list.size());

    assertEquals("([a, a])",
        ComplexCombinationGenerator.convert2String(list.get(0)));

  }

  @Test
  public void test6() {

    // create a combinatorics vector (a, b)
    ICombinatoricsVector<String> initialVector = createVector("a", "b");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
        initialVector, 2, true, true);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(2, list.size());

    assertEquals("([a],[b])",
        ComplexCombinationGenerator.convert2String(list.get(0)));
    assertEquals("([b],[a])",
        ComplexCombinationGenerator.convert2String(list.get(1)));

  }

  @Test
  public void test7() {

    // create a combinatorics vector (a, b)
    ICombinatoricsVector<String> initialVector = createVector("a", "b");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
        initialVector, 2, true, false);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(4, list.size());

    assertEquals("([],[a, b])",
        ComplexCombinationGenerator.convert2String(list.get(0)));
    assertEquals("([a, b],[])",
        ComplexCombinationGenerator.convert2String(list.get(1)));
    assertEquals("([a],[b])",
        ComplexCombinationGenerator.convert2String(list.get(2)));
    assertEquals("([b],[a])",
        ComplexCombinationGenerator.convert2String(list.get(3)));

  }

  @Test
  public void test8() {

    // create a combinatorics vector (a, b)
    ICombinatoricsVector<String> initialVector = createVector("a", "b");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
        initialVector, 1);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(1, list.size());

    assertEquals("([a, b])",
        ComplexCombinationGenerator.convert2String(list.get(0)));

  }

  @Test
  public void test9() {

    // create a combinatorics vector (a, b)
    ICombinatoricsVector<String> initialVector = createVector("a", "b");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
        initialVector, 1, true, true);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(1, list.size());

    assertEquals("([a, b])",
        ComplexCombinationGenerator.convert2String(list.get(0)));
  }

  @Test
  public void test10() {

    // create a combinatorics vector (a)
    ICombinatoricsVector<String> initialVector = createVector("a");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
        initialVector, 1);

    // Create a complex-combination iterator
    Iterator<ICombinatoricsVector<ICombinatoricsVector<String>>> itr = complexGenerator
        .iterator();

    // Iterate the elements
    while (itr.hasNext()) {
      ICombinatoricsVector<ICombinatoricsVector<String>> combination = itr
          .next();
      String str = ComplexCombinationGenerator
          .convert2String(combination);
      System.out.println(str + " = " + itr);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(1, list.size());

    assertEquals("([a])",
        ComplexCombinationGenerator.convert2String(list.get(0)));
  }

  @Test
  public void foreachTest() {

    // create a combinatorics vector (a, b, c, d)
    ICombinatoricsVector<String> initialVector = createVector("a", "b", "c", "d");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
        initialVector, 2, true, false);

    for (ICombinatoricsVector<ICombinatoricsVector<String>> element : complexGenerator) {
      System.out.println("For each: " + element);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateAllObjects();

    assertEquals(16, list.size());

    assertEquals("([],[a, b, c, d])",
        ComplexCombinationGenerator.convert2String(list.get(0)));
    assertEquals("([a, b, c, d],[])",
        ComplexCombinationGenerator.convert2String(list.get(1)));
    assertEquals("([a],[b, c, d])",
        ComplexCombinationGenerator.convert2String(list.get(2)));
    assertEquals("([b, c, d],[a])",
        ComplexCombinationGenerator.convert2String(list.get(3)));
    assertEquals("([b],[a, c, d])",
        ComplexCombinationGenerator.convert2String(list.get(4)));
    assertEquals("([a, c, d],[b])",
        ComplexCombinationGenerator.convert2String(list.get(5)));
    assertEquals("([a, b],[c, d])",
        ComplexCombinationGenerator.convert2String(list.get(6)));
    assertEquals("([c, d],[a, b])",
        ComplexCombinationGenerator.convert2String(list.get(7)));
    assertEquals("([c],[a, b, d])",
        ComplexCombinationGenerator.convert2String(list.get(8)));
    assertEquals("([a, b, d],[c])",
        ComplexCombinationGenerator.convert2String(list.get(9)));
    assertEquals("([a, c],[b, d])",
        ComplexCombinationGenerator.convert2String(list.get(10)));
    assertEquals("([b, d],[a, c])",
        ComplexCombinationGenerator.convert2String(list.get(11)));
    assertEquals("([b, c],[a, d])",
        ComplexCombinationGenerator.convert2String(list.get(12)));
    assertEquals("([a, d],[b, c])",
        ComplexCombinationGenerator.convert2String(list.get(13)));
    assertEquals("([a, b, c],[d])",
        ComplexCombinationGenerator.convert2String(list.get(14)));
    assertEquals("([d],[a, b, c])",
        ComplexCombinationGenerator.convert2String(list.get(15)));
  }

  @Test
  public void subSetPartition() {

    // create a combinatorics vector (a, b, c, d, e, f)
    ICombinatoricsVector<String> initialVector = createVector("a", "b", "c", "d", "e", "f");

    // Create a complex-combination generator and iterator
    Generator<ICombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
        initialVector, 3, false, true);

    for (ICombinatoricsVector<ICombinatoricsVector<String>> element : complexGenerator) {
      System.out.println("subSetPartition: " + element);
    }

    List<ICombinatoricsVector<ICombinatoricsVector<String>>> list = complexGenerator
        .generateFilteredObjects(new IFilter<ICombinatoricsVector<ICombinatoricsVector<String>>>() {

          @Override
          public boolean accepted(
              long index,
              ICombinatoricsVector<ICombinatoricsVector<String>> value) {
            ICombinatoricsVector<Integer> sizes = createVector();

            for (ICombinatoricsVector<String> elements : value
                .getVector()) {
              sizes.addValue(elements.getSize());
            }

            return sizes.isAllElementsEqual()
                && sizes.getValue(0) == 2;
          }
        });

    assertEquals(15, list.size());

    assertEquals("([a, b],[c, d],[e, f])",
        ComplexCombinationGenerator.convert2String(list.get(0)));
    assertEquals("([a, b],[c, e],[d, f])",
        ComplexCombinationGenerator.convert2String(list.get(1)));
    assertEquals("([a, b],[d, e],[c, f])",
        ComplexCombinationGenerator.convert2String(list.get(2)));
    assertEquals("([a, c],[b, d],[e, f])",
        ComplexCombinationGenerator.convert2String(list.get(3)));
    assertEquals("([a, c],[b, e],[d, f])",
        ComplexCombinationGenerator.convert2String(list.get(4)));
    assertEquals("([a, c],[d, e],[b, f])",
        ComplexCombinationGenerator.convert2String(list.get(5)));
    assertEquals("([b, c],[a, d],[e, f])",
        ComplexCombinationGenerator.convert2String(list.get(6)));
    assertEquals("([b, c],[a, e],[d, f])",
        ComplexCombinationGenerator.convert2String(list.get(7)));
    assertEquals("([b, c],[d, e],[a, f])",
        ComplexCombinationGenerator.convert2String(list.get(8)));
    assertEquals("([a, d],[b, e],[c, f])",
        ComplexCombinationGenerator.convert2String(list.get(9)));
    assertEquals("([a, d],[c, e],[b, f])",
        ComplexCombinationGenerator.convert2String(list.get(10)));
    assertEquals("([b, d],[a, e],[c, f])",
        ComplexCombinationGenerator.convert2String(list.get(11)));
    assertEquals("([b, d],[c, e],[a, f])",
        ComplexCombinationGenerator.convert2String(list.get(12)));
    assertEquals("([c, d],[a, e],[b, f])",
        ComplexCombinationGenerator.convert2String(list.get(13)));
    assertEquals("([c, d],[b, e],[a, f])",
        ComplexCombinationGenerator.convert2String(list.get(14)));

  }
}
