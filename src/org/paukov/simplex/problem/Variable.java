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
     * @param token Token object which is associated with the variable
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
    public boolean equals(Object obj) {
	if (!(obj instanceof Variable))
	    return false;
	Variable other = (Variable) obj;
	return token.getToken().equals(other.token.getToken());
    }

}
