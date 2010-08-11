package simplex.problem;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Constraint extends Equation {

    public enum SignOfConstraint {
	EQUAL, MORE_OR_EQUAL, LESS_OR_EQUAL
    }

    public boolean restricaoNaoPraticavelParaCalculo;
    public SignOfConstraint signOfConstraint;
    public Variable variavelBasica;

    public Constraint(List<Variable> variavies) {
	super(new HashMap<Variable, Double>(), variavies);
	restricaoNaoPraticavelParaCalculo = false;
    }

    @Override
    public String toString() {
	if (restricaoNaoPraticavelParaCalculo)
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
	string.append(((valueindependance) < 0 ? " -" : " +")
		+ format.format(Math.abs(valueindependance)));
	string.append(" B:" + variavelBasica);
	string
		.append(restricaoNaoPraticavelParaCalculo ? " (Restrição Não Praticável)"
			: "");
	return string.toString().trim();
    }

    public Constraint clone() {
	Constraint restricao = new Constraint(variables);

	for (Variable variavel : variables) {
	    restricao.coefficients.put(variavel, coefficients.get(variavel));
	}

	restricao.restricaoNaoPraticavelParaCalculo = restricaoNaoPraticavelParaCalculo;
	restricao.signOfConstraint = signOfConstraint;
	restricao.valueindependance = valueindependance;

	return restricao;

    }

}