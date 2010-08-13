package org.paukov.simplex.problem;

import java.util.HashMap;

public class Solution {

    protected final Problem problem;
    protected final Objective objectiveFunction;
    protected final HashMap<Variable, Double> results = new HashMap<Variable, Double>();

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
	for (Variable variavel : problem.getVariablesOriginals()) {
	    string.append(variavel.toString() + " = " + results.get(variavel)
		    + "\n");
	}
	string.append("VARIAVEIS DE FOLGA OU EXCESSO\n");
	for (Variable variavel : problem.getVariablesFolga()) {
	    string.append(variavel.toString() + " = " + results.get(variavel)
		    + "\n");
	}
	string.append("End");

	return string.toString();

    }
}
