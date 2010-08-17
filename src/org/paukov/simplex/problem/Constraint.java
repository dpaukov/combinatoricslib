package org.paukov.simplex.problem;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the constraints
 * 
 */
public class Constraint extends Equation {

    public enum SignOfConstraint {
	EQUAL, MORE_OR_EQUAL, LESS_OR_EQUAL
    }

    protected boolean constraintIsImpossible = false;
    protected SignOfConstraint signOfConstraint;
    protected Variable basicVariable;

    /**
     * Constructor
     * 
     * @param variables
     *            List of variables
     */
    public Constraint(List<Variable> variables) {
	super(new HashMap<Variable, Double>(), variables);
    }

    /**
     * @return the constraintIsImpossible
     */
    public boolean isConstraintImpossible() {
	return constraintIsImpossible;
    }

    /**
     * @param constraintIsImpossible
     *            the constraintIsImpossible to set
     */
    public void setConstraintIsImpossible(boolean constraintIsImpossible) {
	this.constraintIsImpossible = constraintIsImpossible;
    }

    /**
     * @return the signOfConstraint
     */
    public SignOfConstraint getSignOfConstraint() {
	return signOfConstraint;
    }

    /**
     * @param signOfConstraint
     *            the signOfConstraint to set
     */
    public void setSignOfConstraint(SignOfConstraint signOfConstraint) {
	this.signOfConstraint = signOfConstraint;
    }

    /**
     * @return the basicVariable
     */
    public Variable getBasicVariable() {
	return basicVariable;
    }

    /**
     * @param basicVariable
     *            the basicVariable to set
     */
    public void setBasicVariable(Variable basicVariable) {
	this.basicVariable = basicVariable;
    }

    @Override
    public String toString() {
	if (constraintIsImpossible)
	    return "";
	StringBuffer string = new StringBuffer();
	DecimalFormat format = new DecimalFormat("#.#####");
	Iterator<Variable> it = variables.iterator();

	while (it.hasNext()) {
	    Variable variavel = it.next();

	    string.append((coefficients.get(variavel) < 0 ? " -" : " +")
		    + format.format(Math.abs(coefficients.get(variavel))));
	    // string.append( variavel.toString());
	}
	switch (signOfConstraint) {
	case EQUAL:
	    string.append(" = ");
	    break;
	case MORE_OR_EQUAL:
	    string.append(" >= ");
	    break;
	case LESS_OR_EQUAL:
	    string.append(" <= ");
	    break;
	}
	string.append(((valueIndependance) < 0 ? " -" : " +")
		+ format.format(Math.abs(valueIndependance)));
	string.append(" B:" + basicVariable);
	string.append(constraintIsImpossible ? " (Constraint is impossible)"
		: "");
	return string.toString().trim();
    }

    public Constraint clone() {

	Constraint restricao = new Constraint(variables);

	for (Variable var : variables) {
	    restricao.coefficients.put(var, coefficients.get(var));
	}

	restricao.constraintIsImpossible = constraintIsImpossible;
	restricao.signOfConstraint = signOfConstraint;
	restricao.valueIndependance = valueIndependance;

	return restricao;

    }

}