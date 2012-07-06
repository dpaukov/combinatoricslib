import java.util.ArrayList;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.util.ComplexCombinationGenerator;


public class Main {
	
  /**
   * @param args
   */
  public static void main(String[] args) {
    
	  // Initialize the list
	  ArrayList<String> originalList = new ArrayList<String>();
	  originalList.add("a");
	  originalList.add("b");
	  originalList.add("b");
	  originalList.add("c");
	  
	  // Create a complex-combination generator and iterator
	  Generator<CombinatoricsVector<String>> complexGenerator = new ComplexCombinationGenerator<String>( new CombinatoricsVector<String>(originalList), 2, true, true);
	  
	  // Create a complex-combination iterator
	  Iterator <CombinatoricsVector<CombinatoricsVector<String>>> itr = complexGenerator.createIterator();
	  
	  // Iterate the elements
	  while (itr.hasNext())
	  {
		  CombinatoricsVector<CombinatoricsVector<String>> combination = itr.next();
		  String str = ComplexCombinationGenerator.<String>convert2String(combination);
		  System.out.println(str);
	  }
  }
}
