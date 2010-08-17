package org.paukov.simplex.problem;

import java.util.HashMap;

/**
 * This class represents the solution
 *
 */
public class Solution {

    /**
     * The problem
     */
    protected final Problem problem;
    
    /**
     * The objective function
     */
    protected final Objective objectiveFunction;
    
    /**
     * Results
     */
    protected final HashMap<Variable, Double> results = new HashMap<Variable, Double>();

    /**
     * Constructor
     * @param problem Problem object
     * @param obj Objective function object
     */
    public Solution(Problem problem, Objective obj) {
	this.problem = problem;
	this.objectiveFunction = obj;
    }

    /**
     * @return the problem
     */
    public Problem getProblem() {
	return problem;
    }

    /**
     * @return the objectiveFunction
     */
    public Objective getObjectiveFunction() {
	return objectiveFunction;
    }

    /**
     * @return the results
     */
    public HashMap<Variable, Double> getResults() {
	return results;
    }

    public Double putResults(Variable var, Double val) {
	return results.put(var, val);
    }

    @Override
    public String toString() {

	StringBuffer string = new StringBuffer();

	string.append("Optimal objective function value = "
		+ objectiveFunction.valueIndependance);
	string.append("\nOptimal variable values:\n");
	for (Variable var : problem.getVariablesOriginals()) {
	    string.append(var.toString() + " = " + results.get(var)
		    + "\n");
	}
	string.append("VARIAVEIS DE FOLGA OU EXCESSO\n");
	for (Variable var : problem.getVariablesFolga()) {
	    string.append(var.toString() + " = " + results.get(var)
		    + "\n");
	}
	string.append("End");

	return string.toString();

    }
}
