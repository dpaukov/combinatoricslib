package simplex.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import simplex.exception.SimplexException;
import simplex.parser.Token;
import simplex.problem.Constraint.SignOfConstraint;

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
	tratarCondicoesDasVariaveis();

	if (!verificarSeTodasVariaveisTemRestricoesIniciais())
	    throw new SimplexException("Error: Not enought constraints");

	// System.out.println(problem.toString());

	inserirVariaveisDeFolgaEAuxiliares();

	// System.out.println(problem.toString());
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
	    if (t.variable.equals(token))
		return true;
	return false;
    }

    private void interpretObjectiveFunction(List<Token> temp) {
	for (int i = 0; i < temp.size(); i++) {
	    Token token = temp.get(i);
	    if (token.getId() == Token.MAXIMO || token.getId() == Token.MINIMO) {
		switch (token.getId()) {
		case Token.MAXIMO:
		    setType(Type.MAX);
		    break;
		case Token.MINIMO:
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
		tratarObjetivo(tokensObjetivo, getObjective());
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
	    tratarRestricao(t, ta, 0, t.size());
	    getConstraints().add(ta);
	}
    }

    private void interpretVariables(List<Token> arrayList) {
	for (Token token : arrayList)
	    if (token.getId() == Token.VARIAVEL)
		if (!containsVariavel(token)) {
		    Variable novaVariavel = new Variable(token);
		    getVariables().add(novaVariavel);
		    getVariablesOriginals().add(novaVariavel);
		}
    }

    private void tratarObjetivo(ArrayList<Token> t, Objective ta) {
	double value = 1;
	for (Token token : t) {
	    if (token.getId() == Token.SINAL) {
		value = Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == Token.NUMERO) {
		value *= Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == Token.VARIAVEL) {
		for (int k = 0; k < getVariables().size(); k++) {
		    if (getVariables().get(k).variable.equals(token)) {
			ta.coefficients.put(getVariables().get(k), value);
			break;
		    }
		}
		value = 1;
	    }
	}
    }

    private void tratarRestricao(ArrayList<Token> t, Constraint ta, int i,
	    int size) {
	double value = 1;
	boolean valorIndependenteEncontrado = false;
	for (Token token : t) {
	    if (valorIndependenteEncontrado) {
		ta.valueindependance = Double.parseDouble(token.getValue()
			.toString());
		break;
	    }
	    if (token.getId() == Token.SINAL) {
		value = Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == Token.NUMERO) {
		value *= Double.parseDouble(token.getValue().toString());
	    } else if (token.getId() == Token.VARIAVEL) {
		for (int k = 0; k < getVariables().size(); k++) {
		    if (getVariables().get(k).variable.equals(token)) {
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
		valorIndependenteEncontrado = true;
	    }
	}
    }

    private void tratarCondicoesDasVariaveis() {
	ArrayList<Constraint> restricoes = getConstraints();

	for (Constraint restricao : restricoes) {
	    boolean isCondicaoInicial = false;
	    boolean encontradoTermo1 = false;
	    for (Variable variavel : restricao.variables) {
		double coeficienteAtual = restricao.coefficients.get(variavel);
		if (!encontradoTermo1) {
		    if (coeficienteAtual == 1) {
			encontradoTermo1 = true;
		    } else if (coeficienteAtual == 0) {
			continue;
		    } else {
			isCondicaoInicial = true;
			break;
		    }
		} else if (coeficienteAtual != 0) {
		    isCondicaoInicial = true;
		    break;
		}
	    }
	    if (isCondicaoInicial
		    || restricao.signOfConstraint != SignOfConstraint.MORE_OR_EQUAL
		    || restricao.valueindependance != 0)
		continue;

	    restricao.restricaoNaoPraticavelParaCalculo = true;
	}
    }

    private boolean verificarSeTodasVariaveisTemRestricoesIniciais() {
	HashMap<Variable, Boolean> variavies = new HashMap<Variable, Boolean>();
	for (Variable variavel : getVariables()) {
	    variavies.put(variavel, false);
	}
	for (Constraint restricao : getConstraints())
	    if (restricao.restricaoNaoPraticavelParaCalculo)
		for (Variable variavel : restricao.variables)
		    if (restricao.coefficients.get(variavel) == 1) {
			variavies.put(variavel, true);
			break;
		    }
	for (Variable variavel : getVariables())
	    if (variavies.get(variavel) == false)
		return false;

	return true;
    }

    private void inserirVariaveisDeFolgaEAuxiliares() {
	int quantidadeDeVariaveisDeFolga = 0;
	int quantidadeDeVariaveisAuxiliares = 0;
	for (Constraint restricao : getConstraints()) {
	    if (!restricao.restricaoNaoPraticavelParaCalculo
		    && restricao.signOfConstraint != SignOfConstraint.EQUAL) {
		quantidadeDeVariaveisDeFolga++;
	    }
	    if (!restricao.restricaoNaoPraticavelParaCalculo
		    && restricao.signOfConstraint != SignOfConstraint.LESS_OR_EQUAL) {
		quantidadeDeVariaveisAuxiliares++;
	    }
	}

	ArrayList<Variable> novasVariaveisDeFolga = new ArrayList<Variable>(
		quantidadeDeVariaveisDeFolga);
	ArrayList<Variable> novasVariaveisAuxiliares = new ArrayList<Variable>(
		quantidadeDeVariaveisAuxiliares);

	criarNovasVariaveisDeFolga(quantidadeDeVariaveisDeFolga,
		novasVariaveisDeFolga);

	inserirNovasVariaveisDeFolga(novasVariaveisDeFolga);

	criarNovasVariaveisAuxiliares(quantidadeDeVariaveisAuxiliares,
		novasVariaveisAuxiliares);

	inserirNovasVariaveisAuxiliares(novasVariaveisAuxiliares);

	for (Constraint restricao : getConstraints()) {
	    if (!restricao.restricaoNaoPraticavelParaCalculo)
		restricao.signOfConstraint = SignOfConstraint.EQUAL;
	}
    }

    private void criarNovasVariaveisAuxiliares(
	    int quantidadeDeVariaveisAuxiliares,
	    ArrayList<Variable> novasVariaveisAuxiliares) {
	for (int j = 1; j <= quantidadeDeVariaveisAuxiliares; j++) {
	    Token token = new Token(Token.VARIAVEL, "A" + j, "A" + j);
	    Variable variavel = new Variable(token);
	    novasVariaveisAuxiliares.add(variavel);
	    getVariables().add(variavel);
	    getVariablesAuxiliares().add(variavel);
	    getObjective().coefficients.put(variavel, -Integer.MAX_VALUE * 1.0);
	}
    }

    private void criarNovasVariaveisDeFolga(int quantidadeDeVariaveisDeFolga,
	    ArrayList<Variable> novasVariaveisDeFolga) {
	for (int j = 1; j <= quantidadeDeVariaveisDeFolga; j++) {
	    Token token = new Token(Token.VARIAVEL, "XF" + j, "XF" + j);
	    Variable variavel = new Variable(token);
	    novasVariaveisDeFolga.add(variavel);
	    getVariables().add(variavel);
	    getVariablesFolga().add(variavel);
	    getObjective().coefficients.put(variavel, 0.0);
	}
    }

    private void inserirNovasVariaveisAuxiliares(
	    ArrayList<Variable> novasVariaveisAuxiliares) {
	int variavelAtual = 0;
	boolean jaAdicionadoNessaRestricao = false;
	for (Constraint restricao : getConstraints()) {
	    for (int i = 0; i < novasVariaveisAuxiliares.size(); i++) {
		if (variavelAtual == i && !jaAdicionadoNessaRestricao) {
		    if (!restricao.restricaoNaoPraticavelParaCalculo
			    && restricao.signOfConstraint != SignOfConstraint.LESS_OR_EQUAL) {
			restricao.coefficients.put(novasVariaveisAuxiliares
				.get(i), 1.0);
			restricao.variavelBasica = novasVariaveisAuxiliares
				.get(i);
			variavelAtual++;
			jaAdicionadoNessaRestricao = true;
		    } else
			restricao.coefficients.put(novasVariaveisAuxiliares
				.get(i), 0.0);
		} else
		    restricao.coefficients.put(novasVariaveisAuxiliares.get(i),
			    0.0);
	    }
	    jaAdicionadoNessaRestricao = false;
	}
    }

    private void inserirNovasVariaveisDeFolga(
	    ArrayList<Variable> novasVariaveisDeFolga) {
	int variavelAtual = 0;
	boolean jaAdicionadoNessaRestricao = false;
	for (Constraint restricao : getConstraints()) {
	    for (int i = 0; i < novasVariaveisDeFolga.size(); i++) {
		if (variavelAtual == i && !jaAdicionadoNessaRestricao) {
		    if (!restricao.restricaoNaoPraticavelParaCalculo
			    && restricao.signOfConstraint != SignOfConstraint.EQUAL) {
			restricao.coefficients
				.put(
					novasVariaveisDeFolga.get(i),
					restricao.signOfConstraint != SignOfConstraint.LESS_OR_EQUAL ? -1.0
						: 1.0);
			restricao.variavelBasica = novasVariaveisDeFolga.get(i);
			variavelAtual++;
			jaAdicionadoNessaRestricao = true;
		    } else
			restricao.coefficients.put(
				novasVariaveisDeFolga.get(i), 0.0);
		} else
		    restricao.coefficients.put(novasVariaveisDeFolga.get(i),
			    0.0);

	    }
	    jaAdicionadoNessaRestricao = false;
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

	for (Constraint restricao : constraints) {
	    string.append(restricao);
	    string.append("\n");
	}
	string.append("-- End of Problem  --");
	return string.toString();
    }

}
