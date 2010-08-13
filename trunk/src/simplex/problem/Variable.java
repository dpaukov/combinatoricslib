package simplex.problem;

import simplex.parser.Token;

public class Variable {

    private final Token token;

    public Variable(Token token) {
	super();
	this.token = token;
    }

    /**
     * @return the variable
     */
    public Token getToken() {
	return token;
    }

    public String toString() {
	return token.getValue().toString();
    }

    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof Variable))
	    return false;
	Variable other = (Variable) obj;
	return token.getToken().equals(other.token.getToken());
    }

}
