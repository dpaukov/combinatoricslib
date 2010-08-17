package org.paukov.simplex.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.paukov.simplex.parser.Token.TokenCode;

/**
 * This class encapsulates the parser of the problem description's file
 * 
 */
public class Parser {

    /**
     * List of lines
     */
    private final List<String> lines = new LinkedList<String>();

    /**
     * Constructor
     * 
     * @param filePath
     *            Path to the file
     * @throws FileNotFoundException
     */
    public Parser(String filePath) throws FileNotFoundException {
	File f = new File(filePath);
	Scanner s = null;
	s = new Scanner(f);
	while (s.hasNext()) {
	    lines.add(s.nextLine().trim());
	}
    }

    /**
     * Constructor
     * 
     * @param list
     *            List of the lines, which are representing the problem
     *            description
     */
    public Parser(String[] list) {
	for (String s : list)
	    lines.add(s);
    }

    /**
     * Returns the list of the line
     */
    public List<String> getLines() {
	return lines;
    }

    /**
     * Returns the list of the token
     */
    public List<Token> getTokens() {
	return parse(this.lines);
    }

    /**
     * Parses the list of the lines
     */
    protected static List<Token> parse(List<String> lines) {
	LinkedList<Token> tokens = new LinkedList<Token>();
	for (String line : lines) {
	    char[] chars = line.toCharArray();
	    tokens.add(new Token(TokenCode.INITIAL_LINE, null, null));
	    for (int i = 0; i < chars.length; i++) {
		if ("<>=".contains(Character.toString(chars[i]))) {
		    if ("<>".contains(Character.toString(chars[i]))) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(chars[i]);
			i++;
			if (i >= chars.length)
			    break;
			if (chars[i] == '=')
			    buffer.append(chars[i]);

			tokens
				.add(new Token(
					buffer.toString().charAt(0) == '>' ? TokenCode.MORE_EQUAL
						: TokenCode.LESS_EQUAL,
					buffer.toString().charAt(0) == '>' ? 1
						: -1, buffer.toString()));
		    } else {
			tokens.add(new Token(TokenCode.EQUAL, 0, "="));
		    }
		} else if ("+-".contains(Character.toString(chars[i]))) {
		    tokens.add(new Token(TokenCode.SIGNAL, chars[i] == '+' ? 1
			    : -1, Character.toString(chars[i])));
		} else if (Character.isDigit(chars[i])) {
		    StringBuffer buffer = new StringBuffer();
		    do {
			buffer.append(chars[i]);
			i++;
			if (i >= chars.length)
			    break;
		    } while (Character.isDigit(chars[i]) || (chars[i] == '.')
			    && (i < chars.length));
		    if (buffer.toString().charAt(buffer.length() - 1) == '.')
			buffer.append('0');
		    tokens
			    .add(new Token(TokenCode.NUMBER, Double
				    .parseDouble(buffer.toString()), buffer
				    .toString()));
		    i--;
		} else if (Character.isLetter(chars[i])) {
		    StringBuffer buffer = new StringBuffer();
		    do {
			buffer.append(chars[i]);
			i++;
			if (i >= chars.length)
			    break;
		    } while (Character.isLetterOrDigit(chars[i])
			    && (i < chars.length));
		    if (buffer.toString().toUpperCase().trim()
			    .equals("SUBJECT")) {
			tokens.add(new Token(TokenCode.VARIABLE, "SUBJECT",
				"SUBJECT"));
		    } else if (buffer.toString().toUpperCase().trim().equals(
			    "TO")) {
			if (tokens.size() > 0) {
			    if ("SUBJECT".equals(tokens.getLast().getTokenString())) {
				tokens.removeLast();
				tokens.add(new Token(TokenCode.SUBJECT_TO,
					null, "SUBJECT TO"));
			    } else {
				tokens.add(new Token(TokenCode.VARIABLE, "TO",
					"TO"));
			    }
			} else {
			    tokens
				    .add(new Token(TokenCode.VARIABLE, "TO",
					    "TO"));
			}
		    } else if (buffer.toString().toUpperCase().trim().equals(
			    "MAX")) {
			tokens.add(new Token(TokenCode.MAX, null, "MAX"));
		    } else if (buffer.toString().toUpperCase().trim().equals(
			    "MIN")) {
			tokens.add(new Token(TokenCode.MIN, null, "MIN"));
		    } else if (buffer.toString().toUpperCase().trim().equals(
			    "END")) {
			tokens.add(new Token(TokenCode.END, null, "END"));
		    } else {
			tokens.add(new Token(TokenCode.VARIABLE, buffer
				.toString().toUpperCase().trim(), buffer
				.toString().toUpperCase().trim()));
		    }
		}
	    }
	}

	return tokens;
    }
}
