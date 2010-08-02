/**
 * 
 */
package tool;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author dpaukov
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
