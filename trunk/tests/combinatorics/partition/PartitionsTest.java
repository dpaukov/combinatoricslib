/**
 * 
 */
package combinatorics.partition;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import combinatorics.CombinatoricsVector;
import combinatorics.Generator;
import combinatorics.Iterator;


/**
 * @author Dmytro Paukov
 *
 */
public class PartitionsTest {

    @Test 
    public void simplePartition() {
	
	Generator<Integer> partitionGenerator = new PartitionGenerator(5);
	Iterator<CombinatoricsVector<Integer>> partitionIterator = partitionGenerator.createIterator();
	
	System.out.println("Number of partition is: " + partitionGenerator.getNumberOfGeneratedObjects());
	assertEquals (8, partitionGenerator.getNumberOfGeneratedObjects());
	
	partitionIterator.first();
	while (!partitionIterator.isDone()) {
	  partitionIterator.next();
	  System.out.println(partitionIterator);
	}
	
	List<CombinatoricsVector<Integer>> list = partitionGenerator.generateAllObjects();
	
	assertEquals (7, list.size());
	
	assertEquals("CombinatoricsVector=[[1, 1, 1, 1, 1]], size=5]", list.get(0).toString());
	assertEquals("CombinatoricsVector=[[2, 1, 1, 1]], size=4]", list.get(1).toString());
	assertEquals("CombinatoricsVector=[[2, 2, 1]], size=3]", list.get(2).toString());
	assertEquals("CombinatoricsVector=[[3, 1, 1]], size=3]", list.get(3).toString());
	assertEquals("CombinatoricsVector=[[3, 2]], size=2]", list.get(4).toString());
	assertEquals("CombinatoricsVector=[[4, 1]], size=2]", list.get(5).toString());
	assertEquals("CombinatoricsVector=[[5]], size=1]", list.get(6).toString());
    }
}
