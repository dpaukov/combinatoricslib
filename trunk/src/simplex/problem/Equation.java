package simplex.problem;

import java.util.HashMap;
import java.util.List;

public abstract class Equation {

    public List<Variable> variables;
    public HashMap<Variable, Double> coefficients;
    public Double valueindependance;

    public Equation(HashMap<Variable, Double> coeffs, List<Variable> variables) {
	this.coefficients = coeffs;
	this.variables = variables;
    }

    public void mult(double multiplicator) {

	for (Variable variabel : variables) {
	    this.coefficients.put(variabel, this.coefficients.get(variabel)
		    * multiplicator);
	}

	this.valueindependance = this.valueindependance * multiplicator;
    }

    public void div(double divisor) {
	this.mult(1 / divisor);
    }

    public void add(Equation equation) {
	for (Variable variable : variables) {
	    this.coefficients.put(variable, this.coefficients.get(variable)
		    + equation.coefficients.get(variable));
	}
	this.valueindependance = this.valueindependance
		+ equation.valueindependance;
    }

    public void subtract(Equation equation) {
	equation.mult(-1.0);
	this.add(equation);
    }

}
