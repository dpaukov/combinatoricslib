package org.paukov.combinatorics.cartesian;

import static org.junit.Assert.assertEquals;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import org.paukov.combinatorics.CombinatoricsFactory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Created by dpaukov on 5/3/18.
 */
public class CartesianProductTest {

    @Test
    public void test_cartesian_product() {

        ICombinatoricsVector<String> set01 = createVector("a", "b");
        ICombinatoricsVector<String> set02 = createVector("x", "y");

        Generator<String> cartesianProduct = new CartesianProductGenerator<>(createVector(set01, set02));

        Iterator<ICombinatoricsVector<String>> itr = cartesianProduct.iterator();

        while (itr.hasNext()) {
            ICombinatoricsVector<String> combination = itr.next();
            System.out.println(combination);
        }

        List<ICombinatoricsVector<String>> list = cartesianProduct.generateAllObjects();

        assertEquals(4, list.size());
        assertEquals(4, cartesianProduct.getNumberOfGeneratedObjects());

        assertEquals(list.get(0), createVector("a", "x"));
        assertEquals(list.get(1), createVector("a", "y"));
        assertEquals(list.get(2), createVector("b", "x"));
        assertEquals(list.get(3), createVector("b", "y"));
    }

    @Test
    public void test_cartesian_product_one_set_is_empty() {

        ICombinatoricsVector<String> set01 = createVector("a", "b");
        ICombinatoricsVector<String> set02 = createVector();

        Generator<String> cartesianProduct = new CartesianProductGenerator<>(createVector(set01, set02));

        Iterator<ICombinatoricsVector<String>> itr = cartesianProduct.iterator();

        while (itr.hasNext()) {
            ICombinatoricsVector<String> combination = itr.next();
            System.out.println(combination);
        }

        List<ICombinatoricsVector<String>> list = cartesianProduct.generateAllObjects();

        assertEquals(0, list.size());
        assertEquals(0, cartesianProduct.getNumberOfGeneratedObjects());
    }

    @Test
    public void test_cartesian_product_empty() {

        Generator<String> cartesianProduct = new CartesianProductGenerator<>(
            CombinatoricsFactory.<ICombinatoricsVector<String>>createVector());

        Iterator<ICombinatoricsVector<String>> itr = cartesianProduct.iterator();

        while (itr.hasNext()) {
            ICombinatoricsVector<String> combination = itr.next();
            System.out.println(combination);
        }

        List<ICombinatoricsVector<String>> list = cartesianProduct.generateAllObjects();

        assertEquals(0, list.size());
        assertEquals(0, cartesianProduct.getNumberOfGeneratedObjects());
    }
}
