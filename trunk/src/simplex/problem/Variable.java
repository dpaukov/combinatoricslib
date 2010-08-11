package simplex.problem;

import simplex.parser.Token;

public class Variable {
    public Token variable;

    public Variable(Token variavel) {
	super();
	this.variable = variavel;
    }

    public String toString() {
	return variable.getValue().toString();
    }

    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof Variable))
	    return false;
	Variable outra = (Variable) obj;
	return variable.getToken().equals(outra.variable.getToken());
    }

}
