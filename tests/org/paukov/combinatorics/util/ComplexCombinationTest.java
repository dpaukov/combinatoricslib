package org.paukov.combinatorics.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;

/**
 * @author Dmytro Paukov
 * 
 */
public class ComplexCombinationTest {

	@Test
	public void test0() {

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");
		originalList.add("b");
		originalList.add("c");
		originalList.add("d");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 2);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
				.generateAllObjects();

		assertEquals(16, list.size());

		assertEquals(
				"([],[a, b, c, d])",
				ComplexCombinationGenerator.<String> convert2String(list.get(0)));
		assertEquals(
				"([a, b, c, d],[])",
				ComplexCombinationGenerator.<String> convert2String(list.get(1)));
		assertEquals(
				"([a],[b, c, d])",
				ComplexCombinationGenerator.<String> convert2String(list.get(2)));
		assertEquals(
				"([b, c, d],[a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(3)));
		assertEquals(
				"([b],[a, c, d])",
				ComplexCombinationGenerator.<String> convert2String(list.get(4)));
		assertEquals(
				"([a, c, d],[b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(5)));
		assertEquals(
				"([a, b],[c, d])",
				ComplexCombinationGenerator.<String> convert2String(list.get(6)));
		assertEquals(
				"([c, d],[a, b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(7)));
		assertEquals(
				"([c],[a, b, d])",
				ComplexCombinationGenerator.<String> convert2String(list.get(8)));
		assertEquals(
				"([a, b, d],[c])",
				ComplexCombinationGenerator.<String> convert2String(list.get(9)));
		assertEquals("([a, c],[b, d])",
				ComplexCombinationGenerator.<String> convert2String(list
						.get(10)));
		assertEquals("([b, d],[a, c])",
				ComplexCombinationGenerator.<String> convert2String(list
						.get(11)));
		assertEquals("([b, c],[a, d])",
				ComplexCombinationGenerator.<String> convert2String(list
						.get(12)));
		assertEquals("([a, d],[b, c])",
				ComplexCombinationGenerator.<String> convert2String(list
						.get(13)));
		assertEquals("([a, b, c],[d])",
				ComplexCombinationGenerator.<String> convert2String(list
						.get(14)));
		assertEquals("([d],[a, b, c])",
				ComplexCombinationGenerator.<String> convert2String(list
						.get(15)));
	}

	@Test
	public void test1() {

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");
		originalList.add("b");
		originalList.add("b");
		originalList.add("c");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 2, true, true);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
				.generateAllObjects();

		assertEquals(10, list.size());

		assertEquals(
				"([a],[b, b, c])",
				ComplexCombinationGenerator.<String> convert2String(list.get(0)));
		assertEquals(
				"([b, b, c],[a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(1)));
		assertEquals(
				"([b],[a, b, c])",
				ComplexCombinationGenerator.<String> convert2String(list.get(2)));
		assertEquals(
				"([a, b, c],[b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(3)));
		assertEquals(
				"([a, b],[b, c])",
				ComplexCombinationGenerator.<String> convert2String(list.get(4)));
		assertEquals(
				"([b, c],[a, b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(5)));
		assertEquals(
				"([b, b],[a, c])",
				ComplexCombinationGenerator.<String> convert2String(list.get(6)));
		assertEquals(
				"([a, c],[b, b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(7)));
		assertEquals(
				"([a, b, b],[c])",
				ComplexCombinationGenerator.<String> convert2String(list.get(8)));
		assertEquals(
				"([c],[a, b, b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(9)));

	}

	@Test
	public void test2() {

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");
		originalList.add("a");
		originalList.add("a");
		originalList.add("a");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 2, true, true);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
				.generateAllObjects();

		assertEquals(3, list.size());

		assertEquals(
				"([a],[a, a, a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(0)));
		assertEquals(
				"([a, a, a],[a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(1)));
		assertEquals(
				"([a, a],[a, a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(2)));

	}

	@Test
	public void test3() {

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");
		originalList.add("a");
		originalList.add("a");
		originalList.add("b");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 2, true, true);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
				.generateAllObjects();

		assertEquals(6, list.size());

		assertEquals(
				"([a],[a, a, b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(0)));
		assertEquals(
				"([a, a, b],[a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(1)));
		assertEquals(
				"([a, a],[a, b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(2)));
		assertEquals(
				"([a, b],[a, a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(3)));
		assertEquals(
				"([a, a, a],[b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(4)));
		assertEquals(
				"([b],[a, a, a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(5)));

	}

	@Test
	public void test4() {

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");
		originalList.add("a");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 2, true, true);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals(
				"([a],[a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(0)));

	}

	@Test
	public void test5() {

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");
		originalList.add("a");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 1, true, true);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals(
				"([a, a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(0)));

	}

	@Test
	public void test6() {

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");
		originalList.add("b");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 2, true, true);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
				.generateAllObjects();

		assertEquals(2, list.size());

		assertEquals(
				"([a],[b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(0)));
		assertEquals(
				"([b],[a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(1)));

	}

	@Test
	public void test7() {

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");
		originalList.add("b");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 2);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
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

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");
		originalList.add("b");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 1);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals(
				"([a, b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(0)));

	}

	@Test
	public void test9() {

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");
		originalList.add("b");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 1, true, true);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals(
				"([a, b])",
				ComplexCombinationGenerator.<String> convert2String(list.get(0)));
	}

	@Test
	public void test10() {

		// Initialize the list
		ArrayList<String> originalList = new ArrayList<String>();
		originalList.add("a");

		// Create a complex-combination generator and iterator
		Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>(
				new CombinatoricsVector<String>(originalList), 1);

		// Create a complex-combination iterator
		Iterator<CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator
				.createIterator();

		// Iterate the elements
		while (itr.hasNext()) {
			CombinatoricsVector<CombinatoricsVector<String>> combination = itr
					.next();
			String str = ComplexCombinationGenerator
					.<String> convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator
				.generateAllObjects();

		assertEquals(1, list.size());

		assertEquals(
				"([a])",
				ComplexCombinationGenerator.<String> convert2String(list.get(0)));
	}

}
