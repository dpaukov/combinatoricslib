package simplex.problem;

import java.util.HashMap;

public class Solution {

    public Problem problem;
    public Objective objectiveFunction;
    public HashMap<Variable, Double> results = new HashMap<Variable, Double>();

    @Override
    public String toString() {

	StringBuffer string = new StringBuffer();

	string.append("Optimal objective function value = "
		+ objectiveFunction.valueindependance);
	string.append("\nOptimal variable values:\n");
	for (Variable variavel : problem.getVariablesOriginals()) {
	    string.append(variavel.toString() + " = " + results.get(variavel)
		    + "\n");
	}
	string.append("VARIAVEIS DE FOLGA OU EXCESSO\n");
	for (Variable variavel : problem.getVariablesFolga()) {
	    string.append(variavel.toString() + " = " + results.get(variavel)
		    + "\n");
	}
	string.append("End");

	return string.toString();

    }
}
