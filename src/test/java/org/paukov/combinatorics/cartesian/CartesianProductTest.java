package org.paukov.combinatorics.cartesian;

import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.paukov.combinatorics.Factory.createVector;

/**
 * Created by dpaukov on 5/3/18.
 */
public class CartesianProductTest {

    @Test
    public void test_cartesian_product() {

        ICombinatoricsVector<String> set01 = createVector(new String[]{"a", "b"});
        ICombinatoricsVector<String> set02 = createVector(new String[]{"x", "y"});

        ICombinatoricsVector<ICombinatoricsVector<String>> initialVector = createVector();
        initialVector.addValue(set01);
        initialVector.addValue(set02);

        Generator<String> cartesianProduct = new CartesianProductGenerator<String>(
                initialVector);

        Iterator<ICombinatoricsVector<String>> itr = cartesianProduct
                .iterator();

        while (itr.hasNext()) {
            ICombinatoricsVector<String> combination = itr
                    .next();
            System.out.println(combination);
        }

        List<ICombinatoricsVector<String>> list = cartesianProduct
                .generateAllObjects();

        assertEquals(4, list.size());
        assertEquals(4, cartesianProduct.getNumberOfGeneratedObjects());

        assertEquals(list.get(0), createVector(new String[]{"a", "x"}));
        assertEquals(list.get(1), createVector(new String[]{"a", "y"}));
        assertEquals(list.get(2), createVector(new String[]{"b", "x"}));
        assertEquals(list.get(3), createVector(new String[]{"b", "y"}));
    }

    @Test
    public void test_cartesian_product_one_set_is_empty() {

        ICombinatoricsVector<String> set01 = createVector(new String[]{"a", "b"});
        ICombinatoricsVector<String> set02 = createVector();

        ICombinatoricsVector<ICombinatoricsVector<String>> initialVector = createVector();
        initialVector.addValue(set01);
        initialVector.addValue(set02);

        Generator<String> cartesianProduct = new CartesianProductGenerator<String>(
                initialVector);

        Iterator<ICombinatoricsVector<String>> itr = cartesianProduct
                .iterator();

        while (itr.hasNext()) {
            ICombinatoricsVector<String> combination = itr
                    .next();
            System.out.println(combination);
        }

        List<ICombinatoricsVector<String>> list = cartesianProduct
                .generateAllObjects();

        assertEquals(0, list.size());
        assertEquals(0, cartesianProduct.getNumberOfGeneratedObjects());
    }

    @Test
    public void test_cartesian_product_empty() {

        Generator<String> cartesianProduct = new CartesianProductGenerator<String>(
                Factory.<ICombinatoricsVector<String>>createVector());

        Iterator<ICombinatoricsVector<String>> itr = cartesianProduct
                .iterator();

        while (itr.hasNext()) {
            ICombinatoricsVector<String> combination = itr
                    .next();
            System.out.println(combination);
        }

        List<ICombinatoricsVector<String>> list = cartesianProduct
                .generateAllObjects();

        assertEquals(0, list.size());
        assertEquals(0, cartesianProduct.getNumberOfGeneratedObjects());
    }
}
