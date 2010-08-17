package org.paukov.simplex.parser;

/**
 * This class represents tokens
 * 
 */
public class Token {

    public enum TokenCode {
	INITIAL_LINE, VARIABLE, SUBJECT_TO, MAX, MIN, END, NUMBER, SIGNAL, MORE_EQUAL, LESS_EQUAL, EQUAL
    }

    private TokenCode id;
    private Object value;
    private String tokenString;

    /**
     * Constructor
     * @param id
     * @param value
     * @param tokenString
     */
    public Token(TokenCode id, Object value, String tokenString) {
	super();
	this.id = id;
	this.value = value;
	this.tokenString = tokenString;
    }
    
    /**
     * Constructor
     * @param id
     * @param value
     * @param tokenString
     */
    public Token(Token token) {
	super();
	this.id = token.id;
	this.value = token.value;
	this.tokenString = token.tokenString;
    }

    /**
     * @return the id
     */
    public TokenCode getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(TokenCode id) {
	this.id = id;
    }

    /**
     * @return the value
     */
    public Object getValue() {
	return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(Object value) {
	this.value = value;
    }

    /**
     * @return the tokenString
     */
    public String getTokenString() {
	return tokenString;
    }

    /**
     * @param tokenString
     *            the tokenString to set
     */
    public void setTokenString(String tokenString) {
	this.tokenString = tokenString;
    }

    public String toString() {
	return "Token id=<" + id + ">, tokenString=<" + tokenString
		+ ">, value=<" + value + ">";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((tokenString == null) ? 0 : tokenString.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Token other = (Token) obj;
	if (tokenString == null) {
	    if (other.tokenString != null)
		return false;
	} else if (!tokenString.equals(other.tokenString))
	    return false;
	return true;
    }
}
