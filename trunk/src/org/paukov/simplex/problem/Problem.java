package org.paukov.simplex.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.paukov.simplex.exception.SimplexException;
import org.paukov.simplex.parser.Token;
import org.paukov.simplex.problem.Constraint.SignOfConstraint;

public class Problem {

    public enum Type {
	MAX, MIN
    }

    private final List<Variable> variables = new ArrayList<Variable>();

    private final List<Variable> variablesOriginals = new ArrayList<Variable>();
    private final List<Variable> variablesFolga = new ArrayList<Variable>();
    private final List<Variable> variablesAuxiliares = new ArrayList<Variable>();

    private Type type;
    private Objective objective = new Objective(variables);
    private ArrayList<Constraint> constraints = new ArrayList<Constraint>();

    public Problem(List<Token> tokens) throws SimplexException {

	interpretVariables(tokens);
	interpretObjectiveFunction(tokens);
	interpretConstrains(tokens);
	processConditions();

	if (!isEnougthConstraints())
	    throw new SimplexException("Error: Not enought constraints", this);

	insertAuxiliareVariables();
    }

    /**
     * @return the variablesAuxiliares
     */
    public List<Variable> getVariablesAuxiliares() {
	return variablesAuxiliares;
    }

    /**
     * @return the type
     */
    public Type getType() {
	return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Type type) {
	this.type = type;
    }

    /**
     * @return the variables
     */
    public List<Variable> getVariables() {
	return variables;
    }

    /**
     * @return the variablesOriginals
     */
    public List<Variable> getVariablesOriginals() {
	return variablesOriginals;
    }

    /**
     * @return the variablesFolga
     */
    public List<Variable> getVariablesFolga() {
	return variablesFolga;
    }

    /**
     * @return the objective
     */
    public Objective getObjective() {
	return objective;
    }

    /**
     * @return the constraints
     */
    public ArrayList<Constraint> getConstraints() {
	return constraints;
    }

    private boolean containsVariavel(Token token) {
	for (Variable t : getVariables())
	    if (t.getToken().equals(token))
		return true;
	return false;
    }

    private void interpretObjectiveFunction(List<Token> temp) {
	for (int i = 0; i < temp.size(); i++) {
	    Token token = temp.get(i);
	    if (token.getId() == Token.MAX || token.getId() == Token.MIN) {
		switch (token.getId()) {
		case Token.MAX:
		    setType(Type.MAX);
		    break;
		case Token.MIN:
		    setType(Type.MIN);
		    break;
		default:
		    break;
		}
		int j = i;
		ArrayList<Token> tokensObjetivo = new ArrayList<Token>();
		for (; j < temp.size(); j++) {
		    Token token1 = temp.get(j);
		    if (token1.getId() == Token.INICIO_LINHA) {
			break;
		    }
		    tokensObjetivo.add(token1);
		}
		objective = new Objective(getVariables());
		for (int k = 0; k < getVariables().size(); k++) {
		    getObjective().coefficients.put(getVariables().get(k), 0.0);
		}
		processObjective(tokensObjetivo, getObjective());
		break;
	    }
	}
    }

    private void interpretConstrains(List<Token> temp) {
	List<ArrayList<Token>> restricoes = new ArrayList<ArrayList<Token>>();
	for (int i = 0; i < temp.size(); i++) {
	    Token token = temp.get(i);
	    if (token.getId() == Token.SUBJECT_TO) {
		int j = i + 2;
		ArrayList<Token> restricao = new ArrayList<Token>();
		for (; j < temp.size(); j++) {
		    if (temp.get(j).getId() == Token.END) {
			break;
		    } else if (temp.get(j).getId() == Token.INICIO_LINHA) {
			restricoes.add(restricao);
			restricao = new ArrayList<Token>();
		    } else {
			restricao.add(temp.get(j));
		    }
		}
	    }
	}
	constraints = new ArrayList<Constraint>(restricoes.size());
	for (ArrayList<Token> t : restricoes) {
	    Constraint ta = new Constraint(getVariables());
	    for (int k = 0; k < getVariables().size(); k++) {
		ta.coefficients.put(getVariables().get(k), new Double(0));
	    }
	    processConstraints(t, ta, 0, t.size());
	    getConstraints().add(ta);
	}
    }

    private void interpretVariables(List<Token> arrayList) {
	for (Token token : arrayList)
	    if (token.getId() == Token.VARIABLE)
		if (!containsVariavel(token)) {
		    Variable novaVariavel = new Variable(token);
		    getVariables().add(novaVariavel);
		    getVariablesOriginals().add(novaVariavel);
		}
    }

    private void processObjective(ArrayList<Token> t, Objective ta) {
	double value = 1;
	for (Token token : t) {
	    if (token.getId() == Token.SINAL) {
		value = Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == Token.NUMERO) {
		value *= Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == Token.VARIABLE) {
		for (int k = 0; k < getVariables().size(); k++) {
		    if (getVariables().get(k).getToken().equals(token)) {
			ta.coefficients.put(getVariables().get(k), value);
			break;
		    }
		}
		value = 1;
	    }
	}
    }

    private void processConstraints(ArrayList<Token> t, Constraint ta, int i,
	    int size) {
	double value = 1;
	boolean independentFoundValue = false;
	for (Token token : t) {
	    if (independentFoundValue) {
		ta.valueIndependance = Double.parseDouble(token.getValue()
			.toString());
		break;
	    }
	    if (token.getId() == Token.SINAL) {
		value = Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == Token.NUMERO) {
		value *= Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == Token.VARIABLE) {
		for (int k = 0; k < getVariables().size(); k++) {
		    if (getVariables().get(k).getToken().equals(token)) {
			ta.coefficients.put(getVariables().get(k), value);
			break;
		    }
		}
		value = 1;
	    } else if (token.getId() == Token.MAIOR_IGUAL
		    || token.getId() == Token.IGUAL
		    || token.getId() == Token.MENOR_IGUAL) {
		switch (token.getId()) {
		case Token.MAIOR_IGUAL:
		    ta.signOfConstraint = SignOfConstraint.MORE_OR_EQUAL;
		    break;
		case Token.IGUAL:
		    ta.signOfConstraint = SignOfConstraint.EQUAL;
		    break;
		case Token.MENOR_IGUAL:
		    ta.signOfConstraint = SignOfConstraint.LESS_OR_EQUAL;
		    break;
		}
		independentFoundValue = true;
	    }
	}
    }

    private void processConditions() {

	for (Constraint constraint : getConstraints()) {
	    boolean isMainCondition = false;
	    boolean found = false;
	    for (Variable var : constraint.variables) {
		double currentCcoefficient = constraint.coefficients.get(var);
		if (!found) {
		    if (currentCcoefficient == 1) {
			found = true;
		    } else if (currentCcoefficient == 0) {
			continue;
		    } else {
			isMainCondition = true;
			break;
		    }
		} else if (currentCcoefficient != 0) {
		    isMainCondition = true;
		    break;
		}
	    }
	    if (isMainCondition
		    || constraint.signOfConstraint != SignOfConstraint.MORE_OR_EQUAL
		    || constraint.valueIndependance != 0)
		continue;

	    constraint.constraintIsImpossible = true;
	}
    }

    private boolean isEnougthConstraints() {

	HashMap<Variable, Boolean> variables = new HashMap<Variable, Boolean>();

	for (Variable var : getVariables()) {
	    variables.put(var, false);
	}

	for (Constraint constraint : getConstraints())
	    if (constraint.constraintIsImpossible)
		for (Variable var : constraint.variables)
		    if (constraint.coefficients.get(var) == 1) {
			variables.put(var, true);
			break;
		    }

	for (Variable var : getVariables())
	    if (variables.get(var) == false)
		return false;

	return true;
    }

    private void insertAuxiliareVariables() {
	
	int countOfSlackVariables = 0;
	int countOfAuxiliaryVariables = 0;
	for (Constraint constraint : getConstraints()) {
	    if (!constraint.constraintIsImpossible
		    && constraint.signOfConstraint != SignOfConstraint.EQUAL) {
		countOfSlackVariables++;
	    }
	    if (!constraint.constraintIsImpossible
		    && constraint.signOfConstraint != SignOfConstraint.LESS_OR_EQUAL) {
		countOfAuxiliaryVariables++;
	    }
	}

	List<Variable> newSlackVariables = createNewSlackVariables(countOfSlackVariables);

	insertNewSlackVariables(newSlackVariables);

	List<Variable> newAuxiliaryVariables = createNewAuxiliaryVariables(countOfAuxiliaryVariables);

	insertNewAuxiliaryVariables(newAuxiliaryVariables);

	for (Constraint constraint : getConstraints()) {
	    if (!constraint.constraintIsImpossible)
		constraint.signOfConstraint = SignOfConstraint.EQUAL;
	}
    }

    private List<Variable> createNewAuxiliaryVariables(
	    int countNewAuxiliaryVariables) {
	List<Variable> novasVariaveisAuxiliares = new ArrayList<Variable>(
		countNewAuxiliaryVariables);
	for (int j = 1; j <= countNewAuxiliaryVariables; j++) {
	    Token token = new Token(Token.VARIABLE, "A" + j, "A" + j);
	    Variable var = new Variable(token);
	    novasVariaveisAuxiliares.add(var);
	    getVariables().add(var);
	    getVariablesAuxiliares().add(var);
	    getObjective().coefficients.put(var, -Integer.MAX_VALUE * 1.0);
	}
	return novasVariaveisAuxiliares;
    }

    private List<Variable> createNewSlackVariables(int countOfSlackVariables) {
	ArrayList<Variable> newSlackVariables = new ArrayList<Variable>(
		countOfSlackVariables);
	for (int j = 1; j <= countOfSlackVariables; j++) {
	    Token token = new Token(Token.VARIABLE, "XF" + j, "XF" + j);
	    Variable var = new Variable(token);
	    newSlackVariables.add(var);
	    getVariables().add(var);
	    getVariablesFolga().add(var);
	    getObjective().coefficients.put(var, 0.0);
	}
	return newSlackVariables;
    }

    private void insertNewAuxiliaryVariables(
	    List<Variable> newAuxiliaryVariables) {
	int currentVariable = 0;
	boolean addedToThatConstraint = false;
	for (Constraint restricao : getConstraints()) {
	    for (int i = 0; i < newAuxiliaryVariables.size(); i++) {
		if (currentVariable == i && !addedToThatConstraint) {
		    if (!restricao.constraintIsImpossible
			    && restricao.signOfConstraint != SignOfConstraint.LESS_OR_EQUAL) {
			restricao.coefficients.put(
				newAuxiliaryVariables.get(i), 1.0);
			restricao.basicVariable = newAuxiliaryVariables.get(i);
			currentVariable++;
			addedToThatConstraint = true;
		    } else
			restricao.coefficients.put(
				newAuxiliaryVariables.get(i), 0.0);
		} else
		    restricao.coefficients.put(newAuxiliaryVariables.get(i),
			    0.0);
	    }
	    addedToThatConstraint = false;
	}
    }

    private void insertNewSlackVariables(List<Variable> newSlackVariables) {
	int currentVariable = 0;
	boolean addedToThatConstraint = false;
	for (Constraint constraint : getConstraints()) {
	    for (int i = 0; i < newSlackVariables.size(); i++) {
		if (currentVariable == i && !addedToThatConstraint) {
		    if (!constraint.constraintIsImpossible
			    && constraint.signOfConstraint != SignOfConstraint.EQUAL) {
			constraint.coefficients
				.put(
					newSlackVariables.get(i),
					constraint.signOfConstraint != SignOfConstraint.LESS_OR_EQUAL ? -1.0
						: 1.0);
			constraint.basicVariable = newSlackVariables.get(i);
			currentVariable++;
			addedToThatConstraint = true;
		    } else
			constraint.coefficients.put(newSlackVariables.get(i),
				0.0);
		} else
		    constraint.coefficients.put(newSlackVariables.get(i), 0.0);

	    }
	    addedToThatConstraint = false;
	}
    }

    @Override
    public String toString() {
	StringBuffer string = new StringBuffer();

	string.append("-- Problem Simplex --");
	string.append("\nVariables           : " + this.variables);
	string.append("\nVariables Originals : " + this.variablesOriginals);
	string.append("\nVariables de Folga  : " + this.variablesFolga);
	string.append("\nVariables Auxiliares: " + this.variablesAuxiliares);
	string.append("\nObjetive function     : ");
	string.append(objective.toString().trim());
	string.append("\nObjetive            : " + type);
	string.append("\nConstrains          :\n");

	for (Constraint constraint : constraints) {
	    string.append(constraint);
	    string.append("\n");
	}
	string.append("-- End of Problem  --");
	return string.toString();
    }

}
