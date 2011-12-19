package org.paukov.simplex;

import java.util.List;

import org.paukov.simplex.exception.SimplexException;
import org.paukov.simplex.parser.Token;
import org.paukov.simplex.problem.Constraint;
import org.paukov.simplex.problem.Equation;
import org.paukov.simplex.problem.Problem;
import org.paukov.simplex.problem.Solution;
import org.paukov.simplex.problem.Variable;
import org.paukov.simplex.problem.Problem.Type;

/**
 * This class encapsulates the Simplex method resolver of the Linear problem
 *
 */
public class Simplex {

    /**
     * Problem to resolve
     */
    protected final Problem problem;
    

    /**
     * Constructor
     * @param tokens List of tokens, which describe the problem
     * @throws SimplexException
     */
    public Simplex(List<Token> tokens) throws SimplexException {
	problem = new Problem(tokens);
    }

    /**
     * Resolves the problem
     * @return The solution
     */
    public Solution resolve() {
	
	if (problem.getType() == Type.MAX)
	    problem.getObjective().mult(-1.0);

	int iteration = 0;
	while (!isSolutionOptimal()) {

	    log("Iteration " + iteration);
	    log(problem.getObjective().toString());

	    for (Constraint constraint : problem.getConstraints())
		if (!constraint.isConstraintImpossible())
		    log(constraint.toString());

	    calculateNewEntry();

	    log("---------------------------");
	    iteration++;
	}

	log("Iteration " + iteration);
	log(problem.getObjective().toString());

	for (Constraint constraint : problem.getConstraints())
	    if (!constraint.isConstraintImpossible())
		log(constraint.toString());

	log("---------------------------");

	Solution solution = new Solution(problem, problem.getObjective());
	for (Variable variable : problem.getVariables()) {
	    solution.putResults(variable, 0.0);
	}
	for (Constraint constraint : problem.getConstraints()) {
	    solution.putResults(constraint.getBasicVariable(), constraint
		    .getValueIndependance());
	}
	return solution;
    }

    /**
     * Calculates the new entry
     */
    private void calculateNewEntry() {
	
	Variable columnPivot = getColumn();
	Constraint linePivot = (Constraint) getLine(columnPivot);

	Double numberPivo = linePivot.getCoefficients().get(columnPivot);

	log("Line Pivot: " + linePivot);

	linePivot.div(numberPivo);
	linePivot.setBasicVariable(columnPivot);
	Constraint linePivotClone = linePivot.clone();

	linePivotClone.mult(problem.getObjective().getCoefficients().get(
		columnPivot));
	problem.getObjective().subtract(linePivotClone);
	linePivotClone = linePivot.clone();

	for (Constraint constraint : problem.getConstraints()) {
	    if (!constraint.isConstraintImpossible() && constraint != linePivot) {
		linePivotClone.mult(constraint.getCoefficients()
			.get(columnPivot));
		constraint.subtract(linePivotClone);
		linePivotClone = linePivot.clone();
	    }
	}
    }

    /**
     * Returns true if the solution is optimal
     */
    private boolean isSolutionOptimal() {

	for (Variable variable : problem.getVariables())
	    if (getCoefficientOfObjetive(variable) < 0.0)
		return false;

	return true;

    }

    
    private Variable getColumn() {

	Variable columnPivot = problem.getVariables().get(0);

	boolean allPositives = true;
	for (Variable var : problem.getVariables()) {
	    if (getCoefficientOfObjetive(var) < getCoefficientOfObjetive(columnPivot))
		columnPivot = var;
	    if (getCoefficientOfObjetive(var) < 0)
		allPositives = false;
	}
	return allPositives ? null : columnPivot;
    }

    private Double getCoefficientOfObjetive(Variable variable) {
	return problem.getObjective().getCoefficients().get(variable);
    }

    private Equation getLine(Variable column) {

	Equation line = problem.getConstraints().get(0);

	double min = Double.POSITIVE_INFINITY;
	for (int i = 0; i < problem.getConstraints().size(); i++) {
	    if (getCoefficientOfConstraint(column, i) > 0
		    && (!problem.getConstraints().get(i)
			    .isConstraintImpossible())) {
		double possiveMin = (problem.getConstraints().get(i)
			.getValueIndependance())
			/ getCoefficientOfConstraint(column, i);
		if (min > possiveMin) {
		    min = possiveMin;
		    line = problem.getConstraints().get(i);
		}
	    }
	}
	return line;
    }

    // +1 +0 +1 +0 +0 = +3
    // +0 +1 +0 +1 +0 = +4
    // +1 +2 +0 +0 +1 = +9
    private Double getCoefficientOfConstraint(Variable column, int index) {
	return problem.getConstraints().get(index).getCoefficients()
		.get(column);
    }

    public static void log(String string) {
	System.out.println(string);
    }

}