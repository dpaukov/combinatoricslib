package org.paukov.simplex.problem;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Objective extends Equation {

    public Objective(List<Variable> variaveis,
	    HashMap<Variable, Double> coeficientes) {
	super(coeficientes, variaveis);
	super.valueIndependance = 0.0;
    }

    public Objective(List<Variable> variaveis) {
	super(new HashMap<Variable, Double>(), variaveis);
	super.valueIndependance = 0.0;
    }

    @Override
    public String toString() {
	StringBuffer string = new StringBuffer();
	DecimalFormat format = new DecimalFormat("#.#####");
	Iterator<Variable> it = variables.iterator();

	while (it.hasNext()) {
	    Variable variavel = it.next();

	    string.append((coefficients.get(variavel) < 0 ? " -" : " +")
		    + format.format(Math.abs(coefficients.get(variavel))));
	    string.append(variavel.toString());
	}
	string.append(" = " + format.format(valueIndependance));
	return string.toString();
    }

    public Objective clone() {
	Objective restricao = new Objective(variables);

	for (Variable variavel : variables) {
	    restricao.coefficients.put(variavel, coefficients.get(variavel));
	}

	restricao.valueIndependance = valueIndependance;

	return restricao;

    }

}
