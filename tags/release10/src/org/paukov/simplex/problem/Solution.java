package org.paukov.simplex.problem;

import java.util.HashMap;

import org.paukov.simplex.exception.SimplexException;

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
     * 
     * @param problem
     *            Problem object
     * @param obj
     *            Objective function object
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

    public Double getResult(Variable var) {
	return results.get(var);
    }

    public Double getResult(String varName) {
	for (Variable var : problem.getVariablesOriginals()) {
	    if (varName.equals(var.toString()))
		return results.get(var);
	}

	throw new IllegalArgumentException("Variable <" + varName
		+ "> is not found in the problem");
    }

    public Double getOptimalObjectiveFunctionValue() {
	return objectiveFunction.valueIndependance;
    }

    @Override
    public String toString() {

	StringBuffer string = new StringBuffer();

	string.append("Optimal objective function value = "
		+ objectiveFunction.valueIndependance);
	string.append("\nOptimal variable values:\n");
	for (Variable var : problem.getVariablesOriginals()) {
	    string.append(var.toString() + " = " + results.get(var) + "\n");
	}
	string.append("Slack variables or expressions\n");
	for (Variable var : problem.getVariablesFolga()) {
	    string.append(var.toString() + " = " + results.get(var) + "\n");
	}
	string.append("End");

	return string.toString();

    }
}
