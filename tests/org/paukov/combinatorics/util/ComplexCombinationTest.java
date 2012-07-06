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
			String str = ComplexCombinationGenerator.<String>convert2String(combination);
			System.out.println(str + " = " + itr);
		}

		List<CombinatoricsVector<CombinatoricsVector<String>>> list = complexGenerator.generateAllObjects();

		assertEquals(10, list.size());

		assertEquals("([a],[b, b, c])", ComplexCombinationGenerator.<String>convert2String(list.get(0)));
		assertEquals("([b, b, c],[a])", ComplexCombinationGenerator.<String>convert2String(list.get(1)));
		assertEquals("([b],[a, b, c])", ComplexCombinationGenerator.<String>convert2String(list.get(2)));
		assertEquals("([a, b, c],[b])", ComplexCombinationGenerator.<String>convert2String(list.get(3)));
		assertEquals("([a, b],[b, c])", ComplexCombinationGenerator.<String>convert2String(list.get(4)));
		assertEquals("([b, c],[a, b])", ComplexCombinationGenerator.<String>convert2String(list.get(5)));
		assertEquals("([b, b],[a, c])", ComplexCombinationGenerator.<String>convert2String(list.get(6)));
		assertEquals("([a, c],[b, b])", ComplexCombinationGenerator.<String>convert2String(list.get(7)));
		assertEquals("([a, b, b],[c])", ComplexCombinationGenerator.<String>convert2String(list.get(8)));
		assertEquals("([c],[a, b, b])", ComplexCombinationGenerator.<String>convert2String(list.get(9)));
		
	}

}
