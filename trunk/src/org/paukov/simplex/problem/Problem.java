package org.paukov.simplex.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.paukov.simplex.exception.SimplexException;
import org.paukov.simplex.parser.Token;
import org.paukov.simplex.parser.Token.TokenCode;
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

    private void interpretObjectiveFunction(List<Token> list) {

	for (int index = 0; index < list.size(); index++) {

	    Token token = list.get(index);
	    if (token.getId() == TokenCode.MAX
		    || token.getId() == TokenCode.MIN) {

		setType(Type.MAX);
		if (token.getId() == TokenCode.MIN)
		    setType(Type.MIN);

		int j = index;
		ArrayList<Token> tokensOfObjetive = new ArrayList<Token>();
		for (; j < list.size(); j++) {
		    Token tokenObj = list.get(j);
		    if (tokenObj.getId() == TokenCode.INITIAL_LINE) {
			break;
		    }
		    tokensOfObjetive.add(tokenObj);
		}
		objective = new Objective(getVariables());
		for (int k = 0; k < getVariables().size(); k++) {
		    getObjective().coefficients.put(getVariables().get(k), 0.0);
		}
		processObjective(tokensOfObjetive, getObjective());
		break;
	    }
	}
    }

    private void interpretConstrains(List<Token> list) {
	List<ArrayList<Token>> constraintsLocalList = new ArrayList<ArrayList<Token>>();
	for (int index = 0; index < list.size(); index++) {
	    Token token = list.get(index);
	    if (token.getId() == TokenCode.SUBJECT_TO) {
		int j = index + 2;
		ArrayList<Token> constraint = new ArrayList<Token>();
		for (; j < list.size(); j++) {
		    if (list.get(j).getId() == TokenCode.END) {
			break;
		    } else if (list.get(j).getId() == TokenCode.INITIAL_LINE) {
			constraintsLocalList.add(constraint);
			constraint = new ArrayList<Token>();
		    } else {
			constraint.add(list.get(j));
		    }
		}
	    }
	}
	constraints = new ArrayList<Constraint>(constraintsLocalList.size());
	for (ArrayList<Token> constr : constraintsLocalList) {
	    Constraint ta = new Constraint(getVariables());
	    for (int k = 0; k < getVariables().size(); k++) {
		ta.coefficients.put(getVariables().get(k), new Double(0));
	    }
	    processConstraints(constr, ta, 0, constr.size());
	    getConstraints().add(ta);
	}
    }

    private void interpretVariables(List<Token> arrayList) {
	for (Token token : arrayList)
	    if (token.getId() == TokenCode.VARIABLE)
		if (!containsVariavel(token)) {
		    Variable novaVariavel = new Variable(token);
		    getVariables().add(novaVariavel);
		    getVariablesOriginals().add(novaVariavel);
		}
    }

    private void processObjective(ArrayList<Token> t, Objective ta) {
	double value = 1;
	for (Token token : t) {
	    if (token.getId() == TokenCode.SIGNAL) {
		value = Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == TokenCode.NUMBER) {
		value *= Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == TokenCode.VARIABLE) {
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
	    if (token.getId() == TokenCode.SIGNAL) {
		value = Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == TokenCode.NUMBER) {
		value *= Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == TokenCode.VARIABLE) {
		for (int k = 0; k < getVariables().size(); k++) {
		    if (getVariables().get(k).getToken().equals(token)) {
			ta.coefficients.put(getVariables().get(k), value);
			break;
		    }
		}
		value = 1;
	    } else if (token.getId() == TokenCode.MORE_EQUAL
		    || token.getId() == TokenCode.EQUAL
		    || token.getId() == TokenCode.LESS_EQUAL) {
		switch (token.getId()) {
		case MORE_EQUAL:
		    ta.signOfConstraint = SignOfConstraint.MORE_OR_EQUAL;
		    break;
		case EQUAL:
		    ta.signOfConstraint = SignOfConstraint.EQUAL;
		    break;
		case LESS_EQUAL:
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
	    Token token = new Token(Token.TokenCode.VARIABLE, "A" + j, "A" + j);
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
	    Token token = new Token(Token.TokenCode.VARIABLE, "XF" + j, "XF"
		    + j);
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
