package simplex;

import java.util.List;

import simplex.exception.SimplexException;
import simplex.parser.Token;
import simplex.problem.Constraint;
import simplex.problem.Equation;
import simplex.problem.Problem;
import simplex.problem.Solution;
import simplex.problem.Variable;
import simplex.problem.Problem.Type;

public class Simplex {

    public final Problem problemSimplex;
    public final List<Token> tokens;

    public Simplex(List<Token> tokens) throws SimplexException {
	this.tokens = tokens;
	problemSimplex = new Problem(tokens);
    }

    public Solution resolverSimplex() {
	if (problemSimplex.getType() == Type.MAX)
	    problemSimplex.getObjective().mult(-1.0);

	int iteracao = 0;
	while (!isSolutionOptimal()) {
	    System.out.printf("Iteration %d\n", iteracao);
	    System.out.println(problemSimplex.getObjective());
	    for (Constraint constraint : problemSimplex.getConstraints())
		if (!constraint.isConstraintImpossible())
		    System.out.println(constraint);

	    calcularNovaEntrada();
	    System.out.println("...:::...:::...:::...");
	    iteracao++;
	}
	System.out.printf("Iteration %d\n", iteracao);
	System.out.println(problemSimplex.getObjective());
	for (Constraint constraint : problemSimplex.getConstraints())
	    if (!constraint.isConstraintImpossible())
		System.out.println(constraint);
	System.out.println("...:::...:::...:::...");

	Solution solutionSimplex = new Solution(problemSimplex, problemSimplex.getObjective());
	for (Variable variable : problemSimplex.getVariables()) {
	    solutionSimplex.putResults(variable, 0.0);
	}
	for (Constraint constraint : problemSimplex.getConstraints()) {
	    solutionSimplex.putResults(constraint.getBasicVariable(),
		    constraint.getValueIndependance());
	}
	return solutionSimplex;
    }

    private void calcularNovaEntrada() {
	Variable colunaPivo = getColunaPivo();
	Constraint linhaPivo = (Constraint) getLinhaPivo(colunaPivo);

	Double numeroPivo = linhaPivo.getCoefficients().get(colunaPivo);
	System.out.println("Linha Pivo: " + linhaPivo);
	linhaPivo.div(numeroPivo);
	linhaPivo.setBasicVariable(colunaPivo);
	Constraint linhaPivoClone = linhaPivo.clone();

	linhaPivoClone.mult(problemSimplex.getObjective().getCoefficients()
		.get(colunaPivo));
	problemSimplex.getObjective().subtract(linhaPivoClone);
	linhaPivoClone = linhaPivo.clone();

	for (Constraint constraint : problemSimplex.getConstraints()) {
	    if (!constraint.isConstraintImpossible() && constraint != linhaPivo) {
		linhaPivoClone.mult(constraint.getCoefficients()
			.get(colunaPivo));
		constraint.subtract(linhaPivoClone);
		linhaPivoClone = linhaPivo.clone();
	    }
	}
    }

    private boolean isSolutionOptimal() {

	for (Variable variable : problemSimplex.getVariables())
	    if (getCoeficienteEmObjetivo(variable) < 0.0)
		return false;

	return true;

    }

    private Variable getColunaPivo() {

	Variable colunaPivo = problemSimplex.getVariables().get(0);

	boolean allPositives = true;
	for (Variable variables : problemSimplex.getVariables()) {
	    if (getCoeficienteEmObjetivo(variables) < getCoeficienteEmObjetivo(colunaPivo))
		colunaPivo = variables;
	    if (getCoeficienteEmObjetivo(variables) < 0)
		allPositives = false;
	}
	return allPositives ? null : colunaPivo;
    }

    private Double getCoeficienteEmObjetivo(Variable variable) {
	return problemSimplex.getObjective().getCoefficients().get(variable);
    }

    private Equation getLinhaPivo(Variable colunaPivo) {

	Equation linhaPivo = problemSimplex.getConstraints().get(0);

	double minimo = Double.POSITIVE_INFINITY;
	for (int i = 0; i < problemSimplex.getConstraints().size(); i++) {
	    if (getCoeficienteDaRestricao(colunaPivo, i) > 0
		    && (!problemSimplex.getConstraints().get(i)
			    .isConstraintImpossible())) {
		double possivelMinimo = (problemSimplex.getConstraints().get(i)
			.getValueIndependance())
			/ getCoeficienteDaRestricao(colunaPivo, i);
		if (minimo > possivelMinimo) {
		    minimo = possivelMinimo;
		    linhaPivo = problemSimplex.getConstraints().get(i);
		}
	    }
	}
	return linhaPivo;
    }

    // +1 +0 +1 +0 +0 = +3
    // +0 +1 +0 +1 +0 = +4
    // +1 +2 +0 +0 +1 = +9
    private Double getCoeficienteDaRestricao(Variable colunaPivo, int i) {
	return problemSimplex.getConstraints().get(i).getCoefficients().get(
		colunaPivo);
    }

}