package simplex;

import java.util.ArrayList;
import java.util.List;

import simplex.exception.SimplexException;
import simplex.parser.Token;
import simplex.problem.Constraint;
import simplex.problem.Equation;
import simplex.problem.Objective;
import simplex.problem.Problem;
import simplex.problem.Solution;
import simplex.problem.Variable;
import simplex.problem.Problem.Type;

public class Simplex {

    public final Problem problemaSimplex;
    public final List<Token> tokens;

    public Simplex(List<Token> tokens) throws SimplexException {
	this.tokens = tokens;
	problemaSimplex = new Problem(tokens);
    }

    public Solution resolverSimplex() {
	if (problemaSimplex.getType() == Type.MAX)
	    objetivo().mult(-1.0);

	int iteracao = 0;
	while (!isSolucaoOtima()) {
	    System.out.printf("Iteration %d\n", iteracao);
	    System.out.println(problemaSimplex.getObjective());
	    for (Constraint restricao : restricoes())
		if (!restricao.restricaoNaoPraticavelParaCalculo)
		    System.out.println(restricao);

	    calcularNovaEntrada();
	    System.out.println("...:::...:::...:::...");
	    iteracao++;
	}
	System.out.printf("Iteration %d\n", iteracao);
	System.out.println(problemaSimplex.getObjective());
	for (Constraint restricao : restricoes())
	    if (!restricao.restricaoNaoPraticavelParaCalculo)
		System.out.println(restricao);
	System.out.println("...:::...:::...:::...");

	Solution solutionSimplex = new Solution();
	solutionSimplex.problem = problemaSimplex;
	solutionSimplex.objectiveFunction = objetivo();
	for (Variable variavel : variaveis()) {
	    solutionSimplex.results.put(variavel, 0.0);
	}
	for (Constraint restricao : restricoes()) {
	    solutionSimplex.results.put(restricao.variavelBasica,
		    restricao.valueindependance);
	}
	System.out.println(solutionSimplex);
	return solutionSimplex;
    }

    private void calcularNovaEntrada() {
	Variable colunaPivo = getColunaPivo();
	Constraint linhaPivo = (Constraint) getLinhaPivo(colunaPivo);

	Double numeroPivo = linhaPivo.coefficients.get(colunaPivo);
	System.out.println("Linha Pivo: " + linhaPivo);
	linhaPivo.div(numeroPivo);
	linhaPivo.variavelBasica = colunaPivo;
	Constraint linhaPivoClone = linhaPivo.clone();

	linhaPivoClone.mult(objetivo().coefficients.get(colunaPivo));
	objetivo().subtract(linhaPivoClone);
	linhaPivoClone = linhaPivo.clone();

	for (Constraint restricao : restricoes()) {
	    if (!restricao.restricaoNaoPraticavelParaCalculo
		    && restricao != linhaPivo) {
		linhaPivoClone.mult(restricao.coefficients.get(colunaPivo));
		restricao.subtract(linhaPivoClone);
		linhaPivoClone = linhaPivo.clone();
	    }
	}
    }

    private Objective objetivo() {
	return problemaSimplex.getObjective();
    }

    private boolean isSolucaoOtima() {

	for (Variable variavel : variaveis())
	    if (getCoeficienteEmObjetivo(variavel) < 0.0)
		return false;

	return true;

    }

    private Variable getColunaPivo() {

	Variable colunaPivo = variaveis().get(0);

	boolean allPositives = true;
	for (Variable variavel : variaveis()) {
	    if (getCoeficienteEmObjetivo(variavel) < getCoeficienteEmObjetivo(colunaPivo))
		colunaPivo = variavel;
	    if (getCoeficienteEmObjetivo(variavel) < 0)
		allPositives = false;
	}
	return allPositives ? null : colunaPivo;
    }

    private List<Variable> variaveis() {
	return problemaSimplex.getVariables();
    }

    private Double getCoeficienteEmObjetivo(Variable variavel) {
	return problemaSimplex.getObjective().coefficients.get(variavel);
    }

    private Equation getLinhaPivo(Variable colunaPivo) {

	Equation linhaPivo = restricoes().get(0);

	double minimo = Double.POSITIVE_INFINITY;
	for (int i = 0; i < restricoes().size(); i++) {
	    if (getCoeficienteDaRestricao(colunaPivo, i) > 0
		    && (!restricoes().get(i).restricaoNaoPraticavelParaCalculo)) {
		double possivelMinimo = (restricoes().get(i).valueindependance)
			/ getCoeficienteDaRestricao(colunaPivo, i);
		if (minimo > possivelMinimo) {
		    minimo = possivelMinimo;
		    linhaPivo = restricoes().get(i);
		}
	    }
	}
	return linhaPivo;
    }

    // +1 +0 +1 +0 +0 = +3
    // +0 +1 +0 +1 +0 = +4
    // +1 +2 +0 +0 +1 = +9
    private Double getCoeficienteDaRestricao(Variable colunaPivo, int i) {
	return restricoes().get(i).coefficients.get(colunaPivo);
    }

    private ArrayList<Constraint> restricoes() {
	return problemaSimplex.getConstraints();
    }
}