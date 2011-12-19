package org.paukov.simplex.problem;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the objective function
 *
 */
public class Objective extends Equation {

    
    /**
     * Constructor
     * @param vars Variables
     * @param coeffs Coefficients
     */
    public Objective(List<Variable> vars,
	    HashMap<Variable, Double> coeffs) {
	super(coeffs, vars);
	super.valueIndependance = 0.0;
    }

    /**
     * Constructor
     * @param vars Variables
     */
    public Objective(List<Variable> vars) {
	super(new HashMap<Variable, Double>(), vars);
	super.valueIndependance = 0.0;
    }

    @Override
    public String toString() {
	StringBuffer string = new StringBuffer();
	DecimalFormat format = new DecimalFormat("#.#####");
	Iterator<Variable> it = variables.iterator();

	while (it.hasNext()) {
	    Variable var = it.next();

	    string.append((coefficients.get(var) < 0 ? " -" : " +")
		    + format.format(Math.abs(coefficients.get(var))));
	    string.append(var.toString());
	}
	string.append(" = " + format.format(valueIndependance));
	return string.toString();
    }

    /**
     * Creates the clone object
     */
    public Objective clone() {

	Objective obj = new Objective(variables);
	for (Variable vars : variables) 
	    obj.coefficients.put(vars, coefficients.get(vars));

	obj.valueIndependance = valueIndependance;
	return obj;
    }

}
