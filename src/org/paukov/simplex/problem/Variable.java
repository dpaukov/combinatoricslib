package org.paukov.simplex.problem;

import org.paukov.simplex.parser.Token;

/**
 * This class represents variables
 * 
 */
public class Variable {

    /**
     * Token object
     */
    private final Token token;

    /**
     * Constructor
     * 
     * @param token
     *            Token object which is associated with the variable
     */
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
    public int hashCode() {
	final int prime = 33;
	int result = 3;
	result = prime * result + ((token == null) ? 0 : token.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof Variable))
	    return false;
	Variable other = (Variable) obj;
	return token.getTokenString().equals(other.token.getTokenString());
    }

}
