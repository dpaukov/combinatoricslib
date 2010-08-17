package org.paukov.simplex.exception;

import org.paukov.simplex.problem.Problem;

/**
 * Specialized Simplex Exception
 *
 */
public class SimplexException extends Exception {

    private static final long serialVersionUID = 64768232452L;
    private final Problem problem;

    /**
     * Constructor
     * @param string A message
     */
    public SimplexException(String string) {
	super(string);
	problem = null;
    }

    /**
     * Counstructor
     * @param string A message
     * @param problem A problem object
     */
    public SimplexException(String string, Problem problem) {
	super(string);
	this.problem = problem;
    }

    /**
     * Returns the problem, which is associated with the exception
     */
    public Problem getProblem() {
	return problem;
    }

}
