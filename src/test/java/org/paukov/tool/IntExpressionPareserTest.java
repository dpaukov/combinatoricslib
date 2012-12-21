/**
 * Combinatorics Library
 * Copyright 2012 Dmytro Paukov d.paukov@gmail.com
 */
package org.paukov.tool;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.paukov.tool.IntExpressionParser;

/**
 * @author Dmytro Paukov
 * 
 */
public class IntExpressionPareserTest {

    @Test
    public void simpleTest() {
	String expr = "1+2*3*4+3*(2+2)-10";
	int result = IntExpressionParser.eval(expr);
	assertEquals(27, result);
    }
}
