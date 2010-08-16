package org.paukov.simplex.exception;

import org.paukov.simplex.problem.Problem;

public class SimplexException extends Exception {

    private static final long serialVersionUID = 64768232452L;
    private final Problem problem;

    public SimplexException(String string) {
	super(string);
	problem = null;
    }

    public SimplexException(String string, Problem problem) {
	super(string);
	this.problem = problem;
    }

    /**
     * @return the problem
     */
    public Problem getProblem() {
	return problem;
    }

}
