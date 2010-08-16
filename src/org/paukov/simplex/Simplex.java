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

public class Simplex {

    protected final Problem problem;
    protected final List<Token> tokens;

    public Simplex(List<Token> tokens) throws SimplexException {
	this.tokens = tokens;
	problem = new Problem(tokens);
    }

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

    private void calculateNewEntry() {
	Variable columnPivo = getColumnPivo();
	Constraint linePivo = (Constraint) getLinePivo(columnPivo);

	Double numberPivo = linePivo.getCoefficients().get(columnPivo);

	log("Line Pivo: " + linePivo);

	linePivo.div(numberPivo);
	linePivo.setBasicVariable(columnPivo);
	Constraint linePivoClone = linePivo.clone();

	linePivoClone.mult(problem.getObjective().getCoefficients().get(
		columnPivo));
	problem.getObjective().subtract(linePivoClone);
	linePivoClone = linePivo.clone();

	for (Constraint constraint : problem.getConstraints()) {
	    if (!constraint.isConstraintImpossible() && constraint != linePivo) {
		linePivoClone.mult(constraint.getCoefficients()
			.get(columnPivo));
		constraint.subtract(linePivoClone);
		linePivoClone = linePivo.clone();
	    }
	}
    }

    private boolean isSolutionOptimal() {

	for (Variable variable : problem.getVariables())
	    if (getCoefficientOfObjetive(variable) < 0.0)
		return false;

	return true;

    }

    private Variable getColumnPivo() {

	Variable colunaPivo = problem.getVariables().get(0);

	boolean allPositives = true;
	for (Variable variables : problem.getVariables()) {
	    if (getCoefficientOfObjetive(variables) < getCoefficientOfObjetive(colunaPivo))
		colunaPivo = variables;
	    if (getCoefficientOfObjetive(variables) < 0)
		allPositives = false;
	}
	return allPositives ? null : colunaPivo;
    }

    private Double getCoefficientOfObjetive(Variable variable) {
	return problem.getObjective().getCoefficients().get(variable);
    }

    private Equation getLinePivo(Variable column) {

	Equation linePivo = problem.getConstraints().get(0);

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
		    linePivo = problem.getConstraints().get(i);
		}
	    }
	}
	return linePivo;
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