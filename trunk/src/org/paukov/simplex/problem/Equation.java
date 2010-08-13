package org.paukov.simplex.problem;

import java.util.HashMap;
import java.util.List;

public abstract class Equation {

    protected final List<Variable> variables;
    protected final HashMap<Variable, Double> coefficients;
    protected Double valueIndependance;

    public Equation(HashMap<Variable, Double> coeffs, List<Variable> variables) {
	this.coefficients = coeffs;
	this.variables = variables;
    }

    /**
     * @return the valueIndependance
     */
    public Double getValueIndependance() {
	return valueIndependance;
    }

    /**
     * @param valueIndependance
     *            the valueIndependance to set
     */
    public void setValueIndependance(Double valueIndependance) {
	this.valueIndependance = valueIndependance;
    }

    /**
     * @return the variables
     */
    public List<Variable> getVariables() {
	return variables;
    }

    /**
     * @return the coefficients
     */
    public HashMap<Variable, Double> getCoefficients() {
	return coefficients;
    }

    public void mult(double multiplicator) {

	for (Variable variabel : variables) {
	    this.coefficients.put(variabel, this.coefficients.get(variabel)
		    * multiplicator);
	}

	this.valueIndependance = this.valueIndependance * multiplicator;
    }

    public void div(double divisor) {
	this.mult(1 / divisor);
    }

    public void add(Equation equation) {
	for (Variable variable : variables) {
	    this.coefficients.put(variable, this.coefficients.get(variable)
		    + equation.coefficients.get(variable));
	}
	this.valueIndependance = this.valueIndependance
		+ equation.valueIndependance;
    }

    public void subtract(Equation equation) {
	equation.mult(-1.0);
	this.add(equation);
    }

}
