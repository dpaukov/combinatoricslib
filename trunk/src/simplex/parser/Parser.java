package simplex.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    private final LinkedList<String> lines = new LinkedList<String>();;

    public Parser(String file) throws FileNotFoundException {
	File f = new File(file);
	Scanner s = null;
	s = new Scanner(f);
	while (s.hasNext()) {
	    lines.add(s.nextLine().trim());
	}
    }

    public Parser(String[] list) {
	for (String s : list)
	    lines.add(s);
    }

    public List<String> getLines() {
	return lines;
    }

    public List<Token> getTokens() {
	return parse(this.lines);
    }

    protected static List<Token> parse(List<String> lines) {
	LinkedList<Token> tokens = new LinkedList<Token>();
	for (String linha : lines) {
	    char[] chars = linha.toCharArray();
	    tokens.add(new Token(-1, null, null));
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
					buffer.toString().charAt(0) == '>' ? Token.MAIOR_IGUAL
						: Token.MENOR_IGUAL,
					buffer.toString().charAt(0) == '>' ? 1
						: -1, buffer.toString()));
		    } else {
			tokens.add(new Token(Token.IGUAL, 0, "="));
		    }
		} else if ("+-".contains(Character.toString(chars[i]))) {
		    tokens.add(new Token(Token.SINAL, chars[i] == '+' ? 1 : -1,
			    Character.toString(chars[i])));
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
			    .add(new Token(Token.NUMERO, Double
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
			tokens.add(new Token(Token.VARIAVEL, "SUBJECT",
				"SUBJECT"));
		    } else if (buffer.toString().toUpperCase().trim().equals(
			    "TO")) {
			if (tokens.size() > 0) {
			    if ("SUBJECT".equals(tokens.getLast().getToken())) {
				tokens.removeLast();
				tokens.add(new Token(Token.SUBJECT_TO, null,
					"SUBJECT TO"));
			    } else {
				tokens
					.add(new Token(Token.VARIAVEL, "TO",
						"TO"));
			    }
			} else {
			    tokens.add(new Token(Token.VARIAVEL, "TO", "TO"));
			}
		    } else if (buffer.toString().toUpperCase().trim().equals(
			    "MAX")) {
			tokens.add(new Token(Token.MAXIMO, null, "MAX"));
		    } else if (buffer.toString().toUpperCase().trim().equals(
			    "MIN")) {
			tokens.add(new Token(Token.MINIMO, null, "MIN"));
		    } else if (buffer.toString().toUpperCase().trim().equals(
			    "END")) {
			tokens.add(new Token(Token.END, null, "END"));
		    } else {
			tokens.add(new Token(Token.VARIAVEL, buffer.toString()
				.toUpperCase().trim(), buffer.toString()
				.toUpperCase().trim()));
		    }
		}
	    }
	}

	return tokens;
    }
}
