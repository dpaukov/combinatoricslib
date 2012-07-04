import java.util.ArrayList;
import java.util.List;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.Iterator;
import org.paukov.combinatorics.combination.simple.SimpleCombinationGenerator;
import org.paukov.combinatorics.permutations.PermutationGenerator;
import org.paukov.combinatorics.subsets.SubSetGenerator;


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
	  
	  // Generate the results
	  List<CombinatoricsVector<CombinatoricsVector<String>>> result = generateComplexComposition(originalList, 2 );
	  
	  
	  for (CombinatoricsVector<CombinatoricsVector<String>> item : result)
		  System.out.println( item );
  }
  
  
  /**
   * This method generates the n compositions (with fixed length)
   * in relation with permutations.
   *
   * Example:
   *	From the ordered list {a,b,b,c} of length 4, generate all compositions
   *	with 2 (ordered) sublists which include all the elements of the
   *	original list in sum.
   *	{{a},{b,b,c}}
   *	{{a,b},{b,c}}
   *	{{a,c},{b,b}}
   *	{{a,b,b},{c}}
   *	{{a,b,c},{b}}
   *	{{b},{a,b,c}}
   *	{{b,b},{a,c}}
   *	{{b,c},{a,b}}
   *	{{b,b,c},{a}}
   *	{{c},{a,b,b}}
   *
   * @param n 		A list of initial values
   * @param compositionLength	The length of the combinations
   * @return	The list of the compositionLength-combinations
   */
  public static List<CombinatoricsVector<CombinatoricsVector<String>>> generateComplexComposition(List<String> originalList, int n)
  {
	  // This is result list
	  List<CombinatoricsVector<CombinatoricsVector<String>>> result = new ArrayList<CombinatoricsVector<CombinatoricsVector<String>>>();
	  
	  // 1. Generate all permutations from the original list
	  Generator<String> permutationGenerator = new PermutationGenerator<String>( new CombinatoricsVector<String>(originalList) );
	  List<CombinatoricsVector<String>> permutationsList = permutationGenerator.generateAllObjects();
	
	  // 2. Generate all subsets of the original list
	  Generator<String> subSetGenerator = new SubSetGenerator<String>(new CombinatoricsVector<String>(originalList));
	  List<CombinatoricsVector<String>> allSubsetsList = subSetGenerator.generateAllObjects();
	
	  /**
	   * 3. Generate n-combinations of all generated subsets
	   */
	  
	  // 3.1 Create a vector of all subsets
	  CombinatoricsVector<CombinatoricsVector<String>> allSubsetsVector = new CombinatoricsVector<CombinatoricsVector<String>>(allSubsetsList);
	  
	  // 3.2 Create a simple generator to get all n-combination of the subsets
	  Generator<CombinatoricsVector<String>> combinationGenerator = new SimpleCombinationGenerator<CombinatoricsVector<String>>(allSubsetsVector, n);
	  
	  // 3.3 Get an iterator
	  Iterator<CombinatoricsVector<CombinatoricsVector<String>>> combinationIterator = combinationGenerator.createIterator();
	  
	  // 3.4 Create an intermediate combinations list to store the candidates
	  List<CombinatoricsVector<CombinatoricsVector<String>>> intermediateCombinations = new ArrayList<CombinatoricsVector<CombinatoricsVector<String>>>();
	  
	  // 3.5 Search the combinations which are contained in the permutations
	  while (combinationIterator.hasNext()) {
		  
		  // 3.5.1 Get a combination
		  CombinatoricsVector<CombinatoricsVector<String>> combination = combinationIterator.next();
	    
		  /**
		   * 3.5.2 Construct a list/combinatorial vector of all elements from the combination.
		   */
		  
		  // 3.5.2.1 Put the first element into the list 
		  List<String> list = new ArrayList<String>(combination.getValue(0).getVector());
		  
		  // 3.5.2.2 Add all other elements into the list
		  for (int index = 1; index < combination.getSize(); index ++)
	    	list.addAll( combination.getValue(index).getVector() );
	    
		  // 3.5.2.3 Create a vector which contains all elements from the combination
		  CombinatoricsVector<String> vector = new CombinatoricsVector<String>(list);
	    
		  // 3.5.3 Add the combination into the intermediate result if the constructed vector exists in the permutations list
		  if (permutationsList.contains(vector) )
		  {
	    	CombinatoricsVector<CombinatoricsVector<String>> intermediateCombination = new CombinatoricsVector<CombinatoricsVector<String>>(combination);
	
	    	if (!intermediateCombinations.contains( intermediateCombination))
	    		intermediateCombinations.add( intermediateCombination );
		  }
	  }

	  // 4. Generate permutations for each found combination and add them into the final result list
	  for (CombinatoricsVector<CombinatoricsVector<String>> combination : intermediateCombinations)
	  {
		
		  // 4.1 Create a permutation generator for each intermediate combination
		  Generator<CombinatoricsVector<String>> permutationGen = new PermutationGenerator<CombinatoricsVector<String>>( new CombinatoricsVector<CombinatoricsVector<String>>(combination) );

		  // 4.2 Generate all permutations
		  List<CombinatoricsVector<CombinatoricsVector<String>>> permutations = permutationGen.generateAllObjects();
		  
		  // 4.3 Add then into the final result list
		  result.addAll(permutations);
	  }
	  
	  return result;
  }

}
